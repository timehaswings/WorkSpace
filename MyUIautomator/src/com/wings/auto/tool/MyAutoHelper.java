package com.wings.auto.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

import android.os.RemoteException;

public class MyAutoHelper {

	private static MyAutoHelper auto;
	private static UiDevice mUiDevice=UiDevice.getInstance();
	private static int width=0;
	private static int height=0;
	
	private MyAutoHelper(){
		
	}
	
	/**
	 * 获取MyAutoHelper实例
	 * @return MyAutoHelper实例
	 */
	public static MyAutoHelper getInstance(){
		if(auto==null){
			auto=new MyAutoHelper();
		}
		if(width==0){
			width=mUiDevice.getDisplayWidth();
			height=mUiDevice.getDisplayHeight();
		}
		return auto;
	}
	
	/**
	 * 获取宽
	 * @return
	 */
	public int getWidth(){
		return MyAutoHelper.width;
	}
	
	/**
	 * 获取高
	 * @return
	 */
	public int getHeight(){
		return MyAutoHelper.height;
	}
	
	/**
	 * 点击文本
	 * @param text 文本内容
	 * @return 
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickText(String text) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().text(text)).click();
	}
	
	/**
	 * 点击文本（包含）
	 * @param text 文本包含内容
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickTextCotains(String text) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().textContains(text)).click();
	}
	
	/**
	 * 点击描述文本
	 * @param desc 描述文本内容
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickDesc(String desc) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().description(desc)).click();
	}
	
	/**
	 * 点击描述文本（包含）
	 * @param desc 描述文本包含内容
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickDescContains(String desc) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().descriptionContains(desc)).click();
	}
	
	/**
	 * 点击资源id
	 * @param id 资源id
	 * @param instance 实例序号
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickResId(String id,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).click();
	}
	
	/**
	 * 点击class
	 * @param className
	 * @param instance
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean clickClass(String className,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().className(className).instance(instance)).click();
	}
	
	/**
	 * 点击坐标
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean click(int x,int y){
		return mUiDevice.click(x, y);
	}
	
	/**
	 * 搜索text
	 * @param text
	 * @return
	 */
	public boolean searchText(String text){
		return new UiObject(new UiSelector().text(text)).exists();
	}
	
	/**
	 * 搜索text(包含)
	 * @param text
	 * @return
	 */
	public boolean searchTextContains(String text){
		return new UiObject(new UiSelector().textContains(text)).exists();
	}
	
	/**
	 * 搜索描述
	 * @param desc
	 * @return
	 */
	public boolean searchDesc(String desc){
		return new UiObject(new UiSelector().description(desc)).exists();
	}
	
	/**
	 * 搜索描述(包含)
	 * @param desc
	 * @return
	 */
	public boolean searchDescContains(String desc){
		return new UiObject(new UiSelector().descriptionContains(desc)).exists();
	}
	
