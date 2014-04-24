// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TimeCircleListView.java

package com.aedesign.deskclock.ui.timeselector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

// Referenced classes of package com.aedesign.deskclock.ui.timeselector:
//			TimeCircleListAdapter, OnAdjustPositonListener

public class TimeCircleListView extends ListView
	implements android.widget.AbsListView.OnScrollListener, android.widget.AdapterView.OnItemClickListener
{

	private final boolean DEBUG;
	private final String TAG;
	private boolean isScrollDown;
	private TimeCircleListAdapter mAdapter;
	private int mAdjustOffsetItemCount;
	private int mAdjustOffsetY;
	private float mBeforeY;
	private int mBeginPosition;
	private float mBeginY;
	private int mCurrentPosition;
	private float mCurrentY;
	private android.widget.AdapterView.OnItemClickListener mItemClickListener;
	private OnAdjustPositonListener mOnAdjustPositonListener;
	private android.widget.AbsListView.OnScrollListener mScrollListener;
	private TextView tv;

	public TimeCircleListView(Context context)
	{
		super(context);
		DEBUG = false;
		TAG = "TimeCircleListView";
	}

	public TimeCircleListView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		DEBUG = false;
		TAG = "TimeCircleListView";
	}

	public TimeCircleListView(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		DEBUG = false;
		TAG = "TimeCircleListView";
	}

	private String getTimeOnPosition(int i)
	{
		int j = mBeginPosition;
		j = i - j;
		int k = mAdapter.getStringsCount();
		int l = j % k;
		TimeCircleListAdapter timecirclelistadapter = mAdapter;
		if (l < 0)
			k = mAdapter.getStringsCount() + l;
		else
			k = l;
		return (String)timecirclelistadapter.getItem(k);
	}

	private void reAdjustPosition()
	{
		int i = getFirstVisiblePosition();
		int j = mAdjustOffsetItemCount;
		int k = i + j;
		mCurrentPosition = k;
		if (!isScrollDown)
		{
			int l = mCurrentPosition;
			int i1;
			i1++;
			mCurrentPosition = l;
		}
		int j1 = mCurrentPosition;
		int k1 = mAdjustOffsetY;
		setSelectionFromTop(j1, k1);
		computeScroll();
	}

	public String getSelectionString()
	{
		int i = getFirstVisiblePosition();
		int j = mAdjustOffsetItemCount;
		int k = i + j;
		return getTimeOnPosition(k);
	}

	public String getTimeOn()
	{
		int i = mCurrentPosition;
		return getTimeOnPosition(i);
	}

	public void init(TimeCircleListAdapter timecirclelistadapter, int i, int j, int k)
	{
		Integer integer = Integer.valueOf(i);
		setTag(integer);
		setAdapter(timecirclelistadapter);
		mAdapter = timecirclelistadapter;
		mAdjustOffsetY = j;
		mAdjustOffsetItemCount = k;
		setHorizontalScrollBarEnabled(null);
		setVerticalScrollBarEnabled(null);
		int l = timecirclelistadapter.getCount() / 2;
		mCurrentPosition = l;
		mBeginPosition = l;
		int i1 = mCurrentPosition;
		int j1 = mAdjustOffsetY;
		setSelectionFromTop(i1, j1);
		super.setOnScrollListener(this);
		super.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView adapterview, View view, int i, long l)
	{
		mCurrentPosition = i;
		int j = mAdjustOffsetY;
		setSelectionFromTop(i, j);
		if (mItemClickListener != null)
		{
			android.widget.AdapterView.OnItemClickListener onitemclicklistener = mItemClickListener;
			AdapterView adapterview1 = adapterview;
			View view1 = view;
			int k = i;
			long l1 = l;
			onitemclicklistener.onItemClick(adapterview1, view1, k, l1);
		}
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		if (mScrollListener != null)
			mScrollListener.onScroll(abslistview, i, j, k);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
		reAdjustPosition();
		if (mScrollListener != null)
			mScrollListener.onScrollStateChanged(abslistview, i);
	}

	public boolean onTouchEvent(MotionEvent motionevent)
	{
		boolean flag = super.onTouchEvent(motionevent);
		motionevent.getAction();
		JVM INSTR tableswitch 0 3: default 40
	//	               0 67
	//	               1 42
	//	               2 91
	//	               3 42;
		   goto _L1 _L2 _L3 _L4 _L3
_L1:
		return flag;
_L3:
		float f = mBeginY;
		float f1 = mCurrentY;
		if (f != f1)
			reAdjustPosition();
		continue; /* Loop/switch isn't completed */
_L2:
		float f2 = motionevent.getY();
		mCurrentY = f2;
		mBeforeY = f2;
		mBeginY = f2;
_L4:
		float f3 = motionevent.getY();
		mCurrentY = f3;
		f3 = mCurrentY;
		float f4 = mBeforeY;
		f3 !== f4;
		float f5;
		if (f3 > 0)
			f3 = 1;
		else
			f3 = null;
		isScrollDown = f3;
		f5 = mCurrentY;
		mBeforeY = f3;
		if (true) goto _L1; else goto _L5
_L5:
	}

	public void setNextPosition()
	{
		int i = mCurrentPosition;
		int j;
		j++;
		mCurrentPosition = i;
		int k = mCurrentPosition;
		int l = mAdjustOffsetY;
		setSelectionFromTop(k, l);
	}

	public void setOnAdjustPositionListener(OnAdjustPositonListener onadjustpositonlistener)
	{
		mOnAdjustPositonListener = onadjustpositonlistener;
	}

	public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener onitemclicklistener)
	{
		mItemClickListener = onitemclicklistener;
	}

	public void setOnScrollListener(android.widget.AbsListView.OnScrollListener onscrolllistener)
	{
		mScrollListener = onscrolllistener;
	}

	public void setSelectionFromTop(int i, int j)
	{
		super.setSelectionFromTop(i, j);
		if (mOnAdjustPositonListener != null)
			mOnAdjustPositonListener.onAdjuestPosition(this);
	}

	public void setTextView(TextView textview)
	{
		tv = textview;
	}

	public boolean setTimeOnPositon(int i)
	{
		int j = mBeginPosition + i;
		mCurrentPosition = j;
		int k = mCurrentPosition;
		int l = mAdjustOffsetY;
		setSelectionFromTop(k, l);
		return true;
	}
}
