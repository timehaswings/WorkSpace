package com.wings.auto.run;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class AutoRunTest {
	
	private String testCaseName;
	private String testJar;
	
	public AutoRunTest(String testCaseName){
		this.testCaseName=testCaseName;
		String temp=testCaseName.substring(testCaseName.lastIndexOf(".")+1);
		this.testJar=temp;
	}
	
	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	private void executeCommamd(String command){
		 try {  
	            Process p = Runtime.getRuntime().exec(command);
	            InputStream input = p.getInputStream();  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));    
	            String line="";
	            while ((line = reader.readLine()) != null) {  
	                System.out.println(line);  
	            }  
	        } catch (Exception e) {
				e.printStackTrace();
			} 
	 }
	
	public  void createBuild(){
		System.out.println("create build.xml");
		executeCommamd("cmd /c android create uitest-project -n "+testJar+" -t 5 -p "+getWorkSpase());
		System.out.println("create build.xml compelete!\t\n");
	}
	
	public  void createAnt(){
		 System.out.println("create ant");
		 executeCommamd("cmd /c ant clean -p");
		 executeCommamd("cmd /c ant build -p");
		 System.out.println("create ant compelete!\t\n");
	 }
	 
	public  void pushJar(){
		 System.out.println("start push .jar");
		 executeCommamd("adb push bin\\"+testJar+".jar /data/local/tmp");
		 System.out.println("compelete push .jar ! \t\n");
	 }
	 
	public void runtest(){
		 System.out.println("start run ...."+testJar+"..."+testCaseName);
		 executeCommamd("adb shell uiautomator runtest "+testJar+".jar --nohup -c "+testCaseName+" -e debug true");
		 System.out.println("compelete run test.jar!\t\n");
	 }
	
	public String getWorkSpase() {  
			String abPath = System.getProperty("user.dir");
	        System.out.println("---workspace path\t\n" + abPath);
	        return abPath;  
	 }
	
	public void runUIAutomator(){
		createBuild();
		createAnt();
		pushJar();
		runtest();
	}
}

