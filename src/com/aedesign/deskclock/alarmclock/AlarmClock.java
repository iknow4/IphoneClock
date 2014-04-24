// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmClock.java

package com.aedesign.deskclock.alarmclock;

import android.app.Activity;
import android.content.*;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.aedesign.deskclock.ui.CheckboxView;
import java.text.DateFormatSymbols;
import java.util.Calendar;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			IphoneAlarmClockListView, IphoneAlarmClockListItem, Alarms, SetAlarm, 
//			ToastMaster, Alarm, DigitalClock

public class AlarmClock extends Activity
	implements android.view.View.OnClickListener
{
	class AlarmTimeAdapter extends CursorAdapter
	{

		private boolean canGoAlarmSet;
		private Context mContext;
		private android.view.View.OnTouchListener mOnTouchListener;
		final AlarmClock this$0;

		public void bindView(View view, Context context, Cursor cursor)
		{
			byte byte0 = 8;
			boolean flag = false;
			final Alarm alarm = JVM INSTR new #42  <Class Alarm>;
			Alarm alarm1 = alarm;
			Cursor cursor1 = cursor;
			alarm1.Alarm(cursor1);
			IphoneAlarmClockListItem iphonealarmclocklistitem = (IphoneAlarmClockListItem)view;
			iphonealarmclocklistitem.setAlarm(alarm);
			CheckboxView checkboxview = (CheckboxView)iphonealarmclocklistitem.findViewById(0x7f0d0013);
			boolean flag1 = alarm.enabled;
			checkboxview.setChecked(flag1);
			class 1
				implements OnStatusChangeListener
			{

				final AlarmTimeAdapter this$1;
				final Alarm val$alarm;

				public void onStatusChanged(View view1, boolean flag2)
				{
					AlarmClock alarmclock1 = 0;
					int k = alarm.id;
					Alarms.enableAlarm(alarmclock1, k, flag2);
					if (flag2)
					{
						AlarmClock alarmclock2 = 0;
						int l = alarm.hour;
						int i1 = alarm.minutes;
						Alarm.DaysOfWeek daysofweek1 = alarm.daysOfWeek;
						SetAlarm.popAlarmSetToast(alarmclock2, l, i1, daysofweek1);
					}
				}

				1()
				{
					this$1 = AlarmTimeAdapter.this;
					alarm = alarm1;
					super();
				}
			}

			1 1_1 = new 1();
			checkboxview.setOnStatusChangeListener(1_1);
			DigitalClock digitalclock = (DigitalClock)iphonealarmclocklistitem.findViewById(0x7f0d000b);
			Calendar calendar = Calendar.getInstance();
			int i = alarm.hour;
			calendar.set(11, i);
			int j = alarm.minutes;
			calendar.set(12, j);
			digitalclock.updateTime(calendar);
			TextView textview = (TextView)digitalclock.findViewById(0x7f0d0011);
			Alarm.DaysOfWeek daysofweek = alarm.daysOfWeek;
			AlarmClock alarmclock = AlarmClock.this;
			String s = daysofweek.toString(alarmclock, flag);
			TextView textview1;
			if (s != null && s.length() != 0)
			{
				textview.setText(s);
				textview.setVisibility(flag);
			} else
			{
				textview.setVisibility(byte0);
			}
			textview1 = (TextView)digitalclock.findViewById(0x7f0d0012);
			if (alarm.label != null && alarm.label.length() != 0)
			{
				String s1 = alarm.label;
				textview1.setText(s1);
				textview1.setVisibility(flag);
			} else
			{
				textview1.setVisibility(byte0);
			}
			iphonealarmclocklistitem.updateViews();
		}

		public View getView(int i, View view, ViewGroup viewgroup)
		{
			Context context = mContext;
			Cursor cursor = mCursor;
			View view1 = newView(context, cursor, viewgroup);
			mCursor.moveToPosition(i);
			Context context1 = mContext;
			Cursor cursor1 = mCursor;
			bindView(view1, context1, cursor1);
			return view1;
		}

		public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
		{
			IphoneAlarmClockListItem iphonealarmclocklistitem = (IphoneAlarmClockListItem)mFactory.inflate(0x7f030003, viewgroup, null);
			iphonealarmclocklistitem.setClickable(true);
			android.view.View.OnTouchListener ontouchlistener = mOnTouchListener;
			iphonealarmclocklistitem.setOnTouchListener(ontouchlistener);
			Handler handler = mHandler;
			iphonealarmclocklistitem.addHandler(handler);
			TextView textview = (TextView)iphonealarmclocklistitem.findViewById(0x7f0d000e);
			String s = mAm;
			textview.setText(s);
			TextView textview1 = (TextView)iphonealarmclocklistitem.findViewById(0x7f0d000f);
			String s1 = mPm;
			textview1.setText(s1);
			((DigitalClock)iphonealarmclocklistitem.findViewById(0x7f0d000b)).setLive(null);
			return iphonealarmclocklistitem;
		}



/*
		static boolean access$502(AlarmTimeAdapter alarmtimeadapter, boolean flag)
		{
			alarmtimeadapter.canGoAlarmSet = flag;
			return flag;
		}

*/

		public AlarmTimeAdapter(Context context, Cursor cursor)
		{
			this$0 = AlarmClock.this;
			super(context, cursor);
			canGoAlarmSet = null;
			class 2
				implements android.view.View.OnTouchListener
			{

				final AlarmTimeAdapter this$1;

				public boolean onTouch(View view, MotionEvent motionevent)
				{
					boolean flag;
					boolean flag1;
					flag = null;
					flag1 = true;
					AlarmClock.mIsEdit = <no variable>;
					if (<no variable> != 0) goto _L2; else goto _L1
_L1:
					Object obj = flag1;
_L6:
					return ((boolean) (obj));
_L2:
					obj = motionevent.getAction();
					obj;
					JVM INSTR tableswitch 0 1: default 52
				//				               0 58
				//				               1 127;
					   goto _L3 _L4 _L5
_L3:
					obj = flag;
					  goto _L6
_L4:
					obj = mAlarmsList.getMSelectedItem();
					if (obj == null) goto _L8; else goto _L7
_L7:
					mAlarmsList.getMSelectedItem().showDeleteButton();
					canGoAlarmSet = flag;
					obj = flag1;
					  goto _L6
_L8:
					AlarmTimeAdapter alarmtimeadapter = AlarmTimeAdapter.this;
					alarmtimeadapter.canGoAlarmSet = flag1;
					  goto _L3
_L5:
					boolean flag2 = canGoAlarmSet;
					if (flag2)
					{
						IphoneAlarmClockListItem iphonealarmclocklistitem = (IphoneAlarmClockListItem)view;
						AlarmClock alarmclock1 = 0;
						Intent intent = new Intent(alarmclock1, com/aedesign/deskclock/alarmclock/SetAlarm);
						int i = iphonealarmclocklistitem.getAlarm().id;
						intent.putExtra("alarm_id", i);
						alarmclock1 = 0;
						alarmclock1.startActivity(intent);
					}
					  goto _L3
				}

				2()
				{
					this$1 = AlarmTimeAdapter.this;
					super();
				}
			}

			2 2_1 = new 2();
			mOnTouchListener = 2_1;
			mContext = context;
		}
	}


	static final int CLOCKS[] = {
		0x7f030005, 0x7f030008, 0x7f030006, 0x7f030007, 0x7f03000c
	};
	static final boolean DEBUG = false;
	static final int MAX_ALARM_COUNT = 12;
	static final String PREFERENCES = "AlarmClock";
	static final String PREF_CLOCK_FACE = "face";
	static final String PREF_SHOW_CLOCK = "show_clock";
	private static final String TAG = "AlarmClock";
	private static final int TAG_ADD_ALARM = 0;
	private static final int TAG_EDIT_ALARM = 1;
	public static boolean mIsEdit;
	private Button mAddAlarmButton;
	private AlarmTimeAdapter mAlarmsAdapter;
	private IphoneAlarmClockListView mAlarmsList;
	private String mAm;
	private Cursor mCursor;
	private Button mEditAlarmButton;
	private LayoutInflater mFactory;
	private Handler mHandler;
	private TextView mNoAlarmText;
	private String mPm;
	private SharedPreferences mPrefs;

	public AlarmClock()
	{
		1 1_1 = new 1();
		mHandler = 1_1;
	}

	private void changeItemState()
	{
		boolean flag = mAlarmsList;
		int i = flag.getChildCount();
		for (int j = 0; j < i; j++)
		{
			flag = (IphoneAlarmClockListItem)mAlarmsList.getChildAt(j);
			mIsEdit = <no variable>;
			flag.changeState(<no variable>);
		}

		mIsEdit = flag;
		int k;
		int l;
		if (flag == null)
			flag = true;
		else
			flag = null;
		flag = mIsEdit;
		mIsEdit = flag;
		if (flag)
		{
			flag = 0x7f020031;
			k = ((flag) ? 1 : 0);
		} else
		{
			flag = 0x7f020032;
			k = ((flag) ? 1 : 0);
		}
		mIsEdit = flag;
		if (flag)
			l = 0x7f0a0035;
		else
			l = 0x7f0a0036;
		mEditAlarmButton.setBackgroundResource(k);
		mEditAlarmButton.setText(l);
	}

	private void updateLayout()
	{
		byte byte0 = 8;
		boolean flag = false;
		setContentView(0x7f030001);
		Button button = (Button)findViewById(0x7f0d0007);
		mAddAlarmButton = button;
		Button button1 = mAddAlarmButton;
		Integer integer = Integer.valueOf(flag);
		button1.setTag(integer);
		mAddAlarmButton.setOnClickListener(this);
		Button button2 = (Button)findViewById(0x7f0d0005);
		mEditAlarmButton = button2;
		Button button3 = mEditAlarmButton;
		Integer integer1 = Integer.valueOf(1);
		button3.setTag(integer1);
		mEditAlarmButton.setOnClickListener(this);
		IphoneAlarmClockListView iphonealarmclocklistview = (IphoneAlarmClockListView)findViewById(0x7f0d0008);
		mAlarmsList = iphonealarmclocklistview;
		Cursor cursor = mCursor;
		AlarmTimeAdapter alarmtimeadapter = new AlarmTimeAdapter(this, cursor);
		mAlarmsAdapter = alarmtimeadapter;
		IphoneAlarmClockListView iphonealarmclocklistview1 = mAlarmsList;
		AlarmTimeAdapter alarmtimeadapter1 = mAlarmsAdapter;
		iphonealarmclocklistview1.setAdapter(alarmtimeadapter1);
		TextView textview = (TextView)findViewById(0x7f0d0009);
		mNoAlarmText = textview;
		if (mCursor.getCount() > 0)
		{
			mNoAlarmText.setVisibility(byte0);
			mAlarmsList.setVisibility(flag);
			mEditAlarmButton.setVisibility(flag);
		} else
		{
			mNoAlarmText.setVisibility(flag);
			mAlarmsList.setVisibility(byte0);
			mEditAlarmButton.setVisibility(4);
			mEditAlarmButton.setPressed(flag);
		}
	}

	protected void inflateClock()
	{
		TextView textview = (TextView)findViewById(0x7f0d000e);
		TextView textview1 = (TextView)findViewById(0x7f0d000f);
		if (textview != null)
		{
			String s = mAm;
			textview.setText(s);
		}
		if (textview1 != null)
		{
			String s1 = mPm;
			textview1.setText(s1);
		}
	}

	protected void onActivityResult(int i, int j, Intent intent)
	{
		int k = 0;
		if (j == -1)
		{
			Cursor cursor = Alarms.getAlarmsCursor(getContentResolver());
			mCursor = cursor;
			if (mCursor.getCount() == 1)
			{
				AlarmTimeAdapter alarmtimeadapter = mAlarmsAdapter;
				Cursor cursor1 = mCursor;
				alarmtimeadapter.changeCursor(cursor1);
				mNoAlarmText.setVisibility(8);
				mAlarmsList.setVisibility(k);
				mEditAlarmButton.setVisibility(k);
			}
		}
	}

	public void onClick(View view)
	{
		((Integer)view.getTag()).intValue();
		JVM INSTR tableswitch 0 1: default 32
	//	               0 33
	//	               1 63;
		   goto _L1 _L2 _L3
_L1:
		return;
_L2:
		mAlarmsList.setMSelectedItem(null);
		Intent intent = new Intent(this, com/aedesign/deskclock/alarmclock/SetAlarm);
		startActivityForResult(intent, 200);
		continue; /* Loop/switch isn't completed */
_L3:
		changeItemState();
		if (true) goto _L1; else goto _L4
_L4:
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		String as[] = (new DateFormatSymbols()).getAmPmStrings();
		String s = as[null];
		mAm = s;
		String s1 = as[1];
		mPm = s1;
		LayoutInflater layoutinflater = LayoutInflater.from(this);
		mFactory = layoutinflater;
		SharedPreferences sharedpreferences = getSharedPreferences("AlarmClock", 0);
		mPrefs = sharedpreferences;
		Cursor cursor = Alarms.getAlarmsCursor(getContentResolver());
		mCursor = cursor;
		updateLayout();
	}

	protected void onDestroy()
	{
		super.onDestroy();
		ToastMaster.cancelToast();
		mCursor.deactivate();
	}

	protected void onResume()
	{
		super.onResume();
		mIsEdit = <no variable>;
		if (<no variable> != 0)
		{
			boolean flag = mIsEdit;
			int i = mAlarmsList.getChildCount();
			for (int j = 0; j < i; j++)
			{
				((IphoneAlarmClockListItem)mAlarmsList.getChildAt(j)).updateViews();
				((IphoneAlarmClockListItem)mAlarmsList.getChildAt(j)).requestLayout();
			}

			mEditAlarmButton.setBackgroundResource(0x7f020032);
			mEditAlarmButton.setText(0x7f0a0036);
		}
	}

	static 
	{
		boolean flag = mIsEdit;
	}








/*
	static Cursor access$602(AlarmClock alarmclock, Cursor cursor)
	{
		alarmclock.mCursor = cursor;
		return cursor;
	}

*/




	private class 1 extends Handler
	{

		final AlarmClock this$0;

		public void handleMessage(Message message)
		{
			byte byte0 = 8;
			boolean flag = false;
			super.handleMessage(message);
			if (message.obj instanceof Alarm)
			{
				Alarm alarm = (Alarm)message.obj;
				AlarmClock alarmclock = AlarmClock.this;
				int i = alarm.id;
				Alarms.deleteAlarm(alarmclock, i);
				AlarmClock alarmclock1 = AlarmClock.this;
				Cursor cursor = Alarms.getAlarmsCursor(getContentResolver());
				alarmclock1.mCursor = cursor;
				if (mAlarmsAdapter != null)
				{
					AlarmTimeAdapter alarmtimeadapter = mAlarmsAdapter;
					Cursor cursor1 = mCursor;
					alarmtimeadapter.changeCursor(cursor1);
				}
				mAlarmsList.setMSelectedItem(null);
				if (mCursor.getCount() > 0)
				{
					mNoAlarmText.setVisibility(byte0);
					mAlarmsList.setVisibility(flag);
					mEditAlarmButton.setVisibility(flag);
				} else
				{
					mNoAlarmText.setVisibility(flag);
					mAlarmsList.setVisibility(byte0);
					mEditAlarmButton.setVisibility(4);
					mEditAlarmButton.setPressed(flag);
				}
			}
		}

		1()
		{
			this$0 = AlarmClock.this;
			super();
		}
	}

}
