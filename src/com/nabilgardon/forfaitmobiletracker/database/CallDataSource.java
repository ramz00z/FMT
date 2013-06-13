package com.nabilgardon.forfaitmobiletracker.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CallDataSource {

  // Database fields
  private SQLiteDatabase database;
  private FMTSQLiteHelper dbHelper;
  private String[] allColumns = { FMTSQLiteHelper.COLUMN_ID,
      FMTSQLiteHelper.COLUMN_CALL_LENGTH,
      FMTSQLiteHelper.COLUMN_CALL_NUM,
      FMTSQLiteHelper.COLUMN_CALLER_ID,
      FMTSQLiteHelper.COLUMN_DATE_CALL,
      FMTSQLiteHelper.COLUMN_FORFAIT_ID,
      };

  public CallDataSource(Context context) {
    dbHelper = new FMTSQLiteHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  /*
  public Comment createComment(String comment) {
    ContentValues values = new ContentValues();
    values.put(FMTSQLiteHelper.COLUMN_COMMENT, comment);
    long insertId = database.insert(FMTSQLiteHelper.TABLE_CALLS, null,
        values);
    Cursor cursor = database.query(FMTSQLiteHelper.TABLE_CALLS,
        allColumns, FMTSQLiteHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Comment newComment = cursorToComment(cursor);
    cursor.close();
    Log.w("MSH", "Comment added with id : " + insertId);
    return newComment;
  }
  */
  
  public Call createCall()
  {
	  ContentValues values = new ContentValues();
	  values.put(FMTSQLiteHelper.COLUMN_CALL_NUM, "+3361212121212");
	  values.put(FMTSQLiteHelper.COLUMN_CALL_LENGTH, "60");
	  
	  long insertId = database.insert(FMTSQLiteHelper.TABLE_CALLS, null,
		    values);
	  Cursor cursor = database.query(FMTSQLiteHelper.TABLE_CALLS,
		    allColumns, FMTSQLiteHelper.COLUMN_ID + " = " + insertId, null,
		    null, null, null);
	  cursor.moveToFirst();cursorToCall(cursor);
	  Call newCall= cursorToCall(cursor);
	  cursor.close();
	  Log.w("MSH", "Comment added with id : " + insertId);
	  return newCall;
  }

  public void deleteCall(Call call) {
    long id = call.getId();
    Log.i("MSH","Call deleted with id: " + id);
    database.delete(FMTSQLiteHelper.TABLE_CALLS, FMTSQLiteHelper.COLUMN_ID
        + " = " + id, null);
  }

  public List<Call> getAllCalls() {
    List<Call> calls = new ArrayList<Call>();

    Cursor cursor = database.query(FMTSQLiteHelper.TABLE_CALLS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Call call = cursorToCall(cursor);
      calls.add(call);
      cursor.moveToNext();
    }
    // Make sure to close the cursor
    cursor.close();
    return calls;
  }

  private Call cursorToCall(Cursor cursor) {
    Call call = new Call();
    call.setId(cursor.getLong(0));
    call.setCallLength(cursor.getLong(1));
    call.setCallNum(cursor.getString(2));
    call.setCallerId(cursor.getString(3));
    call.setDateCall(cursor.getLong(4));
    call.setForfaitID(cursor.getLong(5));
    return call;
  }
} 