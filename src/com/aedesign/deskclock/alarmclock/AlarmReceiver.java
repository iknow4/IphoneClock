// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmReceiver.java

package com.aedesign.deskclock.alarmclock;

import android.app.*;
import android.content.*;
import android.os.Parcel;
import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			SetAlarm, Alarm, Alarms, Log, 
//			AlarmAlertWakeLock, AlarmAlert, AlarmAlertFullScreen

public class AlarmReceiver extends BroadcastReceiver
{

	private static final int STALE_WINDOW = 1800;

	public AlarmReceiver()
	{
	}

	private NotificationManager getNotificationManager(Context context)
	{
		return (NotificationManager)context.getSystemService("notification");
	}

	private void updateNotification(Context context, Alarm alarm, int i)
	{
		int j = 0;
		NotificationManager notificationmanager = getNotificationManager(context);
		if (alarm != null)
		{
			Intent intent = new Intent(context, com/aedesign/deskclock/alarmclock/SetAlarm);
			int k = alarm.id;
			intent.putExtra("alarm_id", k);
			int l = alarm.id;
			PendingIntent pendingintent = PendingIntent.getActivity(context, l, intent, j);
			String s = alarm.getLabelOrDefault(context);
			long l1 = alarm.time;
			Notification notification = new Notification(0x7f02009c, s, l1);
			Object aobj[] = new Object[1];
			Integer integer = Integer.valueOf(i);
			aobj[j] = integer;
			String s1 = context.getString(0x7f0a004e, aobj);
			notification.setLatestEventInfo(context, s, s1, pendingintent);
			int i1 = notification.flags | 0x10;
			notification.flags = i1;
			int j1 = alarm.id;
			notificationmanager.notify(j1, notification);
		}
	}

