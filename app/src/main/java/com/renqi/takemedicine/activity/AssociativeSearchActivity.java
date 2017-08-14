package com.renqi.takemedicine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.view.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_associative_search)
public class AssociativeSearchActivity extends BaseActivity {
@ViewInject(R.id.medRecyList)
private RecyclerView medRecyList;
    @ViewInject(R.id.medName)
    private EditText medName;
    CommonAdapter commonAdapter;
    List<String> medNameList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setToolBarTitle("请选择");
        medName.addTextChangedListener(watcher);
        medRecyList.setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
        commonAdapter =new CommonAdapter<String>(getApplicationContext(),R.layout.text_med_name_item,medNameList) {
            @Override
            public void convert(ViewHolder holder, final String s, int position) {
                holder.setText(R.id.textMedName,s);

                LinearLayout layout=holder.getView(R.id.medItemLayout);
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medName.setText(s.trim());
                        Intent intent=new Intent();
                        intent.putExtra("medName",medName.getText().toString().trim());
                        setResult(1,intent);
                        finish();
                    }
                });
            }
        };
        medRecyList.setAdapter(commonAdapter);
        httpSearchLike();
    }
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // TODO Auto-generated method stub

        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
             medNameList.clear();
             httpSearchLike();
        }
    };

    private void httpSearchLike() {
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/app_drugreminds/all_drugs_name");
        params.addBodyParameter("name",medName.getText().toString().trim());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray medNameJO= (new JSONObject(result)).getJSONArray("drug_names");
                    if(medNameJO.length()==0) ToastUtils.showShortToast("没有相关"+medName.getText().toString().trim()+"的药品");
                    for (int i = 0; i <medNameJO.length() ; i++) {
                        JSONObject o=medNameJO.getJSONObject(i);
                        medNameList.add(o.optString("name"));
                    }
                    commonAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(R.id.home)
    private void home(View v)
    {
        finish();
    }
}
