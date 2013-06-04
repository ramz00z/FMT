package com.nabilgardon.forfaitmobiletracker;

import android.telephony.PhoneStateListener;
import android.util.Log;

public class PhoneConnectionListener extends PhoneStateListener {

	public PhoneConnectionListener()
	{
		super();
	}
	
	@Override
	public void onDataConnectionStateChanged(int state) {
		// TODO Auto-generated method stub
		Log.v("FMT", "Connection Listener appelé. State : "+state);
		super.onDataConnectionStateChanged(state);
	}
}
