package com.nabilgardon.forfaitmobiletracker;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.util.Log;

/**
 * Database Queries Service Class.
 * Details to be disclosed later
 * @author Nabil Gardon
 *
 */
public class DatabaseService extends IntentService {

	ContentResolver mContentResolver;
	
	public DatabaseService() {
		super("database_service");
		//mContext = getApplicationContext();
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		//if (arg0.getStringExtra("order").equals("check_last_call"))
		//{
		mContentResolver = getContentResolver();
			Log.w("FMT", "Database service created");
			readLastCall();
		//}
	}

	private synchronized void readLastCall()
	{
		Uri uri = android.provider.CallLog.Calls.CONTENT_URI;
		String durationLabel = CallLog.Calls.DURATION,
				numberLabel = CallLog.Calls.NUMBER,
				typeLabel = Calls.TYPE,
				dateLabel = Calls.DATE;
		String[] columnsList = {typeLabel, numberLabel, durationLabel, dateLabel};
		Cursor cursor = mContentResolver.query(
				uri, 
				columnsList, 
				null, 
				null, 
				dateLabel+" DESC LIMIT 2");
		if (cursor == null) {
		    // query failed, handle error.
			Log.e("FMT", "Query failed");
		} else {
			int typeColumnNum = cursor.getColumnIndex(typeLabel);
			int numberColumnNum = cursor.getColumnIndex(numberLabel);
			int durationColumnNum = cursor.getColumnIndex(durationLabel);
			int dateColumnNum = cursor.getColumnIndex(dateLabel);
			int type, duration;
			long date;
			
			cursor.moveToFirst();
			String number = "";
			do
			{
				type = cursor.getInt(typeColumnNum);
				number = cursor.getString(numberColumnNum);
				duration = cursor.getInt(durationColumnNum);
				date = cursor.getLong(dateColumnNum);
				
				Log.d("FMT", "Appel terminé. Numéro : " +number+ " of duration : " +duration +" Date = " +date);
				
				int index = 3;
				
				if (number.charAt(0) != '+')
					index -= 2;
				switch (number.charAt(index))
				{
				case '6': Log.i("FMT", "06 (portable) enregistrée"); break;
				case '7': Log.i("FMT", "07 (portable) enregistrée"); break;
				case '9': Log.i("FMT", "09 (fixe/box) enregistrée"); break;
				}
			} while (cursor.moveToNext());
			cursor.close();
		}
	}
}
