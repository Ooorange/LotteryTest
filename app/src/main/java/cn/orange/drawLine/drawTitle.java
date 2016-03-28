package cn.orange.drawLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import cn.orange.drawText.gridLocation;
import cn.orange.drawText.textLocaltion;
import cn.orange.net.latestlottery;

@SuppressLint("DrawAllocation")
public class drawTitle extends View{

	private static int screen_width;
	private static int screen_height;
	public static List<latestlottery> latestLotteryData=new ArrayList<latestlottery>();
	HashMap<Integer, PointF>TitleCenter=gridLocation.getTitleLocation();
	public ArrayList<Integer[]> storeList=new ArrayList<Integer[]>();
	static int ROW=0;
	public float gridWidth;
	public gridLocation lo;
	static final int LOCAX=5;
	static final int LOCAy=17;
	DrawGrid drawgrid=null;
	textLocaltion drawText=null;
	BgColor bgcolor=null;
	RectF rect1,rect2,rect3,rect4,rect5,rect6,rect7,rect8,
	rectTitle,
	rectIssue,rectWInNum,rectNumLayout,rectFir,rectSec,rectThird,rectFourth,rectLast;
	public static final String[] num={"1","2","3","4","5","6","7","8",
		  "1","2","3","4","5","6","7","8",
		  "1","2","3","4","5","6","7","8",
		  "1","2","3","4","5","6","7","8",
		  "1","2","3","4","5","6","7","8"};
	public static final String ISSUE="期";
	public static final String WINNUM="奖号";
	public static final String WINNUMLAYOUT="奖号分布";
	public static final String FIRSTNUM="第一位号码分布图";
	public static final String SECONDNUM="第二位号码分布图";
	public static final String THIRDNUM="第三位号码分布图";
	public static final String FOURTHNUM="第四位号码分布图";
	public static final String LAST="类和跨";
	public static final String one="1";
	public drawTitle(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public drawTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public drawTitle(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		 screen_width = MeasureSpec.getSize(widthMeasureSpec); 
		 screen_height = MeasureSpec.getSize(heightMeasureSpec);
		 lo=new gridLocation(screen_width, screen_height);
	}
	@Override
	public void draw(Canvas canvas){
		Paint paint3=new Paint();
		gridWidth=gridLocation.gridWidth;
		paint3.setAntiAlias(true);
		
		paint3.setTextSize(gridLocation.gridWidth);
		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		for(int i=0;i<40;i++){
			paint3.setColor(Color.BLUE);
			canvas.drawText(num[i], TitleCenter.get(101+i).x+paint3.measureText(one)/2, TitleCenter.get(101+i).y, paint3);
		}//对应列表数字
		paint3.setColor(Color.rgb(0, 0, 255));
		canvas.drawText(ISSUE, TitleCenter.get(48).x, TitleCenter.get(48).y+gridWidth/3, paint3);//期号
		
		canvas.drawText(WINNUM, TitleCenter.get(50).x, TitleCenter.get(50).y+gridWidth/3, paint3);//奖号
		
		canvas.drawText(WINNUMLAYOUT, TitleCenter.get(7).x, TitleCenter.get(7).y+gridWidth/2, paint3);//奖号分布
		canvas.drawText(FIRSTNUM, TitleCenter.get(13).x, TitleCenter.get(13).y+gridWidth/2, paint3);//1
		canvas.drawText(SECONDNUM, TitleCenter.get(21).x, TitleCenter.get(21).y+gridWidth/2, paint3);//2
		canvas.drawText(THIRDNUM, TitleCenter.get(29).x, TitleCenter.get(29).y+gridWidth/2, paint3);//3
		canvas.drawText(FOURTHNUM, TitleCenter.get(37).x, TitleCenter.get(37).y+gridWidth/2, paint3);//4
		canvas.drawText(LAST, TitleCenter.get(93).x, TitleCenter.get(93).y+gridWidth/3, paint3);//最后
	
		
		Paint paintTitle=new Paint();
		paintTitle.setStyle(Paint.Style.STROKE);
		paintTitle.setAntiAlias(true);
		paintTitle.setColor(Color.rgb(240, 240, 240));
		paintTitle.setStrokeWidth(1);
		rectIssue=new RectF(0,0,1*gridWidth,3*gridWidth);
		rectWInNum=new RectF(1*gridWidth,0,5*gridWidth,3*gridWidth);
		rectNumLayout=new RectF(5*gridWidth,0,13*gridWidth,3*gridWidth);
		rectFir=new RectF(13*gridWidth,0,21*gridWidth,3*gridWidth);
		rectSec=new RectF(21*gridWidth,0,29*gridWidth,3*gridWidth);
		rectThird=new RectF(29*gridWidth,0,37*gridWidth,3*gridWidth);
		rectFourth=new RectF(37*gridWidth,0,45*gridWidth,3*gridWidth);
		rectLast=new RectF(45*gridWidth,0,48*gridWidth,3*gridWidth);
		
		canvas.drawRect(rectIssue, paintTitle);
		canvas.drawRect(rectWInNum, paintTitle);
		canvas.drawRect(rectNumLayout, paintTitle);
		canvas.drawRect(rectFir, paintTitle);
		canvas.drawRect(rectSec, paintTitle);
		canvas.drawRect(rectThird, paintTitle);
		canvas.drawRect(rectFourth, paintTitle);
		canvas.drawRect(rectLast, paintTitle);
		canvas.drawLine(47*gridWidth, 0, 47*gridWidth, 3*gridWidth, paintTitle);
		canvas.drawLine(46*gridWidth, 0, 46*gridWidth, 3*gridWidth, paintTitle);
		
		canvas.drawLine(5*gridWidth, 3*gridWidth/2, 45*gridWidth, 3*gridWidth/2, paintTitle);
	}
}
