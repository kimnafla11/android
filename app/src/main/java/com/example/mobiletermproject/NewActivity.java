package com.example.mobiletermproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by webnautes on 2017-11-27.
 */

public class NewActivity extends AppCompatActivity {

    private WebView mWebView;
    private WebSettings mWebSettings;




    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        String title = "";
        String address = "";

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            title = "error";
        }
        else {

            title = extras.getString("title");
            address = extras.getString("address");
        }

        TextView textView = (TextView) findViewById(R.id.textView_newActivity_contentString);

        String str = title + '\n'+','+ address + '\n';
        textView.setText(str);

        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl("https://www.google.com/search?q="+title+" "+address);
    }
}
