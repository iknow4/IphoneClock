// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AnalogAppWidgetProvider.java

package com.aedesign.deskclock.alarmclock;

import android.appwidget.AppWidgetManager;
import android.content.*;
import android.widget.RemoteViews;

public class AnalogAppWidgetProvider extends BroadcastReceiver
{

	static final String TAG = "AnalogAppWidgetProvider";

	public AnalogAppWidgetProvider()
	{
	}

	public void onReceive(Context context, Intent intent)
	{
		String s = intent.getAction();
		if ("android.appwidget.action.APPWIDGET_UPDATE".equals(s))
		{
			String s1 = context.getPackageName();
			RemoteViews remoteviews = new RemoteViews(s1, 0x7f030004);
			int ai[] = intent.getIntArrayExtra("appWidgetIds");
			AppWidgetManager.getInstance(context).updateAppWidget(ai, remoteviews);
		}
	}
}
