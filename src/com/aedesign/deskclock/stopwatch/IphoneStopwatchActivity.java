// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneStopwatchActivity.java

package com.aedesign.deskclock.stopwatch;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.*;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;
import com.aedesign.deskclock.stopwatch.IphoneStopwatchActivity.LapData;
import android.view.View;
import android.os.Message;
import android.view.MotionEvent;
import java.util.Vector;

public class IphoneStopwatchActivity extends IphoneNoTitleActivity
{
	private static final boolean DEBUG = false;
	private static final int STOPWATCH_MESSAGE_WHAT = 0;
	private static final String TAG = "IphoneStopwatchActivity";
	private static LapData mLapData;
	private Button mEachTimeBT;
	private TextView mEachTimeTV;
	private Handler mHandler;
	private LinearLayout mLayout;
	private int mPaddingBottom;
	private int mPaddingLeft;
	private int mPaddingRight;
	private int mPaddingTop;
	private Button mStopwatchBT;
	private TextView mStopwatchTV;
	class LapData
	{

		private static final String KEY_ET_HOUR = "mEachTimeHour";
		private static final String KEY_ET_MINUTE = "mEachTimeMinitue";
		private static final String KEY_ET_SECOND = "mEachTimeSecond";
		private static final String KEY_ET_SECONDMILL = "mEachTimeMillisecond";
		private static final String KEY_ISRUNNGING = "isRunning";
		private static final String KEY_LAP = "lap";
		private static final String KEY_LAST_SAVE_TIME = "mLastSaveTime";
		private static final String KEY_SW_HOUR = "mStopwatchHour";
		private static final String KEY_SW_MINUTE = "mStopwatchMinUte";
		private static final String KEY_SW_SECOND = "mStopwatchSecond";
		private static final String KEY_SW_SECONDMILL = "mStopwatchMillisecond";
		private static final String LAP_DATA = "lap_data";
		private Context context;
		public boolean isRunning;
		public Vector mEachTimeData;
		public int mEachTimeHour;
		public int mEachTimeMillisecond;
		public int mEachTimeMinute;
		public int mEachTimeSecond;
		public long mLastSaveTime;
		public int mStopwatchHour;
		public int mStopwatchMillisecond;
		public int mStopwatchMinute;
		public int mStopwatchSecond;
		final IphoneStopwatchActivity this$0;

		private Vector stringToVector(String s)
		{
			Vector vector = new Vector();
			int i = 0;
			if (s != null && !s.equals(""))
				String as[] = s.split(";");
				
			else
				return vector;
			
			do
			{
				int j = as.length;
				if (i >= j)
					continue;
				String as1[] = as[i].split(",");
				vector.add(as1);
				i++;
			} while (true);
			//if (true)
				//return vector;
		}

		private String vectorToString(Vector vector)
		{
			String s;
			if (vector == null)
			{
				s = null;
			} else
			{
				int i = vector.size();
				StringBuffer stringbuffer = new StringBuffer();
				for (int j = 0; j < i; j++)
				{
					String as[] = (String[])vector.elementAt(j);
					s = as[null];
					stringbuffer.append(s);
					stringbuffer.append(",");
					s = as[1];
					stringbuffer.append(s);
					s = ";";
					stringbuffer.append(s);
				}

				s = stringbuffer.toString();
			}
			return s;
		}

		public void calcuTime()
		{
			long l = 60L;
			long l1 = 10L;
			if (isRunning)
			{
				long l2 = System.currentTimeMillis();
				long l3 = mLastSaveTime;
				long l4 = (<no variable> - l3) / 100L;
				long l5 = l4 % l1;
				int i = (int)((long)mStopwatchMillisecond + l5);
				mStopwatchMillisecond = i;
				int j = (int)((long)mEachTimeMillisecond + l5);
				mEachTimeMillisecond = j;
				long l6 = (l4 / l1) % l;
				int k = (int)((long)mStopwatchSecond + l6);
				mStopwatchSecond = k;
				int i1 = (int)((long)mEachTimeSecond + l6);
				mEachTimeSecond = i1;
				long l7 = (l4 / 600L) % l;
				int j1 = (int)((long)mStopwatchMinute + l7);
				mStopwatchMinute = j1;
				int k1 = (int)((long)mEachTimeMinute + l7);
				mEachTimeMinute = k1;
				long l8 = l4 / -29536L;
				int i2 = (int)((long)mStopwatchHour + l8);
				mStopwatchHour = i2;
				int j2 = (int)((long)mEachTimeHour + l8);
				mEachTimeHour = j2;
			}
		}

