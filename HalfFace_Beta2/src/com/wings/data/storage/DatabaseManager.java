package com.wings.data.storage;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager {
	
	private static final String TAG=DatabaseManager.class.getSimpleName()+":";
	
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	
	//初始化
	public DatabaseManager(Context context){
		helper=new DatabaseHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,mFactory);
		db=helper.getWritableDatabase();
	}
	
	 /**
     * add info
     * @param persons
     */
    public void add(List<MediaInfo> infos){
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try
        {
            for (MediaInfo info : infos)
            {
            	if(queryOne(info.getVid()+""))
            		continue;
            	String add="INSERT INTO " + DatabaseHelper.TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ? ,?)";
                db.execSQL(add,new Object[] { info.getVid(),info.getVname(),info.getVactor(),info.getVdesc(),info.getVdate(),info.getVpath(),info.getVstatus() });
                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
                // 使用占位符有效区分了这种情况
            }
            db.setTransactionSuccessful(); // 设置事务成功完成
            Log.d("wings", TAG+"数据插入成功");
        }
        finally{
            db.endTransaction(); // 结束事务
        }
    }
    
    /**
     * update info
     * @param person
     */
    public void updateAge(MediaInfo infos)
    {
        ContentValues cv = new ContentValues();
        cv.put("vid", infos.getVid());
        cv.put("vname", infos.getVname());
        cv.put("vactor", infos.getVactor());
        cv.put("vdesc", infos.getVdesc());
        cv.put("vdate", infos.getVdate());
        cv.put("vpath", infos.getVpath());
        cv.put("vstatus", infos.getVstatus());
        //以vid唯一标识查找条件
        db.update(DatabaseHelper.TABLE_NAME, cv, "vid = ?",new String[] { infos.getVid()+"" });
        Log.d("wings", TAG+"数据更新成功");
    }
    
    /**
     * delete info
     * @param person
     */
    public void deleteOldPerson(MediaInfo info){
    	 //以vid唯一标识查找条件
        db.delete(DatabaseHelper.TABLE_NAME, "vname = ?",new String[] { info.getVid()+"" });
        Log.d("wings", TAG+"数据删除成功");
    }
    
    /**
     * query all info, return list
     * 
     * @return List<MediaInfo>
     */
    public List<MediaInfo> query()
    {
        ArrayList<MediaInfo> infos = new ArrayList<MediaInfo>();
        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        while (c.moveToNext())
        {
        	MediaInfo info = new MediaInfo();
        	info.setVid(c.getInt(c.getColumnIndex("vid")));
        	info.setVname(c.getString(c.getColumnIndex("vname")));
        	info.setVactor(c.getString(c.getColumnIndex("vactor")));
        	info.setVdesc(c.getString(c.getColumnIndex("vdesc")));
        	info.setVdate(c.getString(c.getColumnIndex("vdate")));
        	info.setVpath(c.getString(c.getColumnIndex("vpath")));
        	info.setVstatus(c.getInt(c.getColumnIndex("vstatus")));
            infos.add(info);
        }
        c.close();
        Log.d("wings", TAG+"数据查询成功");
        return infos;
    }
    
    /**
     * 查询数据是否存在
     * @param vname
     * @return
     */
    public boolean queryOne(String vid){
    	Cursor c=db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME+" where vid = ?", new String[]{ vid });
    	Log.d("wings", TAG+"查询一条数据成功");
    	return c.getCount() == 0 ? false:true;
    }
    
    /**
     * 查询一条数据
     * @param vid
     */
    public MediaInfo queryOneInfo(int vid){
    	Cursor c=db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME+" where vid = ?", new String[]{ vid+"" });
    	MediaInfo info = new MediaInfo();
    	while (c.moveToNext()){
        	info.setVid(c.getInt(c.getColumnIndex("vid")));
        	info.setVname(c.getString(c.getColumnIndex("vname")));
        	info.setVactor(c.getString(c.getColumnIndex("vactor")));
        	info.setVdesc(c.getString(c.getColumnIndex("vdesc")));
        	info.setVdate(c.getString(c.getColumnIndex("vdate")));
        	info.setVpath(c.getString(c.getColumnIndex("vpath")));
        	info.setVstatus(c.getInt(c.getColumnIndex("vstatus")));
        }
    	c.close();
    	return info;
    }
    
    //关闭数据库
    public void close(){
    	if(db != null)
    		db.close();
    }
}
