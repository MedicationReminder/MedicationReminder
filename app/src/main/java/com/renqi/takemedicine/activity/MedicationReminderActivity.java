package com.renqi.takemedicine.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.blankj.utilcode.util.ToastUtils;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.Add_App_contact;
import com.renqi.takemedicine.base.EventbusActivity;
import com.renqi.takemedicine.bean.CardBean;
import com.renqi.takemedicine.event.BaseEvents;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.utils.WeiboDialogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContentView(R.layout.activity_medication_reminder)
public class MedicationReminderActivity extends EventbusActivity {
    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private ArrayList<CardBean> cardItem2 = new ArrayList<>();
    private ArrayList<CardBean> cardItem3 = new ArrayList<>();
    private ArrayList<CardBean> cardItem4 = new ArrayList<>();
    private ArrayList<CardBean> cardItem5 = new ArrayList<>();
    Dialog medicationUpload;
    @ViewInject(R.id.iption)
    private TextView iption;
    int a=1;
    int reminderModeTyep;
    @ViewInject(R.id.medName)
    private EditText medName;

    @ViewInject(R.id.reminderMode)
    private TextView reminderMode;

    @ViewInject(R.id.editText3)
    private TextView editText3;
    @ViewInject(R.id.editText4)
    private  TextView editText4;

    @ViewInject(R.id.editText41)
    private  TextView editText41;

    @ViewInject(R.id. textView4)
    private TextView  textView4;

    @ViewInject(R.id.selectTime)
    private TextView selectTime;

    @ViewInject(R.id.Reminder)
    private TextView Reminder;

    @ViewInject(R.id.offorno)
    private ImageView offorno;

    @ViewInject(R.id.Remarks)
    private TextView Remarks;

