// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SmsLeftYuan.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SmsLeftYuan extends FrameLayout
{

	private ImageView mSmsLeftDown;
	private ImageView mSmsLeftUp;

	public SmsLeftYuan(Context context)
	{
		this(context, null);
	}

	public SmsLeftYuan(Context context, AttributeSet attributeset)
	{
		this(context, attributeset, 0);
	}

	public SmsLeftYuan(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		ImageView imageview = new ImageView(context, attributeset);
		mSmsLeftDown = imageview;
		mSmsLeftDown.setImageResource(0x7f020082);
		ImageView imageview1 = mSmsLeftDown;
		addView(imageview1);
		ImageView imageview2 = new ImageView(context, attributeset);
		mSmsLeftUp = imageview2;
		mSmsLeftUp.setBackgroundResource(0x7f020081);
		ImageView imageview3 = mSmsLeftUp;
		addView(imageview3);
	}

	public void clearAnimation()
	{
		super.clearAnimation();
		mSmsLeftUp.clearAnimation();
	}

	public int getImgWidth()
	{
		return getResources().getDrawable(0x7f020082).getIntrinsicWidth();
	}

	public ImageView getMSmsLeftDown()
	{
		return mSmsLeftDown;
	}

	public ImageView getMSmsLeftUp()
	{
		return mSmsLeftUp;
	}

	public void setMSmsLeftDown(ImageView imageview)
	{
		if (imageview == null)
			mSmsLeftDown.setImageDrawable(null);
		else
			mSmsLeftDown = imageview;
	}

	public void setMSmsLeftUp(int i)
	{
		mSmsLeftUp.setBackgroundResource(i);
	}

	public void setMSmsLeftUp(ImageView imageview)
	{
		mSmsLeftUp = imageview;
	}

	public void toRotate(Animation animation)
	{
		mSmsLeftUp.clearAnimation();
		mSmsLeftUp.startAnimation(animation);
	}
}
