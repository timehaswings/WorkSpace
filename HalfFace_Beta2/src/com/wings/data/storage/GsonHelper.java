package com.wings.data.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonHelper {
		
	private Gson gson;
	
	public GsonHelper(){
		this.gson=new Gson();
	}
	
	/**
	 * 解析字符串
	 * @param json
	 * @param cls
	 * @return
	 */
	public <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();  
        for(final JsonElement elem : array){  
            mList.add(gson.fromJson(elem, cls));  
        }  
        return mList;  
    }  
	
	/**
	 * 解析流
	 * @param reader
	 * @param cls
	 * @return
	 */
	public <T> ArrayList<T> fromJsonList(Reader reader, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(reader).getAsJsonArray();  
        for(final JsonElement elem : array){  
            mList.add(gson.fromJson(elem, cls));  
        }  
        return mList;  
    }  
	
	/**
	 * 解析文件
	 * @param file
	 * @param cls
	 * @return
	 */
	public <T> List<T> paserFile(File file,Class<T> cls){
		return fromJsonList(readFile(file),cls);
	}
	
	/**
	 * 读取文件
	 * @param file
	 * @return
	 */
	public String readFile(File file){
		BufferedReader buf=null;
		StringBuffer buffer=null;
		try {
			buffer=new StringBuffer();
			buf=new BufferedReader(new FileReader(file));
			String temp=null;
			while((temp=buf.readLine()) != null){
				buffer.append(temp);
			}
			buf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buf != null){
				try {
					buf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buffer.toString();
	}
}
