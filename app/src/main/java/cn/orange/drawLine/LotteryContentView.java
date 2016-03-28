package cn.orange.drawLine;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.orange.drawText.gridLocation;
import cn.orange.drawText.textLocaltion;
import cn.orange.net.Config;
import cn.orange.net.latestlottery;

public class LotteryContentView extends View{
	private static int screen_width;
	private static int screen_height;
	public static boolean flag=true;//true宽大于高,false高大于宽
	public static final String one="4";
	public static String[] current_missed_data=new String[40];
	public static String[] max_missed_data=new String[40];
	public static List<latestlottery> latestLotteryData=new ArrayList<latestlottery>();
	public static final String twoNumer="01";
	public ArrayList<Integer[]> storeList=new ArrayList<Integer[]>();
	public static float max;
	static int ROW=0;
	public static float wid;
	public gridLocation lo;
	public Context context_;
	static final int LOCAX=5;
	static final int LOCAy=17;
	DrawGrid drawgrid=null;
	textLocaltion drawText=null;
	BgColor bgcolor=null;
	public static String anasis="0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
	HashMap<Integer, PointF>gridCenter=gridLocation.getGridLocation();
	public LotteryContentView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		context_=context;
	}
	public LotteryContentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		context_=context;
	}
	public LotteryContentView(Context context) {
		super(context);
		context_=context;
//		init();
	}
	@SuppressLint("DrawAllocation")
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 screen_width = MeasureSpec.getSize(widthMeasureSpec);

		 screen_height = MeasureSpec.getSize(heightMeasureSpec);
		 max=screen_height>screen_width?screen_height:screen_width;
		 System.out.println("布局宽" + screen_width + "布局高" + screen_height);
		 if(!flag){
			 lo=new gridLocation(screen_width,screen_height);
			 drawgrid=new DrawGrid(LotteryContentView.this,screen_width,screen_height,context_); 
			// flag=false;//false高大于宽，手机尺寸
		 }else{
			 lo=new gridLocation(screen_height, screen_width);
			 drawgrid=new DrawGrid(LotteryContentView.this,screen_height,screen_width,context_);
			// flag=true;//true宽大于高，电脑尺寸
		 }
		 System.out.println("screen_height_View=" + screen_height + "screen_width_view" + screen_width);

		 init();

	}


	
	public void init(){
//		 drawgrid=new DrawGrid(MyView.this,screen_width_origin,screen_height_origin);
		//绘制网格
//		drawgrid=new DrawGrid(MyView.this,screen_width_origin,screen_height_origin);//绘制网格
		bgcolor=new BgColor();                             //绘制背景颜色
//		list=new ArrayList<Date>();                            //设置数字坐标
		
//		lo=new gridLocation(screen_width_origin, screen_height_origin);
		
	}
	/**
	 * 画分析数据
	 * @param canvas
	 */
	public void drawAnasis(Canvas canvas){
		Paint paint =new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize((float) (wid / 1.1));
		anasis=Config.getAnalyzeData(context_);
		String jinri_8=anasis.substring(0, 22);
		String []jinri=jinri_8.split(",");

		try {
			for(int i=0;i<4;i++){
				canvas.drawText(jinri[i]+"次",31*wid ,wid*4+wid*i-wid/4, paint);
				canvas.drawText(jinri[i+4]+"次",37*wid ,wid*4+wid*i-wid/4, paint);
			}
		}catch (IndexOutOfBoundsException e){
			return;
		}

		String xingtai=anasis.substring(14,31);
		String xingtaiArr[]=xingtai.split(",");
		
		for(int i=0;i<3;i++){
			switch (xingtaiArr[i].length()) {
			case 1:
				canvas.drawText("      "+xingtaiArr[i]+"期",wid/2+18*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;

			case 2:
				canvas.drawText("    "+xingtaiArr[i]+"期",wid/2+18*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;
				
			default:canvas.drawText(xingtaiArr[i]+"期",wid/2+18*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;
			}
			switch (xingtaiArr[i+3].length()) {
			case 1:
				canvas.drawText("   "+xingtaiArr[i+3]+"期",wid/2+23*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;

			case 2:
				canvas.drawText("  "+xingtaiArr[i+3]+"期",wid/2+23*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;
				
			default:canvas.drawText(xingtaiArr[i+3]+"期",wid/2+23*wid ,wid*4+wid*i+wid/2.5f*i, paint);
				break;
			}
		}
		String lengre=anasis.substring(28,anasis.length());
		String lengreArr[]=lengre.split(",");
		for(int i=0;i<4;i++){
			canvas.drawText(lengreArr[i],4*wid ,wid*4+wid*i-wid/4, paint);
			canvas.drawText(lengreArr[i+3],7*wid ,wid*4+wid*i-wid/4, paint);
			canvas.drawText(lengreArr[i+3],11*wid ,wid*4+wid*i-wid/4, paint);
		}
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		float two=2;
		float onePoint_1=1.1f;
		if(flag==true){//true宽大于高,false高大于宽
			canvas.rotate(90,screen_width/two,screen_width/two);
			wid=screen_height/gridLocation.HorgridCounts;
		}else{
			wid=screen_width/gridLocation.HorgridCounts;
		}

		Paint paint = new Paint();
		bgcolor.draw(canvas,paint);   //绘制背景颜色
		
		for(int i=0;i<latestLotteryData.size();i++ ){
			latestlottery la=latestLotteryData.get(i);
			int[] lotteryNumber={la.getIssue(),la.getFirstnumber(),la.getSecondnnumber(),la.getThirdnnumber()
					,la.getFourthnnumber(),la.getType(),la.getSum(),la.getStep()};
			
			textLocaltion text = new textLocaltion(i+1, lotteryNumber, canvas, paint);
			text.bgdrawinnumber(canvas,paint);
			text.drawFourColumbg(canvas,paint);
		}
		drawgrid.draw(canvas,paint);  //绘制网格
		drawgrid.drawTitle(canvas);//绘制表头
		drawgrid.drawMissedNumer(canvas);//绘制2码3码遗漏显示
		drawAnasis(canvas);
		FontMetricsInt f=paint.getFontMetricsInt();
		int height=(int) Math.ceil(f.descent-f.ascent)+2;
		
		//画期号
		for(int i=0;i<latestLotteryData.size();i++ ){
			latestlottery la=latestLotteryData.get(i);
			int[] lotteryNumber={la.getIssue(),la.getFirstnumber(),la.getSecondnnumber(),la.getThirdnnumber()
					,la.getFourthnnumber(),la.getType(),la.getSum(),la.getStep()};
			 paint.setColor(Color.BLACK);
			 paint.setTextSize((float) (wid/1.1));
			 paint.setColor(Color.BLUE);
			 if(la.getIssue()<10){
				 canvas.drawText("0"+String.valueOf(la.getIssue()), 0,wid*i+11*wid-height/4.5f, paint);
			 }else{
				 canvas.drawText(String.valueOf(la.getIssue()),0, wid*i+11*wid-height/4.5f, paint);
			 }
			
			new textLocaltion(i+1, lotteryNumber, canvas, paint).set(canvas, paint);
			if(i==(latestLotteryData.size()-1)){
				paint.setAntiAlias(true);
				paint.setColor(Color.BLUE);
				paint.setTextSize((float) (wid/1.3));
				for(int j=2;j<textLocaltion.max_missed_data.length+2;j++){//当前遗漏在上面
					if(Integer.parseInt(textLocaltion.max_missed_data[j-2])<10){
						canvas.drawText(textLocaltion.max_missed_data[j-2], gridCenter.get(j+3).x-paint.measureText(one)/4,wid*80, paint);
					}else{
						canvas.drawText(textLocaltion.max_missed_data[j-2], gridCenter.get(j+3).x-paint.measureText(one)*onePoint_1,wid*80, paint);
					}

					if(Integer.parseInt(textLocaltion.current_missed_data[j-2])<10){
						canvas.drawText(textLocaltion.current_missed_data[j-2], gridCenter.get(j+3).x-paint.measureText(one)/4,wid*79, paint);
					}else{
						canvas.drawText(textLocaltion.current_missed_data[j-2], gridCenter.get(j+3).x-paint.measureText(one)*onePoint_1,wid*79, paint);
					}
				}
			}
		
		}
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		paint.setTextSize(wid);
		for(int j=2;j<7;j++){//当前遗漏中文文字
			if(j<6){
				canvas.drawText(textLocaltion.MAX_MISSED[j-2], gridCenter.get(j-1).x-paint.measureText(one), wid*80, paint);
			}
		}
		for(int k=2;k<7;k++){//最大遗漏中文文字
			if(k<6){
				canvas.drawText(textLocaltion.CURRENT_MISSED[k-2], gridCenter.get(k-1).x-paint.measureText(one), wid*79, paint);
			}
		}
	}

}