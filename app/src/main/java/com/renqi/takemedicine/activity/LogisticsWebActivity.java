package com.renqi.takemedicine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_logistics_web)
public class LogisticsWebActivity extends BaseActivity {
    @ViewInject(R.id.logisticsWebView) private WebView logisticsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_logistics_web);
        setToolBarTitle(AppConstants.ToolBarTitle.deliveryLogistics);
        initData();
        logisticsWebView.loadUrl("https://m.kuaidi100.com/");
    }

    private void initData() {
        WebSettings settings = logisticsWebView.getSettings();
        settings.setJavaScriptEnabled(true);//开启webview支持JS

    }
}
