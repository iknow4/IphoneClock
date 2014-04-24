// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTimerAlertVoiceActivity.java

package com.aedesign.deskclock.timer;

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

public class IphoneTimerAlertVoiceActivity extends IphoneNoTitleActivity
	implements android.view.View.OnClickListener, Runnable, OnStatusChangeListener
{

	private static final int DELAY_MS_SELECTION_PLAYED = 300;
	private static final String TAG = "RingtonePickerActivity";
	private final int TAG_CANCEL = 1;
	private final int TAG_COMPLETE = null;
	private final int TAG_RINGTONE_DEFAULT = 2;
	private Button mBtnCancel;
	private Button mBtnComplete;
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
	private android.content.DialogInterface.OnClickListener mRingtoneClickListener;
	private RingtoneManager mRingtoneManager;
	private int mSampleRingtonePos;
	private int mSilentPos;
	private int mStaticItemCount;
	private Uri mUriForDefaultItem;

	public IphoneTimerAlertVoiceActivity()
	{
		mSilentPos = -1;
		mDefaultRingtonePos = -1;
		mClickedPos = -1;
		mSampleRingtonePos = -1;
		1 1_1 = new 1();
		mRingtoneClickListener = 1_1;
	}

	private void addDefaultRingView(LinearLayout linearlayout)
	{
		linearlayout.removeAllViews();
		IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)mInflater.inflate(0x7f03001a, linearlayout, null);
		iphonelabelcheckview.setOnStatusChangListener(this);
		iphonelabelcheckview.setBackgroundResource(0x7f02006c);
		Integer integer = Integer.valueOf(2);
		iphonelabelcheckview.setTag(integer);
		String s = getResources().getString(0x7f0a0022);
		iphonelabelcheckview.setLabelText(s);
		iphonelabelcheckview.setOnClickListener(this);
		linearlayout.addView(iphonelabelcheckview);
	}

	private int addDefaultRingtoneItem(ListView listview)
	{
		return null;
	}

	private int addSilentItem(ListView listview)
	{
		return null;
	}

	private int addStaticItem(ListView listview, int i)
	{
		return listview.getHeaderViewsCount() - 1;
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
		int i = 1;
		if (linearlayout != null && cursor != null) goto _L2; else goto _L1
_L1:
		return;
_L2:
		linearlayout.removeAllViews();
		int j = 0;
		do
		{
			int k = cursor.getCount();
			if (j >= k)
				continue;
			IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)mInflater.inflate(0x7f03001a, linearlayout, null);
			int l;
			String s;
			final int position;
			3 3_1;
			if (j == 0)
			{
				if (cursor.getCount() == i)
					iphonelabelcheckview.setBackgroundResource(0x7f02006c);
				else
					iphonelabelcheckview.setBackgroundResource(0x7f02007b);
			} else
			{
				int i1 = cursor.getCount() - i;
				if (j == i1)
					iphonelabelcheckview.setBackgroundResource(0x7f02006f);
				else
					iphonelabelcheckview.setBackgroundResource(0x7f020075);
			}
			cursor.moveToPosition(j);
			l = cursor.getColumnIndex("title");
			s = cursor.getString(l);
			iphonelabelcheckview.setLabelText(s);
			position = j;
			iphonelabelcheckview.setOnStatusChangListener(this);
			3_1 = new 3();
			iphonelabelcheckview.setOnClickListener(3_1);
			linearlayout.addView(iphonelabelcheckview);
			j++;
		} while (true);
		if (true) goto _L1; else goto _L3
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
		int i = -1;
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 2: default 40
	//	               0 57
	//	               1 149
	//	               2 41;
		   goto _L1 _L2 _L3 _L4
_L1:
		return;
_L4:
		mSampleRingtonePos = i;
		playRingtone(i, 300);
		continue; /* Loop/switch isn't completed */
_L2:
		Intent intent = new Intent();
		Object obj = null;
		View view1;
		2 2_1;
		if (mSampleRingtonePos != i)
		{
			RingtoneManager ringtonemanager = mRingtoneManager;
			int j = mSampleRingtonePos;
			obj = ringtonemanager.getRingtoneUri(j);
		}
		intent.putExtra("android.intent.extra.ringtone.PICKED_URI", ((android.os.Parcelable) (obj)));
		setResult(i, intent);
		view1 = getWindow().getDecorView();
		2_1 = new 2();
		view1.post(2_1);
		finish();
		continue; /* Loop/switch isn't completed */
_L3:
		finish();
		if (true) goto _L1; else goto _L5
