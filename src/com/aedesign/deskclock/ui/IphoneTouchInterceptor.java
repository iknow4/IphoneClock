// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTouchInterceptor.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ListView;

public class IphoneTouchInterceptor extends ListView
{
	public interface RemoveListener
	{

		public abstract void remove(int i);
	}

	public interface DropListener
	{

		public abstract void drop(int i, int j);
	}

	public interface DragListener
	{

		public abstract void drag(int i, int j);
	}


	private static final boolean DEBUG = true;
	private static final int FLING = 0;
	private static final int SLIDE = 1;
	private static final String TAG = "IphoneTouchInterceptor";
	private static final int TRASH = 2;
	private boolean isDeleteMode;
	private Bitmap mDragBitmap;
	private DragListener mDragListener;
	private int mDragPointX;
	private int mDragPointY;
	private int mDragPos;
	private ImageView mDragView;
	private int mDrawingCacheAlpha;
	private float mDrawingCacheHorizontalMargin;
	private DropListener mDropListener;
	private GestureDetector mGestureDetector;
	private int mHeight;
	private int mItemHeightExpanded;
	private int mItemHeightHalf;
	private int mItemHeightNormal;
	private int mLowerBound;
	private RemoveListener mRemoveListener;
	private int mRemoveMode;
	private int mSrcDragPos;
	private Rect mTempRect;
	private final int mTouchSlop;
	private Drawable mTrashcan;
	private int mUpperBound;
	private WindowManager mWindowManager;
	private android.view.WindowManager.LayoutParams mWindowParams;
	private int mXOffset;
	private int mYOffset;

	public IphoneTouchInterceptor(Context context, AttributeSet attributeset)
	{
		super(context, attributeset);
		mRemoveMode = -1;
		Rect rect = new Rect();
		mTempRect = rect;
		isDeleteMode = null;
		int i = ViewConfiguration.get(context).getScaledTouchSlop();
		mTouchSlop = i;
		Resources resources = getResources();
		int j = resources.getDimensionPixelSize(0x7f090007);
		mItemHeightNormal = j;
		int k = mItemHeightNormal / 2;
		mItemHeightHalf = k;
		int l = resources.getDimensionPixelSize(0x7f090008);
		mItemHeightExpanded = l;
	}

	private void adjustScrollBounds(int i)
	{
		int j = mHeight / 3;
		if (i >= j)
		{
			int k = mHeight / 3;
			mUpperBound = k;
		}
		int l = (mHeight * 2) / 3;
		if (i <= l)
		{
			int i1 = (mHeight * 2) / 3;
			mLowerBound = i1;
		}
	}

	private void doExpansion()
	{
		int i;
		int l;
		int k1;
		View view;
		int k2;
		i = 1;
		int j = mDragPos;
		int k = getFirstVisiblePosition();
		l = j - k;
		int i1 = mDragPos;
		int j1 = mSrcDragPos;
		if (i1 > j1)
			l++;
		k1 = getHeaderViewsCount();
		int l1 = mSrcDragPos;
		int i2 = getFirstVisiblePosition();
		int j2 = l1 - i2;
		view = getChildAt(j2);
		k2 = 0;
_L2:
		View view1;
		int l2;
		byte byte0;
		view1 = getChildAt(k2);
		if (view1 == null)
			return;
		l2 = mItemHeightNormal;
		byte0 = null;
		if (mDragPos >= k1 || k2 != k1)
			break; /* Loop/switch isn't completed */
		android.view.ViewGroup.LayoutParams layoutparams;
		if (view1.equals(view))
			byte0 = 4;
		else
			l2 = mItemHeightExpanded;
_L3:
		layoutparams = view1.getLayoutParams();
		layoutparams.height = l2;
		view1.setLayoutParams(layoutparams);
		view1.setVisibility(byte0);
		k2++;
		if (true) goto _L2; else goto _L1
_L1:
label0:
		{
			if (!view1.equals(view))
				break MISSING_BLOCK_LABEL_237;
			int i3 = mDragPos;
			int j3 = mSrcDragPos;
			if (i3 != j3)
			{
				int k3 = getPositionForView(view1);
				int l3 = getCount() - i;
				if (k3 != l3)
					break label0;
			}
			byte0 = 4;
		}
		  goto _L3
		byte0 = 4;
		l2 = 1;
		  goto _L3
		if (k2 == l && mDragPos >= k1)
		{
			int i4 = mDragPos;
			int j4 = getCount() - i;
			if (i4 < j4)
				l2 = mItemHeightExpanded;
		}
		  goto _L3
	}

