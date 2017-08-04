package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;

public class MediaDeal {
	
	private static MediaDeal deal;
	
	private MediaDeal(){
		
	}
	
	public static MediaDeal getInstance(){
		if(deal==null)
			deal=new MediaDeal();
		return deal;
	}
	
	/**
	* 获取视频文件截图
	* @param path 视频文件的路径
	* @return Bitmap 返回获取的Bitmap
	*/
	public Bitmap getVideoThumb(String path) {
		MediaMetadataRetriever media = new MediaMetadataRetriever();
		media.setDataSource(path);
		return media.getFrameAtTime();
	}
	
	/**
	* 获取视频文件缩略图 API>=8(2.2)
	*
	* @param path 视频文件的路径
	* @param kind 缩略图的分辨率：MINI_KIND、MICRO_KIND、FULL_SCREEN_KIND
	* @return Bitmap 返回获取的Bitmap
	*/
	public Bitmap getVideoThumb2(String path, int kind) {
		return ThumbnailUtils.createVideoThumbnail(path, kind);
	}
	
	public Bitmap getVideoThumb2(String path) {
		return getVideoThumb2(path, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
	}
	
	/**
	* Bitmap保存成File
	* @param bitmap input bitmap
	* @param name output file's name
	* @return String output file's path
	*/
	public String bitmap2File(Bitmap bitmap, String name) {
		File f = new File(HalfFaceUtil.getDataDir() + name + ".jpg");
		if (f.exists()) f.delete();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			return null;
		}
		return f.getAbsolutePath();
	}
	
	/**
	 * 获取时间字符串
	 * @return
	 */
	public String getTimeString(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy_MM_dd_HH_mm_ss",Locale.CHINA);
		return simpleDateFormat.format(new Date());
	}
}
