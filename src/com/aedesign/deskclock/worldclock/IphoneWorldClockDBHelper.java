// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneWorldClockDBHelper.java

package com.aedesign.deskclock.worldclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class IphoneWorldClockDBHelper
{

	private static final boolean DEBUG = false;
	private static final String DataBaseName = "AedesignWorldclock.db";
	private static final String TABLE_NAME = "clockdata";
	private static final String TAG = "IphoneWorldClockDBHelper";
	private SQLiteDatabase db;

	public IphoneWorldClockDBHelper(Context context)
	{
		open(context);
	}

	private void createTable()
	{
		db.execSQL("CREATE TABLE IF NOT EXISTS clockdata    (_id INTEGER PRIMARY KEY autoincrement,  area TEXT, timezone TEXT, position INTEGER)");
_L2:
		return;
		throw ;
		Exception exception;
		exception;
		if (true) goto _L2; else goto _L1
_L1:
	}

	public void close()
	{
		db.close();
	}

	public void deleteById(int i)
	{
		String s = (new StringBuilder()).append("delete from clockdata where _id=").append(i).toString();
		db.execSQL(s);
	}

	public Cursor loadAll()
	{
		SQLiteDatabase sqlitedatabase = db;
		String as[] = new String[4];
		as[0] = "_id";
		as[1] = "area";
		as[2] = "timezone";
		as[3] = "position";
		String as1[] = null;
		String s = null;
		String s1 = null;
		return sqlitedatabase.query("clockdata", as, null, as1, s, s1, "position");
	}

	public void open(Context context)
	{
		if (db == null || !db.isOpen())
		{
			SQLiteDatabase sqlitedatabase = context.openOrCreateDatabase("AedesignWorldclock.db", 0, null);
			db = sqlitedatabase;
			createTable();
		}
	}

	public boolean save(String s, String s1, int i)
	{
		String s2;
		StringBuilder stringbuilder = (new StringBuilder()).append("insert into clockdata values(null,'").append(s).append("','").append(s1).append("','").append(i).append("')");
		s2 = stringbuilder.toString();
		SQLiteDatabase sqlitedatabase = db;
		sqlitedatabase.execSQL(s2);
		boolean flag = true;
_L2:
		return flag;
		Exception exception;
		exception;
		flag = null;
		if (true) goto _L2; else goto _L1
_L1:
		throw ;
	}

	public boolean updateByPosition(int i, int j)
	{
		ContentValues contentvalues = new ContentValues();
		Integer integer = Integer.valueOf(j);
		contentvalues.put("position", integer);
		SQLiteDatabase sqlitedatabase = db;
		String s = (new StringBuilder()).append("_id=").append(i).toString();
		int k = sqlitedatabase.update("clockdata", contentvalues, s, null);
		if (k > 0)
			k = 1;
		else
			k = null;
		return k;
	}
}
