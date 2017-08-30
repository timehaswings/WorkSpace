package com.wings.halfface_beta2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Application;

public class LauncherApplication extends Application{
	
	private List<Activity> activitys;
	private Map<String, Object> shareData;
	
	@Override
	public void onCreate() {
		super.onCreate();
		activitys=new ArrayList<Activity>();
		shareData=new HashMap<String, Object>();
	}
	
	//当终止应用程序对象时调用，不保证一定被调用，当程序是被内核终止以便为其他应用程序释放资源，那
	//么将不会提醒，并且不调用应用程序的对象的onTerminate方法而直接终止进 程
	@Override
	public void onTerminate() {
		super.onTerminate();
		
	}
	
	//当后台程序已经终止资源还匮乏时会调用这个方法。好的应用程序一般会在这个方法里面释放一些不必
	//要的资源来应付当后台程序已经终止，前台应用程序内存还不够时的情况
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		activitys=null;
		shareData=null;
	}
	
	public void addActivitys(Activity activity){
		if(activitys != null && !activitys.contains(activity))
			activitys.add(activity);
	}
	
	public void removeOneActivitys(Activity activity){
		if(activitys != null && activitys.contains(activity))
			activitys.remove(activity);
	}
	
	public void removeAllActivitys(){
		if(activitys != null && activitys.size()>0){
			for(Activity ac:activitys){
				activitys.remove(ac);
				ac.finish();
			}
			activitys=null;
		}
	}
	
	public void setShareData(String key,Object value){
		if(shareData != null)
			shareData.put(key, value);
	}
	
	public Object getShareData(String key){
		Object object=null;
		if(shareData != null){
			object=shareData.get(key);
			shareData.remove(key);
		}
		return object;
	}
	
}
