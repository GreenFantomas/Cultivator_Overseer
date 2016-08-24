package com.sarjsheff.egor.cultivatoroverseer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    SQLiteDatabase db;

    SDB sdb;

    Intent intent;

    Button record;
    Button add;
    Button sitings;

    TextView text;

    SD sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        record = (Button) findViewById(R.id.button_record);
        add = (Button) findViewById(R.id.button_add);
        sitings = (Button) findViewById(R.id.button_sitings);

        text = (TextView) findViewById(R.id.textView);

        record.setOnClickListener(this);
        add.setOnClickListener(this);
        sitings.setOnClickListener(this);

        sdb = new SDB(this, 2);

        db = sdb.getWritableDatabase();

        sd = new SD();

        text.setText(getLastRowTable(db, "allTime")[0]);
        //if(sd.getFormat() == "h"){
            //long r = Long.parseLong(getLastRowTable(db, "allTime")[0]);
          //  text.setText(String.valueOf(r/60));
        //}
    }

    public void onClick(View v){

        if(v.getId() == R.id.button_record){
            intent = new Intent(this, RecordActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.button_add){
            intent = new Intent(this, AddTimeActivity.class);
            startActivity(intent);
        }else{
            intent = new Intent(this, SitingsActivity.class);
            startActivity(intent);
        }
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

}