package com.example.EIT_Template_Method;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by sreay on 15/2/2.
 */
public class mp3Service extends Service {
	private IBinder mBinder = null;
	@Override public void onCreate() {
		mBinder = new mp3PlayerBinder(getApplicationContext());
	}
	@Override public IBinder onBind(Intent intent) { return mBinder; }
}