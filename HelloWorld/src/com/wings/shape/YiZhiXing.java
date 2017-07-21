package com.wings.shape;

import android.content.Context;
import android.graphics.Canvas;

public class YiZhiXing extends BaseShape{

	public YiZhiXing(Context context) {
		super(context);
	}
	
	/**
	 * 长方形
	 * @param canvas
	 * @param left
	 * @param top
	 */
	public void createShape(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left, top+length), paint);
		canvas.drawRect(getLitteRext(left, top+length*2), paint);
		canvas.drawRect(getLitteRext(left, top+length*3), paint);
	}
	
	public void changeDirection(Canvas canvas,int left, int top){
		canvas.drawRect(getLitteRext(left, top), paint);
		canvas.drawRect(getLitteRext(left+length, top), paint);
		canvas.drawRect(getLitteRext(left+length*2, top), paint);
		canvas.drawRect(getLitteRext(left+length*3, top), paint);
	}

	@Override
	public void moveDown() {
	}

	@Override
	public void moveLeft() {
	}

	@Override
	public void moveRight() {
	}
	
	
}