	private void dragView(int i, int j)
	{
		int k;
		int l2;
		byte byte0 = 2;
		k = 0;
		android.view.WindowManager.LayoutParams layoutparams = mWindowParams;
		float f = mDrawingCacheAlpha;
		layoutparams.alpha = f;
		android.view.WindowManager.LayoutParams layoutparams1 = mWindowParams;
		float f1 = mDrawingCacheHorizontalMargin;
		layoutparams1.horizontalMargin = f1;
		android.view.WindowManager.LayoutParams layoutparams3;
		int l1;
		int i2;
		int j2;
		int k2;
		WindowManager windowmanager;
		ImageView imageview;
		android.view.WindowManager.LayoutParams layoutparams4;
		int i3;
		if (mRemoveMode == 0 || mRemoveMode == byte0)
		{
			android.view.WindowManager.LayoutParams layoutparams2 = mWindowParams;
			int l = mDragPointX;
			int i1 = i - l;
			int j1 = mXOffset;
			int k1 = i1 + j1;
			layoutparams2.x = k1;
		} else
		{
			mWindowParams.x = k;
		}
		layoutparams3 = mWindowParams;
		l1 = mDragPointY;
		i2 = j - l1;
		j2 = mYOffset;
		k2 = i2 + j2;
		layoutparams3.y = k2;
		windowmanager = mWindowManager;
		imageview = mDragView;
		layoutparams4 = mWindowParams;
		windowmanager.updateViewLayout(imageview, layoutparams4);
		if (mTrashcan == null) goto _L2; else goto _L1
_L1:
		l2 = mDragView.getWidth();
		i3 = (getHeight() * 3) / 4;
		if (j <= i3) goto _L4; else goto _L3
_L3:
		mTrashcan.setLevel(byte0);
_L2:
		return;
_L4:
		if (l2 > 0)
		{
			int j3 = l2 / 4;
			if (i > j3)
			{
				mTrashcan.setLevel(1);
				continue; /* Loop/switch isn't completed */
			}
		}
		mTrashcan.setLevel(k);
		if (true) goto _L2; else goto _L5
_L5:
	}

	private int getItemForPosition(int i)
	{
		int i1;
		int j1;
		int j = mDragPointY;
		int k = i - j;
		int l = mItemHeightHalf;
		i1 = k - l;
		j1 = myPointToPosition(0, i1);
		if (j1 < 0) goto _L2; else goto _L1
_L1:
		int k1 = mSrcDragPos;
		if (j1 <= k1)
			j1++;
_L4:
		return j1;
_L2:
		if (i1 < 0)
			j1 = null;
		if (true) goto _L4; else goto _L3
_L3:
	}

	private int myPointToPosition(int i, int j)
	{
		int k = 1;
		if (j >= 0) goto _L2; else goto _L1
_L1:
		int j1;
		int l = mItemHeightNormal + j;
		j1 = myPointToPosition(i, l);
		if (j1 <= 0) goto _L2; else goto _L3
_L3:
		int i1 = j1 - k;
_L5:
		return i1;
_L2:
		Rect rect = mTempRect;
		int k1 = getChildCount() - k;
		do
		{
			if (k1 < 0)
				break;
			getChildAt(k1).getHitRect(rect);
			i1 = rect.contains(i, j);
			if (i1 != 0)
			{
				i1 = getFirstVisiblePosition() + k1;
				continue; /* Loop/switch isn't completed */
			}
			k1--;
		} while (true);
		i1 = -1;
		if (true) goto _L5; else goto _L4
_L4:
	}

