package com.example.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.TextureView;


/**
 * Created by Xingliu on 2016/12/5.
 */
@SuppressLint("ClickableViewAccessibility")
public class LargeMediaPlayer extends TextureView implements TextureView.SurfaceTextureListener,View.OnTouchListener{
	
    private String fileName;
    private MediaPlayer mMediaPlayer;
    private Surface surface;
    private int skipTime=60,swipeLength=15;
    private float startX=0,startY=0;
    private Context context;
    private int light=80;//亮度
//    private int cProgress;//进度

    public LargeMediaPlayer(Context context,String fileName) {
        super(context);
        this.context=context;
        this.fileName=fileName;
        initView();
    }
    
    /**
     * 必须存在此构造函数才能在xml中使用此控件
     * @param context
     * @param attributeSet
     */
    public LargeMediaPlayer(Context context,AttributeSet attributeSet,String fileName){
    	super(context,attributeSet);
    	this.context=context;
    	this.fileName=fileName;
    	initView();
    }

    /**
     * 视图初始化
     */
    private void initView() {
    	setLight((Activity)context, light);
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
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
            mMediaPlayer.setDataSource(fileName);
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
    
	/* (non-Javadoc)
	 * 触摸事件
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX=event.getX();
			startY=event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			//快退
			if((event.getY()>startY-swipeLength  ||  event.getY()<startY+swipeLength)  && event.getX()<startX-swipeLength*2)
				setMideaProgress(mMediaPlayer.getCurrentPosition() - mMediaPlayer.getDuration()/skipTime);
			//快进
			if((event.getY()>startY-swipeLength  ||  event.getY()<startY+swipeLength)  && event.getX()>startX+swipeLength*2)
				setMideaProgress(mMediaPlayer.getCurrentPosition() + mMediaPlayer.getDuration()/skipTime);
			//音量
			if(event.getX() > getWidth()/2 && (event.getX()>startX-swipeLength || event.getX()<startX+swipeLength)){
				if(event.getY()<startY-swipeLength*2)
					setSound((Activity)context, 1);
				if(event.getY()>startY+swipeLength*2)
					setSound((Activity)context, -1);
			}
			//亮度
			if(event.getX() < getWidth()/2 && (event.getX()>startX-swipeLength || event.getX()<startX+swipeLength)){
				if(event.getY()<startY-swipeLength*2){
					if(light<255)
						light+=5;
					setLight((Activity)context,light);
				}
				if(event.getY()>startY+swipeLength*2){
					if(light>0)
						light-=5;
					setLight((Activity)context,light);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			//点击事件
			if(event.getX()==startX && event.getY()==startY){
				if(mMediaPlayer != null){
					if(mMediaPlayer.isPlaying())
						mMediaPlayer.pause();
					else
						mMediaPlayer.start();
				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	/**
	 * 设置进度
	 * @param milliseconds
	 */
	public void setMideaProgress(int milliseconds){
		if(mMediaPlayer != null){
			if(milliseconds>0 && milliseconds<mMediaPlayer.getDuration()){
				mMediaPlayer.seekTo(milliseconds);
				if(!mMediaPlayer.isPlaying())
					mMediaPlayer.start();
			}
		}
	}
	
	/**
	 * 设置亮度
	 * @param context
	 * @param brightness
	 */
	private void setLight(Activity context, int brightness) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        context.getWindow().setAttributes(lp);
	}
	
	/**
	 * 设置声音
	 * @param context
	 * @param soundness
	 */
	private void setSound(Activity context, int soundness){
		AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		if(soundness==1)
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
		if(soundness==-1)
			audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
	}
	
	public boolean getIsPlaying(){
		return mMediaPlayer==null ? false:mMediaPlayer.isPlaying();
	}
	
	public int getProgress(){
		return mMediaPlayer==null ? -1:mMediaPlayer.getCurrentPosition();
	}
	
	public void setProgress(int cProgress){
		setMideaProgress(cProgress);
	}
	
	public int getMediaTotalProgress(){
		return mMediaPlayer==null ? 0:mMediaPlayer.getDuration();
	}
}
