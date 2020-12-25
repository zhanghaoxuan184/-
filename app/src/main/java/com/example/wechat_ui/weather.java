package com.example.wechat_ui;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class weather extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);

        Intent i = getIntent();
        //获取按钮传递数值
        String getData = i.getStringExtra("data");

        webView = (WebView)findViewById(R.id.webView);//获取web组件
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置JavaScript可用
        webView.setWebChromeClient(new WebChromeClient());//处理JavaScript对话框
        //处理各种通知和请求时间，如果不使用该句代码，将使用内置浏览器访问网页
        webView.setWebViewClient(new WebViewClient());
//        设置默认显示天气预报信息
        webView.loadUrl("http://m.weather.com.cn/mweather/101010100.shtml");
        openUrl(getData);
    }
    private void openUrl(String id){
        webView.loadUrl("http://m.weather.com.cn/mweather/" + id + ".shtml");
    }
}