	private void startDragging(Bitmap bitmap, int i, int j)
	{
		stopDragging();
		android.view.WindowManager.LayoutParams layoutparams = new android.view.WindowManager.LayoutParams();
		mWindowParams = layoutparams;
		mWindowParams.gravity = 51;
		android.view.WindowManager.LayoutParams layoutparams1 = mWindowParams;
		int k = mDragPointX;
		int l = i - k;
		int i1 = mXOffset;
		int j1 = l + i1;
		layoutparams1.x = j1;
		android.view.WindowManager.LayoutParams layoutparams2 = mWindowParams;
		int k1 = mDragPointY;
		int l1 = j - k1;
		int i2 = mYOffset;
		int j2 = l1 + i2;
		layoutparams2.y = j2;
		mWindowParams.height = -1;
		mWindowParams.width = -1;
		mWindowParams.flags = 920;
		mWindowParams.format = -1;
		mWindowParams.windowAnimations = null;
		Context context = getContext();
		ImageView imageview = new ImageView(context);
		imageview.setImageBitmap(bitmap);
		mDragBitmap = bitmap;
		WindowManager windowmanager = (WindowManager)context.getSystemService("window");
		mWindowManager = windowmanager;
		WindowManager windowmanager1 = mWindowManager;
		android.view.WindowManager.LayoutParams layoutparams3 = mWindowParams;
		windowmanager1.addView(imageview, layoutparams3);
		mDragView = imageview;
	}

	private void stopDragging()
	{
		Drawable drawable = 0;
		if (mDragView != null)
		{
			mDragView.setVisibility(8);
			WindowManager windowmanager = (WindowManager)getContext().getSystemService("window");
			ImageView imageview = mDragView;
			windowmanager.removeView(imageview);
			mDragView.setImageDrawable(drawable);
			mDragView = drawable;
		}
		if (mDragBitmap != null)
		{
			mDragBitmap.recycle();
			mDragBitmap = drawable;
		}
		if (mTrashcan != null)
			mTrashcan.setLevel(0);
	}

	private void unExpandViews(boolean flag)
	{
		int i = 0;
		int j = 0;
		do
		{
			View view = getChildAt(j);
			if (view == null)
			{
				if (flag)
				{
					int k = getFirstVisiblePosition();
					int l = getChildAt(i).getTop();
					android.widget.ListAdapter listadapter = getAdapter();
					setAdapter(listadapter);
					setSelectionFromTop(k, l);
				}
				android.view.ViewGroup.LayoutParams layoutparams;
				int i1;
				try
				{
					layoutChildren();
					view = getChildAt(j);
				}
				catch (IllegalStateException illegalstateexception) { }
				if (view == null)
					return;
			}
			layoutparams = view.getLayoutParams();
			i1 = mItemHeightNormal;
			layoutparams.height = i1;
			view.setLayoutParams(layoutparams);
			view.setVisibility(i);
			j++;
		} while (true);
	}

	public boolean isDeleteMode()
	{
		return isDeleteMode;
	}

