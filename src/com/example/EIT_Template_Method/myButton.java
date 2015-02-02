package com.example.EIT_Template_Method;

import android.content.Context;
import android.widget.Button;

/**
 * Created by sreay on 15/2/2.
 */
public class myButton extends Button {
	public myButton(Context ctx) {
		super(ctx);
	}

	public int get_width() {
		return 80;
	}

	public int get_height() {
		return 50;
	}

}
