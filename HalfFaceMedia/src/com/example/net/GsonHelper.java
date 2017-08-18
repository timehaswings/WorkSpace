package com.example.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.example.data.FreeInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class GsonHelper {
	
	private Gson gson;
	
	public GsonHelper(){
		this.gson=new Gson();
	}
	
	public List<FreeInfo> paserFile(File file){
		return fromJsonList(readFile(file),FreeInfo.class);
	}
	
	/**
	 * 解析Object
	 * @param content
	 */
	public void paserObject(String content){
		@SuppressWarnings("unchecked")
		HashMap<String,String> map=gson.fromJson(content, HashMap.class);
//		String key="";
//		String value="";
//		for (Map.Entry<String,String> entry : map.entrySet()) {  
//			key=entry.getKey();
//			value=entry.getValue();
//		}  
	}
	
	/**
	 * 解析Array
	 * @param content
	 */
	public List<FreeInfo> paserArray(File file){
		String content=readFile(file);
		List<FreeInfo> data = gson.fromJson(content, new MyToken<List<FreeInfo>>().getType());
		return data;
	}
	
	/**
	 * 解析Object
	 * @param file
	 */
	public void paserObject(File file){
		Gson gson=new Gson();
		try {
			HashMap map=gson.fromJson(new FileReader(file), HashMap.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	
	private class MyToken<T> extends TypeToken<T>{
        public MyToken() {
            super();
        }
    }
	
	public <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();  
        for(final JsonElement elem : array){  
            mList.add(gson.fromJson(elem, cls));  
        }  
        return mList;  
    }  
	
	public <T> ArrayList<T> fromJsonList(Reader reader, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(reader).getAsJsonArray();  
        for(final JsonElement elem : array){  
            mList.add(gson.fromJson(elem, cls));  
        }  
        return mList;  
    }  
}
