// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmAlertWakeLock.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.os.PowerManager;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Log

class AlarmAlertWakeLock
{

	private static android.os.PowerManager.WakeLock sCpuWakeLock;

	AlarmAlertWakeLock()
	{
	}

	static void acquireCpuWakeLock(Context context)
	{
		Log.v("Acquiring cpu wake lock");
		if (sCpuWakeLock == null)
		{
			sCpuWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(0x30000001, "AlarmClock");
			sCpuWakeLock.acquire();
		}
	}

	static void releaseCpuLock()
	{
		Log.v("Releasing cpu wake lock");
		if (sCpuWakeLock != null)
		{
			sCpuWakeLock.release();
			sCpuWakeLock = null;
		}
	}
}
