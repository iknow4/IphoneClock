// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ZoneList.java

package com.aedesign.deskclock.worldclock;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.aedesign.deskclock.ui.IphoneNoTitleActivity;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;
import com.aedesign.deskclock.worldclock.ZoneList.ZoneListAdapter;

public class ZoneList extends IphoneNoTitleActivity
{
	class ZoneListAdapter extends BaseAdapter
	{

		private static final int MINI_LIST_COUNT = 9;
		private Context mContext;
		private Handler mHandler;
		private List mList;

		public int getCount()
		{
			byte byte0 = 9;
			int i = mList.size();
			if (i < byte0)
				i = byte0;
			else
				i = mList.size();
			return i;
		}

		public Object getItem(int i)
		{
			int j = mList.size();
			Object obj;
			if (i < j)
				obj = mList.get(i);
			else
				obj = 0;
			return obj;
		}

		public long getItemId(int i)
		{
			return (long)i;
		}

		public View getView(int j, View view, ViewGroup viewgroup)
		{
			boolean flag = false;
			Button button = (Button)((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(0x7f030020, viewgroup, flag);
			int k = mList.size();
			class 1
				implements android.view.View.OnClickListener
			{

				final ZoneListAdapter this$0;
				final int val$i;

				public void onClick(View view1)
				{
					ZoneListAdapter zonelistadapter = ZoneListAdapter.this;
					int l = i;
					HashMap hashmap = (HashMap)zonelistadapter.getItem(l);
					Message message = new Message();
					message.obj = hashmap;
					mHandler.sendMessage(message);
				}

				1()
				{
					this$0 = ZoneListAdapter.this;
					i = j;
					super();
				}
			}

			final int i;
			1 1_1;
			if (j < k)
			{
				String s = (String)((HashMap)mList.get(j)).get("name");
				button.setText(s);
				button.setClickable(true);
				button.setBackgroundResource(0x7f02004b);
			} else
			{
				button.setClickable(flag);
				button.setBackgroundResource(0x7f02004d);
			}
			i = j;
			1_1 = new 1();
			button.setOnClickListener(1_1);
			return button;
		}


		public ZoneListAdapter(Context context, List list, Handler handler)
		{
			mContext = context;
			mList = list;
			mHandler = handler;
		}
	}

	class MyComparator
		implements Comparator
	{

		private String mSortingKey;

		private boolean isComparable(Object obj)
		{
			if (obj == null) goto _L2; else goto _L1
_L1:
			boolean flag = obj instanceof Comparable;
			if (!flag) goto _L2; else goto _L3
_L3:
			flag = true;
_L5:
			return flag;
_L2:
			flag = null;
			if (true) goto _L5; else goto _L4
_L4:
		}

		public volatile int compare(Object obj, Object obj1)
		{
			HashMap hashmap = (HashMap)obj;
			HashMap hashmap1 = (HashMap)obj1;
			return compare(((HashMap) (obj)), ((HashMap) (obj1)));
		}

		public int compare(HashMap hashmap, HashMap hashmap1)
		{
			String s = mSortingKey;
			Object obj = hashmap.get(s);
			s = mSortingKey;
			Object obj1 = hashmap1.get(s);
			boolean flag = isComparable(obj);
			int i;
			if (!flag)
			{
				boolean flag1 = isComparable(obj1);
				if (flag1)
					i = 1;
				else
					i = null;
			} else
			{
				i = isComparable(obj1);
				if (i == 0)
					i = -1;
				else
					i = ((Comparable)obj).compareTo(obj1);
			}
			return i;
		}

		public void setSortingKey(String s)
		{
			mSortingKey = s;
		}

		public MyComparator(String s)
		{
			mSortingKey = s;
		}
	}


	private static final boolean DEBUG = false;
	private static final int HOURS_1 = 0x36ee80;
	private static final int HOURS_24 = 0x5265c00;
	private static final int HOURS_HALF = 0x1b7740;
	private static final String KEY_DISPLAYNAME = "name";
	private static final String KEY_EN = "en";
	private static final String KEY_GMT = "gmt";
	private static final String KEY_ID = "id";
	private static final String KEY_OFFSET = "offset";
	private static final int MENU_ALPHABETICAL = 1;
	private static final int MENU_TIMEZONE = 2;
	private static final String TAG = "ZoneList";
	private static final String XMLTAG_TIMEZONE = "timezone";
	private Handler mAddClockHandler;
	private ZoneListAdapter mAlphabeticalAdapter;
	private Button mCancelButton;
	private Button mClearButton;
	private int mDefault;
	private ListView mListView;
	private TextView mNoResult;
	private EditText mSearchEditText;
	private boolean mSortedByTimezone;
	private List timezoneSortedList;

	public ZoneList()
	{
		4 4_1 = new 4();
		mAddClockHandler = 4_1;
	}

	private List getZones()
	{
		byte byte0;
		byte byte1;
		int i;
		String s;
		ArrayList arraylist;
		byte0 = 3;
		byte1 = 2;
		i = 1;
		s = "ZoneList";
		arraylist = new ArrayList();
		long l = Calendar.getInstance().getTimeInMillis();
		XmlResourceParser xmlresourceparser;
		for (xmlresourceparser = getResources().getXml(0x7f050003); xmlresourceparser.next() != byte1;);
		xmlresourceparser.next();
_L6:
		if (xmlresourceparser.getEventType() == byte0)
			break MISSING_BLOCK_LABEL_221;
_L5:
		if (xmlresourceparser.getEventType() == byte1) goto _L2; else goto _L1
_L1:
		if (xmlresourceparser.getEventType() != i) goto _L4; else goto _L3
_L3:
		return arraylist;
_L4:
		XmlPullParserException xmlpullparserexception;
		xmlresourceparser.next();
		  goto _L5
_L2:
		try
		{
			if (xmlresourceparser.getName().equals("timezone"))
			{
				String s1 = xmlresourceparser.getAttributeValue(0);
				String s2 = xmlresourceparser.getAttributeValue(1);
				String s3 = xmlresourceparser.nextText();
				addItem(arraylist, s1, s2, s3, <no variable>);
			}
			for (; xmlresourceparser.getEventType() != byte0; xmlresourceparser.next());
			break MISSING_BLOCK_LABEL_210;
		}
		// Misplaced declaration of an exception variable
		catch (XmlPullParserException xmlpullparserexception)
		{
			Log.e(s, "Ill-formatted timezones.xml file");
		}
		catch (IOException ioexception)
		{
			Log.e(s, "Unable to read timezones.xml file");
		}
		  goto _L3
		xmlresourceparser.next();
		  goto _L6
		xmlresourceparser.close();
		  goto _L3
	}

	private void init()
	{
		setContentView(0x7f030018);
		ListView listview = (ListView)findViewById(0x7f0d0057);
		mListView = listview;
		mListView.setDivider(null);
		mListView.setScrollBarStyle(0);
		mListView.setScrollbarFadingEnabled(true);
		mListView.setClickable(true);
		ArrayList arraylist = new ArrayList();
		Handler handler = mAddClockHandler;
		ZoneListAdapter zonelistadapter = new ZoneListAdapter(this, arraylist, handler);
		mAlphabeticalAdapter = zonelistadapter;
		ListView listview1 = mListView;
		ZoneListAdapter zonelistadapter1 = mAlphabeticalAdapter;
		listview1.setAdapter(zonelistadapter1);
		Button button = (Button)findViewById(0x7f0d002b);
		mCancelButton = button;
		Button button1 = mCancelButton;
		1 1_1 = new 1();
		button1.setOnClickListener(1_1);
		EditText edittext = (EditText)findViewById(0x7f0d0056);
		mSearchEditText = edittext;
		EditText edittext1 = mSearchEditText;
		2 2_1 = new 2();
		edittext1.addTextChangedListener(2_1);
		Button button2 = (Button)findViewById(0x7f0d0026);
		mClearButton = button2;
		Button button3 = mClearButton;
		3 3_1 = new 3();
		button3.setOnClickListener(3_1);
		TextView textview = (TextView)findViewById(0x7f0d0058);
		mNoResult = textview;
	}

	private List searchItem(CharSequence charsequence)
	{
		byte byte0 = -1;
		ArrayList arraylist = new ArrayList();
		Object obj = null;
		int i = timezoneSortedList.size();
		for (int j = 0; j < i; j++)
		{
			HashMap hashmap = (HashMap)timezoneSortedList.get(j);
			String s = ((String)hashmap.get("name")).toLowerCase();
			String s1 = charsequence.toString().toLowerCase();
			int k = s.indexOf(s1);
			String s2 = ((String)hashmap.get("en")).toLowerCase();
			String s3 = charsequence.toString().toLowerCase();
			int l = s2.indexOf(s3);
			if (k != byte0 || l != byte0)
				arraylist.add(hashmap);
		}

		return arraylist;
	}

	private void updateViews(List list)
	{
		MyComparator mycomparator = new MyComparator("offset");
		mycomparator.setSortingKey("name");
		Collections.sort(list, mycomparator);
		Handler handler = mAddClockHandler;
		ZoneListAdapter zonelistadapter = new ZoneListAdapter(this, list, handler);
		mAlphabeticalAdapter = zonelistadapter;
		ListView listview = mListView;
		ZoneListAdapter zonelistadapter1 = mAlphabeticalAdapter;
		listview.setAdapter(zonelistadapter1);
	}

	protected void addItem(List list, String s, String s1, String s2, long l)
	{
		HashMap hashmap = new HashMap();
		hashmap.put("id", s);
		hashmap.put("en", s1);
		hashmap.put("name", s2);
		int i = TimeZone.getTimeZone(s).getOffset(l);
		int j = Math.abs(i);
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append("GMT");
		int k;
		int i1;
		String s3;
		Integer integer;
		String s4;
		if (i < 0)
			stringbuilder.append('-');
		else
			stringbuilder.append('+');
		k = j / 0x36ee80;
		stringbuilder.append(k);
		stringbuilder.append(':');
		i1 = (j / 60000) % 60;
		if (i1 < 10)
			stringbuilder.append('0');
		stringbuilder.append(i1);
		s3 = stringbuilder.toString();
		hashmap.put("gmt", s3);
		integer = Integer.valueOf(i);
		hashmap.put("offset", integer);
		s4 = TimeZone.getDefault().getID();
		if (s.equals(s4))
		{
			int j1 = list.size();
			mDefault = j1;
		}
		list.add(hashmap);
	}

	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		List list = getZones();
		timezoneSortedList = list;
		init();
		setResult(0);
	}






