package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity9 extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        WebView webView = (WebView)findViewById(R.id.webview);
        String url = "http://10.0.2.2:5500/ESM/login.html";
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebViewClient(new WebViewClient());
        //此方法可以启用html5页面的javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}