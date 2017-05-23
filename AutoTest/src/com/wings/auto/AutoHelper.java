package com.wings.auto;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class AutoHelper {
	
	private static AutoHelper auto;
	private static UiDevice mUiDevice;
	private static int width;
	private static int height;
	
	private AutoHelper(){
	}
	
	public static AutoHelper getInstance(){
		if(auto==null){
			auto=new AutoHelper();
		}
		if(mUiDevice==null){
			mUiDevice=UiDevice.getInstance();
			width=mUiDevice.getDisplayWidth();
			height=mUiDevice.getDisplayHeight();
		}
		return auto;
	}
	
	public boolean clickText(String text) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().text(text)).click();
	}
	
	public boolean clickDesc(String desc) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().description(desc)).click();
	}
	
	public boolean clickResId(String id,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).click();
	}
	
	public boolean swipeDown(int steps){
		return mUiDevice.swipe(width/2, height/5, width/2, height*4/5, steps);
	}
	
	public boolean swipeUp(int steps){
		return mUiDevice.swipe(width/2, height*4/5, width/2, height/5, steps);
	}
	
	public boolean swipeLeft(int steps){
		return mUiDevice.swipe(width*4/5, height/2, width/5, height/2, steps);
	}
	
	public boolean swipeRight(int steps){
		return mUiDevice.swipe(width/5, height/2, width*4/5, height/2, steps);
	}
	
	public void backHome() throws InterruptedException{
		int i=0;
		while(i++<5){
			mUiDevice.pressBack();
			sleep(1000);
		}
		mUiDevice.pressHome();
	}
	
	public void sleep(int time) throws InterruptedException{
		Thread.sleep(time);
	}
}
