// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneClockBroadcastThread.java

package com.aedesign.deskclock.worldclock;

import android.content.Context;
import android.content.Intent;
import android.text.format.Time;
import com.aedesign.deskclock.listener.ClockBroadcastListener;
import java.util.Vector;

public class IphoneClockBroadcastThread extends Thread
{

	public static final String ACTION_IPHONE_CLOCK_SECOND_CHANGE = "android.intent.app.IPHONE_CLOCK";
	private static final boolean DEBUG = false;
	private static final String TAG = "IphoneClockBroadcastThread";
	private static Context mBroadcastContext;
	private static Vector mClocks = new Vector();
	private static boolean mFlags;
	private static IphoneClockBroadcastThread mInstance;

	private IphoneClockBroadcastThread()
	{
	}

	public static IphoneClockBroadcastThread getInstance(Context context)
	{
		if (mBroadcastContext == null)
			mBroadcastContext = context;
		if (mInstance == null)
			mInstance = new IphoneClockBroadcastThread();
		return mInstance;
	}

	public void addClockListener(ClockBroadcastListener clockbroadcastlistener)
	{
		boolean flag = mClocks;
		flag.add(clockbroadcastlistener);
		mFlags = flag;
		if (flag == null)
		{
			boolean flag1 = mFlags;
			IphoneClockBroadcastThread iphoneclockbroadcastthread = mInstance;
			(new Thread(iphoneclockbroadcastthread)).start();
		}
	}

	public void removeClockListener(ClockBroadcastListener clockbroadcastlistener)
	{
		mClocks.removeElement(clockbroadcastlistener);
		if (mClocks.size() == 0)
		{
			boolean flag = mFlags;
			mClocks.clear();
		}
	}

	public void run()
	{
		Time time;
		int i;
		time = new Time();
		i = -1;
_L2:
		long l;
		Object obj;
		mFlags = ((boolean) (obj));
		if (obj == 0)
			break; /* Loop/switch isn't completed */
		obj = mClocks;
		int j = ((Vector) (obj)).size();
		time.setToNow();
		int k = time.second;
		if (i != k)
		{
			i = k;
			Intent intent = new Intent("android.intent.app.IPHONE_CLOCK");
			intent.putExtra("currentSecond", i);
			obj = mBroadcastContext;
			((Context) (obj)).sendBroadcast(intent);
		}
		l = 20L;
		Thread.sleep(l);
		continue; /* Loop/switch isn't completed */
		printStackTrace();
		if (true) goto _L2; else goto _L1
_L1:
	}

	static 
	{
		boolean flag = mFlags;
	}
}
