package cn.orange.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.orange.lottery.MainActivity;

public class Config {
	public static final String APP_NAME="cn.orange.lottery";
	public static final String APP_NAME_TABLE="cn.orange.lottery.TableData";
	public static final String APP_NAME_TABLE2="cn.orange.lottery.TableData2";
	
	public static final String CHARSET="utf-8";
	//本机服务器
//	public static final String DOWNLOADURL = "http://192.168.1.103:8080/servlet/downLoad.jsp";
//	public static final String SERVER_URL = "http://192.168.1.103:8080/servlet/server";
	//校园服务器
	public static final String SERVER_PRE="http://121.42.12.12:8080";
	public static final String YESTERDAT_URL = SERVER_PRE+"/servlet/Getyestodaydata";
	public static final String THE_DAY_BEFORE_URL = SERVER_PRE+"/servlet/Getbeforeyestadydata";

	//掉咋天服务器
	public static final String DOWNLOADURL = SERVER_PRE+"/servlet/downLoad.jsp";
	public static final String SERVER_URL =SERVER_PRE+ "/servlet/server";
	
	
	
	public static final int RESULT_STATUS_FAIL=0;
	public static final int RESULT_STATUS_SUCCESS=1;
	public static final int RESULT_STATUS_NONEWS=2;
	public static final int RESULT_JSON_EXCEPECTION=3;
	
	public static final String ACTION="android.intent.action.BOOT_COMPLETED";
	public static final String KEY_ACTION = "action";
	public static final String KEY_STATUS = "status";
	public static final String KEY_WINNUMBER = "win_number";
	public static final String KEY_FIRSTNUMBER = "first_number";
	public static final String KEY_SECONDNUMBER = "second_number";
	public static final String KEY_THIRDNUMBER = "third_number";
	public static final String KEY_FOURTHNUMBER = "fourth_number";
	public static final String KEY_TYPE = "type";
	public static final String KEY_SUM = "sum";
	public static final String KEY_STEP = "step";
	public static final String KEY_ISSUE = "issue";
	public static final String KEY_MAX_MISSED = "max_missed";
	public static final String KEY_CURRENT_MISSED = "current_missed";
	
	public static final String KEY_APKNAME = "apkName";
	public static final String KEY_APPNAME = "appName";
	public static final String KEY_VERSIONNAME = "versionName";
	public static final String KEY_VERSIONCODE = "versionCode";
	public static final String KEY_VERSION = "version";
	
	public static final String YESTERDAYDATA="yesterdayData";
	public static final String THE_DAY_BEFORE="the_day_berfore";
	
	public static final String YESTERDAT_MISS_DATA="yes_missdata";
	public static final String THE_DAY_BEFORE_MISSDATA="the_day_before_missdata";
	
	public static final String KEY_PROBABILITIES = "probabilities";
	public static final String KEY_GROUP_NUMBER = "group_number";
	public static final String KEY_FREQUENCY = "frequency";
	public static final String KEY_CURRENT_GROUP_MISS = "current_group_miss";
	public static final String KEY_MAX_GROUP_MISS = "max_group_miss";
	public static final String KEY_STATISTICS = "statistics";
	
	public static final String MISSEDGROUP ="missed_group";
	