	/**
	 * 搜索资源id
	 * @param id
	 * @param instance
	 * @return
	 */
	public boolean searchResId(String id,int instance){
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).exists();
	}
	
	/**
	 * 搜索class
	 * @param className
	 * @param instance
	 * @return
	 */
	public boolean searchClass(String className,int instance){
		return new UiObject(new UiSelector().className(className).instance(instance)).exists();
	}
	
	/**
	 * 长按text
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickText(String text) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().text(text)).longClick();
	}
	
	/**
	 * 长按text(包含)
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickTextContains(String text) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().textContains(text)).longClick();
	}
	
	/**
	 * 长按desc
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickDesc(String desc) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().description(desc)).longClick();
	}
	
	/**
	 * 长按desc(包含)
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickDescContains(String desc) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().descriptionContains(desc)).longClick();
	}
	
	/**
	 * 长按资源id
	 * @param id
	 * @param instance
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickResId(String id,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).longClick();
	}
	
	/**
	 * 长按class
	 * @param className
	 * @param instance
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean longClickClass(String className,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().className(className).instance(instance)).longClick();
	}
	
	/**
	 * 搜索listview中的文本
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public boolean searchListText(String text) throws UiObjectNotFoundException, InterruptedException{
		if(searchText(text)){
			return true;
		}else if(new UiScrollable(new UiSelector().scrollable(true)).exists()){
			return new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(text);
		}else{
			int i=0;
			while(i++<=5){
				swipeDown(10);
				sleep(500);
			}
			while(i-->=0){
				if(searchText(text)){
					return true;
				}else{
					swipeUp(30);
					sleep(1000);
				}
			}
		}
		return false;
	}
	
	/**
	 * 点击listview中的文本
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public boolean clickListText(String text) throws UiObjectNotFoundException, InterruptedException{
		if(new UiScrollable(new UiSelector().scrollable(true)).exists()){
			new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(text);
			clickText(text);
		}else{
			int i=0;
			while(i++<=6){
				swipeDown(10);
				sleep(500);
			}
			while(i-->=0){
				if(searchText(text)){
					return clickText(text);
				}else{
					swipeUp(30);
					sleep(1000);
				}
			}
		}
		return false;
	}
	
	/**
	 * 搜索listview中的文本(包含)
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public boolean searchListTextContains(String text) throws UiObjectNotFoundException, InterruptedException{
		if(searchText(text)){
			return true;
		}else if(new UiScrollable(new UiSelector().scrollable(true)).exists()){
			return new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(text));
		}else{
			int i=0;
			while(i++<=6){
				swipeDown(10);
				sleep(500);
			}
			while(i-->=0){
				if(searchTextContains(text)){
					return true;
				}else{
					swipeUp(30);
					sleep(1000);
				}
			}
		}
		return false;
	}
	
	/**
	 * 点击listview中的文本(包含)
	 * @param text
	 * @return
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public boolean clickListTextContains(String text) throws UiObjectNotFoundException, InterruptedException{
		if(new UiScrollable(new UiSelector().scrollable(true)).exists()){
			new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(text));
			clickTextCotains(text);
		}else{
			int i=0;
			while(i++<=6){
				swipeDown(10);
				sleep(500);
			}
			while(i-->=0){
				if(searchText(text)){
					return clickTextCotains(text);
				}else{
					swipeUp(30);
					sleep(1000);
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据文本获取text
	 * @param id
	 * @param instance
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public String getTextById(String id,int instance) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).getText();
	}
	
	/**
	 * 依照参数传入顺序点击文本
	 * @param texts
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public void clickTextByOrder(String...texts) throws UiObjectNotFoundException, InterruptedException{
		if(texts.length != 0){
			for(String text:texts){
				clickText(text);
				sleep(2000);
			}
		}
	}
	
	/**
	 * 依照参数传入顺序点击描述
	 * @param descs
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 */
	public void clickDescByOrder(String...descs)throws UiObjectNotFoundException, InterruptedException{
		if(descs.length != 0){
			for(String desc:descs){
				clickText(desc);
				sleep(2000);
			}
		}
	}
	
	/**
	 * 输入内容
	 * @param text
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInText(String text,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().text(text)).setText(content);
	}
	
	/**
	 * 输入内容(包含)
	 * @param text
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInTextContains(String text,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().textContains(text)).setText(content);
	}
	

	/**
	 * 输入内容(根据描述包含)
	 * @param text
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInDescContains(String text,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().descriptionContains(text)).setText(content);
	}
	
	/**
	 * 输入内容(根据描述)
	 * @param text
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInDesc(String text,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().description(text)).setText(content);
	}
	
	/**
	 * 输入内容(根据资源id)
	 * @param id
	 * @param instance
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInResId(String id,int instance,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().resourceId(id).instance(instance)).setText(content);
	}
	
	/**
	 * 输入内容(根据class)
	 * @param id
	 * @param instance
	 * @param content
	 * @return
	 * @throws UiObjectNotFoundException
	 */
	public boolean enterInClass(String className,int instance,String content) throws UiObjectNotFoundException{
		return new UiObject(new UiSelector().className(className).instance(instance)).setText(content);
	}
	
	/**
	 * 长按任意控件
	 * @param us
	 * @throws UiObjectNotFoundException
	 */
	public void longClickSelector(UiSelector us) throws UiObjectNotFoundException{
		UiObject uiObject=new UiObject(us);
		int x=uiObject.getBounds().centerX();
		int y=uiObject.getBounds().centerY();
		mUiDevice.swipe(x, y, x, y, 150);
	}
	
	/**
	 * 下划
	 * @param steps 划动速度
	 * @return
	 */
	public boolean swipeDown(int steps){
		return mUiDevice.swipe(width/2, height/5, width/2, height*4/5, steps);
	}
	
	/**
	 * 上划
	 * @param steps 划动速度
	 * @return
	 */
	public boolean swipeUp(int steps){
		return mUiDevice.swipe(width/2, height*4/5, width/2, height/5, steps);
	}
	
	/**
	 * 左划
	 * @param steps 划动速度
	 * @return
	 */
	public boolean swipeLeft(int steps){
		return mUiDevice.swipe(width*4/5, height/2, width/5, height/2, steps);
	}
	
	/**
	 * 右划
	 * @param steps 划动速度
	 * @return
	 */
	public boolean swipeRight(int steps){
		return mUiDevice.swipe(width/5, height/2, width*4/5, height/2, steps);
	}
	
	/**
	 * 停顿
	 * @param time 停顿时间毫秒数
	 * @throws InterruptedException
	 */
	public void sleep(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按返回键
	 * @return
	 */
	public boolean pressBack(){
		return mUiDevice.pressBack();
	}
	
	/**
	 * 点击home
	 * @return
	 */
	public boolean pressHome(){
		return mUiDevice.pressHome();
	}
	
	/**
	 * 点击按键
	 * @param keyCode
	 * @return
	 */
	public boolean pressKey(int keyCode){
		return mUiDevice.pressKeyCode(keyCode);
	}
	
	/**
	 * 休眠
	 * @throws RemoteException
	 */
	public void sleep() throws RemoteException{
		mUiDevice.sleep();
	}
	
	/**
	 * 唤醒
	 * @throws RemoteException
	 */
	public void wakeUp() throws RemoteException{
		mUiDevice.wakeUp();
	}
	
	/**
	 * 注册并运行监听
	 */
	public void runAndRegisterWatcher(UiWatcher uiWatcher){
		mUiDevice.registerWatcher("mywatcher", uiWatcher);
		mUiDevice.runWatchers();
	}
	
	/**
	 * 停止监听器
	 */
	public void stopWatcher(){
		mUiDevice.removeWatcher("mywatcher");
	}
	
	/**
	 * 重置监听器
	 */
	public void resetWatcher(){
		mUiDevice.resetWatcherTriggers();
	}
	
	/**
	 * 截屏/storage/sdcard0/MyAuto/Images
	 */
	public void takeScreenshot(){
		File filed=new File("/storage/sdcard0/MyAuto/Images");
		if(!filed.exists()){
			filed.mkdirs();
		}
		SimpleDateFormat sdf=new SimpleDateFormat("MM_dd_HH_mm_ss");
		String time=sdf.format(new Date());
		File filep=new File("/storage/sdcard0/MyAuto/Images/"+time+".png");
		mUiDevice.takeScreenshot(filep);
	}
	
	/**
	 * 写入内容到/storage/sdcard0/MyAuto/Files/out.txt
	 * @param content
	 * @param append
	 */
	public void writeFile(String fileName,String content,boolean append){
		File filed=new File("/storage/sdcard0/MyAuto/Files");
		if(!filed.exists()){
			filed.mkdirs();
		}
		File filef=new File("/storage/sdcard0/MyAuto/Files/"+fileName+".txt");
		FileWriter fWriter=null;
		try{
			if(!filef.exists()){
				filef.createNewFile();
			}
			fWriter=new FileWriter(filef,append);
			fWriter.write(content+"\n");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(fWriter != null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 执行dos命令
	 * @param command
	 * @return
	 */
	public String exec(String command){
		 return AutoTool.getInstance().executeCommamd(command);
	 }
}

