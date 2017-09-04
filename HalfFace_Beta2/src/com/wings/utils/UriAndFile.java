package com.wings.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import android.net.Uri;
import io.vov.vitamio.utils.Log;

public class UriAndFile {
	
	private static final String TAG=UriAndFile.class.getSimpleName();
	
	/**
	 * @param uri
	 * @return file path or null
	 */
	public static String getFilePath(String uri){
		Log.d(TAG, "开始获取uri的路径");
		String path=null;
		try {
			path=new File(new URI(uri.toString())).getAbsolutePath();
			Log.d("wings", "获取uri的路径成功->"+path);
		} catch (URISyntaxException e) {
			path=null;
		}
		return path;  
	}
	
	/**
	 * @param path
	 * @return uri
	 */
	public static Uri getUri(String path){
		Log.d(TAG, "开始获取路径的URI");
		return Uri.fromFile(new File(path));
	}
	
}
