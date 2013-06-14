package com.nabilgardon.forfaitmobiletracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.database.*;

public class PhoneStateBroadcastReceiver extends BroadcastReceiver
{
	TelephonyManager mTelephonyManager;
	private boolean callStarted = false;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		String mExtraState = "";
		mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		if (action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED))
		{
			Log.d("FMT", "ACTION_PHONE_STATE_CHANGED passé");
			mExtraState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		}
			
		if ((action.equals(Intent.ACTION_NEW_OUTGOING_CALL) && !callStarted))
		{
			Log.d("FMT", "Début d'un nouvel appel sortant");
			callStarted = true;
			
		}
		
		if (!callStarted && mExtraState.equals(TelephonyManager.EXTRA_STATE_IDLE))
		{
			callStarted = false;
			getLastCallInfos(context);
		}

		//Log.i("FMT", "Call State : "+intent.getStringExtra(TelephonyManager.EXTRA_STATE));
		
		if (mExtraState.equals(TelephonyManager.EXTRA_STATE_IDLE))
			Log.i("FMT", "IDLE MA GUEULE");
		else if (mExtraState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
			Log.i("FMT", "OFFHOOK negook");
	}
	
	private void getLastCallInfos(Context context)
	{
	    Intent serviceIntent = new Intent(context, DatabaseService.class);
	    context.startService(serviceIntent);
	    context.startService(serviceIntent);
	}
}