	public boolean onInterceptTouchEvent(MotionEvent motionevent)
	{
		Object obj;
		obj = mRemoveListener;
		if (obj != null)
		{
			obj = mGestureDetector;
			if (obj == null)
			{
				int i = mRemoveMode;
				if (i == 0)
				{
					Context context = getContext();
					1 1_1 = new 1();
					i = new GestureDetector(context, 1_1);
					mGestureDetector = i;
				}
			}
		}
		i = mDragListener;
		if (i != null) goto _L2; else goto _L1
_L1:
		i = mDropListener;
		if (i == null) goto _L3; else goto _L2
_L2:
		boolean flag = isDeleteMode;
		if (flag) goto _L3; else goto _L4
_L4:
		int j = motionevent.getAction();
		j;
		JVM INSTR tableswitch 0 0: default 108
	//	               0 116;
		   goto _L3 _L5
_L3:
		j = super.onInterceptTouchEvent(motionevent);
_L7:
		return j;
_L5:
		int k = (int)motionevent.getX();
		int l = (int)motionevent.getY();
		int i1 = pointToPosition(k, l);
		j = -1;
		if (i1 == j)
			continue; /* Loop/switch isn't completed */
		j = getFirstVisiblePosition();
		j = i1 - j;
		ViewGroup viewgroup = (ViewGroup)getChildAt(j);
		j = viewgroup.getLeft();
		j = k - j;
		mDragPointX = j;
		j = viewgroup.getTop();
		j = l - j;
		mDragPointY = j;
		j = (int)motionevent.getRawX() - k;
		mXOffset = j;
		j = (int)motionevent.getRawY() - l;
		mYOffset = j;
		View view = viewgroup.findViewById(0x7f0d0052);
		int j1 = view.getVisibility();
		j = view.getLeft();
		if (k <= j || j1 != 0)
			break; /* Loop/switch isn't completed */
		viewgroup.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(viewgroup.getDrawingCache());
		startDragging(bitmap, k, l);
		mDragPos = i1;
		j = mDragPos;
		mSrcDragPos = j;
		j = getHeight();
		mHeight = j;
		int k1 = mTouchSlop;
		j = l - k1;
		int l1 = mHeight / 3;
		j = Math.min(j, l1);
		mUpperBound = j;
		j = l + k1;
		int i2 = (mHeight * 2) / 3;
		j = Math.max(j, i2);
		mLowerBound = j;
		viewgroup.setVisibility(4);
		j = null;
		if (true) goto _L7; else goto _L6
_L6:
		stopDragging();
		if (true) goto _L3; else goto _L8
_L8:
	}

	public boolean onTouchEvent(MotionEvent motionevent)
	{
		boolean flag;
		boolean flag1;
		int i;
		flag = false;
		flag1 = true;
		Object obj = mGestureDetector;
		if (obj != null)
		{
			obj = mGestureDetector;
			((GestureDetector) (obj)).onTouchEvent(motionevent);
		}
		obj = mDragListener;
		if (obj == null)
		{
			obj = mDropListener;
			if (obj == null)
				break MISSING_BLOCK_LABEL_589;
		}
		obj = mDragView;
		if (obj == null)
			break MISSING_BLOCK_LABEL_589;
		boolean flag2 = isDeleteMode;
		if (flag2)
			break MISSING_BLOCK_LABEL_589;
		i = motionevent.getAction();
		i;
		JVM INSTR tableswitch 0 3: default 112
	//	               0 297
	//	               1 118
	//	               2 297
	//	               3 118;
		   goto _L1 _L2 _L3 _L2 _L3
_L1:
		Object obj1 = flag1;
_L11:
		return ((boolean) (obj1));
_L3:
		Rect rect;
		rect = mTempRect;
		mDragView.getDrawingRect(rect);
		stopDragging();
		obj1 = mRemoveMode;
		if (obj1 != flag1) goto _L5; else goto _L4
_L4:
		obj1 = motionevent.getX();
		float f = (rect.right * 3) / 4;
		obj1 !== f;
		if (obj1 <= 0) goto _L5; else goto _L6
_L6:
		obj1 = mRemoveListener;
		if (obj1 != null)
		{
			obj1 = mRemoveListener;
			int j = mSrcDragPos;
			((RemoveListener) (obj1)).remove(j);
		}
		unExpandViews(flag1);
		  goto _L1
_L5:
		obj1 = mDropListener;
		if (obj1 != null)
		{
			obj1 = mDragPos;
			if (obj1 >= 0)
			{
				obj1 = mDragPos;
				int k = getCount();
				if (obj1 < k)
				{
					obj1 = mDropListener;
					int l = mSrcDragPos;
					int i1 = mDragPos;
					((DropListener) (obj1)).drop(l, i1);
				}
			}
		}
		unExpandViews(flag);
		  goto _L1
_L2:
		int k1;
		int l1;
		int j1 = (int)motionevent.getX();
		obj1 = motionevent.getY();
		k1 = (int)obj1;
		dragView(j1, k1);
		l1 = getItemForPosition(k1);
		if (l1 < 0) goto _L1; else goto _L7
_L7:
		if (i == 0) goto _L9; else goto _L8
_L8:
		obj1 = mDragPos;
		if (l1 == obj1) goto _L10; else goto _L9
_L9:
		obj1 = mDragListener;
		if (obj1 != null)
		{
			obj1 = mDragListener;
			int i2 = mDragPos;
			((DragListener) (obj1)).drag(i2, l1);
		}
		mDragPos = l1;
		doExpansion();
_L10:
		int j2 = null;
		adjustScrollBounds(k1);
		obj1 = mLowerBound;
		if (k1 > obj1)
		{
			obj1 = getLastVisiblePosition();
			int k2 = getCount() - flag1;
			if (obj1 < k2)
			{
				obj1 = mHeight;
				int l2 = mLowerBound;
				obj1 = (obj1 + l2) / 2;
				if (k1 > obj1)
				{
					obj1 = 16;
					j2 = ((int) (obj1));
				} else
				{
					obj1 = 4;
					j2 = ((int) (obj1));
				}
			} else
			{
				j2 = 1;
			}
		} else
		{
			obj1 = mUpperBound;
			if (k1 < obj1)
			{
				obj1 = mUpperBound / 2;
				if (k1 < obj1)
				{
					obj1 = 65520;
					j2 = ((int) (obj1));
				} else
				{
					obj1 = -1;
					j2 = ((int) (obj1));
				}
				obj1 = getFirstVisiblePosition();
				if (obj1 == 0)
				{
					obj1 = getChildAt(flag).getTop();
					int i3 = getPaddingTop();
					if (obj1 >= i3)
						j2 = null;
				}
			}
		}
		if (j2 == null);
		  goto _L1
		try
		{
			obj1 = super.onTouchEvent(motionevent);
		}
		catch (Exception exception)
		{
			StringBuilder stringbuilder = (new StringBuilder()).append("onTouchEvent EXCEPTION = ");
			String s = exception.getMessage();
			String s1 = stringbuilder.append(s).toString();
			Log.e("IphoneTouchInterceptor", s1);
			obj1 = flag;
		}
		  goto _L11
	}

