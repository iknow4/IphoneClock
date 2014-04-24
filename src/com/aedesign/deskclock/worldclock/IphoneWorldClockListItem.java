// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneWorldClockListItem.java

package com.aedesign.deskclock.worldclock;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.aedesign.deskclock.anim.AnimationRotateZ;
import com.aedesign.deskclock.anim.AnimationRotateZResume;
import com.aedesign.deskclock.ui.SmsLeftYuan;

// Referenced classes of package com.aedesign.deskclock.worldclock:
//			IphoneAnalogClock, IphoneWorldClockListView

public class IphoneWorldClockListItem extends RelativeLayout
	implements android.view.View.OnClickListener
{

	private static final boolean DEBUG = false;
	private static final long DURATION = 250L;
	private static final String TAG = "IphoneWorldClockListItem";
	private static final int TAG_DELETE_BUTTON = 1;
	private static final int TAG_SMSLEFT_BUTTON;
	private boolean isDeleteBtnShow;
	private TextView mAreaTextView;
	private View mDelete;
	private Handler mHandler;
	private int mItemId;
	private SmsLeftYuan mMinus;
	private Button mSortButton;
	private LinearLayout mTimeLayout;
	private IphoneAnalogClock mWorldClock;

	public IphoneWorldClockListItem(Context context)
	{
		super(context);
		isDeleteBtnShow = null;
	}

	public IphoneWorldClockListItem(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		isDeleteBtnShow = null;
	}

	public IphoneWorldClockListItem(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		isDeleteBtnShow = null;
	}

	public static TranslateAnimation getTranslateAnimation(float f, float f1, float f2, float f3, long l)
	{
		TranslateAnimation translateanimation = new TranslateAnimation(f, f1, f2, f3);
		translateanimation.setDuration(l);
		translateanimation.setFillAfter(true);
		return translateanimation;
	}

	public void changeToComplete()
	{
		boolean flag = null;
		int i = 0x43090000;
		long l = 250L;
		Animation animation = null;
		LinearLayout linearlayout1;
		float f7;
		float f8;
		float f9;
		float f10;
		long l1;
		TranslateAnimation translateanimation3;
		SmsLeftYuan smsleftyuan;
		float f11;
		float f12;
		float f13;
		TranslateAnimation translateanimation4;
		TextView textview1;
		int j;
		if (((android.widget.RelativeLayout.LayoutParams)mAreaTextView.getLayoutParams()).leftMargin != 10)
		{
			mAreaTextView.clearAnimation();
			TextView textview = mAreaTextView;
			float f = -mMinus.getImgWidth();
			float f1 = animation;
			float f2 = animation;
			TranslateAnimation translateanimation = getTranslateAnimation(animation, f, f1, f2, l);
			textview.startAnimation(translateanimation);
			mWorldClock.clearAnimation();
			IphoneAnalogClock iphoneanalogclock = mWorldClock;
			float f3 = animation;
			float f4 = animation;
			TranslateAnimation translateanimation1 = getTranslateAnimation(animation, 0xc2980000, f3, f4, l);
			iphoneanalogclock.startAnimation(translateanimation1);
			mTimeLayout.clearAnimation();
			LinearLayout linearlayout = mTimeLayout;
			float f5 = animation;
			float f6 = animation;
			TranslateAnimation translateanimation2 = getTranslateAnimation(animation, 0xc3090000, f5, f6, l);
			linearlayout.startAnimation(translateanimation2);
		} else
		{
			mAreaTextView.clearAnimation();
			TextView textview2 = mAreaTextView;
			float f14 = mMinus.getImgWidth();
			float f15 = animation;
			float f16 = animation;
			float f17 = animation;
			long l2 = l;
			TranslateAnimation translateanimation5 = getTranslateAnimation(f14, f15, f16, f17, l2);
			textview2.startAnimation(translateanimation5);
			mWorldClock.clearAnimation();
			IphoneAnalogClock iphoneanalogclock1 = mWorldClock;
			float f18 = animation;
			float f19 = animation;
			float f20 = animation;
			long l3 = l;
			TranslateAnimation translateanimation6 = getTranslateAnimation(0x42980000, f18, f19, f20, l3);
			iphoneanalogclock1.startAnimation(translateanimation6);
			mTimeLayout.clearAnimation();
			LinearLayout linearlayout2 = mTimeLayout;
			float f21 = i;
			float f22 = animation;
			float f23 = animation;
			float f24 = animation;
			long l4 = l;
			TranslateAnimation translateanimation7 = getTranslateAnimation(f21, f22, f23, f24, l4);
			linearlayout2.startAnimation(translateanimation7);
		}
		mTimeLayout.clearAnimation();
		linearlayout1 = mTimeLayout;
		f7 = i;
		f8 = animation;
		f9 = animation;
		f10 = animation;
		l1 = l;
		translateanimation3 = getTranslateAnimation(f7, f8, f9, f10, l1);
		linearlayout1.startAnimation(translateanimation3);
		mTimeLayout.setVisibility(flag);
		mMinus.clearAnimation();
		smsleftyuan = mMinus;
		f11 = -mMinus.getImgWidth();
		f12 = animation;
		f13 = animation;
		translateanimation4 = getTranslateAnimation(animation, f11, f12, f13, l);
		smsleftyuan.startAnimation(animation);
		mMinus.setVisibility(4);
		mMinus.setClickable(flag);
		mSortButton.setVisibility(8);
		textview1 = mAreaTextView;
		j = mAreaTextView.getWidth() - 37;
		animation.setWidth(j);
	}

	public void changeToEdit()
	{
		int i = 0;
		long l = 250L;
		int j = null;
		LinearLayout linearlayout;
		float f5;
		float f6;
		float f7;
		long l1;
		TranslateAnimation translateanimation2;
		SmsLeftYuan smsleftyuan;
		float f8;
		float f9;
		float f10;
		TranslateAnimation translateanimation3;
		TextView textview1;
		int k;
		if (((android.widget.RelativeLayout.LayoutParams)mAreaTextView.getLayoutParams()).leftMargin != 10)
		{
			mAreaTextView.clearAnimation();
			TextView textview = mAreaTextView;
			float f = -mMinus.getImgWidth();
			float f1 = j;
			float f2 = j;
			TranslateAnimation translateanimation = getTranslateAnimation(f, j, f1, f2, l);
			textview.startAnimation(translateanimation);
			mWorldClock.clearAnimation();
			IphoneAnalogClock iphoneanalogclock = mWorldClock;
			float f3 = j;
			float f4 = j;
			TranslateAnimation translateanimation1 = getTranslateAnimation(0xc2980000, j, f3, f4, l);
			iphoneanalogclock.startAnimation(translateanimation1);
		} else
		{
			mAreaTextView.clearAnimation();
			TextView textview2 = mAreaTextView;
			float f11 = mMinus.getImgWidth();
			float f12 = j;
			float f13 = j;
			float f14 = j;
			long l2 = l;
			TranslateAnimation translateanimation4 = getTranslateAnimation(f12, f11, f13, f14, l2);
			textview2.startAnimation(translateanimation4);
			mWorldClock.clearAnimation();
			IphoneAnalogClock iphoneanalogclock1 = mWorldClock;
			float f15 = j;
			float f16 = j;
			float f17 = j;
			long l3 = l;
			TranslateAnimation translateanimation5 = getTranslateAnimation(f15, 0x42980000, f16, f17, l3);
			iphoneanalogclock1.startAnimation(translateanimation5);
		}
		mTimeLayout.clearAnimation();
		linearlayout = mTimeLayout;
		f5 = j;
		f6 = j;
		f7 = j;
		l1 = l;
		translateanimation2 = getTranslateAnimation(f5, 0x43090000, f6, f7, l1);
		linearlayout.startAnimation(translateanimation2);
		mTimeLayout.setVisibility(4);
		mMinus.clearAnimation();
		smsleftyuan = mMinus;
		f8 = -mMinus.getImgWidth();
		f9 = j;
		f10 = j;
		translateanimation3 = getTranslateAnimation(f8, j, f9, f10, l);
		smsleftyuan.startAnimation(translateanimation3);
		mMinus.setVisibility(i);
		mMinus.setClickable(true);
		mSortButton.setVisibility(i);
		textview1 = mAreaTextView;
		k = mAreaTextView.getWidth();
		j += 37;
		textview1.setWidth(j);
	}

	public int getItemId()
	{
		return mItemId;
	}

	public void onClick(View view)
	{
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 1: default 32
	//	               0 33
	//	               1 69;
		   goto _L1 _L2 _L3
_L1:
		return;
_L2:
		IphoneWorldClockListView iphoneworldclocklistview = (IphoneWorldClockListView)getParent();
		if (iphoneworldclocklistview != null)
			if (iphoneworldclocklistview.getMSelectedItem() != null)
				iphoneworldclocklistview.getMSelectedItem().showDeleteBtn();
			else
				showDeleteBtn();
		continue; /* Loop/switch isn't completed */
_L3:
		if (mHandler != null)
		{
			Message message = mHandler.obtainMessage();
			message.obj = this;
			mHandler.sendMessage(message);
			Message message1 = mHandler.obtainMessage(1);
			Boolean boolean1 = Boolean.valueOf(null);
			message1.obj = boolean1;
			mHandler.sendMessage(message1);
		}
		if (true) goto _L1; else goto _L4
_L4:
	}

	protected void onFinishInflate()
	{
		super.onFinishInflate();
		SmsLeftYuan smsleftyuan = (SmsLeftYuan)findViewById(0x7f0d000a);
		mMinus = smsleftyuan;
		mMinus.setVisibility(0);
		SmsLeftYuan smsleftyuan1 = mMinus;
		Integer integer = Integer.valueOf(0);
		smsleftyuan1.setTag(integer);
		mMinus.setOnClickListener(this);
		IphoneAnalogClock iphoneanalogclock = (IphoneAnalogClock)findViewById(0x7f0d004d);
		mWorldClock = iphoneanalogclock;
		LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0d004e);
		mTimeLayout = linearlayout;
		TextView textview = (TextView)findViewById(0x7f0d004c);
		mAreaTextView = textview;
		Button button = (Button)findViewById(0x7f0d0052);
		mSortButton = button;
		Button button1 = (Button)findViewById(0x7f0d0053);
		mDelete = button1;
		View view = mDelete;
		Integer integer1 = Integer.valueOf(1);
		view.setTag(integer1);
		mDelete.setOnClickListener(this);
	}

	public void setItemId(int i)
	{
		mItemId = i;
	}

	public void setMHandler(Handler handler)
	{
		mHandler = handler;
	}

	public void showDeleteBtn()
	{
		if (isDeleteBtnShow) goto _L2; else goto _L1
_L1:
		IphoneWorldClockListView iphoneworldclocklistview;
		isDeleteBtnShow = true;
		mDelete.setClickable(true);
		iphoneworldclocklistview = (IphoneWorldClockListView)getParent();
		if (iphoneworldclocklistview != null) goto _L4; else goto _L3
_L3:
		return;
_L4:
		iphoneworldclocklistview.setMSelectedItem(this);
		float f = mDelete.getMeasuredWidth();
		TranslateAnimation translateanimation = new TranslateAnimation(f, null, null, null);
		translateanimation.setDuration(250L);
		AnimationRotateZ animationrotatez = new AnimationRotateZ(0x42b40000);
		animationrotatez.setDuration(250L);
		animationrotatez.setFillAfter(true);
		mMinus.toRotate(animationrotatez);
		mWorldClock.clearAnimation();
		int i = getResources().getDimensionPixelOffset(0x7f09000a);
		Message message;
		Boolean boolean1;
		if (((android.widget.RelativeLayout.LayoutParams)mWorldClock.getLayoutParams()).leftMargin != i)
		{
			IphoneAnalogClock iphoneanalogclock = mWorldClock;
			TranslateAnimation translateanimation2 = getTranslateAnimation(null, 0xc1400000, null, null, 250L);
			iphoneanalogclock.startAnimation(translateanimation2);
		} else
		{
			IphoneAnalogClock iphoneanalogclock1 = mWorldClock;
			TranslateAnimation translateanimation3 = getTranslateAnimation(0x42980000, 0x42580000, null, null, 250L);
			iphoneanalogclock1.startAnimation(translateanimation3);
		}
		mDelete.clearAnimation();
		mDelete.startAnimation(translateanimation);
		mDelete.setVisibility(0);
_L6:
		if (mHandler != null)
		{
			message = mHandler.obtainMessage(1);
			boolean1 = Boolean.valueOf(isDeleteBtnShow);
			message.obj = boolean1;
			mHandler.sendMessage(message);
		}
		if (true) goto _L3; else goto _L2
_L2:
		isDeleteBtnShow = null;
		mDelete.setClickable(null);
		iphoneworldclocklistview = (IphoneWorldClockListView)getParent();
		if (iphoneworldclocklistview == null) goto _L3; else goto _L5
_L5:
		iphoneworldclocklistview.setMSelectedItem(null);
		float f1 = mDelete.getMeasuredWidth();
		TranslateAnimation translateanimation1 = new TranslateAnimation(null, f1, null, null);
		translateanimation1.setDuration(250L);
		AnimationRotateZResume animationrotatezresume = new AnimationRotateZResume(0x42b40000);
		animationrotatezresume.setDuration(250L);
		animationrotatezresume.setFillAfter(true);
		mMinus.toRotate(animationrotatezresume);
		mWorldClock.clearAnimation();
		int j = getResources().getDimensionPixelOffset(0x7f09000a);
		if (((android.widget.RelativeLayout.LayoutParams)mWorldClock.getLayoutParams()).leftMargin != j)
		{
			IphoneAnalogClock iphoneanalogclock2 = mWorldClock;
			TranslateAnimation translateanimation4 = getTranslateAnimation(0xc1400000, null, null, null, 250L);
			iphoneanalogclock2.startAnimation(translateanimation4);
		} else
		{
			IphoneAnalogClock iphoneanalogclock3 = mWorldClock;
			TranslateAnimation translateanimation5 = getTranslateAnimation(0x42580000, 0x42980000, null, null, 250L);
			iphoneanalogclock3.startAnimation(translateanimation5);
		}
		mDelete.clearAnimation();
		mDelete.startAnimation(translateanimation1);
		mDelete.setVisibility(8);
		  goto _L6
	}

	public void updateViews()
	{
		int i = 0x7f09000a;
		boolean flag = true;
		byte byte0 = 4;
		boolean flag1 = false;
		mAreaTextView.clearAnimation();
		mWorldClock.clearAnimation();
		mTimeLayout.clearAnimation();
		mMinus.clearAnimation();
		mDelete.clearAnimation();
		boolean flag2 = mSortButton;
		flag2.clearAnimation();
		IphoneWorldClockListView.mEdit = flag2;
		if (flag2 != null)
		{
			int k = getResources().getDimensionPixelOffset(0x7f090009);
			int l = getResources().getDimensionPixelOffset(i);
			Object obj = (android.widget.RelativeLayout.LayoutParams)mAreaTextView.getLayoutParams();
			int i1 = k + 10;
			obj.leftMargin = i1;
			obj = (android.widget.RelativeLayout.LayoutParams)mWorldClock.getLayoutParams();
			int j1 = l + 76;
			obj.leftMargin = j1;
			mAreaTextView.setWidth(139);
			mTimeLayout.setVisibility(byte0);
			mMinus.setVisibility(flag1);
			boolean flag3 = isDeleteBtnShow;
			int j;
			Message message;
			Boolean boolean1;
			if (flag3)
			{
				mDelete.setVisibility(flag1);
				mDelete.setClickable(flag);
				flag3 = mSortButton;
				flag3.setVisibility(8);
			} else
			{
				mDelete.setVisibility(byte0);
				mDelete.setClickable(flag1);
				flag4 = mSortButton;
				flag4.setVisibility(flag1);
			}
		} else
		{
			((android.widget.RelativeLayout.LayoutParams)mAreaTextView.getLayoutParams()).leftMargin = 10;
			int k1 = getResources().getDimensionPixelOffset(i);
			((android.widget.RelativeLayout.LayoutParams)mWorldClock.getLayoutParams()).leftMargin = k1;
			mAreaTextView.setWidth(102);
			mTimeLayout.setVisibility(flag1);
			mMinus.setVisibility(byte0);
			mMinus.setClickable(flag1);
			mDelete.setVisibility(byte0);
			mDelete.setClickable(flag1);
			flag4 = mSortButton;
			flag4.setVisibility(byte0);
		}
		flag3 = mHandler;
		if (flag3 != null)
		{
			message = mHandler.obtainMessage(flag);
			j = mDelete.getVisibility();
			boolean flag4;
			if (j == 0)
				flag4 = flag;
			else
				flag4 = flag1;
			boolean1 = Boolean.valueOf(flag4);
			message.obj = flag4;
			mHandler.sendMessage(message);
		}
	}
}