		public void getLapData()
		{
			SharedPreferences sharedpreferences = context.getSharedPreferences("lap_data", 0);
			boolean flag = sharedpreferences.getBoolean("isRunning", false);
			isRunning = flag;
			String s = sharedpreferences.getString("lap", "");
			Vector vector = stringToVector(s);
			mEachTimeData = vector;
			int i = sharedpreferences.getInt("mStopwatchHour", 0);
			mStopwatchHour = i;
			int j = sharedpreferences.getInt("mStopwatchMinUte", 0);
			mStopwatchMinute = j;
			int k = sharedpreferences.getInt("mStopwatchSecond", 0);
			mStopwatchSecond = k;
			int l = sharedpreferences.getInt("mStopwatchMillisecond", 0);
			mStopwatchMillisecond = l;
			int i1 = sharedpreferences.getInt("mEachTimeHour", 0);
			mEachTimeHour = i1;
			int j1 = sharedpreferences.getInt("mEachTimeMinitue", 0);
			mEachTimeMinute = j1;
			int k1 = sharedpreferences.getInt("mEachTimeSecond", 0);
			mEachTimeSecond = k1;
			int l1 = sharedpreferences.getInt("mEachTimeMillisecond", 0);
			mEachTimeMillisecond = l1;
			long l2 = System.currentTimeMillis();
			long l3 = sharedpreferences.getLong("mLastSaveTime", -1);
			mLastSaveTime = -1;
		}

		public boolean saveLapData()
		{
			android.content.SharedPreferences.Editor editor = context.getSharedPreferences("lap_data", 0).edit();
			boolean flag = isRunning;
			editor.putBoolean("isRunning", flag);
			Vector vector = mEachTimeData;
			String s = vectorToString(vector);
			editor.putString("lap", s);
			int i = mStopwatchHour;
			editor.putInt("mStopwatchHour", i);
			int j = mStopwatchMinute;
			editor.putInt("mStopwatchMinUte", j);
			int k = mStopwatchSecond;
			editor.putInt("mStopwatchSecond", k);
			int l = mStopwatchMillisecond;
			editor.putInt("mStopwatchMillisecond", l);
			int i1 = mEachTimeHour;
			editor.putInt("mEachTimeHour", i1);
			int j1 = mEachTimeMinute;
			editor.putInt("mEachTimeMinitue", j1);
			int k1 = mEachTimeSecond;
			editor.putInt("mEachTimeSecond", k1);
			int l1 = mEachTimeMillisecond;
			editor.putInt("mEachTimeMillisecond", l1);
			long l2 = System.currentTimeMillis();
			editor.putLong("mLastSaveTime", <no variable>);
			return editor.commit();
		}

		public LapData(Context context1)
		{
			this$0 = IphoneStopwatchActivity.this;
			super();
			Vector vector = new Vector();
			mEachTimeData = vector;
			context = context1;
		}
	}

	class StopWatchThread extends Thread
	{

		final IphoneStopwatchActivity this$0;

