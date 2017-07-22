package com.wings.helloworld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class MyApplication extends Application{

	
	/**
	 * 在应用程序启动之前，在创建任何活动，服务或接收者对象（不包括内容提供者）之前调用。 
	 * 实现应尽可能快（例如使用状态的惰性初始化），因为在此函数中花费的时间直接影响在进程
	 * 中启动第一个活动，服务或接收器的性能。 如果您重写此方法，请确保调用super.onCreate（）。
	 * */
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	/**
	 * 此方法用于仿真过程环境。 它将永远不会在一个生产的Android设备上被呼叫，
	 * 通过简单的杀死它们就可以删除进程; 在执行此操作时，不会执行用户代码（包括此回调）
	 * */
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	/**
	 * 当您的组件运行时，设备配置更改时由系统调用。 请注意，与活动不同，配置更改时，其他组件不会重新启动：
	 * 它们必须始终处理更改的结果，例如重新获取资源。
	 * 在调用此函数时，您的Resources对象将被更新，以返回与新配置匹配的资源值
	 * */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	
	/**
	 * 当整个系统的内存不足时，调用这个方法，并且主动运行的进程应该调整其内存使用。 虽然没有定义这个要点，
	 * 但通常当所有后台进程都被杀死时，会发生这种情况。 也就是说，在达到杀死进程托管服务和前台UI之前，我们希望避免杀死。
		您应该实现此方法来释放您可能持有的任何高速缓存或其他不必要的资源。 从该方法返回后，系统将为您执行垃圾收集
	 * */
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	/**
	 * 当操作系统确定这是一个进程从其进程中调整不需要的内存的好时机时调用。 这将发生在例如在后台进行，
	 * 并且没有足够的内存来保持尽可能多的后台进程按需运行。 您不应该与级别的精确值进行比较，
	 * 因为可能添加了新的中间值 - 如果值大于或等于您感兴趣的级别，则通常需要进行比较。
	 * 简而言之：进程被杀死后重启，可以在此方法中保存一些值
	 * */
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}
	
	/**
	 * 设置此ContextWrapper的基本上下文。 然后将所有呼叫委派给基本上下文。 如果已经设置了基本上下文，则抛出IllegalStateException
	 * */
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public Context getBaseContext() {
		return super.getBaseContext();
	}
	
	/**
	 * 返回应用程序包的AssetManager实例。注意：此方法的实现应返回与getResources（）返回的Resources实例一致的AssetManager实例。 
	 * 例如，他们应该共享相同的配置对象。
	 * */
	@Override
	public AssetManager getAssets() {
		return super.getAssets();
	}

	@Override
	public Resources getResources() {
		return super.getResources();
	}

	@Override
	public PackageManager getPackageManager() {
		return super.getPackageManager();
	}

	@Override
	public ContentResolver getContentResolver() {
		return super.getContentResolver();
	}

	@Override
	public Looper getMainLooper() {
		return super.getMainLooper();
	}

	@Override
	public Context getApplicationContext() {
		return super.getApplicationContext();
	}
	
	/**
	 * 设置这个上下文的基础主题。 请注意，在上下文中实例化任何视图
	 * （例如在调用android.app.Activity.setContentView或android.view.LayoutInflater.inflate之前）之前，应该调用它。
	 * */
	@Override
	public void setTheme(int resid) {
		super.setTheme(resid);
	}

	@Override
	public Theme getTheme() {
		return super.getTheme();
	}

	@Override
	public ClassLoader getClassLoader() {
		return super.getClassLoader();
	}

	@Override
	public String getPackageName() {
		return super.getPackageName();
	}

	@Override
	public ApplicationInfo getApplicationInfo() {
		return super.getApplicationInfo();
	}

	@Override
	public String getPackageResourcePath() {
		return super.getPackageResourcePath();
	}

	@Override
	public String getPackageCodePath() {
		return super.getPackageCodePath();
	}

	@Override
	public SharedPreferences getSharedPreferences(String name, int mode) {
		return super.getSharedPreferences(name, mode);
	}

	@Override
	public boolean moveSharedPreferencesFrom(Context sourceContext, String name) {
		return super.moveSharedPreferencesFrom(sourceContext, name);
	}

	@Override
	public boolean deleteSharedPreferences(String name) {
		return super.deleteSharedPreferences(name);
	}
	
	/**
	 * 打开与该Context的应用程序包相关联的私有文件进行阅读。
	 * */
	@Override
	public FileInputStream openFileInput(String name) throws FileNotFoundException {
		return super.openFileInput(name);
	}

	@Override
	public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
		
		return super.openFileOutput(name, mode);
	}

	@Override
	public boolean deleteFile(String name) {
		return super.deleteFile(name);
	}

	@Override
	public File getFileStreamPath(String name) {
		return super.getFileStreamPath(name);
	}

	@Override
	public String[] fileList() {
		return super.fileList();
	}

	@Override
	public File getDataDir() {
		return super.getDataDir();
	}

	@Override
	public File getFilesDir() {
		return super.getFilesDir();
	}


	@Override
	public File getExternalFilesDir(String type) {
		return super.getExternalFilesDir(type);
	}


	@Override
	public File getObbDir() {
		return super.getObbDir();
	}


	@Override
	public File getCacheDir() {
		return super.getCacheDir();
	}


	@Override
	public File getExternalCacheDir() {
		return super.getExternalCacheDir();
	}

	@Override
	public File getDir(String name, int mode) {
		return super.getDir(name, mode);
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {
		return super.openOrCreateDatabase(name, mode, factory);
	}

	@Override
	public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory,
			DatabaseErrorHandler errorHandler) {
		return super.openOrCreateDatabase(name, mode, factory, errorHandler);
	}

	@Override
	public boolean moveDatabaseFrom(Context sourceContext, String name) {
		return super.moveDatabaseFrom(sourceContext, name);
	}

	@Override
	public boolean deleteDatabase(String name) {
		return super.deleteDatabase(name);
	}

	@Override
	public File getDatabasePath(String name) {
		return super.getDatabasePath(name);
	}

	@Override
	public String[] databaseList() {
		return super.databaseList();
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
	}

	@Override
	public void startActivities(Intent[] intents) {
		super.startActivities(intents);
	}


	@Override
	public void startIntentSender(IntentSender intent, Intent fillInIntent, int flagsMask, int flagsValues,
			int extraFlags) throws SendIntentException {
		super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
	}

	@Override
	public void sendBroadcast(Intent intent) {
		super.sendBroadcast(intent);
	}

	@Override
	public void sendBroadcast(Intent intent, String receiverPermission) {
		super.sendBroadcast(intent, receiverPermission);
	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
		super.sendOrderedBroadcast(intent, receiverPermission);
	}

	@Override
	public void sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver,
			Handler scheduler, int initialCode, String initialData, Bundle initialExtras) {
		super.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData,
				initialExtras);
	}

	@Override
	public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
		return super.registerReceiver(receiver, filter);
	}

	@Override
	public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission,
			Handler scheduler) {
		return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
	}

	@Override
	public void unregisterReceiver(BroadcastReceiver receiver) {
		super.unregisterReceiver(receiver);
	}

	@Override
	public ComponentName startService(Intent service) {
		return super.startService(service);
	}

	@Override
	public boolean stopService(Intent name) {
		return super.stopService(name);
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return super.bindService(service, conn, flags);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		super.unbindService(conn);
	}

	@Override
	public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
		return super.startInstrumentation(className, profileFile, arguments);
	}

	@Override
	public Object getSystemService(String name) {
		return super.getSystemService(name);
	}

	@Override
	public int checkPermission(String permission, int pid, int uid) {
		return super.checkPermission(permission, pid, uid);
	}
	
	/**
	 * 确定在系统中运行的特定进程和用户ID是否允许给定的权限。
	 * */
	@Override
	public int checkCallingPermission(String permission) {
		return super.checkCallingPermission(permission);
	}

	@Override
	public int checkCallingOrSelfPermission(String permission) {
		return super.checkCallingOrSelfPermission(permission);
	}

	@Override
	public void enforcePermission(String permission, int pid, int uid, String message) {
		super.enforcePermission(permission, pid, uid, message);
	}

	@Override
	public void enforceCallingPermission(String permission, String message) {
		super.enforceCallingPermission(permission, message);
	}

	@Override
	public void enforceCallingOrSelfPermission(String permission, String message) {
		super.enforceCallingOrSelfPermission(permission, message);
	}

	@Override
	public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
		super.grantUriPermission(toPackage, uri, modeFlags);
	}

	@Override
	public void revokeUriPermission(Uri uri, int modeFlags) {
		super.revokeUriPermission(uri, modeFlags);
	}

	@Override
	public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
		return super.checkUriPermission(uri, pid, uid, modeFlags);
	}

	@Override
	public int checkCallingUriPermission(Uri uri, int modeFlags) {
		return super.checkCallingUriPermission(uri, modeFlags);
	}

	@Override
	public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
		return super.checkCallingOrSelfUriPermission(uri, modeFlags);
	}

	@Override
	public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid,
			int modeFlags) {
		return super.checkUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags);
	}

	@Override
	public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
		super.enforceUriPermission(uri, pid, uid, modeFlags, message);
	}

	@Override
	public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
		super.enforceCallingUriPermission(uri, modeFlags, message);
	}

	@Override
	public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
		super.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
	}

	@Override
	public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid,
			int modeFlags, String message) {
		super.enforceUriPermission(uri, readPermission, writePermission, pid, uid, modeFlags, message);
	}

	@Override
	public Context createPackageContext(String packageName, int flags) throws NameNotFoundException {
		return super.createPackageContext(packageName, flags);
	}

	@Override
	public boolean isRestricted() {
		return super.isRestricted();
	}

	@Override
	public Context createDeviceProtectedStorageContext() {
		return super.createDeviceProtectedStorageContext();
	}

	@Override
	public boolean isDeviceProtectedStorage() {
		return super.isDeviceProtectedStorage();
	}
	
}
