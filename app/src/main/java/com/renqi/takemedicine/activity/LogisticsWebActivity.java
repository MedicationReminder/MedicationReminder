package com.renqi.takemedicine.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static android.webkit.WebSettings.LOAD_NO_CACHE;

@ContentView(R.layout.activity_logistics_web)
public class LogisticsWebActivity extends BaseActivity {
    @ViewInject(R.id.logisticsWebView)
    private WebView logisticsWebView;

    @ViewInject(R.id.pb_progress)
    private ProgressBar pbProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.deliveryLogistics);
        initData();
        logisticsWebView.loadUrl("https://m.kuaidi100.com/");
        logisticsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


        });
        logisticsWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if(newProgress==100){
                    pbProgress.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    pbProgress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pbProgress.setProgress(newProgress);//设置进度值
                }

            }
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
        WebSettings settings = logisticsWebView.getSettings();
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
        if (keyCode == KeyEvent.KEYCODE_BACK && logisticsWebView.canGoBack()) {
            logisticsWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (logisticsWebView != null) {
            logisticsWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            logisticsWebView.clearHistory();
            ((ViewGroup) logisticsWebView.getParent()).removeView(logisticsWebView);
            logisticsWebView.destroy();
            logisticsWebView = null;
        }
        super.onDestroy();
    }
}