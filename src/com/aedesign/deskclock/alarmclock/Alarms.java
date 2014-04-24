// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Alarms.java

package com.aedesign.deskclock.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.text.format.DateFormat;
import java.util.Calendar;
import java.util.Date;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Alarm

public class Alarms
{

	public static final String ALARM_ALERT_ACTION = "com.aedesign.alarmclock.ALARM_ALERT";
	public static final String ALARM_ALERT_SILENT = "silent";
	public static final String ALARM_ID = "alarm_id";
	public static final String ALARM_INTENT_EXTRA = "intent.extra.alarm";
	public static final String ALARM_KILLED = "alarm_killed";
	public static final String ALARM_KILLED_TIMEOUT = "alarm_killed_timeout";
	public static final String ALARM_RAW_DATA = "intent.extra.alarm_raw";
	public static final String CANCEL_SNOOZE = "cancel_snooze";
	public static final String CLEAR_NOTIFICATION = "clear_notification";
	private static final String DM12 = "E h:mm aa";
	private static final String DM24 = "E k:mm";
	private static final String M12 = "h:mm aa";
	static final String M24 = "kk:mm";
	static final String PREF_SNOOZE_ID = "snooze_id";
	static final String PREF_SNOOZE_TIME = "snooze_time";

	public Alarms()
	{
	}

	public static Uri addAlarm(ContentResolver contentresolver)
	{
		ContentValues contentvalues = new ContentValues();
		Integer integer = Integer.valueOf(8);
		contentvalues.put("hour", integer);
		Uri uri = Alarm.Columns.CONTENT_URI;
		return contentresolver.insert(uri, contentvalues);
	}

	static Calendar calculateAlarm(int i, int j, Alarm.DaysOfWeek daysofweek)
	{
		byte byte0 = 12;
		byte byte1 = 11;
		int k = 0;
		Calendar calendar = Calendar.getInstance();
		long l = System.currentTimeMillis();
		calendar.setTimeInMillis(<no variable>);
		int i1 = calendar.get(byte1);
		int j1 = calendar.get(byte0);
		if (i < i1 || i == i1 && j <= j1)
			calendar.add(6, 1);
		calendar.set(byte1, i);
		calendar.set(byte0, j);
		calendar.set(13, k);
		calendar.set(14, k);
		int k1 = daysofweek.getNextAlarm(calendar);
		if (k1 > 0)
			calendar.add(7, k1);
		return calendar;
	}

	public static Alarm calculateNextAlert(Context context)
	{
		Object obj;
		long l;
		Cursor cursor;
		obj = 0;
		l = 0x7fffffffffffffffL;
		long l1 = System.currentTimeMillis();
		cursor = getFilteredAlarmsCursor(context.getContentResolver());
		if (cursor == null) goto _L2; else goto _L1
_L1:
		if (!cursor.moveToFirst()) goto _L4; else goto _L3
_L3:
		Alarm alarm = new Alarm(cursor);
		if (alarm.time != 0L) goto _L6; else goto _L5
_L5:
		int i = alarm.hour;
		int j = alarm.minutes;
		Alarm.DaysOfWeek daysofweek = alarm.daysOfWeek;
		long l2 = calculateAlarm(i, j, daysofweek).getTimeInMillis();
		alarm.time = <no variable>;
_L8:
		if (alarm.time < l)
		{
			l = alarm.time;
			obj = alarm;
		}
_L9:
		if (cursor.moveToNext()) goto _L3; else goto _L4
_L4:
		cursor.close();
_L2:
		return ((Alarm) (obj));
_L6:
		if (alarm.time >= <no variable>) goto _L8; else goto _L7
_L7:
		enableAlarmInternal(context, alarm, null);
		  goto _L9
	}

	private static void clearSnoozePreference(android.content.SharedPreferences.Editor editor)
	{
		editor.remove("snooze_id");
		editor.remove("snooze_time");
		editor.commit();
	}

	public static void deleteAlarm(Context context, int i)
	{
		ContentResolver contentresolver = context.getContentResolver();
		disableSnoozeAlert(context, i);
		Uri uri = Alarm.Columns.CONTENT_URI;
		long l = i;
		Uri uri1 = ContentUris.withAppendedId(uri, l);
		contentresolver.delete(uri1, "", null);
		setNextAlert(context);
	}

	static void disableAlert(Context context)
	{
		AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
		Intent intent = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
		PendingIntent pendingintent = PendingIntent.getBroadcast(context, 0, intent, 0x10000000);
		alarmmanager.cancel(pendingintent);
		setStatusBarIcon(context, null);
		saveNextAlarm(context, "");
	}