		public void run()
		{
			byte byte0;
			byte byte1;
			byte0 = 10;
			byte1 = 60;
			long l4;
			int i = IphoneStopwatchActivity.mLapData.isRunning;
			if (!i)
				break; /* Loop/switch isn't completed */
			LapData lapdata = IphoneStopwatchActivity.mLapData;
			int l = lapdata.mStopwatchMillisecond;
			int i1;
			i1++;
			lapdata.mStopwatchMillisecond = l;
			lapdata = IphoneStopwatchActivity.mLapData.mStopwatchMillisecond;
			if (lapdata >= byte0)
			{
				lapdata = IphoneStopwatchActivity.mLapData;
				int j1 = lapdata.mStopwatchMillisecond % 10;
				lapdata.mStopwatchMillisecond = j1;
				lapdata = IphoneStopwatchActivity.mLapData;
				int k1 = lapdata.mStopwatchSecond;
				i1++;
				lapdata.mStopwatchSecond = k1;
				lapdata = IphoneStopwatchActivity.mLapData.mStopwatchSecond;
				if (lapdata >= byte1)
				{
					lapdata = IphoneStopwatchActivity.mLapData;
					int l1 = lapdata.mStopwatchSecond % 60;
					lapdata.mStopwatchSecond = l1;
					lapdata = IphoneStopwatchActivity.mLapData;
					int i2 = lapdata.mStopwatchMinute;
					i1++;
					lapdata.mStopwatchMinute = i2;
					lapdata = IphoneStopwatchActivity.mLapData.mStopwatchMinute;
					if (lapdata >= byte1)
					{
						lapdata = IphoneStopwatchActivity.mLapData;
						int j2 = lapdata.mStopwatchMinute % 60;
						lapdata.mStopwatchMinute = j2;
						lapdata = IphoneStopwatchActivity.mLapData;
						int k2 = lapdata.mStopwatchHour;
						i1++;
						lapdata.mStopwatchHour = k2;
					}
				}
			}
			lapdata = IphoneStopwatchActivity.mLapData;
			int l2 = lapdata.mEachTimeMillisecond;
			i1++;
			lapdata.mEachTimeMillisecond = l2;
			lapdata = IphoneStopwatchActivity.mLapData.mEachTimeMillisecond;
			if (lapdata >= byte0)
			{
				LapData lapdata1 = IphoneStopwatchActivity.mLapData;
				int i3 = lapdata1.mEachTimeMillisecond % 10;
				lapdata1.mEachTimeMillisecond = i3;
				lapdata1 = IphoneStopwatchActivity.mLapData;
				int j3 = lapdata1.mEachTimeSecond;
				i1++;
				lapdata1.mEachTimeSecond = j3;
				int j = IphoneStopwatchActivity.mLapData.mEachTimeSecond;
				if (j >= byte1)
				{
					LapData lapdata2 = IphoneStopwatchActivity.mLapData;
					int k3 = lapdata2.mEachTimeSecond % 60;
					lapdata2.mEachTimeSecond = k3;
					lapdata2 = IphoneStopwatchActivity.mLapData;
					int l3 = lapdata2.mEachTimeMinute;
					i1++;
					lapdata2.mEachTimeMinute = l3;
					int k = IphoneStopwatchActivity.mLapData.mEachTimeMinute;
					if (k >= byte1)
					{
						LapData lapdata3 = IphoneStopwatchActivity.mLapData;
						int i4 = lapdata3.mEachTimeMinute % 60;
						lapdata3.mEachTimeMinute = i4;
						lapdata3 = IphoneStopwatchActivity.mLapData;
						int j4 = lapdata3.mEachTimeHour;
						i1++;
						lapdata3.mEachTimeHour = j4;
					}
				}
			}
			mHandler.sendEmptyMessage(null);
			l4 = 100L;
			Thread.sleep(l4);
			continue; /* Loop/switch isn't completed */
			//printStackTrace();
		}

		private StopWatchThread()
		{
			this$0 = IphoneStopwatchActivity.this;
			super();
		}

		StopWatchThread(1 1)
		{
			this();
		}
	}




	public IphoneStopwatchActivity()
	{
		mPaddingLeft = 0;
		mPaddingTop = 0;
		mPaddingRight = 0;
		mPaddingBottom = 0;
	}

