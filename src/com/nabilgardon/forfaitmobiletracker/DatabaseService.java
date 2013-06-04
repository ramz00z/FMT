package com.nabilgardon.forfaitmobiletracker;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.util.Log;
import android.widget.Toast;

public class DatabaseService extends IntentService {

	ContentResolver mContentResolver;
	
	public DatabaseService() {
		super("database_service");
		//mContext = getApplicationContext();
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		//if (arg0.getStringExtra("order").equals("check_last_call"))
		//{
		mContentResolver = getContentResolver();
			Log.i("FMT", "Database service created");
			readLastCall();
		//}
	}

	private void readLastCall()
	{
		Uri uri = android.provider.CallLog.Calls.CONTENT_URI;
		String durationLabel = CallLog.Calls.DURATION,
				numberLabel = CallLog.Calls.NUMBER,
				typeLabel = Calls.TYPE;
		String[] columnsList = {typeLabel, numberLabel, durationLabel};
		Cursor cursor = mContentResolver.query(
				uri, 
				columnsList, 
				null, 
				null, 
				null);
		if (cursor == null) {
		    // query failed, handle error.
			Log.e("FMT", "Query failed");
		} else {
			int typeColumnNum = cursor.getColumnIndex(typeLabel);
			int numberColumnNum = cursor.getColumnIndex(durationLabel);
			int durationColumnNum = cursor.getColumnIndex(durationLabel);
			int type, duration;
			String number = "";
			do
			{
				Log.i("FMT", ""+cursor.getColumnName(0));
				Log.i("FMT", ""+cursor.getColumnName(1));
				Log.i("FMT", ""+cursor.getColumnName(2));

				//type = cursor.getInt(typeColumnNum);
				number = cursor.getString(numberColumnNum);
				duration = cursor.getInt(durationColumnNum);
				
				Log.d("FMT", "Appel terminé. Numéro : " +number+ " of duration : " +duration);		

			} while (cursor.moveToNext());
			
		}
	}
}
