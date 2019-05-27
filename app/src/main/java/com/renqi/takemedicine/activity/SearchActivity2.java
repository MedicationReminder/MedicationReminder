package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.SpinnerAdapter;
import com.renqi.takemedicine.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xu Wei on 2017/7/27.
 */
@ContentView(R.layout.activity_search2)
public class SearchActivity2 extends BaseActivity {

    @ViewInject(R.id.spinner)
    private Spinner spinner;

    @ViewInject(R.id.search_name)
    private EditText search_name;
    @ViewInject(R.id.listView)
    private ListView listView;

    List<TextView> textviweList=new ArrayList<>();
    private  String[] mCountries = { "药品", "药店", "药厂"};
    private SpinnerAdapter spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //把GroupList加入适配器
//        spinnerAdapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,mCountries);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(spinnerAdapter);

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
    }
}
