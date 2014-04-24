// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SetAlarm.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.*;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.widget.*;
import com.aedesign.deskclock.ui.CheckboxView;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;
import com.aedesign.deskclock.ui.timeselector.*;
import java.util.Calendar;
import java.util.List;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Alarms, ToastMaster, RepeatPreference, Alarm, 
//			AlarmPreference

public class SetAlarm extends IphoneNoTitleActivity
	implements android.app.TimePickerDialog.OnTimeSetListener, android.view.View.OnClickListener, android.widget.AbsListView.OnScrollListener
{

	private static final int ALARM_LABEL_PICKED = 3003;
	private static final int ALARM_REPEAT_PICKED = 3001;
	private static final int ALARM_RING_PICKED = 3002;
	private static final boolean DEBUG = false;
	public static final String INTENT_LABEL_NAME = "label";
	public static final String INTENT_VALUE_NAME = "repeat";
	private static final String TAG = "SetAlarm";
	private static final int TAG_ALERT_LAYOUT = 1;
	private static final int TAG_AMPM_LIST_VIEW = 2;
	private static final int TAG_HOUR_LIST_VIEW = 0;
	private static final int TAG_LABEL_LAYOUT = 2;
	private static final int TAG_MINUTE_LIST_VIEW = 1;
	private static final int TAG_REPEAT_LAYOUT;
	Alarm alarm;
	private int beforeHour;
	int beforeMinute;
	private Alarm.DaysOfWeek daysOfWeek;
	private boolean isHour24;
	private AlarmPreference mAlarmPref;
	private RelativeLayout mAlertLayout;
	private String mAlertRingTone;
	private TextView mAlertTextView;
	private TimeListAdapter mAmPmAdapter;
	private TimeListView mAmPmListView;
	private boolean mEnabled;
	private int mHour;
	private TimeCircleListAdapter mHourAdapter;
	private TimeCircleListView mHourListView;
	private int mId;
	private EditTextPreference mLabel;
	private RelativeLayout mLabelLayout;
	private TextView mLabelTextView;
	private TimeCircleListAdapter mMinuteAdapter;
	private TimeCircleListView mMinuteListView;
	private int mMinutes;
	private RelativeLayout mRepeatLayout;
	private RepeatPreference mRepeatPref;
	private TextView mRepeatTextView;
	private Preference mTimePref;
	private RelativeLayout mTimeSelectorBGLayout;
	private RelativeLayout mTimeSelectorLayout;
	private CheckBoxPreference mVibratePref;
	private CheckboxView mWaitCheckbox;

	public SetAlarm()
	{
		isHour24 = null;
		beforeHour = -1;
	}

	private void changeAmPm()
	{
		byte byte0 = 10;
		byte byte1 = 12;
		int i = 11;
		int j = 1;
		int k = Integer.parseInt(mHourListView.getSelectionString());
		boolean flag = null;
		int l = beforeHour;
		if (l == byte0 && k == byte1)
		{
			flag = true;
		} else
		{
			int i1 = beforeHour;
			if (i1 == i && (k == byte1 || k == j))
			{
				flag = true;
			} else
			{
				int j1 = beforeHour;
				if (j1 == byte1 && (k == i || k == byte0))
				{
					flag = true;
				} else
				{
					int k1 = beforeHour;
					if (k1 == j && k == i)
						flag = true;
				}
			}
		}
		if (flag != null)
		{
			TimeListView timelistview = mAmPmListView;
			i = mAmPmListView.getCurrentPosition();
			if (i == 0)
				i = j;
			else
				i = 0;
			timelistview.setTimeOnPositon(i);
		}
		beforeHour = k;
	}

	static String formatToast(Context context, int i, int j, Alarm.DaysOfWeek daysofweek)
	{
		i = Alarms.calculateAlarm(i, j, daysofweek).getTimeInMillis();
		Object obj = System.currentTimeMillis();
		i -= <no variable>;
		long l = i / -4480L;
		long l2 = (i / -5536L) % 60L;
		j = l / 24L;
		long l3 = l % 24L;
		l = 0L;
		i = j != l;
		Object obj1;
		int j1;
		int k1;
		String s;
		Object aobj[];
		if (i == 0)
		{
			i = "";
		} else
		{
			long l1 = 1L;
			i = j != l1;
			if (i == 0)
			{
				i = context.getString(0x7f0a0051);
			} else
			{
				obj = ((Object) (new Object[1]));
				j1 = 0;
				obj1 = Long.toString(j);
				obj[j1] = obj1;
				i = context.getString(0x7f0a0052, ((Object []) (obj)));
			}
		}
		l = l2 != 0L;
		if (obj == 0)
		{
			obj = "";
			obj1 = obj;
		} else
		{
			int k = l2 != 1L;
			if (obj == 0)
			{
				obj = context.getString(0x7f0a0055);
				obj1 = obj;
			} else
			{
				j1 = ((int) (new Object[1]));
				String s1 = Long.toString(l2);
				j1[null] = s1;
				obj = context.getString(0x7f0a0056, j1);
				obj1 = obj;
			}
		}
		l = l3 != 0L;
		if (obj == 0)
		{
			obj = "";
			j1 = ((int) (obj));
		} else
		{
			int i1 = l3 != 1L;
			if (obj == 0)
			{
				obj = context.getString(0x7f0a0053);
				j1 = ((int) (obj));
			} else
			{
				j1 = ((int) (new Object[1]));
				String s2 = Long.toString(l3);
				j1[0] = s2;
				obj = context.getString(0x7f0a0054, j1);
				j1 = ((int) (obj));
			}
		}
		j !== 0L;
		if (j > 0)
			j = 1;
		else
			j = null;
		daysofweek = l3 != 0L;
		if (daysofweek > 0)
			daysofweek = 1;
		else
			daysofweek = null;
		l = l2 != 0L;
		if (obj > 0)
			obj = 1;
		else
			obj = null;
		if (j != null)
			j = 1;
		else
			j = null;
		if (daysofweek != null)
			daysofweek = 2;
		else
			daysofweek = null;
		j |= daysofweek;
		if (obj != null)
			daysofweek = 4;
		else
			daysofweek = null;
		k1 = j | daysofweek;
		s = context.getResources().getStringArray(0x7f070004)[j];
		aobj = new Object[3];
		j[null] = i;
		j[1] = j1;
		j[2] = obj1;
		return String.format(context, j);
	}

	private void newAlarm()
	{
		int i = Integer.parseInt((String)Alarms.addAlarm(getContentResolver()).getPathSegments().get(1));
		mId = i;
	}

	static void popAlarmSetToast(Context context, int i, int j, Alarm.DaysOfWeek daysofweek)
	{
		String s = formatToast(context, i, j, daysofweek);
		Toast toast = Toast.makeText(context, s, 1);
		ToastMaster.setToast(toast);
		toast.show();
	}

	private void saveAlarm()
	{
		byte byte0 = 12;
		byte byte1 = -1;
		int i = mId;
		if (i == byte1)
			newAlarm();
		int k = Integer.parseInt(mHourListView.getTimeOn());
		int l = mAmPmListView.getCurrentPosition();
		boolean flag = isHour24;
		int i1;
		int j1;
		boolean flag1;
		int k1;
		int l1;
		Alarm.DaysOfWeek daysofweek;
		boolean flag2;
		String s;
		String s1;
		boolean flag3;
		Intent intent;
		if (flag)
		{
			flag = k;
		} else
		{
			if (k == byte0)
				flag = k;
			else
				flag = mAmPmListView.getCurrentPosition() * 12 + k;
			flag %= 24;
		}
		mHour = ((flag) ? 1 : 0);
		flag = isHour24;
		if (flag != 0)
			mHour = k;
		else
		if (k == byte0)
		{
			int j = 1;
			if (l == j)
				j = k;
			else
				j = null;
			mHour = j;
		} else
		{
			int i2 = l * 12 + k;
			mHour = i2;
		}
		i1 = Integer.parseInt(mMinuteListView.getTimeOn());
		mMinutes = i1;
		j1 = mId;
		flag1 = mEnabled;
		k1 = mHour;
		l1 = mMinutes;
		daysofweek = daysOfWeek;
		flag2 = mWaitCheckbox.isChecked();
		s = mLabelTextView.getText().toString();
		s1 = mAlertRingTone;
		flag3 = mWaitCheckbox.isChecked();
		Alarms.setAlarm(this, j1, flag1, k1, l1, daysofweek, flag2, s, s1, flag3);
		intent = new Intent();
		setResult(byte1, intent);
	}

	private static void saveAlarm(Context context, int i, boolean flag, int j, int k, Alarm.DaysOfWeek daysofweek, boolean flag1, String s, 
			String s1, boolean flag2, boolean flag3)
	{
		Alarms.setAlarm(context, i, flag, j, k, daysofweek, flag1, s, s1, flag2);
		if (flag && flag3)
			popAlarmSetToast(context, j, k, daysofweek);
	}

	private void updateRingtone(Uri uri)
	{
		if (uri == null)
		{
			mAlertRingTone = "silent";
			TextView textview = mAlertTextView;
			String s = getString(0x7f0a0064);
			textview.setText(s);
		} else
		if (RingtoneManager.isDefault(uri))
		{
			mAlertRingTone = null;
			TextView textview1 = mAlertTextView;
			String s1 = getString(0x7f0a002c);
			textview1.setText(s1);
		} else
		{
			String s2 = uri.toString();
			mAlertRingTone = s2;
			Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
			TextView textview2 = mAlertTextView;
			String s3 = ringtone.getTitle(this);
			textview2.setText(s3);
		}
	}

	private void updateTime()
	{
		Preference preference = mTimePref;
		int i = mHour;
		int j = mMinutes;
		Alarm.DaysOfWeek daysofweek = mRepeatPref.getDaysOfWeek();
		String s = Alarms.formatTime(this, i, j, daysofweek);
		preference.setSummary(s);
	}

	protected void onActivityResult(int i, int j, Intent intent)
	{
		super.onActivityResult(i, j, intent);
		if (j == -1) goto _L2; else goto _L1
_L1:
		return;
_L2:
		switch (i)
		{
		case 3001: 
			if (intent != null)
			{
				Alarm.DaysOfWeek daysofweek = new Alarm.DaysOfWeek(0);
				daysOfWeek = daysofweek;
				boolean aflag[] = intent.getBooleanArrayExtra("repeat");
				for (int k = 0; k < 7; k++)
				{
					Alarm.DaysOfWeek daysofweek1 = daysOfWeek;
					boolean flag = aflag[k];
					daysofweek1.set(k, flag);
				}

				TextView textview = mRepeatTextView;
				String s = daysOfWeek.toString(this, true);
				textview.setText(s);
			}
			break;

		case 3002: 
			if (intent != null)
			{
				Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
				updateRingtone(uri);
			}
			break;

		case 3003: 
			if (intent != null)
			{
				CharSequence charsequence = intent.getCharSequenceExtra("label");
				mLabelTextView.setText(charsequence);
			}
			break;
		}
		if (true) goto _L1; else goto _L3
_L3:
	}

	public void onBackPressed()
	{
		finish();
	}

	public void onClick(View view)
	{
		boolean flag;
		Intent intent;
		flag = true;
		intent = new Intent();
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 2: default 48
	//	               0 49
	//	               1 86
	//	               2 200;
		   goto _L1 _L2 _L3 _L4
_L1:
		return;
_L2:
		intent.setAction("android.intent.action.alarmrepeat");
		boolean aflag[] = daysOfWeek.getBooleanArray();
		intent.putExtra("repeat", aflag);
		startActivityForResult(intent, 3001);
		continue; /* Loop/switch isn't completed */
_L3:
		intent.setAction("android.intent.action.alarmring");
		intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", null);
		intent.putExtra("android.intent.extra.ringtone.TYPE", flag);
		intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", flag);
		String s = mAlertRingTone;
		Object obj;
		if ("silent".equals(s))
			obj = null;
		else
		if (mAlertRingTone == null || mAlertRingTone.length() == 0)
			obj = RingtoneManager.getDefaultUri(4);
		else
			obj = Uri.parse(mAlertRingTone);
		intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", ((android.os.Parcelable) (obj)));
		startActivityForResult(intent, 3002);
		continue; /* Loop/switch isn't completed */
_L4:
		intent.setAction("android.intent.action.alarmlabel");
		CharSequence charsequence = mLabelTextView.getText();
		intent.putExtra("label", charsequence);
		startActivityForResult(intent, 3003);
		if (true) goto _L1; else goto _L5
_L5:
	}

	protected void onCreate(Bundle bundle)
	{
		int i = 0x7f030002;
		byte byte0 = -1;
		int j = 0;
		byte byte1 = 2;
		boolean flag = true;
		super.onCreate(bundle);
		setContentView(0x7f030011);
		int k = DateFormat.is24HourFormat(this);
		isHour24 = k;
		Object obj = (RelativeLayout)findViewById(0x7f0d002e);
		mRepeatLayout = ((RelativeLayout) (obj));
		obj = mRepeatLayout;
		Object obj1 = Integer.valueOf(j);
		((RelativeLayout) (obj)).setTag(obj1);
		mRepeatLayout.setOnClickListener(this);
		obj = (RelativeLayout)findViewById(0x7f0d0030);
		mAlertLayout = ((RelativeLayout) (obj));
		obj = mAlertLayout;
		obj1 = Integer.valueOf(flag);
		((RelativeLayout) (obj)).setTag(obj1);
		mAlertLayout.setOnClickListener(this);
		obj = (RelativeLayout)findViewById(0x7f0d0012);
		mLabelLayout = ((RelativeLayout) (obj));
		obj = mLabelLayout;
		obj1 = Integer.valueOf(byte1);
		((RelativeLayout) (obj)).setTag(obj1);
		mLabelLayout.setOnClickListener(this);
		obj = (CheckboxView)findViewById(0x7f0d0033);
		mWaitCheckbox = ((CheckboxView) (obj));
		obj = (TextView)findViewById(0x7f0d002f);
		mRepeatTextView = ((TextView) (obj));
		obj = (TextView)findViewById(0x7f0d0031);
		mAlertTextView = ((TextView) (obj));
		obj = (TextView)findViewById(0x7f0d0034);
		mLabelTextView = ((TextView) (obj));
		Resources resources = getResources();
		obj = (TimeListView)findViewById(0x7f0d0036);
		mAmPmListView = ((TimeListView) (obj));
		String as[] = resources.getStringArray(0x7f070003);
		obj = new TimeListAdapter(this, as, i);
		mAmPmAdapter = ((TimeListAdapter) (obj));
		int l = resources.getDimensionPixelOffset(0x7f090003);
		obj = mAmPmListView;
		as = mAmPmAdapter;
		((TimeListView) (obj)).init(as, byte1, l, byte1);
		obj = (TimeCircleListView)findViewById(0x7f0d0037);
		mHourListView = ((TimeCircleListView) (obj));
		obj = JVM INSTR new #509 <Class TimeCircleListAdapter>;
		boolean flag2 = isHour24;
		boolean flag1;
		int i1;
		int j1;
		TimeCircleListView timecirclelistview;
		int l1;
		Button button;
		1 1_1;
		Button button1;
		2 2_1;
		if (flag2)
			flag2 = 0x7f070000;
		else
			flag2 = 0x7f070001;
		flag2 = resources.getStringArray(flag2);
		((TimeCircleListAdapter) (obj)).TimeCircleListAdapter(this, flag2, 0x7f03001d);
		mHourAdapter = ((TimeCircleListAdapter) (obj));
		i1 = resources.getDimensionPixelOffset(0x7f090000);
		obj = mHourListView;
		flag2 = mHourAdapter;
		((TimeCircleListView) (obj)).init(flag2, j, i1, byte1);
		mHourListView.setOnScrollListener(this);
		obj = (TimeCircleListView)findViewById(0x7f0d0038);
		mMinuteListView = ((TimeCircleListView) (obj));
		flag2 = resources.getStringArray(0x7f070002);
		obj = new TimeCircleListAdapter(this, flag2, i);
		mMinuteAdapter = ((TimeCircleListAdapter) (obj));
		j1 = resources.getDimensionPixelOffset(0x7f090001);
		obj = mMinuteListView;
		flag2 = mMinuteAdapter;
		((TimeCircleListView) (obj)).init(flag2, flag, j1, byte1);
		obj = (RelativeLayout)findViewById(0x7f0d0039);
		mTimeSelectorBGLayout = ((RelativeLayout) (obj));
		obj = (RelativeLayout)findViewById(0x7f0d0035);
		mTimeSelectorLayout = ((RelativeLayout) (obj));
		obj = isHour24;
		if (!obj)
		{
			mAmPmListView.setVisibility(j);
			int k1 = resources.getDimensionPixelOffset(0x7f090005);
			((android.widget.RelativeLayout.LayoutParams)mTimeSelectorLayout.getLayoutParams()).leftMargin = k1;
			obj = mTimeSelectorBGLayout;
			flag2 = 0x7f02009b;
			((RelativeLayout) (obj)).setBackgroundResource(flag2);
		}
		obj = getIntent().getIntExtra("alarm_id", byte0);
		mId = ((int) (obj));
		obj = mId;
		if (obj == byte0)
		{
			obj = new Alarm();
			alarm = ((Alarm) (obj));
			alarm.enabled = flag;
			Time time = new Time();
			time.setToNow();
			obj = alarm;
			flag2 = time.hour;
			obj.hour = ((flag2) ? 1 : 0);
			obj = alarm;
			flag2 = time.minute;
			obj.minutes = ((flag2) ? 1 : 0);
		} else
		{
			flag1 = getContentResolver();
			flag2 = mId;
			flag1 = Alarms.getAlarm(flag1, flag2);
			alarm = flag1;
		}
		obj = alarm.daysOfWeek;
		daysOfWeek = ((Alarm.DaysOfWeek) (obj));
		obj = alarm.enabled;
		mEnabled = ((boolean) (obj));
		obj = mLabelTextView;
		flag2 = alarm.label;
		((TextView) (obj)).setText(flag2);
		obj = mRepeatTextView;
		flag2 = alarm.daysOfWeek.toString(this, flag);
		((TextView) (obj)).setText(flag2);
		obj = mWaitCheckbox;
		flag2 = alarm.waitable;
		((CheckboxView) (obj)).setChecked(flag2);
		obj = alarm.alert;
		updateRingtone(((Uri) (obj)));
		obj = alarm.hour;
		mHour = ((int) (obj));
		obj = alarm.minutes;
		mMinutes = ((int) (obj));
		obj = mAmPmListView;
		flag2 = mHour;
		if (flag2 < 12)
			flag2 = j;
		else
			flag2 = flag;
		((TimeListView) (obj)).setTimeOnPositon(flag2);
		obj = mHourListView;
		flag2 = isHour24;
		if (flag2)
			flag2 = mHour;
		else
			flag2 = mHour - flag;
		((TimeCircleListView) (obj)).setTimeOnPositon(flag2);
		flag1 = isHour24;
		if (flag1)
			flag1 = mHour;
		else
			flag1 = mHour % 12;
		beforeHour = ((flag1) ? 1 : 0);
		timecirclelistview = mMinuteListView;
		l1 = mMinutes;
		flag1.setTimeOnPositon(l1);
		button = (Button)findViewById(0x7f0d002c);
		1_1 = new 1();
		button.setOnClickListener(flag1);
		button1 = (Button)findViewById(0x7f0d002b);
		2_1 = new 2();
		button1.setOnClickListener(flag1);
	}

	public void onScroll(AbsListView abslistview, int i, int j, int k)
	{
		int l = mHourListView;
		if (abslistview == l && !isHour24)
			changeAmPm();
	}

	public void onScrollStateChanged(AbsListView abslistview, int i)
	{
	}

	public void onTimeSet(TimePicker timepicker, int i, int j)
	{
		mHour = i;
		mMinutes = j;
		updateTime();
		mEnabled = true;
	}

	void setTestAlarm()
	{
		boolean flag = true;
		Calendar calendar = Calendar.getInstance();
		long l = System.currentTimeMillis();
		calendar.setTimeInMillis(<no variable>);
		int i = calendar.get(11);
		int j = calendar.get(12);
		l = j + 1;
		int k = l % 60;
		Context context;
		int i1;
		int j1;
		Alarm.DaysOfWeek daysofweek;
		String s;
		String s1;
		boolean flag1;
		SetAlarm setalarm;
		boolean flag2;
		boolean flag3;
		if (j == 0)
			context = flag;
		else
			context = null;
		i1 = i + context;
		j1 = mId;
		daysofweek = mRepeatPref.getDaysOfWeek();
		s = mLabel.getText();
		s1 = mAlarmPref.getAlertString();
		flag1 = mWaitCheckbox.isChecked();
		setalarm = this;
		flag2 = flag;
		flag3 = flag;
		saveAlarm(context, j1, flag, i1, k, daysofweek, flag2, s, s1, flag3, flag1);
	}


	private class 1
		implements android.view.View.OnClickListener
	{

		final SetAlarm this$0;

		public void onClick(View view)
		{
			saveAlarm();
			finish();
		}

		1()
		{
			this$0 = SetAlarm.this;
			super();
		}
	}


	private class 2
		implements android.view.View.OnClickListener
	{

		final SetAlarm this$0;

		public void onClick(View view)
		{
			finish();
			overridePendingTransition(0x7f040004, 0x7f040001);
		}

		2()
		{
			this$0 = SetAlarm.this;
			super();
		}
	}

}
