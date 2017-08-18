package com.example.halffacemedia;

import com.example.utils.BitmapDecodeUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ImageView imageView=(ImageView) findViewById(R.id.splash_image);
		Bitmap b=BitmapDecodeUtil.getInstance().decodeBitmap(this, R.raw.timg);
		imageView.setImageBitmap(b);
		
		
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
	}
	
	@Override
	public void onBackPressed() {
	    // super.onBackPressed();   不要调用父类的方法
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    startActivity(intent);
	}
}
