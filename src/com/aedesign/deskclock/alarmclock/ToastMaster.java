// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ToastMaster.java

package com.aedesign.deskclock.alarmclock;

import android.widget.Toast;

public class ToastMaster
{

	private static Toast sToast = null;

	private ToastMaster()
	{
	}

	public static void cancelToast()
	{
		if (sToast != null)
			sToast.cancel();
		sToast = null;
	}

	public static void setToast(Toast toast)
	{
		if (sToast != null)
			sToast.cancel();
		sToast = toast;
	}

}
