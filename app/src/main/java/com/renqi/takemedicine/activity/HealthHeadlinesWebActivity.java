package com.renqi.takemedicine.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

@ContentView(R.layout.activity_health_headlines_web)
public class HealthHeadlinesWebActivity extends BaseActivity {
    @ViewInject(R.id.healthHeadlinesWebView)
    private WebView healthHeadlinesWebView;
    @ViewInject(R.id.pb_progress)
    private ProgressBar pbProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.healthHeadlines);
        initData();
        healthHeadlinesWebView.loadUrl("http://www.jiankanghj.com");
        healthHeadlinesWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }


        });
        healthHeadlinesWebView.setWebChromeClient(new WebChromeClient() {
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
        WebSettings settings = healthHeadlinesWebView.getSettings();
        settings.setJavaScriptEnabled(true);//开启webview支持JS
        settings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        //启用地理定位
        settings.setGeolocationEnabled(true);
        //设置定位的数据库路径
        settings.setGeolocationDatabasePath(dir);
        settings.setDomStorageEnabled(true);

        if (isNetworkAvailable(getApplicationContext())) {
            //有网络连接，设置默认缓存模式
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //无网络连接，设置本地缓存模式
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
    public  boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && healthHeadlinesWebView.canGoBack()) {
            healthHeadlinesWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (healthHeadlinesWebView != null) {
            healthHeadlinesWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            healthHeadlinesWebView.clearHistory();
            ((ViewGroup) healthHeadlinesWebView.getParent()).removeView(healthHeadlinesWebView);
            healthHeadlinesWebView.destroy();
            healthHeadlinesWebView = null;
        }
        super.onDestroy();
    }
}