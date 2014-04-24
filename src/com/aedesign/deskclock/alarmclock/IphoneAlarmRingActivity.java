// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAlarmRingActivity.java

package com.aedesign.deskclock.alarmclock;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.aedesign.deskclock.listener.OnStatusChangeListener;
import com.aedesign.deskclock.ui.IphoneLabelCheckView;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;

public class IphoneAlarmRingActivity extends IphoneNoTitleActivity
	implements android.view.View.OnClickListener, Runnable, OnStatusChangeListener
{

	private static final boolean DEBUG = false;
	private static final int DELAY_MS_SELECTION_PLAYED = 300;
	private static final String TAG = "RingtonePickerActivity";
	private final int TAG_BACK_BUTTON = null;
	private final int TAG_RINGTONE_DEFAULT = 1;
	private final int TAG_RINGTONE_SILENT = 2;
	private Button mBackButton;
	private int mClickedPos;
	private View mCurrentLabelCheckView;
	private Cursor mCursor;
	private Ringtone mDefaultRingtone;
	private int mDefaultRingtonePos;
	private Uri mExistingUri;
	private Handler mHandler;
	private boolean mHasDefaultItem;
	private boolean mHasSilentItem;
	private LayoutInflater mInflater;
	private LinearLayout mLinearRingDefault;
	private LinearLayout mLinearRingStandard;
	private RingtoneManager mRingtoneManager;
	private int mSampleRingtonePos;
	private int mSilentPos;
	private int mStaticItemCount;
	private Uri mUriForDefaultItem;

	public IphoneAlarmRingActivity()
	{
		mSilentPos = -1;
		mDefaultRingtonePos = -1;
		mClickedPos = -1;
		mSampleRingtonePos = -1;
	}

	private int addDefaultRingtoneItem(ListView listview)
	{
		return -1;
	}

	private int addSilentItem(ListView listview)
	{
		return -1;
	}

	private int getListPosition(int i)
	{
		int j;
		if (i < 0)
			j = i;
		else
			j = mStaticItemCount + i;
		return j;
	}

	private int getRingtoneManagerPosition(int i)
	{
		int j = mStaticItemCount;
		return i - j;
	}

	private void inflateListview(LinearLayout linearlayout, Cursor cursor)
	{
		int i;
		int j;
		int k;
		boolean flag;
		int l;
		i = 0x7f02006f;
		j = 0x7f03001a;
		k = 0x7f02006c;
		flag = null;
		l = 1;
		linearlayout.removeAllViews();
		if (cursor != null) goto _L2; else goto _L1
_L1:
		IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)mInflater.inflate(j, linearlayout, flag);
		iphonelabelcheckview.setOnStatusChangListener(this);
		iphonelabelcheckview.setBackgroundResource(k);
		Integer integer = Integer.valueOf(l);
		iphonelabelcheckview.setTag(integer);
		String s = getString(0x7f0a002c);
		iphonelabelcheckview.setLabelText(s);
		iphonelabelcheckview.setOnClickListener(this);
		linearlayout.addView(iphonelabelcheckview);
_L4:
		return;
_L2:
		int i1;
		if (mHasSilentItem)
		{
			IphoneLabelCheckView iphonelabelcheckview1 = (IphoneLabelCheckView)mInflater.inflate(j, linearlayout, flag);
			iphonelabelcheckview1.setOnStatusChangListener(this);
			Integer integer1;
			String s1;
			int j1;
			int k1;
			int l1;
			String s2;
			final int position;
			2 2_1;
			if (cursor.getCount() == 0)
				iphonelabelcheckview1.setBackgroundResource(k);
			else
				iphonelabelcheckview1.setBackgroundResource(0x7f02007b);
			integer1 = Integer.valueOf(2);
			iphonelabelcheckview1.setTag(integer1);
			s1 = getResources().getString(0x7f0a0006);
			iphonelabelcheckview1.setLabelText(s1);
			iphonelabelcheckview1.setOnClickListener(this);
			linearlayout.addView(iphonelabelcheckview1);
		}
		i1 = 0;
		j1 = cursor.getCount();
		if (i1 < j1)
		{
			IphoneLabelCheckView iphonelabelcheckview2 = (IphoneLabelCheckView)mInflater.inflate(j, linearlayout, flag);
			if (mHasSilentItem)
			{
				k1 = cursor.getCount() - l;
				if (i1 == k1)
					iphonelabelcheckview2.setBackgroundResource(i);
				else
					iphonelabelcheckview2.setBackgroundResource(0x7f020075);
			} else
			if (i1 == 0)
			{
				if (cursor.getCount() == l)
					iphonelabelcheckview2.setBackgroundResource(k);
				else
					iphonelabelcheckview2.setBackgroundResource(0x7f02007b);
			} else
			{
				int i2 = cursor.getCount() - l;
				if (i1 == i2)
					iphonelabelcheckview2.setBackgroundResource(i);
				else
					iphonelabelcheckview2.setBackgroundResource(0x7f020075);
			}
			cursor.moveToPosition(i1);
			l1 = cursor.getColumnIndex("title");
			s2 = cursor.getString(l1);
			iphonelabelcheckview2.setLabelText(s2);
			position = i1;
			iphonelabelcheckview2.setOnStatusChangListener(this);
			2_1 = new 2();
			iphonelabelcheckview2.setOnClickListener(2_1);
			linearlayout.addView(iphonelabelcheckview2);
			i1++;
			break MISSING_BLOCK_LABEL_189;
		}
		continue; /* Loop/switch isn't completed */
		if (true) goto _L4; else goto _L3
_L3:
	}

	private void playRingtone(int i, int j)
	{
		mHandler.removeCallbacks(this);
		mSampleRingtonePos = i;
		Handler handler = mHandler;
		long l = j;
		handler.postDelayed(this, l);
	}

	private void stopAnyPlayingRingtone()
	{
		if (mDefaultRingtone != null && mDefaultRingtone.isPlaying())
			mDefaultRingtone.stop();
		if (mRingtoneManager != null)
			mRingtoneManager.stopPreviousRingtone();
	}

	public void onClick(View view)
	{
		int i;
		int j;
		i = -1;
		j = -1;
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 2: default 44
	//	               0 73
	//	               1 45
	//	               2 61;
		   goto _L1 _L2 _L3 _L4
_L1:
		return;
_L3:
		mSampleRingtonePos = j;
		playRingtone(j, 300);
		continue; /* Loop/switch isn't completed */
_L4:
		mSampleRingtonePos = i;
		stopAnyPlayingRingtone();
		continue; /* Loop/switch isn't completed */
_L2:
		Intent intent;
		Uri uri;
		intent = new Intent();
		uri = null;
		if (mSampleRingtonePos != j)
			break; /* Loop/switch isn't completed */
		uri = mUriForDefaultItem;
_L6:
		intent.putExtra("android.intent.extra.ringtone.PICKED_URI", uri);
		setResult(j, intent);
		View view1 = getWindow().getDecorView();
		1 1_1 = new 1();
		view1.post(1_1);
		finish();
		if (true) goto _L1; else goto _L5
_L5:
		if (mSampleRingtonePos == i)
		{
			uri = null;
		} else
		{
			RingtoneManager ringtonemanager = mRingtoneManager;
			int k = mSampleRingtonePos;
			uri = ringtonemanager.getRingtoneUri(k);
		}
		  goto _L6
		if (true) goto _L1; else goto _L7
_L7:
	}

	protected void onCreate(Bundle bundle)
	{
		int i = -1;
		int j = 0;
		boolean flag = true;
		super.onCreate(bundle);
		setContentView(0x7f030010);
		Button button = (Button)findViewById(0x7f0d0023);
		mBackButton = button;
		Button button1 = mBackButton;
		Integer integer = Integer.valueOf(j);
		button1.setTag(integer);
		mBackButton.setOnClickListener(this);
		LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0d0028);
		mLinearRingDefault = linearlayout;
		LinearLayout linearlayout1 = (LinearLayout)findViewById(0x7f0d0029);
		mLinearRingStandard = linearlayout1;
		LayoutInflater layoutinflater = (LayoutInflater)getSystemService("layout_inflater");
		mInflater = layoutinflater;
		Handler handler = new Handler();
		mHandler = handler;
		Intent intent = getIntent();
		boolean flag1 = intent.getBooleanExtra("android.intent.extra.ringtone.SHOW_DEFAULT", flag);
		mHasDefaultItem = flag1;
		Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.DEFAULT_URI");
		mUriForDefaultItem = uri;
		if (mUriForDefaultItem == null)
		{
			Uri uri1 = android.provider.Settings.System.DEFAULT_RINGTONE_URI;
			mUriForDefaultItem = uri1;
		}
		boolean flag2 = intent.getBooleanExtra("android.intent.extra.ringtone.SHOW_SILENT", flag);
		mHasSilentItem = flag2;
		RingtoneManager ringtonemanager = new RingtoneManager(this);
		mRingtoneManager = ringtonemanager;
		boolean flag3 = intent.getBooleanExtra("android.intent.extra.ringtone.INCLUDE_DRM", flag);
		mRingtoneManager.setIncludeDrm(flag3);
		int k = intent.getIntExtra("android.intent.extra.ringtone.TYPE", i);
		if (k != i)
			mRingtoneManager.setType(k);
		Cursor cursor = mRingtoneManager.getCursor();
		mCursor = cursor;
		int l = mRingtoneManager.inferStreamType();
		setVolumeControlStream(l);
		Uri uri2 = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.EXISTING_URI");
		mExistingUri = uri2;
		LinearLayout linearlayout2 = mLinearRingDefault;
		inflateListview(linearlayout2, null);
		LinearLayout linearlayout3 = mLinearRingStandard;
		Cursor cursor1 = mCursor;
		inflateListview(linearlayout3, cursor1);
		if (mExistingUri == null)
		{
			mSampleRingtonePos = j;
			LinearLayout linearlayout4 = mLinearRingStandard;
			int i1 = mSampleRingtonePos;
			IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)linearlayout4.getChildAt(i1);
			mCurrentLabelCheckView = iphonelabelcheckview;
			LinearLayout linearlayout5 = mLinearRingStandard;
			int j1 = mSampleRingtonePos;
			((IphoneLabelCheckView)linearlayout5.getChildAt(j1)).setCheck(flag);
		} else
		if (RingtoneManager.isDefault(mExistingUri))
		{
			mSampleRingtonePos = i;
			IphoneLabelCheckView iphonelabelcheckview1 = (IphoneLabelCheckView)mLinearRingDefault.getChildAt(j);
			mCurrentLabelCheckView = iphonelabelcheckview1;
			((IphoneLabelCheckView)mLinearRingDefault.getChildAt(j)).setCheck(flag);
		} else
		{
			RingtoneManager ringtonemanager1 = mRingtoneManager;
			Uri uri3 = mExistingUri;
			int k1 = ringtonemanager1.getRingtonePosition(uri3);
			mSampleRingtonePos = k1;
			int l1 = -1;
			IphoneLabelCheckView iphonelabelcheckview2;
			if (mHasSilentItem)
				l1 = mSampleRingtonePos + 1;
			else
				l1 = mSampleRingtonePos;
			iphonelabelcheckview2 = (IphoneLabelCheckView)mLinearRingStandard.getChildAt(l1);
			mCurrentLabelCheckView = iphonelabelcheckview2;
			((IphoneLabelCheckView)mLinearRingStandard.getChildAt(l1)).setCheck(flag);
		}
	}

	public void onItemSelected(AdapterView adapterview, View view, int i, long l)
	{
		playRingtone(i, 300);
	}

	public void onNothingSelected(AdapterView adapterview)
	{
	}

	protected void onPause()
	{
		super.onPause();
		stopAnyPlayingRingtone();
	}

	public void onPrepareListView(ListView listview)
	{
		if (mHasDefaultItem)
		{
			int i = addDefaultRingtoneItem(listview);
			mDefaultRingtonePos = i;
			if (RingtoneManager.isDefault(mExistingUri))
			{
				int j = mDefaultRingtonePos;
				mClickedPos = j;
			}
		}
		if (mHasSilentItem)
		{
			int k = addSilentItem(listview);
			mSilentPos = k;
			if (mExistingUri == null)
			{
				int l = mSilentPos;
				mClickedPos = l;
			}
		}
		if (mClickedPos == -1)
		{
			RingtoneManager ringtonemanager = mRingtoneManager;
			Uri uri = mExistingUri;
			int i1 = ringtonemanager.getRingtonePosition(uri);
			int j1 = getListPosition(i1);
			mClickedPos = j1;
		}
	}

	public void onStatusChanged(View view, boolean flag)
	{
		if (mCurrentLabelCheckView instanceof IphoneLabelCheckView)
			((IphoneLabelCheckView)mCurrentLabelCheckView).setCheck(null);
		mCurrentLabelCheckView = view;
	}

	protected void onStop()
	{
		super.onStop();
		stopAnyPlayingRingtone();
	}

	public void run()
	{
		if (mDefaultRingtone != null && mDefaultRingtone.isPlaying())
		{
			mDefaultRingtone.stop();
			mDefaultRingtone = null;
		}
		Ringtone ringtone1;
		if (mSampleRingtonePos == -1)
		{
			if (mDefaultRingtone == null)
			{
				Uri uri = mUriForDefaultItem;
				Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
				mDefaultRingtone = ringtone;
			}
			ringtone1 = mDefaultRingtone;
			mRingtoneManager.stopPreviousRingtone();
		} else
		{
			RingtoneManager ringtonemanager = mRingtoneManager;
			int i = mSampleRingtonePos;
			ringtone1 = ringtonemanager.getRingtone(i);
		}
		if (ringtone1 != null)
			ringtone1.play();
	}




