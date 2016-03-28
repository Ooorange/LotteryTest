package cn.orange.lottery;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.orange.drawLine.LotteryContentView;

public class SoundPlayActivity extends Activity {
	/*********************** 文字转语音 ************************/
	public static String[] lottery=new String[11];
	public static TextView word;
	public static String world_speak_buff="";
	private ImageView speak;
	private AnimationDrawable animationDrawable;
	public Toast toast;
	public Dialog mDialog;
	static SoundPlayActivity instance=null;
	public AudioManager mAudioManager;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//定义全屏参数
        int flag=WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(LotteryContentView.flag){//true宽大于高,false高大于宽   
			setContentView(R.layout.soundplay_aty);//电脑尺寸
		}else{
			setContentView(R.layout.soundplay_aty_height_in_mobile);//手机尺寸
		}
		instance=this;
		
		mDialog = new Dialog(this);
		toast = new Toast(this);
		speak=(ImageView) findViewById(R.id.speaker);
		word=(TextView) findViewById(R.id.wordToSpeak);
		System.out.println("rotationX="+word.getRotationX()+"rotationY"+word.getRotationY());
		if(LotteryContentView.flag){
			speak.setImageResource(R.drawable.speak_switcher);//电脑尺寸
		}else{
			speak.setImageResource(R.drawable.speak_switcher_height);
		}
		
		animationDrawable=(AnimationDrawable) speak.getDrawable();
		animationDrawable.start();           //开始动画
		
		/* ********************** 文字语音播报 ************* */
		timer.start();

		instance=this;
		
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

	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}
/*	@SuppressLint("HandlerLeak")
	public Handler handler =new Handler(){
		public void handleMessage(android.os.Message msg) {
			SoundPlay.this.finish();
		};
	};*/

	@Override
	public void onBackPressed() {
	}
}
