package cn.orange.drawText;


import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.util.Log;

import java.util.HashMap;

/*
* 绘制48*70的网格
* */

public class gridLocation {
	public static float gridWidth;//网格的宽高
	public static float gridHeight;
	private static int screenWidth,screenHeight;//屏幕的宽,高
	 
	public static final float HorgridCounts=46;//格子的列数
	public static final int VerGridCounts=70;//格子的行数
	public textLocaltion drawText=null;
	@SuppressLint("UseSparseArrays")
	public static HashMap<Integer, PointF> gridLocation=new HashMap<Integer,PointF>();
	@SuppressLint("UseSparseArrays")
	public static HashMap<Integer, PointF> TitleLocation=new HashMap<Integer,PointF>();


	public static int getScreen_height() {
		return screenHeight;
	}

	public gridLocation(int screenWidth1,int screenHeight1){
		screenWidth=screenWidth1;
		screenHeight=screenHeight1;
		gridWidth=screenWidth/HorgridCounts;
		gridHeight=screenHeight/HorgridCounts;//设置正方形的宽高
	}
	
	/**
	 * 绘制48*70的网格
	 */
	public static HashMap<Integer, PointF> getTitleLocation(){
		int AllTitleNumber=0;
		for(int k=0;k<3;k++){
			for(int y=1;y<47;y++){
				PointF gridTitleCenterLocation=new PointF();
				
				gridTitleCenterLocation.x=(y-1)*gridWidth;
				gridTitleCenterLocation.y=k*gridWidth+gridWidth/2;
				TitleLocation.put(AllTitleNumber, gridTitleCenterLocation);
				AllTitleNumber++;
			}
		}
		return TitleLocation;
	}
	
	public static HashMap<Integer, PointF> getGridLocation(){
		int ALLGridNumber=0;
		
		for (int i = 3; i < 90; i++) {
			for (int j = 1; j < 47; j++) {
				PointF gridCenterLocation=new PointF();
				gridCenterLocation.x=(j-1)*gridWidth+gridWidth/2;
				gridCenterLocation.y=i*gridWidth+gridWidth/2;
				gridLocation.put(ALLGridNumber, gridCenterLocation);
				ALLGridNumber++;
			}
		}
		return gridLocation;
	}
}