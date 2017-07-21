package com.wings.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class BoxView extends View{
	
	private Paint paint;
	private int widthPixels,heightPixels;
	private BoxRect boxRect;
	private int rectLength;
	private int i=0,r=0,rM=0;
	
	private Map<Integer, Point> map;
	private int mapID=0;
	
	@SuppressLint("UseSparseArrays")
	public BoxView(Context context) {
		super(context);
		paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		boxRect=new BoxRect(context);
		rectLength=boxRect.length;
		map=new HashMap<Integer, Point>();
		widthPixels = getResources().getDisplayMetrics().widthPixels;
		heightPixels = getResources().getDisplayMetrics().heightPixels;
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(new Rect(0,0,widthPixels,heightPixels), paint);
		nextShape(canvas, rectLength, i);
		i+=2;
		int shapeHeight=isBottom();
		getShape(canvas,shapeHeight);
		invalidate();
		
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		MeasureSpec.getMode(MeasureSpec.EXACTLY);
		setMeasuredDimension(widthPixels*3/4, heightPixels*3/4);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		  
	}
	
	public void getShape(Canvas canvas,int shapeHeight){
		int size=map.size();
		if(size!=0){
			for(int j=0;j<size;j++){
				Point point=map.get(j);
				switch (point.x) {
				case 0:
					boxRect.sZhixin(canvas, rectLength*point.y, shapeHeight);
					break;
				case 1:
					boxRect.dingZhixin(canvas, rectLength*point.y, shapeHeight);
					break;
				case 2:
					boxRect.qiZhixin(canvas, rectLength*point.y, shapeHeight);
					break;
				case 3:
					boxRect.changFangxin(canvas, rectLength*point.y, shapeHeight);
					break;
				case 4:
					boxRect.tianZhixin(canvas, rectLength*point.y, shapeHeight);
					break;
				default:
					break;
				}
			}
		}
	}
	
	/**
	 * 判断是否触底
	 */
	public int isBottom(){
		int bottom=getBottom();
		int oldHeight=bottom-rectLength*4;
		int shapeHeight=0;
		switch (r) {
		case 0:
			shapeHeight=rectLength*3;
			break;
		case 1:
			shapeHeight=rectLength*2;
			break;
		case 2:
			shapeHeight=rectLength*3;
			break;
		case 3:
			shapeHeight=rectLength*4;
			break;
		case 4:
			shapeHeight=rectLength*2;
			break;
		default:
			break;
		}
		if(i+shapeHeight>=bottom){
			oldHeight=i;
			i=0;
		}
		return oldHeight;
	}
	
	/**
	 * 产生下一个图形
	 * @param canvas
	 * @param width
	 * @param height
	 */
	public void nextShape(Canvas canvas,int width,int height){
		if(height==0){
			Random random=new Random();
			r=random.nextInt(5);
			rM=random.nextInt(6)*3;
			map.put(mapID++, new Point(r, rM));
		}
		switch (r) {
		case 0:
			boxRect.sZhixin(canvas, width*rM, height);
			break;
		case 1:
			boxRect.dingZhixin(canvas, width*rM, height);
			break;
		case 2:
			boxRect.qiZhixin(canvas, width*rM, height);
			break;
		case 3:
			boxRect.changFangxin(canvas, width*rM, height);
			break;
		case 4:
			boxRect.tianZhixin(canvas, width*rM, height);
			break;
		default:
			break;
		}
	}
	
	
	
}
