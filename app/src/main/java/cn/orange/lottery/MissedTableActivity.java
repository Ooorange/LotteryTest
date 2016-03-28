package cn.orange.lottery;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Util.MergerMp3;
import cn.orange.drawLine.LotteryContentView;
import cn.orange.drawText.gridLocation;
import cn.orange.net.Config;
import cn.orange.net.getTableData;
import cn.orange.net.tableData;

public class MissedTableActivity extends Activity {
	public Toast toast;
	public Dialog mDialog;
	private static final int REQ_TTS_STATUS_CHECK = 0;
	private TextToSpeech mTts;
	public static String[] lottery=new String[11];
	static MissedTableActivity instance=null;
	public float textSize;
	MergerMp3 mp3;
	public static List<tableData> tabledatas=new ArrayList<tableData>();
	private TextView Num,Pro,seq,Curr,maxM,
					 t_1_2,t_1_3,t_1_4,t_1_5,t_1_6,
					 t_2_2,t_2_3,t_2_4,t_2_5,t_2_6,
					 t_3_2,t_3_3,t_3_4,t_3_5,t_3_6,
					 t_4_2,t_4_3,t_4_4,t_4_5,t_4_6,
					 t_5_2,t_5_3,t_5_4,t_5_5,t_5_6,
					 t_6_2,t_6_3,t_6_4,t_6_5,t_6_6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//定义全屏参数
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
		mp3= new MergerMp3(this);
		getConnect();
		if(LotteryContentView.flag){//true宽大于高,false高大于宽 
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.missed_table_aty);
			//电脑尺寸
		}else{
			setTitleColor(Color.rgb(255, 255, 255));
			setTitle("概率统计");
			setContentView(R.layout.missed_table_aty_in_moblie);
		}
		windowSet();		//设置窗口大小
		initView();		//。。。。find
		instance=this;
		textSize=gridLocation.gridWidth;
		timer.start();
	}
	public void playSound(){
		int[] soundData = new int[11];
		for(int i=0;i<soundData.length;i++){
			soundData[i]=Integer.parseInt(SoundPlayActivity.lottery[i]);
			Log.d("--", soundData[i]+"");
		}
		if(mp3==null){
			mp3=new MergerMp3(this);
		}
		mp3.setLotterys(soundData);
		mp3.play();
	}
	public  void getConnect(){
		new getTableData(new getTableData.OnSccussedCallBack() {
			
			@Override
			public void successed(final List<tableData> tabledata) {
				Config.setCachedTableData(MissedTableActivity.this, tabledata);
				tabledatas=tabledata;
				setTextViewStyle();


				for (int i = 0; i < tabledata.size(); i++) {
					SoundPlayActivity.lottery[5 + i] = tabledata.get(i).getGroup_number();
					Log.d("static",""+tabledata.get(i).getCurrent_group_miss());
				}
				/*for(int i=0;i<4;i++){
					SoundPlayActivity.lottery[i]=
				}*/
				SoundPlayActivity.word.setText("本期开奖号码为 " + SoundPlayActivity.lottery[0] + " " + SoundPlayActivity.lottery[1]
						+ " " + SoundPlayActivity.lottery[2] + " " + SoundPlayActivity.lottery[3] + ",类型为组" + SoundPlayActivity.lottery[4]
						+ ",根据遗漏情况,本期二码关注" +
						SoundPlayActivity.lottery[5] + ", " + SoundPlayActivity.lottery[6] + ", " + SoundPlayActivity.lottery[7] + "  三码关注" +
						SoundPlayActivity.lottery[8] + ", " + SoundPlayActivity.lottery[9] + ", " + SoundPlayActivity.lottery[10] + ",祝大家中奖,再见");
				
				MainActivity.message.setText("根据遗漏情况,本期二码关注" + SoundPlayActivity.lottery[5] + ", " +
						SoundPlayActivity.lottery[6] + ", " + SoundPlayActivity.lottery[7] + "  三码关注" + SoundPlayActivity.lottery[8] + ", " + SoundPlayActivity.lottery[9] +
						", " + SoundPlayActivity.lottery[10]);
				playSound();
			}
		}, new getTableData.OnFailedCallBack() {
			
			@Override
			public void failed(int error) {
				tabledatas=Config.getCachedTableData(MissedTableActivity.this);
				setTextViewStyle();
				switch (error) {
				case Config.RESULT_STATUS_FAIL:
					break;
				case Config.RESULT_JSON_EXCEPECTION:
					break;
				default:
					break;
				}
			}
		});
	}
	CountDownTimer timer=new CountDownTimer(30000, 150) {//long millisInFuture倒计时总时间数, long countDownInterval倒计时执行频率
		
		@Override
		public void onTick(long millisUntilFinished) {
		}
		
		@Override
		public void onFinish() {
			finish();
		}
	};
	@SuppressWarnings("deprecation")
	public void windowSet() {
		
		
		WindowManager layoutParams = getWindowManager();
		Display d = layoutParams.getDefaultDisplay();

		WindowManager.LayoutParams p = getWindow().getAttributes();
		if(LotteryContentView.flag){//true宽大于高,false高大于宽
			p.gravity=Gravity.RIGHT|Gravity.BOTTOM;//电脑
			p.x=5;
			p.height = (int) (d.getHeight() * 0.5);
			p.width = (int) (d.getWidth() * 0.4);
		}else{
			p.gravity=Gravity.RIGHT|Gravity.TOP;
			p.x=5;
			p.height = (int) (d.getHeight() * 0.4);
			p.width = (int) (d.getWidth() * 0.5);
		}
		getWindow().setAttributes(p);
	}
	public void initView(){
//		 serise=(TextView) findViewById(R.id.serise);
		 Num=(TextView) findViewById(R.id.Num);
		 Pro=(TextView) findViewById(R.id.Pro);
		 seq=(TextView) findViewById(R.id.seq);
		 Curr=(TextView) findViewById(R.id.Curr);
		 maxM=(TextView) findViewById(R.id.maxM);
		
	   	 t_1_2=(TextView) findViewById(R.id.N_1);
		 t_1_3=(TextView) findViewById(R.id.P_1);
		 t_1_4=(TextView) findViewById(R.id.T_1);
		 t_1_5=(TextView) findViewById(R.id.C_1);
		 t_1_6=(TextView) findViewById(R.id.M_1);
		
		 t_2_2=(TextView) findViewById(R.id.N_2);
		 t_2_3=(TextView) findViewById(R.id.P_2);
		 t_2_4=(TextView) findViewById(R.id.T_2);
		 t_2_5=(TextView) findViewById(R.id.C_2);
		 t_2_6=(TextView) findViewById(R.id.M_2);
		 
		 t_3_2=(TextView) findViewById(R.id.N_3);
		 t_3_3=(TextView) findViewById(R.id.P_3);
		 t_3_4=(TextView) findViewById(R.id.T_3);
		 t_3_5=(TextView) findViewById(R.id.C_3);
		 t_3_6=(TextView) findViewById(R.id.M_3);
		 
		 t_4_2=(TextView) findViewById(R.id.N_4);
		 t_4_3=(TextView) findViewById(R.id.P_4);
		 t_4_4=(TextView) findViewById(R.id.T_4);
		 t_4_5=(TextView) findViewById(R.id.C_4);
		 t_4_6=(TextView) findViewById(R.id.M_4);
		 
		 t_5_2=(TextView) findViewById(R.id.N_5);
		 t_5_3=(TextView) findViewById(R.id.P_5);
		 t_5_4=(TextView) findViewById(R.id.T_5);
		 t_5_5=(TextView) findViewById(R.id.C_5);
		 t_5_6=(TextView) findViewById(R.id.M_5);
		 
		 t_6_2=(TextView) findViewById(R.id.N_6);
		 t_6_3=(TextView) findViewById(R.id.P_6);
		 t_6_4=(TextView) findViewById(R.id.T_6);
		 t_6_5=(TextView) findViewById(R.id.C_6);
		 t_6_6=(TextView) findViewById(R.id.M_6);

	}
    public void setTextViewStyle(){
    	TextView[] title={Num,Pro,seq,Curr,maxM};
		/*for (int i = 0; i < title.length; i++) {
			title[i].setTextSize((float) (textSize*0.9));
		}*/
		
		TextView[][] tableData={
				 {t_1_2,t_1_3,t_1_4,t_1_5,t_1_6},
				 {t_2_2,t_2_3,t_2_4,t_2_5,t_2_6},
				 {t_3_2,t_3_3,t_3_4,t_3_5,t_3_6},
				 {t_4_2,t_4_3,t_4_4,t_4_5,t_4_6},
				 {t_5_2,t_5_3,t_5_4,t_5_5,t_5_6},
				 {t_6_2,t_6_3,t_6_4,t_6_5,t_6_6}};
		
//		for (int i = 0; i < 6; i++) {
//			for(int j=0; j< 5;j++)
//			tableData[i][j].setTextSize((float) (textSize*0.5));
//				;
//		}
		
		for(int i=0,k=0;i<tabledatas.size();i++,k++){
			tableData ObjectTableData=tabledatas.get(i);
			
			SoundPlayActivity.lottery[5+i]=ObjectTableData.getGroup_number();

			lottery[5+i]=ObjectTableData.getGroup_number();
			
			tableData[k][0].setText(ObjectTableData.getGroup_number());
			tableData[k][1].setText("2.98%");
			tableData[k][2].setText(ObjectTableData.getFrequency());
			tableData[k][3].setText(ObjectTableData.getCurrent_group_miss());
			tableData[k][4].setText(ObjectTableData.getMax_group_miss());
		}
		
    }
    @Override
    protected void onDestroy() {
    	if(mp3!=null)
    	mp3.stop();
    	timer.cancel();
    	super.onDestroy();
    }

	@Override
	public void onBackPressed() {

	}
}
