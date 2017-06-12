package com.wings.hello;

public class TestA {

	public static void main(String[] args) {
		
		System.out.println("A main is called");
		
		new TestA().test();
	}
	
	public void test(){
		System.out.println("A test is called");
	}
}