	private void actionPerformed()
	{
		boolean flag = mLapData.isRunning;
		Button button;
		boolean flag1;
		int i;
		if (flag)
		{
			mStopwatchBT.setText(stopwatch_stop_button_text);
			mStopwatchBT.setBackgroundResource(iphone_stopwatch_stop_button);
			mEachTimeBT.setText(stopwatch_shot_button_text);
			1 1 = null;
			StopWatchThread stopwatchthread = new StopWatchThread(1);
			stopwatchthread.start();
		} else
		{
			mStopwatchBT.setText(stopwatch_start_button_text);
			mEachTimeBT.setText(stopwatch_reset_button_text);
			Button button1 = mStopwatchBT;
			i = 0x7f020059;
			button1.setBackgroundResource(i);
		}
		mEachTimeBT.setClickable(true);
		button = mEachTimeBT;
		flag1 = mEachTimeBT.isClickable();
		if (flag1)
			i = -1;
		else
			i = 0xff888888;
		button.setTextColor(i);
		mLapData.saveLapData();
	}

	private void initEachTime()
	{
		mLapData.mEachTimeHour = 0;
		mLapData.mEachTimeMinute = 0;
		mLapData.mEachTimeSecond = 0;
		mLapData.mEachTimeMillisecond = 0;
	}

	private void initEachTimeListView()
	{
		android.view.ViewGroup viewgroup = null;
		int i = 0x7f030013;
		byte byte0 = 6;
		int j = 1;
		boolean flag = false;
		int k = mLapData.mEachTimeData.size();
		for (int l = k - j; l > -1; l--)
		{
			String as[] = (String[])mLapData.mEachTimeData.elementAt(l);
			RelativeLayout relativelayout = (RelativeLayout)getLayoutInflater().inflate(i, viewgroup);
			TextView textview = (TextView)relativelayout.findViewById(0x7f0d0040);
			String s = as[0];
			textview.setText(s);
			TextView textview1 = (TextView)relativelayout.findViewById(0x7f0d0041);
			String s1 = as[j];
			textview1.setText(s1);
			mLayout.addView(relativelayout);
		}

		if (k == 0)
		{
			mEachTimeBT.setTextColor(0x7f080002);
			mEachTimeBT.setClickable(flag);
		}
		if (k < byte0)
		{
			int i1 = 0;
			do
			{
				int j1 = byte0 - k;
				if (i1 >= j1)
					break;
				RelativeLayout relativelayout1 = (RelativeLayout)getLayoutInflater().inflate(i, viewgroup);
				mLayout.addView(relativelayout1);
				i1++;
			} while (true);
		}
	}

	private void initStopwatch()
	{
		mLapData.mStopwatchHour = 0;
		mLapData.mStopwatchMinute = 0;
		mLapData.mStopwatchSecond = 0;
		mLapData.mStopwatchMillisecond = 0;
		updateEachTimeList(true);
		mHandler.sendEmptyMessage(0);
	}

	private void setupViews()
	{
		LinearLayout linearlayout = (LinearLayout)findViewById(0x7f0d003e);
		mLayout = linearlayout;
		TextView textview = (TextView)findViewById(0x7f0d003a);
		mEachTimeTV = textview;
		TextView textview1 = (TextView)findViewById(0x7f0d003b);
		mStopwatchTV = textview1;
		int i = mStopwatchTV.getPaddingLeft();
		mPaddingLeft = i;
		int j = mStopwatchTV.getPaddingTop();
		mPaddingTop = j;
		int k = mStopwatchTV.getPaddingRight();
		mPaddingRight = k;
		int l = mStopwatchTV.getPaddingBottom();
		mPaddingBottom = l;
		Button button = (Button)findViewById(0x7f0d003c);
		mStopwatchBT = button;
		Button button1 = mStopwatchBT;
		1 1_1 = new 1();
		button1.setOnTouchListener(1_1);
		Button button2 = (Button)findViewById(0x7f0d003d);
		mEachTimeBT = button2;
		Button button3 = mEachTimeBT;
		2 2_1 = new 2();
		button3.setOnTouchListener(2_1);
		3 3_1 = new 3();
		mHandler = 3_1;
		initEachTimeListView();
		if (mLapData.isRunning)
		{
			mLapData.calcuTime();
			actionPerformed();
		} else
		{
			updateTime();
		}
	}

