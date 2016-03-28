package cn.orange.lottery;

import cn.orange.net.Config;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/*
 * 开机自启动
 * */
public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Config.ACTION)){
			Intent i=new Intent(context, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
		
	}

}
