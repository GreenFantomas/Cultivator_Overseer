package com.sarjsheff.egor.cultivatoroverseer;

import android.content.Context;
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
}