	public static void disableExpiredAlarms(Context context)
	{
		Cursor cursor = getFilteredAlarmsCursor(context.getContentResolver());
		long l = System.currentTimeMillis();
		if (cursor.moveToFirst())
			do
			{
				Alarm alarm = new Alarm(cursor);
				if (alarm.time != 0L && alarm.time < <no variable>)
					enableAlarmInternal(context, alarm, null);
			} while (cursor.moveToNext());
		cursor.close();
	}

	static void disableSnoozeAlert(Context context, int i)
	{
		SharedPreferences sharedpreferences;
		int j;
		sharedpreferences = context.getSharedPreferences("AlarmClock", 0);
		j = sharedpreferences.getInt("snooze_id", -1);
		break MISSING_BLOCK_LABEL_19;
		if (j != -1 && j == i)
			clearSnoozePreference(sharedpreferences.edit());
		return;
	}

	public static void enableAlarm(Context context, int i, boolean flag)
	{
		enableAlarmInternal(context, i, flag);
		setNextAlert(context);
	}

	private static void enableAlarmInternal(Context context, int i, boolean flag)
	{
		Alarm alarm = getAlarm(context.getContentResolver(), i);
		enableAlarmInternal(context, alarm, flag);
	}

	private static void enableAlarmInternal(Context context, Alarm alarm, boolean flag)
	{
		String as[] = null;
		ContentResolver contentresolver = context.getContentResolver();
		ContentValues contentvalues = new ContentValues(2);
		String s = "enabled";
		Integer integer;
		Integer integer1;
		Uri uri;
		long l2;
		Uri uri1;
		if (flag)
			integer = 1;
		else
			integer = null;
		integer1 = Integer.valueOf(integer);
		contentvalues.put(s, integer);
		if (flag)
		{
			long l = 0L;
			if (!alarm.daysOfWeek.isRepeatSet())
			{
				int i = alarm.hour;
				int j = alarm.minutes;
				Alarm.DaysOfWeek daysofweek = alarm.daysOfWeek;
				long l1 = calculateAlarm(i, j, daysofweek).getTimeInMillis();
			}
			Long long1 = Long.valueOf(l);
			contentvalues.put("alarmtime", long1);
		}
		uri = Alarm.Columns.CONTENT_URI;
		l2 = alarm.id;
		uri1 = ContentUris.withAppendedId(uri, l2);
		contentresolver.update(uri1, contentvalues, as, as);
	}

	private static void enableAlert(Context context, Alarm alarm, long l)
	{
		AlarmManager alarmmanager = (AlarmManager)context.getSystemService("alarm");
		Intent intent = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
		Parcel parcel = Parcel.obtain();
		alarm.writeToParcel(parcel, 0);
		parcel.setDataPosition(0);
		byte abyte0[] = parcel.marshall();
		intent.putExtra("intent.extra.alarm_raw", abyte0);
		PendingIntent pendingintent = PendingIntent.getBroadcast(context, 0, intent, 0x10000000);
		alarmmanager.set(0, l, pendingintent);
		setStatusBarIcon(context, true);
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(l);
		calendar.setTime(date);
		String s = formatDayAndTime(context, calendar);
		saveNextAlarm(context, s);
	}

	private static boolean enableSnoozeAlert(Context context)
	{
		int i = 0;
		SharedPreferences sharedpreferences = context.getSharedPreferences("AlarmClock", i);
		String s = "snooze_id";
		int j = sharedpreferences.getInt(s, -1);
		boolean flag;
		if (j == -1)
		{
			flag = i;
		} else
		{
			long l = sharedpreferences.getLong("snooze_time", 65535L);
			Alarm alarm = getAlarm(context.getContentResolver(), j);
			alarm.time = <no variable>;
			enableAlert(context, alarm, <no variable>);
			flag = true;
		}
		return flag;
	}

	private static String formatDayAndTime(Context context, Calendar calendar)
	{
		boolean flag = get24HourMode(context);
		String s1;
		String s2;
		if (flag)
		{
			String s = "E k:mm";
			s2 = s;
		} else
		{
			s1 = "E h:mm aa";
			s2 = s1;
		}
		if (calendar == null)
			s1 = "";
		else
			s1 = (String)DateFormat.format(s2, calendar);
		return s1;
	}

	static String formatTime(Context context, int i, int j, Alarm.DaysOfWeek daysofweek)
	{
		Calendar calendar = calculateAlarm(i, j, daysofweek);
		return formatTime(context, calendar);
	}

	static String formatTime(Context context, Calendar calendar)
	{
		boolean flag = get24HourMode(context);
		String s1;
		String s2;
		if (flag)
		{
			String s = "kk:mm";
			s2 = s;
		} else
		{
			s1 = "h:mm aa";
			s2 = s1;
		}
		if (calendar == null)
			s1 = "";
		else
			s1 = (String)DateFormat.format(s2, calendar);
		return s1;
	}

