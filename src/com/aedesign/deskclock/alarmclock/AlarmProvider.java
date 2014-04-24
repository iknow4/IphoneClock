// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmProvider.java

package com.aedesign.deskclock.alarmclock;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

public class AlarmProvider extends ContentProvider
{
	class DatabaseHelper extends SQLiteOpenHelper
	{

		private static final String DATABASE_NAME = "aedesignalarms.db";
		private static final int DATABASE_VERSION = 5;

		public void onCreate(SQLiteDatabase sqlitedatabase)
		{
			sqlitedatabase.execSQL("CREATE TABLE alarms (_id INTEGER PRIMARY KEY,hour INTEGER, minutes INTEGER, daysofweek INTEGER, alarmtime INTEGER, enabled INTEGER, vibrate INTEGER, message TEXT, alert TEXT, waitable INTEGER);");
			String s = (new StringBuilder()).append("INSERT INTO alarms (hour, minutes, daysofweek, alarmtime, enabled, vibrate, message, alert, waitable) VALUES ").append("(7, 0, 127, 0, 0, 1, '', '', 0);").toString();
			sqlitedatabase.execSQL(s);
			String s1 = (new StringBuilder()).append("INSERT INTO alarms (hour, minutes, daysofweek, alarmtime, enabled, vibrate, message, alert, waitable) VALUES ").append("(8, 30, 31, 0, 0, 1, '', '', 0);").toString();
			sqlitedatabase.execSQL(s1);
			String s2 = (new StringBuilder()).append("INSERT INTO alarms (hour, minutes, daysofweek, alarmtime, enabled, vibrate, message, alert, waitable) VALUES ").append("(9, 00, 0, 0, 0, 1, '', '', 0);").toString();
			sqlitedatabase.execSQL(s2);
		}

		public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
		{
			sqlitedatabase.execSQL("DROP TABLE IF EXISTS alarms");
			onCreate(sqlitedatabase);
		}

		public DatabaseHelper(Context context)
		{
			super(context, "aedesignalarms.db", null, 5);
		}
	}


	private static final int ALARMS = 1;
	private static final int ALARMS_ID = 2;
	private static final UriMatcher sURLMatcher;
	private SQLiteOpenHelper mOpenHelper;

	public AlarmProvider()
	{
	}

	public int delete(Uri uri, String s, String as[])
	{
		String s1;
		String s2;
		SQLiteDatabase sqlitedatabase;
		s1 = "alarms";
		s2 = "_id=";
		sqlitedatabase = mOpenHelper.getWritableDatabase();
		sURLMatcher.match(uri);
		JVM INSTR tableswitch 1 2: default 48
	//	               1 79
	//	               2 105;
		   goto _L1 _L2 _L3
_L1:
		String s3 = (new StringBuilder()).append("Cannot delete from URL: ").append(uri).toString();
		throw new IllegalArgumentException(s3);
_L2:
		int i = sqlitedatabase.delete(s1, s, as);
_L5:
		getContext().getContentResolver().notifyChange(uri, null);
		return i;
_L3:
		String s4 = (String)uri.getPathSegments().get(1);
		long l = Long.parseLong(s4);
		if (TextUtils.isEmpty(s))
			s = (new StringBuilder()).append(s2).append(s4).toString();
		else
			s = (new StringBuilder()).append(s2).append(s4).append(" AND (").append(s).append(")").toString();
		i = sqlitedatabase.delete(s1, s, as);
		if (true) goto _L5; else goto _L4
_L4:
	}

	public String getType(Uri uri)
	{
		Object obj = sURLMatcher;
		((UriMatcher) (obj)).match(uri);
		JVM INSTR tableswitch 1 2: default 32
	//	               1 42
	//	               2 47;
		   goto _L1 _L2 _L3
_L1:
		throw new IllegalArgumentException("Unknown URL");
_L2:
		obj = "vnd.android.cursor.dir/alarms";
_L5:
		return ((String) (obj));
_L3:
		obj = "vnd.android.cursor.item/alarms";
		if (true) goto _L5; else goto _L4
_L4:
	}