	private void updateEachTimeList(boolean flag)
	{
		initEachTime();
		mLayout.removeAllViews();
		if (flag)
		{
			mLapData.mEachTimeData.clear();
		} else
		{
			String as[] = new String[2];
			String s = getResources().getString(stopwatch_shot_button_text);
			StringBuffer stringbuffer = new StringBuffer(s);
			int i = mLapData.mEachTimeData.size();
			int j;
			j++;
			String s1 = stringbuffer.append(i).toString();
			as[0] = s1;
			String s2 = mEachTimeTV.getText().toString();
			as[1] = s2;
			mLapData.mEachTimeData.add(as);
		}
		initEachTimeListView();
	}

	private void updateTime()
	{
		byte byte0 = 10;
		String s = ".";
		String s1 = ":";
		String s2 = "0";
		StringBuffer stringbuffer = new StringBuffer();
		int i = mLapData.mEachTimeHour;
		if (i > 0)
		{
			i = mLapData.mEachTimeHour;
			int k;
			int l;
			String s3;
			int i1;
			int j1;
			int k1;
			int l1;
			int i2;
			int j2;
			int k2;
			int l2;
			int i3;
			int j3;
			TextView textview;
			String s4;
			if (i < byte0)
			{
				StringBuilder stringbuilder = (new StringBuilder()).append(s2);
				int j = mLapData.mEachTimeHour;
				stringbuilder = stringbuilder.append(j).toString();
			} else
			{
				stringbuilder = Integer.valueOf(mLapData.mEachTimeHour);
			}
			stringbuffer.append(stringbuilder);
			stringbuilder = ":";
			stringbuffer.append(s1);
		}
		stringbuilder = mLapData.mEachTimeMinute;
		if (stringbuilder < byte0)
		{
			stringbuilder = (new StringBuilder()).append(s2);
			k = mLapData.mEachTimeMinute;
			stringbuilder = stringbuilder.append(k).toString();
		} else
		{
			stringbuilder = Integer.valueOf(mLapData.mEachTimeMinute);
		}
		stringbuffer.append(stringbuilder);
		stringbuffer.append(s1);
		stringbuilder = mLapData.mEachTimeSecond;
		if (stringbuilder < byte0)
		{
			stringbuilder = (new StringBuilder()).append(s2);
			l = mLapData.mEachTimeSecond;
			stringbuilder = stringbuilder.append(l).toString();
		} else
		{
			stringbuilder = Integer.valueOf(mLapData.mEachTimeSecond);
		}
		stringbuffer.append(stringbuilder);
		stringbuffer.append(s);
		stringbuilder = mLapData.mEachTimeMillisecond;
		stringbuffer.append(stringbuilder);
		stringbuilder = mEachTimeTV;
		s3 = stringbuffer.toString();
		stringbuilder.setText(s3);
		stringbuffer = new StringBuffer();
		stringbuilder = mLapData.mStopwatchHour;
		if (stringbuilder > 0)
		{
			mStopwatchTV.setTextSize(0x425c0000);
			stringbuilder = mLapData.mStopwatchHour;
			if (stringbuilder < byte0)
			{
				stringbuilder = (new StringBuilder()).append(s2);
				i1 = mLapData.mStopwatchHour;
				stringbuilder = stringbuilder.append(i1).toString();
			} else
			{
				stringbuilder = Integer.valueOf(mLapData.mStopwatchHour);
			}
			stringbuffer.append(stringbuilder);
			stringbuffer.append(s1);
			stringbuilder = mStopwatchTV;
			j1 = mPaddingLeft;
			k1 = mPaddingTop;
			l1 += 12;
			i2 = mPaddingRight;
			j2 = mPaddingBottom;
			k2 += 15;
			stringbuilder.setPadding(j1, k1, i2, j2);
		} else
		{
			mStopwatchTV.setTextSize(0x42960000);
			stringbuilder = mStopwatchTV;
			int k3 = mPaddingLeft;
			int l3 = mPaddingTop;
			int i4 = mPaddingRight;
			int j4 = mPaddingBottom;
			stringbuilder.setPadding(k3, l3, i4, j4);
		}
		stringbuilder = mLapData.mStopwatchMinute;
		if (stringbuilder < byte0)
		{
			stringbuilder = (new StringBuilder()).append(s2);
			l2 = mLapData.mStopwatchMinute;
			stringbuilder = stringbuilder.append(l2).toString();
		} else
		{
			stringbuilder = Integer.valueOf(mLapData.mStopwatchMinute);
		}
		stringbuffer.append(stringbuilder);
		stringbuffer.append(s1);
		stringbuilder = mLapData.mStopwatchSecond;
		if (stringbuilder < byte0)
		{
			stringbuilder = (new StringBuilder()).append(s2);
			i3 = mLapData.mStopwatchSecond;
			stringbuilder = stringbuilder.append(i3).toString();
		} else
		{
			stringbuilder = Integer.valueOf(mLapData.mStopwatchSecond);
		}
		stringbuffer.append(stringbuilder);
		stringbuffer.append(s);
		j3 = mLapData.mStopwatchMillisecond;
		stringbuffer.append(stringbuilder);
		textview = mStopwatchTV;
		s4 = stringbuffer.toString();
		stringbuilder.setText(s4);
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(1);
		setContentView(iphone_stop_watch);
		mLapData = new LapData(this);
		mLapData.getLapData();
		setupViews();
	}

