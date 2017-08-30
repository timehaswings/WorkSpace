package com.wings.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String TAG=DatabaseHelper.class.getSimpleName()+":";
	
	 //类没有实例化,是不能用作父类构造器的参数,必须声明为静态  
    public static final String TABLE_NAME = "MediaInfo"; //数据库名称  
    public static final int DB_VERSION = 1; //数据库版本  

	public DatabaseHelper(Context context) {
		//第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类  
        super(context, TABLE_NAME, null, DB_VERSION); 
	}

	// 当第一次创建数据库的时候，调用该方法  
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建表
		String creat="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (_id integer primary key autoincrement, vid INTEGER, vname varchar(20), vactor varchar(20),"
				+ "vdesc varchar(60),vdate varchar(20),vpath varchar(60),vstatus INTEGER)";
		db.execSQL(creat);
		Log.d("wings", TAG+"数据库创建成功");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//当版本更新，删除旧表
		 String drop="DROP TABLE IF EXISTS "+TABLE_NAME;
		 db.execSQL(drop);
		 //创建新表
		 onCreate(db);
		// 一般在实际项目的做法是在更新数据表结构时，还要考虑用户存放于数据库中的数据不丢失
		 Log.d("wings", TAG+"数据库更新成功");
	}
	
	// 每次打开数据库之后首先被执行
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		Log.d("wings", TAG+"打开数据库");
	}

}
