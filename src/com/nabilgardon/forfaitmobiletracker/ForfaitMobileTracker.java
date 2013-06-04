package com.nabilgardon.forfaitmobiletracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.nabilgardon.forfaitmobiletracker.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ForfaitMobileTracker extends PreferenceActivity
	implements SharedPreferences.OnSharedPreferenceChangeListener{
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;
	
	private static String KEY_PREF_SEUIL = "pref_seuil";
	private NotificationCompat.Builder mNotificationBuilder;
	private TelephonyManager mTelephonyManager;
	private PhoneConnectionListener listener;
	private int mId;
	
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

		//setContentView(R.layout.activity_forfait_mobile_tracker);
	    addPreferencesFromResource(R.xml.preferences);
	    initStatusBarNotification();
	    //findViewById(android.R.id.list);
	    //IntentService databaseService = new DatabaseService();
	    Intent serviceIntent = new Intent(this, DatabaseService.class);
	    startService(serviceIntent);
	    mTelephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//	    listener = new PhoneConnectionListener();
//	    mTelephonyManager.listen(listener, PhoneStateListener.LISTEN_DATA_ACTIVITY
//	    		| PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
//	    		| PhoneStateListener.LISTEN_SERVICE_STATE);
	    Log.i("FMT", "service block executed");
	}
	
	private void initStatusBarNotification()
	{
		mNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_stat_notify_running)
		        .setContentTitle("ForfaitMobileTracker")
		        .setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ForfaitMobileTracker.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ForfaitMobileTracker.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
		        stackBuilder.getPendingIntent(
		            0,
PendingIntent.FLAG_UPDATE_CURRENT
		        );
		mNotificationBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
		    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		Notification mNotification = mNotificationBuilder.build();
		mNotification.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;
		mNotificationManager.notify(mId, mNotification);
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