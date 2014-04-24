// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAlarmRepeatActivity.java

package com.aedesign.deskclock.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.aedesign.deskclock.ui.IphoneLabelCheckView;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;

public class IphoneAlarmRepeatActivity extends IphoneNoTitleActivity
	implements android.view.View.OnClickListener
{

	private static final boolean DEBUG = false;
	private static final String TAG = "IphoneAlarmRepeatActivity";
	private static final int TAG_BACK_BUTTON;
	private Alarm.DaysOfWeek daysOfWeek;
	private LinearLayout mAlarmRepeatListLayout;
	private Button mBackButton;
	private LayoutInflater mInflater;
	private int weekdays[];

	public IphoneAlarmRepeatActivity()
	{
		int ai[] = {
			0x7f0a002e, 0x7f0a002f, 0x7f0a0030, 0x7f0a0031, 0x7f0a0032, 0x7f0a0033, 0x7f0a0034
		};
		weekdays = ai;
	}

	private boolean[] getIsCheckIndex()
	{
		int i = mAlarmRepeatListLayout.getChildCount();
		boolean aflag[] = new boolean[7];
		for (int j = 0; j < i; j++)
		{
			boolean flag = ((IphoneLabelCheckView)mAlarmRepeatListLayout.getChildAt(j)).isCheck();
			aflag[j] = flag;
		}

		return aflag;
	}

	private void setupViews()
	{
		mAlarmRepeatListLayout.removeAllViews();
		boolean aflag[] = getIntent().getBooleanArrayExtra("repeat");
		int i = null;
		while (i < 7) 
		{
			LayoutInflater layoutinflater = mInflater;
			LinearLayout linearlayout = mAlarmRepeatListLayout;
			IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)layoutinflater.inflate(0x7f03001a, linearlayout, null);
			int j;
			boolean flag;
			if (i == null)
				iphonelabelcheckview.setBackgroundResource(0x7f02007b);
			else
			if (i == 6)
				iphonelabelcheckview.setBackgroundResource(0x7f02006f);
			else
				iphonelabelcheckview.setBackgroundResource(0x7f020075);
			j = weekdays[i];
			iphonelabelcheckview.setLabelTextRes(j);
			iphonelabelcheckview.setMultiCheckable(true);
			flag = aflag[i];
			iphonelabelcheckview.setCheck(flag);
			mAlarmRepeatListLayout.addView(iphonelabelcheckview);
			i++;
		}
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
		boolean aflag[] = getIsCheckIndex();
		intent.putExtra("repeat", aflag);
		setResult(-1, intent);
		finish();
		if (true) goto _L1; else goto _L3
_L3:
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(0x7f03000f);
		Button button = (Button)findViewById(0x7f0d0023);
		mBackButton = button;
		Button button1 = mBackButton;
		Integer integer = Integer.valueOf(0);
		button1.setTag(integer);
		mBackButton.setOnClickListener(this);
		LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0d0027);
		mAlarmRepeatListLayout = linearlayout;
		LayoutInflater layoutinflater = getLayoutInflater();
		mInflater = layoutinflater;
		setupViews();
	}
}
