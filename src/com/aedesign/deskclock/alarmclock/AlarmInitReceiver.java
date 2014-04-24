// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmInitReceiver.java

package com.aedesign.deskclock.alarmclock;

import android.content.*;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Log, Alarms

public class AlarmInitReceiver extends BroadcastReceiver
{

	public AlarmInitReceiver()
	{
	}

	public void onReceive(Context context, Intent intent)
	{
		String s = intent.getAction();
		if (context.getContentResolver() == null)
		{
			Log.e("AlarmInitReceiver: FAILURE unable to get content resolver.  Alarms inactive.");
		} else
		{
			if (s.equals("android.intent.action.BOOT_COMPLETED"))
			{
				Alarms.saveSnoozeAlert(context, -1, 65535L);
				Alarms.disableExpiredAlarms(context);
			}
			Alarms.setNextAlert(context);
		}
	}
}
