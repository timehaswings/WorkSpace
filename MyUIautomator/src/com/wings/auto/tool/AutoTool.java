package com.wings.auto.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 301002028
 *公共工具类
 */
public class AutoTool {
	
	private static AutoTool tool;
	private String log_path="";
	private String project_path;
	
	private AutoTool(){}
	
	public static AutoTool getInstance(){
		if(tool==null){
			tool=new AutoTool();
		}
		return tool;
	}
	
	/**
	 * log输出
	 * @param content
	 */
	public void outLog(String content,boolean append){
		project_path=System.getProperty("user.dir");
		String fileName=project_path.substring(project_path.lastIndexOf("\\")+1, project_path.length()-1);
		log_path="/data/local/tmp/"+fileName+"/log.txt";
		File filed=new File(log_path);
		if(!filed.exists()){
			filed.mkdirs();
		}
		FileWriter fWriter=null;
		try{
			fWriter=new FileWriter(filed,append);
			fWriter.write(content+"\n");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fWriter != null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 获取log
	 */
	public void inLog(){
		if(!log_path.equals("") && project_path!=null){
			String pc_log_path=project_path+"\\log";
			File file=new File(pc_log_path);
			if(!file.exists()){
				file.mkdirs();
			}
			executeCommamd("adb pull "+log_path+" "+pc_log_path);
			System.out.println("log已经成功导出，见：-->>"+pc_log_path);
		}
	}

	/**
	 * 获取log路径
	 * @return
	 */
	public String getLog_path() {
		return log_path;
	}

	/**
	 * 设置log路径
	 * @param log_path
	 */
	public void setLog_path(String log_path) {
		this.log_path = log_path;
	}
	
	/**
	 * 执行dos命令
	 * @param command
	 * @return
	 */
	public String executeCommamd(String command){
		String content="";
		 try {  
	            Process p = Runtime.getRuntime().exec(command);
	            InputStream input = p.getInputStream();  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));    
	            String line="";
	            while ((line = reader.readLine()) != null) {  
	            	content+=line;  
	            }  
	        } catch (Exception e) {
				e.printStackTrace();
			} 
		 return content;
	 }
}

