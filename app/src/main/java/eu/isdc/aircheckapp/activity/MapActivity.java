package eu.isdc.aircheckapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import eu.isdc.aircheckapp.R;

/**
 * Created by ancestor on 4/23/16.
 */
public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        WebView webview = (WebView) findViewById(R.id.webview);
        if (webview != null) {
            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("http://ec2-54-229-86-36.eu-west-1.compute.amazonaws.com/pollution_map.html");
        }
    }
}
