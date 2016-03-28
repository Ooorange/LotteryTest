package cn.orange.drawLine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import cn.orange.drawText.gridLocation;

public class BgColor {
	public int basePoint=7;
	RectF rect1,rect9,
	rectTitle,rectMain,rectWInNum,rectNumLayout,rectFir,rectSec,rectThird,rectFourth,rectLast,group;
	public BgColor(){
		//边框色
		//rectIssue=new RectF(0,0,48* gridLocation.gridWidth,gridLocation.gridWidth*78);
		rectWInNum=new RectF(0,basePoint*gridLocation.gridWidth,5*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：期+奖号
		rectNumLayout=new RectF(5*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,13*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：奖号分布+ 数字1-8
		rectFir=new RectF(13*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,21*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：自由泳
		rectSec=new RectF(21*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,29*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：仰泳
		rectThird=new RectF(29*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,37*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：蛙泳
		rectFourth=new RectF(37*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,45*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：蝶泳
		rectLast=new RectF(45*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth,48*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//表头文字：类和跨
		 
		//背景色
		rectTitle=new RectF(0,(basePoint)*gridLocation.gridWidth,48*gridLocation.gridWidth,(basePoint+3)*gridLocation.gridWidth);//标题栏背景色
		rectMain = new RectF(0, 0, 48*gridLocation.gridWidth,LotteryContentView.max);//彩票主显示区的背景颜色

//		group=new RectF(0,3*gridLocation.gridWidth,gridLocation.gridWidth,1*gridLocation.getScreen_height());
		
		//rect1=new RectF(0,3*gridLocation.gridWidth,gridLocation.gridWidth,LotteryContentView.max-gridLocation.gridWidth);//期号背景色
		rect9=new RectF(0,LotteryContentView.max-1.7f*gridLocation.gridWidth,48*gridLocation.gridWidth,LotteryContentView.max);//2码3码背景
		
	}
	
	
	public void draw(Canvas canvas,Paint paint){
		paint.setAlpha(100);
//		paint.setColor(Color.parseColor("#99CCFF"));
//		canvas.drawRect(rect1, paint);
		paint.setColor(Color.WHITE);
		canvas.drawRect(rectMain, paint);
		paint.setColor(Color.parseColor("#CCCCCC"));
		canvas.drawRect(rectTitle, paint);

		paint.setColor(Color.WHITE);
		canvas.drawRect(rect9, paint);   
		
		Paint paintTitle=new Paint();
		paintTitle.setStyle(Paint.Style.STROKE);
		paintTitle.setAntiAlias(true);
		paintTitle.setColor(Color.BLACK);
		paintTitle.setStrokeWidth(1);
		Paint drawLine = new Paint();
		drawLine.setColor(Color.BLACK);
		
		
		//canvas.drawRect(rectIssue, paintTitle);
		//标题栏
		canvas.drawRect(rectWInNum, paintTitle);
		canvas.drawRect(rectNumLayout, paintTitle);
		canvas.drawRect(rectFir, paintTitle);
		canvas.drawRect(rectSec, paintTitle);
		canvas.drawRect(rectThird, paintTitle);
		canvas.drawRect(rectFourth, paintTitle);
		canvas.drawRect(rectLast, paintTitle);
		
		//分析标题栏
		
		canvas.drawLine(47*gridLocation.gridWidth, (basePoint)*gridLocation.gridWidth, 47*gridLocation.gridWidth, (basePoint+3)*gridLocation.gridWidth, drawLine);
		canvas.drawLine(46*gridLocation.gridWidth, (basePoint)*gridLocation.gridWidth, 46*gridLocation.gridWidth, (basePoint+3)*gridLocation.gridWidth, drawLine);//类和跨线条
		canvas.drawLine(5*gridLocation.gridWidth,(basePoint)*gridLocation.gridWidth+3*gridLocation.gridWidth/2, 45*gridLocation.gridWidth, (basePoint)*gridLocation.gridWidth+3*gridLocation.gridWidth/2, drawLine);//表头中间的横线
		canvas.drawLine(1*gridLocation.gridWidth, (basePoint)*gridLocation.gridWidth, 1*gridLocation.gridWidth, (basePoint+3)*gridLocation.gridWidth, drawLine);//文字 期右边线条
		
		drawFenxiLine(canvas);
	}
	public void drawFenxiLine(Canvas canvas){
		Paint paint=new Paint();
		paint.setColor(Color.parseColor("#D1850A"));
		RectF rectF=new RectF(0, 0, 46*gridLocation.gridWidth, 2*gridLocation.gridWidth);
//		paint.setStyle(Paint.Style.STROKE);
//		paint.setAntiAlias(true);
	//	canvas.drawLine(0, 0, gridLocation.gridWidth*46, 0, paint);//第一条线
	//	canvas.drawLine(0, gridLocation.gridWidth*3, gridLocation.gridWidth*46, gridLocation.gridWidth*3, paint);//第3条
		canvas.drawRect(rectF, paint);
		RectF rehao=new RectF(3*gridLocation.gridWidth,gridLocation.gridWidth*2,gridLocation.gridWidth*6,gridLocation.gridWidth*3);
		paint.setColor(Color.parseColor("#F0674C"));
		canvas.drawRect(rehao, paint);
		RectF lenghao=new RectF(6*gridLocation.gridWidth,gridLocation.gridWidth*2,gridLocation.gridWidth*9,gridLocation.gridWidth*3);
		paint.setColor(Color.parseColor("#99DBD5"));
		canvas.drawRect(lenghao, paint);
		RectF haomalr=new RectF(0,gridLocation.gridWidth*2,gridLocation.gridWidth*3,gridLocation.gridWidth*3);
		paint.setColor(Color.parseColor("#cccccc"));
		canvas.drawRect(rehao, paint);
		
		RectF haomaXT=new RectF(14*gridLocation.gridWidth, gridLocation.gridWidth*2, gridLocation.gridWidth*17, gridLocation.gridWidth*2);
		canvas.drawRect(haomaXT, paint);
		RectF haomaJR=new RectF(27*gridLocation.gridWidth, gridLocation.gridWidth*2, gridLocation.gridWidth*30, gridLocation.gridWidth*2);
		canvas.drawRect(haomaJR, paint);
		paint.setColor(Color.parseColor("#F0D94C"));
		RectF rect_23=new RectF(0,gridLocation.gridWidth*3, 3*gridLocation.gridWidth, gridLocation.gridWidth*7);
		RectF recct_1234=new RectF(gridLocation.gridWidth*27,gridLocation.gridWidth*3,gridLocation.gridWidth*30,gridLocation.gridWidth*7);
		RectF recct_1234_2=new RectF(gridLocation.gridWidth*33,gridLocation.gridWidth*3,gridLocation.gridWidth*36,gridLocation.gridWidth*7);
		RectF rect_baozi=new RectF(14*gridLocation.gridWidth,3*gridLocation.gridWidth,17*gridLocation.gridWidth,7*gridLocation.gridWidth);
		canvas.drawRect(rect_23, paint);
		canvas.drawRect(recct_1234, paint);
		canvas.drawRect(rect_baozi, paint);
		canvas.drawRect(recct_1234_2, paint);
		
		paint.setColor(Color.BLACK);
			canvas.drawLine((3)*gridLocation.gridWidth,gridLocation.gridWidth*2, gridLocation.gridWidth*(3), gridLocation.gridWidth*7, paint);//	
			canvas.drawLine((6)*gridLocation.gridWidth,gridLocation.gridWidth*2, gridLocation.gridWidth*(6), gridLocation.gridWidth*7, paint);//	
			canvas.drawLine((9)*gridLocation.gridWidth,gridLocation.gridWidth*2, gridLocation.gridWidth*(9), gridLocation.gridWidth*7, paint);//	
			canvas.drawLine((14)*gridLocation.gridWidth,gridLocation.gridWidth*0, gridLocation.gridWidth*(14), gridLocation.gridWidth*7, paint);//	
			
			canvas.drawLine(0*gridLocation.gridWidth,gridLocation.gridWidth*3, gridLocation.gridWidth*(46), gridLocation.gridWidth*3, paint);//	
			canvas.drawLine(0*gridLocation.gridWidth,gridLocation.gridWidth*5, gridLocation.gridWidth*(14),gridLocation.gridWidth*5, paint);//	
			canvas.drawLine(3*gridLocation.gridWidth,gridLocation.gridWidth*6, gridLocation.gridWidth*(14),gridLocation.gridWidth*6, paint);//	
			canvas.drawLine(3*gridLocation.gridWidth,gridLocation.gridWidth*4, gridLocation.gridWidth*(14),gridLocation.gridWidth*4, paint);//	
			
			canvas.drawLine(27*gridLocation.gridWidth,gridLocation.gridWidth*4, gridLocation.gridWidth*(39), gridLocation.gridWidth*4, paint);//	
			canvas.drawLine(27*gridLocation.gridWidth,gridLocation.gridWidth*5, gridLocation.gridWidth*(39),gridLocation.gridWidth*5, paint);//	
			canvas.drawLine(27*gridLocation.gridWidth,gridLocation.gridWidth*6, gridLocation.gridWidth*(39),gridLocation.gridWidth*6, paint);//	
//			
			
			canvas.drawLine(14*gridLocation.gridWidth, gridLocation.gridWidth*3+gridLocation.gridWidth*4/3,
							27*gridLocation.gridWidth,gridLocation.gridWidth*3+gridLocation.gridWidth*4/3, paint);
			canvas.drawLine(14*gridLocation.gridWidth, gridLocation.gridWidth*4/3*2+gridLocation.gridWidth*3,
							27*gridLocation.gridWidth,gridLocation.gridWidth*4/3*2+gridLocation.gridWidth*3, paint);
			
			canvas.drawLine(17*gridLocation.gridWidth, gridLocation.gridWidth*2,17*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			canvas.drawLine(22*gridLocation.gridWidth, gridLocation.gridWidth*2,22*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			canvas.drawLine(27*gridLocation.gridWidth, gridLocation.gridWidth*0,27*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			
			canvas.drawLine(30*gridLocation.gridWidth, gridLocation.gridWidth*2,30*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			canvas.drawLine(33*gridLocation.gridWidth, gridLocation.gridWidth*2,33*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			canvas.drawLine(36*gridLocation.gridWidth, gridLocation.gridWidth*2,36*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			canvas.drawLine(49*gridLocation.gridWidth, gridLocation.gridWidth*2,49*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			
			canvas.drawLine(39*gridLocation.gridWidth, gridLocation.gridWidth*0,39*gridLocation.gridWidth,7*gridLocation.gridWidth, paint);
			RectF rectF2=new RectF(0,0,46*gridLocation.gridWidth,7*gridLocation.gridWidth);
			paint.setAntiAlias(true);
			paint.setStyle(Paint.Style.STROKE);
			canvas.drawRect(rectF2, paint);
			
	}
}