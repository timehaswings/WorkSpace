package com.example.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.TextureView;


/**
 * Created by Xingliu on 2016/12/5.
 */
@SuppressLint("ClickableViewAccessibility")
public class LitteMediaPlayer2 extends TextureView implements TextureView.SurfaceTextureListener,View.OnClickListener{
	
//    public static final String FILE_NAME = Environment.getExternalStorageDirectory()+"/Byebye/hello.mp4";
    public static final String FILE_NAME = "http://172.31.84.73/res/video/v001.mp4";

    private MediaPlayer mMediaPlayer;
    private Surface surface;
    private Context context;

    public LitteMediaPlayer2(Context context) {
        super(context);
        this.context=context;
        initView();
    }
    
    /**
     * 必须存在此构造函数才能在xml中使用此控件
     * @param context
     * @param attributeSet
     */
    public LitteMediaPlayer2(Context context,AttributeSet attributeSet){
    	super(context,attributeSet);
    	this.context=context;
    	initView();
    }

    /**
     * 视图初始化
     */
    private void initView() {
        setSurfaceTextureListener(this);
        setOnClickListener(this);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(preparedListener);
        mMediaPlayer.setOnCompletionListener(completionListener);
        mMediaPlayer.setOnErrorListener(errorListener);
    }
    
    /**
     * 停止播放
     */
    private void stopPlay(){
    	if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer=null;
        }
    }
    
    /**
     * 开始播放
     */
    private void startPlay(){
    	if(mMediaPlayer==null)
        	mMediaPlayer = new MediaPlayer();
    	surface = new Surface(getSurfaceTexture());
    	mMediaPlayer.setSurface(surface);
    	try {
//            mMediaPlayer.setDataSource(FILE_NAME);
    		mMediaPlayer
    		.setDataSource(context, Uri.parse(FILE_NAME));
            mMediaPlayer.prepareAsync();//能否播放视频，此代码是关键
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 播放器监听事件
     * */
    
    private MediaPlayer.OnPreparedListener preparedListener=new MediaPlayer.OnPreparedListener(){
		@Override
		public void onPrepared(MediaPlayer mp) {
			 mMediaPlayer.start();
//             mMediaPlayer.pause();
		}
    };
    
    private MediaPlayer.OnErrorListener errorListener=new MediaPlayer.OnErrorListener(){
		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			return false;
		}
    };
    
    private MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener(){
		@Override
		public void onCompletion(MediaPlayer mp) {
			stopPlay();
		}
    };
    
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
    	startPlay();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    	
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
    	stopPlay();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    	startPlay();
    }
	
	/**
	 * 设置进度
	 * @param milliseconds
	 */
	public int setMediaProgress(int milliseconds){
		if(mMediaPlayer != null){
			if(milliseconds>0 && milliseconds<mMediaPlayer.getDuration()){
				mMediaPlayer.seekTo(milliseconds);
				if(!mMediaPlayer.isPlaying())
					mMediaPlayer.start();
					return milliseconds;
			}
		}
		return -1;
	}

	@Override
	public void onClick(View v) {
		if(mMediaPlayer != null){
			if(mMediaPlayer.isPlaying()){
				mMediaPlayer.pause();
			}else{
				mMediaPlayer.start();
			}
		}
	}
	
	public boolean mediaIsPlaying(){
		return mMediaPlayer != null && mMediaPlayer.isPlaying();
	}
	
	public int getMediaProgress(){
		return mMediaPlayer == null ? 0 : mMediaPlayer.getCurrentPosition();
	}
	
	public int getMediaDuration(){
		return mMediaPlayer == null ? 0 : mMediaPlayer.getDuration();
	}
	
}
