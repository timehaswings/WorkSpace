package com.wings.data.storage;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

public class PermissionUtil {
	
	public static final int READ_EXTERNAL_STORAGE_CODE=101;
	public static final int WRITE_EXTERNAL_STORAGE_CODE=102;
	public static final int INTERNET_CODE=103;
	public static final int SEND_SMS_CODE=104;
	public static final int READ_PHONE_CODE=105;
	
	public static final String[] PERMISSIONS=new String[]{
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.INTERNET,
			Manifest.permission.SEND_SMS,
			Manifest.permission.READ_PHONE_STATE
	};
	
	private Activity activity;
	
	public PermissionUtil(Activity activity){
		this.activity=activity;
	}
	
	@SuppressLint("NewApi")
	public void requestPermission(){
		//权限一：
		if (activity.checkSelfPermission(PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) {
		    // 没有权限。
		    if (activity.shouldShowRequestPermissionRationale(PERMISSIONS[0])) {
		            // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
		    } else {
		        // 申请授权。
		    	activity.requestPermissions(new String[]{PERMISSIONS[0]}, READ_EXTERNAL_STORAGE_CODE);
		    }
		}
		//权限二：
		if (activity.checkSelfPermission(PERMISSIONS[1]) != PackageManager.PERMISSION_GRANTED) {
		    // 没有权限。
		    if (activity.shouldShowRequestPermissionRationale(PERMISSIONS[1])) {
		            // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
		    } else {
		        // 申请授权。
		    	activity.requestPermissions(new String[]{PERMISSIONS[1]}, WRITE_EXTERNAL_STORAGE_CODE);
		    }
		}
		//权限三：
		if (activity.checkSelfPermission(PERMISSIONS[2]) != PackageManager.PERMISSION_GRANTED) {
		    // 没有权限。
		    if (activity.shouldShowRequestPermissionRationale(PERMISSIONS[2])) {
		            // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
		    } else {
		        // 申请授权。
		    	activity.requestPermissions(new String[]{PERMISSIONS[2]}, INTERNET_CODE);
		    }
		}
		//权限四：
		if (activity.checkSelfPermission(PERMISSIONS[3]) != PackageManager.PERMISSION_GRANTED) {
		    // 没有权限。
		    if (activity.shouldShowRequestPermissionRationale(PERMISSIONS[3])) {
		            // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
		    } else {
		        // 申请授权。
		    	activity.requestPermissions(new String[]{PERMISSIONS[3]}, SEND_SMS_CODE);
		    }
		}
		//权限四：
		if (activity.checkSelfPermission(PERMISSIONS[4]) != PackageManager.PERMISSION_GRANTED) {
		    // 没有权限。
		    if (activity.shouldShowRequestPermissionRationale(PERMISSIONS[4])) {
		            // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
		    } else {
		        // 申请授权。
		    	activity.requestPermissions(new String[]{PERMISSIONS[4]}, READ_PHONE_CODE);
		    }
		}
	}
	
	
	
	public static String[] PERMISSION = {Manifest.permission.READ_PHONE_STATE};

    @SuppressLint("NewApi")
	public boolean isLacksOfPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED;
        }
        return false;
    }
}
