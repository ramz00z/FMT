package com.nabilgardon.forfaitmobiletracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.nabilgardon.forfaitmobiletracker.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ForfaitMobileTrackerPreferenceActivity extends PreferenceActivity
	implements SharedPreferences.OnSharedPreferenceChangeListener{
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
	    super.onResume();
	    //getPreferenceScreen().getSharedPreferences()
//	            .registerOnSharedPreferenceChangeListener(this);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
	    Intent serviceIntent = new Intent(this, DatabaseService.class);
	    startService(serviceIntent);

	    Log.i("FMT", "service block executed");
	}
	
	@SuppressWarnings("deprecation")
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if (key.equals(KEY_PREF_SEUIL)) {
//            Preference connectionPref = findPreference(key);
//            // Set summary to be the user-description for the selected value
//            connectionPref.setSummary(sharedPreferences.getString(key, ""));
//            Log.i("FMT", "la pref seuil a été changée en : "+sharedPreferences.getString(key, ""));
//        }
    }
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
	    super.onPause();
	    //getPreferenceScreen().getSharedPreferences()
	      //      .unregisterOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}