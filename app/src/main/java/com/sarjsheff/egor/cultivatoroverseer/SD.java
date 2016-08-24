package com.sarjsheff.egor.cultivatoroverseer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Egor on 24.08.16.
 */
public class SD extends Activity{

    private SharedPreferences pref;

    private boolean help = false;
    private String format = "h";

    public boolean getHelp(){
        pref = getPreferences(MODE_PRIVATE);
        return pref.getBoolean("help", false);
    }

    public String getFormat(){
        pref = getPreferences(MODE_PRIVATE);
        return pref.getString("format", "h");
    }

    public void setHelp(boolean help){
        this.help = help;

        pref = getPreferences(MODE_PRIVATE);
        Editor ed = pref.edit();
        ed.putBoolean("help", help);
        ed.commit();
    }

    public void setFormat(String format){
        this.format = format;

        pref = getPreferences(MODE_PRIVATE);
        Editor ed = pref.edit();
        ed.putString("format", format);
        ed.commit();
    }
}
