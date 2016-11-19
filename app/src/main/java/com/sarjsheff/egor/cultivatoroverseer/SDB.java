package com.sarjsheff.egor.cultivatoroverseer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Egor on 15.08.16.
 */
public class SDB extends SQLiteOpenHelper {

    public SDB(Context context, int db_version){
        super(context, "OverseerDB", null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table useTime(time TEXT, date DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion == 1 && newVersion == 2){
            ContentValues cv = new ContentValues();

            cv.put("time", "0");
            cv.put("date", "1970-01-01");

            try {
                db.beginTransaction();

                db.execSQL("create table allTime(time TEXT, date DATE);");

                db.insert("allTime", null, cv);

                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }
        }

    }

    public String[] getLastRowTable(SQLiteDatabase db, String tableName){

        String[] result = new String[2];

        int timeIndex;
        int dateIndex;

        Cursor c;

        try {
            db.beginTransaction();
            c = db.query(tableName, null, null, null, null, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        timeIndex = c.getColumnIndex("time");
        dateIndex = c.getColumnIndex("date");

        c.moveToLast();
        result[0] = c.getString(timeIndex);
        result[1] = c.getString(dateIndex);

        return result;
    }

    public long insertInTable(SQLiteDatabase db, String tableName,ContentValues cv){
        
      //For test
      
      /*ContentValues cv = new ContentValues();

        cv.put("time", time);
        cv.put("date", data);*/

        long result = db.insert(tableName, null, cv);

        return result;
    }

    public int deleteTable(SQLiteDatabase db, String tableName){

        int result = db.delete(tableName, null, null);

        return result;
    }
}