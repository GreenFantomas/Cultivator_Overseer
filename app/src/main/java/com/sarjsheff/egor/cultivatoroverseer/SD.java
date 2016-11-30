package com.sarjsheff.egor.cultivatoroverseer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Egor on 24.08.16.
 * Preferences class
 */

//TODO need to work
public class SD extends Activity{

    private SharedPreferences pref;

    public SD(Context context){
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean getHelp(){
        return pref.getBoolean("help", false);
    }

    public String getName(){
        return pref.getString("name", "Юзер");
    }

    public String getFormat(){
        return pref.getString("format", "Минуты");
    }
}