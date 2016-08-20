package com.sarjsheff.egor.cultivatoroverseer;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.data;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{

    Intent intent;

    SDB sdb;

    TextView help;

    ImageButton start;
    ImageButton stop;
    ImageButton save;

    Chronometer chronometer;

    private long time;

    private int minute = 60_000;

    Date date;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        help = (TextView) findViewById(R.id.recordHelp);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        start = (ImageButton) findViewById(R.id.start_record);
        stop = (ImageButton) findViewById(R.id.stop_record);
        save = (ImageButton) findViewById(R.id.save);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        save.setOnClickListener(this);

        help.setOnClickListener(this);

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer mchronometer) {
                time = (SystemClock.elapsedRealtime()-
                        chronometer.getBase())/minute;
            }
        });

        sdb = new SDB(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.start_record){
            chronometer.setBase(SystemClock.elapsedRealtime());
            start.setEnabled(false);
            chronometer.start();
        }else if(v.getId() == R.id.stop_record){
            start.setEnabled(true);
            chronometer.stop();
        }else if(v.getId() == R.id.recordHelp){
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
            startActivity(intent);
        }else{
            date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            db = sdb.getWritableDatabase();
            sdb.insertInTable(db, "useTime", String.valueOf(time), format.format(date));
        }
    }

}
