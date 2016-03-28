package cn.orange.net;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getRemoteVersion {
	public getRemoteVersion(final onSuccessedCallBack onSuccessedCallBack,final onFailedCallBack onFailedCallBack) {
		try {
			new NetConnection(Config.SERVER_URL, HttpMethod.post, new NetConnection.SuccessedCallBack() {
				@Override
				public void onSuccessed(String result) {
					System.out.println("------"+result);
						try {
							JSONObject object=new JSONObject(result);
							switch (object.getInt(Config.KEY_STATUS)) {
							case Config.RESULT_STATUS_SUCCESS:
								if(onSuccessedCallBack!=null){
									JSONArray versionArray=object.getJSONArray(Config.KEY_VERSION);
									JSONObject data;
									int versionCode=-1;
									String versionName="";
									String appName="";
									String apkname="";
									
									for(int i=0;i<versionArray.length();i++){
										data=versionArray.getJSONObject(i);
										
										versionCode=data.getInt(Config.KEY_VERSIONCODE);
										versionName=data.getString(Config.KEY_VERSIONNAME);
										appName=data.getString(Config.KEY_APPNAME);
										apkname=data.getString(Config.KEY_APKNAME);
									}
									onSuccessedCallBack.Successed(apkname,appName,versionName,versionCode);
								}
								break;

							case Config.RESULT_JSON_EXCEPECTION:
								if(onFailedCallBack!=null){
									onFailedCallBack.onFailed(Config.RESULT_STATUS_FAIL);
								}
								break;
							}
						} catch (JSONException e) {
							e.printStackTrace();
							if(onFailedCallBack!=null){
								onFailedCallBack.onFailed(Config.RESULT_JSON_EXCEPECTION);
							}
						}
				}
			}, new NetConnection.FailedCallBack() {
				
				@Override
				public void onFailed() {
					if(onFailedCallBack!=null){
						onFailedCallBack.onFailed(Config.RESULT_STATUS_FAIL);
					}
				}
			}, Config.KEY_ACTION,Config.GET_VERSION_CODE);
		} catch (MalformedURLException e) {
			if(onFailedCallBack!=null){
				onFailedCallBack.onFailed(Config.RESULT_STATUS_FAIL);
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			if(onFailedCallBack!=null){
				onFailedCallBack.onFailed(Config.RESULT_STATUS_FAIL);
			}
		}
	}
	public static interface onSuccessedCallBack{
		public void  Successed(String apkName,String appName,String versionName,int versionCode);
	}
	public static interface onFailedCallBack{
		public void onFailed(int error);
	}
}