/*
	static int access$202(IphoneAlarmRingActivity iphonealarmringactivity, int i)
	{
		iphonealarmringactivity.mSampleRingtonePos = i;
		return i;
	}

*/


	private class 2
		implements android.view.View.OnClickListener
	{

		final IphoneAlarmRingActivity this$0;
		final int val$position;

		public void onClick(View view)
		{
			IphoneAlarmRingActivity iphonealarmringactivity1;
			int j;
			if (mHasSilentItem)
			{
				IphoneAlarmRingActivity iphonealarmringactivity = IphoneAlarmRingActivity.this;
				int i = position - 1;
				iphonealarmringactivity.mSampleRingtonePos = i;
			} else
			{
				IphoneAlarmRingActivity iphonealarmringactivity2 = IphoneAlarmRingActivity.this;
				int k = position;
				iphonealarmringactivity2.mSampleRingtonePos = k;
			}
			iphonealarmringactivity1 = IphoneAlarmRingActivity.this;
			j = position;
			iphonealarmringactivity1.playRingtone(j, 300);
		}

		2()
		{
			this$0 = IphoneAlarmRingActivity.this;
			position = i;
			super();
		}
	}


	private class 1
		implements Runnable
	{

		final IphoneAlarmRingActivity this$0;

		public void run()
		{
			mCursor.deactivate();
		}

		1()
		{
			this$0 = IphoneAlarmRingActivity.this;
			super();
		}
	}

}
