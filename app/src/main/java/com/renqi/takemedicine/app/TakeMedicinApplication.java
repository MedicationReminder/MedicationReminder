package com.renqi.takemedicine.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.renqi.takemedicine.bean.response.LoginResponseBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lzp on 2017/5/4 0004.
 */

public class TakeMedicinApplication extends Application {
    private Logger log = LoggerFactory.getLogger(TakeMedicinApplication.class);
    private static Context context;
    //保存们每一个activity的list
    private List<Activity> activityList = new LinkedList<>();

    private static TakeMedicinApplication instance;
    public static String macAdress,testMacAdress="426426426";

    private SharedPreferences sp,sp2;

    public static String wlan_mac;

    public static int isFirstTiem=0,isFirstWater=0,isFisterFood=0,isFirstSpecial=0;

    //单例获取app对象
    public synchronized static TakeMedicinApplication getInstance() {
        return instance;
    }

    //获取Application对应的Context实例
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        context = getApplicationContext();

        Utils.init(context);

        SharedPreferences sp = getSharedPreferences("login_state", Context.MODE_PRIVATE);

        boolean name = sp.getBoolean("state",true);

        if(name) {
            //{"status":200,"device_token":"qqwwrwet009"}
            RequestParams params = new RequestParams(AppConstants.BASE_ACTION+AppConstants.DEVICE_TOKENS);
            params.setAsJsonContent(true);
            params.setBodyContent("{\"device_token\":{\"device_token\":" +426426426 + "}}");
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    //解析result
                    Log.e("m_szDevIDShort", result);
                    LoginResponseBean loginResponseBean = new Gson().fromJson(result, LoginResponseBean.class);
                    if (loginResponseBean.getStatus() == 200) {
                        SharedPreferences sp = getSharedPreferences("login_state", Context.MODE_PRIVATE);
                        sp.edit().putBoolean("state", false).commit();
                    } else {
                        SharedPreferences sp = getSharedPreferences("login_state", Context.MODE_PRIVATE);
                        sp.edit().putBoolean("state", false).commit();
                    }


                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("m_szDevIDShort", ex.toString());
                  //  ToastUtils.showShortToast("后台接口异常！");
                  //  sp.edit().putBoolean("state", true).commit();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                    Log.e("m_szDevIDShort", "onFinished");
                }
            });
        }
        //  Log.e("m_szDevIDShort", getLocalMacAddress());
        sp2 = getSharedPreferences("is_mac", Context.MODE_PRIVATE);
        if(sp2.getBoolean("state",false))
        {
            macAdress=sp2.getString("mac","");
        }else  getLocalMacAddress();
    }

    public String getLocalMacAddress() {
        String mac = null;
        try {
            String path = "sys/class/net/eth0/address";
            FileInputStream fis_name = new FileInputStream(path);
            byte[] buffer_name = new byte[8192];
            int byteCount_name = fis_name.read(buffer_name);
            if (byteCount_name > 0) {
                mac = new String(buffer_name, 0, byteCount_name, "utf-8");
            }


            if (mac == null) {
                fis_name.close();
                return "";
            }
            fis_name.close();
        } catch (Exception io) {
            String path = "sys/class/net/wlan0/address";
            FileInputStream fis_name;
            try {
                fis_name = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis_name.read(buffer_name);
                if (byteCount_name > 0) {
                    mac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
                fis_name.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        if (mac == null) {
            return "";
        } else {
           // sp2 = getSharedPreferences("is_mac", Context.MODE_PRIVATE);
            sp2.edit().putBoolean("state", true).commit();
            sp2.edit().putString("mac", mac.trim()).commit();
            macAdress=mac.trim();
            return mac.trim();
        }

    }


    //添加Activity到list里
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    //杀进程
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}