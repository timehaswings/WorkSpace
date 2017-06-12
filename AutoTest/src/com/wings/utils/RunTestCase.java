package com.wings.utils;
import java.io.BufferedReader;  
import java.io.BufferedWriter;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
  
public class RunTestCase {  
  
    // 以下参数需要配置，用例集id，用例id，安卓id  
    private static String android_id = "1";  
    private static String jar_name = "MyAuto";  
    private static String test_class = "com.wings.auto.MyAuto";  
    private static String test_name = "MyAuto"; 
    private static String workspace_path;  
  
    // 是否被调试,默认不开启调试模式，UiAutomator的调试，需要配合remote Java Apllication 使用，详见后续文章  
    public boolean isDebug = false;  
  
    public void setDebug(boolean isDebug) {  
        this.isDebug = isDebug;  
    }  
  
    public static void main(String[] args) {  
        RunTestCase runCase = new RunTestCase();  
        runCase.runUiautomator();  
    }  
  
    public RunTestCase() {  
        workspace_path = getWorkSpase();  
        System.out.println("---workspace path：\t\n" + getWorkSpase());  
    }  
  
    /** 
     * 原生工程调试构造器，输入jar包名，包名，类名，用例名 
     * @param jarName 要打成的jar包名称 
     * @param testClass packageName+testCaseName 
     * @param testName 测试的方法名称 
     * @param androidId Android list ID值 
     */  
    public RunTestCase(String jarName, String testClass, String testName,  
            String androidId) {  
        System.out.println("-----------start--uiautomator--debug-------------");  
        workspace_path = getWorkSpase();  
        System.out.println("----workspace：\t\n" + getWorkSpase());  
        jar_name = jarName;  
        test_class = testClass;  
        test_name = "";  
        android_id = androidId;       
    }  
  
    // 原生工程运行步骤  
    public void runUiautomator() {  
        creatBuildXml();  
        modfileBuild();  
        buildWithAnt();  
        if (System.getProperty("os.name").equals("Linux")) {  
            pushTestJar(workspace_path + "/bin/" + jar_name + ".jar");  
        } else {  
            pushTestJar(workspace_path + "\\bin\\" + jar_name + ".jar");  
        }  
        if (test_name.equals("")) {  
            runTest(jar_name, test_class);  
            return;  
        }  
        runTest(jar_name, test_class + "." + test_name);  
        System.out.println("*************************");  
        System.out.println("*----TESTCASE FINISH----*");  
        System.out.println("*************************");  
    }  
  
    // 1--判断是否有build  
    public boolean isBuild() {  
//        File buildFile = new File("build.xml");  
//        if (buildFile.exists() && buildFile.isFile()) {  
//            return true;  
//        }  
        // 创建build.xml  
    	String str=test_class.replace(".", "\\");
        execCmd("cmd /c android create uitest-project -n " + jar_name + " -t "  
                + android_id + " -p " + workspace_path +"\\"+str);  
        return false;  
    }  
  
    // 创建build.xml  
    public void creatBuildXml() {  
    	String str=test_class.replace(".", "\\");
        execCmd("cmd /c android create uitest-project -n " + jar_name + " -t "  
                + android_id + " -p " + workspace_path +"\\"+str);  
    }  
  
    // 2---修改build  
    public void modfileBuild() {  
        StringBuffer stringBuffer = new StringBuffer();  
        try {  
            File file = new File("build.xml");  
            if (file.isFile() && file.exists()) { // 判断文件是否存在  
                InputStreamReader read = new InputStreamReader(  
                        new FileInputStream(file));  
                BufferedReader bufferedReader = new BufferedReader(read);  
                String lineTxt = null;  
                while ((lineTxt = bufferedReader.readLine()) != null) {  
                    if (lineTxt.matches(".*help.*")) {  
                        lineTxt = lineTxt.replaceAll("help", "build");  
                    }  
                    stringBuffer = stringBuffer.append(lineTxt + "\t\n");  
                }  
                read.close();  
            } else {  
                System.out.println("File could not be found!");  
            }  
        } catch (Exception e) {  
            System.out.println("Read file error!");  
            e.printStackTrace();  
        }  
  
        System.out.println("-----------------------");  
  
        // 修改后写回去  
        writerText("build.xml", new String(stringBuffer));  
        System.out.println("--------build.xml is modified---------");  
    }  
  
    /** 
     * 写如内容到指定的文件中 
     * @param path 文件的路径 
     * @param content 写入文件的内容 
     */  
    public void writerText(String path, String content) {  
  
        File dirFile = new File(path);  
        if (!dirFile.exists()) {  
            dirFile.mkdir();  
        }  
        try {  
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(path));  
            bw1.write(content);  
            bw1.flush();  
            bw1.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 3---ant 执行build  
    public void buildWithAnt() {  
        if (System.getProperty("os.name").equals("Linux")) {  
            execCmd("ant");  
            return;  
        }  
        execCmd("cmd /c ant");  
    }  
  
    // 4---push jar  
    public void pushTestJar(String localPath) {  
        localPath = "\"" + localPath + "\"";  
        System.out.println("----jar Path： " + localPath);  
        String pushCmd = "adb push " + localPath + " /data/local/tmp/";  
        System.out.println("----" + pushCmd);  
        execCmd(pushCmd);  
    }  
  
    // 运行测试  
    public void runTest(String jarName, String testName) {  
        String runCmd = "adb shell uiautomator runtest ";  
        String testCmd = jarName + ".jar " + "--nohup -c " + testName;  
        System.out.println("----runTest:  " + runCmd + testCmd);  
        if (isDebug) {  
            execCmd(runCmd + testCmd + " -e debug true");  
        } else {  
            execCmd(runCmd + testCmd);  
        }  
  
    }  
  
    public String getWorkSpase() {  
        File directory = new File("");  
        String abPath = directory.getAbsolutePath();  
        return abPath;  
    }  
  
    /** 
     * 需求：执行cmd命令，且输出信息到控制台 
     * @param cmd 
     */  
    public void execCmd(String cmd) {  
        System.out.println("------execute command:  " + cmd);  
        try {  
            Process p = Runtime.getRuntime().exec(cmd);  
            InputStream input = p.getInputStream();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    input));  
            int code = 0;  
            while ((code = reader.read()) != -1) {  
                System.out.print((char) code);  
            }  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
}  
