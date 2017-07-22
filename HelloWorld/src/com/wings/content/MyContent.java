package com.wings.content;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;

public class MyContent extends ContentProvider{
	
	/**
	 * 实现此操作可在启动时初始化内容提供程序。 在应用程序启动时，应用程序主线程上的所有注册内容提供商都会调用此方法。
	 *  它不能执行冗长的操作，否则应用程序启动将被延迟。您应该延迟初始化（例如打开，升级和扫描数据库），直到使用内容提供者（通过查询，插入等））。
	 *   延迟初始化使应用程序启动快速，避免不必要的工作，如果提供者不需要，并停止数据库错误（如完整的磁盘）停止应用程序启动。
	 * */
	@Override
	public boolean onCreate() {
		return false;
	}
	
	/**
	 * 实现它来处理来自客户端的查询请求。 可以从多个线程调用此方法，如进程和线程中所述。
	 * */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

	
	
	/**
	 * 当您的组件运行时，设备配置更改时由系统调用。 请注意，与活动不同，配置更改时，其他组件不会重新启动：它们必须始终处理更改的结果，例如重新获取资源。
在调用此函数时，您的Resources对象将被更新，以返回与新配置匹配的资源值。
有关更多信息，请参阅处理运行时更改。 这种方法总是在应用程序主线程上调用，不能执行冗长的操作。
默认内容提供程序实现什么都不做。 覆盖此方法以采取适当的措施。 （内容提供商通常不关心像屏幕方向的事情，但可能想知道区域设置更改。）
	 * */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}

	@SuppressLint("NewApi")
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder,
			CancellationSignal cancellationSignal) {
		// TODO Auto-generated method stub
		return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
	}

	@SuppressLint("NewApi")
	@Override
	public Uri canonicalize(Uri url) {
		// TODO Auto-generated method stub
		return super.canonicalize(url);
	}

	@SuppressLint("NewApi")
	@Override
	public Uri uncanonicalize(Uri url) {
		// TODO Auto-generated method stub
		return super.uncanonicalize(url);
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		// TODO Auto-generated method stub
		return super.bulkInsert(uri, values);
	}

	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
		return super.openFile(uri, mode);
	}

	@SuppressLint("NewApi")
	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode, CancellationSignal signal) throws FileNotFoundException {
		return super.openFile(uri, mode, signal);
	}

	@Override
	public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return super.openAssetFile(uri, mode);
	}


	@Override
	public String[] getStreamTypes(Uri uri, String mimeTypeFilter) {
		return super.getStreamTypes(uri, mimeTypeFilter);
	}

	@Override
	public AssetFileDescriptor openTypedAssetFile(Uri uri, String mimeTypeFilter, Bundle opts)
			throws FileNotFoundException {
		return super.openTypedAssetFile(uri, mimeTypeFilter, opts);
	}

	@SuppressLint("NewApi")
	@Override
	public AssetFileDescriptor openTypedAssetFile(Uri uri, String mimeTypeFilter, Bundle opts,
			CancellationSignal signal) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return super.openTypedAssetFile(uri, mimeTypeFilter, opts, signal);
	}

	@Override
	public <T> ParcelFileDescriptor openPipeHelper(Uri uri, String mimeType, Bundle opts, T args,
			PipeDataWriter<T> func) throws FileNotFoundException {
		return super.openPipeHelper(uri, mimeType, opts, args, func);
	}

	@Override
	protected boolean isTemporary() {
		return super.isTemporary();
	}

	@Override
	public void attachInfo(Context context, ProviderInfo info) {
		super.attachInfo(context, info);
	}

	@Override
	public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
			throws OperationApplicationException {
		return super.applyBatch(operations);
	}

	@Override
	public Bundle call(String method, String arg, Bundle extras) {
		return super.call(method, arg, extras);
	}

	@Override
	public void shutdown() {
		super.shutdown();
	}
	
	/**
	 * 将提供者的状态打印到给定的流中。 如果运行“adb shell dumpsys activity provider <provider_component_name>”，则会调用此方法。
	 * */
	@SuppressLint("NewApi")
	@Override
	public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
		super.dump(fd, writer, args);
	}
	
	

}
