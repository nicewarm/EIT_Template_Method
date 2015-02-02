package com.example.EIT_Template_Method;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Parcel;
import android.util.Log;

/**
 * Created by sreay on 15/2/2.
 */
public class mp3PlayerBinder extends Binder {
	private MediaPlayer mPlayer = null;
	private Context ctx;

	public mp3PlayerBinder(Context cx) {
		ctx = cx;
	}

	@Override
	public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
			throws android.os.RemoteException {
		if (code == 1)
			this.play();
		else if (code == 2)
			this.stop();
		return true;
	}

	public void play() {
		if (mPlayer != null)
			return;
		mPlayer = MediaPlayer.create(ctx, 0);
		try {
			mPlayer.start();
		} catch (Exception e) {
			Log.e("StartPlay", "error: " + e.getMessage(), e);
		}
	}

	public void stop() {
		if (mPlayer != null) {
			mPlayer.stop();
			mPlayer.release();
			mPlayer = null;
		}
	}
}
