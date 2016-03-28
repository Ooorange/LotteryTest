package cn.orange.drawLine;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import cn.orange.drawText.gridLocation;
import cn.orange.drawText.textLocaltion;
import cn.orange.lottery.SoundPlayActivity;
import cn.orange.net.Config;
import cn.orange.net.tableData;

//import android.graphics.Rect;

/*
 * 绘制48*70的网格
 * */
public class DrawGrid {
	public static int baseLine=46;
	public  Context context;
	public  float grid_width, grid_height;//网格的宽高
	public  float screen_width,screen_height;//屏幕的宽,高
	public static final String ISSUE="期";
	public static final String WINNUM="奖号";
	public static final String WINNUMLAYOUT="奖号分布";
	public static final String FIRSTNUM="自由泳";
	public static final String SECONDNUM="仰泳";
	public static final String THIRDNUM="蛙泳";
	public static final String FOURTHNUM="蝶泳";
	public static final String LAST="跨";
//	public static final String LAST="类和跨";
	public static final String LLHMFX="冷热号码分析";
	public static final String XTYLFX="形态遗漏分析";
	public static final String JRBGHMCXCS="今日8个号码出现次数";
	
	public static final String MINGCHENG="名称";
	public static final String REHAO="热号";
	public static final String LENGHAO="冷号";
	public static final String CISHU="次数";
	public static final String BAOZI="豹子";
	public static final String SHUANGDUI="双对";
	public static final String SHUNZI="顺子";
	public static final String ERMA="二码";
	public static final String SHANMA="三码";
	public static final String one="1";
	HashMap<Integer, PointF>gridCenter=gridLocation.getGridLocation();
	HashMap<Integer, PointF>TitleCenter=gridLocation.getTitleLocation();
	LotteryContentView myview;
	public float getGrid_width() {
		return grid_width;
	}
	

	public DrawGrid(LotteryContentView myview,int screen_width,int screen_height,Context context){
		this.context=context;
		this.myview=myview;
		this.screen_width=screen_width;
		this.screen_height=screen_height;
		this.grid_width=gridLocation.gridWidth;
		this.grid_height=gridLocation.gridWidth;
	}
	
