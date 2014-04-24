// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTabBarItemLayout.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.aedesign.deskclock.ui:
//			OnTabViewChangeListener

public class IphoneTabBarItemLayout extends ViewGroup
{

	private final boolean DEBUG;
	private final String TAG;
	private boolean mIsOn;
	private ImageView mMainImageView;
	private Drawable mMainImageViewBackgroundOff;
	private Drawable mMainImageViewBackgroundOn;
	private int mMainImageViewHeith;
	private int mMainImageViewWidth;
	private OnTabViewChangeListener mOnTabViewChangeListener;
	private android.view.View.OnTouchListener mOnTouchListener;
	private TextView mSubTextView;
	private Drawable mSubTextViewBackgroundOff;
	private Drawable mSubTextViewBackgroundOn;
	private int mSubTextViewHeight;
	private int mSubTextViewWidth;

	public IphoneTabBarItemLayout(Context context)
	{
		super(context);
		TAG = "IphoneTabBarItemLayout";
		DEBUG = null;
		mIsOn = null;
		setUpView(context);
	}

	public IphoneTabBarItemLayout(Context context, AttributeSet attributeset)
	{
		this(context, attributeset, 0);
	}

	public IphoneTabBarItemLayout(Context context, AttributeSet attributeset, int i)
	{
		byte byte0;
		TypedArray typedarray;
		int j;
		int k;
		byte0 = 20;
		super(context, attributeset, i);
		TAG = "IphoneTabBarItemLayout";
		DEBUG = null;
		mIsOn = null;
		int ai[] = com.aedesign.deskclock.R.styleable.iphoneTabBarItem;
		typedarray = context.obtainStyledAttributes(attributeset, ai, i, 0);
		j = typedarray.getIndexCount();
		k = 0;
_L11:
		int l;
		if (k >= j)
			break MISSING_BLOCK_LABEL_274;
		l = typedarray.getIndex(k);
		l;
		JVM INSTR tableswitch 0 7: default 116
	//	               0 140
	//	               1 122
	//	               2 194
	//	               3 214
	//	               4 176
	//	               5 158
	//	               6 234
	//	               7 254;
		   goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
		break MISSING_BLOCK_LABEL_254;
_L1:
		break; /* Loop/switch isn't completed */
_L3:
		break; /* Loop/switch isn't completed */
_L12:
		k++;
		if (true) goto _L11; else goto _L10
_L10:
		Drawable drawable = typedarray.getDrawable(l);
		mMainImageViewBackgroundOff = drawable;
		  goto _L12
_L2:
		Drawable drawable1 = typedarray.getDrawable(l);
		mMainImageViewBackgroundOn = drawable1;
		  goto _L12
_L7:
		Drawable drawable2 = typedarray.getDrawable(l);
		mSubTextViewBackgroundOff = drawable2;
		  goto _L12
_L6:
		Drawable drawable3 = typedarray.getDrawable(l);
		mSubTextViewBackgroundOn = drawable3;
		  goto _L12
_L4:
		int i1 = typedarray.getDimensionPixelSize(l, 64);
		mMainImageViewWidth = i1;
		  goto _L12
_L5:
		int j1 = typedarray.getDimensionPixelSize(l, 49);
		mMainImageViewHeith = j1;
		  goto _L12
_L8:
		int k1 = typedarray.getDimensionPixelSize(l, byte0);
		mSubTextViewWidth = k1;
		  goto _L12
		int l1 = typedarray.getDimensionPixelSize(l, byte0);
		mSubTextViewHeight = l1;
		  goto _L12
		typedarray.recycle();
		setUpView(context);
		return;
	}

	private void setOnFromUser(boolean flag)
	{
		setOn(flag);
		if (mOnTabViewChangeListener != null)
			mOnTabViewChangeListener.onTabViewChange(this, flag);
	}

