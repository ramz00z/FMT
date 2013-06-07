package com.nabilgardon.forfaitmobiletracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class FMTSQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_CALLS = "calls";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_CALL_LENGTH = "call_length";
  public static final String COLUMN_DATE_CALL = "date_call";
  public static final String COLUMN_CALL_NUM = "call_num";
  public static final String COLUMN_CALLER_ID = "caller_id";
  public static final String COLUMN_FORFAIT_ID = "forfait_id";

  private static final String DATABASE_NAME = "calls.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_CALLS + "(" 
		  + COLUMN_ID
		  + " integer primary key autoincrement, "
		  + COLUMN_CALL_LENGTH
		  + " text not null, "
		  + COLUMN_DATE_CALL
		  + " integer, "
		  + COLUMN_CALL_NUM
		  + " text not null, "
		  + COLUMN_CALLER_ID
		  + " text, "
		  + COLUMN_FORFAIT_ID
		  + " integer "
		  + ");";

  public FMTSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
    Log.e("MSH", "Database created");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w("MSH",
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALLS);
    onCreate(db);
  }

} 
