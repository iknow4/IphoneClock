// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmPreference.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.RingtonePreference;
import android.util.AttributeSet;

public class AlarmPreference extends RingtonePreference
{

	private Uri mAlert;

	public AlarmPreference(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
	}

	public String getAlertString()
	{
		Object obj = mAlert;
		if (obj != null)
			obj = mAlert.toString();
		else
			obj = "silent";
		return ((String) (obj));
	}

	protected Uri onRestoreRingtone()
	{
		return mAlert;
	}

	protected void onSaveRingtone(Uri uri)
	{
		setAlert(uri);
	}

	public void setAlert(Uri uri)
	{
		mAlert = uri;
		if (uri != null)
		{
			Ringtone ringtone = RingtoneManager.getRingtone(getContext(), uri);
			if (ringtone != null)
			{
				Context context = getContext();
				String s = ringtone.getTitle(context);
				setSummary(s);
			}
		} else
		{
			setSummary(0x7f0a0064);
		}
	}
}