	public static final String GET_ANALYSIS="get_analysis";
	public static final String GET_LATEST_LOTTERY = "?action=get_latest_lottery";
	public static final String GET_STATISTICS= "get_statistics";
	public static final String GET_VERSION_CODE= "get_version_code";
	public static final String GET_GETYESTODAYDATA="get_yestodaydata";
	public static final String GET_BEFOREYESTADYDATA="get_beforeyestadydata";
	public static final String DEFAULT_ERROR_MISSDATA="" +
			"0,0,0,0,0,0,0,0" +
			"0,0,0,0,0,0,0,0" +
			"0,0,0,0,0,0,0,0" +
			"0,0,0,0,0,0,0,0" +
			"0,0,0,0,0,0,0,0" ;
	/*设置当前遗漏本地缓存*/
	@SuppressLint("WorldReadableFiles")
	@SuppressWarnings("deprecation")
	public static void setCachedCurrentMissData(Context context,String current_miss){
		Editor editor=context.getSharedPreferences(APP_NAME, Context.MODE_WORLD_READABLE).edit();
		editor.putString(KEY_CURRENT_MISSED, current_miss);
		editor.commit();
		
	}
	public synchronized static String getCachedCurrentMissData(Context context){
		return context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).getString(KEY_CURRENT_MISSED,DEFAULT_ERROR_MISSDATA);
	}
	/*设置最大遗漏本地缓存*/
	@SuppressLint("WorldReadableFiles")
	public static void setCachedMaxMissData(Context context,String max_miss){
		@SuppressWarnings("deprecation")
		Editor editor=context.getSharedPreferences(APP_NAME, Context.MODE_WORLD_READABLE).edit();
		editor.putString(KEY_MAX_MISSED, max_miss);
		editor.commit();
	}
	public synchronized static String getCachedMaxMissData(Context context){
		
		return context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).getString(KEY_MAX_MISSED,DEFAULT_ERROR_MISSDATA);
		
	}
	/*设置上次获奖数据本地缓存*/
	@SuppressLint("WorldReadableFiles")
	public static void setCachedLotteryData(Context context,List<latestlottery> latestlotteries){
		Editor editor=context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).edit();
		for (int i=0;i<latestlotteries.size();i++) {
			editor.putInt(i+"issue", latestlotteries.get(i).getIssue());
			editor.putInt(i+"first_number",latestlotteries.get(i).getFirstnumber());
			editor.putInt(i+"second_number",latestlotteries.get(i).getSecondnnumber());
			editor.putInt(i+"third_number", latestlotteries.get(i).getThirdnnumber());
			editor.putInt(i+"fourth_number",latestlotteries.get(i).getFourthnnumber());
			editor.putInt(i+"type",latestlotteries.get(i).getType());
			editor.putInt(i+"sum", latestlotteries.get(i).getSum());
			editor.putInt(i+"step",latestlotteries.get(i).getStep());
			editor.commit();
		}
		editor.putInt("lotteryLength",latestlotteries.size());
		editor.commit();
	}
	public synchronized static List<latestlottery> getCachedLotteryData(Context context){
		SharedPreferences shared=context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
		List<latestlottery> latestLotteery1=new ArrayList<latestlottery>();
		for(int j=0;j<getLotteryLength(context);j++){
			latestLotteery1.add(new latestlottery(shared.getInt(j + "issue", 0),
					shared.getInt(j + "first_number", 0), shared.getInt(j + "second_number", 0),
					shared.getInt(j + "third_number", 0), shared.getInt(j + "fourth_number", 0),
					shared.getInt(j + "type", 0),
					shared.getInt(j + "sum", 0), shared.getInt(j + "step", 0)));
		}
		return latestLotteery1;
	}
	/*设置上期获奖数据组遗漏统计表本地缓存*/
	public static void setCachedTableData(Context context,List<tableData> tableDatas){
		Editor editor=context.getSharedPreferences(APP_NAME_TABLE, Context.MODE_PRIVATE).edit();
		for(int i=0;i<tableDatas.size();i++){
			editor.putString(i+"group_number", tableDatas.get(i).getGroup_number());
			editor.putString(i+"frequency", tableDatas.get(i).getFrequency());
			editor.putString(i+"current_group_miss", tableDatas.get(i).getCurrent_group_miss());
			editor.putString(i+"max_group_miss", tableDatas.get(i).getMax_group_miss());
			editor.commit();
		}
		editor.putInt("TableDataLength", tableDatas.size());
		editor.commit();
	}
	public static List<tableData> getCachedTableData(Context context){
		SharedPreferences shared=context.getSharedPreferences(APP_NAME_TABLE, Context.MODE_PRIVATE);
		List<tableData> tableDatas=new ArrayList<tableData>();
		for (int i = 0; i < getTableDataLength(context); i++) {
			tableDatas.add(new tableData(shared.getString(i + "group_number", ""),
					shared.getString(i + "frequency", ""),
					shared.getString(i + "current_group_miss", ""),
					shared.getString(i + "max_group_miss", " ")));
		}
		return tableDatas;
	}

	/*保存前两天的数据
	 * 1：判断存在在昨天还是前天
	 * 2:判断是否是今日结束，最后一期。然后将其保存
	 * 3:只要的到昨日数据,前天数据=昨日数据，昨天数据等于今天数据
	 * 判断方法：当知道是新一天的数据后，将昨日本地保存的标志位==2，判断此标志位如果等于2的话就将昨天的数据替换到前天的
	 * 
	*/
	//保存 昨天 的数据
	public static void setYesTCachedData(Context context,List<latestlottery> latestlotteries,String current_miss,String maxMiss){
		
		if(latestlotteries.get(0).getIssue()==1
				&&latestlotteries.get(latestlotteries.size()-1).getIssue()==78){
			
			Editor editor=context.getSharedPreferences(YESTERDAYDATA, Context.MODE_PRIVATE).edit();
			setTheDayBCacheData(context, getYesTChachedData(context));
			setYesCachedCurrentMaxMissData(context,current_miss,maxMiss);

				for (int i = 0; i < latestlotteries.size(); i++) {
					editor.putInt(i + "issue", latestlotteries.get(i)
							.getIssue());
					editor.putInt(i + "first_number", latestlotteries.get(i)
							.getFirstnumber());
					editor.putInt(i + "second_number", latestlotteries.get(i)
							.getSecondnnumber());
					editor.putInt(i + "third_number", latestlotteries.get(i)
							.getThirdnnumber());
					editor.putInt(i + "fourth_number", latestlotteries.get(i)
							.getFourthnnumber());
					editor.putInt(i + "type", latestlotteries.get(i).getType());
					editor.putInt(i + "sum", latestlotteries.get(i).getSum());
					editor.putInt(i + "step", latestlotteries.get(i).getStep());
					editor.commit();
			}
			editor.putInt("YesDataLength", latestlotteries.size());
			editor.commit();
		}
	}
	//获得 昨天 的缓存数据
	public synchronized static List<latestlottery> getYesTChachedData(Context context){
		SharedPreferences shared=context.getSharedPreferences(YESTERDAYDATA, Context.MODE_PRIVATE);
		List<latestlottery> latestLotteery1=new ArrayList<latestlottery>();
		for(int j=0;j<getYesDataLength(context);j++){
			latestLotteery1.add(new latestlottery(shared.getInt(j + "issue", 0),
					shared.getInt(j + "first_number", 0), shared.getInt(j + "second_number", 0),
					shared.getInt(j + "third_number", 0), shared.getInt(j + "fourth_number", 0),
					shared.getInt(j + "type", 0),
					shared.getInt(j + "sum", 0), shared.getInt(j + "step", 0)));
		}
		return latestLotteery1;
	}
	//保存前天的缓冲数据
	public static void setTheDayBCacheData(Context context,List<latestlottery> latestlotteries){
		Editor editor=context.getSharedPreferences(THE_DAY_BEFORE, Context.MODE_PRIVATE).edit();
		for (int i=0;i<latestlotteries.size();i++) {
			editor.putInt(i+"issue", latestlotteries.get(i).getIssue());
			editor.putInt(i+"first_number",latestlotteries.get(i).getFirstnumber());
			editor.putInt(i+"second_number",latestlotteries.get(i).getSecondnnumber());
			editor.putInt(i+"third_number", latestlotteries.get(i).getThirdnnumber());
			editor.putInt(i+"fourth_number",latestlotteries.get(i).getFourthnnumber());
			editor.putInt(i+"type",latestlotteries.get(i).getType());
			editor.putInt(i+"sum", latestlotteries.get(i).getSum());
			editor.putInt(i+"step",latestlotteries.get(i).getStep());
			editor.commit();
		}
		editor.putInt("TheDayBLength", latestlotteries.size());
		editor.commit();
	}
	//获得前天的保存数据
	public static synchronized List<latestlottery> getTheDayBCacheData(Context context ){
		SharedPreferences shared=context.getSharedPreferences(THE_DAY_BEFORE, Context.MODE_PRIVATE);
		List<latestlottery> latestLotteery1=new ArrayList<latestlottery>();
		for(int j=0;j<getTheDBforeDataLength(context);j++){
			latestLotteery1.add(new latestlottery(shared.getInt(j + "issue", 0),
					shared.getInt(j + "first_number", 0), shared.getInt(j + "second_number", 0),
					shared.getInt(j + "third_number", 0), shared.getInt(j + "fourth_number", 0),
					shared.getInt(j + "type", 0),
					shared.getInt(j + "sum", 0), shared.getInt(j + "step", 0)));
		}
		return latestLotteery1;
	}
	//设置昨天的最大遗漏和当前遗漏
	public static void setYesCachedCurrentMaxMissData(Context context,String current_miss,String max_miss){
		SharedPreferences sharedPreferences=context.getSharedPreferences(YESTERDAT_MISS_DATA, Context.MODE_PRIVATE);
		
		String lastCurrentMiss[]=getYesCurrentMaxMissData(context);
//		Toast.makeText(context, lastCurrentMiss[0]+"", Toast.LENGTH_SHORT).show();
		Log.d("get_current", lastCurrentMiss[0]+"");
		System.out.println("get_current"+lastCurrentMiss[0]);
		setTDBMaxCurrentData(context, lastCurrentMiss[0], lastCurrentMiss[1]);
		
		Editor editor=context.getSharedPreferences(YESTERDAT_MISS_DATA, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_CURRENT_MISSED, current_miss);
		editor.putString(KEY_MAX_MISSED, max_miss);
		editor.commit();
	}
	//得到昨天的最大遗漏和当前遗漏
	public static synchronized String[] getYesCurrentMaxMissData(Context context){
		String yesCurrentMissData=
				context.getSharedPreferences(YESTERDAT_MISS_DATA, Context.MODE_PRIVATE).getString(KEY_CURRENT_MISSED, "");
		String yesMaxMissData=context.getSharedPreferences(YESTERDAYDATA, Context.MODE_PRIVATE).getString(KEY_MAX_MISSED, "");
		
		String[] result={yesCurrentMissData,yesMaxMissData};
		return result;
	}
	//设置前天最大遗漏和当前遗漏
	public static void setTDBMaxCurrentData(Context context,String current_miss,String max_miss){
		Editor editor=context.getSharedPreferences(THE_DAY_BEFORE_MISSDATA, Context.MODE_PRIVATE).edit();
		editor.putString(KEY_CURRENT_MISSED, current_miss);
		editor.putString(KEY_MAX_MISSED, max_miss);
		editor.commit();
	}
	//得到前天最大遗漏和当前遗漏
	public static synchronized String[] getTDBCUrrentMaxData(Context context){
		String TDBCurrentMissData=
				context.getSharedPreferences(THE_DAY_BEFORE_MISSDATA, Context.MODE_PRIVATE).getString(KEY_CURRENT_MISSED, "");
		String TDBMaxMissData=context.getSharedPreferences(THE_DAY_BEFORE_MISSDATA, Context.MODE_PRIVATE).getString(KEY_MAX_MISSED, "");
		
		String[] result={TDBCurrentMissData,TDBMaxMissData};
		return result;
	}
	public static int getLotteryLength(Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
		int lenght=sharedPreferences.getInt("lotteryLength",78);
		return lenght;
	}
	public static int getTableDataLength(Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(APP_NAME_TABLE, Context.MODE_PRIVATE);
		int lenght=sharedPreferences.getInt("TableDataLength",6);
		return lenght;
	}
	public static int getYesDataLength(Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(YESTERDAYDATA, Context.MODE_PRIVATE);
		int lenght=sharedPreferences.getInt("YesDataLength",78);
		return lenght;
	}
	public static int getTheDBforeDataLength(Context context){
		SharedPreferences sharedPreferences=context.getSharedPreferences(YESTERDAYDATA, Context.MODE_PRIVATE);
		int lenght=sharedPreferences.getInt("TheDayBLength",78);
		return lenght;
	}
	public static void setAnalyzeData(String data,Context context){
//		Editor editor=context.getSharedPreferences("analyze_data", Context.MODE_PRIVATE).edit();
		Editor editor=context.getSharedPreferences("analyze_data", Context.MODE_PRIVATE).edit();
		editor.putString("data",data);
		editor.commit();
	}
	public static String getAnalyzeData(Context context){
		return context.getSharedPreferences("analyze_data", Context.MODE_PRIVATE).getString("data", 
				"0,0,0,0,0,0,0,0,42,8,45,118,45,17,45,14,47,37,130,127,378,357,567,147");
	}
}