    private String contactID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.medicationReminder);
      //  setContentView(R.layout.activity_medication_reminder);
         setIption(AppConstants.iption.complete);
         initCustomOptionPicker();
         timeInterval();
         getke();
         times();
         Reminder();
         rReminderType();
    }
    @Event(R.id.Remarks)
    private void  Remarks(View view)
    {
        startActivity(new Intent(MedicationReminderActivity.this,RemarksActivity.class));
    }
    @Event(R.id.selectTime)
    private void selectTime(View view){

        MedicationHelper.hideInputMethod(view);
        showDatePickDialog(DateType.TYPE_YMDHMS);

    }
    private void showDatePickDialog(DateType type) {
        DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(30);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm:ss");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String str=sdf.format(date);
                selectTime.setText(str);

            }
        });
        dialog.show();
    }
    private void initCustomOptionPicker() {
        //条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if(cardItem.get(0).isSetPicker()){
                    String tx = cardItem.get(options1).getPickerViewText();

                    editText3.setText(tx);
                    cardItem.get(0).setSetPicker(false);
                    return;
                }
                if(cardItem2.get(0).isSetPicker())
                {

                String num=cardItem2.get(options1).getPickerViewText();
                editText41.setText(num);
                    cardItem2.get(0).setSetPicker(false);
                    return;
                }
                if(cardItem3.get(0).isSetPicker())
                {
                    String num=cardItem3.get(options1).getPickerViewText();

                    editText4.setText(num);
                    cardItem3.get(0).setSetPicker(false);
                    return;
                }
                if(cardItem4.get(0).isSetPicker())
                {
                    cardItem4.get(0).setSetPicker(false);
                    if(cardItem4.get(options1).getId()==0)
                    {

                        startActivityForResult(new Intent(MedicationReminderActivity.this,ContactActivity.class),1);

                        return;
                    }
                    String num=cardItem4.get(options1).getPickerViewText();

                    Reminder.setText(num);
                    cardItem4.get(0).setSetPicker(false);
                    return;
                }
                if(cardItem5.get(0).isSetPicker())
                {
                    String num=cardItem5.get(options1).getPickerViewText();
                    reminderModeTyep=cardItem5.get(options1).getId();
                    reminderMode.setText(num);
                    cardItem5.get(0).setSetPicker(false);
                    return;
                }

            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView pickerTitle= (TextView) v.findViewById(R.id.pickerTitle);
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        //   final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        pickerTitle.setText("请选择");
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                    }})
                .isDialog(false)
                .build();



    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (data != null) {
                    Reminder.setText(data.getStringExtra("1"));
                    contactID = data.getStringExtra("2");
                }
                break;
            case 10:
                medName.setText(data.getStringExtra("medName"));
                break;
        }
    }

    private void getke() {

        cardItem.add(new CardBean(0, "颗"));
        cardItem.add(new CardBean(1, "片"));
        cardItem.add(new CardBean(2, "丸"));
        cardItem.add(new CardBean(3, "贴"));
        cardItem.add(new CardBean(4, "支"));

        cardItem.add(new CardBean(5, "袋"));
        cardItem.add(new CardBean(6, "Mg"));
        cardItem.add(new CardBean(7, "Ml"));
        cardItem.add(new CardBean(8, "g"));

    }
    private void timeInterval(){

        for (int i = 1; i <31 ; i++) {
            cardItem2.add(new CardBean(i, i+"天" ));
        }
    }
    private void times(){
        cardItem3.add(new CardBean(0,"每日一次"));
        cardItem3.add(new CardBean(1,"每日二次"));
        cardItem3.add(new CardBean(2,"每日三次"));
    }
    private void Reminder(){
        cardItem4.add(new CardBean(0,"联系人页面选择"));
        cardItem4.add(new CardBean(1,"本人"));
    }
    private void rReminderType(){
        cardItem5.add(new CardBean(0,"铃声"));
        cardItem5.add(new CardBean(1,"短信"));
        cardItem5.add(new CardBean(2,"电话"));
        cardItem5.add(new CardBean(3,"震动"));
    }
    @Event(R.id.editText3)
    private void editText3(View view)
    {
        if (pvCustomOptions != null) {
        pvCustomOptions.setPicker(cardItem);//添加数据
        cardItem.get(0).setSetPicker(true);
        pvCustomOptions.show();
        MedicationHelper.hideInputMethod(view);

    }else {
        ToastUtils.showShortToast("选择框尚未初始化");
    }

    }
    @Event(R.id.editText5)
    private void editText5(View view)
    {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem);//添加数据
            cardItem.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }

    }
    @Event(R.id.editText41)
    private void editText41(View view)
    {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem2);//添加数据
            cardItem2.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }
    @Event(R.id.editText4)
    private void editText4(View v)
    {

        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem3);//添加数据
            cardItem3.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(v);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }
    @Event(R.id.Reminder)
    private void Reminder(View v)
    {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem4);//添加数据
            cardItem4.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(v);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }
    @Event(R.id.reduce)
    private void reduce(View v)
    {
       int num=Integer.parseInt(textView4.getText().toString().trim());
       if(num==1)
           return;
        else if(num>1)
           textView4.setText(( num-1)+"");
    }
    @Event(R.id.add)
    private void add(View view)
    {
        int num=Integer.parseInt(textView4.getText().toString().trim());
        textView4.setText((num+1)+"");
    }
    @Event(R.id.offorno)
    private void offorno(View v)
    {
        if(a==1) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slider_close, new BitmapFactory.Options());
            offorno.setImageBitmap(bitmap);
            a=0;
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slider_open, new BitmapFactory.Options());
            offorno.setImageBitmap(bitmap);
            a=1;
        }

    }
    @Event(R.id.reminderMode)
    private void reminderMode(View v)
    {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem5);//添加数据
            cardItem5.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(v);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }
    private String getApp_contactJsonParam(){
        List<Add_App_contact> addApp_contactList =new ArrayList<>();
        addApp_contactList.add(
                new Add_App_contact(
                        new Add_App_contact.app_drugremind(TakeMedicinApplication.testMacAdress,
                                 medName.getText().toString().trim(),
                                textView4.getText().toString().trim()+editText3.getText().toString().trim(),
       reminderModeTyep,editText4.getText().toString(),selectTime.getText().toString().trim(),editText41.getText().toString().trim(),contactID))
        );

        try {
            return new JSONArray(MedicationHelper
                    .gson.toJson(addApp_contactList)).get(0).toString().trim();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
    @Event(R.id.iption)
    private void ipition(View view)
    {

        if(MedicationHelper.isNullOrEmpty(medName.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "药名").show();
            return;
        }

        if(MedicationHelper.isNullOrEmpty(editText4.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "服用次数").show();
            return;
        }
        if(MedicationHelper.isNullOrEmpty(selectTime.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "服药起始时间").show();
            return;
        }
        if(MedicationHelper.isNullOrEmpty(editText41.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "服药间隔时间").show();
            return;
        }
        if(MedicationHelper.isNullOrEmpty(Reminder.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "被提醒人").show();
            return;
        }
        if(MedicationHelper.isNullOrEmpty(reminderMode.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "提醒方式").show();
            return;
        }
        medicationUpload = WeiboDialogUtils.createLoadingDialog(MedicationReminderActivity.this, "上传用药信息中...");
        medicationUpload.show();
        RequestParams params=new RequestParams(AppConstants.BASE_ACTION+AppConstants.app_drugreminds);
        params.setAsJsonContent(true);
        params.setBodyContent(getApp_contactJsonParam());
        Log.e("json",getApp_contactJsonParam());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
             //   ToastUtils.showShortToast(result);
                Log.e("result",result);
               WeiboDialogUtils.closeDialog(medicationUpload);
                new ToastUtil(getApplicationContext(), R.layout.toast_complete, "添加提醒成功").show();
                finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ToastUtils.showShortToast(ex.getMessage().toString().trim());
                WeiboDialogUtils.closeDialog(medicationUpload);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvents.sendRemarks event) {
        // UI updates must run on MainThread

        if (event == BaseEvents.sendRemarks.SEND_REMARKS) {

            Remarks.setText(  event.getObject().toString().trim());
        }

    }
    @Event(R.id.medName)
    private void medName(View view)
    {
        Intent intent=new Intent(MedicationReminderActivity.this,AssociativeSearchActivity.class);
        startActivityForResult(intent,10);
    }








}
