package cn.orange.net;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;


public class NetConnection {
	public NetConnection(final String url,final HttpMethod method,final SuccessedCallBack successed,final FailedCallBack failed,final String ...args) throws MalformedURLException, IOException {
		 
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				
				StringBuffer stringBuffer=new StringBuffer();
				for (int i = 0; i < args.length; i+=2) {
					stringBuffer.append(args[i]).append("=").append(args[i+1]).append("&");
				}
			try {
				URLConnection conn;
				switch (method) {
				case post:
					conn=new URL(url).openConnection();
					conn.setDoOutput(true);
					conn.setConnectTimeout(5000);	
					BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(),Config.CHARSET));
					bufferedWriter.write(stringBuffer.toString());
					bufferedWriter.flush();
					break;
				default:
					conn=new URL(url+"?"+stringBuffer.toString()).openConnection();
					break;
				}
					System.out.println("Request URL="+url+"?"+stringBuffer.toString());
//					System.out.println("Request Data="+stringBuffer);
					BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream(),Config.CHARSET));
					String line=null;
					StringBuffer result = new StringBuffer();
					while((line=br.readLine())!=null){
						result.append(line);
					}
					//System.out.println("<<<<<<<<<<<<<<<<result<<<<<<<<<<<"+result);
					return result.toString();//-------------------非常重要的返回，用于判断回调方法实例是否为空。
				}catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				if (result != null) {
					if (successed != null) {
						successed.onSuccessed(result);
					}
				} else {
					if (failed != null) {
						failed.onFailed();
					}
				}
				super.onPostExecute(result);
			}
		}.execute();
	}
	public  static interface SuccessedCallBack{
		  void onSuccessed(String result);
	}
	public static  interface FailedCallBack{
		  void onFailed();
	}
}