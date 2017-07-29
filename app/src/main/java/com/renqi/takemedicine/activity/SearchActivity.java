package com.renqi.takemedicine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.HistoricalRecordAdapter;
import com.renqi.takemedicine.adapter.SpinnerAdapter;
import com.renqi.takemedicine.base.BaseActivity;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity {
    @ViewInject(R.id.KeyWord1)
    private TextView KeyWord1;
    @ViewInject(R.id.KeyWord2)
    private TextView KeyWord2;
    @ViewInject(R.id.KeyWord3)
    private TextView KeyWord3;
    @ViewInject(R.id.KeyWord4)
    private TextView KeyWord4;
    @ViewInject(R.id.KeyWord5)
    private TextView KeyWord5;
    @ViewInject(R.id.KeyWord6)
    private TextView KeyWord6;
    @ViewInject(R.id.KeyWord7)
    private TextView KeyWord7;
    @ViewInject(R.id.KeyWord8)
    private TextView KeyWord8;
    @ViewInject(R.id.spinner)
    private Spinner spinner;

    @ViewInject(R.id.search_name)
    private EditText search_name;
    @ViewInject(R.id.listView)
    private ListView listView;

    List<TextView> textviweList=new ArrayList<>();

    private  String[] mCountries = { "药品", "药店", "药厂"};

    private SpinnerAdapter spinnerAdapter;
    private HistoricalRecordAdapter historicalRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textviweList.add(KeyWord1);  textviweList.add(KeyWord2);  textviweList.add(KeyWord3);  textviweList.add(KeyWord4);
        textviweList.add(KeyWord5);  textviweList.add(KeyWord6);  textviweList.add(KeyWord7);  textviweList.add(KeyWord8);
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/app_drugreminds/search_words");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray list=new JSONArray(result);
                    for (int i = 0; i <list.length() ; i++) {
                        if(i==8)
                            return;
                        textviweList.get(i).setText(list.get(i)+"");

                    }

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

        spinnerAdapter=new SpinnerAdapter(this,mCountries);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position,true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setSelection(0,true);



        if(getInfo(this,"HR").equals("")){
            List<Map<String, String>> getInfo=new ArrayList<>();
            Map<String, String> map=new HashMap<>();
            map.put("search","name");
            getInfo.add(map);
            historicalRecordAdapter=new HistoricalRecordAdapter(this,getInfo);
            listView.setAdapter(historicalRecordAdapter);
        }else {
            List<Map<String, String>> getInfo=getInfo(this,"HR");
            historicalRecordAdapter=new HistoricalRecordAdapter(this,getInfo);
            listView.setAdapter(historicalRecordAdapter);
        }
    }


    @Event(R.id.iption)
    private void iption(View view){
        if(getInfo(this,"HR").equals("")){
            List<Map<String, String>> getInfo=new ArrayList<>();
            Map<String, String> map=new HashMap<>();
            map.put("search_name",search_name.getText().toString());
            getInfo.add(0,map);
            saveInfo(this,"HR",getInfo);
        }else {
        List<Map<String, String>> getInfo=getInfo(this,"HR");
            if(getInfo.size()<5){
                Map<String, String> map=new HashMap<>();
                map.put("search_name",search_name.getText().toString());
                getInfo.add(getInfo.size(),map);
                saveInfo(this,"HR",getInfo);
            }else {
                getInfo.remove(0);
                Map<String, String> map=new HashMap<>();
                map.put("search_name",search_name.getText().toString());
                getInfo.add(getInfo.size(),map);
                saveInfo(this,"HR",getInfo);
            }
        }

//        Intent intent =new Intent(SearchActivity.this,RelatedDrugsWebActivity.class);
//        Bundle bundle=new Bundle();
//        bundle.putString("flag",search_name.getText().toString());
//        intent.putExtras(bundle);
//        startActivity(intent);

        Intent intent =new Intent(SearchActivity.this,SearchDrugStoresActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("flag",search_name.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);

    }
    public void saveInfo(Context context, String key, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = context.getSharedPreferences("HistoricalRecord", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }


    public List<Map<String, String>> getInfo(Context context, String key) {
        List<Map<String, String>> datas = new ArrayList<>();
        SharedPreferences sp = context.getSharedPreferences("HistoricalRecord", Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datas;
    }
    @Event(R.id.clear)
    private void clear(View view){
        List<Map<String, String>> getInfo=new ArrayList<>();
        saveInfo(this,"HR",getInfo);
        historicalRecordAdapter=new HistoricalRecordAdapter(this,getInfo);
        listView.setAdapter(historicalRecordAdapter);
    }
    @Event(R.id.home)
    private void home(View view){
        finish();
    }
}
