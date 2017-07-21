package com.wings.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class BoxRect {
	
	private int widthPixels,heightPixels;
	public int length;
	private Paint paint;
	
	public BoxRect(Context context){
		widthPixels = context.getResources().getDisplayMetrics().widthPixels;
		heightPixels = context.getResources().getDisplayMetrics().heightPixels;
		length=widthPixels/24;
		paint=new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
	}
	
	public Rect getLitteRext(int left, int top){
		return new Rect(left, top, left+length, top+length);
	}
	
	/**
	 * 长方形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void changFangxin(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left, top+length), paint);
		canvas.drawRect(getLitteRext(left, top+length*2), paint);
		canvas.drawRect(getLitteRext(left, top+length*3), paint);
	}
	
	/**
	 * 田字形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void tianZhixin(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left+length, top), paint);
		canvas.drawRect(getLitteRext(left, top+length), paint);
		canvas.drawRect(getLitteRext(left+length, top+length), paint);
	}
	
	/**
	 * 丁字形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void dingZhixin(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left+length, top), paint);
		canvas.drawRect(getLitteRext(left+length*2, top), paint);
		canvas.drawRect(getLitteRext(left+length, top+length), paint);
	}
	
	/**
	 * 7字形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void qiZhixin(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left+length, top), paint);
		canvas.drawRect(getLitteRext(left+length, top+length), paint);
		canvas.drawRect(getLitteRext(left+length, top+length*2), paint);
	}
	
	/**
	 * s字形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void sZhixin(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left+length, top), paint);
		canvas.drawRect(getLitteRext(left+length, top+length), paint);
		canvas.drawRect(getLitteRext(left+length*2, top+length), paint);
	}
	
}
