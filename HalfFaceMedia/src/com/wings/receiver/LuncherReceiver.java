package com.wings.receiver;

import com.wings.pushmsg.ReceiveMsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LuncherReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		//开机自启
		Intent intent2=new Intent();
		intent2.setClass(context, ReceiveMsg.class);
		context.startActivity(intent2);
	}
	
	
}
