package com.wings.mynet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MediaCacheHelper {

	private static MediaCacheHelper helper;
	public static final  String IMAGE_URL="http://172.31.84.73/res/image/p10.jpg";
	public static final  String IMAGE3_URL="http://172.31.84.73/res/image/p1.jpg";
	public static final  String IMAGE2_URL="http://172.31.84.73/res/image/g5.jpg";
	public static final  String VIDEO_URL="http://172.31.84.73/res/video/v001.mp4";
	public static final int MAXSIZE=(int) Runtime.getRuntime().maxMemory()/8;
	public static final int COMPELET_OK=3;
	public static final int COMPELET_NO=-3;
	
	private MediaCacheHelper(){}
	
	/**
	 * 获取实例对象
	 * @return
	 */
	public static MediaCacheHelper  getInstance(){
		if(helper == null){
			synchronized(MediaCacheHelper.class){
				if(helper == null){
					helper=new MediaCacheHelper();
				}
			}
		}
		return helper;
	}
	
	
	/**
     * 将字符串进行MD5编码,只需要调用一下hashKeyForDisk()方法，并把图片的URL传入到这个方法中，就可以得到对应的key了
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
    	String cacheKey;
    	try {
    		final MessageDigest mDigest = MessageDigest.getInstance("MD5");
    		mDigest.update(key.getBytes());
    		cacheKey = bytesToHexString(mDigest.digest());
    	} catch (NoSuchAlgorithmException e) {
    		cacheKey = String.valueOf(key.hashCode());
    	}
    	return cacheKey;
    }
    
    /**
     * 将字符串进行MD5编码，辅助方法
     * @param bytes
     * @return
     */
    private String bytesToHexString(byte[] bytes) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < bytes.length; i++) {
    		String hex = Integer.toHexString(0xFF & bytes[i]);
    		if (hex.length() == 1) {
    			sb.append('0');
    		}
    		sb.append(hex);
    	}
    	return sb.toString();
    }
}
