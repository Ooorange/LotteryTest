package cn.orange.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import cn.orange.lottery.SoundPlayActivity;


public class getTableData {
	public getTableData(final OnSccussedCallBack onSccussedCallBack,final OnFailedCallBack onFailedCallBack) {
		try {
			new NetConnection(Config.SERVER_URL, HttpMethod.post, new NetConnection.SuccessedCallBack() {
				@Override
				public void onSuccessed(String result) {
					try {
						System.out.println("获取统计"+result.toString());
						JSONObject jsonObject=new JSONObject(result);
						switch (jsonObject.getInt(Config.KEY_STATUS)) {
							case Config.RESULT_STATUS_SUCCESS:
								if(onSccussedCallBack!=null){
									List<tableData> tabledata=new ArrayList<tableData>();
									JSONArray dataArray=jsonObject.getJSONArray(Config.KEY_STATISTICS);
									JSONObject dataObject;
									for (int i = 0; i < dataArray.length(); i++) {
										dataObject=dataArray.getJSONObject(i);
//										String[] group_number=dataObject.getString(Config.KEY_GROUP_NUMBER).split("\\,");
										
										SoundPlayActivity.lottery[5+i]=dataObject.getString(Config.KEY_GROUP_NUMBER);
										tabledata.add(new tableData(
												dataObject.getString(Config.KEY_GROUP_NUMBER),
												dataObject.getString(Config.KEY_FREQUENCY),
												dataObject.getString(Config.KEY_CURRENT_GROUP_MISS),
												dataObject.getString(Config.KEY_MAX_GROUP_MISS)
												));
									}
									onSccussedCallBack.successed(tabledata);
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
			},Config.KEY_ACTION,Config.GET_STATISTICS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  static interface OnSccussedCallBack{
		 void successed(List<tableData> tabledata);
	}
	public static interface OnFailedCallBack{
		 void failed(int error);
	}

}
