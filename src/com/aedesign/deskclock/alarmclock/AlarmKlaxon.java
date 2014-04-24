// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AlarmKlaxon.java

package com.aedesign.deskclock.alarmclock;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.*;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import java.io.IOException;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			Alarm, Log, AlarmAlertWakeLock

public class AlarmKlaxon extends Service
{

	private static final int ALARM_TIMEOUT_SECONDS = 600;
	private static final float IN_CALL_VOLUME = 0.125F;
	private static final int KILLER = 1000;
	private static final long sVibratePattern[] = 2;
	private Alarm mCurrentAlarm;
	private Handler mHandler;
	private int mInitialCallState;
	private MediaPlayer mMediaPlayer;
	private PhoneStateListener mPhoneStateListener;
	private boolean mPlaying;
	private long mStartTime;
	private TelephonyManager mTelephonyManager;

	public AlarmKlaxon()
	{
		mPlaying = null;
		1 1_1 = new 1();
		mHandler = 1_1;
		2 2_1 = new 2();
		mPhoneStateListener = 2_1;
	}

	private void disableKiller()
	{
		mHandler.removeMessages(1000);
	}

	private void enableKiller(Alarm alarm)
	{
		Handler handler = mHandler;
		Message message = mHandler.obtainMessage(1000, alarm);
		handler.sendMessageDelayed(message, 0x927c0L);
	}

	private void play(Alarm alarm)
	{
		stop();
		if (alarm.silent) goto _L2; else goto _L1
_L1:
		android.net.Uri uri;
		uri = alarm.alert;
		if (uri == null)
			uri = RingtoneManager.getDefaultUri(4);
		MediaPlayer mediaplayer = new MediaPlayer();
		mMediaPlayer = mediaplayer;
		MediaPlayer mediaplayer1 = mMediaPlayer;
		3 3_1 = new 3();
		mediaplayer1.setOnErrorListener(3_1);
		if (mTelephonyManager.getCallState() == 0) goto _L4; else goto _L3
_L3:
		Log.v("Using the in-call alarm");
		mMediaPlayer.setVolume(0x3e000000, 0x3e000000);
		Resources resources = getResources();
		MediaPlayer mediaplayer2 = mMediaPlayer;
		setDataSourceFromResource(resources, mediaplayer2, 0x7f060001);
_L5:
		MediaPlayer mediaplayer3 = mMediaPlayer;
		startAlarm(mediaplayer3);
_L2:
		enableKiller(alarm);
		mPlaying = true;
		long l = System.currentTimeMillis();
		mStartTime = <no variable>;
		return;
_L4:
		mMediaPlayer.setDataSource(this, uri);
		  goto _L5
		Exception exception;
		exception;
		Log.v("Using the fallback ringtone");
		try
		{
			mMediaPlayer.reset();
			Resources resources1 = getResources();
			MediaPlayer mediaplayer4 = mMediaPlayer;
			setDataSourceFromResource(resources1, mediaplayer4, 0x7f060000);
			MediaPlayer mediaplayer5 = mMediaPlayer;
			startAlarm(mediaplayer5);
		}
		catch (Exception exception1)
		{
			Log.e("Failed to play fallback ringtone", exception1);
		}
		  goto _L2
	}

	private void sendKillBroadcast(Alarm alarm)
	{
		long l = System.currentTimeMillis();
		long l1 = mStartTime;
		int i = (int)Math.round((double)(<no variable> - l1) / 0x40ed4c0000000000L);
		Intent intent = new Intent("alarm_killed");
		intent.putExtra("intent.extra.alarm", alarm);
		intent.putExtra("alarm_killed_timeout", i);
		sendBroadcast(intent);
	}

	private void setDataSourceFromResource(Resources resources, MediaPlayer mediaplayer, int i)
		throws IOException
	{
		AssetFileDescriptor assetfiledescriptor = resources.openRawResourceFd(i);
		if (assetfiledescriptor != null)
		{
			java.io.FileDescriptor filedescriptor = assetfiledescriptor.getFileDescriptor();
			long l = assetfiledescriptor.getStartOffset();
			long l1 = assetfiledescriptor.getLength();
			mediaplayer.setDataSource(filedescriptor, <no variable>, <no variable>);
			assetfiledescriptor.close();
		}
	}

