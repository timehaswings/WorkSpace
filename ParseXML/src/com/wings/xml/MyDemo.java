package com.wings.xml;

import java.io.File;
import java.text.ParseException;
import java.util.TimerTask;


public class MyDemo {
	
	public static void main(String[] args){
		
		MyDemo demo=new MyDemo();
//		demo.test0();
//		demo.test2();
//		demo.test3();
		demo.testEmail();
	}
	
	public void test0(){
		MyDom dom=new MyDom();
		File file=new File("C:\\Users\\301002028.HUAQIN\\Desktop\\临时文件\\qian1.xml");
//		dom.getAllNode(dom.getDocument(file));
//		dom.getNode(file);
		dom.getAllRootNode(file);
	}
	
	public void test1(){
		MyTimer timer=new MyTimer();
		timer.timerExecutors(3,1,new Runnable() {
			String s="~";
			@Override
			public void run() {
				System.out.println(s+=s);
			}
		});
	}
	
	public void test2(){
		MyTimer timer=new MyTimer();
		timer.timerExecutors(1, 1, new TimerTask() {
			int i=0;			
			@Override
			public void run() {
				System.out.println(i++);
			}
		});
	}
	
	public void test3(){
		MyTimer timer=new MyTimer();
		try {
			timer.exeDay("19:45:00", new Runnable() {
				@Override
				public void run() {
					System.out.println("Hello");
				}
			});
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void testEmail(){
		MyEmail email=new MyEmail();
		try {
			email.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
