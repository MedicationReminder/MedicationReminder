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

@ContentView(R.layout.activity_medication_introduction_web)
public class MedicationIntroductionWebActivity extends BaseActivity {
    @ViewInject(R.id.medicationIntroductionWebView)
    private WebView medicationIntroductionWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.medicationIntroduction);
        initData();
        medicationIntroductionWebView.loadUrl("http://www.yiliaode.com/strategy_categories/576b48c2391af0550c6798f8/sub_strategy_categories");
        medicationIntroductionWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


        });
        medicationIntroductionWebView.setWebChromeClient(new WebChromeClient() {

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
        WebSettings settings = medicationIntroductionWebView.getSettings();
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
        if (keyCode == KeyEvent.KEYCODE_BACK && medicationIntroductionWebView.canGoBack()) {
            medicationIntroductionWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (medicationIntroductionWebView != null) {
            medicationIntroductionWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            medicationIntroductionWebView.clearHistory();
            ((ViewGroup) medicationIntroductionWebView.getParent()).removeView(medicationIntroductionWebView);
            medicationIntroductionWebView.destroy();
            medicationIntroductionWebView = null;
        }
        super.onDestroy();
    }
}