	private class 4 extends Handler
	{

		final ZoneList this$0;

		public void handleMessage(Message message)
		{
			if (message.obj instanceof HashMap)
			{
				HashMap hashmap = (HashMap)message.obj;
				Intent intent = new Intent();
				String s = (String)hashmap.get("id");
				intent.putExtra("time_zone", s);
				String s1 = (String)hashmap.get("name");
				intent.putExtra("area", s1);
				setResult(-1, intent);
				finish();
			}
		}

		4()
		{
			this$0 = ZoneList.this;
			super();
		}
	}


	private class 1
		implements android.view.View.OnClickListener
	{

		final ZoneList this$0;

		public void onClick(View view)
		{
			setResult(0);
			finish();
			overridePendingTransition(0x7f040004, 0x7f040001);
		}

		1()
		{
			this$0 = ZoneList.this;
			super();
		}
	}


	private class 2
		implements TextWatcher
	{

		final ZoneList this$0;

		public void afterTextChanged(Editable editable)
		{
		}

		public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
		{
		}

		public void onTextChanged(CharSequence charsequence, int i, int j, int k)
		{
			int l = 0;
			byte byte0 = 4;
			if (charsequence.toString().equals(""))
			{
				mClearButton.setVisibility(byte0);
				ZoneList zonelist = ZoneList.this;
				ArrayList arraylist = new ArrayList();
				zonelist.updateViews(arraylist);
				mNoResult.setVisibility(byte0);
			} else
			{
				mClearButton.setVisibility(l);
				List list = searchItem(charsequence);
				if (list.size() > 0)
					mNoResult.setVisibility(byte0);
				else
					mNoResult.setVisibility(l);
				updateViews(list);
			}
		}

		2()
		{
			this$0 = ZoneList.this;
			super();
		}
	}


	private class 3
		implements android.view.View.OnClickListener
	{

		final ZoneList this$0;

		public void onClick(View view)
		{
			mSearchEditText.setText("");
		}

		3()
		{
			this$0 = ZoneList.this;
			super();
		}
	}

}
