package cn.orange.net;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class getAnalyze {
	public getAnalyze(final OnSccussedCallBack onSccussedCallBack,final OnFailedCallBack onFailedCallBack) {
		try {
			new NetConnection(Config.SERVER_URL, HttpMethod.post, new NetConnection.SuccessedCallBack() {
				@Override
				public void onSuccessed(String result) {
					try {
						
						JSONObject jsonObject=new JSONObject(result);
						switch (jsonObject.getInt("statue")) {
							case Config.RESULT_STATUS_SUCCESS:
								if(onSccussedCallBack!=null){
									StringBuffer analysis=new StringBuffer();
									if(jsonObject.has("numbercount")){
									JSONObject json=jsonObject.getJSONObject("numbercount");
									analysis.append(json.getInt("number1"));
									analysis.append(","+json.getInt("number2"));
									analysis.append(","+json.getInt("number3"));
									analysis.append(","+json.getInt("number4"));
									analysis.append(","+json.getInt("number5"));
									analysis.append(","+json.getInt("number6"));
									analysis.append(","+json.getInt("number7"));
									analysis.append(","+json.getInt("number8"));
									}
									if(jsonObject.has("formanalyse")){
									JSONObject json2=jsonObject.getJSONObject("formanalyse");
									json2.getInt("samethreemaxmiss");
									analysis.append(","+json2.getInt("samethreenowmiss"));
									analysis.append(","+json2.getInt("shunmaxmiss"));
									analysis.append(","+json2.getInt("shunnowmiss"));
									analysis.append(","+json2.getInt("twodoublemaxmiss"));
									analysis.append(","+json2.getInt("twodoublenowmiss"));
									}
									if(jsonObject.has("hotcoldnumber")){
									JSONObject json3=jsonObject.getJSONObject("hotcoldnumber");
									json3.getInt("group2cdnowmiss1");
									analysis.append(","+json3.getInt("group2cdnowmiss2"));
									analysis.append(","+json3.getInt("group2cdnumber1"));
									analysis.append(","+json3.getInt("group2cdnumber2"));
									analysis.append(","+json3.getInt("group2htnumber1"));
									analysis.append(","+json3.getInt("group2htnumber2"));
									analysis.append(","+json3.getInt("group3cdnowmiss1"));
									
									analysis.append(","+json3.getInt("group3cdnowmiss2"));
									analysis.append(","+json3.getInt("group3cdnumber1"));
									analysis.append(","+json3.getInt("group3cdnumber2"));
									analysis.append(","+json3.getInt("group3htnumber1"));
									analysis.append(","+json3.getInt("group3htnumber2"));
									}
									onSccussedCallBack.successed(analysis.toString());
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
			},Config.KEY_ACTION,Config.GET_ANALYSIS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public  static interface OnSccussedCallBack{
		 void successed(String analyze);
	}
	public static interface OnFailedCallBack{
		 void failed(int error);
	}
}
