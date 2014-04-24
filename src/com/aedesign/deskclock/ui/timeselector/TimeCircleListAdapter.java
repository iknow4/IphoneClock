// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TimeCircleListAdapter.java

package com.aedesign.deskclock.ui.timeselector;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeCircleListAdapter extends BaseAdapter
{
	public interface OnGetViewListener
	{

		public abstract void onGetViewListener();
	}


	private final boolean DEBUG = false;
	private final String TAG = "TimeCircleListAdapter";
	private OnGetViewListener listener;
	private int mItemResid;
	private final LayoutInflater mLayoutInflater;
	private final String mStrings[];

	public TimeCircleListAdapter(Context context, String as[], int i)
	{
		LayoutInflater layoutinflater = LayoutInflater.from(context);
		mLayoutInflater = layoutinflater;
		mStrings = as;
		mItemResid = i;
	}

	public int getCount()
	{
		return 0x7fffffff;
	}

	public Object getItem(int i)
	{
		return mStrings[i];
	}

	public long getItemId(int i)
	{
		return (long)i;
	}

	public int getStringsCount()
	{
		return mStrings.length;
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		int j;
		CharSequence charsequence;
		int l;
		TextView textview;
		int i1;
		String s;
		if (view == null)
		{
			LayoutInflater layoutinflater = mLayoutInflater;
			int k = mItemResid;
			textview = (TextView)layoutinflater.inflate(k, viewgroup, false);
		} else
		{
			textview = (TextView)view;
		}
		j = getCount() / 2;
		j = i - j;
		l = mStrings.length;
		i1 = j % l;
		charsequence = mStrings;
		if (i1 < 0)
			l = mStrings.length + i1;
		else
			l = i1;
		s = charsequence[l];
		textview.setText(charsequence);
		if (listener != null)
			listener.onGetViewListener();
		return textview;
	}

	public void setOnGetViewListener(OnGetViewListener ongetviewlistener)
	{
		listener = ongetviewlistener;
	}
}
