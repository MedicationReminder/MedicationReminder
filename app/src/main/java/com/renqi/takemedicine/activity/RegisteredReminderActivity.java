package com.renqi.takemedicine.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.blankj.utilcode.util.ToastUtils;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.CardBean;
import com.renqi.takemedicine.bean.request.RegisterReminderRequestBean;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.utils.WeiboDialogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.renqi.takemedicine.R.id.registerd_reminder_reminder;

@ContentView(R.layout.activity_registered_reminder)
public class RegisteredReminderActivity extends BaseActivity {
    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem4 = new ArrayList<>();
    private ArrayList<CardBean> cardItem5 = new ArrayList<>();
    private ArrayList<CardBean> cardItem6 = new ArrayList<>();
    Dialog medicationUpload;
    int reminderModeTyep;
    int a = 1;
    String contactID = "";
    @ViewInject(registerd_reminder_reminder)
    private TextView reminder;

    @ViewInject(R.id.reg_hospital_name)
    private TextView reg_hospital_name;


    @ViewInject(R.id.reg_department_name)
    private TextView reg_department_name;

    @ViewInject(R.id.reg_reister_time)
    private TextView reg_reister_time;

    @ViewInject(R.id.reg_doctor_name)
    private TextView reg_doctor_name;

    @ViewInject(R.id.reg_type)
    private TextView reg_type;


    @ViewInject(R.id.reg_remarks)
    private TextView reg_remarks;

    @ViewInject(R.id.reg_offorno)
    private ImageView reg_offorno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setToolBarTitle(AppConstants.ToolBarTitle.registrationReminder);
        setIption(AppConstants.iption.complete);
        initCustomOptionPicker();
        Reminder();
        rReminderType();
        rDepartmentName();
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String str = sdf.format(date);
                reg_reister_time.setText(str);

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