_L5:
	}

	protected void onCreate(Bundle bundle)
	{
		int i = 0;
		int j = -1;
		boolean flag = true;
		super.onCreate(bundle);
		setContentView(0x7f030015);
		Button button = (Button)findViewById(0x7f0d0049);
		mBtnCancel = button;
		Button button1 = mBtnCancel;
		Integer integer = Integer.valueOf(flag);
		button1.setTag(integer);
		mBtnCancel.setOnClickListener(this);
		Button button2 = (Button)findViewById(0x7f0d004a);
		mBtnComplete = button2;
		Button button3 = mBtnComplete;
		Integer integer1 = Integer.valueOf(i);
		button3.setTag(integer1);
		mBtnComplete.setOnClickListener(this);
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
		boolean flag2 = intent.getBooleanExtra("android.intent.extra.ringtone.SHOW_SILENT", flag);
		mHasSilentItem = flag2;
		RingtoneManager ringtonemanager = new RingtoneManager(this);
		mRingtoneManager = ringtonemanager;
		boolean flag3 = intent.getBooleanExtra("android.intent.extra.ringtone.INCLUDE_DRM", flag);
		mRingtoneManager.setIncludeDrm(flag3);
		int k = intent.getIntExtra("android.intent.extra.ringtone.TYPE", j);
		if (k != j)
			mRingtoneManager.setType(k);
		Cursor cursor = mRingtoneManager.getCursor();
		mCursor = cursor;
		int l = mRingtoneManager.inferStreamType();
		setVolumeControlStream(l);
		Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.EXISTING_URI");
		mExistingUri = uri;
		LinearLayout linearlayout2 = mLinearRingDefault;
		addDefaultRingView(linearlayout2);
		LinearLayout linearlayout3 = mLinearRingStandard;
		Cursor cursor1 = mCursor;
		inflateListview(linearlayout3, cursor1);
		if (RingtoneManager.isDefault(mExistingUri))
		{
			mSampleRingtonePos = j;
			IphoneLabelCheckView iphonelabelcheckview = (IphoneLabelCheckView)mLinearRingDefault.getChildAt(i);
			mCurrentLabelCheckView = iphonelabelcheckview;
			((IphoneLabelCheckView)mLinearRingDefault.getChildAt(i)).setCheck(flag);
		} else
		{
			RingtoneManager ringtonemanager1 = mRingtoneManager;
			Uri uri1 = mExistingUri;
			int i1 = ringtonemanager1.getRingtonePosition(uri1);
			mSampleRingtonePos = i1;
			LinearLayout linearlayout4 = mLinearRingStandard;
			int j1 = mSampleRingtonePos;
			IphoneLabelCheckView iphonelabelcheckview1 = (IphoneLabelCheckView)linearlayout4.getChildAt(j1);
			mCurrentLabelCheckView = iphonelabelcheckview1;
			LinearLayout linearlayout5 = mLinearRingStandard;
			int k1 = mSampleRingtonePos;
			((IphoneLabelCheckView)linearlayout5.getChildAt(k1)).setCheck(flag);
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
		Ringtone ringtone = null;
		if (mSampleRingtonePos != -1)
		{
			RingtoneManager ringtonemanager = mRingtoneManager;
			int i = mSampleRingtonePos;
			ringtone = ringtonemanager.getRingtone(i);
		}
		if (ringtone != null)
			ringtone.play();
	}


/*
	static int access$002(IphoneTimerAlertVoiceActivity iphonetimeralertvoiceactivity, int i)
	{
		iphonetimeralertvoiceactivity.mClickedPos = i;
		return i;
	}

*/




/*
	static int access$302(IphoneTimerAlertVoiceActivity iphonetimeralertvoiceactivity, int i)
	{
		iphonetimeralertvoiceactivity.mSampleRingtonePos = i;
		return i;
	}

*/

	private class 1
		implements android.content.DialogInterface.OnClickListener
	{

		final IphoneTimerAlertVoiceActivity this$0;

		public void onClick(DialogInterface dialoginterface, int i)
		{
			mClickedPos = i;
			playRingtone(i, 0);
		}

		1()
		{
			this$0 = IphoneTimerAlertVoiceActivity.this;
			super();
		}
	}


	private class 3
		implements android.view.View.OnClickListener
	{

		final IphoneTimerAlertVoiceActivity this$0;
		final int val$position;

		public void onClick(View view)
		{
			IphoneTimerAlertVoiceActivity iphonetimeralertvoiceactivity = IphoneTimerAlertVoiceActivity.this;
			int i = position;
			iphonetimeralertvoiceactivity.mSampleRingtonePos = i;
			IphoneTimerAlertVoiceActivity iphonetimeralertvoiceactivity1 = IphoneTimerAlertVoiceActivity.this;
			int j = position;
			iphonetimeralertvoiceactivity1.playRingtone(j, 300);
		}

		3()
		{
			this$0 = IphoneTimerAlertVoiceActivity.this;
			position = i;
			super();
		}
	}


	private class 2
		implements Runnable
	{

		final IphoneTimerAlertVoiceActivity this$0;

		public void run()
		{
			mCursor.deactivate();
		}

		2()
		{
			this$0 = IphoneTimerAlertVoiceActivity.this;
			super();
		}
	}

}
