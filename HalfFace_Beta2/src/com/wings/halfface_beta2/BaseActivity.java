package com.wings.halfface_beta2;

import com.wings.service.ReceiveNetData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity{
	
	public Context mContext;
	public LauncherApplication mApplication;
//	private long time=0;//用于退出
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(mContext==null)
			mContext=getApplicationContext();
		if(mApplication==null)
			mApplication=(LauncherApplication) getApplication();
		mApplication.addActivitys(this);
		openService();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mApplication.removeOneActivitys(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	
//	//退出方法
//	public void exit() {
//		//如果在大于2秒
//		if (System.currentTimeMillis() - time > 2000) {
//			//获得当前的时间
//			time = System.currentTimeMillis();
//			showToast("再点击一次退出应用程序");
//		} else {
//			//点击在两秒以内
//			mApplication.removeAllActivitys();//执行移除所以Activity方法
//			time=0;
//		}
//	}
	
	/* 把Toast定义成一个方法  可以重复使用，使用时只需要传入需要提示的内容即可*/
	public void showToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}
	
	//跳转
	public void startActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }
	
	/**
	 * 开启后台常驻服务
	 */
	public void openService(){
		Intent intent = new Intent(this, ReceiveNetData.class);
		startService(intent);
	}
}
