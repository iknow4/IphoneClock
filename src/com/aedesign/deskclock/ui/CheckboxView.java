// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CheckboxView.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import com.aedesign.deskclock.listener.OnStatusChangeListener;

public class CheckboxView extends ViewGroup
	implements Checkable
{

	public static final boolean DEBUG = false;
	public static final String TAG = "IphoneCheckbox";
	private int mCheckboxLeft;
	private Drawable mCheckboxSrcPress;
	private Drawable mCheckboxSrcUnpress;
	private ImageView mCheckboxView;
	private boolean mChecked;
	private int mIphoneCheckboxLeftMargin;
	private int mLastMotionX;
	private OnStatusChangeListener mListener;
	private int mTop;

	public CheckboxView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		init(context, attributeset);
	}

	public CheckboxView(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		init(context, attributeset);
	}

	private void init(Context context, AttributeSet attributeset)
	{
		Drawable drawable = null;
		Resources resources = getResources();
		int i = resources.getDimensionPixelSize(0x7f09000b);
		mCheckboxLeft = i;
		int j = resources.getDimensionPixelSize(0x7f09000d);
		mTop = j;
		int k = resources.getDimensionPixelSize(0x7f09000c);
		mIphoneCheckboxLeftMargin = k;
		int ai[] = com.aedesign.deskclock.R.styleable.iphonecheckbox;
		TypedArray typedarray = context.obtainStyledAttributes(attributeset, ai);
		Drawable drawable1 = typedarray.getDrawable(drawable);
		mCheckboxSrcPress = drawable1;
		if (mCheckboxSrcPress == null)
		{
			Drawable drawable2 = resources.getDrawable(0x7f02006a);
			mCheckboxSrcPress = drawable2;
		}
		Drawable drawable3 = typedarray.getDrawable(1);
		mCheckboxSrcUnpress = drawable3;
		if (mCheckboxSrcUnpress == null)
		{
			Drawable drawable4 = resources.getDrawable(0x7f02006b);
			mCheckboxSrcUnpress = drawable4;
		}
		boolean flag = typedarray.getBoolean(3, drawable);
		mChecked = flag;
		ImageView imageview = new ImageView(context);
		mCheckboxView = imageview;
		ImageView imageview1 = mCheckboxView;
		Drawable drawable5 = mCheckboxSrcUnpress;
		imageview1.setImageDrawable(drawable);
		ImageView imageview2 = mCheckboxView;
		addView(imageview2);
	}

	private void refreshView(boolean flag)
	{
		ImageView imageview = mCheckboxView;
		if (flag)
		{
			int i = imageview.getTop();
			int j = imageview.getWidth();
			int k = imageview.getBottom();
			imageview.layout(0, i, j, k);
		} else
		{
			int l = -mIphoneCheckboxLeftMargin;
			int i1 = imageview.getTop();
			int j1 = -mIphoneCheckboxLeftMargin;
			int k1 = imageview.getWidth();
			int l1 = j1 + k1;
			int i2 = imageview.getBottom();
			imageview.layout(l, i1, l1, i2);
		}
	}

	public void changeChecked(boolean flag)
	{
		mChecked = flag;
		refreshView(flag);
		if (mListener != null)
			mListener.onStatusChanged(this, flag);
	}

	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		if (!isEnabled())
			canvas.drawColor(0x66ffffff);
	}

	public Drawable getCheckBoxSrcPressDrawable()
	{
		return mCheckboxSrcPress;
	}

	public Drawable getCheckBoxSrcUnpressDrawable()
	{
		return mCheckboxSrcUnpress;
	}

	public boolean isChecked()
	{
		return mChecked;
	}

	protected void onLayout(boolean flag, int i, int j, int k, int l)
	{
		ImageView imageview = mCheckboxView;
		int i1 = mCheckboxSrcUnpress.getIntrinsicWidth();
		int j1 = mCheckboxSrcUnpress.getIntrinsicHeight();
		imageview.layout(0, 0, i1, j1);
		boolean flag1 = mChecked;
		refreshView(flag1);
	}

	protected void onMeasure(int i, int j)
	{
		Drawable drawable = mCheckboxSrcUnpress;
		ImageView imageview = mCheckboxView;
		int k = drawable.getIntrinsicWidth();
		int l = drawable.getIntrinsicWidth();
		imageview.measure(k, l);
		int i1 = drawable.getIntrinsicWidth();
		int j1 = mIphoneCheckboxLeftMargin;
		int k1 = i1 - j1;
		int l1 = drawable.getIntrinsicHeight();
		setMeasuredDimension(k1, l1);
	}

	public boolean onTouchEvent(MotionEvent motionevent)
	{
		boolean flag;
		boolean flag1;
		flag = true;
		flag1 = isEnabled();
		if (flag1) goto _L2; else goto _L1
_L1:
		float f = super.onTouchEvent(motionevent);
_L8:
		return f;
_L2:
		int l;
		int i1;
		ImageView imageview;
		l = motionevent.getAction();
		f = motionevent.getX();
		i1 = (int)f;
		imageview = mCheckboxView;
		l;
		JVM INSTR tableswitch 0 3: default 72
	//	               0 77
	//	               1 194
	//	               2 97
	//	               3 194;
		   goto _L3 _L4 _L5 _L6 _L5
_L5:
		break MISSING_BLOCK_LABEL_194;
_L3:
		break; /* Loop/switch isn't completed */
_L4:
		break; /* Loop/switch isn't completed */
_L9:
		f = flag;
		if (true) goto _L8; else goto _L7
_L7:
		mLastMotionX = i1;
		Drawable drawable = mCheckboxSrcPress;
		imageview.setImageDrawable(drawable);
		  goto _L9
_L6:
		int j1 = imageview.getLeft();
		int k1 = imageview.getRight();
		int i = mLastMotionX;
		i = i1 - i;
		j1 += i;
		i = mLastMotionX;
		i = i1 - i;
		k1 += i;
		if (j1 <= 0)
		{
			int j = -mIphoneCheckboxLeftMargin;
			if (j1 >= j)
			{
				int k = imageview.getTop();
				int l1 = imageview.getBottom();
				imageview.layout(j1, k, k1, l1);
			}
		}
		mLastMotionX = i1;
		  goto _L9
		Drawable drawable1 = mCheckboxSrcUnpress;
		imageview.setImageDrawable(drawable1);
		boolean flag2 = mChecked;
		if (!flag2)
			flag2 = flag;
		else
			flag2 = null;
		changeChecked(flag2);
		  goto _L9
	}

	public void setCheckBoxSrcPress(Drawable drawable)
	{
		if (drawable == null)
			throw new NullPointerException("CheckBoxSrcPress drawable null");
		int i = mCheckboxSrcPress;
		if (drawable != i)
			mCheckboxSrcPress = drawable;
	}

	public void setCheckBoxSrcPressResId(int i)
	{
		Drawable drawable = getResources().getDrawable(i);
		setCheckBoxSrcPress(drawable);
	}

	public void setCheckBoxSrcResId(int i)
	{
		Drawable drawable = getResources().getDrawable(i);
		setCheckBoxSrcUnpress(drawable);
	}

	public void setCheckBoxSrcUnpress(Drawable drawable)
	{
		if (drawable == null)
			throw new NullPointerException("CheckBoxSrcUnpress drawable null");
		int i = mCheckboxSrcUnpress;
		if (drawable != i)
		{
			mCheckboxView.setImageDrawable(drawable);
			mCheckboxSrcUnpress = drawable;
		}
	}

	public void setChecked(boolean flag)
	{
		mChecked = flag;
		refreshView(flag);
	}

	public void setOnStatusChangeListener(OnStatusChangeListener onstatuschangelistener)
	{
		mListener = onstatuschangelistener;
	}

	public void toggle()
	{
		boolean flag = mChecked;
		if (!flag)
			flag = true;
		else
			flag = null;
		setChecked(flag);
	}
}
