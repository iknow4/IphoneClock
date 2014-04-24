// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DeskClockGroupActivity.java

package com.aedesign.deskclock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import com.aedesign.deskclock.alarmclock.AlarmClock;
import com.aedesign.deskclock.stopwatch.IphoneStopwatchActivity;
import com.aedesign.deskclock.timer.IphoneTimerActivity;
import com.aedesign.deskclock.ui.IphoneTabBarItemLayout;
import com.aedesign.deskclock.ui.IphoneTabsActivity;
import com.aedesign.deskclock.ui.OnTabChangeListener;
import com.aedesign.deskclock.worldclock.IphoneWorldClockActivity;

public class DeskClockGroupActivity extends IphoneTabsActivity
	implements OnTabChangeListener
{

	private static final String KEY_LANUCHER_TAG = "mLauncherTag";
	private static final String LAUNCHER_TAG = "launcher_tag";
	private static final int TAG_ALARM_CLOCK = 1;
	private static final int TAG_STOP_WATCH = 2;
	private static final int TAG_TIMER = 3;
	private static final int TAG_WORLD_CLOCK = 4;
	private MenuItem mAboutItem;

	public DeskClockGroupActivity()
	{
	}

	private int getLanucherTag()
	{
		return getSharedPreferences("launcher_tag", 0).getInt("mLauncherTag", 0);
	}

	private void init()
	{
		Intent intent = new Intent();
		intent.setClass(this, com.aedesign.deskclock.worldclock.IphoneWorldClockActivity.class);
		IphoneTabBarItemLayout iphonetabbaritemlayout = (IphoneTabBarItemLayout)findViewById(0x7f0d001e);
		Integer integer = Integer.valueOf(0);
		com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo tabiteminfo = new com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo(this, integer, iphonetabbaritemlayout, intent);
		addTabItem(tabiteminfo);
		Intent intent1 = new Intent();
		intent1.setClass(this, com.aedesign.deskclock.alarmclock.AlarmClock.class);
		IphoneTabBarItemLayout iphonetabbaritemlayout1 = (IphoneTabBarItemLayout)findViewById(0x7f0d001f);
		Integer integer1 = Integer.valueOf(1);
		com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo tabiteminfo1 = new com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo(this, integer1, iphonetabbaritemlayout1, intent1);
		addTabItem(tabiteminfo1);
		Intent intent2 = new Intent();
		intent2.setClass(this, com.aedesign.deskclock.stopwatch.IphoneStopwatchActivity.class);
		IphoneTabBarItemLayout iphonetabbaritemlayout2 = (IphoneTabBarItemLayout)findViewById(0x7f0d0020);
		Integer integer2 = Integer.valueOf(2);
		com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo tabiteminfo2 = new com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo(this, integer2, iphonetabbaritemlayout2, intent2);
		addTabItem(tabiteminfo2);
		Intent intent3 = new Intent();
		intent3.setClass(this, com.aedesign.deskclock.timer.IphoneTimerActivity.class);
		IphoneTabBarItemLayout iphonetabbaritemlayout3 = (IphoneTabBarItemLayout)findViewById(0x7f0d0021);
		Integer integer3 = Integer.valueOf(3);
		com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo tabiteminfo3 = new com.aedesign.deskclock.ui.IphoneTabsActivity.TabItemInfo(this, integer3, iphonetabbaritemlayout3, intent3);
		addTabItem(tabiteminfo3);
		Integer integer4 = Integer.valueOf(getLanucherTag());
		setCurrentTag(integer4);
	}

	private boolean isAppropriateResolution()
	{
		char c = '\u0356';
		char c1 = '\u0320';
		char c2 = '\u0140';
		char c3 = '\u01E0';
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int i = displaymetrics.widthPixels;
		int j = displaymetrics.heightPixels;
		Object obj = null;
		boolean flag;
		if (i == c2 && j == c3 || i == c3 && j == c2)
			flag = true;
		else
		if (i == c3 && j == c1 || i == c1 && j == c3)
			flag = true;
		else
		if (i == c3 && j == c || i == c && j == c3)
			flag = true;
		else
			flag = false;
		return flag;
	}

	private boolean saveLauncherTag(int i)
	{
		android.content.SharedPreferences.Editor editor = getSharedPreferences("launcher_tag", 0).edit();
		editor.putInt("mLauncherTag", i);
		return editor.commit();
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(0x7f03000b);
		/*if (!isAppropriateResolution())
		{
			android.app.AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(this)).setMessage(0x7f0a0000);
			1 1_1 = new 1();
			builder.setPositiveButton(0x7f0a0001, 1_1).show();
		}*/
		setOnTabChangeListener(this);
		init();
	}

/*	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuItem menuitem = menu.add(0, 0, 0, 0x7f0a0002);
		mAboutItem = menuitem;
		mAboutItem.setIcon(0x1080041);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuitem)
	{
		int i = mAboutItem;
		if (menuitem == i)
		{
			android.app.AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a0002);
			2 2_1 = new 2();
			builder.setPositiveButton(0x7f0a0003, 2_1).setMessage(0x7f0a0004).show();
			builder = 1;
		} else
		{
			builder = null;
		}
		return builder;
	}
*/
	public void onTabChange(Object obj)
	{
		int i = ((Integer)obj).intValue();
		saveLauncherTag(i);
	}

}
