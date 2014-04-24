// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SettingsActivity.java

package com.aedesign.deskclock.alarmclock;

import android.os.Bundle;
import android.preference.*;

public class SettingsActivity extends PreferenceActivity
	implements android.preference.Preference.OnPreferenceChangeListener
{

	private static final int ALARM_STREAM_TYPE_BIT = 16;
	private static final String KEY_ALARM_IN_SILENT_MODE = "alarm_in_silent_mode";
	static final String KEY_ALARM_SNOOZE = "snooze_duration";
	static final String KEY_VOLUME_BEHAVIOR = "volume_button_setting";

	public SettingsActivity()
	{
	}

	private void refresh()
	{
		int i = 0;
		CheckBoxPreference checkboxpreference = (CheckBoxPreference)findPreference("alarm_in_silent_mode");
		int j = android.provider.Settings.System.getInt(getContentResolver(), "mode_ringer_streams_affected", i) & 0x10;
		CharSequence charsequence;
		ListPreference listpreference;
		CharSequence charsequence1;
		if (j == 0)
			charsequence = 1;
		else
			charsequence = i;
		checkboxpreference.setChecked(charsequence);
		listpreference = (ListPreference)findPreference("snooze_duration");
		charsequence1 = listpreference.getEntry();
		listpreference.setSummary(charsequence);
		listpreference.setOnPreferenceChangeListener(this);
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		addPreferencesFromResource(0x7f050002);
	}

	public boolean onPreferenceChange(Preference preference, Object obj)
	{
		ListPreference listpreference = (ListPreference)preference;
		String s = (String)obj;
		int i = listpreference.findIndexOfValue(((String) (obj)));
		CharSequence charsequence = listpreference.getEntries()[i];
		listpreference.setSummary(charsequence);
		return true;
	}

	public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
	{
		String s = "mode_ringer_streams_affected";
		String s1 = preference.getKey();
		boolean flag = "alarm_in_silent_mode".equals(s1);
		if (flag)
		{
			CheckBoxPreference checkboxpreference = (CheckBoxPreference)preference;
			int i = android.provider.Settings.System.getInt(getContentResolver(), s, 0);
			flag = checkboxpreference.isChecked();
			if (flag)
				i &= 0xffffffef;
			else
				i |= 0x10;
			android.provider.Settings.System.putInt(getContentResolver(), s, i);
			flag = true;
		} else
		{
			flag = super.onPreferenceTreeClick(preferencescreen, preference);
		}
		return flag;
	}

	protected void onResume()
	{
		super.onResume();
		refresh();
	}
}
