// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RepeatPreference.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.preference.ListPreference;
import android.util.AttributeSet;
import java.text.DateFormatSymbols;

public class RepeatPreference extends ListPreference
{

	private Alarm.DaysOfWeek mDaysOfWeek;
	private Alarm.DaysOfWeek mNewDaysOfWeek;

	public RepeatPreference(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		Alarm.DaysOfWeek daysofweek = new Alarm.DaysOfWeek(0);
		mDaysOfWeek = daysofweek;
		Alarm.DaysOfWeek daysofweek1 = new Alarm.DaysOfWeek(0);
		mNewDaysOfWeek = daysofweek1;
		String as[] = (new DateFormatSymbols()).getWeekdays();
		String as1[] = new String[7];
		String s = as[2];
		as1[0] = s;
		String s1 = as[3];
		as1[1] = s1;
		String s2 = as[4];
		as1[2] = s2;
		String s3 = as[5];
		as1[3] = s3;
		String s4 = as[6];
		as1[4] = s4;
		String s5 = as[7];
		as1[5] = s5;
		String s6 = as[1];
		as1[6] = s6;
		setEntries(as1);
		setEntryValues(as1);
	}

	public Alarm.DaysOfWeek getDaysOfWeek()
	{
		return mDaysOfWeek;
	}

	protected void onDialogClosed(boolean flag)
	{
		if (flag)
		{
			Alarm.DaysOfWeek daysofweek = mDaysOfWeek;
			Alarm.DaysOfWeek daysofweek1 = mNewDaysOfWeek;
			daysofweek.set(daysofweek1);
			Alarm.DaysOfWeek daysofweek2 = mDaysOfWeek;
			Context context = getContext();
			String s = daysofweek2.toString(context, true);
			setSummary(s);
		}
	}

	protected void onPrepareDialogBuilder(android.app.AlertDialog.Builder builder)
	{
		CharSequence acharsequence[] = getEntries();
		CharSequence acharsequence1[] = getEntryValues();
		boolean aflag[] = mDaysOfWeek.getBooleanArray();
		1 1_1 = new 1();
		builder.setMultiChoiceItems(acharsequence, aflag, 1_1);
	}

	public void setDaysOfWeek(Alarm.DaysOfWeek daysofweek)
	{
		mDaysOfWeek.set(daysofweek);
		mNewDaysOfWeek.set(daysofweek);
		Context context = getContext();
		String s = daysofweek.toString(context, true);
		setSummary(s);
	}


	private class 1
		implements android.content.DialogInterface.OnMultiChoiceClickListener
	{

		final RepeatPreference this$0;

		public void onClick(DialogInterface dialoginterface, int i, boolean flag)
		{
			mNewDaysOfWeek.set(i, flag);
		}

		1()
		{
			this$0 = RepeatPreference.this;
			super();
		}
	}

}
