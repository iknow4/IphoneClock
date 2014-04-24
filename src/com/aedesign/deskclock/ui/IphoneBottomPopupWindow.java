// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneBottomPopupWindow.java

package com.aedesign.deskclock.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.Button;
import android.widget.PopupWindow;

// Referenced classes of package com.aedesign.deskclock.ui:
//			IphoneBottomPopupView

public class IphoneBottomPopupWindow extends PopupWindow
{
	public interface OnPopStateChangeListener
	{

		public abstract void onPopDissmiss();

		public abstract void onPopShow();
	}


	public static final int BUTTON_BACKGROUND_BLACK = 0x7f020083;
	public static final int BUTTON_BACKGROUND_GRAY = 0x7f020086;
	public static final int BUTTON_BACKGROUND_GREEN = 0x7f020089;
	public static final int BUTTON_BACKGROUND_RED = 0x7f02008c;
	public static final int BUTTON_BACKGROUND_WHITE = 0x7f02008f;
	public static final int BUTTON_BACKGROUND_YELLOW = 0x7f020092;
	private final boolean DEBUG = null;
	public final int MESSAGE_DISMISS_WINDOW = 121;
	public final int MESSAGE_POPUP_WINDOW = 120;
	private final String TAG = "IphoneBottomPopupWindow";
	private Context mContext;
	private android.widget.PopupWindow.OnDismissListener mDismissListener;
	private boolean mFinishAdd;
	private IphoneBottomPopupView mIphoneBottomPopupView;
	private OnPopStateChangeListener mOnPopStateChangeListener;
	private final Handler mPopWindowHandler;

	public IphoneBottomPopupWindow(Context context)
	{
		super(context);
		mFinishAdd = null;
		1 1_1 = new 1();
		mPopWindowHandler = 1_1;
		mContext = context;
		IphoneBottomPopupView iphonebottompopupview = (IphoneBottomPopupView)LayoutInflater.from(context).inflate(0x7f030019, null);
		mIphoneBottomPopupView = iphonebottompopupview;
		mIphoneBottomPopupView.init();
		IphoneBottomPopupView iphonebottompopupview1 = mIphoneBottomPopupView;
		setContentView(iphonebottompopupview1);
		setBackgroundDrawable(null);
		setInputMethodMode(2);
		updateWidthHeight();
		setAnimationStyle(0x7f0b0006);
	}

	public Button addButton(int i, int j)
	{
		String s = mContext.getString(j);
		return addButton(i, s);
	}

	public Button addButton(int i, String s)
	{
		Context context = mContext;
		Button button = new Button(context);
		button.setBackgroundResource(i);
		button.setTextSize(0x41a00000);
		button.setText(s);
		if (i == 0x7f02008f)
		{
			android.content.res.ColorStateList colorstatelist = mContext.getResources().getColorStateList(0x7f08000b);
			button.setTextColor(colorstatelist);
		} else
		if (i == 0x7f020092)
			button.setTextColor(0xff000000);
		else
			button.setTextColor(-1);
		mIphoneBottomPopupView.addView(button);
		return button;
	}

	public Button addCancelButton(int i, int j)
	{
		Button button = addButton(i, j);
		2 2_1 = new 2();
		button.setOnClickListener(2_1);
		return button;
	}

	public void addView(View view)
	{
		mIphoneBottomPopupView.addView(view);
	}

	public void addViewWithLayoutParams(View view, android.widget.LinearLayout.LayoutParams layoutparams)
	{
		mIphoneBottomPopupView.addViewWithLayoutParams(view, layoutparams);
	}

	public void cancelPopupWindow()
	{
		if (isShowing())
		{
			mIphoneBottomPopupView.setImageViewVisibility(4);
			Handler handler = mPopWindowHandler;
			Message message = mPopWindowHandler.obtainMessage(121);
			handler.sendMessage(message);
		}
	}

	public void cancelPopupWindowImmediately()
	{
		setAnimationStyle(0);
		update();
		cancelPopupWindow();
		3 3_1 = new 3();
		mDismissListener = 3_1;
	}

	public void dismiss()
	{
		super.dismiss();
		if (mDismissListener != null)
		{
			mDismissListener.onDismiss();
			mDismissListener = null;
		}
		if (mOnPopStateChangeListener != null)
			mOnPopStateChangeListener.onPopDissmiss();
	}

	public void finishAddButton()
	{
		mFinishAdd = true;
	}

	public boolean isFinishAddButton()
	{
		return mFinishAdd;
	}

	public void reBeginAddButton()
	{
		mFinishAdd = null;
	}

	public void removeAllViews()
	{
		mIphoneBottomPopupView.removeAllButtons();
	}

	public void setBottomPupupWindowBackgroundDrawable(Drawable drawable)
	{
		mIphoneBottomPopupView.setBottomPopupWindowBackgroundDrawable(drawable);
	}

	public void setBottonPupupWindowBackgroundRes(int i)
	{
		Drawable drawable = mContext.getResources().getDrawable(i);
		setBottomPupupWindowBackgroundDrawable(drawable);
	}

	public void setOnPopStateChangeListener(OnPopStateChangeListener onpopstatechangelistener)
	{
		mOnPopStateChangeListener = onpopstatechangelistener;
	}

	public void showPopupWindow(View view)
	{
		Message message = null;
		if (!isShowing()) goto _L2; else goto _L1
_L1:
		update();
_L4:
		return;
_L2:
		showAtLocation(view, message, message, message);
		Handler handler = mPopWindowHandler;
		Message message1 = mPopWindowHandler.obtainMessage(120);
		handler.sendMessageDelayed(message, 600L);
		if (mOnPopStateChangeListener != null)
			mOnPopStateChangeListener.onPopShow();
		if (true) goto _L4; else goto _L3
_L3:
	}

	public boolean updateIphoneBottomMenu(int i, KeyEvent keyevent)
	{
		boolean flag = isShowing();
		if (flag)
		{
			cancelPopupWindow();
			flag = true;
		} else
		{
			flag = null;
		}
		return flag;
	}

	public void updateWidthHeight()
	{
		DisplayMetrics displaymetrics = mContext.getResources().getDisplayMetrics();
		int i = displaymetrics.widthPixels;
		setWidth(i);
		int j = displaymetrics.heightPixels;
		setHeight(j);
	}


	private class 1 extends Handler
	{

		final IphoneBottomPopupWindow this$0;

		public void handleMessage(Message message)
		{
			message.what;
			JVM INSTR tableswitch 120 121: default 28
		//		               120 29
		//		               121 43;
			   goto _L1 _L2 _L3
_L1:
			return;
_L2:
			mIphoneBottomPopupView.setImageViewVisibility(0);
			continue; /* Loop/switch isn't completed */
_L3:
			dismiss();
			if (true) goto _L1; else goto _L4
_L4:
		}

		1()
		{
			this$0 = IphoneBottomPopupWindow.this;
			super();
		}
	}


	private class 2
		implements android.view.View.OnClickListener
	{

		final IphoneBottomPopupWindow this$0;

		public void onClick(View view)
		{
			cancelPopupWindow();
		}

		2()
		{
			this$0 = IphoneBottomPopupWindow.this;
			super();
		}
	}


	private class 3
		implements android.widget.PopupWindow.OnDismissListener
	{

		final IphoneBottomPopupWindow this$0;

		public void onDismiss()
		{
			setAnimationStyle(0x7f0b0006);
		}

		3()
		{
			this$0 = IphoneBottomPopupWindow.this;
			super();
		}
	}

}
