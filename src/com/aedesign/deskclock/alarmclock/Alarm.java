// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Alarm.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public final class Alarm
	implements Parcelable
{
	final class DaysOfWeek
	{

		private static int DAY_MAP[] = {
			2, 3, 4, 5, 6, 7, 1
		};
		private int mDays;

		private boolean isSet(int i)
		{
			int j = 1;
			int k = mDays;
			int l = j << i;
			k &= l;
			if (k > 0)
				k = j;
			else
				k = null;
			return k;
		}

		public boolean[] getBooleanArray()
		{
			byte byte0 = 7;
			boolean aflag[] = new boolean[byte0];
			for (int i = 0; i < byte0; i++)
			{
				boolean flag = isSet(i);
				aflag[i] = flag;
			}

			return aflag;
		}

		public int getCoded()
		{
			return mDays;
		}

		public int getNextAlarm(Calendar calendar)
		{
			byte byte0;
			int i;
			byte0 = 7;
			i = mDays;
			if (i != 0) goto _L2; else goto _L1
_L1:
			i = -1;
_L4:
			return i;
_L2:
			i = calendar.get(byte0);
			int j = (i += 5) % 7;
			int k = null;
			do
			{
label0:
				{
					if (k < byte0)
					{
						int l = (j + k) % 7;
						i = isSet(l);
						if (i == 0)
							break label0;
					}
					i = k;
				}
				if (true)
					continue;
				k++;
			} while (true);
			if (true) goto _L4; else goto _L3
_L3:
		}

		public boolean isRepeatSet()
		{
			int i = mDays;
			if (i != 0)
				i = 1;
			else
				i = null;
			return i;
		}

		public void set(int i, boolean flag)
		{
			int j = 1;
			if (flag)
			{
				int k = mDays;
				int l = j << i;
				int i1 = k | j;
				mDays = i1;
			} else
			{
				int j1 = mDays;
				int k1 = ~(j << i);
				int l1 = j1 & j;
				mDays = l1;
			}
		}

		public void set(DaysOfWeek daysofweek)
		{
			int i = daysofweek.mDays;
			mDays = i;
		}

		public String toString(Context context, boolean flag)
		{
			int i = 1;
			StringBuilder stringbuilder = new StringBuilder();
			int j = mDays;
			Object obj;
			if (j == 0)
			{
				if (flag)
					obj = context.getText(0x7f0a0058).toString();
				else
					obj = "";
			} else
			{
				obj = mDays;
				if (obj == 127)
				{
					obj = context.getText(0x7f0a0057).toString();
				} else
				{
					int k = null;
					for (int l = mDays; l > 0; l >>= 1)
					{
						obj = l & 1;
						if (obj == i)
							k++;
					}

					DateFormatSymbols dateformatsymbols = new DateFormatSymbols();
					String as[];
					int i1;
					if (k > i)
					{
						obj = dateformatsymbols.getShortWeekdays();
						as = ((String []) (obj));
					} else
					{
						obj = dateformatsymbols.getWeekdays();
						as = ((String []) (obj));
					}
					i1 = null;
					do
					{
						obj = 7;
						if (i1 >= obj)
							break;
						obj = mDays;
						int j1 = i << i1;
						obj &= j1;
						if (obj != 0)
						{
							obj = DAY_MAP[i1];
							obj = as[obj];
							stringbuilder.append(((String) (obj)));
							if (--k > 0)
							{
								obj = context.getText(0x7f0a0059);
								stringbuilder.append(((CharSequence) (obj)));
							}
						}
						i1++;
					} while (true);
					obj = stringbuilder.toString();
				}
			}
			return ((String) (obj));
		}


		DaysOfWeek(int i)
		{
			mDays = i;
		}
	}

	public class Columns
		implements BaseColumns
	{

		public static final int ALARM_ALERT_INDEX = 8;
		public static final int ALARM_DAYS_OF_WEEK_INDEX = 3;
		public static final int ALARM_ENABLED_INDEX = 5;
		public static final int ALARM_HOUR_INDEX = 1;
		public static final int ALARM_ID_INDEX = 0;
		public static final int ALARM_MESSAGE_INDEX = 7;
		public static final int ALARM_MINUTES_INDEX = 2;
		static final String ALARM_QUERY_COLUMNS[];
		public static final String ALARM_TIME = "alarmtime";
		public static final int ALARM_TIME_INDEX = 4;
		public static final int ALARM_VIBRATE_INDEX = 6;
		public static final int ALARM_WAITABLE_INDEX = 9;
		public static final String ALERT = "alert";
		public static final Uri CONTENT_URI = Uri.parse("content://com.aedesign.alarmclock/alarm");
		public static final String DAYS_OF_WEEK = "daysofweek";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";
		public static final String ENABLED = "enabled";
		public static final String HOUR = "hour";
		public static final String MESSAGE = "message";
		public static final String MINUTES = "minutes";
		public static final String VIBRATE = "vibrate";
		public static final String WAITABLE = "waitable";
		public static final String WHERE_ENABLED = "enabled=1";

		static 
		{
			String as[] = new String[10];
			as[0] = "_id";
			as[1] = "hour";
			as[2] = "minutes";
			as[3] = "daysofweek";
			as[4] = "alarmtime";
			as[5] = "enabled";
			as[6] = "vibrate";
			as[7] = "message";
			as[8] = "alert";
			as[9] = "waitable";
			ALARM_QUERY_COLUMNS = as;
		}

		public Columns()
		{
		}
	}


	public static final android.os.Parcelable.Creator CREATOR = new 1();
	public Uri alert;
	public DaysOfWeek daysOfWeek;
	public boolean enabled;
	public int hour;
	public int id;
	public String label;
	public int minutes;
	public boolean silent;
	public long time;
	public boolean vibrate;
	public boolean waitable;

	public Alarm()
	{
		DaysOfWeek daysofweek = new DaysOfWeek(0);
		daysOfWeek = daysofweek;
		Uri uri = RingtoneManager.getDefaultUri(4);
		alert = uri;
	}

	public Alarm(Cursor cursor)
	{
		byte byte0 = 4;
		int i = 0;
		int j = 1;
		String s = "silent";
		super();
		int k = cursor.getInt(i);
		id = k;
		k = cursor.getInt(5);
		Uri uri;
		int l;
		String s1;
		if (k == j)
			uri = j;
		else
			uri = i;
		enabled = uri;
		uri = cursor.getInt(j);
		hour = uri;
		uri = cursor.getInt(2);
		minutes = uri;
		l = cursor.getInt(3);
		uri = new DaysOfWeek(l);
		daysOfWeek = uri;
		uri = cursor.getLong(byte0);
		time = <no variable>;
		uri = cursor.getInt(6);
		if (uri == j)
			uri = j;
		else
			uri = i;
		vibrate = uri;
		uri = cursor.getString(7);
		label = uri;
		s1 = cursor.getString(8);
		uri = s.equals(s1);
		if (uri != 0)
		{
			silent = j;
		} else
		{
			uri = s.equals(s1);
			if (uri != 0)
			{
				uri = 0;
				alert = uri;
			} else
			{
				if (s1 != null)
				{
					uri = s1.length();
					if (uri != 0)
					{
						uri = Uri.parse(s1);
						alert = uri;
					}
				}
				uri = alert;
				if (uri == null)
				{
					uri = RingtoneManager.getDefaultUri(byte0);
					alert = uri;
				}
			}
		}
		uri = cursor.getInt(9);
		if (uri == j)
			uri = j;
		else
			uri = i;
		waitable = uri;
	}

	public Alarm(Parcel parcel)
	{
		Object obj = null;
		int i = 1;
		super();
		int j = parcel.readInt();
		id = j;
		j = parcel.readInt();
		int k;
		if (j == i)
			j = i;
		else
			j = obj;
		enabled = j;
		j = parcel.readInt();
		hour = j;
		j = parcel.readInt();
		minutes = j;
		k = parcel.readInt();
		j = new DaysOfWeek(k);
		daysOfWeek = j;
		j = parcel.readLong();
		time = <no variable>;
		j = parcel.readInt();
		if (j == i)
			j = i;
		else
			j = obj;
		vibrate = j;
		j = parcel.readString();
		label = j;
		j = (Uri)parcel.readParcelable(null);
		alert = j;
		j = parcel.readInt();
		if (j == i)
			j = i;
		else
			j = obj;
		waitable = j;
		j = parcel.readInt();
		if (j == i)
			j = i;
		else
			j = obj;
		silent = j;
	}

	public int describeContents()
	{
		return null;
	}

	public String getLabelOrDefault(Context context)
	{
		String s = label;
		if (s == null) goto _L2; else goto _L1
_L1:
		int i = label.length();
		if (i != 0) goto _L3; else goto _L2
_L2:
		i = context.getString(0x7f0a0047);
_L5:
		return i;
_L3:
		i = label;
		if (true) goto _L5; else goto _L4
_L4:
	}

	public void writeToParcel(Parcel parcel, int i)
	{
		boolean flag = true;
		boolean flag1 = false;
		int j = id;
		parcel.writeInt(j);
		boolean flag2 = enabled;
		long l;
		if (flag2)
			flag2 = flag;
		else
			flag2 = flag1;
		parcel.writeInt(flag2);
		flag2 = hour;
		parcel.writeInt(flag2);
		flag2 = minutes;
		parcel.writeInt(flag2);
		flag2 = daysOfWeek.getCoded();
		parcel.writeInt(flag2);
		l = time;
		parcel.writeLong(l);
		flag2 = vibrate;
		if (flag2 != 0)
			flag2 = flag;
		else
			flag2 = flag1;
		parcel.writeInt(flag2);
		flag2 = label;
		parcel.writeString(flag2);
		flag2 = alert;
		parcel.writeParcelable(flag2, i);
		flag2 = waitable;
		if (flag2 != 0)
			flag2 = flag;
		else
			flag2 = flag1;
		parcel.writeInt(flag2);
		flag2 = silent;
		if (flag2 != 0)
			flag2 = flag;
		else
			flag2 = flag1;
		parcel.writeInt(flag2);
	}


	private class 1
		implements android.os.Parcelable.Creator
	{

		public Alarm createFromParcel(Parcel parcel)
		{
			return new Alarm(parcel);
		}

		public volatile Object createFromParcel(Parcel parcel)
		{
			return createFromParcel(parcel);
		}

		public Alarm[] newArray(int i)
		{
			return new Alarm[i];
		}

		public volatile Object[] newArray(int i)
		{
			return newArray(i);
		}

		1()
		{
		}
	}

}