	private void setUpView(Context context)
	{
		ImageView imageview = new ImageView(context);
		mMainImageView = imageview;
		ImageView imageview1 = mMainImageView;
		Drawable drawable = mMainImageViewBackgroundOff;
		imageview1.setBackgroundDrawable(drawable);
		mMainImageView.setVisibility(0);
		TextView textview = new TextView(context);
		mSubTextView = textview;
		TextView textview1 = mSubTextView;
		Drawable drawable1 = mSubTextViewBackgroundOff;
		textview1.setBackgroundDrawable(drawable1);
		mSubTextView.setVisibility(4);
		ImageView imageview2 = mMainImageView;
		addView(imageview2);
		TextView textview2 = mSubTextView;
		addView(textview2);
		1 1_1 = new 1();
		super.setOnTouchListener(1_1);
	}

	public int getSubTextViewVisibility()
	{
		return mSubTextView.getVisibility();
	}

	public boolean isOn()
	{
		return mIsOn;
	}

	protected void onLayout(boolean flag, int i, int j, int k, int l)
	{
		TextView textview = mSubTextView;
		int i1 = mMainImageViewWidth;
		int j1 = mSubTextViewWidth;
		int k1 = i1 - j1;
		int l1 = mMainImageViewWidth;
		int i2 = mMainImageViewHeith;
		int j2 = mSubTextViewHeight;
		int k2 = i2 - j2;
		textview.layout(k1, 0, l1, k2);
		ImageView imageview = mMainImageView;
		int l2 = mMainImageViewWidth;
		int i3 = mMainImageViewHeith;
		imageview.layout(0, 0, l2, i3);
	}

	protected void onMeasure(int i, int j)
	{
		ImageView imageview = mMainImageView;
		int k = mMainImageViewWidth;
		int l = mMainImageViewHeith;
		imageview.measure(k, l);
		TextView textview = mSubTextView;
		int i1 = mSubTextViewWidth;
		int j1 = mSubTextViewHeight;
		textview.measure(i1, j1);
		int k1 = mMainImageViewWidth;
		int l1 = mMainImageViewHeith;
		setMeasuredDimension(k1, l1);
	}

	public void setOn(boolean flag)
	{
		if (mIsOn != flag)
		{
			mIsOn = flag;
			if (flag)
			{
				ImageView imageview = mMainImageView;
				Drawable drawable = mMainImageViewBackgroundOn;
				imageview.setBackgroundDrawable(drawable);
				TextView textview = mSubTextView;
				Drawable drawable1 = mSubTextViewBackgroundOn;
				textview.setBackgroundDrawable(drawable1);
			} else
			{
				ImageView imageview1 = mMainImageView;
				Drawable drawable2 = mMainImageViewBackgroundOff;
				imageview1.setBackgroundDrawable(drawable2);
				TextView textview1 = mSubTextView;
				Drawable drawable3 = mSubTextViewBackgroundOff;
				textview1.setBackgroundDrawable(drawable3);
			}
		}
	}

	public void setOnTabViewChangeListener(OnTabViewChangeListener ontabviewchangelistener)
	{
		mOnTabViewChangeListener = ontabviewchangelistener;
	}

	public void setOnTouchListener(android.view.View.OnTouchListener ontouchlistener)
	{
		mOnTouchListener = ontouchlistener;
	}

	public void setSubTextViewNumberText(int i)
	{
		TextView textview = mSubTextView;
		String s = String.valueOf(i);
		textview.setText(s);
		if (i > 0)
			mSubTextView.setVisibility(0);
		else
			mSubTextView.setVisibility(4);
	}

	public void setSubTextViewVisibility(int i)
	{
		mSubTextView.setVisibility(i);
	}



	private class 1
		implements android.view.View.OnTouchListener
	{

		final IphoneTabBarItemLayout this$0;

		public boolean onTouch(View view, MotionEvent motionevent)
		{
			boolean flag = true;
			setOnFromUser(flag);
			android.view.View.OnTouchListener ontouchlistener = mOnTouchListener;
			boolean flag1;
			if (ontouchlistener != null)
				flag1 = mOnTouchListener.onTouch(view, motionevent);
			else
				flag1 = flag;
			return flag1;
		}

		1()
		{
			this$0 = IphoneTabBarItemLayout.this;
			super();
		}
	}

}
