// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneBottomPopActivity.java

package com.aedesign.deskclock.ui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;

// Referenced classes of package com.aedesign.deskclock.ui:
//			IphoneNoTitleActivity, IphoneBottomPopupWindow

public class IphoneBottomPopActivity extends IphoneNoTitleActivity
	implements IphoneBottomPopupWindow.OnPopStateChangeListener
{

	private final boolean DEBUG = null;
	private final String TAG = "IphoneBottomPopActivity";
	private View mContentView;
	private IphoneBottomPopupWindow mPopwindow;
	private int mRequestedOrientation;

	public IphoneBottomPopActivity()
	{
	}

	public boolean dispatchKeyEvent(KeyEvent keyevent)
	{
		int i;
		byte byte0;
		i = keyevent.getKeyCode();
		byte0 = 4;
		if (i != byte0) goto _L2; else goto _L1
_L1:
		IphoneBottomPopupWindow iphonebottompopupwindow = mPopwindow;
		if (iphonebottompopupwindow == null) goto _L2; else goto _L3
_L3:
		iphonebottompopupwindow = mPopwindow.updateIphoneBottomMenu(i, keyevent);
		if (!iphonebottompopupwindow) goto _L2; else goto _L4
_L4:
		iphonebottompopupwindow = 1;
_L6:
		return iphonebottompopupwindow;
_L2:
		iphonebottompopupwindow = super.dispatchKeyEvent(keyevent);
		if (true) goto _L6; else goto _L5
_L5:
	}

	public void finish()
	{
		byte byte0 = 4;
		if (mPopwindow != null)
		{
			IphoneBottomPopupWindow iphonebottompopupwindow = mPopwindow;
			KeyEvent keyevent = new KeyEvent(0, byte0);
			iphonebottompopupwindow.updateIphoneBottomMenu(byte0, keyevent);
		}
		super.finish();
	}

	protected View getContentView()
	{
		return mContentView;
	}

	protected IphoneBottomPopupWindow getPopupWindow()
	{
		if (mPopwindow == null)
		{
			IphoneBottomPopupWindow iphonebottompopupwindow = new IphoneBottomPopupWindow(this);
			mPopwindow = iphonebottompopupwindow;
			mPopwindow.setOnPopStateChangeListener(this);
		}
		return mPopwindow;
	}

	public void onConfigurationChanged(Configuration configuration)
	{
		super.onConfigurationChanged(configuration);
		if (mPopwindow != null)
			mPopwindow.updateWidthHeight();
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
	}

	public void onPopDissmiss()
	{
		int i = mRequestedOrientation;
		int j = getRequestedOrientation();
		if (i != j)
		{
			int k = mRequestedOrientation;
			super.setRequestedOrientation(k);
		}
	}

	public void onPopShow()
	{
		int i;
		int j;
		i = 1;
		j = getRequestedOrientation();
		mRequestedOrientation = j;
		if (j != i && j != 0 && j != 4) goto _L2; else goto _L1
_L1:
		return;
_L2:
		int k = getResources().getConfiguration().orientation;
		if (k == i)
			super.setRequestedOrientation(i);
		else
		if (k == 2)
			super.setRequestedOrientation(0);
		if (true) goto _L1; else goto _L3
_L3:
	}

	public void setContentView(int i)
	{
		View view = getLayoutInflater().inflate(i, null);
		setContentView(view);
	}

	public void setContentView(View view)
	{
		mContentView = view;
		View view1 = mContentView;
		super.setContentView(view1);
	}

	public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
	{
		mContentView = view;
		super.setContentView(view, layoutparams);
	}

	public void setRequestedOrientation(int i)
	{
		super.setRequestedOrientation(i);
		mRequestedOrientation = i;
	}
}
