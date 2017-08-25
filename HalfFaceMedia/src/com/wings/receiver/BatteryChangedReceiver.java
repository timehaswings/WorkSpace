package com.wings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryChangedReceiver extends BroadcastReceiver {

	private static final String TAG = "BatteryChangedReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		int currLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0); // 当前电量
		int total = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1); // 总电量
		int percent = currLevel * 100 / total;
		Toast.makeText(context, "当前电量："+percent, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 获取实时电量
	 */
	public int getBattery(Context context){
		Intent batteryIntent = context.getApplicationContext().registerReceiver(null,  
		        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  
		int currLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);  
		int total = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);  
		return currLevel * 100 / total; 
	}
}