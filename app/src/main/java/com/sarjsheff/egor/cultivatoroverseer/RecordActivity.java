package com.sarjsheff.egor.cultivatoroverseer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Created by Egor
 * Activity to record time of motor
*/

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

        sdb = new SDB(this, 2);
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
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://egor.sarjsheff.ru"));
            startActivity(intent);
        }else{
            date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            db = sdb.getWritableDatabase();
            

ContentValues cv = new ContentValues();
          cv.put("time", String.valueOf(time));
          cv.put("date", format.format(date));

          sdb.insertInTable(db, "useTime", cv);
            /*String[] lastTime = sdb.getLastRowTable(db, "allTime");
            long resultTime = Long.parseLong(lastTime[0])+time;
            sdb.insertInTable(db, "allTime",  String.valueOf(resultTime), format.format(date));*/
        }
    }

}