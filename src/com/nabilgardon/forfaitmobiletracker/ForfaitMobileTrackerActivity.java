package com.nabilgardon.forfaitmobiletracker;

import java.util.List;

import com.nabilgardon.forfaitmobiletracker.database.*;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.ArrayAdapter;

public class ForfaitMobileTrackerActivity extends ListActivity 
{
	private CallDataSource datasource;
	private NotificationCompat.Builder mNotificationBuilder;
	private int mId;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_database);

    datasource = new CallDataSource(this);
    datasource.open();

    List<Call> values = datasource.getAllCalls();

    // Use the SimpleCursorAdapter to show the
    // elements in a ListView
    ArrayAdapter<Call> adapter = new ArrayAdapter<Call>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
    initStatusBarNotification();
  }
  
  private void initStatusBarNotification()
	{
		mNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_stat_notify_running)
		        .setContentTitle("ForfaitMobileTracker")
		        .setContentText("Hello World!");
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, ForfaitMobileTrackerPreferenceActivity.class);

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(ForfaitMobileTrackerPreferenceActivity.class);
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

  // Will be called via the onClick attribute
  // of the buttons in main.xml
  public void onClick(View view) {
    @SuppressWarnings("unchecked")
    ArrayAdapter<Call> adapter = (ArrayAdapter<Call>) getListAdapter();
    Call call= null;
    switch (view.getId()) {
    case R.id.add:
      // Save the new call to the database
      call = datasource.createCall();
      adapter.add(call);
      break;
    case R.id.delete:
      if (getListAdapter().getCount() > 0) {
    	  call = (Call) getListAdapter().getItem(0);
        datasource.deleteCall(call);
        adapter.remove(call);
      }
      break;
    }
    adapter.notifyDataSetChanged();
  }

  @Override
  protected void onResume() {
    datasource.open();
    super.onResume();
  }

  @Override
  protected void onPause() {
    datasource.close();
    super.onPause();
  }

} 
