package com.renqi.takemedicine.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.imagepicker.view.SystemBarTintManager;
import com.renqi.takemedicine.R;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Xu Wei on 2017/7/4.
 */

public class BaseActivity extends AppCompatActivity {
    @ViewInject(R.id.toolbar_title) private TextView toolbar_title;
    @ViewInject(R.id.home) private TextView home;
    @ViewInject(R.id.iption)private TextView iption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        initSystemBarTint();
    }
    /**子类修改Toolbartitle**/
    protected void  setToolBarTitle(String tiele)
    {
        toolbar_title.setText(tiele);
    }
    protected void  setIption(String iption1){iption.setText(iption1);}
    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }
    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }
    /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }
    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog dialog;

    public void showLoading() {
        if (dialog != null && dialog.isShowing()) return;
        dialog = new ProgressDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        dialog.show();
    }

    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Event(R.id.home)
    private void home(View view)
    {
        finish();
    }

}
