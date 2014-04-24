// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TimeListAdapter.java

package com.aedesign.deskclock.ui.timeselector;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeListAdapter extends BaseAdapter
{

	private final int BLANK_COUNT = 5;
	private final boolean DEBUG = null;
	private final String TAG = "TimeListAdapter";
	private int mItemResid;
	private final LayoutInflater mLayoutInflater;
	private final String mStrings[];

	public TimeListAdapter(Context context, String as[], int i)
	{
		LayoutInflater layoutinflater = LayoutInflater.from(context);
		mLayoutInflater = layoutinflater;
		mStrings = as;
		mItemResid = i;
	}

	public int getBlankCount()
	{
		return 5;
	}

	public int getCount()
	{
		int i = mStrings.length;
		int j;
		j += 10;
		return i;
	}

	public Object getItem(int i)
	{
		byte byte0 = 5;
		int j = mStrings.length;
		j += 5;
		Object obj;
		if (i >= j || i < byte0)
		{
			obj = "";
		} else
		{
			obj = mStrings;
			int k = i - byte0;
			obj = obj[byte0];
		}
		return obj;
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		byte byte0 = 5;
		TextView textview;
		int k;
		int l;
		if (view == null)
		{
			LayoutInflater layoutinflater = mLayoutInflater;
			int j = mItemResid;
			textview = (TextView)layoutinflater.inflate(j, viewgroup, null);
		} else
		{
			textview = (TextView)view;
		}
		k = mStrings.length;
		l += 5;
		if (i >= k || i < byte0)
		{
			textview.setText("");
		} else
		{
			String as[] = mStrings;
			int i1 = i - byte0;
			String s = as[i1];
			textview.setText(s);
		}
		return textview;
	}
}
