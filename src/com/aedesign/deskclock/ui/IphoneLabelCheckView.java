// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneLabelCheckView.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;
import com.aedesign.deskclock.listener.OnStatusChangeListener;

public class IphoneLabelCheckView extends RelativeLayout
	implements android.view.View.OnClickListener
{

	private final boolean DEBUG;
	private final String TAG;
	private boolean isCheck;
	private boolean isMultiCheckable;
	private ImageView mCheckImageView;
	private android.view.View.OnClickListener mClickListener;
	private ColorStateList mLabelCheckOnTextColor;
	private Drawable mLabelLeftSrc;
	private int mLabelMarginLeft;
	private int mLabelMarginRight;
	private Drawable mLabelSrc;
	private CharSequence mLabelText;
	private ColorStateList mLabelTextColor;
	private int mLabelTextSize;
	private TextView mLableTV;
	private ImageView mLeftImageLabel;
	private OnStatusChangeListener mStatusChangListener;

	public IphoneLabelCheckView(Context context, AttributeSet attributeset)
	{
		this(context, attributeset, 0);
	}

	public IphoneLabelCheckView(Context context, AttributeSet attributeset, int i)
	{
		boolean flag = false;
		super(context, attributeset, i);
		TAG = "IphoneLableView";
		DEBUG = flag;
		isCheck = flag;
		isMultiCheckable = flag;
		int ai[] = com.aedesign.deskclock.R.styleable.iphoneLabelCheckView;
		TypedArray typedarray = context.obtainStyledAttributes(attributeset, ai, i, flag);
		if (typedarray != null)
		{
			Drawable drawable = typedarray.getDrawable(7);
			mLabelLeftSrc = drawable;
			CharSequence charsequence = typedarray.getText(flag);
			mLabelText = charsequence;
			ColorStateList colorstatelist = typedarray.getColorStateList(2);
			mLabelTextColor = colorstatelist;
			ColorStateList colorstatelist1 = typedarray.getColorStateList(3);
			mLabelCheckOnTextColor = colorstatelist1;
			int j = typedarray.getDimensionPixelSize(1, 18);
			mLabelTextSize = j;
			Drawable drawable1 = typedarray.getDrawable(4);
			mLabelSrc = drawable1;
			int k = typedarray.getDimensionPixelSize(5, flag);
			mLabelMarginLeft = k;
			int l = typedarray.getDimensionPixelSize(6, flag);
			mLabelMarginRight = l;
			typedarray.recycle();
			setUpView(context);
		}
	}

	protected void drawableStateChanged()
	{
		super.drawableStateChanged();
		boolean flag = isPressed();
		mLableTV.setPressed(flag);
		mCheckImageView.setPressed(flag);
	}

	public int getLabelMarginLeft()
	{
		return mLabelMarginLeft;
	}

	public int getLabelMarginRight()
	{
		return mLabelMarginRight;
	}

	public Drawable getLabelSrc()
	{
		return mLabelSrc;
	}

	public CharSequence getLabelText()
	{
		return mLabelText;
	}

	public ColorStateList getLabelTextColor()
	{
		return mLabelTextColor;
	}

	public int getLabelTextSize()
	{
		return mLabelTextSize;
	}

	public boolean isCheck()
	{
		return isCheck;
	}

	public void onClick(View view)
	{
		boolean flag;
		android.view.View.OnClickListener onclicklistener = mClickListener;
		if (onclicklistener != null)
		{
			android.view.View.OnClickListener onclicklistener1 = mClickListener;
			onclicklistener1.onClick(view);
		}
		flag = isMultiCheckable;
		if (flag) goto _L2; else goto _L1
_L1:
		flag = isCheck;
		if (!flag) goto _L2; else goto _L3
_L3:
		return;
_L2:
		boolean flag1 = isCheck;
		if (!flag1)
			flag1 = true;
		else
			flag1 = null;
		setCheck(flag1);
		if (mStatusChangListener != null)
		{
			OnStatusChangeListener onstatuschangelistener = mStatusChangListener;
			boolean flag2 = isCheck;
			onstatuschangelistener.onStatusChanged(this, flag2);
		}
		if (true) goto _L3; else goto _L4
_L4:
	}

	public void setCheck(boolean flag)
	{
		boolean flag1 = isCheck;
		if (flag != flag1) goto _L2; else goto _L1
_L1:
		return;
_L2:
		isCheck = flag;
		if (flag)
		{
			mCheckImageView.setVisibility(0);
			if (!isMultiCheckable)
			{
				TextView textview = mLableTV;
				ColorStateList colorstatelist = mLabelCheckOnTextColor;
				textview.setTextColor(colorstatelist);
			}
		} else
		{
			mCheckImageView.setVisibility(4);
			TextView textview1 = mLableTV;
			ColorStateList colorstatelist1 = mLabelTextColor;
			textview1.setTextColor(colorstatelist1);
		}
		if (true) goto _L1; else goto _L3
_L3:
	}

	public void setLabelMarginLeft(int i)
	{
		mLabelMarginLeft = i;
		((android.widget.RelativeLayout.LayoutParams)mLableTV.getLayoutParams()).leftMargin = i;
	}

	public void setLabelMarginRight(int i)
	{
		mLabelMarginRight = i;
		((android.widget.RelativeLayout.LayoutParams)mCheckImageView.getLayoutParams()).rightMargin = i;
	}

	public void setLabelSrc(Drawable drawable)
	{
		mCheckImageView.setImageDrawable(drawable);
	}

	public void setLabelText(CharSequence charsequence)
	{
		mLableTV.setText(charsequence);
	}

	public void setLabelTextColor(ColorStateList colorstatelist)
	{
		mLableTV.setTextColor(colorstatelist);
	}

	public void setLabelTextRes(int i)
	{
		mLableTV.setText(i);
	}

	public void setLabelTextSize(int i)
	{
		TextView textview = mLableTV;
		float f = i;
		textview.setTextSize(f);
	}

	public void setLeftImageLabel(Drawable drawable)
	{
		mLeftImageLabel.setImageDrawable(drawable);
	}

	public void setLeftImageLabelVisibility(int i)
	{
		mLeftImageLabel.setVisibility(i);
	}

	public void setMultiCheckable(boolean flag)
	{
		isMultiCheckable = flag;
	}

	public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
	{
		mClickListener = onclicklistener;
	}

	public void setOnStatusChangListener(OnStatusChangeListener onstatuschangelistener)
	{
		mStatusChangListener = onstatuschangelistener;
	}

	public void setUpView(Context context)
	{
		ImageView imageview = new ImageView(context);
		mLeftImageLabel = imageview;
		ImageView imageview1 = mLeftImageLabel;
		Drawable drawable = mLabelLeftSrc;
		imageview1.setImageDrawable(drawable);
		android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
		layoutparams.addRule(9);
		layoutparams.addRule(15);
		int i = mLabelMarginLeft;
		layoutparams.leftMargin = i;
		mLeftImageLabel.setLayoutParams(layoutparams);
		mLeftImageLabel.setVisibility(8);
		TextView textview = new TextView(context);
		mLableTV = textview;
		mLableTV.setBackgroundColor(0);
		TextView textview1 = mLableTV;
		CharSequence charsequence = mLabelText;
		textview1.setText(charsequence);
		TextView textview2 = mLableTV;
		ColorStateList colorstatelist = mLabelTextColor;
		textview2.setTextColor(colorstatelist);
		TextView textview3 = mLableTV;
		float f = mLabelTextSize;
		textview3.setTextSize(0, f);
		android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, -1);
		layoutparams1.addRule(9);
		layoutparams1.addRule(15);
		layoutparams1.leftMargin = 10;
		mLableTV.setLayoutParams(layoutparams1);
		ImageView imageview2 = new ImageView(context);
		mCheckImageView = imageview2;
		mCheckImageView.setBackgroundColor(0);
		ImageView imageview3 = mCheckImageView;
		Drawable drawable1 = mLabelSrc;
		imageview3.setImageDrawable(drawable1);
		android.widget.RelativeLayout.LayoutParams layoutparams2 = new android.widget.RelativeLayout.LayoutParams(-1, -1);
		layoutparams2.addRule(11);
		layoutparams2.addRule(15);
		int j = mLabelMarginRight;
		layoutparams2.rightMargin = j;
		mCheckImageView.setLayoutParams(layoutparams2);
		mCheckImageView.setVisibility(4);
		ImageView imageview4 = mLeftImageLabel;
		addView(imageview4);
		TextView textview4 = mLableTV;
		addView(textview4);
		ImageView imageview5 = mCheckImageView;
		addView(imageview5);
		super.setOnClickListener(this);
	}
}
