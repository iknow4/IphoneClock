// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTwoStatusControlImageButton.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageButton;
import com.aedesign.deskclock.listener.OnStatusChangeListener;

public class IphoneTwoStatusControlImageButton extends ImageButton
{

	private final boolean DEBUG;
	private final String TAG;
	private boolean mIsOnStatusOne;
	private android.view.View.OnClickListener mOnClickListener;
	private OnStatusChangeListener mOnStatusChangeListener;
	private boolean mStatusChangeEnable;
	private Drawable mStatusOneImageDrawable;
	private Drawable mStatusTwoImageDrawable;

	public IphoneTwoStatusControlImageButton(Context context)
	{
		super(context);
		TAG = "IphoneTwoStatusControlImageButton";
		DEBUG = null;
	}

	public IphoneTwoStatusControlImageButton(Context context, AttributeSet attributeset)
	{
		this(context, attributeset, 0);
	}

	public IphoneTwoStatusControlImageButton(Context context, AttributeSet attributeset, int i)
	{
		boolean flag = true;
		boolean flag1 = false;
		super(context, attributeset, i);
		TAG = "IphoneTwoStatusControlImageButton";
		DEBUG = flag1;
		int ai[] = com.aedesign.deskclock.R.styleable.iphoneTwoStatusControlImageButton;
		TypedArray typedarray = context.obtainStyledAttributes(attributeset, ai, i, flag1);
		if (typedarray != null)
		{
			Drawable drawable = typedarray.getDrawable(flag1);
			mStatusOneImageDrawable = drawable;
			Drawable drawable1 = typedarray.getDrawable(flag);
			mStatusTwoImageDrawable = drawable1;
			boolean flag2 = typedarray.getBoolean(2, flag);
			mIsOnStatusOne = flag2;
			mStatusChangeEnable = flag;
			1 1_1;
			if (mIsOnStatusOne)
			{
				Drawable drawable2 = mStatusOneImageDrawable;
				setImageDrawable(drawable2);
			} else
			{
				Drawable drawable3 = mStatusTwoImageDrawable;
				setImageDrawable(drawable3);
			}
			typedarray.recycle();
			1_1 = new 1();
			super.setOnClickListener(1_1);
		}
	}

	public Drawable getStatusOneImageDrawable()
	{
		return mStatusOneImageDrawable;
	}

	public Drawable getStatusTwoImageDrawable()
	{
		return mStatusTwoImageDrawable;
	}

	public boolean isOnStatusOne()
	{
		return mIsOnStatusOne;
	}

	public boolean isStatusChangeEnable()
	{
		return mStatusChangeEnable;
	}

	public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
	{
		mOnClickListener = onclicklistener;
	}

	public void setOnStatusChangeListener(OnStatusChangeListener onstatuschangelistener)
	{
		mOnStatusChangeListener = onstatuschangelistener;
	}

	public void setStatusChangeEnable(boolean flag)
	{
		mStatusChangeEnable = flag;
	}

	public void setStatusOneImageDrawable(Drawable drawable)
	{
		mStatusOneImageDrawable = drawable;
	}

	public void setStatusOneImageResId(int i)
	{
		Drawable drawable = getResources().getDrawable(i);
		setStatusOneImageDrawable(drawable);
	}

	public void setStatusOneOn(boolean flag)
	{
		boolean flag1 = mIsOnStatusOne;
		if (flag != flag1)
		{
			boolean flag2 = mStatusChangeEnable;
			if (flag2)
			{
				mIsOnStatusOne = flag;
				Drawable drawable;
				if (flag)
					drawable = mStatusOneImageDrawable;
				else
					drawable = mStatusTwoImageDrawable;
				setImageDrawable(drawable);
			}
		}
	}

	public void setStatusOneOnAndNotify(boolean flag)
	{
		boolean flag1 = mIsOnStatusOne;
		if (flag == flag1) goto _L2; else goto _L1
_L1:
		flag1 = mStatusChangeEnable;
		if (flag1) goto _L3; else goto _L2
_L2:
		return;
_L3:
		mIsOnStatusOne = flag;
		Drawable drawable;
		if (flag)
			drawable = mStatusOneImageDrawable;
		else
			drawable = mStatusTwoImageDrawable;
		setImageDrawable(drawable);
		if (mOnStatusChangeListener != null)
			mOnStatusChangeListener.onStatusChanged(this, flag);
		if (true) goto _L2; else goto _L4
_L4:
	}

	public void setStatusTwoImageDrawable(Drawable drawable)
	{
		mStatusTwoImageDrawable = drawable;
	}

	public void setStatusTwoImageResId(int i)
	{
		Drawable drawable = getResources().getDrawable(i);
		setStatusTwoImageDrawable(drawable);
	}




	private class 1
		implements android.view.View.OnClickListener
	{

		final IphoneTwoStatusControlImageButton this$0;

		public void onClick(View view)
		{
			android.view.View.OnClickListener onclicklistener = mOnClickListener;
			if (onclicklistener != null)
			{
				android.view.View.OnClickListener onclicklistener1 = mOnClickListener;
				onclicklistener1.onClick(view);
			}
			boolean flag = mStatusChangeEnable;
			if (flag)
			{
				IphoneTwoStatusControlImageButton iphonetwostatuscontrolimagebutton = IphoneTwoStatusControlImageButton.this;
				boolean flag1 = mIsOnStatusOne;
				if (!flag1)
					flag1 = true;
				else
					flag1 = null;
				iphonetwostatuscontrolimagebutton.setStatusOneOnAndNotify(flag1);
			}
		}

		1()
		{
			this$0 = IphoneTwoStatusControlImageButton.this;
			super();
		}
	}

}
