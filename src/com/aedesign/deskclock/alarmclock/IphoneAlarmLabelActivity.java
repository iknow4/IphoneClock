// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAlarmLabelActivity.java

package com.aedesign.deskclock.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.aedesign.deskclock.ui.IphoneBottomPopActivity;
import java.util.Timer;

public class IphoneAlarmLabelActivity extends IphoneBottomPopActivity
	implements android.view.View.OnClickListener
{

	private static final boolean DEBUG = false;
	private static final String TAG = "IphoneAlarmLabelActivity";
	private static final int TAG_BACK_BUTTON;
	private Button mBackButton;
	private Button mClearButton;
	private EditText mLabelEditText;

	public IphoneAlarmLabelActivity()
	{
	}

	private void setupViews()
	{
		int i = null;
		setContentView(0x7f03000e);
		Object obj = (Button)findViewById(0x7f0d0023);
		mBackButton = ((Button) (obj));
		obj = mBackButton;
		Object obj1 = Integer.valueOf(i);
		((Button) (obj)).setTag(obj1);
		mBackButton.setOnClickListener(this);
		obj = (Button)findViewById(0x7f0d0026);
		mClearButton = ((Button) (obj));
		obj = mClearButton;
		obj1 = new 1();
		((Button) (obj)).setOnClickListener(((android.view.View.OnClickListener) (obj1)));
		obj = (EditText)findViewById(0x7f0d0025);
		mLabelEditText = ((EditText) (obj));
		Timer timer = new Timer();
		obj = new 2();
		timer.schedule(((TimerTask) (obj)), 800L);
		obj = mLabelEditText;
		obj1 = getIntent().getCharSequenceExtra("label");
		((EditText) (obj)).setText(((CharSequence) (obj1)));
		obj = mClearButton;
		boolean flag = mLabelEditText.getText().toString().equals("");
		EditText edittext;
		3 3_1;
		if (flag)
			flag = 4;
		else
			flag = i;
		((Button) (obj)).setVisibility(flag);
		edittext = mLabelEditText;
		3_1 = new 3();
		((EditText) (obj)).addTextChangedListener(flag);
	}

	public void onClick(View view)
	{
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 0: default 28
	//	               0 29;
		   goto _L1 _L2
_L1:
		return;
_L2:
		Intent intent = new Intent();
		Editable editable = mLabelEditText.getText();
		intent.putExtra("label", editable);
		setResult(-1, intent);
		finish();
		if (true) goto _L1; else goto _L3
_L3:
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setupViews();
	}



	private class 1
		implements android.view.View.OnClickListener
	{

		final IphoneAlarmLabelActivity this$0;

		public void onClick(View view)
		{
			mLabelEditText.setText("");
			mClearButton.setVisibility(4);
		}

		1()
		{
			this$0 = IphoneAlarmLabelActivity.this;
			super();
		}
	}


	private class 2 extends TimerTask
	{

		final IphoneAlarmLabelActivity this$0;

		public void run()
		{
			((InputMethodManager)mLabelEditText.getContext().getSystemService("input_method")).toggleSoftInput(0, 2);
		}

		2()
		{
			this$0 = IphoneAlarmLabelActivity.this;
			super();
		}
	}


	private class 3
		implements TextWatcher
	{

		final IphoneAlarmLabelActivity this$0;

		public void afterTextChanged(Editable editable)
		{
		}

		public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
		{
		}

		public void onTextChanged(CharSequence charsequence, int i, int j, int k)
		{
			Button button = mClearButton;
			boolean flag = mLabelEditText.getText().toString().equals("");
			byte byte0;
			if (flag)
				byte0 = 4;
			else
				byte0 = 0;
			button.setVisibility(byte0);
		}

		3()
		{
			this$0 = IphoneAlarmLabelActivity.this;
			super();
		}
	}

}
