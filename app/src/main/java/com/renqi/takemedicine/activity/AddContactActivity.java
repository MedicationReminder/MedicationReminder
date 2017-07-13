package com.renqi.takemedicine.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.CardBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_add_contact)
public class AddContactActivity extends BaseActivity {
    private OptionsPickerView  pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();

    @ViewInject(R.id.ContactType)
    private TextView ContactType;

    @ViewInject(R.id.contactUserName)
    private TextView contactUserName;

    @ViewInject(R.id.phoneNumber)
    private TextView phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_add_contact);
        getCardData();
        initCustomOptionPicker();//初始化底部选择窗口
        setIption(AppConstants.iption.complete);
        setToolBarTitle(AppConstants.ToolBarTitle.addContacts);
    }
@Event(R.id.ContactType)
    private void ContactType(View view)
{
   if(pvCustomOptions != null)
        pvCustomOptions.show();
}
@Event(R.id.importAddress)
private void importAddress(View view)
{
    startActivityForResult(new Intent(
            Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
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
                ContactType.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView pickerTitle= (TextView) v.findViewById(R.id.pickerTitle);
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

                    }})
                .isDialog(false)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据

    }
    private void getCardData() {
        cardItem.add(new CardBean(0, "吴国佬"));
        cardItem.add(new CardBean(1, "张雪滑"));
        cardItem.add(new CardBean(2, "袁本初"));
        cardItem.add(new CardBean(3, "谭总"));
        cardItem.add(new CardBean(4, "狗鹏"));
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
