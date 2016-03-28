package cn.orange.drawText;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Util.WinNumber;
import bean.WinNumberInfo;

public class textLocaltion{
	private float gridWidth;
	boolean flag=false;//判断是否有重数,有重数置位true,数字颜色设置成红色，否则为false蓝色
	int colum;
	FontMetricsInt f;
	int height;
	public Canvas canvas;
	private int[] number;
	WinNumberInfo numberinfo;
	@SuppressLint("UseSparseArrays")
	public static HashMap<Integer,PointF> lastPointWidth=new HashMap<Integer,PointF>();
	public static final String[] CURRENT_MISSED={"当","前","遗","漏"};
	public static final String[] MAX_MISSED={"最","大","遗","漏"};
	public static final String[] num={"1","2","3","4","5","6","7","8",
									  "1","2","3","4","5","6","7","8",
									  "1","2","3","4","5","6","7","8",
									  "1","2","3","4","5","6","7","8",
									  "1","2","3","4","5","6","7","8"};
	public static String[] current_missed_data=new String[40];
	public static String[] max_missed_data=new String[40];
	public static final String one="1";
	public static final int BLACK=1;
	public static final int RED=2;
	public static final int GREEN=3;
	public static final int PUPLE=5;
	public static final int BLUE=4;
	public gridLocation lo;

	HashMap<Integer, PointF>gridCenter=gridLocation.getGridLocation();
	public textLocaltion(int colum,int []number,Canvas canvas,Paint paint) {
		this.colum=colum;
		this.gridWidth=gridLocation.gridWidth;
		this.number=number;
		this.canvas=canvas;
		this.numberinfo=WinNumber.getWinNumberInfo(number);
		f=paint.getFontMetricsInt();
		height=(int) Math.ceil(f.descent-f.ascent)+2;
	}
	public void set(Canvas canvas,Paint paint){
		int[] afterSort= {number[1],number[2],number[3],number[4]};
		
		Set<Integer> set=new HashSet<Integer>();
		for (int i=0;i<afterSort.length;i++) {
			set.add(afterSort[i]);
		}
		if(set.size()!=4){
			flag=true;
			paint.setColor(Color.rgb(255, 0, 0));
		}else{
			flag=false;
			paint.setColor(Color.rgb(0, 0, 255));
		}
		drawFourColumNumer(canvas, paint);
		sortNumberToUnique(canvas,paint);
	}


