// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneWorldClockActivity.java

package com.aedesign.deskclock.worldclock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;
import com.aedesign.deskclock.ui.IphoneTouchInterceptor;

// Referenced classes of package com.aedesign.deskclock.worldclock:
//			IphoneWorldClockDBHelper, IphoneWorldClockListView, IphoneWorldClockListAdapter, IphoneWorldClockListItem

public class IphoneWorldClockActivity extends IphoneNoTitleActivity
	implements android.view.View.OnClickListener, android.view.View.OnTouchListener
{

	private static final boolean DEBUG = false;
	public static final int MESSAGE_CHANGE_DELETE_MODE = 1;
	private static final String TAG = "IphoneWorldClockActivity";
	private static final int TAG_ADD_BUTTON = 1;
	private static final int TAG_EDIT_BUTTON;
	private Button mButtonAdd;
	private Button mButtonEdit;
	private Cursor mCursor;
	protected IphoneWorldClockDBHelper mDBHelper;
	private com.aedesign.deskclock.ui.IphoneTouchInterceptor.DropListener mDropListener;
	private final Handler mHandler;
	protected LayoutInflater mInflater;
	private IphoneWorldClockListAdapter mListAdapter;
	private IphoneWorldClockListView mListView;
	private TextView mNoClock;

	public IphoneWorldClockActivity()
	{
		1 1_1 = new 1();
		mHandler = 1_1;
		2 2_1 = new 2();
		mDropListener = 2_1;
	}

	private void init()
	{
		byte byte0 = 8;
		boolean flag = false;
		IphoneWorldClockDBHelper iphoneworldclockdbhelper = new IphoneWorldClockDBHelper(this);
		mDBHelper = iphoneworldclockdbhelper;
		setContentView(0x7f030017);
		Button button = (Button)findViewById(0x7f0d0005);
		mButtonEdit = button;
		Button button1 = mButtonEdit;
		Integer integer = Integer.valueOf(flag);
		button1.setTag(integer);
		mButtonEdit.setOnClickListener(this);
		IphoneWorldClockListView iphoneworldclocklistview = (IphoneWorldClockListView)findViewById(0x7f0d0054);
		mListView = iphoneworldclocklistview;
		mListView.setOnTouchListener(this);
		Cursor cursor = mDBHelper.loadAll();
		mCursor = cursor;
		Cursor cursor1 = mCursor;
		Handler handler = mHandler;
		IphoneWorldClockListAdapter iphoneworldclocklistadapter = new IphoneWorldClockListAdapter(this, cursor1, flag, handler);
		mListAdapter = iphoneworldclocklistadapter;
		IphoneWorldClockListView iphoneworldclocklistview1 = mListView;
		IphoneWorldClockListAdapter iphoneworldclocklistadapter1 = mListAdapter;
		iphoneworldclocklistview1.setAdapter(iphoneworldclocklistadapter1);
		Button button2 = (Button)findViewById(0x7f0d0007);
		mButtonAdd = button2;
		Button button3 = mButtonAdd;
		Integer integer1 = Integer.valueOf(1);
		button3.setTag(integer1);
		mButtonAdd.setOnClickListener(this);
		TextView textview = (TextView)findViewById(0x7f0d0055);
		mNoClock = textview;
		if (mCursor.getCount() > 0)
		{
			mListView.setVisibility(flag);
			mButtonEdit.setVisibility(flag);
			mNoClock.setVisibility(byte0);
		} else
		{
			mListView.setVisibility(byte0);
			mButtonEdit.setVisibility(4);
			mNoClock.setVisibility(flag);
		}
	}

	protected void onActivityResult(int i, int j, Intent intent)
	{
		byte byte0 = 8;
		int k = 0;
		if (j == -1 && intent != null)
		{
			String s = intent.getStringExtra("time_zone");
			String s1 = intent.getStringExtra("area");
			Cursor cursor;
			IphoneWorldClockListAdapter iphoneworldclocklistadapter;
			Cursor cursor1;
			if (mCursor.moveToLast())
			{
				int l = mCursor.getColumnIndex("position");
				int i1 = mCursor.getInt(l);
				IphoneWorldClockDBHelper iphoneworldclockdbhelper = mDBHelper;
				int j1 = i1 + 1;
				iphoneworldclockdbhelper.save(s1, s, j1);
			} else
			{
				mDBHelper.save(s1, s, 1);
			}
			cursor = mDBHelper.loadAll();
			mCursor = cursor;
			iphoneworldclocklistadapter = mListAdapter;
			cursor1 = mCursor;
			iphoneworldclocklistadapter.changeCursor(cursor1);
			if (mListView.getVisibility() == byte0)
			{
				mListView.setVisibility(k);
				mButtonEdit.setVisibility(k);
				mNoClock.setVisibility(byte0);
			}
			mListView.setDropListener(null);
		}
	}

	public void onAttachedToWindow()
	{
		super.onAttachedToWindow();
	}

	public void onClick(View view)
	{
		boolean flag = view.getId();
		flag;
		JVM INSTR tableswitch 2131558405 2131558407: default 32
	//	               2131558405 33
	//	               2131558406 32
	//	               2131558407 142;
		   goto _L1 _L2 _L1 _L3
_L1:
		return;
_L2:
		IphoneWorldClockListView.mEdit = flag;
		if (flag)
		{
			if (mListView.getMSelectedItem() != null)
				mListView.getMSelectedItem().showDeleteBtn();
			mListView.changeToComplete();
			mListView.setDropListener(null);
			mButtonEdit.setText(0x7f0a0036);
			mButtonEdit.setBackgroundResource(0x7f020032);
		} else
		{
			mListView.changeToEdit();
			IphoneWorldClockListView iphoneworldclocklistview = mListView;
			com.aedesign.deskclock.ui.IphoneTouchInterceptor.DropListener droplistener = mDropListener;
			iphoneworldclocklistview.setDropListener(droplistener);
			mButtonEdit.setText(0x7f0a0035);
			mButtonEdit.setBackgroundResource(0x7f020031);
		}
		continue; /* Loop/switch isn't completed */
_L3:
		Intent intent = new Intent("android.intent.action.zonelist");
		startActivityForResult(intent, 0);
		if (true) goto _L1; else goto _L4
_L4:
	}

	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(1);
		init();
	}

	protected void onDestroy()
	{
		super.onDestroy();
		mDBHelper.close();
	}

	protected void onResume()
	{
		super.onResume();
		IphoneWorldClockListView.mEdit = <no variable>;
		if (<no variable> != 0)
		{
			boolean flag = IphoneWorldClockListView.mEdit;
			int i = mListView.getChildCount();
			for (int j = 0; j < i; j++)
				((IphoneWorldClockListItem)mListView.getChildAt(j)).updateViews();

			mButtonEdit.setBackgroundResource(0x7f020032);
			mButtonEdit.setText(0x7f0a0036);
		}
	}

	protected void onStart()
	{
		super.onStart();
		Cursor cursor = mDBHelper.loadAll();
		mCursor = cursor;
		IphoneWorldClockListAdapter iphoneworldclocklistadapter = mListAdapter;
		Cursor cursor1 = mCursor;
		iphoneworldclocklistadapter.changeCursor(cursor1);
	}

	public boolean onTouch(View view, MotionEvent motionevent)
	{
		boolean flag = view instanceof IphoneWorldClockListView;
		if (!flag) goto _L2; else goto _L1
_L1:
		motionevent.getAction();
		JVM INSTR tableswitch 0 2: default 40
	//	               0 44
	//	               1 40
	//	               2 40;
		   goto _L2 _L3 _L2 _L2
_L2:
		flag = null;
_L5:
		return flag;
_L3:
		flag = mListView.getMSelectedItem();
		if (flag != null)
		{
			mListView.getMSelectedItem().showDeleteBtn();
			flag = mListView;
			flag.setMSelectedItem(null);
		}
		flag = true;
		if (true) goto _L5; else goto _L4
_L4:
	}




