package com.renqi.takemedicine.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

@ContentView(R.layout.activity_related_drugs_web)
public class RelatedDrugsWebActivity extends BaseActivity {
    @ViewInject(R.id.relatedDrugsWebView)
    private WebView relatedDrugsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.relatedDrugs);
        initData();
        relatedDrugsWebView.loadUrl("http://www.yiliaode.com/drug_names");
        relatedDrugsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


        });
        relatedDrugsWebView.setWebChromeClient(new WebChromeClient() {

            //配置权限（同样在WebChromeClient中实现）
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

        });

    }

    private void initData() {
        WebSettings settings = relatedDrugsWebView.getSettings();
        settings.setJavaScriptEnabled(true);//开启webview支持JS
        settings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        //启用地理定位
        settings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        settings.setGeolocationDatabasePath(dir);
        settings.setDomStorageEnabled(true);

        settings.setCacheMode(LOAD_NO_CACHE);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && relatedDrugsWebView.canGoBack()) {
            relatedDrugsWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (relatedDrugsWebView != null) {
            relatedDrugsWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            relatedDrugsWebView.clearHistory();
            ((ViewGroup) relatedDrugsWebView.getParent()).removeView(relatedDrugsWebView);
            relatedDrugsWebView.destroy();
            relatedDrugsWebView = null;
        }
        super.onDestroy();
    }
}