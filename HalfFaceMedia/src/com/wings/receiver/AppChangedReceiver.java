package com.wings.receiver;

import com.wings.pushmsg.ReceiveMsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AppChangedReceiver extends BroadcastReceiver{
	
	 public void onReceive(Context context, Intent intent) {
	        //区分接收到的是哪种广播
	        String action = intent.getAction();
	        //获取广播中包含的应用包名
	        Uri uri = intent.getData();
	        if(action.equals("android.intent.action.PACKAGE_ADDED")){
	        	openService(context);
	        	if(context != null)
	        		Toast.makeText(context, uri + "被安装了",Toast.LENGTH_SHORT).show();
	        }
	        else if(action.equals("android.intent.action.PACKAGE_REPLACED")){
	        	openService(context);
	        	if(context != null)
	        		Toast.makeText(context, uri + "被更新了",Toast.LENGTH_SHORT).show();
	        }
	        else if(action.equals("android.intent.action.PACKAGE_REMOVED")){
	        	if(context != null)
	        		Toast.makeText(context, uri + "被卸载了",Toast.LENGTH_SHORT).show();
	        }
	    }
	 
	 public void openService(Context context){
//		Intent intent=new Intent();
//		intent.setClass(context, ReceiveMsg.class);
//		context.startService(intent);
	 }
}
