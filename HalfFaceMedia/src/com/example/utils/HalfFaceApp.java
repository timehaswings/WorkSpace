package com.example.utils;

import android.Manifest;
import android.app.Application;
import android.content.Context;

public class HalfFaceApp extends Application{
	
	public static String[] permissions=new String[]{
			Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
	};
	
	public static Context getContext(){
		return getContext();
	}
	
	
}
