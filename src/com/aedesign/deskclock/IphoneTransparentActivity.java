// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   IphoneTransparentActivity.java

package com.aedesign.deskclock;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.content.DialogInterface;

public class IphoneTransparentActivity extends Activity
{

	Ringtone ringtone;

	public IphoneTransparentActivity()
	{
	}

	private void playAlert()
	{
		String s = getIntent().getStringExtra("alarm_alert_uri");
		if (s != null && !s.equals(""))
		{
			Uri uri = Uri.parse(s);
			Ringtone ringtone1 = RingtoneManager.getRingtone(this, uri);
			ringtone = ringtone1;
			ringtone.play();
		}
	}

	private void showAlertMessage()
	{
		android.app.AlertDialog.Builder builder = (new android.app.AlertDialog.Builder(this)).setTitle("Jason");
		Dialog D = new Dialog();
		builder.setNegativeButton(android.R.string.yes, D).show();
	}

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		showAlertMessage();
		playAlert();
	}

	private class Dialog implements android.content.DialogInterface.OnClickListener
	{

		final IphoneTransparentActivity Activity;

		public void onClick(DialogInterface dialoginterface, int i)
		{
			if (ringtone != null && ringtone.isPlaying())
				ringtone.stop();
			finish();
		}

		public  Dialog()
		{
			Activity = IphoneTransparentActivity.this;
			//super();
		}
	}

}
