package com.sarjsheff.egor.cultivatoroverseer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

public class BrowserActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar bar;
    WebView web;
    Uri uri;

    Button back;
    Button reload;
    Button forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        back = (Button) findViewById(R.id.back);
        reload = (Button) findViewById(R.id.reload);
        forward = (Button) findViewById(R.id.forward);

        back.setOnClickListener(this);
        reload.setOnClickListener(this);
        forward.setOnClickListener(this);

        web = (WebView) findViewById(R.id.Browser);
        uri = getIntent().getData();

        web.loadUrl(uri.toString());

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back){
            if (web.canGoBack())web.goBack();
        }else if(v.getId() == R.id.forward){
            if (web.canGoForward())web.goForward();
        }else{
            web.reload();
        }
    }

}
