package com.renqi.takemedicine.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.renqi.takemedicine.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Xu Wei on 2017/7/4.
 * 共有方法工具类
 */

public class MedicationHelper {
    private static Logger log = LoggerFactory.getLogger(MedicationHelper.class);
    public static MedicationHelper mh = new MedicationHelper();
    public static final Gson gson = new Gson(); // gson是线程安全的，可以使用单例避免重复创建
    public static String getTime() {
        Calendar cal = Calendar.getInstance();

        int dom = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);
        return "    "+dom+"月"+day+"日";
    }

    //根据日期取得星期几
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }
    public static String idgui(String s,int num)throws Exception{
        int changdu = s.getBytes("UTF-8").length;
        if(changdu > num){
            s = s.substring(0, s.length() - 1);
            s = idgui(s,num);
        }
        return s;
    }
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }
    /**
     * 方法名：isNullOrEmpty
     * 功    能：判断字符框是否为空或者null
     *
     * @param ：String value
     * @return ：boolean
     */
    public static boolean isNullOrEmpty(String value) {
        if (value == null) { //正确的写法
            return true;
        } else if ("null".equals(value)) { //正确的写法
            return true;
        } else if ("".equals(value.trim())) { //正确的写法
            return true;
        } else {
            return false;
        }
    }
    //endregion
    /*        //隐藏软键盘点击某个动作（view：v 触发 让软盘缩回）*/
    public static void hideInputMethod(View view) {

        InputMethodManager imm = ( InputMethodManager ) view.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        imm.hideSoftInputFromWindow( view.getApplicationWindowToken( ) , 0 );
    }

}
