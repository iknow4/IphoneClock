// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTimerActivity.java

package com.aedesign.deskclock.timer;

import android.content.*;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aedesign.deskclock.TimerBroadcastRecevier;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;
import com.aedesign.deskclock.ui.IphoneTwoStatusControlImageButton;
import com.aedesign.deskclock.ui.timeselector.*;

public class IphoneTimerActivity extends IphoneNoTitleActivity
	implements android.view.View.OnClickListener, OnAdjustPositonListener
{

	private static final boolean DEBUG = false;
	private static final int RINGTONE_PICKED = 3023;
	private static final String TAG = "IphoneTimerActivity";
	private final int TAG_ALERT_VOICE_LAYOUT = 3;
	private final int TAG_HOUR_LIST_VIEW = 1;
	private final int TAG_MINUTE_LIST_VIEW = 2;
	private final int TAG_START_OR_STOP_BUTTON = 4;
	private TextView mAlertVoiceTextView;
	private IphoneTwoStatusControlImageButton mButton;
	private String mCustomRingtone;
	private TimeListAdapter mHourAdapter;
	private String mHourArray[];
	private RelativeLayout mHourMinuteLayout;
	private TimeCircleListAdapter mMinuteAdapter;
	private String mMinuteArray[];
	private TimeCircleListView mTimeCircleListViewMinute;
	private TimeListView mTimeListViewHour;
	private RelativeLayout mTimeSelectorBGRelativeLayout;
	private RelativeLayout mTimeSelectorRelativeLayout;
	private RelativeLayout mTimeTextRelativeLayout;
	private TextView mTimerTextView;
	private RelativeLayout mVoiceRelativeLayout;
	private BroadcastReceiver receiver;

	public IphoneTimerActivity()
	{
		1 1_1 = new 1();
		receiver = 1_1;
	}

	private void autoStart()
	{
		mTimeSelectorRelativeLayout.setVisibility(4);
		mTimeSelectorBGRelativeLayout.setVisibility(4);
		mHourMinuteLayout.setVisibility(4);
		mTimeTextRelativeLayout.setVisibility(0);
		updateTimer();
		mButton.setStatusOneOn(null);
	}

	private void getTimeSelectorString()
	{
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata = TimerBroadcastRecevier.mTimerData;
		int i = Integer.parseInt(mTimeListViewHour.getTimeOn());
		timerdata.mHour = i;
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata1 = TimerBroadcastRecevier.mTimerData;
		int j = Integer.parseInt(mTimeCircleListViewMinute.getTimeOn());
		timerdata1.mMinute = j;
		if (TimerBroadcastRecevier.mTimerData.mHour == 0 && TimerBroadcastRecevier.mTimerData.mMinute == 0)
			TimerBroadcastRecevier.mTimerData.mMinute = 1;
		TimerBroadcastRecevier.mTimerData.mSecond = null;
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata2 = TimerBroadcastRecevier.mTimerData;
		int k = TimerBroadcastRecevier.mTimerData.mHour;
		timerdata2.mStartHour = k;
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata3 = TimerBroadcastRecevier.mTimerData;
		int l = TimerBroadcastRecevier.mTimerData.mMinute;
		timerdata3.mStartMinute = l;
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata4 = TimerBroadcastRecevier.mTimerData;
		long l1 = System.currentTimeMillis();
		timerdata4.mstartTime = <no variable>;
	}

	private void handleRingtonePicked(Uri uri)
	{
		if (uri != null && !RingtoneManager.isDefault(uri)) goto _L2; else goto _L1
_L1:
		mCustomRingtone = null;
_L4:
		updateView();
		return;
_L2:
		String s = uri.toString();
		mCustomRingtone = s;
		com.aedesign.deskclock.TimerBroadcastRecevier.TimerData timerdata = TimerBroadcastRecevier.mTimerData;
		String s1 = uri.toString();
		timerdata.alert = s1;
		if (!TimerBroadcastRecevier.mTimerData.mIsStart)
			try
			{
				TimerBroadcastRecevier.saveTimerData(TimerBroadcastRecevier.mTimerData);
			}
			catch (Exception exception)
			{
				Intent intent = new Intent("android.intent.app.IPHONE_TIMER");
				sendBroadcast(intent);
			}
		if (true) goto _L4; else goto _L3
_L3:
	}

	private void onClickButton(boolean flag)
	{
		boolean flag1;
		byte byte0;
		boolean flag2;
		flag1 = true;
		byte0 = 4;
		flag2 = false;
		if (!TimerBroadcastRecevier.mTimerData.mIsStart && !flag) goto _L2; else goto _L1
_L1:
		TimerBroadcastRecevier.mTimerData.mIsStart = flag2;
		mTimeSelectorRelativeLayout.setVisibility(flag2);
		mTimeSelectorBGRelativeLayout.setVisibility(flag2);
		mHourMinuteLayout.setVisibility(flag2);
		mTimeTextRelativeLayout.setVisibility(byte0);
		if (flag)
			mButton.setStatusOneOn(flag1);
		TimerBroadcastRecevier.saveTimerData(TimerBroadcastRecevier.mTimerData);
_L4:
		return;
_L2:
		mTimeSelectorRelativeLayout.setVisibility(byte0);
		mTimeSelectorBGRelativeLayout.setVisibility(byte0);
		mHourMinuteLayout.setVisibility(byte0);
		mTimeTextRelativeLayout.setVisibility(flag2);
		getTimeSelectorString();
		updateTimer();
		TimerBroadcastRecevier.mTimerData.mIsStart = flag1;
		try
		{
			TimerBroadcastRecevier.saveTimerData(TimerBroadcastRecevier.mTimerData);
		}
		catch (Exception exception)
		{
			Intent intent = new Intent("android.intent.app.IPHONE_TIMER");
			sendBroadcast(intent);
		}
		TimerBroadcastRecevier.mTimerThread = new com.aedesign.deskclock.TimerBroadcastRecevier.TimerThread();
		TimerBroadcastRecevier.mTimerThread.start();
		continue; /* Loop/switch isn't completed */
		Exception exception1;
		exception1;
		if (true) goto _L4; else goto _L3
_L3:
	}

	private void onClickLayout()
	{
		boolean flag = true;
		Intent intent = new Intent();
		intent.setAction("android.intent.action.timeralertvoice");
		intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", flag);
		intent.putExtra("android.intent.extra.ringtone.TYPE", flag);
		intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", null);
		Uri uri;
		if (mCustomRingtone != null)
			uri = Uri.parse(mCustomRingtone);
		else
			uri = RingtoneManager.getDefaultUri(flag);
		intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", uri);
		startActivityForResult(intent, 3023);
	}

	private void setupViews()
	{
		Resources resources = getResources();
		String as[] = resources.getStringArray(0x7f070000);
		mHourArray = as;
		String as1[] = resources.getStringArray(0x7f070002);
		mMinuteArray = as1;
		TimeListView timelistview = (TimeListView)findViewById(0x7f0d0037);
		mTimeListViewHour = timelistview;
		String as2[] = mHourArray;
		TimeListAdapter timelistadapter = new TimeListAdapter(this, as2, 0x7f03001f);
		mHourAdapter = timelistadapter;
		int i = resources.getDimensionPixelOffset(0x7f090004);
		TimeListView timelistview1 = mTimeListViewHour;
		TimeListAdapter timelistadapter1 = mHourAdapter;
		timelistview1.init(timelistadapter1, 1, i, 2);
		TimeListView timelistview2 = mTimeListViewHour;
		int j = TimerBroadcastRecevier.mTimerData.mStartHour;
		timelistview2.setTimeOnPositon(j);
		mTimeListViewHour.setOnAdjustPositionListener(this);
		TimeCircleListView timecirclelistview = (TimeCircleListView)findViewById(0x7f0d0038);
		mTimeCircleListViewMinute = timecirclelistview;
		String as3[] = mMinuteArray;
		TimeCircleListAdapter timecirclelistadapter = new TimeCircleListAdapter(this, as3, 0x7f03001f);
		mMinuteAdapter = timecirclelistadapter;
		int k = resources.getDimensionPixelOffset(0x7f090002);
		TimeCircleListView timecirclelistview1 = mTimeCircleListViewMinute;
		TimeCircleListAdapter timecirclelistadapter1 = mMinuteAdapter;
		timecirclelistview1.init(timecirclelistadapter1, 2, k, 2);
		TimeCircleListView timecirclelistview2 = mTimeCircleListViewMinute;
		int l = TimerBroadcastRecevier.mTimerData.mStartMinute;
		timecirclelistview2.setTimeOnPositon(l);
		mTimeCircleListViewMinute.setOnAdjustPositionListener(this);
		IphoneTwoStatusControlImageButton iphonetwostatuscontrolimagebutton = (IphoneTwoStatusControlImageButton)findViewById(0x7f0d0048);
		mButton = iphonetwostatuscontrolimagebutton;
		IphoneTwoStatusControlImageButton iphonetwostatuscontrolimagebutton1 = mButton;
		Integer integer = Integer.valueOf(4);
		iphonetwostatuscontrolimagebutton1.setTag(integer);
		mButton.setOnClickListener(this);
		RelativeLayout relativelayout = (RelativeLayout)findViewById(0x7f0d0045);
		mVoiceRelativeLayout = relativelayout;
		RelativeLayout relativelayout1 = mVoiceRelativeLayout;
		Integer integer1 = Integer.valueOf(3);
		relativelayout1.setTag(integer1);
		mVoiceRelativeLayout.setOnClickListener(this);
		RelativeLayout relativelayout2 = (RelativeLayout)findViewById(0x7f0d0035);
		mTimeSelectorRelativeLayout = relativelayout2;
		RelativeLayout relativelayout3 = (RelativeLayout)findViewById(0x7f0d0039);
		mTimeSelectorBGRelativeLayout = relativelayout3;
		RelativeLayout relativelayout4 = (RelativeLayout)findViewById(0x7f0d0043);
		mTimeTextRelativeLayout = relativelayout4;
		mTimeTextRelativeLayout.setVisibility(4);
		RelativeLayout relativelayout5 = (RelativeLayout)findViewById(0x7f0d0042);
		mHourMinuteLayout = relativelayout5;
		TextView textview = (TextView)findViewById(R.id.alert_voice_title);
		mTimerTextView = textview;
		TextView textview1 = (TextView)findViewById(R.id.alert_voice_text);
		mAlertVoiceTextView = textview1;
		if (!TimerBroadcastRecevier.mTimerData.alert.equals(""))
		{
			TextView textview2 = mAlertVoiceTextView;
			String s = TimerBroadcastRecevier.mTimerData.alert;
			textview2.setText(s);
		}
		if (TimerBroadcastRecevier.mTimerData.mIsStart)
			autoStart();
	}

	private void updateTimer()
	{
		byte byte0 = 10;
		String s = ":";
		String s1 = "0";
		StringBuffer stringbuffer = new StringBuffer();
		int i = TimerBroadcastRecevier.mTimerData.mHour;
		Object obj;
		if (i > 0)
		{
			int j = TimerBroadcastRecevier.mTimerData.mHour;
			int l;
			int i1;
			TextView textview;
			String s2;
			if (j < byte0)
			{
				obj = (new StringBuilder()).append(s1);
				int k = TimerBroadcastRecevier.mTimerData.mHour;
				obj = ((StringBuilder) (obj)).append(k).toString();
			} else
			{
				obj = Integer.valueOf(TimerBroadcastRecevier.mTimerData.mHour);
			}
			stringbuffer.append(obj);
			obj = ":";
			stringbuffer.append(s);
		}
		obj = TimerBroadcastRecevier.mTimerData.mMinute;
		if (obj < byte0)
		{
			obj = (new StringBuilder()).append(s1);
			l = TimerBroadcastRecevier.mTimerData.mMinute;
			obj = ((StringBuilder) (obj)).append(l).toString();
		} else
		{
			obj = Integer.valueOf(TimerBroadcastRecevier.mTimerData.mMinute);
		}
		stringbuffer.append(obj);
		stringbuffer.append(s);
		obj = TimerBroadcastRecevier.mTimerData.mSecond;
		if (obj < byte0)
		{
			obj = (new StringBuilder()).append(s1);
			i1 = TimerBroadcastRecevier.mTimerData.mSecond;
			obj = ((StringBuilder) (obj)).append(i1).toString();
		} else
		{
			obj = Integer.valueOf(TimerBroadcastRecevier.mTimerData.mSecond);
		}
		stringbuffer.append(obj);
		textview = mTimerTextView;
		s2 = stringbuffer.toString();
		((TextView) (obj)).setText(s2);
	}

	private void updateView()
	{
		if (mCustomRingtone != null) goto _L2; else goto _L1
_L1:
		TextView textview = mAlertVoiceTextView;
		String s = getString(R.string.iphone_timer_sleep_type_voice);
		textview.setText(s);
_L4:
		return;
_L2:
		Uri uri = Uri.parse(mCustomRingtone);
		Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
		if (ringtone != null)
		{
			TextView textview1 = mAlertVoiceTextView;
			String s1 = ringtone.getTitle(this);
			textview1.setText(s1);
		}
		if (true) goto _L4; else goto _L3
_L3:
	}

	protected void onActivityResult(int i, int j, Intent intent)
	{
		byte byte0;
		byte0 = -1;
		super.onActivityResult(i, j, intent);
		if (j == byte0) goto _L2; else goto _L1
_L1:
		return;
_L2:
		switch (i)
		{
		case 3023: 
			if (j == byte0 && intent != null)
			{
				Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
				handleRingtonePicked(uri);
			}
			break;
		}
		if (true) goto _L1; else goto _L3
_L3:
	}

	public void onAdjuestPosition(Object obj)
	{
		if (mTimeCircleListViewMinute.getTimeOn().equals("00") && mTimeListViewHour.getTimeOn().equals("0"))
		{
			mTimeCircleListViewMinute.setOnAdjustPositionListener(null);
			mTimeCircleListViewMinute.setNextPosition();
			mTimeCircleListViewMinute.setOnAdjustPositionListener(this);
		}
	}

	public void onClick(View view)
	{
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 3 4: default 32
	//	               3 41
	//	               4 33;
		   goto _L1 _L2 _L3
_L1:
		return;
_L3:
		onClickButton(null);
		continue; /* Loop/switch isn't completed */
_L2:
		onClickLayout();
		if (true) goto _L1; else goto _L4
_L4:
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(0x7f030014);
		setupViews();
	}

	protected void onDestroy()
	{
		super.onDestroy();
		BroadcastReceiver broadcastreceiver = receiver;
		unregisterReceiver(broadcastreceiver);
	}

	protected void onResume()
	{
		byte byte0 = 4;
		int i = 0;
		if (TimerBroadcastRecevier.mTimerData.mIsStart)
		{
			mTimeSelectorRelativeLayout.setVisibility(byte0);
			mTimeSelectorBGRelativeLayout.setVisibility(byte0);
			mHourMinuteLayout.setVisibility(byte0);
			mTimeTextRelativeLayout.setVisibility(i);
		} else
		{
			mTimeSelectorRelativeLayout.setVisibility(i);
			mTimeSelectorBGRelativeLayout.setVisibility(i);
			mHourMinuteLayout.setVisibility(i);
			mTimeTextRelativeLayout.setVisibility(byte0);
			mButton.setStatusOneOn(true);
		}
		updateView();
		super.onResume();
	}

	protected void onStart()
	{
		super.onStart();
		IntentFilter intentfilter = new IntentFilter();
		intentfilter.addAction("action.iphone_timer_change");
		BroadcastReceiver broadcastreceiver = receiver;
		registerReceiver(broadcastreceiver, intentfilter);
	}



	private class 1 extends BroadcastReceiver
	{

		final IphoneTimerActivity this$0;

		public void onReceive(Context context, Intent intent)
		{
			if (intent.getAction().equals("action.iphone_timer_change"))
			{
				updateTimer();
				if (intent.getBooleanExtra("isStop", null))
					onClickButton(true);
			}
		}

		1()
		{
			this$0 = IphoneTimerActivity.this;
			super();
		}
	}

}
