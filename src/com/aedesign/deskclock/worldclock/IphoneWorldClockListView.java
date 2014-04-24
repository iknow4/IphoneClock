// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneWorldClockListView.java

package com.aedesign.deskclock.worldclock;

import android.content.Context;
import android.util.AttributeSet;
import com.aedesign.deskclock.ui.IphoneTouchInterceptor;

// Referenced classes of package com.aedesign.deskclock.worldclock:
//			IphoneWorldClockListItem

public class IphoneWorldClockListView extends IphoneTouchInterceptor
{

	public static boolean mEdit;
	private IphoneWorldClockListItem mSelectedItem;

	public IphoneWorldClockListView(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		setDivider(null);
		setScrollbarFadingEnabled(true);
		setCacheColorHint(0);
		setFadingEdgeLength(0);
		setDrawingCacheAlpha(60);
	}

	public void changeToComplete()
	{
		int i = 0;
		do
		{
			int j = getChildCount();
			if (i < j)
			{
				((IphoneWorldClockListItem)getChildAt(i)).changeToComplete();
				boolean flag = mEdit;
				i++;
			} else
			{
				return;
			}
		} while (true);
	}

	public void changeToEdit()
	{
		int i = 0;
		do
		{
			int j = getChildCount();
			if (i < j)
			{
				((IphoneWorldClockListItem)getChildAt(i)).changeToEdit();
				boolean flag = mEdit;
				i++;
			} else
			{
				return;
			}
		} while (true);
	}

	public IphoneWorldClockListItem getMSelectedItem()
	{
		return mSelectedItem;
	}

	public void setMSelectedItem(IphoneWorldClockListItem iphoneworldclocklistitem)
	{
		mSelectedItem = iphoneworldclocklistitem;
	}

	static 
	{
		boolean flag = mEdit;
	}
}
