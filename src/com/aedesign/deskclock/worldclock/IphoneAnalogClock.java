// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAnalogClock.java

package com.aedesign.deskclock.worldclock;

import android.content.*;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.aedesign.deskclock.listener.ClockBroadcastListener;
import com.aedesign.deskclock.listener.IphoneWorldClockUpdateListener;
import java.util.Calendar;
import java.util.TimeZone;

// Referenced classes of package com.aedesign.deskclock.worldclock:
//			IphoneClockBroadcastThread

public class IphoneAnalogClock extends View
	implements ClockBroadcastListener
{

	private static final boolean DEBUG = false;
	private static final String M12 = "h:mm";
	private static final String M24 = "kk:mm";
	private static final String TAG = "IphoneAnalogClock";
	private static final int mSecondUpdateMessage = 10000;
	private TextView mAmPmTextView;
	private boolean mAttached;
	private Time mCalendar;
	private boolean mChanged;
	private Drawable mCircleBackHand;
	private Context mContext;
	private Drawable mDial;
	private int mDialHeight;
	private int mDialWidth;
	private final Handler mHandler;
	private float mHour;
	private Drawable mHourHand;
	private final BroadcastReceiver mIntentReceiver;
	private IphoneWorldClockUpdateListener mListener;
	private Drawable mMinuteHand;
	private float mMinutes;
	private int mSecond;
	private Drawable mSecondHand;
	private Handler mSecondUpdateHandler;
	private String mTimeZone;
	private TextView mWorldClockTime;

	public IphoneAnalogClock(Context context)
	{
		this(context, null);
	}

	public IphoneAnalogClock(Context context, AttributeSet attributeset)
	{
		this(context, attributeset, 0);
	}

	public IphoneAnalogClock(Context context, AttributeSet attributeset, int i)
	{
		super(context, attributeset, i);
		Handler handler = new Handler();
		mHandler = handler;
		2 2_1 = new 2();
		mIntentReceiver = 2_1;
		mContext = context;
		setResources(true);
		Time time = new Time();
		mCalendar = time;
		int j = mDial.getIntrinsicWidth();
		mDialWidth = j;
		int k = mDial.getIntrinsicHeight();
		mDialHeight = k;
	}

	private void onTimeChanged()
	{
		mCalendar.setToNow();
		int i = mCalendar.hour;
		int j = mCalendar.minute;
		int k = mCalendar.second;
		mSecond = k;
		float f = j;
		float f1 = (float)k / 0x42700000;
		f += f1;
		mMinutes = f;
		f = i;
		f1 = mMinutes / 0x42700000;
		f += f1;
		mHour = f;
		mChanged = true;
		TextView textview = mWorldClockTime;
		if (textview != null)
		{
			Calendar calendar = Calendar.getInstance();
			Object obj = TimeZone.getTimeZone(mTimeZone);
			calendar.setTimeZone(((TimeZone) (obj)));
			boolean flag = DateFormat.is24HourFormat(getContext());
			CharSequence charsequence;
			if (flag)
				flag = "kk:mm";
			else
				flag = "h:mm";
			charsequence = DateFormat.format(flag, calendar);
			mWorldClockTime.setText(charsequence);
			flag = DateFormat.is24HourFormat(getContext());
			if (flag == 0)
			{
				flag = mAmPmTextView;
				int l = mHour != 0x41400000;
				if (l > 0)
					l = 0x7f0a0008;
				else
					l = 0x7f0a0007;
				flag.setText(l);
			}
		}
	}

	private void setResources(boolean flag)
	{
		Object obj = mContext;
		Resources resources = ((Context) (obj)).getResources();
		Drawable drawable;
		if (flag)
			obj = 0x7f02002a;
		else
			obj = 0x7f020029;
		obj = resources.getDrawable(((int) (obj)));
		mDial = ((Drawable) (obj));
		if (flag)
			obj = 0x7f020042;
		else
			obj = 0x7f020043;
		obj = resources.getDrawable(((int) (obj)));
		mHourHand = ((Drawable) (obj));
		if (flag)
			obj = 0x7f020045;
		else
			obj = 0x7f020046;
		obj = resources.getDrawable(((int) (obj)));
		mMinuteHand = ((Drawable) (obj));
		obj = resources.getDrawable(0x7f020047);
		mSecondHand = ((Drawable) (obj));
		if (flag)
			obj = 0x7f020041;
		else
			obj = 0x7f020040;
		drawable = resources.getDrawable(((int) (obj)));
		mCircleBackHand = ((Drawable) (obj));
	}

	public int dayComparCurrentTimeZone()
	{
		byte byte0;
		Time time;
		Time time1;
		int i;
		int j;
		byte0 = null;
		time = new Time();
		time.setToNow();
		String s = TimeZone.getTimeZone(mTimeZone).getID();
		time1 = new Time(s);
		time1.setToNow();
		i = time.monthDay;
		j = time1.monthDay;
		if (i <= j) goto _L2; else goto _L1
_L1:
		byte0 = -1;
_L4:
		return byte0;
_L2:
		int k = time.monthDay;
		int l = time1.monthDay;
		if (k < l)
			byte0 = 1;
		if (true) goto _L4; else goto _L3
_L3:
	}

	public String getTimeZone()
	{
		return mTimeZone;
	}

	protected void onAttachedToWindow()
	{
		super.onAttachedToWindow();
		if (!mAttached)
		{
			mAttached = true;
			IntentFilter intentfilter = new IntentFilter();
			intentfilter.addAction("android.intent.action.TIME_TICK");
			intentfilter.addAction("android.intent.app.IPHONE_CLOCK");
			Context context = getContext();
			BroadcastReceiver broadcastreceiver = mIntentReceiver;
			Handler handler = mHandler;
			context.registerReceiver(broadcastreceiver, intentfilter, null, handler);
			1 1_1 = new 1();
			mSecondUpdateHandler = 1_1;
			onTimeChanged();
			IphoneClockBroadcastThread.getInstance(getContext()).addClockListener(this);
		}
	}

	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		if (mAttached)
		{
			Context context = getContext();
			BroadcastReceiver broadcastreceiver = mIntentReceiver;
			context.unregisterReceiver(broadcastreceiver);
			IphoneClockBroadcastThread.getInstance(getContext()).removeClockListener(this);
			mAttached = null;
		}
	}

	/**
	 * @deprecated Method onDraw is deprecated
	 */

	protected void onDraw(Canvas canvas)
	{
		this;
		JVM INSTR monitorenter ;
		super.onDraw(canvas);
		boolean flag = mChanged;
		if (flag)
		{
			boolean flag1 = null;
			mChanged = flag1;
		}
		int i = getRight();
		int j = getLeft();
		int k = i - j;
		int l = getBottom();
		int i1 = getTop();
		int j1 = l - i1;
		int k1 = k / 2;
		int l1 = j1 / 2;
		Drawable drawable = mDial;
		int i2 = drawable.getIntrinsicWidth();
		int j2 = drawable.getIntrinsicHeight();
		boolean flag2 = null;
		int k2 = k;
		int l2 = i2;
		if (k2 < l2 || j1 < j2)
		{
			flag2 = true;
			float f = k;
			float f1 = i2;
			float f2 = f / f1;
			float f3 = j1;
			float f4 = j2;
			float f5 = f3 / f4;
			float f6 = Math.min(f2, f5);
			canvas.save();
			float f7 = k1;
			float f8 = l1;
			Canvas canvas1 = canvas;
			float f9 = f6;
			float f10 = f6;
			float f11 = f7;
			float f12 = f8;
			canvas1.scale(f9, f10, f11, f12);
		}
		if (flag)
		{
			int i3 = i2 / 2;
			int j3 = k1 - i3;
			int k3 = j2 / 2;
			int l3 = l1 - k3;
			int i4 = i2 / 2 + k1;
			int j4 = j2 / 2 + l1;
			Drawable drawable1 = drawable;
			int k4 = j3;
			int l4 = l3;
			int i5 = i4;
			int j5 = j4;
			drawable1.setBounds(k4, l4, i5, j5);
		}
		Drawable drawable2 = drawable;
		Canvas canvas2 = canvas;
		drawable2.draw(canvas2);
		canvas.save();
		float f13 = (mHour / 0x41400000) * 0x43b40000;
		float f14 = k1;
		float f15 = l1;
		Canvas canvas3 = canvas;
		float f16 = f13;
		float f17 = f14;
		float f18 = f15;
		canvas3.rotate(f16, f17, f18);
		Drawable drawable3 = mHourHand;
		int l6;
		if (flag)
		{
			int k5 = drawable3.getIntrinsicWidth();
			int l5 = drawable3.getIntrinsicHeight();
			int i6 = k5 / 2;
			int j6 = k1 - i6;
			int k6 = l1 - l5;
			l6 += 5;
			int i7 = k5 / 2 + k1;
			int j7 = l1 + 5;
			Drawable drawable4 = drawable3;
			int k7 = j6;
			int l7 = k6;
			int i8 = i7;
			int j8 = j7;
			drawable4.setBounds(k7, l7, i8, j8);
		}
		Drawable drawable5 = drawable3;
		Canvas canvas4 = canvas;
		drawable5.draw(canvas4);
		canvas.restore();
		canvas.save();
		float f19 = (mMinutes / 0x42700000) * 0x43b40000;
		float f20 = k1;
		float f21 = l1;
		Canvas canvas5 = canvas;
		float f22 = f19;
		float f23 = f20;
		float f24 = f21;
		canvas5.rotate(f22, f23, f24);
		Drawable drawable6 = mMinuteHand;
		if (flag)
		{
			int k8 = drawable6.getIntrinsicWidth();
			int l8 = drawable6.getIntrinsicHeight();
			int i9 = k8 / 2;
			int j9 = k1 - i9;
			int k9 = l1 - l8;
			l6 += 6;
			int l9 = k8 / 2 + k1;
			int i10 = l1 + 6;
			Drawable drawable7 = drawable6;
			int j10 = j9;
			int k10 = k9;
			int l10 = l9;
			int i11 = i10;
			drawable7.setBounds(j10, k10, l10, i11);
		}
		Drawable drawable8 = drawable6;
		Canvas canvas6 = canvas;
		drawable8.draw(canvas6);
		canvas.restore();
		canvas.save();
		Drawable drawable9 = mCircleBackHand;
		if (flag)
		{
			int j11 = drawable9.getIntrinsicWidth();
			int k11 = drawable9.getIntrinsicHeight();
			int l11 = j11 / 2;
			int i12 = k1 - l11;
			int j12 = k11 / 2;
			int k12 = l1 - j12;
			int l12 = j11 / 2 + k1;
			int i13 = k11 / 2 + l1;
			Drawable drawable10 = drawable9;
			int j13 = i12;
			int k13 = k12;
			int l13 = l12;
			int i14 = i13;
			drawable10.setBounds(j13, k13, l13, i14);
		}
		Drawable drawable11 = drawable9;
		Canvas canvas7 = canvas;
		drawable11.draw(canvas7);
		canvas.restore();
		canvas.save();
		float f25 = ((float)mSecond / 0x42700000) * 0x43b40000;
		float f26 = k1;
		float f27 = l1;
		Canvas canvas8 = canvas;
		float f28 = f25;
		float f29 = f26;
		float f30 = f27;
		canvas8.rotate(f28, f29, f30);
		Drawable drawable12 = mSecondHand;
		if (flag)
		{
			int j14 = drawable12.getIntrinsicWidth();
			int k14 = drawable12.getIntrinsicHeight();
			int l14 = j14 / 2;
			int i15 = k1 - l14;
			int j15 = l1 - k14;
			l6 += 5;
			int k15 = j14 / 2 + k1;
			int l15 = l1 + 5;
			Drawable drawable13 = drawable12;
			int i16 = i15;
			int j16 = j15;
			int k16 = k15;
			int l16 = l15;
			drawable13.setBounds(i16, j16, k16, l16);
		}
		Drawable drawable14 = drawable12;
		Canvas canvas9 = canvas;
		drawable14.draw(canvas9);
		canvas.restore();
		if (flag2 != null)
			canvas.restore();
		this;
		JVM INSTR monitorexit ;
		return;
		Exception exception;
		exception;
		throw exception;
	}

	protected void onMeasure(int i, int j)
	{
		int k = android.view.View.MeasureSpec.getMode(i);
		int l = android.view.View.MeasureSpec.getSize(i);
		int i1 = android.view.View.MeasureSpec.getMode(j);
		int j1 = android.view.View.MeasureSpec.getSize(j);
		float f = 0x3f800000;
		float f1 = 0x3f800000;
		if (k != 0)
		{
			int k1 = mDialWidth;
			if (l < k1)
			{
				float f2 = l;
				float f3 = mDialWidth;
				f = f2 / f3;
			}
		}
		if (i1 != 0)
		{
			int l1 = mDialHeight;
			if (j1 < l1)
			{
				float f4 = j1;
				float f5 = mDialHeight;
				f1 = f4 / f5;
			}
		}
		float f6 = Math.min(f, f1);
		int i2 = resolveSize((int)((float)mDialWidth * f6), i);
		int j2 = resolveSize((int)((float)mDialHeight * f6), j);
		setMeasuredDimension(i2, j2);
	}

	protected void onSizeChanged(int i, int j, int k, int l)
	{
		super.onSizeChanged(i, j, k, l);
		mChanged = true;
	}

	public void setAmPmTextView(TextView textview)
	{
		mAmPmTextView = textview;
	}

	public void setIphoneWorldClockUpdateListener(IphoneWorldClockUpdateListener iphoneworldclockupdatelistener)
	{
		mListener = iphoneworldclockupdatelistener;
	}

	public void setTimeZone(String s)
	{
		mTimeZone = s;
		TimeZone timezone = TimeZone.getTimeZone(mTimeZone);
		String s1 = timezone.getID();
		Time time = new Time(s1);
		mCalendar = time;
		long l = Calendar.getInstance().getTimeInMillis();
		if (timezone.getOffset(<no variable>) < 0)
			setResources(null);
		onTimeChanged();
	}

	public void setWorldClockTimeTextView(TextView textview)
	{
		mWorldClockTime = textview;
	}

	public void updateSecond(Object obj, int i)
	{
		mSecond = i;
		Message message = new Message();
		message.what = 10000;
		mSecondUpdateHandler.sendMessage(message);
	}



/*
	static int access$102(IphoneAnalogClock iphoneanalogclock, int i)
	{
		iphoneanalogclock.mSecond = i;
		return i;
	}

*/


	private class 2 extends BroadcastReceiver
	{

		final IphoneAnalogClock this$0;

		public void onReceive(Context context, Intent intent)
		{
			if (intent.getAction().equals("android.intent.app.IPHONE_CLOCK"))
			{
				IphoneAnalogClock iphoneanalogclock = IphoneAnalogClock.this;
				int i = intent.getIntExtra("currentSecond", 0);
				iphoneanalogclock.mSecond = i;
			}
			onTimeChanged();
			invalidate();
		}

		2()
		{
			this$0 = IphoneAnalogClock.this;
			super();
		}
	}


	private class 1 extends Handler
	{

		final IphoneAnalogClock this$0;

		public void handleMessage(Message message)
		{
			if (message.what == 10000)
			{
				IphoneAnalogClock iphoneanalogclock = IphoneAnalogClock.this;
				Drawable drawable = mSecondHand;
				iphoneanalogclock.invalidateDrawable(drawable);
				invalidate();
			}
		}

		1()
		{
			this$0 = IphoneAnalogClock.this;
			super();
		}
	}

}