	public void onReceive(Context context, Intent intent)
	{
		String s = intent.getAction();
		if (!"clear_notification".equals(s)) goto _L2; else goto _L1
_L1:
		Intent intent1 = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
		Context context1 = context;
		Intent intent2 = intent1;
		context1.stopService(intent2);
_L4:
		return;
_L2:
		String s1 = intent.getAction();
		if ("alarm_killed".equals(s1))
		{
			Intent intent3 = intent;
			String s2 = "intent.extra.alarm";
			Alarm alarm = (Alarm)intent3.getParcelableExtra(s2);
			Intent intent4 = intent;
			String s3 = "alarm_killed_timeout";
			int i = 65535;
			int j = intent4.getIntExtra(s3, i);
			AlarmReceiver alarmreceiver = this;
			Context context2 = context;
			Alarm alarm1 = alarm;
			int k = j;
			alarmreceiver.updateNotification(context2, alarm1, k);
		} else
		{
			String s4 = intent.getAction();
			if ("cancel_snooze".equals(s4))
			{
				Context context3 = context;
				int l = 65535;
				long l1 = 65535L;
				Alarms.saveSnoozeAlert(context3, l, l1);
			} else
			{
				Object obj = 0;
				Intent intent5 = intent;
				String s5 = "intent.extra.alarm_raw";
				byte abyte0[] = intent5.getByteArrayExtra(s5);
				if (abyte0 != null)
				{
					Parcel parcel = Parcel.obtain();
					int i1 = abyte0.length;
					Parcel parcel1 = parcel;
					byte abyte1[] = abyte0;
					int j1 = 0;
					int k1 = i1;
					parcel1.unmarshall(abyte1, j1, k1);
					Parcel parcel2 = parcel;
					int i2 = 0;
					parcel2.setDataPosition(i2);
					android.os.Parcelable.Creator creator = Alarm.CREATOR;
					Parcel parcel3 = parcel;
					obj = (Alarm)creator.createFromParcel(parcel3);
				}
				if (obj == 0)
				{
					Log.v("AlarmReceiver failed to parse the alarm from the intent");
				} else
				{
					long l2 = System.currentTimeMillis();
					SimpleDateFormat simpledateformat = JVM INSTR new #185 <Class SimpleDateFormat>;
					SimpleDateFormat simpledateformat1 = simpledateformat;
					String s6 = "HH:mm:ss.SSS aaa";
					simpledateformat1.SimpleDateFormat(s6);
					StringBuilder stringbuilder = (new StringBuilder()).append("AlarmReceiver.onReceive() id ");
					int j2 = ((Alarm) (obj)).id;
					StringBuilder stringbuilder1 = stringbuilder.append(j2).append(" setFor ");
					long l3 = ((Alarm) (obj)).time;
					Date date = new Date(l3);
					SimpleDateFormat simpledateformat2 = simpledateformat;
					Date date1 = date;
					String s7 = simpledateformat2.format(date1);
					Log.v(stringbuilder1.append(s7).toString());
					long l4 = ((Alarm) (obj)).time + 0x1b7740L;
					if (<no variable> <= l4)
					{
						AlarmAlertWakeLock.acquireCpuWakeLock(context);
						Intent intent6 = JVM INSTR new #30  <Class Intent>;
						Intent intent7 = intent6;
						String s8 = "android.intent.action.CLOSE_SYSTEM_DIALOGS";
						intent7.Intent(s8);
						Context context4 = context;
						Intent intent8 = intent6;
						context4.sendBroadcast(intent8);
						/*<invalid signature>*/java.lang.Object local = com/aedesign/deskclock/alarmclock/AlarmAlert;
						Context context5 = context;
						String s9 = "keyguard";
						if (((KeyguardManager)context5.getSystemService(s9)).inKeyguardRestrictedInputMode())
							local = com/aedesign/deskclock/alarmclock/AlarmAlertFullScreen;
						Intent intent9 = JVM INSTR new #30  <Class Intent>;
						Intent intent10 = intent9;
						Context context6 = context;
						Class class1 = local;
						intent10.Intent(context6, class1);
						Intent intent11 = intent9;
						String s10 = "intent.extra.alarm";
						Object obj1 = obj;
						intent11.putExtra(s10, ((android.os.Parcelable) (obj1)));
						Intent intent12 = intent9;
						int k2 = 0x10040000;
						intent12.setFlags(k2);
						Context context7 = context;
						Intent intent13 = intent9;
						context7.startActivity(intent13);
						int i3 = ((Alarm) (obj)).id;
						Context context8 = context;
						int j3 = i3;
						Alarms.disableSnoozeAlert(context8, j3);
						Intent intent14;
						Intent intent15;
						String s11;
						Object obj2;
						Context context10;
						Intent intent16;
						Intent intent17;
						Intent intent18;
						Context context11;
						Class class2;
						Intent intent19;
						String s12;
						Object obj3;
						int j4;
						Context context12;
						int k4;
						Intent intent20;
						int i5;
						PendingIntent pendingintent;
						Object obj4;
						Context context13;
						String s13;
						Notification notification;
						long l5;
						Notification notification1;
						int j5;
						String s14;
						long l6;
						Context context14;
						int k5;
						String s15;
						Notification notification2;
						Context context15;
						String s16;
						String s17;
						PendingIntent pendingintent1;
						int i6;
						int j6;
						int k6;
						int i7;
						Intent intent21;
						Intent intent22;
						Context context16;
						Class class3;
						Intent intent23;
						String s18;
						Context context17;
						int j7;
						Intent intent24;
						int k7;
						PendingIntent pendingintent2;
						NotificationManager notificationmanager;
						int l7;
						NotificationManager notificationmanager1;
						int i8;
						Notification notification3;
						if (!((Alarm) (obj)).daysOfWeek.isRepeatSet())
						{
							int k3 = ((Alarm) (obj)).id;
							Context context9 = context;
							int i4 = k3;
							boolean flag = null;
							Alarms.enableAlarm(context9, i4, flag);
						} else
						{
							Alarms.setNextAlert(context);
						}
						intent14 = new Intent("com.aedesign.alarmclock.ALARM_ALERT");
						intent15 = intent14;
						s11 = "intent.extra.alarm";
						obj2 = obj;
						intent15.putExtra(s11, ((android.os.Parcelable) (obj2)));
						context10 = context;
						intent16 = intent14;
						context10.startService(intent16);
						intent17 = JVM INSTR new #30  <Class Intent>;
						intent18 = intent17;
						context11 = context;
						class2 = com/aedesign/deskclock/alarmclock/AlarmAlert;
						intent18.Intent(context11, class2);
						intent19 = intent17;
						s12 = "intent.extra.alarm";
						obj3 = obj;
						intent19.putExtra(s12, ((android.os.Parcelable) (obj3)));
						j4 = ((Alarm) (obj)).id;
						context12 = context;
						k4 = j4;
						intent20 = intent17;
						i5 = 0;
						pendingintent = PendingIntent.getActivity(context12, k4, intent20, i5);
						obj4 = obj;
						context13 = context;
						s13 = ((Alarm) (obj4)).getLabelOrDefault(context13);
						notification = JVM INSTR new #62  <Class Notification>;
						l5 = ((Alarm) (obj)).time;
						notification1 = notification;
						j5 = 0x7f02009c;
						s14 = s13;
						l6 = l5;
						notification1.Notification(j5, s14, l6);
						context14 = context;
						k5 = 0x7f0a0065;
						s15 = context14.getString(k5);
						notification2 = notification;
						context15 = context;
						s16 = s13;
						s17 = s15;
						pendingintent1 = pendingintent;
						notification2.setLatestEventInfo(context15, s16, s17, pendingintent1);
						i6 = notification.flags | 1;
						notification.flags = i6;
						j6 = 0xff00ff00;
						notification.ledARGB = j6;
						k6 = 500;
						notification.ledOnMS = k6;
						i7 = 500;
						notification.ledOffMS = i7;
						intent21 = JVM INSTR new #30  <Class Intent>;
						intent22 = intent21;
						context16 = context;
						class3 = com/aedesign/deskclock/alarmclock/AlarmReceiver;
						intent22.Intent(context16, class3);
						intent23 = intent21;
						s18 = "clear_notification";
						intent23.setAction(s18);
						context17 = context;
						j7 = 0;
						intent24 = intent21;
						k7 = 0;
						pendingintent2 = PendingIntent.getBroadcast(context17, j7, intent24, k7);
						notification.deleteIntent = pendingintent2;
						notificationmanager = getNotificationManager(context);
						l7 = ((Alarm) (obj)).id;
						notificationmanager1 = notificationmanager;
						i8 = l7;
						notification3 = notification;
						notificationmanager1.notify(i8, notification3);
					}
				}
			}
		}
		if (true) goto _L4; else goto _L3
_L3:
	}
}
