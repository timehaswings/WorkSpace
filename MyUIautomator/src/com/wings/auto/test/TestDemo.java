package com.wings.auto.test;

import com.wings.auto.run.MyTestCase;

public class TestDemo extends MyTestCase{

	public static void main(String[] args) {
		runTestCase("com.wings.auto.test.TestDemo");
	}
	
	public void test001(){
		auto.pressBack();
		auto.sleep(2000);
		auto.pressBack();
		auto.sleep(2000);
		auto.pressBack();
		auto.sleep(2000);
	}
}
