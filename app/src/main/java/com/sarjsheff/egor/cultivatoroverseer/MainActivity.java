package com.sarjsheff.egor.cultivatoroverseer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.sarjsheff.egor.cultivatoroverseer.sitings.SitingsActivity;

/*
*Created by Egor
* Main Activity
*/

//TODO NEED TO TEST!!!!
public class MainActivity extends AppCompatActivity implements OnClickListener {

    SQLiteDatabase db;

    SDB sdb;

    Intent intent;

    Button record;
    Button add;
    Button sitings;

    SD sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        record = (Button) findViewById(R.id.button_record);
        add = (Button) findViewById(R.id.button_add);
        sitings = (Button) findViewById(R.id.button_sitings);

        record.setOnClickListener(this);
        add.setOnClickListener(this);
        sitings.setOnClickListener(this);

        try{
      	//connect to DB class
      	sdb = new SDB(this, 2);

        //get DB in write mode(?)
      	db = sdb.getWritableDatabase();

        sd = new SD(this);
        }catch(Exception e){
        	Log.e(TAG, "Error to connect to DB: " + e.getMessage());
        }
        
    }

    public void onClick(View v){

        if(v.getId() == R.id.button_record){
            //start torecord time
          	intent = new Intent(this, RecordActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.button_add){
            //go to AddTimeActivity
          	intent = new Intent(this, AddTimeActivity.class);
            startActivity(intent);
        }else{
            //go to sitings
          	intent = new Intent(this, SitingsActivity.class);
            startActivity(intent);
        }
    }

/*
* Method to read last row in db
*/
  
  	public String[] getLastRowTable(SQLiteDatabase db, String tableName){

      	String[] result = new String[2];

        int timeIndex;
        int dateIndex;

        //get cursor from DB
      	Cursor c = db.query(tableName,null,null,null,null,null,null);

        //get data
      	timeIndex = c.getColumnIndex("time");
        dateIndex = c.getColumnIndex("date");

        //got to end
      	c.moveToLast();
        result[0] = c.getString(timeIndex);
        result[1] = c.getString(dateIndex);

        return result;
    }

}