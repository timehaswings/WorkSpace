package com.wings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkStateReceiver extends BroadcastReceiver {
	
	private static final String TAG = "NetworkStateReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!isNetworkAvailable(context)) {
			Toast.makeText(context, "network disconnected!", 0).show();
		}
	}

	/**
	 * 网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

}