	/*4个大列,画出每一行对应坐标的对应数字*/
	public void drawFourColumNumer(Canvas canvas,Paint paint){
		paint.setTextSize(gridWidth);

		FontMetricsInt f=paint.getFontMetricsInt();
		int height=(int) Math.ceil(f.descent-f.ascent)+2;
		PointF p;
		paint.setColor(Color.WHITE);
		

		//画第一行的前面四个中奖号
		for (int i = 1; i < 5; i++) {
			 p=gridCenter.get(46*(colum+6)+i);
			 canvas.drawText(String.valueOf(number[i]), p.x-paint.measureText(one)/2, p.y+height/3, paint);
		}
//		-----------------------------画对应的四列数字-----------
		String numberStr="12345678";
		for(int i=1;i<5;i++){//第几列1，2，3, 4
			PointF last=new PointF();
			if(i == 1) paint.setColor(Color.rgb(168,86,157));
			if(i == 2) paint.setColor(Color.rgb(0,0,255));
			if(i == 3) paint.setColor(Color.rgb(232,62,86));
			if(i == 4) paint.setColor(Color.rgb(47,106,179));


			int index=numberStr.indexOf(number[i]+"");

			p=gridCenter.get((46*(colum+6)+index+13)+(i-1)*8);//获取到第Colum列的1的坐标
			if(colum!=1){
				canvas.drawLine(p.x, p.y, lastPointWidth.get(i).x, p.y-gridWidth, paint);
			}
			paint.setColor(Color.WHITE);
			canvas.drawText(String.valueOf(number[i]), p.x-paint.measureText(one)/2, p.y+height/3, paint);//画出对应的数字
			last.x=p.x;
			last.y=p.y;

			lastPointWidth.put(i,last);
		}

		//画最后3列数字
		paint.setColor(Color.BLACK);
		for (int i = 7; i < 8; i++) {
			paint.setTextSize((float) (gridWidth/1.3));
			 p=gridCenter.get(46*(colum+6)+43+(i-5));
			 if(number[i]<10){
				 canvas.drawText(String.valueOf(number[i]), p.x-paint.measureText(one)/2, p.y+height/5, paint);
			 }else{
				 
				 canvas.drawText(String.valueOf(number[i]), p.x-paint.measureText(one), p.y+height/5, paint);
			 }
		}
	}
	//奖号背景颜色
	public void bgdrawinnumber(Canvas canvas,Paint paint){
		RectF winnumberbk = new RectF(gridLocation.gridWidth,(colum+9)*gridLocation.gridWidth,  5*gridLocation.gridWidth,(colum+10)*gridLocation.gridWidth);
		numberinfo.setColor();
		if(numberinfo.getColor() == RED) paint.setColor(Color.RED);
		else if(numberinfo.getColor() == BLACK) paint.setColor(Color.BLACK);
		else if(numberinfo.getColor() == GREEN) paint.setColor(Color.rgb(22, 146, 50));
		else if(numberinfo.getColor() == BLUE) paint.setColor(Color.BLUE);
		else if(numberinfo.getColor() == PUPLE) paint.setColor(Color.rgb(141, 75, 187));
		canvas.drawRect(winnumberbk, paint); 
	}
	//四大列背景：自由泳--蝶泳
	public void drawFourColumbg(Canvas canvas,Paint paint){
		PointF p;
		String numberStr="12345678";
		for(int i=1;i<5;i++){//第几列1，2，3, 4
			paint.setColor(Color.rgb(168,86,157));
			if(i == 1) paint.setColor(Color.rgb(168,86,157));
			if(i == 2) paint.setColor(Color.rgb(0,0,255));
			if(i == 3) paint.setColor(Color.rgb(232,62,86));
			if(i == 4) paint.setColor(Color.rgb(47,106,179));

			int index=numberStr.indexOf(number[i]+"");
			
			p=gridCenter.get((46*(colum+6)+13+index)+(i-1)*8);//获取到第Colum列的1的坐标
			p.x-=gridWidth/2;
			p.y-=gridWidth/2;
			RectF rect1 = new RectF(p.x, p.y, p.x+gridWidth, p.y+gridWidth);
			canvas.drawRect(rect1, paint);//画出对应的数字

		}
	}

	//格局数字
		public void sortNumberToUnique(Canvas canvas,Paint paint){
			//重号背景
			
			for(int i=0;i<numberinfo.getArry().length;i++){
				if(numberinfo.getArry()[i] >=2){
					flag = true;
					RectF winnumberbk = new RectF((5+i-1)*gridLocation.gridWidth+1,(colum+9)*gridLocation.gridWidth,(5+i)*gridLocation.gridWidth,(colum+10)*gridLocation.gridWidth-1);
					paint.setColor(Color.RED);
					canvas.drawRect(winnumberbk, paint);
				}
			}
			
			int[] counts = numberinfo.getCounts();
			float bigoffleft = gridWidth/6;
			float smalloffleft = gridWidth/3;
			float smalloffbottom = gridWidth/3;
			PointF p;
			String numberStr="12345678";
			for(int i=0;i<numberinfo.sortwinnumber.length;i++){
				paint.setTextSize(gridWidth);
				int index=numberStr.indexOf(numberinfo.sortwinnumber[i]+"");
				p=gridCenter.get(46*(colum+6)+5+index);

				if(counts[index+1] == 1){
					paint.setColor(Color.BLACK);
					canvas.drawText(String.valueOf(index+1), p.x-paint.measureText(one)/2, p.y+height/3, paint);
				}else{
					paint.setColor(Color.WHITE);
					canvas.drawText(String.valueOf(index+1), p.x-paint.measureText(one)/2-bigoffleft, p.y+height/3, paint);
					paint.setTextSize(gridWidth/2);
					canvas.drawText(String.valueOf(counts[index+1]), p.x-paint.measureText(one)/2+smalloffleft, p.y+height/3-smalloffbottom, paint);
				}

			}
		}
}
