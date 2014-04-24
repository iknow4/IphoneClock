// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAlarmClockListItem.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.aedesign.deskclock.anim.AnimationRotateZ;
import com.aedesign.deskclock.anim.AnimationRotateZResume;
import com.aedesign.deskclock.ui.CheckboxView;
import com.aedesign.deskclock.ui.SmsLeftYuan;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			DigitalClock, IphoneAlarmClockListView, AlarmClock, Alarm

public class IphoneAlarmClockListItem extends RelativeLayout
	implements android.view.View.OnClickListener
{

	private static final boolean DEBUG = false;
	private static final long DURATION = 250L;
	private static final String TAG = "IphoneAlarmClockListItem";
	private static final int TAG_DELETE_BUTTON = 1;
	private static final int TAG_MINUS_BUTTON;
	private Alarm alarm;
	private boolean isDeletable;
	private CheckboxView mCheckBox;
	private Button mDeleteButton;
	private DigitalClock mDigitalClock;
	private Handler mHandler;
	private SmsLeftYuan mMinus;
	private Button mRightArrowIV;

	public IphoneAlarmClockListItem(Context context)
	{
		this(context, null);
	}

	public IphoneAlarmClockListItem(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		isDeletable = null;
	}

	private void changeToComplete()
	{
		boolean flag = false;
		float f = null;
		setBackgroundResource(0x7f020044);
		mMinus.clearAnimation();
		SmsLeftYuan smsleftyuan = mMinus;
		float f1 = -mMinus.getImgWidth();
		Animation animation = getAnimation(f, f1, f, f);
		smsleftyuan.startAnimation(animation);
		mMinus.setVisibility(4);
		mRightArrowIV.clearAnimation();
		Button button = mRightArrowIV;
		float f2 = mRightArrowIV.getMeasuredWidth();
		Animation animation1 = getAnimation(f, f2, f, f);
		button.startAnimation(animation1);
		mRightArrowIV.setVisibility(4);
		mDigitalClock.clearAnimation();
		if (((android.widget.RelativeLayout.LayoutParams)mDigitalClock.getLayoutParams()).leftMargin != 10)
		{
			DigitalClock digitalclock = mDigitalClock;
			float f3 = -mMinus.getImgWidth();
			Animation animation2 = getAnimation(f, f3, f, f);
			digitalclock.startAnimation(animation2);
		} else
		{
			DigitalClock digitalclock1 = mDigitalClock;
			float f4 = mMinus.getImgWidth();
			Animation animation3 = getAnimation(f4, f, f, f);
			digitalclock1.startAnimation(animation3);
		}
		if (isDeletable)
			showDeleteButton();
		mCheckBox.setVisibility(flag);
		isDeletable = flag;
	}

	private void changeToEdit()
	{
		boolean flag = false;
		float f = null;
		setBackgroundResource(0x7f020022);
		mMinus.clearAnimation();
		SmsLeftYuan smsleftyuan = mMinus;
		float f1 = -mMinus.getImgWidth();
		Animation animation = getAnimation(f1, f, f, f);
		smsleftyuan.startAnimation(animation);
		mMinus.setVisibility(flag);
		mRightArrowIV.clearAnimation();
		Button button = mRightArrowIV;
		float f2 = mRightArrowIV.getMeasuredWidth();
		Animation animation1 = getAnimation(f2, f, f, f);
		button.startAnimation(animation1);
		mRightArrowIV.setVisibility(flag);
		mDigitalClock.clearAnimation();
		if (((android.widget.RelativeLayout.LayoutParams)mDigitalClock.getLayoutParams()).leftMargin != 10)
		{
			DigitalClock digitalclock = mDigitalClock;
			float f3 = -mMinus.getImgWidth();
			Animation animation2 = getAnimation(f3, f, f, f);
			digitalclock.startAnimation(animation2);
		} else
		{
			DigitalClock digitalclock1 = mDigitalClock;
			float f4 = mMinus.getImgWidth();
			Animation animation3 = getAnimation(f, f4, f, f);
			digitalclock1.startAnimation(animation3);
		}
		mCheckBox.setVisibility(4);
		isDeletable = flag;
	}

	private void deleteItem()
	{
		if (mHandler != null)
		{
			IphoneAlarmClockListView iphonealarmclocklistview = (IphoneAlarmClockListView)getParent();
			if (iphonealarmclocklistview != null)
				iphonealarmclocklistview.setMSelectedItem(null);
			Message message = new Message();
			Alarm alarm1 = alarm;
			message.obj = alarm1;
			mHandler.sendMessage(message);
		}
	}

	private Animation getAnimation(float f, float f1, float f2, float f3)
	{
		TranslateAnimation translateanimation = new TranslateAnimation(f, f1, f2, f3);
		translateanimation.setDuration(250L);
		translateanimation.setFillAfter(true);
		return translateanimation;
	}

	public void addHandler(Handler handler)
	{
		mHandler = handler;
	}

	public void changeState(boolean flag)
	{
		if (flag)
			changeToComplete();
		else
			changeToEdit();
	}

	protected void drawableStateChanged()
	{
		super.drawableStateChanged();
		AlarmClock.mIsEdit = <no variable>;
		if (<no variable> != 0)
		{
			boolean flag = isPressed();
			dispatchSetPressed(flag);
		}
	}

	public Alarm getAlarm()
	{
		return alarm;
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
		IphoneAlarmClockListView iphonealarmclocklistview = (IphoneAlarmClockListView)getParent();
		if (iphonealarmclocklistview != null)
			if (iphonealarmclocklistview.getMSelectedItem() != null)
				iphonealarmclocklistview.getMSelectedItem().showDeleteButton();
			else
				showDeleteButton();
		continue; /* Loop/switch isn't completed */
_L3:
		if (isDeletable)
			deleteItem();
		if (true) goto _L1; else goto _L4
_L4:
	}

	protected void onFinishInflate()
	{
		super.onFinishInflate();
		DigitalClock digitalclock = (DigitalClock)findViewById(0x7f0d000b);
		mDigitalClock = digitalclock;
		CheckboxView checkboxview = (CheckboxView)findViewById(0x7f0d0013);
		mCheckBox = checkboxview;
		SmsLeftYuan smsleftyuan = (SmsLeftYuan)findViewById(0x7f0d000a);
		mMinus = smsleftyuan;
		SmsLeftYuan smsleftyuan1 = mMinus;
		Integer integer = Integer.valueOf(0);
		smsleftyuan1.setTag(integer);
		mMinus.setOnClickListener(this);
		Button button = (Button)findViewById(0x7f0d0015);
		mDeleteButton = button;
		Button button1 = mDeleteButton;
		Integer integer1 = Integer.valueOf(1);
		button1.setTag(integer1);
		mDeleteButton.setOnClickListener(this);
		Button button2 = (Button)findViewById(0x7f0d0014);
		mRightArrowIV = button2;
	}

	public void setAlarm(Alarm alarm1)
	{
		alarm = alarm1;
	}

	public void showDeleteButton()
	{
		float f;
		boolean flag;
		long l;
		boolean flag1;
		float f1;
		boolean flag2;
		f = 0x42b40000;
		flag = null;
		l = 250L;
		flag1 = true;
		f1 = null;
		flag2 = isDeletable;
		if (!flag2) goto _L2; else goto _L1
_L1:
		IphoneAlarmClockListView iphonealarmclocklistview;
		Button button = mDeleteButton;
		button.setClickable(flag);
		iphonealarmclocklistview = (IphoneAlarmClockListView)getParent();
		if (iphonealarmclocklistview != null) goto _L4; else goto _L3
_L3:
		return;
_L4:
		iphonealarmclocklistview.setMSelectedItem(null);
		float f2 = mDeleteButton.getMeasuredWidth();
		TranslateAnimation translateanimation = new TranslateAnimation(f1, f2, f1, f1);
		translateanimation.setDuration(l);
		AnimationRotateZResume animationrotatezresume = new AnimationRotateZResume(f);
		animationrotatezresume.setDuration(l);
		animationrotatezresume.setFillAfter(flag1);
		mMinus.toRotate(animationrotatezresume);
		mDeleteButton.clearAnimation();
		mDeleteButton.startAnimation(translateanimation);
		Button button1 = mDeleteButton;
		button1.setVisibility(8);
_L7:
		boolean flag3 = isDeletable;
		Object obj;
		IphoneAlarmClockListView iphonealarmclocklistview1;
		TranslateAnimation translateanimation1;
		AnimationRotateZ animationrotatez;
		if (!flag3)
			obj = flag1;
		else
			obj = flag;
		isDeletable = ((boolean) (obj));
_L6:
		if (true) goto _L3; else goto _L2
_L2:
		obj = mDeleteButton;
		((Button) (obj)).setClickable(flag1);
		iphonealarmclocklistview1 = (IphoneAlarmClockListView)getParent();
		if (iphonealarmclocklistview1 == null) goto _L6; else goto _L5
_L5:
		iphonealarmclocklistview1.setMSelectedItem(this);
		obj = (float)mDeleteButton.getMeasuredWidth();
		translateanimation1 = new TranslateAnimation(((float) (obj)), f1, f1, f1);
		translateanimation1.setDuration(l);
		animationrotatez = new AnimationRotateZ(f);
		animationrotatez.setDuration(l);
		animationrotatez.setFillAfter(flag1);
		mMinus.toRotate(animationrotatez);
		mDeleteButton.clearAnimation();
		mDeleteButton.startAnimation(translateanimation1);
		obj = mDeleteButton;
		((Button) (obj)).setVisibility(flag);
		  goto _L7
	}

	public void updateViews()
	{
		byte byte0;
		boolean flag1;
		byte0 = 4;
		boolean flag = false;
		AlarmClock.mIsEdit = <no variable>;
		Object obj;
		if (<no variable> != 0)
		{
			int i = 0x7f020022;
			setBackgroundResource(i);
		} else
		{
			int j = 0x7f020044;
			setBackgroundResource(j);
		}
		mDigitalClock.clearAnimation();
		obj = (android.widget.RelativeLayout.LayoutParams)mDigitalClock.getLayoutParams();
		AlarmClock.mIsEdit = <no variable>;
		if (<no variable> != 0)
		{
			flag1 = mMinus.getImgWidth();
			flag1 += 10;
		} else
		{
			flag1 = 10;
		}
		obj.leftMargin = ((flag1) ? 1 : 0);
		mMinus.clearAnimation();
		obj = mMinus;
		AlarmClock.mIsEdit = flag1;
		if (flag1)
			flag1 = flag;
		else
			flag1 = byte0;
		((SmsLeftYuan) (obj)).setVisibility(flag1);
		mCheckBox.clearAnimation();
		obj = mCheckBox;
		AlarmClock.mIsEdit = flag1;
		if (flag1)
			flag1 = byte0;
		else
			flag1 = flag;
		((CheckboxView) (obj)).setVisibility(flag1);
		mRightArrowIV.clearAnimation();
		obj = mRightArrowIV;
		AlarmClock.mIsEdit = flag1;
		if (flag1)
			flag1 = flag;
		else
			flag1 = byte0;
		((Button) (obj)).setVisibility(flag1);
		mDeleteButton.clearAnimation();
		obj = mDeleteButton;
		AlarmClock.mIsEdit = flag1;
		if (!flag1) goto _L2; else goto _L1
_L1:
		flag1 = isDeletable;
		if (!flag1) goto _L2; else goto _L3
_L3:
		flag1 = flag;
_L5:
		((Button) (obj)).setVisibility(flag1);
		return;
_L2:
		flag1 = byte0;
		if (true) goto _L5; else goto _L4
_L4:
	}
}
