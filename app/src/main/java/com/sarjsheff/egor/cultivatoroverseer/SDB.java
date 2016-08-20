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

    public SDB(Context context){
        super(context, "OverseerDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table useTime(time TEXT, date DATE);");
        //db.execSQL("create table allTime(ID, time TEXT, date DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String[] getLastRowTable(SQLiteDatabase db, String tableName){

        String[] result = new String[2];

        int timeIndex;
        int dateIndex;

        Cursor c = db.query(tableName,null,null,null,null,null,null);

        timeIndex = c.getColumnIndex("time");
        dateIndex = c.getColumnIndex("date");

        c.moveToLast();
        result[0] = c.getString(timeIndex);
        result[1] = c.getString(dateIndex);

        return result;
    }

    public long insertInTable(SQLiteDatabase db, String tableName, String time, String data){
        ContentValues cv = new ContentValues();

        cv.put("time", time);
        cv.put("date", data);

        long result = db.insert(tableName, null, cv);

        return result;
    }
}
