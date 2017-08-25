package com.wings.pushmsg;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class ReceiveMsg extends Service{

	
	//其它组件执行bindService（）方法调用
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	//此方法只会被调用一次
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	//其它组件执行startService()方法调用
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//启动定时任务
		timer.schedule(task, 0, 1000*5);
		return START_STICKY;
	}
	
	//服务销毁时调用
	@Override
	public void onDestroy() {
		super.onDestroy();
		//再次启动本服务，让服务一直运行
//		timer.cancel();
		Intent intent=new Intent(this,ReceiveMsg.class);
		this.startService(intent);
	}
	
	//定时任务
	private final Timer timer = new Timer(); 
	
	//定时任务,在异步线程中执行
	private TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			handler.sendEmptyMessage(1);
		}
	};
	
	private static int i=1;
	
	//多线程信息交流
	private static Handler handler=new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			System.out.println("定时任务执行中----:"+i);
		};
	};
}