	protected void onDestroy()
	{
		super.onDestroy();
		mLapData.saveLapData();
		mLapData.isRunning = null;
	}








	private class 1 implements android.view.View.OnTouchListener
	{

		final IphoneStopwatchActivity this$0;

		public boolean onTouch(View view, MotionEvent motionevent)
		{
			boolean flag = true;
			Object obj = null;
			int i = motionevent.getAction();
			if (i == 0)
			{
				LapData lapdata = IphoneStopwatchActivity.mLapData;
				boolean flag1 = IphoneStopwatchActivity.mLapData.isRunning;
				if (!flag1)
					flag1 = flag;
				else
					flag1 = (Boolean) obj;
				lapdata.isRunning = flag1;
				actionPerformed();
				lapdata = flag;
			} else
			{
				lapdata = obj;
			}
			return lapdata;
		}

		1()
		{
			this$0 = IphoneStopwatchActivity.this;
			super();
		}
	}


	private class 2 implements android.view.View.OnTouchListener
	{

		final IphoneStopwatchActivity this$0;

		public boolean onTouch(View view, MotionEvent motionevent)
		{
			boolean flag = null;
			int i = motionevent.getAction();
			Object obj;
			if (i == 0)
			{
				boolean flag1 = IphoneStopwatchActivity.mLapData.isRunning;
				if (flag1)
				{
					IphoneStopwatchActivity iphonestopwatchactivity = IphoneStopwatchActivity.this;
					iphonestopwatchactivity.updateEachTimeList(flag);
				} else
				{
					initStopwatch();
					obj = mEachTimeBT;
					((Button) (obj)).setClickable(flag);
				}
				obj = mEachTimeBT;
				flag = mEachTimeBT.isClickable();
				if (flag)
					flag = -1;
				else
					flag = 0x7f080002;
				((Button) (obj)).setTextColor(flag);
				IphoneStopwatchActivity.mLapData.saveLapData();
				obj = 1;
			} else
			{
				obj = flag;
			}
			return ((boolean) (obj));
		}

		2()
		{
			this$0 = IphoneStopwatchActivity.this;
			super();
		}
	}


	private class MyExecutor extends Handler
	{

		final IphoneStopwatchActivity this$0;
		@Override
		public void handleMessage(Message message)
		{
			super.handleMessage(message);
			if (message.what == 0)
				updateTime();
		}

		public handler()
		{
			this$0 = IphoneStopwatchActivity.this;
			super();
		}
	}

}
