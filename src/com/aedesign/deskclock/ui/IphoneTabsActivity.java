// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTabsActivity.java

package com.aedesign.deskclock.ui;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.LinearLayout;
import java.util.HashMap;

// Referenced classes of package com.aedesign.deskclock.ui:
//			OnTabViewChangeListener, IphoneTabBarItemLayout, OnTabChangeListener

public class IphoneTabsActivity extends ActivityGroup
	implements OnTabViewChangeListener
{
	public final class TabItemInfo
	{

		private Intent mIntent;
		private IphoneTabBarItemLayout mTabItemLayout;
		private Object mTag;
		final IphoneTabsActivity this$0;

		public Intent getIntent()
		{
			return mIntent;
		}

		public IphoneTabBarItemLayout getTabItemLayout()
		{
			return mTabItemLayout;
		}

		public Object getTag()
		{
			return mTag;
		}

		public void setItemSubChildNumber(int i)
		{
			mTabItemLayout.setSubTextViewNumberText(i);
		}

		public void setOn(boolean flag)
		{
			mTabItemLayout.setOn(flag);
		}

		public String toString()
		{
			return mTag.toString();
		}

		public TabItemInfo(Object obj, IphoneTabBarItemLayout iphonetabbaritemlayout, Intent intent)
		{
			this$0 = IphoneTabsActivity.this;
			super();
			mTag = obj;
			mTabItemLayout = iphonetabbaritemlayout;
			iphonetabbaritemlayout.setTag(obj);
			iphonetabbaritemlayout.setOnTabViewChangeListener(IphoneTabsActivity.this);
			mIntent = intent;
		}
	}


	private static final boolean DEBUG = false;
	private static final String TAG = "IphoneTabsActivity";
	private static IphoneTabsActivity mInstance;
	private LocalActivityManager mActivityManager;
	private LinearLayout mContentViewLayout;
	private TabItemInfo mCurrentTab;
	private OnTabChangeListener mOnTabChangeListener;
	private HashMap mTabsHost;

	public IphoneTabsActivity()
	{
	}

	public static IphoneTabsActivity getInstance()
	{
		if (mInstance == null)
			Log.e("IphoneTabsActivity", "mInstance null error");
		return mInstance;
	}

	private void startCurrentTabActivity()
	{
		mContentViewLayout.removeAllViews();
		LocalActivityManager localactivitymanager = mActivityManager;
		String s = mCurrentTab.toString();
		Intent intent = mCurrentTab.getIntent();
		View view = localactivitymanager.startActivity(s, intent).getDecorView();
		android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
		mContentViewLayout.addView(view, layoutparams);
		mCurrentTab.setOn(true);
	}

	protected void addTabItem(TabItemInfo tabiteminfo)
	{
		Object obj = tabiteminfo.getTag();
		IphoneTabBarItemLayout iphonetabbaritemlayout = tabiteminfo.getTabItemLayout();
		mTabsHost.put(obj, tabiteminfo);
	}

	public void backToTabActivity()
	{
		startCurrentTabActivity();
	}

	public void finish()
	{
		mInstance = null;
		super.finish();
	}

	protected Object getCurrentTag()
	{
		Object obj = mCurrentTab;
		if (obj == null)
			obj = 0;
		else
			obj = mCurrentTab.getTag();
		return obj;
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(1);
		LocalActivityManager localactivitymanager = getLocalActivityManager();
		mActivityManager = localactivitymanager;
		HashMap hashmap = new HashMap();
		mTabsHost = hashmap;
		mInstance = this;
	}

	public boolean onKeyDown(int i, KeyEvent keyevent)
	{
		boolean flag = mActivityManager.getCurrentActivity().onKeyDown(i, keyevent);
		if (flag)
			flag = true;
		else
			flag = super.onKeyDown(i, keyevent);
		return flag;
	}

	public boolean onKeyUp(int i, KeyEvent keyevent)
	{
		boolean flag = mActivityManager.getCurrentActivity().onKeyUp(i, keyevent);
		if (flag)
			flag = true;
		else
			flag = super.onKeyUp(i, keyevent);
		return flag;
	}

	public void onTabViewChange(IphoneTabBarItemLayout iphonetabbaritemlayout, boolean flag)
	{
		int i;
		int j;
		i = ((int) (mCurrentTab.getTag()));
		j = ((int) (iphonetabbaritemlayout.getTag()));
		break MISSING_BLOCK_LABEL_14;
		while (true) 
		{
			do
				return;
			while (j == i || !flag);
			mCurrentTab.setOn(null);
			setCurrentTag(j);
			if (mOnTabChangeListener != null)
				mOnTabChangeListener.onTabChange(j);
		}
	}

	public void setContentView(int i)
	{
		View view = getLayoutInflater().inflate(i, null);
		setContentView(view);
	}

	public void setContentView(View view)
	{
		LinearLayout linearlayout = (LinearLayout)((ViewGroup)view).getChildAt(0);
		mContentViewLayout = linearlayout;
		super.setContentView(view);
	}

	public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
	{
		LinearLayout linearlayout = (LinearLayout)((ViewGroup)view).getChildAt(0);
		mContentViewLayout = linearlayout;
		super.setContentView(view, layoutparams);
	}

	protected void setCurrentTag(Object obj)
	{
		if (mCurrentTab == null) goto _L2; else goto _L1
_L1:
		if (mCurrentTab.getTag() != obj) goto _L4; else goto _L3
_L3:
		return;
_L4:
		mCurrentTab.setOn(null);
_L2:
		TabItemInfo tabiteminfo = (TabItemInfo)mTabsHost.get(obj);
		mCurrentTab = tabiteminfo;
		mCurrentTab.setOn(true);
		startCurrentTabActivity();
		if (true) goto _L3; else goto _L5
_L5:
	}

	public void setOnTabChangeListener(OnTabChangeListener ontabchangelistener)
	{
		mOnTabChangeListener = ontabchangelistener;
	}

	protected void startGroupActivityInTab(Intent intent)
	{
		mContentViewLayout.removeAllViews();
		LocalActivityManager localactivitymanager = mActivityManager;
		String s = mCurrentTab.toString();
		View view = localactivitymanager.startActivity(s, intent).getDecorView();
		android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
		mContentViewLayout.addView(view, layoutparams);
	}
}
