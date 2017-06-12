package com.wings.auto;

import java.io.IOException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.wings.utils.RunTestCase;


public class MyAuto extends UiAutomatorTestCase{
	
	
	public static void main(String[] args) throws IOException {  
        RunTestCase runTestCase=new RunTestCase("MyAuto",  
                "com.wings.auto.MyAuto", "MyAuto", "1");  
        runTestCase.setDebug(true);  
        runTestCase.runUiautomator(); 
    }  
	
	public void test01() throws Throwable{
//		UiDevice uDevice=super.getUiDevice();
//		uDevice.pressBack();
//		sleep(2000);
//		uDevice.pressBack();
//		sleep(2000);
//		uDevice.pressRecentApps();
//		sleep(2000);
		AutoHelper autoHelper=AutoHelper.getInstance();
		autoHelper.backHome();
		autoHelper.clickText("Camera");
		autoHelper.sleep(3000);
		autoHelper.clickDesc("click to capture");
		autoHelper.sleep(4000);
		autoHelper.clickDesc("click to capture");
		autoHelper.sleep(4000);
		autoHelper.clickDesc("click to capture");
		autoHelper.sleep(4000);
		autoHelper.backHome();
	}
	
	public void test02()throws Throwable{
		AutoHelper autoHelper=AutoHelper.getInstance();
		autoHelper.backHome();
		autoHelper.swipeLeft(10);
		autoHelper.sleep(3000);
		autoHelper.clickText("Settings");
		autoHelper.sleep(3000);
		autoHelper.swipeUp(10);
		autoHelper.sleep(3000);
		autoHelper.swipeUp(10);
		autoHelper.sleep(3000);
		autoHelper.swipeUp(10);
		autoHelper.sleep(3000);
		autoHelper.clickText("About phone");
		autoHelper.sleep(5000);
	}
	
}
