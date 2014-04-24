// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneBottomPopupView.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IphoneBottomPopupView extends LinearLayout
{

	private final boolean DEBUG;
	private final String TAG;
	private float mDensity;
	private ImageView mImageView;
	private LinearLayout mLinearLayout;

	public IphoneBottomPopupView(Context context)
	{
		super(context);
		TAG = "IphoneBottomPopupWindow";
		DEBUG = null;
		mDensity = 0x3f800000;
	}

	public IphoneBottomPopupView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		TAG = "IphoneBottomPopupWindow";
		DEBUG = null;
		mDensity = 0x3f800000;
	}

	public void addView(View view)
	{
		float f = 0x41a00000;
		float f1 = 0x41700000;
		int i = 0;
		int j = mLinearLayout.getChildCount();
		if (j > 0)
		{
			LinearLayout linearlayout = mLinearLayout;
			int k = j - 1;
			((android.widget.LinearLayout.LayoutParams)linearlayout.getChildAt(k).getLayoutParams()).bottomMargin = i;
		}
		android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
		int l = (int)(mDensity * f);
		layoutparams.topMargin = l;
		int i1 = (int)(mDensity * f1);
		layoutparams.leftMargin = i1;
		int j1 = (int)(mDensity * f1);
		layoutparams.rightMargin = j1;
		int k1 = (int)(mDensity * f);
		layoutparams.bottomMargin = k1;
		layoutparams.gravity = 17;
		view.setLayoutParams(layoutparams);
		mLinearLayout.addView(view);
		mLinearLayout.measure(i, i);
		int l1 = mLinearLayout.getMeasuredHeight();
		int i2 = getResources().getDisplayMetrics().heightPixels - l1;
		if (i2 > 0)
			mImageView.getLayoutParams().height = i2;
	}

	public void addViewWithLayoutParams(View view, android.widget.LinearLayout.LayoutParams layoutparams)
	{
		int i = 0;
		int j = mLinearLayout.getChildCount();
		if (j > 0)
		{
			LinearLayout linearlayout = mLinearLayout;
			int k = j - 1;
			((android.widget.LinearLayout.LayoutParams)linearlayout.getChildAt(k).getLayoutParams()).bottomMargin = i;
		}
		view.setLayoutParams(layoutparams);
		mLinearLayout.addView(view);
		mLinearLayout.measure(i, i);
		int l = mLinearLayout.getMeasuredHeight();
		int i1 = getResources().getDisplayMetrics().heightPixels - l;
		if (i1 > 0)
			mImageView.getLayoutParams().height = i1;
	}

	public void init()
	{
		ImageView imageview = (ImageView)getChildAt(0);
		mImageView = imageview;
		LinearLayout linearlayout = (LinearLayout)getChildAt(1);
		mLinearLayout = linearlayout;
		float f = getResources().getDisplayMetrics().density;
		mDensity = f;
	}

	public void removeAllButtons()
	{
		mLinearLayout.removeAllViews();
	}

	public void setBottomPopupWindowBackgroundDrawable(Drawable drawable)
	{
		mLinearLayout.setBackgroundDrawable(drawable);
	}

	public void setImageViewVisibility(int i)
	{
		mImageView.setVisibility(i);
	}
}
