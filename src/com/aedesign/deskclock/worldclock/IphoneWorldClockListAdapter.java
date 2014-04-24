// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneWorldClockListAdapter.java

package com.aedesign.deskclock.worldclock;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.*;
import android.widget.CursorAdapter;
import android.widget.TextView;

// Referenced classes of package com.aedesign.deskclock.worldclock:
//			IphoneWorldClockListItem, IphoneAnalogClock

public class IphoneWorldClockListAdapter extends CursorAdapter
{
	public interface OnContentChangedListener
	{

		public abstract void onContentChanged(IphoneWorldClockListAdapter iphoneworldclocklistadapter);
	}


	private static final boolean DEBUG = false;
	private static final String TAG = "IphoneWorldClockListAdapter";
	private OnContentChangedListener listener;
	private Context mContext;
	private Handler mHandler;

	public IphoneWorldClockListAdapter(Context context, Cursor cursor, boolean flag, Handler handler)
	{
		super(context, cursor, flag);
		mContext = context;
		mHandler = handler;
	}

	public void bindView(View view, Context context, Cursor cursor)
	{
	}

	public View getView(int i, View view, ViewGroup viewgroup)
	{
		Cursor cursor = getCursor();
		cursor.moveToPosition(i);
		Context context = mContext;
		View view1 = newView(context, cursor, viewgroup);
		Context context1 = mContext;
		bindView(view1, context1, cursor);
		return view1;
	}

	public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
	{
		TextView textview3;
		boolean flag = null;
		IphoneWorldClockListItem iphoneworldclocklistitem = (IphoneWorldClockListItem)((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x7f030016, viewgroup, flag);
		Object obj = mHandler;
		iphoneworldclocklistitem.setMHandler(((Handler) (obj)));
		int i = cursor.getInt(flag);
		iphoneworldclocklistitem.setItemId(i);
		TextView textview = (TextView)iphoneworldclocklistitem.findViewById(0x7f0d004f);
		boolean flag1 = DateFormat.is24HourFormat(mContext);
		TextView textview1;
		String s;
		IphoneAnalogClock iphoneanalogclock;
		String s1;
		TextView textview2;
		int j;
		if (flag1)
			flag1 = 4;
		else
			flag1 = flag;
		textview.setVisibility(flag1);
		textview1 = (TextView)iphoneworldclocklistitem.findViewById(0x7f0d004c);
		s = cursor.getString(1);
		textview1.setText(flag1);
		iphoneanalogclock = (IphoneAnalogClock)iphoneworldclocklistitem.findViewById(0x7f0d004d);
		iphoneanalogclock.setAmPmTextView(textview);
		s1 = cursor.getString(2);
		iphoneanalogclock.setTimeZone(flag1);
		textview2 = (TextView)iphoneworldclocklistitem.findViewById(0x7f0d0050);
		iphoneanalogclock.setWorldClockTimeTextView(textview2);
		j = iphoneanalogclock.dayComparCurrentTimeZone();
		textview3 = (TextView)iphoneworldclocklistitem.findViewById(0x7f0d0051);
		j;
		JVM INSTR tableswitch -1 1: default 224
	//	               -1 239
	//	               0 249
	//	               1 259;
		   goto _L1 _L2 _L3 _L4
_L1:
		iphoneworldclocklistitem.updateViews();
		return iphoneworldclocklistitem;
_L2:
		textview3.setText(0x7f0a000b);
		continue; /* Loop/switch isn't completed */
_L3:
		textview3.setText(0x7f0a0009);
		continue; /* Loop/switch isn't completed */
_L4:
		textview3.setText(0x7f0a000a);
		if (true) goto _L1; else goto _L5
_L5:
	}

	protected void onContentChanged()
	{
		super.onContentChanged();
		if (getCursor() != null && !getCursor().isClosed() && listener != null)
			listener.onContentChanged(this);
	}

	public void setOnContentChangedListener(OnContentChangedListener oncontentchangedlistener)
	{
		listener = oncontentchangedlistener;
	}
}
