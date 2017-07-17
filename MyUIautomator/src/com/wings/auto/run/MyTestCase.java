package com.wings.auto.run;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.IAutomationSupport;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.wings.auto.tool.MyAutoHelper;

import android.os.Bundle;

public class MyTestCase extends UiAutomatorTestCase{
	
	protected MyAutoHelper auto;
	
	/**
	 * 执行测试 （将此方法放入main方法中执行）
	 * @param testCaseName 类名+报名，例如：com.wings.auto.MyAuto
	 */
	protected static void runTestCase(String testCaseName){
		AutoRunTest autoRunTest=new AutoRunTest(testCaseName);
		autoRunTest.runUIAutomator();
	}
	

	@Override
	public IAutomationSupport getAutomationSupport() {
		return super.getAutomationSupport();
	}

	@Override
	public Bundle getParams() {
		return super.getParams();
	}

	@Override
	public UiDevice getUiDevice() {
		return super.getUiDevice();
	}

	@Override
	protected void setUp() throws Exception {
		auto=MyAutoHelper.getInstance();
		super.setUp();
	}

	@Override
	public void sleep(long ms) {
		super.sleep(ms);
	}

	@Override
	protected void tearDown() throws Exception {
		auto=null;
		super.tearDown();
	}
}
