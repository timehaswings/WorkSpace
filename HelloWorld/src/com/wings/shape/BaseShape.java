package com.wings.shape;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class BaseShape {
	
	protected int widthPixels,heightPixels;
	protected int length;
	protected Paint paint;
	
	public BaseShape(Context context) {
		widthPixels = context.getResources().getDisplayMetrics().widthPixels;
		heightPixels = context.getResources().getDisplayMetrics().heightPixels;
		length=widthPixels/24;
		paint=new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
	}
	
	protected Rect getLitteRext(int left, int top){
		return new Rect(left, top, left+length, top+length);
	}
	
	public int getLength(){
		return this.length;
	}
	
	public abstract void moveDown();
	
	public abstract void moveLeft();
	
	public abstract void moveRight();
}
