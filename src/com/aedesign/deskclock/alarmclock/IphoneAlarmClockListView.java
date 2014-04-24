// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneAlarmClockListView.java

package com.aedesign.deskclock.alarmclock;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

// Referenced classes of package com.aedesign.deskclock.alarmclock:
//			IphoneAlarmClockListItem

public class IphoneAlarmClockListView extends ListView
{

	private IphoneAlarmClockListItem mSelectedItem;

	public IphoneAlarmClockListView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		setDivider(null);
		setScrollbarFadingEnabled(true);
		setCacheColorHint(0);
		setFadingEdgeLength(0);
	}

	public IphoneAlarmClockListItem getMSelectedItem()
	{
		return mSelectedItem;
	}

	public void setMSelectedItem(IphoneAlarmClockListItem iphonealarmclocklistitem)
	{
		mSelectedItem = iphonealarmclocklistitem;
	}
}
