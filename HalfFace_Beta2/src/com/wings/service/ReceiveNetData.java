package com.wings.service;



import com.wings.data.storage.DataProvider;
import com.wings.net.NetUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

//执行定时任务服务
public class ReceiveNetData extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//定时执行的内容
//		new Thread(new Runnable() {
//				@Override
//				public void run() {
//					//
//				}
//		}).start();
		if(NetUtils.isNetworkAvailable(this))
			DataProvider.getInstance().writeJson(getApplicationContext());
		
		//长期定时实现逻辑
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour = 24 * 60 * 60 * 1000; // 24小时的毫秒数
		//System.currentTimeMillis()方法可以获取到1970 年1 月1 日0 点至今所经历时间的毫秒数
		//SystemClock.elapsedRealtime()方法可以获取到系统开机至今所经历时间的毫秒数
		long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
		Intent i = new Intent(this, AlarmReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		//第一个参数是一个整型参数，用于指定AlarmManager 的工作类型，有四种值可选
		//ELAPSED_REALTIME 表示让定时任务的触发时间从系统开机开始算起，但不会唤醒CPU
		//ELAPSED_REALTIME_WAKEUP 同样表示让定时任务的触发时间从系统开机开始算起，但会唤醒CPU
		//RTC 表示让定时任务的触发时间从1970 年1月1 日0 点开始算起，但不会唤醒CPU
		//RTC_WAKEUP 同样表示让定时任务的触发时间从1970 年1 月1 日0 点开始算起，但会唤醒CPU
		
		//第二个参数
		//定时任务触发的时间，以毫秒为单位。如果第一个参数使用的是ELAPSED_REALTIME 或ELAPSED_REALTIME_WAKEUP，
		//则这里传入开机至今的时间再加上延迟执行的时间。如果第一个参数使用的是RTC 或RTC_WAKEUP，则这里传入1970 年1 月1 日0 点至今的时间再加上延迟执行的时间
		
		//第三个参数是一个PendingIntent
		//一般会调用getBroadcast()方法来获取一个能够执行广播的PendingIntent。这样当定时任务被触发的时候，广播接收器的onReceive()方法就可以得到执行
		manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
			
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
}