	/*绘制48*70的网格*/
	public void draw(Canvas canvas,Paint paint){
		
		
		


		paint.setAlpha(200);
		paint.setColor(Color.rgb(191, 191, 191));
		paint.setTextSize(1);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		/*绘制网格 此时先画横线*/
		float horLine=0;
		float verLine=0;
		
		
		//-----------------
		for(int i=0;i<72;i++,horLine+=grid_width){
//			canvas.drawLine(0, 0*grid_width+horLine,grid_width*48,0*grid_width+horLine, paint);
			canvas.drawLine(0, 10*grid_width+horLine,grid_width*48,10*grid_width+horLine, paint);
		}
	/*绘制网格 画列线
	* |||||||||||||||||||
	* */
		for(int j=0;j<47;j++,verLine+=grid_width){
//			canvas.drawLine(verLine, grid_width*0, verLine,81*getGrid_width(), paint);
			canvas.drawLine(verLine, grid_width*10, verLine,78*getGrid_width(), paint);
		}

		drawLineSpace(canvas,paint);
	}
	//画每个分区的间隔线和网格
	public void drawLineSpace(Canvas canvas,Paint drawLine){
		drawLine.setColor(Color.BLACK);
//		drawLine.setAntiAlias(true);
		
//		drawLine.setStyle(Paint.Style.STROKE);
//		drawLine.setAntiAlias(true);
//		drawLine.setColor(Color.BLACK);
//		drawLine.setStrokeWidth(1);
		
		int five=1;
		RectF juxingkuang=new RectF((29)*gridLocation.gridWidth,10*gridLocation.gridWidth,37*gridLocation.gridWidth,81*gridLocation.gridWidth);//表头文字：期+奖号
		while((five--)>0){
//			canvas.drawRect(juxingkuang, drawLine);
			canvas.drawLine(13*gridLocation.gridWidth,7*gridLocation.gridWidth,13*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
			canvas.drawLine(21*gridLocation.gridWidth,7*gridLocation.gridWidth,21*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
			canvas.drawLine(29*gridLocation.gridWidth,7*gridLocation.gridWidth,29*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
			canvas.drawLine(37*gridLocation.gridWidth,7*gridLocation.gridWidth,37*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
			canvas.drawLine(46*gridLocation.gridWidth,7*gridLocation.gridWidth,46*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
			canvas.drawLine(45*gridLocation.gridWidth,7*gridLocation.gridWidth,45*gridLocation.gridWidth,78*gridLocation.gridWidth,drawLine);
		}
		
	}
	public void drawFenXiTitle(Canvas canvas){
		
	}
	public  void drawTitle(Canvas canvas){
		Paint paint3=new Paint();
		paint3.setAntiAlias(true);
		paint3.setTextSize(grid_width);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		
		for(int i=0;i<40;i++){
			paint3.setColor(Color.BLUE);
			canvas.drawText(textLocaltion.num[i], TitleCenter.get(46*2+5+i).x+paint3.measureText(one)/2, 7*gridLocation.gridWidth+TitleCenter.get(46*2+5+i).y, paint3);
		}//对应列表数字（1-8）*5
		paint3.setColor(Color.rgb(0, 0, 255));
		canvas.drawText(ISSUE, TitleCenter.get(baseLine).x, TitleCenter.get(baseLine).y+grid_width/3+7*gridLocation.gridWidth, paint3);//期号
		
		canvas.drawText(WINNUM, TitleCenter.get(baseLine+2).x, TitleCenter.get(baseLine+2).y+grid_width/3+7*gridLocation.gridWidth, paint3);//奖号
		
		canvas.drawText(WINNUMLAYOUT, TitleCenter.get(7).x, TitleCenter.get(7).y+grid_width/2+7*gridLocation.gridWidth, paint3);//奖号分布
		canvas.drawText(FIRSTNUM, TitleCenter.get(13+3).x, TitleCenter.get(13).y+grid_width/2+7*gridLocation.gridWidth, paint3);//1
		canvas.drawText(SECONDNUM, TitleCenter.get(21+3).x, TitleCenter.get(21).y+grid_width/2+7*gridLocation.gridWidth, paint3);//2
		canvas.drawText(THIRDNUM, TitleCenter.get(29+3).x, TitleCenter.get(29).y+grid_width/2+7*gridLocation.gridWidth, paint3);//3
		canvas.drawText(FOURTHNUM, TitleCenter.get(37+3).x, TitleCenter.get(37).y+grid_width/2+7*gridLocation.gridWidth, paint3);//4
		canvas.drawText(LAST, TitleCenter.get(baseLine*2-1).x, TitleCenter.get(baseLine*2-1).y+grid_width/3+7*gridLocation.gridWidth, paint3);//最后
	
		canvas.drawText(LLHMFX, TitleCenter.get(3).x, TitleCenter.get(3).y+grid_width, paint3);//冷热号码分析
		canvas.drawText(XTYLFX, TitleCenter.get(17).x, TitleCenter.get(3).y+grid_width, paint3);//形态遗漏分析
		canvas.drawText(JRBGHMCXCS, TitleCenter.get(28).x, TitleCenter.get(3).y+grid_width, paint3);//今日8个号码出现次数
		
		canvas.drawText(MINGCHENG, TitleCenter.get(baseLine).x+grid_width/2, TitleCenter.get(baseLine+1).y+grid_width*3/2, paint3);//名称
		canvas.drawText(REHAO, TitleCenter.get(baseLine+3).x+grid_width/2, TitleCenter.get(baseLine+3).y+grid_width*3/2, paint3);//热号
		canvas.drawText(LENGHAO, TitleCenter.get(baseLine+6).x+grid_width/2,TitleCenter.get(baseLine+5).y+grid_width*3/2, paint3);//冷号
		canvas.drawText("当前遗漏", TitleCenter.get(baseLine+9).x+grid_width/2, TitleCenter.get(baseLine+7).y+grid_width*3/2, paint3);//当前遗漏
		
		canvas.drawText(MINGCHENG, TitleCenter.get(baseLine+14).x+grid_width/2, TitleCenter.get(baseLine+10).y+grid_width*3/2, paint3);//名称
		canvas.drawText("最大遗漏", TitleCenter.get(baseLine+17).x+grid_width/2, TitleCenter.get(baseLine+12).y+grid_width*3/2, paint3);//最大遗漏
		canvas.drawText("当前遗漏", TitleCenter.get(baseLine+22).x+grid_width/2, TitleCenter.get(baseLine+16).y+grid_width*3/2, paint3);//当前遗漏
		
		canvas.drawText("号码", TitleCenter.get(baseLine*2+27).x+grid_width/2, TitleCenter.get(baseLine*2+12).y+grid_width/2, paint3);//号码
		canvas.drawText(CISHU, TitleCenter.get(baseLine*2+30).x+grid_width/2, TitleCenter.get(baseLine*2+14).y+grid_width/2, paint3);//次数
		canvas.drawText("号码", TitleCenter.get(baseLine*2+33).x+grid_width/2, TitleCenter.get(baseLine*2+16).y+grid_width/2, paint3);//号码
		canvas.drawText(CISHU, TitleCenter.get(baseLine*2+36).x+grid_width/2, TitleCenter.get(baseLine*2+18).y+grid_width/2, paint3);//次数
		
		canvas.drawText(ERMA, TitleCenter.get(baseLine).x+grid_width/2, grid_width*4+grid_width/2, paint3);//二码
		canvas.drawText(SHANMA, TitleCenter.get(baseLine).x+grid_width/2,grid_width*6+grid_width/2, paint3);//三码
		
		canvas.drawText(BAOZI, TitleCenter.get(baseLine).x+grid_width*14+grid_width/2,grid_width*3+grid_width*4/4, paint3);//豹子
		canvas.drawText(SHUANGDUI, TitleCenter.get(baseLine).x+grid_width*14+grid_width/2,grid_width*4+grid_width*4/3, paint3);//双对
		canvas.drawText(SHUNZI, TitleCenter.get(baseLine).x+grid_width*14+grid_width/2,grid_width*5+grid_width*5/3, paint3);//顺子
		
		for(int i=1;i<5;i++){
			canvas.drawText(i+"", grid_width*28,grid_width*(i+3), paint3);//1234
			canvas.drawText(i+4+"", grid_width*34,grid_width*(i+3), paint3);//1234
		}
	}
	/**
	 * 最大遗漏，当前遗漏--最下面
	 * @param canvas
	 */
	public  void drawMissedNumer(Canvas canvas){
		Paint paint1=new Paint();
		paint1.setAntiAlias(true);
		paint1.setColor(Color.rgb(0, 0, 255));
		paint1.setTextSize((float) (grid_width*1.5));
		paint1.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));
		if(LotteryContentView.flag){
			List<tableData> tableDatas = Config.getCachedTableData(context);
			for (int i = 0; i < tableDatas.size(); i++) {
				SoundPlayActivity.lottery[5 + i] = tableDatas.get(i).getGroup_number();
			}
			canvas.drawText("根据遗漏情况,本期二码关注"+SoundPlayActivity.lottery[5]+", "+SoundPlayActivity.lottery[6]+", "+SoundPlayActivity.lottery[7]+"  三码关注"+
					SoundPlayActivity.lottery[8]+", "+SoundPlayActivity.lottery[9]+", "+SoundPlayActivity.lottery[10], grid_width, LotteryContentView.max-grid_width*0.3f, paint1);
		}
	}
}