/*
	static Cursor access$102(IphoneWorldClockActivity iphoneworldclockactivity, Cursor cursor)
	{
		iphoneworldclockactivity.mCursor = cursor;
		return cursor;
	}

*/




	private class 1 extends Handler
	{

		final IphoneWorldClockActivity this$0;

		public void handleMessage(Message message)
		{
			boolean flag = null;
			if (message.what != 1) goto _L2; else goto _L1
_L1:
			boolean flag1 = null;
			if (message.obj instanceof Boolean)
				flag1 = ((Boolean)message.obj).booleanValue();
			mListView.setDeleteMode(flag1);
_L4:
			return;
_L2:
			if (message.obj instanceof IphoneWorldClockListItem)
			{
				mListView.setMSelectedItem(null);
				IphoneWorldClockDBHelper iphoneworldclockdbhelper = mDBHelper;
				int i = ((IphoneWorldClockListItem)message.obj).getItemId();
				iphoneworldclockdbhelper.deleteById(i);
				IphoneWorldClockActivity iphoneworldclockactivity = IphoneWorldClockActivity.this;
				Cursor cursor = mDBHelper.loadAll();
				iphoneworldclockactivity.mCursor = cursor;
				IphoneWorldClockListAdapter iphoneworldclocklistadapter = mListAdapter;
				Cursor cursor1 = mCursor;
				iphoneworldclocklistadapter.changeCursor(cursor1);
				if (mCursor.getCount() <= 0)
				{
					mNoClock.setVisibility(flag);
					mListView.setVisibility(8);
					mButtonEdit.setVisibility(4);
					mButtonEdit.setPressed(flag);
					mButtonEdit.setBackgroundResource(0x7f020032);
				}
			}
			if (true) goto _L4; else goto _L3
_L3:
		}

		1()
		{
			this$0 = IphoneWorldClockActivity.this;
			super();
		}
	}


	private class 2
		implements com.aedesign.deskclock.ui.IphoneTouchInterceptor.DropListener
	{

		final IphoneWorldClockActivity this$0;

		public void drop(int i, int j)
		{
			int k = 1;
			Log.e("IphoneWorldClockActivity", "IphoneTouchInterceptor DropListener ----->");
			String s = (new StringBuilder()).append("IphoneTouchInterceptor DropListener from = ").append(i).toString();
			Log.e("IphoneWorldClockActivity", s);
			String s1 = (new StringBuilder()).append("IphoneTouchInterceptor DropListener to = ").append(j).toString();
			Log.e("IphoneWorldClockActivity", s1);
			ContentValues contentvalues = new ContentValues();
			int l = mCursor.getColumnIndex("_id");
			int i1 = mCursor.getColumnIndex("position");
			if (i < j)
			{
				mCursor.moveToPosition(j);
				int j1 = mCursor.getInt(l);
				int k1 = mCursor.getInt(i1);
				mCursor.moveToPosition(i);
				int l1 = mCursor.getInt(l);
				mDBHelper.updateByPosition(l1, k1);
				for (int i2 = i + 1; i2 <= j; i2++)
				{
					mCursor.moveToPosition(i2);
					int k2 = mCursor.getInt(l);
					int l2 = mCursor.getInt(i1);
					IphoneWorldClockDBHelper iphoneworldclockdbhelper = mDBHelper;
					int i3 = l2 - k;
					iphoneworldclockdbhelper.updateByPosition(k2, i3);
				}

			} else
			if (i > j)
			{
				mCursor.moveToPosition(j);
				int j3 = mCursor.getInt(l);
				int k3 = mCursor.getInt(i1);
				mCursor.moveToPosition(i);
				int l3 = mCursor.getInt(l);
				mDBHelper.updateByPosition(l3, k3);
				for (int j2 = i - k; j2 >= j; j2--)
				{
					mCursor.moveToPosition(j2);
					int i4 = mCursor.getInt(l);
					int j4 = mCursor.getInt(i1);
					IphoneWorldClockDBHelper iphoneworldclockdbhelper1 = mDBHelper;
					int k4 = j4 + 1;
					iphoneworldclockdbhelper1.updateByPosition(i4, k4);
				}

			}
			IphoneWorldClockActivity iphoneworldclockactivity = IphoneWorldClockActivity.this;
			Cursor cursor = mDBHelper.loadAll();
			iphoneworldclockactivity.mCursor = cursor;
			IphoneWorldClockListAdapter iphoneworldclocklistadapter = mListAdapter;
			Cursor cursor1 = mCursor;
			iphoneworldclocklistadapter.changeCursor(cursor1);
		}

		2()
		{
			this$0 = IphoneWorldClockActivity.this;
			super();
		}
	}

}
