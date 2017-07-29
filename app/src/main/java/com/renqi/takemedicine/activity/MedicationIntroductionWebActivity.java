package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.MedIntroductionAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.MedIntroductionAdapterBean;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_medication_introduction_web)
public class MedicationIntroductionWebActivity extends BaseActivity {
    @ViewInject(R.id.medicationIntroductionListView)
    private ListView medicationIntroductionListView;
private List<MedIntroductionAdapterBean> stringList;
    private MedIntroductionAdapter medIntroductionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle(AppConstants.ToolBarTitle.medicationIntroduction);
        stringList=new ArrayList<>();

        MedIntroductionAdapterBean medIntroductionAdapterBean=new MedIntroductionAdapterBean("用药参考",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean);

        MedIntroductionAdapterBean medIntroductionAdapterBean1=new MedIntroductionAdapterBean("中药堂",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean1);

        MedIntroductionAdapterBean medIntroductionAdapterBean2=new MedIntroductionAdapterBean("儿童用药",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean2);

        MedIntroductionAdapterBean medIntroductionAdapterBean3=new MedIntroductionAdapterBean("安全用药",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean3);

        MedIntroductionAdapterBean medIntroductionAdapterBean4=new MedIntroductionAdapterBean("药师用药",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean4);

        MedIntroductionAdapterBean medIntroductionAdapterBean5=new MedIntroductionAdapterBean("家庭用药大全",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean5);

        MedIntroductionAdapterBean medIntroductionAdapterBean6=new MedIntroductionAdapterBean("孕妇用药",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean6);

        MedIntroductionAdapterBean medIntroductionAdapterBean7=new MedIntroductionAdapterBean("妇科用药",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean7);

        MedIntroductionAdapterBean medIntroductionAdapterBean8=new MedIntroductionAdapterBean("吃药提醒",R.mipmap.down);
        stringList.add(medIntroductionAdapterBean8);


        medIntroductionAdapter=new MedIntroductionAdapter(MedicationIntroductionWebActivity.this,stringList);
        medicationIntroductionListView.setAdapter(medIntroductionAdapter);
        medicationIntroductionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(stringList.get(position).getId()==R.mipmap.down){
                    stringList.get(position).setId(R.mipmap.up);
                }else {
                    stringList.get(position).setId(R.mipmap.down);
                }
                medIntroductionAdapter.notifyDataSetChanged();
            }
        });
    }





}