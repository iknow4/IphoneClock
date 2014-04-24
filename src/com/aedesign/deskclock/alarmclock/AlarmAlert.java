// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmAlert.java

package com.aedesign.deskclock.alarmclock;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.Toast;
import java.util.Calendar;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Alarm, Alarms, AlarmReceiver, Log

public class AlarmAlert extends Activity
	implements android.content.DialogInterface.OnClickListener
{

	private static final String DEFAULT_SNOOZE = "10";
	private static final String DEFAULT_VOLUME_BEHAVIOR = "2";
	private Alarm mAlarm;
	private BroadcastReceiver mReceiver;
	private int mVolumeBehavior;

	public AlarmAlert()
	{
		1 1_1 = new 1();
		mReceiver = 1_1;
	}

	private void dismiss(boolean flag)
	{
		if (!flag)
		{
			NotificationManager notificationmanager = getNotificationManager();
			int i = mAlarm.id;
			notificationmanager.cancel(i);
			Intent intent = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
			stopService(intent);
		}
		finish();
	}

	private NotificationManager getNotificationManager()
	{
		return (NotificationManager)getSystemService("notification");
	}

	private void setTitle()
	{
	}

	private void snooze()
	{
		int i = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("snooze_duration", "10"));
		long l = System.currentTimeMillis();
		long l1 = 60000 * i;
		long l2 = <no variable> + l1;
		int j = mAlarm.id;
		Alarms.saveSnoozeAlert(this, j, l2);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(l2);
		String s = mAlarm.getLabelOrDefault(this);
		Object aobj[] = new Object[1];
		aobj[0] = s;
		String s1 = getString(0x7f0a0066, aobj);
		Intent intent = new Intent(this, com/aedesign/deskclock/alarmclock/AlarmReceiver);
		intent.setAction("cancel_snooze");
		int k = mAlarm.id;
		intent.putExtra("alarm_id", k);
		int i1 = mAlarm.id;
		PendingIntent pendingintent = PendingIntent.getBroadcast(this, i1, intent, 0);
		NotificationManager notificationmanager = getNotificationManager();
		Notification notification = new Notification(0x7f02009c, s1, 0L);
		Object aobj1[] = new Object[1];
		String s2 = Alarms.formatTime(this, calendar);
		aobj1[0] = s2;
		String s3 = getString(0x7f0a0067, aobj1);
		notification.setLatestEventInfo(this, s1, s3, pendingintent);
		notification.deleteIntent = pendingintent;
		int j1 = notification.flags | 0x10;
		notification.flags = j1;
		int k1 = mAlarm.id;
		notificationmanager.notify(k1, notification);
		Object aobj2[] = new Object[1];
		Integer integer = Integer.valueOf(i);
		aobj2[0] = integer;
		String s4 = getString(0x7f0a0050, aobj2);
		Log.v(s4);
		Toast.makeText(this, s4, 1).show();
		Intent intent1 = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
		stopService(intent1);
		finish();
	}

	private void updateLayout()
	{
		setTitle();
		android.app.AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f0a0025).setNegativeButton(0x7f0a000c, this);
		if (mAlarm.waitable)
			builder = builder.setPositiveButton(0x7f0a002b, this);
		builder.show();
	}

	public boolean dispatchKeyEvent(KeyEvent keyevent)
	{
		boolean flag;
		int i;
		int j;
		int k;
		flag = null;
		i = 1;
		j = keyevent.getAction();
		if (j == i)
			k = i;
		else
			k = ((flag) ? 1 : 0);
		j = keyevent.getKeyCode();
		j;
		JVM INSTR lookupswitch 4: default 68
	//	               24: 84
	//	               25: 84
	//	               27: 84
	//	               80: 84;
		   goto _L1 _L2 _L2 _L2 _L2
_L1:
		j = super.dispatchKeyEvent(keyevent);
_L9:
		return j;
_L2:
		if (k == null) goto _L4; else goto _L3
_L3:
		j = mVolumeBehavior;
		j;
		JVM INSTR tableswitch 1 2: default 120
	//	               1 126
	//	               2 133;
		   goto _L5 _L6 _L7
_L5:
		break; /* Loop/switch isn't completed */
_L6:
		break; /* Loop/switch isn't completed */
_L4:
		j = i;
		if (true) goto _L9; else goto _L8
_L8:
		snooze();
		  goto _L4
_L7:
		dismiss(flag);
		  goto _L4
	}

	protected View inflateView(LayoutInflater layoutinflater)
	{
		return layoutinflater.inflate(0x7f030000, null);
	}

	public void onClick(DialogInterface dialoginterface, int i)
	{
		i;
		JVM INSTR tableswitch -2 -1: default 24
	//	               -2 32
	//	               -1 25;
		   goto _L1 _L2 _L3
_L1:
		return;
_L3:
		snooze();
		continue; /* Loop/switch isn't completed */
_L2:
		dismiss(null);
		if (true) goto _L1; else goto _L4
_L4:
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		Alarm alarm = (Alarm)getIntent().getParcelableExtra("intent.extra.alarm");
		mAlarm = alarm;
		int i = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("volume_button_setting", "2"));
		mVolumeBehavior = i;
		requestWindowFeature(1);
		getWindow().addFlags(0x680080);
		updateLayout();
		BroadcastReceiver broadcastreceiver = mReceiver;
		IntentFilter intentfilter = new IntentFilter("alarm_killed");
		registerReceiver(broadcastreceiver, intentfilter);
	}

	public void onDestroy()
	{
		super.onDestroy();
		BroadcastReceiver broadcastreceiver = mReceiver;
		unregisterReceiver(broadcastreceiver);
	}

	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		Alarm alarm = (Alarm)intent.getParcelableExtra("intent.extra.alarm");
		mAlarm = alarm;
		setTitle();
	}

	protected void onStop()
	{
		super.onStop();
		finish();
	}



	private class 1 extends BroadcastReceiver
	{

		final AlarmAlert this$0;

		public void onReceive(Context context, Intent intent)
		{
			Alarm alarm = (Alarm)intent.getParcelableExtra("intent.extra.alarm");
			int i = mAlarm.id;
			int j = alarm.id;
			if (i == j)
				dismiss(true);
		}

		1()
		{
			this$0 = AlarmAlert.this;
			super();
		}
	}

}
