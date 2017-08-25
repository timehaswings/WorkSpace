package com.example.utils;


import com.wings.pushmsg.ReceiveMsg;

import android.Manifest;
import android.app.Application;
import android.content.Intent;

public class HalfFaceApp extends Application{
	
	public static String[] permissions=new String[]{
			Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
	};
	
	@Override
	public void onCreate() {
		super.onCreate();
		//启动服务
		Intent intent=new Intent(this,ReceiveMsg.class);
		this.startService(intent);
	}
	
}
