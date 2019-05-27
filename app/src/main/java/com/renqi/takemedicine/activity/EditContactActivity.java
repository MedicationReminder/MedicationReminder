package com.renqi.takemedicine.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
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
import com.renqi.takemedicine.bean.request.EditContactRequestBean;
import com.renqi.takemedicine.bean.response.EditContactResponseBean;
import com.renqi.takemedicine.http.HttpPatch;
import com.renqi.takemedicine.utils.MedicationHelper;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.utils.WeiboDialogUtils;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_edit_contact)
public class EditContactActivity extends BaseActivity {

    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private ArrayList<CardBean> cardItemRel = new ArrayList<>();
    @ViewInject(R.id.ContactType)
    private TextView ContactType;

    @ViewInject(R.id.contactUserName)
    private EditText contactUserName;

    @ViewInject(R.id.phoneNumber)
    private EditText phoneNumber;

    @ViewInject(R.id.inputRelation)
    private TextView inputRelation;

    @ViewInject(R.id.iption)
    private TextView iption;

    private int user_type = 5;

    Dialog contactUpload;
    String id;
    String device_token;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            contactUpload.dismiss();
            new ToastUtil(getApplicationContext(), R.layout.toast_complete, "已修改").show();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCardData();
        getRelData();
        initCustomOptionPicker();//初始化底部选择窗口
        setIption(AppConstants.iption.complete);
        setToolBarTitle("修改联系人");
        setIption("修改");
        Intent intent = getIntent();
        Log.e("wwwwww", intent.getStringExtra("edit"));

        try {
            JSONObject object = new JSONObject(intent.getStringExtra("edit"));
            id = object.getString("id");
            device_token = object.getString("device_token");
            contactUserName.setText(object.getString("name"));
            phoneNumber.setText(object.getString("phone"));
            inputRelation.setText(object.getString("relation"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Event(R.id.ContactType)
    private void ContactType(View view) {

        if (pvCustomOptions != null) {
            pvCustomOptions.show();
            MedicationHelper.hideInputMethod(view);
            pvCustomOptions.setPicker(cardItem);//添加数据
            cardItem.get(0).setSetPicker(true);
        } else {
            ToastUtils.showShortToast("选择框尚未初始化");
        }
    }

    @Event(R.id.inputRelation)
    private void inputRelation(View view) {
        if (pvCustomOptions != null) {
            MedicationHelper.hideInputMethod(view);
            pvCustomOptions.setPicker(cardItemRel);//添加数据
            cardItemRel.get(0).setSetPicker(true);
            pvCustomOptions.show();
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
                if (cardItem.get(0).isSetPicker()) {
                    //返回的分别是三个级别的选中位置
                    String tx = cardItem.get(options1).getPickerViewText();

                    user_type = cardItem.get(options1).getId();
                    ContactType.setText(tx);
                    cardItem.get(0).setSetPicker(false);
                } else if (cardItemRel.get(0).isSetPicker()) {
                    String tx = cardItemRel.get(options1).getPickerViewText();
                    inputRelation.setText(tx);
                    cardItemRel.get(0).setSetPicker(false);
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

    private void getCardData() {
        cardItem.add(new CardBean(0, "个人使用"));
        cardItem.add(new CardBean(1, "药店使用"));
        cardItem.add(new CardBean(2, "护士使用"));
        cardItem.add(new CardBean(3, "医生使用"));
    }

    private void getRelData() {
        cardItemRel.add(new CardBean(0, "医患"));
        cardItemRel.add(new CardBean(1, "客户"));
        cardItemRel.add(new CardBean(2, "好友"));
        cardItemRel.add(new CardBean(3, "家属"));
        cardItemRel.add(new CardBean(4, "同事"));
    }

    private String getApp_contactJsonParam() {
        List<Add_App_contact> addApp_contactList = new ArrayList<>();
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
    private void iption(View view) {
        if (user_type == 5) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "联系人类型").show();
            return;
        }

//        if(MedicationHelper.isNullOrEmpty(contactUserName.getText().toString().trim()))
//        {   new ToastUtil(getApplicationContext(), R.layout.toast_center, "联系人").show();return;}


        if (MedicationHelper.isNullOrEmpty(inputRelation.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "关系").show();
            return;
        }
        if (MedicationHelper.isNullOrEmpty(phoneNumber.getText().toString().trim())) {
            new ToastUtil(getApplicationContext(), R.layout.toast_center, "电话号码").show();
            return;

        }
        contactUpload = WeiboDialogUtils.createLoadingDialog(EditContactActivity.this, "修改中...");
        contactUpload.show();


        new Thread() {
            @Override
            public void run() {
                super.run();
                EditContactRequestBean.AppContactBean appContactBean = new EditContactRequestBean.AppContactBean();
                appContactBean.setName(contactUserName.getText().toString());
                appContactBean.setPhone(phoneNumber.getText().toString());
                appContactBean.setRelation(inputRelation.getText().toString());
                appContactBean.setToken(device_token);
                EditContactRequestBean editContactRequestBean = new EditContactRequestBean();
                editContactRequestBean.setApp_contact(appContactBean);
                editContactRequestBean.setId(id);
                String jsonParam = new Gson().toJson(editContactRequestBean);
                JSONObject resultObj = null;
                HttpClient httpClient = new DefaultHttpClient();
                HttpPatch httpPut = new HttpPatch("http://101.69.181.251/api/v1/app_contacts/" + id);
                httpPut.setHeader("Content-type", "application/json");
                httpPut.setHeader("Charset", HTTP.UTF_8);
                httpPut.setHeader("Accept", "application/json");
                httpPut.setHeader("Accept-Charset", HTTP.UTF_8);
                try {
                    if (jsonParam != null) {
                        StringEntity entity = new StringEntity(jsonParam, HTTP.UTF_8);
                        httpPut.setEntity(entity);
                    }
                    HttpResponse response = httpClient.execute(httpPut);

                    resultObj = new JSONObject(EntityUtils.toString(response.getEntity()));
                } catch (IOException | ParseException | JSONException e) {
                    e.printStackTrace();
                }
                EditContactResponseBean editContactResponseBean = new Gson().fromJson(resultObj.toString(), EditContactResponseBean.class);
                if (editContactResponseBean.getStatus() == 200) {
                    mHandler.sendMessage(new Message());
                }
            }
        }.start();


    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
