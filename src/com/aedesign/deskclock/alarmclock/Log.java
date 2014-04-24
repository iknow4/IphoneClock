// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Log.java

package com.aedesign.deskclock.alarmclock;


class Log
{

	public static final String LOGTAG = "AlarmClock";
	//static final boolean LOGV;

	Log()
	{
	}

	static void e(String s)
	{
		android.util.Log.e("AlarmClock", s);
	}

	static void e(String s, Exception exception)
	{
		android.util.Log.e("AlarmClock", s, exception);
	}

	static void v(String s)
	{
		android.util.Log.v("AlarmClock", s);
	}
}
