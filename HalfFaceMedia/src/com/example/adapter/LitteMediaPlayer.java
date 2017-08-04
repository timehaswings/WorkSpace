package com.example.adapter;


import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.TextureView;

public class LitteMediaPlayer extends TextureView implements TextureView.SurfaceTextureListener,View.OnClickListener{
	
	public static final String FILE_NAME = Environment.getExternalStorageDirectory()+"/Byebye/hello.mp4";
	private MediaPlayer mPlayer;

	public LitteMediaPlayer(Context context) {
		super(context);
		init();
	}
	
	public LitteMediaPlayer(Context context,AttributeSet attr) {
		super(context,attr);
		init();
	}
	
	private void init(){
		setSurfaceTextureListener(this);
		setOnClickListener(this);
		mPlayer=new MediaPlayer();
	}
	
	/**
	 * 初始化播放
	 */
	private void initPlay(){
		mPlayer.setSurface(new Surface(getSurfaceTexture()));
		mPlayer.setOnPreparedListener(preparedListener);
		mPlayer.setOnCompletionListener(completionListener);
		try {
			mPlayer.setDataSource(FILE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mPlayer.prepareAsync();//能否播放视频，此代码是关键
	}
	
	
	 /**
     * 停止播放
     */
    private void stopPlay(){
    	if (mPlayer != null) {
    		mPlayer.stop();
    		mPlayer.reset();
    		mPlayer.release();
    		mPlayer=null;
        }
    }
	
    /**
     * 暂停或播放
     */
    private void pauseOrPlay(){
    	if(mPlayer != null){
    		if(mPlayer.isPlaying())
    			mPlayer.pause();
    		else
    			mPlayer.start();
    	}
    }
    
	private MediaPlayer.OnPreparedListener preparedListener=new MediaPlayer.OnPreparedListener() {
		@Override
		public void onPrepared(MediaPlayer mp) {
			mPlayer.start();
		}
	};
	
	private MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer mp) {
			stopPlay();
		}
	};
	
	
	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		 initPlay();
	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
		stopPlay();
	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		stopPlay();
		return false;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {
		
	}

	@Override
	public void onClick(View v) {
		
	}
	
}
