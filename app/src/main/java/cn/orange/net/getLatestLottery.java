package cn.orange.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import cn.orange.lottery.MainActivity;
import cn.orange.lottery.SoundPlayActivity;


public class getLatestLottery {
	public getLatestLottery(final OnSccussedCallBack onSccussedCallBack,final OnFailedCallBack onFailedCallBack,String URl) {
		try {				//Config.SERVER_URL
			new NetConnection(URl, HttpMethod.post, new NetConnection.SuccessedCallBack() {
				@Override
				public void onSuccessed(String result) {
					try {
						JSONObject jsonObject=new JSONObject(result);
						switch (jsonObject.getInt(Config.KEY_STATUS)) {
							case Config.RESULT_STATUS_SUCCESS:
								if(onSccussedCallBack!=null){
									List<latestlottery> latestlotteries=new ArrayList<latestlottery>();
									JSONArray dataArray=jsonObject.getJSONArray(Config.KEY_WINNUMBER);
									JSONObject dataObject;
									for (int i = 0; i < dataArray.length(); i++) {
										dataObject=dataArray.getJSONObject(i);
										
										latestlotteries.add(new latestlottery(
												dataObject.getInt(Config.KEY_ISSUE),
												dataObject.getInt(Config.KEY_FIRSTNUMBER),
												dataObject.getInt(Config.KEY_SECONDNUMBER),
												dataObject.getInt(Config.KEY_THIRDNUMBER),
												dataObject.getInt(Config.KEY_FOURTHNUMBER),
												dataObject.getInt(Config.KEY_TYPE),
												dataObject.getInt(Config.KEY_SUM),
												dataObject.getInt(Config.KEY_STEP)
												));
									}
									SoundPlayActivity.lottery[0]=String.valueOf(
											dataArray.getJSONObject(dataArray.length()-1).getInt(Config.KEY_FIRSTNUMBER));
									SoundPlayActivity.lottery[1]=String.valueOf(
											dataArray.getJSONObject(dataArray.length()-1).getInt(Config.KEY_SECONDNUMBER));
									SoundPlayActivity.lottery[2]=String.valueOf(
											dataArray.getJSONObject(dataArray.length()-1).getInt(Config.KEY_THIRDNUMBER));
									SoundPlayActivity.lottery[3]=String.valueOf(
											dataArray.getJSONObject(dataArray.length()-1).getInt(Config.KEY_FOURTHNUMBER));
									SoundPlayActivity.lottery[4]=String.valueOf(
											dataArray.getJSONObject(dataArray.length()-1).getInt(Config.KEY_TYPE));
									if(jsonObject.has(Config.KEY_CURRENT_MISSED)&&jsonObject.has(Config.KEY_MAX_MISSED))
									onSccussedCallBack.successed(jsonObject.getString(Config.KEY_CURRENT_MISSED),
											jsonObject.getString(Config.KEY_MAX_MISSED),latestlotteries);
									else
										onSccussedCallBack.successed(Config.getCachedCurrentMissData(MainActivity.mainActivity),
												Config.getCachedMaxMissData(MainActivity.mainActivity),latestlotteries);
								}
							break;
							case Config.RESULT_STATUS_NONEWS:
								if(onFailedCallBack!=null){
									 onFailedCallBack.failed(Config.RESULT_STATUS_NONEWS);
								}
								break;
							default:
								if(onFailedCallBack!=null){
									onFailedCallBack.failed(Config.RESULT_STATUS_FAIL);
								}
							break;
						}
					} catch (JSONException e) {
						e.printStackTrace();
						if(onFailedCallBack!=null){
							onFailedCallBack.failed(Config.RESULT_JSON_EXCEPECTION);
						}
					}
				}
			},new NetConnection.FailedCallBack() {
				@Override
				public void onFailed() {
					if(onFailedCallBack!=null)
					onFailedCallBack.failed(Config.RESULT_STATUS_FAIL);
				}
			});
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  static interface OnSccussedCallBack{
		 void successed( String current_missed,String max_missed,List<latestlottery> latestlotteries);
	}
	public static interface OnFailedCallBack{
		 void failed(int error);
	}
}