	public void setDeleteMode(boolean flag)
	{
		isDeleteMode = flag;
	}

	public void setDragListener(DragListener draglistener)
	{
		mDragListener = draglistener;
	}

	public void setDrawingCacheAlpha(int i)
	{
		mDrawingCacheAlpha = i;
	}

	public void setDrawingCacheHorizontalMargin(float f)
	{
		mDrawingCacheHorizontalMargin = f;
	}

	public void setDropListener(DropListener droplistener)
	{
		mDropListener = droplistener;
	}

	public void setRemoveListener(RemoveListener removelistener)
	{
		mRemoveListener = removelistener;
	}

	public void setTrashcan(Drawable drawable)
	{
		mTrashcan = drawable;
		mRemoveMode = 2;
	}







	private class 1 extends android.view.GestureDetector.SimpleOnGestureListener
	{

		final IphoneTouchInterceptor this$0;

		public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
		{
			boolean flag = true;
			ImageView imageview = mDragView;
			float f2;
			if (imageview != null)
			{
				f2 = f != 0x447a0000;
				if (f2 > 0)
				{
					Rect rect = mTempRect;
					mDragView.getDrawingRect(rect);
					f2 = motionevent1.getX();
					float f3 = (rect.right * 2) / 3;
					f2 !== f3;
					if (f2 > 0)
					{
						stopDragging();
						Object obj = mRemoveListener;
						int i = mSrcDragPos;
						((RemoveListener) (obj)).remove(i);
						obj = IphoneTouchInterceptor.this;
						((IphoneTouchInterceptor) (obj)).unExpandViews(flag);
					}
				}
				obj = flag;
			} else
			{
				obj = null;
			}
			return ((boolean) (obj));
		}

		1()
		{
			this$0 = IphoneTouchInterceptor.this;
			super();
		}
	}

}
