package com.renqi.takemedicine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonArray;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.AppConstants;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.Remarks;
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

/**
 * Created by Xu Wei on 2017/7/17.
 */
@ContentView(R.layout.recyclerview_dialog)
public class RemakesRecyclerViewActivity extends BaseActivity {
    @ViewInject(R.id.recyclerViewDialog)
    private RecyclerView recyclerViewDialog;
    private CommonAdapter commonAdapter;
    private List<Remarks> remarksList=new ArrayList<>(),remarksListResult=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestParams params=new RequestParams(AppConstants.BASE_ACTION+AppConstants.app_drugreminds+AppConstants.APP_CHOICE_NAME);
        params.addBodyParameter("name","water");
        Log.e("params",params.toString());
        recyclerViewDialog.setLayoutManager(new LinearLayoutManager(this));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

               // ToastUtils.showShortToast(result);
                try {
                    Log.e("result",result);
                    JSONArray names=new JSONObject(result).getJSONArray("names");
                    for (int i = 0; i <names.length() ; i++) {
                        remarksList.add(new Remarks(
                                ((JSONObject)names.get(i)).getString("id"),//params1
                                ((JSONObject)names.get(i)).getString("name"),//params2
                                false//params3
                                ) );
                    }


                } catch (JSONException e) {
                    ToastUtils.showShortToast("json数据解析异常了");
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
                commonAdapter=new CommonAdapter<Remarks>(RemakesRecyclerViewActivity.this,
                        R.layout.recyclerview_dialog_item,remarksList) {
                    @Override
                    public void convert(ViewHolder holder, final Remarks remarks, final int position) {
                        final CheckBox checkBox= holder.getView(R.id.select_checkbox);
                        holder.setOnClickListener(R.id.contentView, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if(remarks.isSelect ) //如果是选中的取消选中
                                {
                                    checkBox.setChecked(false);
                                   remarksList.get(position).isSelect=false;

                                }
                                else //如果是没选中的 点击是选中
                                {
                                    checkBox.setChecked(true);
                                    remarksList.get(position).isSelect=true;

                                }
                             //   ToastUtils.showShortToast(remarks.id+"");
                            }

                        });
                        holder.setText(R.id.text,remarks.data);

                    }
                };
                recyclerViewDialog.setAdapter(commonAdapter);
            }
        });

    }
    @Event(R.id.query)
    private void query(View view){
        for (Remarks r:remarksList) {
            if(r.isSelect)
            {
                Log.e("选中",r.data+"  id是  "+r.id);
            }
        }

    }
}
