package cn.orange.version;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;
import cn.orange.net.Config;
import cn.orange.net.getRemoteVersion;
import cn.orange.net.getRemoteVersion.onFailedCallBack;
import cn.orange.net.getRemoteVersion.onSuccessedCallBack;

public class getVersion {
	public  Handler handler=new Handler(){};
	public  ProgressDialog progressDialog;
	public  Context context;
	public getVersion(Context context1){
		context=context1;
	}
	
	public int getLocalVersionCode(){
		int verCode=-1;
		try {
			verCode=context.getPackageManager().getPackageInfo("cn.orange.lottery", 0).versionCode;
			System.out.println("verCode="+verCode);
		} catch (NameNotFoundException e) {
			
			e.printStackTrace();
		}
		return verCode;
	}
	public String getLocalVersionName(){
		String versionName="";
		try {
			versionName=context.getPackageManager().getPackageInfo("cn.orange.lottery", 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return versionName;
	}
	public  void getServerVersion(){
		new getRemoteVersion(new onSuccessedCallBack() {
			
			@Override
			public void Successed(String apkName, String appName, String versionName,int versionCode) {
				if(versionCode>getLocalVersionCode()){
					//显示提示下载对话框
					//MainActivity.timer.cancel();
					showDialog(versionCode);
				}else{
					//当前已经为最新版本,不做处理
					
				}
			}
		}, new onFailedCallBack(){

			@Override
			public void onFailed(int error) {
				//获取消息失败
				switch (error) {
				case Config.RESULT_JSON_EXCEPECTION:
					Toast.makeText(context,"json格式错误", Toast.LENGTH_SHORT).show();
					break;

				default:
					//Toast.makeText(context,"获取版本消息失败", Toast.LENGTH_SHORT).show();
					break;
				}
			}
			
		});
	}
	private   void showDialog(int versionCode) {
		
		Dialog dialog=new AlertDialog.Builder(context)
				.setTitle("请更新")
//				.setMessage("修复历史查询bug,新版本："+versionCode)
				.setMessage("发现新版本"+versionCode)
				.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//下载新版本
						progressDialog=new ProgressDialog(context);
						progressDialog.setTitle("正在下载");
						progressDialog.setMessage("请稍后");
						progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						downLoadNewApk();
					}
				})
				.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//取消更新
						//context.startActivity(new Intent());
					}
				})
				.create();
		dialog.show();
	}
	//下载新apk
	public  void downLoadNewApk() {
		progressDialog.show();
		new Thread(){
			@Override
			public void run() {
/*				HttpClient client=new DefaultHttpClient();
				HttpGet get=new HttpGet(Config.DOWNLOADURL);
				HttpResponse httpResponse;*/
				File file;
				try {
					URL url=new URL(Config.DOWNLOADURL);
					HttpURLConnection connection=(HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(10000);
					FileOutputStream fileOutputStream=null;
					InputStream inputStream=null;
					if(connection.getResponseCode()==200){
						inputStream =connection.getInputStream();
						File desDir=new File(context.getFilesDir().getParent()+ File.separator+"apkVersion");
						if(!desDir.exists()){
							desDir.mkdir();
						}
						if(inputStream!=null){
							file=new File(context.getFilesDir().getParent()+ File.separator+"apkVersion"+File.separator+"lottery.apk");
//							fileOutputStream=context.openFileOutput("lottery.apk", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
							fileOutputStream=new FileOutputStream(file);
							byte[] buffer=new byte[64];
							int length=0;
							while((length=inputStream.read(buffer))!=-1){
								fileOutputStream.write(buffer,0,length);
							}
							
							fileOutputStream.flush();
							inputStream.close();
							fileOutputStream.close();
							connection.disconnect();
 						}
					}

					
					downLoadComplete();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	public  void downLoadComplete() {
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				progressDialog.cancel();
				updata();
			}
		});
	}
	@SuppressLint("InlinedApi")
	public  void updata() {
		try {
			String command="chmod 777"+" "+context.getFilesDir().getParent()+ File.separator+"apkVersion"+File.separator+"lottery.apk";
			String command2="chmod 777"+" "+context.getFilesDir().getParent()+ File.separator+"apkVersion";
			Runtime runtime=Runtime.getRuntime();
			runtime.exec(command2);
			runtime.exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent i=new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//		i.setDataAndType(Uri.fromFile(new File("lottery.apk")),
		i.setDataAndType(Uri.fromFile(new File(context.getFilesDir().getParent()+ File.separator+"apkVersion"+File.separator+"lottery.apk")),
				"application/vnd.android.package-archive");
		((Activity) context).finish();
		context.startActivity(i);
	}
}