                if (cardItem4.get(0).isSetPicker()) {
                    if (cardItem4.get(options1).getId() == 0) {

                        startActivityForResult(new Intent(RegisteredReminderActivity.this, ContactActivity.class), 1);

                        return;
                    }
                    String num = cardItem4.get(options1).getPickerViewText();

                    reminder.setText(num);
                    cardItem4.get(0).setSetPicker(false);
                    return;
                }
                if (cardItem5.get(0).isSetPicker()) {
                    String num = cardItem5.get(options1).getPickerViewText();
                    reminderModeTyep = cardItem5.get(options1).getId();
                    reg_type.setText(num);
                    cardItem5.get(0).setSetPicker(false);
                    return;
                }
                if (cardItem6.get(0).isSetPicker()) {
                    String num = cardItem6.get(options1).getPickerViewText();
                    reminderModeTyep = cardItem6.get(options1).getId();
                    reg_department_name.setText(num);
                    cardItem6.get(0).setSetPicker(false);
                    return;
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView pickerTitle = (TextView) v.findViewById(R.id.pickerTitle);
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

                    }
                })
                .isDialog(false)
                .build();


    }


    private void Reminder() {
        cardItem4.add(new CardBean(0, "联系人页面选择"));
        cardItem4.add(new CardBean(1, "本人"));
    }

    private void rReminderType() {
        cardItem5.add(new CardBean(0, "铃声"));
        cardItem5.add(new CardBean(1, "震动"));
        cardItem5.add(new CardBean(2, "短信"));
        cardItem5.add(new CardBean(3, "电话"));
    }

    private void rDepartmentName() {
        cardItem6.add(new CardBean(0, "呼吸内科"));
        cardItem6.add(new CardBean(1, "消化内科"));
        cardItem6.add(new CardBean(2, "心血管内科"));
        cardItem6.add(new CardBean(3, "肾内科"));
        cardItem6.add(new CardBean(4, "神经内科"));
        cardItem6.add(new CardBean(5, "内分泌科"));
        cardItem6.add(new CardBean(6, "血液科"));
        cardItem6.add(new CardBean(7, "肿瘤科"));
        cardItem6.add(new CardBean(8, "普外科"));
        cardItem6.add(new CardBean(9, "胸外科"));
        cardItem6.add(new CardBean(10, "心血管外科"));
        cardItem6.add(new CardBean(11, "泌尿外科"));
        cardItem6.add(new CardBean(12, "骨科"));
        cardItem6.add(new CardBean(13, "烧伤科"));
        cardItem6.add(new CardBean(14, "神经外科"));
        cardItem6.add(new CardBean(15, "妇科"));
        cardItem6.add(new CardBean(16, "产科"));
        cardItem6.add(new CardBean(17, "儿科"));
        cardItem6.add(new CardBean(18, "五官科"));
        cardItem6.add(new CardBean(19, "口腔科"));
        cardItem6.add(new CardBean(20, "眼科"));
        cardItem6.add(new CardBean(21, "皮肤科"));
        cardItem6.add(new CardBean(22, "中医"));
        cardItem6.add(new CardBean(23, "理疗"));

    }

    @Event(R.id.reg_reister_time)
    private void reg_reister_time(View view) {
        MedicationHelper.hideInputMethod(view);
        showDatePickDialog(DateType.TYPE_YMDHMS);
    }

    @Event(R.id.reg_remarks)
    private void reg_remarks(View view) {

    }

    @Event(R.id.reg_offorno)
    private void reg_offorno(View view) {
        if (a == 1) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slider_close, new BitmapFactory.Options());
            reg_offorno.setImageBitmap(bitmap);
            a = 0;
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slider_open, new BitmapFactory.Options());
            reg_offorno.setImageBitmap(bitmap);
            a = 1;
        }
    }

    @Event(registerd_reminder_reminder)
    private void registerd_reminder_reminder(View view) {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem4);//添加数据
            cardItem4.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        } else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }

    }

    @Event(R.id.reg_type)
    private void reg_type(View view) {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem5);//添加数据
            cardItem5.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        } else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }

    }

    @Event(R.id.reg_department_name)
    private void reg_department_name(View view) {
        if (pvCustomOptions != null) {
            pvCustomOptions.setPicker(cardItem6);//添加数据
            cardItem6.get(0).setSetPicker(true);
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        } else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }

    }

    @Event(R.id.iption)
    private void ipition(View view) {

        if (MedicationHelper.isNullOrEmpty(reg_hospital_name.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "医院名称").show();
            return;
        }

        if (MedicationHelper.isNullOrEmpty(reg_department_name.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "科室名称").show();
            return;
        }
        if (MedicationHelper.isNullOrEmpty(reg_doctor_name.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "医生名称").show();
            return;
        }
        if (MedicationHelper.isNullOrEmpty(reg_reister_time.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "提醒时间").show();
            return;
        }
        if (MedicationHelper.isNullOrEmpty(reminder.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "被提醒人").show();
            return;
        }
        if (MedicationHelper.isNullOrEmpty(reg_type.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "提醒方式").show();
            return;
        }
        medicationUpload = WeiboDialogUtils.createLoadingDialog(RegisteredReminderActivity.this, "上传用药信息中...");
        medicationUpload.show();
        RequestParams params = new RequestParams(AppConstants.BASE_ACTION + AppConstants.APP_REGISTERREMINDS);
        params.setAsJsonContent(true);
        RegisterReminderRequestBean registerReminderRequestBean = new RegisterReminderRequestBean();
        RegisterReminderRequestBean.AppRegisterremindBean appRegisterremindBean = new RegisterReminderRequestBean.AppRegisterremindBean();
        appRegisterremindBean.setToken("426426426");
        appRegisterremindBean.setHosptial_name(reg_hospital_name.getText().toString());
        appRegisterremindBean.setCharacteristic_name(reg_department_name.getText().toString());
        appRegisterremindBean.setDoctor_name(reg_doctor_name.getText().toString());
        appRegisterremindBean.setGet_time(reg_reister_time.getText().toString());
        appRegisterremindBean.setApp_contact_id(contactID);

        if (reg_type.getText().toString().equals("铃声")) {
            appRegisterremindBean.setAlert_mode("1");
        } else if (reg_type.getText().toString().equals("震动")) {
            appRegisterremindBean.setAlert_mode("2");
        } else if (reg_type.getText().toString().equals("短信")) {
            appRegisterremindBean.setAlert_mode("3");
        } else {
            appRegisterremindBean.setAlert_mode("4");
        }


        registerReminderRequestBean.setApp_registerremind(appRegisterremindBean);

        params.setBodyContent(new Gson().toJson(registerReminderRequestBean));
        //    Log.e("json",getApp_contactJsonParam());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ToastUtils.showShortToast(result);
                WeiboDialogUtils.closeDialog(medicationUpload);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (data != null) {
                    reminder.setText(data.getStringExtra("1"));
                    contactID = data.getStringExtra("2");
                }
                break;
        }
    }
}
