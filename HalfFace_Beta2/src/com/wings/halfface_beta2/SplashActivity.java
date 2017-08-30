package com.wings.halfface_beta2;


import com.wings.data.storage.PermissionUtil;
import com.wings.utils.BitmapDecodeUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ImageView imageView=(ImageView) findViewById(R.id.splash_image);
		Bitmap b=BitmapDecodeUtil.getInstance().decodeBitmap(this, R.raw.timg);
		imageView.setImageBitmap(b);
		
		//设置引导页面动画
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.uinit); 
		animation.setFillAfter(true);
		animation.setInterpolator(new AccelerateInterpolator(1f));
		imageView.startAnimation(animation);  
        animation.setAnimationListener(new AnimationListener() {  
            @Override  
            public void onAnimationStart(Animation arg0) {}   //在动画开始时使用  
               
            @Override  
            public void onAnimationRepeat(Animation arg0) {}  //在动画重复时使用  
  
            @Override  
            public void onAnimationEnd(Animation arg0) {  
            	Intent intent=new Intent();
    			intent.setClass(SplashActivity.this, MainActivity.class);
    			SplashActivity.this.startActivity(intent);
    			finish();
            }  
	    }); 
        
        //申请应用权限
        new PermissionUtil(this).requestPermission();
	}
	
	@Override
	public void onBackPressed() {
	    // super.onBackPressed();   不要调用父类的方法
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    startActivity(intent);
	}
	
	
	//应用权限申请回调
	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		if(grantResults.length == 0)
			return;
	    switch (requestCode) {
	        case PermissionUtil.READ_EXTERNAL_STORAGE_CODE: 
	            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
	                // 权限被用户同意，可以去放肆了。
	            } else {
	                Toast.makeText(this, "拒绝某些权限可能会影响应用正常运行！"+permissions[0], Toast.LENGTH_SHORT).show();
	            }
	            break;
	        case PermissionUtil.WRITE_EXTERNAL_STORAGE_CODE: 
	            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
	                // 权限被用户同意，可以去放肆了。
	            } else {
	                Toast.makeText(this, "拒绝某些权限可能会影响应用正常运行！"+permissions[0], Toast.LENGTH_SHORT).show();
	            }
	            break;
	        case PermissionUtil.INTERNET_CODE: 
	            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
	                // 权限被用户同意，可以去放肆了。
	            } else {
	                Toast.makeText(this, "拒绝某些权限可能会影响应用正常运行！"+permissions[0], Toast.LENGTH_SHORT).show();
	            }
	            break;
	        case PermissionUtil.SEND_SMS_CODE: 
	            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
	                // 权限被用户同意，可以去放肆了。
	            } else {
	                Toast.makeText(this, "拒绝某些权限可能会影响应用正常运行！"+permissions[0], Toast.LENGTH_SHORT).show();
	            }
	            break;
	    }
	}
}
