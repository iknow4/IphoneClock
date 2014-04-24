// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TimerBroadcastRecevier.java

package com.aedesign.deskclock;

import android.content.*;
import android.os.Handler;

public class TimerBroadcastRecevier extends BroadcastReceiver
{
	public class TimerThread extends Thread
	{

		private int mSecondeMill;

		public void run()
		{
			int i;
			int j;
			boolean flag;
			i = 0;
			j = 59;
			flag = true;
			mSecondeMill = i;
_L5:
			boolean flag1 = TimerBroadcastRecevier.mTimerData.mIsStart;
			if (!flag1) goto _L2; else goto _L1
_L1:
			long l = 100L;
			Thread.sleep(l);
_L3:
			int k = TimerBroadcastRecevier.mTimerData.mSecond;
			if (k != 0)
				break MISSING_BLOCK_LABEL_128;
			k = TimerBroadcastRecevier.mTimerData.mMinute;
			if (k != 0)
				break MISSING_BLOCK_LABEL_128;
			k = TimerBroadcastRecevier.mTimerData.mHour;
			if (k != 0)
				break MISSING_BLOCK_LABEL_128;
			Object obj;
			boolean flag2;
			Intent intent;
			int i1;
			try
			{
				stopTimer();
				TimerBroadcastRecevier.saveTimerData(TimerBroadcastRecevier.mTimerData);
			}
			catch (Exception exception) { }
			intent = new Intent("action.iphone_timer_change");
			intent.putExtra("isStop", flag);
			TimerBroadcastRecevier.mServicesContext.sendBroadcast(intent);
			TimerBroadcastRecevier.mSelfHandler.sendEmptyMessage(i);
_L2:
			return;
			printStackTrace();
			  goto _L3
			k = mSecondeMill;
			k++;
			mSecondeMill = k;
			k = mSecondeMill;
			i1 = 10;
			if (k < i1)
				continue; /* Loop/switch isn't completed */
			k = mSecondeMill % 10;
			mSecondeMill = k;
			obj = TimerBroadcastRecevier.mTimerData;
			i1 = TimerBroadcastRecevier.mTimerData.mSecond;
			if (i1 == 0)
				i1 = j;
			else
				i1 = TimerBroadcastRecevier.mTimerData.mSecond - flag;
			obj.mSecond = i1;
			obj = TimerBroadcastRecevier.mTimerData.mSecond;
			if (obj != j)
				break; /* Loop/switch isn't completed */
			obj = TimerBroadcastRecevier.mTimerData.mMinute;
			if (obj != 0)
				break; /* Loop/switch isn't completed */
			obj = TimerBroadcastRecevier.mTimerData;
			obj.mMinute = j;
_L6:
			obj = TimerBroadcastRecevier.mTimerData.mMinute;
			if (obj == j)
			{
				obj = TimerBroadcastRecevier.mTimerData.mSecond;
				if (obj == j)
				{
					obj = TimerBroadcastRecevier.mTimerData;
					i1 = ((TimerData) (obj)).mHour - flag;
					obj.mHour = i1;
				}
			}
			obj = TimerBroadcastRecevier.mSelfHandler;
			i1 = 2;
			((Handler) (obj)).sendEmptyMessage(i1);
			flag2 = TimerBroadcastRecevier.mSelfHandler.hasMessages(flag);
			if (flag2)
			{
				flag2 = TimerBroadcastRecevier.mSelfHandler;
				flag2.removeMessages(flag);
			}
			flag2 = TimerBroadcastRecevier.mSelfHandler;
			flag2.sendEmptyMessage(flag);
			if (true) goto _L5; else goto _L4
_L4:
			flag2 = TimerBroadcastRecevier.mTimerData.mSecond;
			if (flag2 == j)
			{
				flag2 = TimerBroadcastRecevier.mTimerData;
				i1 = ((TimerData) (flag2)).mMinute - flag;
				flag2.mMinute = i1;
			}
			  goto _L6
			if (true) goto _L5; else goto _L7
_L7:
		}

		public void stopTimer()
		{
			TimerBroadcastRecevier.mTimerData.mIsStart = null;
		}

		public TimerThread()
		{
		}
	}

	public class TimerData
	{

		public static final String KEY_ALERT = "alert";
		public static final String KEY_HOUR = "mHour";
		public static final String KEY_IS_START = "mIsStart";
		public static final String KEY_MINUTE = "mMinute";
		public static final String KEY_SECOND = "mSecond";
		public static final String KEY_START_HOUR = "mStartHour";
		public static final String KEY_START_MINUTE = "mStartMinute";
		public static final String KEY_START_SECOND = "mStartSecond";
		public static final String KEY_START_TIMER = "mStartTimer";
		public String alert;
		public int mHour;
		public boolean mIsStart;
		public int mMinute;
		public int mSecond;
		public int mStartHour;
		public int mStartMinute;
		public int mStartSecond;
		public long mstartTime;

		public TimerData()
		{
			alert = "";
		}
	}


