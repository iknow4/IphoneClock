// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoPressRelativeLayout.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class AutoPressRelativeLayout extends RelativeLayout
{

	public AutoPressRelativeLayout(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
	}

	protected void drawableStateChanged()
	{
		super.drawableStateChanged();
		boolean flag = isPressed();
		dispatchSetPressed(flag);
	}
}
