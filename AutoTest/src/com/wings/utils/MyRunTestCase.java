package com.wings.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyRunTestCase {
	
	//需要配置参数
	public static String testCaseName="com.wings.auto.MyAuto";//测试包名+类名
	//public static String reportFile="a.txt";//输出文件
	
	public static void main(String args[]){
		createBuild();
		createAnt(); 
		pushJar();
		runtest();
	}
	
	public static void createBuild(){
		System.out.println("create build.xml");
		executeCommamd("cmd /c android create uitest-project -n test -t 5 -p "+getWorkSpase());
		System.out.println("create build.xml compelete!\t\n");
	}
	
	 public static String getWorkSpase() {  
	        File directory = new File("");  
	        String abPath = directory.getAbsolutePath(); 
	        System.out.println("---workspace path：\t\n" + abPath);
	        return abPath;  
	 }
	 
	 public static void executeCommamd(String command){
		 try {  
	            Process p = Runtime.getRuntime().exec(command);
	            InputStream input = p.getInputStream();  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));    
	            int code = 0;  
	            while ((code = reader.read()) != -1) {  
	                System.out.print((char) code);  
	            }  
	        } catch (Exception e) {
				e.printStackTrace();
			} 
	 }
	 
	 public static void createAnt(){
		 System.out.println("create ant");
		 executeCommamd("cmd /c ant build");
		 System.out.println("create ant compelete!\t\n");
	 }
	 
	 public static void pushJar(){
		 System.out.println("start push test.jar");
		 executeCommamd("adb push bin\\test.jar /data/local/tmp");
		 System.out.println("compelete push test.jar ! \t\n");
	 }
	 
	 public static void runtest(){
		 System.out.println("start run test.jar");
//		 executeCommamd("adb shell uiautomator runtest test.jar --nohup -c "+testCaseName+" > "+reportFile);
		 executeCommamd("adb shell uiautomator runtest test.jar --nohup -c "+testCaseName+" -e debug true");
		 System.out.println("compelete run test.jar!\t\n");
	 }
	 
	
}
