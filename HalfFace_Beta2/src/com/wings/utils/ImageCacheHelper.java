package com.wings.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImageCacheHelper {

	private static ImageCacheHelper helper;
	public static final int MAXSIZE=(int) Runtime.getRuntime().maxMemory()/8;
	public static final int COMPELET_OK=3;
	public static final int COMPELET_NO=-3;
	
	private ImageCacheHelper(){}
	
	/**
	 * 获取实例对象
	 * @return
	 */
	public static ImageCacheHelper  getInstance(){
		if(helper == null){
			synchronized(ImageCacheHelper.class){
				if(helper == null){
					helper=new ImageCacheHelper();
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
