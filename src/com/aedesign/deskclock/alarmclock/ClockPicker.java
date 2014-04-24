// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClockPicker.java

package com.aedesign.deskclock.alarmclock;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			AlarmClock

public class ClockPicker extends Activity
	implements android.widget.AdapterView.OnItemSelectedListener, android.widget.AdapterView.OnItemClickListener
{
	class ClockAdapter extends BaseAdapter
	{

		final ClockPicker this$0;

		public int getCount()
		{
			return AlarmClock.CLOCKS.length;
		}

		public Object getItem(int i)
		{
			return Integer.valueOf(i);
		}

		public long getItemId(int i)
		{
			return (long)i;
		}

		public View getView(int i, View view, ViewGroup viewgroup)
		{
			LayoutInflater layoutinflater = mFactory;
			int j = AlarmClock.CLOCKS[i];
			return layoutinflater.inflate(j, null);
		}

		public ClockAdapter()
		{
			this$0 = ClockPicker.this;
			super();
		}
	}


	private View mClock;
	private ViewGroup mClockLayout;
	private LayoutInflater mFactory;
	private Gallery mGallery;
	private int mPosition;
	private SharedPreferences mPrefs;

	public ClockPicker()
	{
	}

	/**
	 * @deprecated Method selectClock is deprecated
	 */

	private void selectClock(int i)
	{
		this;
		JVM INSTR monitorenter ;
		android.content.SharedPreferences.Editor editor = mPrefs.edit();
		editor.putInt("face", i);
		editor.commit();
		setResult(-1);
		finish();
		this;
		JVM INSTR monitorexit ;
		return;
		Exception exception;
		exception;
		throw exception;
	}

	public void onCreate(Bundle bundle)
	{
		boolean flag;
		int i;
label0:
		{
			flag = null;
			super.onCreate(bundle);
			requestWindowFeature(1);
			LayoutInflater layoutinflater = LayoutInflater.from(this);
			mFactory = layoutinflater;
			setContentView(0x7f030009);
			Gallery gallery = (Gallery)findViewById(0x7f0d0019);
			mGallery = gallery;
			Gallery gallery1 = mGallery;
			ClockAdapter clockadapter = new ClockAdapter();
			gallery1.setAdapter(clockadapter);
			mGallery.setOnItemSelectedListener(this);
			mGallery.setOnItemClickListener(this);
			SharedPreferences sharedpreferences = getSharedPreferences("AlarmClock", flag);
			mPrefs = sharedpreferences;
			i = mPrefs.getInt("face", flag);
			if (i >= 0)
			{
				int j = AlarmClock.CLOCKS.length;
				if (i < j)
					break label0;
			}
			i = 0;
		}
		ViewGroup viewgroup = (ViewGroup)findViewById(0x7f0d0018);
		mClockLayout = viewgroup;
		ViewGroup viewgroup1 = mClockLayout;
		1 1_1 = new 1();
		viewgroup1.setOnClickListener(1_1);
		mGallery.setSelection(i, flag);
	}

	public void onItemClick(AdapterView adapterview, View view, int i, long l)
	{
		selectClock(i);
	}

	public void onItemSelected(AdapterView adapterview, View view, int i, long l)
	{
		if (mClock != null)
		{
			ViewGroup viewgroup = mClockLayout;
			View view1 = mClock;
			viewgroup.removeView(view1);
		}
		LayoutInflater layoutinflater = mFactory;
		int j = AlarmClock.CLOCKS[i];
		View view2 = layoutinflater.inflate(j, null);
		mClock = view2;
		ViewGroup viewgroup1 = mClockLayout;
		View view3 = mClock;
		viewgroup1.addView(view3, 0);
		mPosition = i;
	}

	public void onNothingSelected(AdapterView adapterview)
	{
	}




	private class 1
		implements android.view.View.OnClickListener
	{

		final ClockPicker this$0;

		public void onClick(View view)
		{
			ClockPicker clockpicker = ClockPicker.this;
			int i = mPosition;
			clockpicker.selectClock(i);
		}

		1()
		{
			this$0 = ClockPicker.this;
			super();
		}
	}

}