	static boolean get24HourMode(Context context)
	{
		return DateFormat.is24HourFormat(context);
	}

	public static Alarm getAlarm(ContentResolver contentresolver, int i)
	{
		Uri uri = Alarm.Columns.CONTENT_URI;
		long l = i;
		Uri uri1 = ContentUris.withAppendedId(uri, l);
		String as[] = Alarm.Columns.ALARM_QUERY_COLUMNS;
		ContentResolver contentresolver1 = contentresolver;
		String as1[] = null;
		String s = null;
		Cursor cursor = contentresolver1.query(uri1, as, null, as1, s);
		Object obj = 0;
		if (cursor != null)
		{
			if (cursor.moveToFirst())
				obj = new Alarm(cursor);
			cursor.close();
		}
		return ((Alarm) (obj));
	}

	public static Cursor getAlarmsCursor(ContentResolver contentresolver)
	{
		Uri uri = Alarm.Columns.CONTENT_URI;
		String as[] = Alarm.Columns.ALARM_QUERY_COLUMNS;
		ContentResolver contentresolver1 = contentresolver;
		String as1[] = null;
		return contentresolver1.query(uri, as, null, as1, "_id ASC");
	}

	private static Cursor getFilteredAlarmsCursor(ContentResolver contentresolver)
	{
		Uri uri = Alarm.Columns.CONTENT_URI;
		String as[] = Alarm.Columns.ALARM_QUERY_COLUMNS;
		ContentResolver contentresolver1 = contentresolver;
		String s = null;
		return contentresolver1.query(uri, as, "enabled=1", null, s);
	}

	static void saveNextAlarm(Context context, String s)
	{
		android.provider.Settings.System.putString(context.getContentResolver(), "next_alarm_formatted", s);
	}

	static void saveSnoozeAlert(Context context, int i, long l)
	{
		android.content.SharedPreferences.Editor editor = context.getSharedPreferences("AlarmClock", 0).edit();
		if (i == -1)
		{
			clearSnoozePreference(editor);
		} else
		{
			editor.putInt("snooze_id", i);
			editor.putLong("snooze_time", l);
			editor.commit();
		}
		setNextAlert(context);
	}

	public static void setAlarm(Context context, int i, boolean flag, int j, int k, Alarm.DaysOfWeek daysofweek, boolean flag1, String s, 
			String s1, boolean flag2)
	{
		ContentValues contentvalues = new ContentValues(8);
		ContentResolver contentresolver = context.getContentResolver();
		long l = 0L;
		boolean flag3 = daysofweek.isRepeatSet();
		long l1;
		if (!flag3)
			l1 = calculateAlarm(j, k, daysofweek).getTimeInMillis();
		String s2 = "enabled";
		Integer integer;
		Integer integer1;
		Integer integer2;
		Long long1;
		Integer integer3;
		Boolean boolean1;
		Boolean boolean2;
		Uri uri;
		long l2;
		Uri uri1;
		if (flag)
			flag = true;
		else
			flag = null;
		integer = Integer.valueOf(flag);
		contentvalues.put(s2, flag);
		integer1 = Integer.valueOf(j);
		contentvalues.put("hour", j);
		integer2 = Integer.valueOf(k);
		contentvalues.put("minutes", j);
		long1 = Long.valueOf(l);
		contentvalues.put("alarmtime", j);
		integer3 = Integer.valueOf(daysofweek.getCoded());
		contentvalues.put("daysofweek", j);
		boolean1 = Boolean.valueOf(flag1);
		contentvalues.put("vibrate", j);
		contentvalues.put("message", s);
		contentvalues.put("alert", s1);
		boolean2 = Boolean.valueOf(flag2);
		contentvalues.put("waitable", j);
		uri = Alarm.Columns.CONTENT_URI;
		l2 = i;
		uri1 = ContentUris.withAppendedId(flag, j);
		contentresolver.update(i, contentvalues, null, null);
		setNextAlert(context);
	}

	public static void setNextAlert(Context context)
	{
		if (!enableSnoozeAlert(context))
		{
			Alarm alarm = calculateNextAlert(context);
			if (alarm != null)
			{
				long l = alarm.time;
				enableAlert(context, alarm, l);
			} else
			{
				disableAlert(context);
			}
		}
	}

	private static void setStatusBarIcon(Context context, boolean flag)
	{
		Intent intent = new Intent("android.intent.action.ALARM_CHANGED");
		intent.putExtra("alarmSet", flag);
		context.sendBroadcast(intent);
	}
}
