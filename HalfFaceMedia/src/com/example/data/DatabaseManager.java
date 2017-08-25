package com.example.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
	
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
    public void add(List<FreeInfo> infos){
        // 采用事务处理，确保数据完整性
        db.beginTransaction(); // 开始事务
        try
        {
            for (FreeInfo info : infos)
            {
            	String add="INSERT INTO " + DatabaseHelper.TABLE_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?)";
                db.execSQL(add,new Object[] { info.getVname(),info.getVactor(),info.getVdesc(),info.getVdate(),info.getVpath(),info.getVstatus() });
                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
                // 使用占位符有效区分了这种情况
            }
            db.setTransactionSuccessful(); // 设置事务成功完成
        }
        finally{
            db.endTransaction(); // 结束事务
        }
    }
    
    /**
     * update info
     * @param person
     */
    public void updateAge(FreeInfo infos)
    {
        ContentValues cv = new ContentValues();
        cv.put("vactor", infos.getVactor());
        cv.put("vdesc", infos.getVdesc());
        cv.put("vdate", infos.getVdate());
        cv.put("vpath", infos.getVpath());
        cv.put("vstatus", infos.getVstatus());
        //以vname唯一标识查找条件
        db.update(DatabaseHelper.TABLE_NAME, cv, "vname = ?",new String[] { infos.getVname() });
    }
    
    /**
     * delete info
     * @param person
     */
    public void deleteOldPerson(FreeInfo info){
    	 //以vname唯一标识查找条件
        db.delete(DatabaseHelper.TABLE_NAME, "vname = ?",new String[] { info.getVname() });
    }
    
    /**
     * query all info, return list
     * 
     * @return List<FreeInfo>
     */
    public List<FreeInfo> query()
    {
        ArrayList<FreeInfo> infos = new ArrayList<FreeInfo>();
        Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        while (c.moveToNext())
        {
        	FreeInfo info = new FreeInfo();
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
        return infos;
    }
    
    /**
     * 查询一条数据
     * @param vname
     * @return
     */
    public List<FreeInfo> queryOne(String vname){
    	ArrayList<FreeInfo> infos =null;
    	Cursor c=db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME+" where vname = ?", new String[]{ vname });
    	if(c.getCount() != 0){
    		infos=new ArrayList<FreeInfo>();
    		while (c.moveToNext())
            {
            	FreeInfo info = new FreeInfo();
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
    	}
    	return null;
    }
    
    //关闭数据库
    public void close(){
    	if(db != null)
    		db.close();
    }
}
