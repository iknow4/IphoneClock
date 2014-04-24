// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DigitalClock.java

package com.aedesign.deskclock.alarmclock;

import android.content.*;
import android.database.ContentObserver;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Calendar;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Alarms

public class DigitalClock extends LinearLayout
{
	class FormatChangeObserver extends ContentObserver
	{

		final DigitalClock this$0;

		public void onChange(boolean flag)
		{
			setDateFormat();
			updateTime();
		}

		public FormatChangeObserver()
		{
			this$0 = DigitalClock.this;
			Handler handler = new Handler();
			super(handler);
		}
	}

	class AmPm
	{

		private TextView mAm;
		private LinearLayout mAmPmLayout;
		private TextView mIphoneAmPm;
		private TextView mPm;

		void setIsMorning(boolean flag)
		{
			TextView textview = mIphoneAmPm;
			CharSequence charsequence;
			if (flag)
				charsequence = mAm.getText();
			else
				charsequence = mPm.getText();
			textview.setText(charsequence);
		}

		void setShowAmPm(boolean flag)
		{
			LinearLayout linearlayout = mAmPmLayout;
			int i;
			if (flag)
				i = 0;
			else
				i = 8;
			linearlayout.setVisibility(i);
		}

		AmPm(View view)
		{
			LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f0d000c);
			mAmPmLayout = linearlayout;
			TextView textview = (TextView)mAmPmLayout.findViewById(0x7f0d000e);
			mAm = textview;
			TextView textview1 = (TextView)mAmPmLayout.findViewById(0x7f0d000f);
			mPm = textview1;
			TextView textview2 = (TextView)mAmPmLayout.findViewById(0x7f0d000d);
			mIphoneAmPm = textview2;
			android.content.res.Resources resources = view.getResources();
		}
	}


	private static final String M12 = "h:mm";
	private AmPm mAmPm;
	private boolean mAnimate;
	private boolean mAttached;
	private Calendar mCalendar;
	private Context mContext;
	private String mFormat;
	private ContentObserver mFormatChangeObserver;
	private final Handler mHandler;
	private final BroadcastReceiver mIntentReceiver;
	private boolean mLive;
	private TextView mTimeDisplay;

	public DigitalClock(Context context)
	{
		this(context, null);
	}

	public DigitalClock(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		mLive = true;
		Handler handler = new Handler();
		mHandler = handler;
		1 1_1 = new 1();
		mIntentReceiver = 1_1;
		mContext = context;
	}

	private void setDateFormat()
	{
		int i = "h:mm";
		boolean flag = Alarms.get24HourMode(mContext);
		Object obj;
		int j;
		if (flag)
			obj = "kk:mm";
		else
			obj = i;
		mFormat = ((String) (obj));
		obj = mAmPm;
		j = mFormat;
		if (j == i)
			j = 1;
		else
			j = null;
		((AmPm) (obj)).setShowAmPm(j);
	}

	private void updateTime()
	{
		boolean flag = mLive;
		if (flag)
		{
			Calendar calendar = mCalendar;
			long l = System.currentTimeMillis();
			calendar.setTimeInMillis(<no variable>);
		}
		Object obj = mFormat;
		Calendar calendar1 = mCalendar;
		CharSequence charsequence = DateFormat.format(((CharSequence) (obj)), calendar1);
		mTimeDisplay.setText(charsequence);
		obj = mAmPm;
		int i = mCalendar.get(9);
		if (i == 0)
			i = 1;
		else
			i = null;
		((AmPm) (obj)).setIsMorning(i);
	}

	protected void onAttachedToWindow()
	{
		boolean flag = true;
		super.onAttachedToWindow();
		if (!mAttached)
		{
			mAttached = flag;
			if (mAnimate)
			{
				setBackgroundResource(0x7f020000);
				((AnimationDrawable)getBackground()).start();
			}
			if (mLive)
			{
				IntentFilter intentfilter = new IntentFilter();
				intentfilter.addAction("android.intent.action.TIME_TICK");
				intentfilter.addAction("android.intent.action.TIME_SET");
				intentfilter.addAction("android.intent.action.TIMEZONE_CHANGED");
				Context context = mContext;
				BroadcastReceiver broadcastreceiver = mIntentReceiver;
				Handler handler = mHandler;
				context.registerReceiver(broadcastreceiver, intentfilter, null, handler);
			}
			FormatChangeObserver formatchangeobserver = new FormatChangeObserver();
			mFormatChangeObserver = formatchangeobserver;
			ContentResolver contentresolver = mContext.getContentResolver();
			android.net.Uri uri = android.provider.Settings.System.CONTENT_URI;
			ContentObserver contentobserver = mFormatChangeObserver;
			contentresolver.registerContentObserver(uri, flag, contentobserver);
			updateTime();
		}
	}

	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		if (mAttached)
		{
			mAttached = null;
			android.graphics.drawable.Drawable drawable = getBackground();
			if (drawable instanceof AnimationDrawable)
				((AnimationDrawable)drawable).stop();
			if (mLive)
			{
				Context context = mContext;
				BroadcastReceiver broadcastreceiver = mIntentReceiver;
				context.unregisterReceiver(broadcastreceiver);
			}
			ContentResolver contentresolver = mContext.getContentResolver();
			ContentObserver contentobserver = mFormatChangeObserver;
			contentresolver.unregisterContentObserver(contentobserver);
		}
	}

	protected void onFinishInflate()
	{
		super.onFinishInflate();
		TextView textview = (TextView)findViewById(0x7f0d0010);
		mTimeDisplay = textview;
		AmPm ampm = new AmPm(this);
		mAmPm = ampm;
		Calendar calendar = Calendar.getInstance();
		mCalendar = calendar;
		setDateFormat();
	}

	void setAnimate()
	{
		mAnimate = true;
	}

	void setLive(boolean flag)
	{
		mLive = flag;
	}

	void updateTime(Calendar calendar)
	{
		mCalendar = calendar;
		updateTime();
	}



/*
	static Calendar access$102(DigitalClock digitalclock, Calendar calendar)
	{
		digitalclock.mCalendar = calendar;
		return calendar;
	}

*/



	private class 1 extends BroadcastReceiver
	{

		final DigitalClock this$0;

		public void onReceive(Context context, Intent intent)
		{
			if (mLive && intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
			{
				DigitalClock digitalclock = DigitalClock.this;
				Calendar calendar = Calendar.getInstance();
				digitalclock.mCalendar = calendar;
			}
			updateTime();
		}

		1()
		{
			this$0 = DigitalClock.this;
			super();
		}
	}

}