	private void startAlarm(MediaPlayer mediaplayer)
		throws IOException, IllegalArgumentException, IllegalStateException
	{
		byte byte0 = 4;
		if (((AudioManager)getSystemService("audio")).getStreamVolume(byte0) != 0)
		{
			mediaplayer.setAudioStreamType(byte0);
			mediaplayer.setLooping(true);
			mediaplayer.prepare();
			mediaplayer.start();
		}
	}

	public IBinder onBind(Intent intent)
	{
		return null;
	}

	public void onCreate()
	{
		TelephonyManager telephonymanager = (TelephonyManager)getSystemService("phone");
		mTelephonyManager = telephonymanager;
		TelephonyManager telephonymanager1 = mTelephonyManager;
		PhoneStateListener phonestatelistener = mPhoneStateListener;
		telephonymanager1.listen(phonestatelistener, 32);
		AlarmAlertWakeLock.acquireCpuWakeLock(this);
	}

	public void onDestroy()
	{
		stop();
		TelephonyManager telephonymanager = mTelephonyManager;
		PhoneStateListener phonestatelistener = mPhoneStateListener;
		telephonymanager.listen(phonestatelistener, 0);
		AlarmAlertWakeLock.releaseCpuLock();
	}

	public int onStartCommand(Intent intent, int i, int j)
	{
		byte byte0 = 2;
		Object obj;
		if (intent == null)
		{
			stopSelf();
			obj = byte0;
		} else
		{
			obj = "intent.extra.alarm";
			Alarm alarm = (Alarm)intent.getParcelableExtra(((String) (obj)));
			if (alarm == null)
			{
				Log.v("AlarmKlaxon failed to parse the alarm from the intent");
				stopSelf();
				obj = byte0;
			} else
			{
				obj = mCurrentAlarm;
				if (obj != null)
				{
					obj = mCurrentAlarm;
					sendKillBroadcast(((Alarm) (obj)));
				}
				play(alarm);
				mCurrentAlarm = alarm;
				obj = mTelephonyManager.getCallState();
				mInitialCallState = ((int) (obj));
				obj = 1;
			}
		}
		return ((int) (obj));
	}

	public void stop()
	{
		if (mPlaying)
		{
			mPlaying = null;
			if (mMediaPlayer != null)
			{
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		}
		disableKiller();
	}

	static 
	{
		2[0] = 500L;
		2[1] = 500L;
	}





/*
	static MediaPlayer access$302(AlarmKlaxon alarmklaxon, MediaPlayer mediaplayer)
	{
		alarmklaxon.mMediaPlayer = mediaplayer;
		return mediaplayer;
	}

*/

	private class 1 extends Handler
	{

		final AlarmKlaxon this$0;

		public void handleMessage(Message message)
		{
			message.what;
			JVM INSTR tableswitch 1000 1000: default 24
		//		               1000 25;
			   goto _L1 _L2
_L1:
			return;
_L2:
			AlarmKlaxon alarmklaxon = AlarmKlaxon.this;
			Alarm alarm = (Alarm)message.obj;
			alarmklaxon.sendKillBroadcast(alarm);
			stopSelf();
			if (true) goto _L1; else goto _L3
_L3:
		}

		1()
		{
			this$0 = AlarmKlaxon.this;
			super();
		}
	}


	private class 2 extends PhoneStateListener
	{

		final AlarmKlaxon this$0;

		public void onCallStateChanged(int i, String s)
		{
			if (i != 0)
			{
				int j = mInitialCallState;
				if (i != j)
				{
					AlarmKlaxon alarmklaxon = AlarmKlaxon.this;
					Alarm alarm = mCurrentAlarm;
					alarmklaxon.sendKillBroadcast(alarm);
					stopSelf();
				}
			}
		}

		2()
		{
			this$0 = AlarmKlaxon.this;
			super();
		}
	}


	private class 3
		implements android.media.MediaPlayer.OnErrorListener
	{

		final AlarmKlaxon this$0;

		public boolean onError(MediaPlayer mediaplayer, int i, int j)
		{
			Log.e("Error occurred while playing audio.");
			mediaplayer.stop();
			mediaplayer.release();
			mMediaPlayer = null;
			return true;
		}

		3()
		{
			this$0 = AlarmKlaxon.this;
			super();
		}
	}

}
