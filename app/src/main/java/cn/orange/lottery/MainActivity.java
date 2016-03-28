package cn.orange.lottery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.orange.drawLine.LotteryContentView;
import cn.orange.drawText.textLocaltion;
import cn.orange.net.Config;
import cn.orange.net.getAnalyze;
import cn.orange.net.getLatestLottery;
import cn.orange.net.getLatestLottery.OnFailedCallBack;
import cn.orange.net.getLatestLottery.OnSccussedCallBack;
import cn.orange.net.latestlottery;
import cn.orange.net.tableData;
import cn.orange.version.getVersion;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity implements View.OnClickListener{
	public static TextView message;
	public int requsetFailedCount=0;
	public boolean firstrun;//是否是首次运行
	public static boolean newData=false;  //false表示没有最新数据
	public  Timer timer=new Timer();//-----------------------高危
	public boolean isMainCorvered=false;
	private TimerTask timerTask=null;
	public View view;
	private static String FIRST_RUN="first";
	private boolean first;
	private boolean returnPressed=true;
	private Button bt_return,bt_yesterday,bt_day_before;
	public SharedPreferences shared2;
	public SharedPreferences shared;
	public int requestCount=1;//当requestCount>2时就不再更新失败情况下的界面
	public static MainActivity mainActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity=this;
		firstrun = true;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//定义全屏参数
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=MainActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
		DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
		int screen_width_origin=displayMetrics.widthPixels;
		int screen_height_origin=displayMetrics.heightPixels;
		if(screen_height_origin>screen_width_origin){
			LotteryContentView.flag=false ;//手机尺寸,高大于宽
		}else{
			LotteryContentView.flag=true;//电脑尺寸
		}
		getNetConnect();
		if(LotteryContentView.flag){//true宽大于高,false高大于宽
			setContentView(R.layout.main_lottery);//电脑尺寸
			
		}else{ 
			setContentView(R.layout.main_lottery_height_in_mobile);//手机尺寸
			shared2=MainActivity.this.getSharedPreferences(Config.APP_NAME_TABLE, Context.MODE_PRIVATE);
			shared=this.getSharedPreferences("setting", Context.MODE_PRIVATE);
			first=shared.getBoolean(FIRST_RUN, true);
		}
		initView();
		initListener();
		startCount();//计时器
		new getVersion(this).getServerVersion();//获取版本消息
	}

	private void initListener() {
		bt_day_before.setOnClickListener(this);
		bt_return.setOnClickListener(this);
		bt_yesterday.setOnClickListener(this);
		
	}
	public void initView(){
		view=findViewById(R.id.lotteryContent);//绘图区
		message=(TextView) findViewById(R.id.message);
		bt_return=(Button) findViewById(R.id.return_back);
		bt_yesterday=(Button) findViewById(R.id.yesterday);
		bt_day_before=(Button) findViewById(R.id.day_before);
		
	}
	private Handler handler=new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				if(isMainCorvered==false){

//					view.invalidate();
				    Intent sound=new Intent(MainActivity.this,SoundPlayActivity.class);//人物界面
				    Intent missed=new Intent(MainActivity.this,MissedTableActivity.class);//表格界面
				    startActivity(missed);
				    startActivity(sound);

					//showGroupNumer(); do in missedTable
					}
				break;
			}
		};//1326
	};
	public void startCount(){
		if(timerTask==null){
			timerTask=new TimerTask() {
				@Override
				public void run() {
						getNetConnect();
				}
			};
			timer.schedule(timerTask, 10*1000 , 1000*10);
		}
	}
	public void getNetConnect(){
		new getLatestLottery(new getLatestLottery.OnSccussedCallBack() {
			@Override
			public void successed(final String current_missed,final String max_missed,final List<latestlottery> latestlotteries) {
				String s=Config.getCachedCurrentMissData(MainActivity.this);
				
				if(!s.equals(current_missed)) {
					newData=true;
					requsetFailedCount=1;
					LotteryContentView.latestLotteryData=latestlotteries.subList(10, 78);
					String[] get_current_missed_data=current_missed.split(",");
					String[] get_max_missed_data=max_missed.split(",");
					textLocaltion.max_missed_data=get_max_missed_data;
					textLocaltion.current_missed_data=get_current_missed_data;
					getAnalysis();
					view.invalidate();
					handler.sendEmptyMessage(1);
					Config.setCachedCurrentMissData(MainActivity.this, current_missed);
					Config.setCachedMaxMissData(MainActivity.this, max_missed);
					Config.setCachedLotteryData(MainActivity.this, LotteryContentView.latestLotteryData);
					
					Config.setYesTCachedData(MainActivity.this,  LotteryContentView.latestLotteryData,current_missed,max_missed);
				}
				else{
					//Toast.makeText(MainActivity.this, "requetNodata", Toast.LENGTH_SHORT).show();
					newData=false;
					if(requsetFailedCount<=1){
					requsetFailedCount=2;
					LotteryContentView.latestLotteryData=Config.getCachedLotteryData(MainActivity.this);
					String[] get_current_missed_data=Config.getCachedCurrentMissData(MainActivity.this).split(",");
					String[] get_max_missed_data=Config.getCachedMaxMissData(MainActivity.this).split(",");
					textLocaltion.max_missed_data=get_max_missed_data;
					textLocaltion.current_missed_data=get_current_missed_data;
					showGroupNumer();
					
					view.invalidate();
					}
				}
			}
		}, new getLatestLottery.OnFailedCallBack() {
			@Override
			public void failed(int error) {//失败则从本地取出数据
				requestCount++;
				newData=false;
				if (requestCount <4) {
					showGroupNumer();
					LotteryContentView.latestLotteryData = Config.getCachedLotteryData(MainActivity.this);
					String[] get_current_missed_data = Config.getCachedCurrentMissData(MainActivity.this).split(",");
					String[] get_max_missed_data = Config.getCachedMaxMissData(MainActivity.this).split(",");
					textLocaltion.max_missed_data = get_max_missed_data;
					textLocaltion.current_missed_data = get_current_missed_data;
					view.invalidate();
					switch (error) {
						case Config.RESULT_JSON_EXCEPECTION:
							Toast.makeText(MainActivity.this, getResources().getString(R.string.json_exception), Toast.LENGTH_LONG).show();
							break;
						case Config.RESULT_STATUS_FAIL:
							Toast.makeText(MainActivity.this, getResources().getString(R.string.Failed_to_login), Toast.LENGTH_SHORT).show();
							break;
					}
				}
			}
		},Config.SERVER_URL+Config.GET_LATEST_LOTTERY);
	}

	public void showGroupNumer(){
		List<tableData> tableDatas = Config.getCachedTableData(this);
		for (int i = 0; i < tableDatas.size(); i++) {
			SoundPlayActivity.lottery[5 + i] = tableDatas.get(i).getGroup_number();
		}
		message.setText("根据遗漏情况,本期二码关注" + SoundPlayActivity.lottery[5] + ", " +
				SoundPlayActivity.lottery[6] + ", " + SoundPlayActivity.lottery[7] + "  三码关注" + SoundPlayActivity.lottery[8] + ", " + SoundPlayActivity.lottery[9] +
				", " + SoundPlayActivity.lottery[10]);

	}
	@Override
	protected void onStop() {
		super.onStop();
		Editor editor=MainActivity.this.getSharedPreferences("setting", Context.MODE_WORLD_READABLE).edit();
		if(first){
			editor.putBoolean(FIRST_RUN, false);
		}
		editor.commit();
	}
	@Override
	protected void onDestroy() {
		isMainCorvered=true;
		super.onDestroy();
		timer.cancel();
	}
	@Override
	protected void onPause() {
		isMainCorvered=true;
		super.onPause();

	}
	@Override
	protected void onResume() {
		isMainCorvered=false;
		super.onResume();
	}
	/**
	 * 
	 */
	public void getAnalysis(){
		new getAnalyze(new getAnalyze.OnSccussedCallBack() {
			
			@Override
			public void successed(String analyze) {
				Log.d("analyze",analyze);
				Config.setAnalyzeData(analyze, MainActivity.this);
			}
		}, new getAnalyze.OnFailedCallBack() {
			
			@Override
			public void failed(int error) {
				switch (error) {
				case Config.RESULT_JSON_EXCEPECTION:
					Toast.makeText(MainActivity.this, "json解析异常", Toast.LENGTH_SHORT).show();
					break;

				case Config.RESULT_STATUS_FAIL:
					Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}
	public void getYestLotteryData(String url){
		new getLatestLottery(new OnSccussedCallBack() {
			
			@Override
			public void successed(String current_missed, String max_missed,
					List<latestlottery> latestlotteries) {
				LotteryContentView.latestLotteryData=latestlotteries;
				String[] get_current_missed_data=current_missed.split(",");
				String[] get_max_missed_data=max_missed.split(",");
				textLocaltion.max_missed_data=get_max_missed_data;
				textLocaltion.current_missed_data=get_current_missed_data;
				view.invalidate();
			}
		}, new OnFailedCallBack(){

			@Override
			public void failed(int error) {
				if(error==Config.RESULT_JSON_EXCEPECTION)
					Toast.makeText(MainActivity.this, "数据为空", Toast.LENGTH_SHORT).show();
				else Toast.makeText(MainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
			}
			
		},url);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.yesterday:
			if(!LotteryContentView.flag){
				bt_day_before.setBackgroundResource(R.drawable.the_daybefore_pressed);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday);
				bt_return.setBackgroundResource(R.drawable.return_back_pressed);
			}else{
				bt_day_before.setBackgroundResource(R.drawable.the_daybefore_pressed_r);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday_r);
				bt_return.setBackgroundResource(R.drawable.return_back_pressed_r);
			}
//			LotteryContentView.latestLotteryData=Config.getYesTChachedData(MainActivity.this);
			Toast.makeText(MainActivity.this, "昨天数据", Toast.LENGTH_SHORT).show();
			getYestLotteryData(Config.YESTERDAT_URL);
			returnPressed=false;
			break;
		case R.id.return_back:
			if(!LotteryContentView.flag){
				bt_day_before.setBackgroundResource(R.drawable.the_day_before);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday);
			}else{
				bt_day_before.setBackgroundResource(R.drawable.the_day_before_r);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday_r);
			}
			
			if(!returnPressed){
				bt_return.setBackgroundResource(R.drawable.return_back);
				bt_yesterday.setVisibility(View.GONE);
				bt_day_before.setVisibility(View.GONE);
				Toast.makeText(MainActivity.this, "返回今日数据", Toast.LENGTH_SHORT).show();
	    		LotteryContentView.latestLotteryData=Config.getCachedLotteryData(MainActivity.this);
				view.invalidate();
				returnPressed=true;
			}else{
				if(!LotteryContentView.flag)
				bt_return.setBackgroundResource(R.drawable.return_back_pressed);
				else
					bt_return.setBackgroundResource(R.drawable.return_back_pressed_r);
				bt_yesterday.setVisibility(View.VISIBLE);
				bt_day_before.setVisibility(View.VISIBLE);
				returnPressed=false;
			}
			
			break;
			
		case R.id.day_before:
			if(!LotteryContentView.flag){
				bt_day_before.setBackgroundResource(R.drawable.the_day_before);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday_pressed);
				bt_return.setBackgroundResource(R.drawable.return_back_pressed);
			}else{
				bt_day_before.setBackgroundResource(R.drawable.the_day_before_r);
				bt_yesterday.setBackgroundResource(R.drawable.yesterday_pressed_r);
				bt_return.setBackgroundResource(R.drawable.return_back_pressed_r);
			}
			
			Toast.makeText(MainActivity.this, "前天数据", Toast.LENGTH_SHORT).show();
			getYestLotteryData(Config.THE_DAY_BEFORE_URL);
//			LotteryContentView.latestLotteryData=Config.getTheDayBCacheData(MainActivity.this);
			
			returnPressed=false;
			//progressDialog.dismiss();
			break;
		}
	}

	public  final long DOUBLE_CLICK_PERIOD=500;
	private long pressed;

	@Override
	public void onBackPressed() {
			if (DOUBLE_CLICK_PERIOD + pressed > System.currentTimeMillis()) {
				super.onBackPressed();
			}
			Toast.makeText(this,"双击退出",Toast.LENGTH_SHORT).show();
			pressed = System.currentTimeMillis();
	}
}
