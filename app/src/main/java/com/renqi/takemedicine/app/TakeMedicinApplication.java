package com.renqi.takemedicine.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static String wlan_mac;

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
        //{"device_token":{"device_token":"009"}}
//
//
//        Map<String,String> map=new ArrayMap<>();
//        map.
//        params.addBodyParameter("device_token", "{\"device_token\":\"123456\"}");

     //   XUtilHttpRequest.getInstance().post("",
      //  Log.e("m_szDevIDShort", getLocalMacAddress());
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
