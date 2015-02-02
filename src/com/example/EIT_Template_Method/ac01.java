package com.example.EIT_Template_Method;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ac01 extends Activity implements View.OnClickListener {
	private final int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
	private final int FP = LinearLayout.LayoutParams.FILL_PARENT;
	private static ac01 appRef = null;
	private myButton btn, btn2, btn3;
	public TextView tv;
	private IBinder ib;

	public static ac01 getApp() {
		return appRef;
	}

	public void btEvent(String data) {
		setTitle(data);
	}

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		appRef = this;
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		btn = new myButton(this);
		btn.setId(101);
		btn.setText("play");
		btn.setOnClickListener(this);
		LinearLayout.LayoutParams param =
				new LinearLayout.LayoutParams(btn.get_width(), btn.get_height());
		param.topMargin = 10;
		layout.addView(btn, param);
		btn2 = new myButton(this);
		btn2.setId(102);
		btn2.setText("stop");
		btn2.setOnClickListener(this);
		layout.addView(btn2, param);
		btn3 = new myButton(this);
		btn3.setId(103);
		btn3.setText("exit");
		btn3.setOnClickListener(this);
		layout.addView(btn3, param);
		tv = new TextView(this);
		tv.setTextColor(Color.WHITE);
		tv.setText("Ready");
		LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(FP, WC);
		param2.topMargin = 10;
		layout.addView(tv, param2);
		setContentView(layout);
		bindService(new Intent(ac01.this, mp3Service.class), mConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder ibinder) {
			ib = ibinder;
		}

		public void onServiceDisconnected(ComponentName className) {
		}
	};

	public void onClick(View v) {
		switch (v.getId()) {
		case 101:
			tv.setText("Playing audio...");
			setTitle("MP3 Music");
			try {
				ib.transact(1, null, null, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case 102:
			tv.setText("Stop");
			try {
				ib.transact(2, null, null, 0);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			break;
		case 103:
			finish();
			break;
		}
	}
}