	public static final String ACTION_IPHONE_TIMER = "android.intent.app.IPHONE_TIMER";
	public static final String ACTION_IPHONE_TIMER_CHANGE = "action.iphone_timer_change";
	public static final String ALARM_ALERT_URI = "alarm_alert_uri";
	private static final boolean DEBUG = false;
	private static final int MSG_SAVE_DATA = 1;
	private static final int MSG_SEND_TIME_CHANG = 2;
	private static final int MSG_SHOW_MESSAGE = 0;
	private static final String TAG = "TimerBroadcastRecevier";
	private static final String TIMER_PREFERENCES = "IphoneTimer";
	private static Handler mSelfHandler = new 1();
	private static Context mServicesContext;
	public static TimerData mTimerData = new TimerData();
	public static TimerThread mTimerThread;

	public TimerBroadcastRecevier()
	{
	}

	public static TimerData getTimerData()
		throws Exception
	{
		boolean flag = null;
		SharedPreferences sharedpreferences = mServicesContext.getSharedPreferences("IphoneTimer", flag);
		Object obj = 0;
		if (sharedpreferences != null)
		{
			obj = new TimerData();
			int i = sharedpreferences.getInt("mHour", flag);
			obj.mHour = i;
			int j = sharedpreferences.getInt("mMinute", flag);
			obj.mMinute = j;
			int k = sharedpreferences.getInt("mSecond", flag);
			obj.mSecond = k;
			int l = sharedpreferences.getInt("mStartHour", flag);
			obj.mStartHour = l;
			int i1 = sharedpreferences.getInt("mStartMinute", flag);
			obj.mStartMinute = i1;
			int j1 = sharedpreferences.getInt("mStartSecond", flag);
			obj.mStartSecond = j1;
			long l1 = sharedpreferences.getLong("mStartTimer", 0L);
			obj.mstartTime = <no variable>;
			boolean flag1 = sharedpreferences.getBoolean("mIsStart", flag);
			obj.mIsStart = flag1;
			String s = sharedpreferences.getString("alert", "");
			obj.alert = s;
		}
		return ((TimerData) (obj));
	}

	public static boolean saveTimerData(TimerData timerdata)
		throws Exception
	{
		android.content.SharedPreferences.Editor editor = mServicesContext.getSharedPreferences("IphoneTimer", 0).edit();
		int i = timerdata.mHour;
		editor.putInt("mHour", i);
		int j = timerdata.mMinute;
		editor.putInt("mMinute", j);
		int k = timerdata.mSecond;
		editor.putInt("mSecond", k);
		int l = timerdata.mStartHour;
		editor.putInt("mStartHour", l);
		int i1 = timerdata.mStartMinute;
		editor.putInt("mStartMinute", i1);
		int j1 = timerdata.mStartSecond;
		editor.putInt("mStartSecond", j1);
		long l1 = timerdata.mstartTime;
		editor.putLong("mStartTimer", l1);
		boolean flag = timerdata.mIsStart;
		editor.putBoolean("mIsStart", flag);
		String s = timerdata.alert;
		editor.putString("alert", s);
		return editor.commit();
	}

	private static void showMessage()
	{
		Intent intent = new Intent("android.intent.action.transacti");
		String s = mTimerData.alert;
		intent.putExtra("alarm_alert_uri", s);
		intent.setFlags(0x10000000);
		mServicesContext.startActivity(intent);
	}

	public void onReceive(Context context, Intent intent)
	{
		mServicesContext = context;
		if (!intent.getAction().equals("android.intent.app.IPHONE_TIMER")) goto _L2; else goto _L1
_L1:
		return;
_L2:
		try
		{
			mTimerData = getTimerData();
		}
		catch (Exception exception) { }
		if (mTimerData.mIsStart)
		{
			long l = System.currentTimeMillis();
			long l1 = mTimerData.mstartTime;
			long l2 = (<no variable> - l1) / 1000L;
			int i = mTimerData.mStartHour * 60 * 60;
			int j = mTimerData.mStartMinute * 60;
			long l3 = (long)(i + j) - l2;
			if (l3 <= 0L)
			{
				mTimerData.mIsStart = null;
				mSelfHandler.sendEmptyMessage(0);
			} else
			{
				TimerData timerdata = mTimerData;
				int k = (int)(l3 % 60L);
				timerdata.mSecond = k;
				int i1 = (int)(l3 / 60L);
				TimerData timerdata1 = mTimerData;
				int j1 = i1 % 60;
				timerdata1.mMinute = j1;
				int k1 = i1 / 60;
				TimerData timerdata2 = mTimerData;
				int i2 = k1 % 60;
				timerdata2.mHour = i2;
				mTimerThread = new TimerThread();
				mTimerThread.start();
			}
		}
		if (true) goto _L1; else goto _L3
_L3:
	}





	private class 1 extends Handler
	{

		public void handleMessage(Message message)
		{
			message.what;
			JVM INSTR tableswitch 0 2: default 32
		//		               0 33
		//		               1 39
		//		               2 53;
			   goto _L1 _L2 _L3 _L4
_L1:
			return;
_L2:
			TimerBroadcastRecevier.showMessage();
			continue; /* Loop/switch isn't completed */
_L3:
			try
			{
				TimerBroadcastRecevier.saveTimerData(TimerBroadcastRecevier.mTimerData);
			}
			catch (Exception exception) { }
			continue; /* Loop/switch isn't completed */
_L4:
			Context context = TimerBroadcastRecevier.mServicesContext;
			Intent intent = new Intent("action.iphone_timer_change");
			context.sendBroadcast(intent);
			if (true) goto _L1; else goto _L5
_L5:
		}

		1()
		{
		}
	}

}
