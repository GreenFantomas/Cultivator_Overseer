package com.sarjsheff.egor.cultivatoroverseer.sitings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.sarjsheff.egor.cultivatoroverseer.R;

public class SitingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.sitings);

    }
}