	public Uri insert(Uri uri, ContentValues contentvalues)
	{
		int i = 1;
		int j = 0;
		String s = "alarmtime";
		String s1 = "";
		String s2 = "message";
		if (sURLMatcher.match(uri) != i)
		{
			String s3 = (new StringBuilder()).append("Cannot insert into URL: ").append(uri).toString();
			throw new IllegalArgumentException(s3);
		}
		ContentValues contentvalues1;
		long l;
		if (contentvalues != null)
			contentvalues1 = new ContentValues(contentvalues);
		else
			contentvalues1 = new ContentValues();
		if (!contentvalues1.containsKey("hour"))
		{
			Integer integer = Integer.valueOf(j);
			contentvalues1.put("hour", integer);
		}
		if (!contentvalues1.containsKey("minutes"))
		{
			Integer integer1 = Integer.valueOf(j);
			contentvalues1.put("minutes", integer1);
		}
		if (!contentvalues1.containsKey("daysofweek"))
		{
			Integer integer2 = Integer.valueOf(j);
			contentvalues1.put("daysofweek", integer2);
		}
		if (!contentvalues1.containsKey(s))
		{
			Integer integer3 = Integer.valueOf(j);
			contentvalues1.put(s, integer3);
		}
		if (!contentvalues1.containsKey("enabled"))
		{
			Integer integer4 = Integer.valueOf(j);
			contentvalues1.put("enabled", integer4);
		}
		if (!contentvalues1.containsKey("vibrate"))
		{
			Integer integer5 = Integer.valueOf(i);
			contentvalues1.put("vibrate", integer5);
		}
		if (!contentvalues1.containsKey(s2))
			contentvalues1.put(s2, s1);
		if (!contentvalues1.containsKey("alert"))
			contentvalues1.put("alert", s1);
		if (!contentvalues1.containsKey("waitable"))
		{
			Integer integer6 = Integer.valueOf(j);
			contentvalues1.put("waitable", integer6);
		}
		l = mOpenHelper.getWritableDatabase().insert("alarms", s2, contentvalues1);
		if (<no variable> < 0L)
		{
			String s4 = (new StringBuilder()).append("Failed to insert row into ").append(uri).toString();
			throw new SQLException(s4);
		} else
		{
			Uri uri1 = ContentUris.withAppendedId(Alarm.Columns.CONTENT_URI, <no variable>);
			getContext().getContentResolver().notifyChange(uri1, null);
			return uri1;
		}
	}

	public boolean onCreate()
	{
		Context context = getContext();
		DatabaseHelper databasehelper = new DatabaseHelper(context);
		mOpenHelper = databasehelper;
		return true;
	}

	public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
	{
		String s2;
		String s3;
		SQLiteQueryBuilder sqlitequerybuilder;
		s2 = null;
		s3 = "alarms";
		sqlitequerybuilder = new SQLiteQueryBuilder();
		switch (sURLMatcher.match(uri))
		{
		default:
			String s4 = (new StringBuilder()).append("Unknown URL ").append(uri).toString();
			throw new IllegalArgumentException(s4);

		case 2: // '\002'
			break MISSING_BLOCK_LABEL_138;

		case 1: // '\001'
			sqlitequerybuilder.setTables(s3);
			break;
		}
_L1:
		SQLiteDatabase sqlitedatabase = mOpenHelper.getReadableDatabase();
		String as2[] = as;
		String s5 = s;
		String as3[] = as1;
		String s6 = s2;
		String s7 = s1;
		Cursor cursor = sqlitequerybuilder.query(sqlitedatabase, as2, s5, as3, s2, s6, s7);
		CharSequence charsequence;
		if (cursor != null)
		{
			ContentResolver contentresolver = getContext().getContentResolver();
			cursor.setNotificationUri(contentresolver, uri);
		}
		return cursor;
		sqlitequerybuilder.setTables(s3);
		sqlitequerybuilder.appendWhere("_id=");
		charsequence = (CharSequence)uri.getPathSegments().get(1);
		sqlitequerybuilder.appendWhere(charsequence);
		  goto _L1
	}

	public int update(Uri uri, ContentValues contentvalues, String s, String as[])
	{
		android.database.ContentObserver contentobserver = null;
		int i = sURLMatcher.match(uri);
		SQLiteDatabase sqlitedatabase = mOpenHelper.getWritableDatabase();
		long l;
		switch (i)
		{
		default:
			String s1 = (new StringBuilder()).append("Cannot update URL: ").append(uri).toString();
			throw new UnsupportedOperationException(s1);

		case 2: // '\002'
			l = Long.parseLong((String)uri.getPathSegments().get(1));
			break;
		}
		String s2 = (new StringBuilder()).append("_id=").append(<no variable>).toString();
		int j = sqlitedatabase.update("alarms", contentvalues, s2, contentobserver);
		getContext().getContentResolver().notifyChange(uri, contentobserver);
		return j;
	}

	static 
	{
		sURLMatcher = new UriMatcher(-1);
		sURLMatcher.addURI("com.aedesign.alarmclock", "alarm", 1);
		sURLMatcher.addURI("com.aedesign.alarmclock", "alarm/#", 2);
	}
}
