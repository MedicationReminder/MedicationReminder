package com.renqi.takemedicine.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.Add_App_contact;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.CardBean;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.utils.WeiboDialogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_add_contact)
public class AddContactActivity extends BaseActivity {
    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    @ViewInject(R.id.ContactType)
    private TextView ContactType;

    @ViewInject(R.id.contactUserName)
    private EditText contactUserName;

    @ViewInject(R.id.phoneNumber)
    private EditText phoneNumber;

    @ViewInject(R.id.inputRelation)
    private EditText inputRelation;

    @ViewInject(R.id.iption)
    private TextView iption;

    private int user_type=5;


    Dialog contactUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_add_contact);
        getCardData();
        initCustomOptionPicker();//初始化底部选择窗口
        setIption(AppConstants.iption.complete);
        setToolBarTitle(AppConstants.ToolBarTitle.addContacts);
    // ToastUtils.showShortToast( TakeMedicinApplication.macAdress);

    }

    @Event(R.id.ContactType)
    private void ContactType(View view) {

        if (pvCustomOptions != null) {
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);

        }else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }

    @Event(R.id.importAddress)
    private void importAddress(View view) {


        if (Build.VERSION.SDK_INT >= 23) {
            //获取权限

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            } else {
                ToastUtils.showLongToast("您已拒绝读取联系人权限，请前往设置开通或者重新安装！");
            }
        } else {
            startActivityForResult(new Intent(
                    Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            // ContentProvider展示数据类似一个单个数据库表
            // ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
            ContentResolver reContentResolverol = getContentResolver();
            // URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
            Uri uri = data.getData();
            // 查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
            Cursor cursor = reContentResolverol.query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                // 获得DATA表中的名字
                String username = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                // 条件为联系人ID
                String contactId = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts._ID));
                // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
                cursor.close();
                Cursor phone = reContentResolverol.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                                + contactId, null, null);
                while (phone.moveToNext()) {
                    String usernumber = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneNumber.setText(usernumber);
                    contactUserName.setText(username);
                }
                phone.close();
            }

        }
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
                String tx = cardItem.get(options1).getPickerViewText();
                 user_type=cardItem.get(options1).getId();
                ContactType.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView pickerTitle = (TextView) v.findViewById(R.id.pickerTitle);
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        //   final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        pickerTitle.setText("请选择联系人类型");
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

        pvCustomOptions.setPicker(cardItem);//添加数据

    }

    private void getCardData() {
        cardItem.add(new CardBean(0, "个人使用"));
        cardItem.add(new CardBean(1, "药店使用"));
        cardItem.add(new CardBean(2, "护士使用"));
        cardItem.add(new CardBean(3, "医生使用"));
    }

    private String getApp_contactJsonParam(){
        List<Add_App_contact> addApp_contactList =new ArrayList<>();
        addApp_contactList.add(
                new Add_App_contact(
               new Add_App_contact.app_contact(
                contactUserName.getText().toString().trim(),
                TakeMedicinApplication.testMacAdress,
                inputRelation.getText().toString().trim(),
                phoneNumber.getText().toString().trim(),
                user_type))
        );

        try {
            return new JSONArray(MedicationHelper.gson.toJson(addApp_contactList)).get(0).toString().trim();
        } catch (JSONException e) {
            e.printStackTrace();
        }

     return "";
    }
    @Event(R.id.iption)
    private void iption(View view){
        if(user_type==5)
        {

            new ToastUtil(getApplicationContext(), R.layout.toast_center, "联系人类型").show();
            return;
        }
        if(MedicationHelper.isNullOrEmpty(contactUserName.getText().toString().trim()))
        {   new ToastUtil(getApplicationContext(), R.layout.toast_center, "联系人").show();return;}


        if(MedicationHelper.isNullOrEmpty(inputRelation.getText().toString().trim()))
        {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "关系").show();return;
        }
        if(MedicationHelper.isNullOrEmpty(phoneNumber.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "电话号码").show();return;

        }

        RequestParams params=new RequestParams(AppConstants.BASE_ACTION+AppConstants.app_contacts);
        params.setBodyContent(getApp_contactJsonParam());
        params.setAsJsonContent(true);
      Log.e("params",params.toString());
      //  Log.e("content",getApp_contactJsonParam());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                WeiboDialogUtils.closeDialog(contactUpload);
                new ToastUtil(getApplicationContext(), R.layout.toast_complete, "已添加").show();
               // ToastUtils.showShortToast(result);
                finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                 ToastUtils.showShortToast("访问接口失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        contactUpload = WeiboDialogUtils.createLoadingDialog(AddContactActivity.this, "保存中...");
        contactUpload.show();
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}