// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TimeListView.java

package com.aedesign.deskclock.ui.timeselector;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

// Referenced classes of package com.aedesign.deskclock.ui.timeselector:
//			TimeListAdapter, OnAdjustPositonListener

public class TimeListView extends ListView
	implements android.widget.AdapterView.OnItemClickListener, android.widget.AbsListView.OnScrollListener
{

	private final boolean DEBUG;
	private final String TAG;
	private int mAdjustOffsetItemCount;
	private int mAdjustOffsetY;
	private float mBeforeY;
	private int mCurrentPosition;
	private Handler mHandler;
	private boolean mIsScrollDown;
	private android.widget.AdapterView.OnItemClickListener mItemClickListener;
	private OnAdjustPositonListener mOnAdjustPositonListener;
	private final Runnable mReAdjestHandle;
	private android.widget.AbsListView.OnScrollListener mScrollListener;
	private TimeListAdapter mTimeListAdapter;
	private TextView tv;

	public TimeListView(Context context)
	{
		super(context);
		DEBUG = null;
		TAG = "TimeListView";
		mIsScrollDown = true;
		1 1_1 = new 1();
		mReAdjestHandle = 1_1;
		2 2_1 = new 2();
		mHandler = 2_1;
	}

	public TimeListView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		DEBUG = null;
		TAG = "TimeListView";
		mIsScrollDown = true;
		1 1_1 = new 1();
		mReAdjestHandle = 1_1;
		2 2_1 = new 2();
		mHandler = 2_1;
	}

	public TimeListView(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		DEBUG = null;
		TAG = "TimeListView";
		mIsScrollDown = true;
		1 1_1 = new 1();
		mReAdjestHandle = 1_1;
		2 2_1 = new 2();
		mHandler = 2_1;
	}

	private void reAdjustPosition()
	{
		int i;
		int j;
		int k;
		int i1;
		i = mTimeListAdapter.getBlankCount();
		j = mTimeListAdapter.getCount();
		k = getFirstVisiblePosition();
		int l = mAdjustOffsetItemCount;
		k += l;
		if (!mIsScrollDown)
			k++;
		i1 = j - i;
		if (k < i1) goto _L2; else goto _L1
_L1:
		k = j - i - 1;
_L4:
		int j1 = k - i;
		mCurrentPosition = j1;
		int k1 = mAdjustOffsetY;
		setSelectionFromTop(k, k1);
		computeScroll();
		return;
_L2:
		if (k < i)
			k = i;
		if (true) goto _L4; else goto _L3
_L3:
	}

	private void setSelectedPosition(int i)
	{
		int j = mTimeListAdapter.getBlankCount();
		int k = i - j;
		int l = mTimeListAdapter.getCount();
		int i1 = mTimeListAdapter.getBlankCount();
		int j1 = l - i1;
		int k1 = i - j1;
		if (k >= 0 && k1 < 0)
		{
			mCurrentPosition = k;
			int l1 = mAdjustOffsetY;
			setSelectionFromTop(i, l1);
			computeScroll();
		}
	}

	public int getCurrentPosition()
	{
		return mCurrentPosition;
	}

	public String getTimeOn()
	{
		int i = mTimeListAdapter.getBlankCount();
		TimeListAdapter timelistadapter = mTimeListAdapter;
		int j = mCurrentPosition + i;
		return (String)timelistadapter.getItem(j);
	}

	public void init(TimeListAdapter timelistadapter, int i, int j, int k)
	{
		Integer integer = Integer.valueOf(i);
		setTag(integer);
		setAdapter(timelistadapter);
		mTimeListAdapter = timelistadapter;
		mAdjustOffsetItemCount = k;
		mAdjustOffsetY = j;
		setHorizontalScrollBarEnabled(null);
		setVerticalScrollBarEnabled(null);
		int l = timelistadapter.getBlankCount();
		setSelectionFromTop(l, j);
		super.setOnScrollListener(this);
		super.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView adapterview, View view, int i, long l)
	{
		setSelectedPosition(i);
		if (mItemClickListener != null)
		{
			android.widget.AdapterView.OnItemClickListener onitemclicklistener = mItemClickListener;
			AdapterView adapterview1 = adapterview;
			View view1 = view;
			int j = i;
			long l1 = l;
			onitemclicklistener.onItemClick(adapterview1, view1, j, l1);
		}
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		int l = i;
		int i1 = mTimeListAdapter.getBlankCount();
		int j1 = mAdjustOffsetItemCount;
		l += j1;
		if (!mIsScrollDown)
			l++;
		int k1 = k - i1;
		if (l >= k1 || l < i1)
		{
			Runnable runnable = mReAdjestHandle;
			removeCallbacks(runnable);
			Runnable runnable1 = mReAdjestHandle;
			postDelayed(runnable1, 200L);
		}
		if (mScrollListener != null)
			mScrollListener.onScroll(abslistview, i, j, k);
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
		if (i == 0)
		{
			Runnable runnable = mReAdjestHandle;
			removeCallbacks(runnable);
			Runnable runnable1 = mReAdjestHandle;
			postDelayed(runnable1, 200L);
		}
		if (mScrollListener != null)
			mScrollListener.onScrollStateChanged(abslistview, i);
	}

	public boolean onTouchEvent(MotionEvent motionevent)
	{
		boolean flag = super.onTouchEvent(motionevent);
		int i = motionevent.getAction();
		float f = motionevent.getY();
		float f1 = mBeforeY;
		f1 = f != f1;
		if (f1 > 0)
			f1 = 1;
		else
			f1 = null;
		mIsScrollDown = f1;
		mBeforeY = f;
		i;
		JVM INSTR tableswitch 1 3: default 76
	//	               1 84
	//	               2 76
	//	               3 84;
		   goto _L1 _L2 _L1 _L2
_L1:
		return flag;
_L2:
		Runnable runnable = mReAdjestHandle;
		removeCallbacks(runnable);
		Runnable runnable1 = mReAdjestHandle;
		postDelayed(runnable1, 200L);
		if (true) goto _L1; else goto _L3
_L3:
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

	public void setTimeOnPositon(int i)
	{
		mCurrentPosition = i;
		mHandler.sendEmptyMessageDelayed(i, 30L);
		invalidate();
		computeScroll();
	}




	private class 1
		implements Runnable
	{

		final TimeListView this$0;

		public void run()
		{
			reAdjustPosition();
		}

		1()
		{
			this$0 = TimeListView.this;
			super();
		}
	}


	private class 2 extends Handler
	{

		final TimeListView this$0;

		public void handleMessage(Message message)
		{
			super.handleMessage(message);
			TimeListView timelistview = TimeListView.this;
			int i = message.what;
			int j = mTimeListAdapter.getBlankCount();
			int k = i + j;
			timelistview.setSelectedPosition(k);
		}

		2()
		{
			this$0 = TimeListView.this;
			super();
		}
	}

}
