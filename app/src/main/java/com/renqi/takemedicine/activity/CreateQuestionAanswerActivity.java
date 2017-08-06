package com.renqi.takemedicine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonArray;
import com.renqi.takemedicine.R;
import com.renqi.takemedicine.adapter.CommonAdapter;
import com.renqi.takemedicine.app.TakeMedicinApplication;
import com.renqi.takemedicine.base.BaseActivity;
import com.renqi.takemedicine.bean.Reply;
import com.renqi.takemedicine.utils.ToastUtil;
import com.renqi.takemedicine.view.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_create_question_aanswer)
public class CreateQuestionAanswerActivity extends BaseActivity {
    @ViewInject(R.id.questionTitleContent)
    private TextView questionTitleContent;
    @ViewInject(R.id.questionTitle)
    private TextView questionTitle;
    @ViewInject(R.id.createTime)
    private TextView createTime;
    @ViewInject(R.id.replyView)
    private LinearLayout replyView;
    @ViewInject(R.id.replyRecyclerview)private RecyclerView replyRecyclerview;
    CommonAdapter commonAdapter;
    int replies_count;
    List<Reply> replyList=new ArrayList<>();

    String QAid,topic_category_name,created_at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("问答详情");
        QAid= getIntent().getStringExtra("id");
        RequestParams params=new RequestParams("http://101.69.181.251/api/v1/topics/"+QAid);
        Log.e("param",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
            //    ToastUtils.showShortToast(result);
                try {
                    JSONObject o=(new JSONObject(result)).getJSONObject("topic");
                    questionTitleContent.setText(o.getString("content"));
                    questionTitle.setText(o.getString("title"));
                    createTime.setText("创建时间 "+o.getString("created_at"));
                    topic_category_name=o.getString("topic_category_name");
                    replies_count=o.getInt("replies_count");
                   JSONArray replies= o.getJSONArray("replies");
                    if(replies.length()==0)
                        ToastUtils.showShortToast("还没有人回复此问答,快来抢沙发!");
                    for (int i = 0; i <replies.length() ; i++) {
                       JSONObject oReply=replies.getJSONObject(i);
                        Reply reply=new Reply();
                        reply.setContent(oReply.getString("content"));
                        reply.setCreated_at(oReply.getString("created_at"));
                        reply.setId(oReply.getString("id"));
                        replyList.add(reply);
                    }
                    replyRecyclerview.setLayoutManager(new LinearLayoutManager(TakeMedicinApplication.getContext()));
                    commonAdapter = new CommonAdapter<Reply>(getApplicationContext(),R.layout.reply_item,replyList){

                        @Override
                        public void convert(ViewHolder holder, final Reply reply, int position) {
                          LinearLayout replyItemLayout= holder.getView(R.id.replyItemLayout);
                            holder.setText(R.id.content,reply.getContent());
                            holder.setText(R.id.created_at,reply.getCreated_at());
                            replyItemLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ToastUtils.showShortToast(reply.getId());
                                }
                            });

                        }
                    };
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
                replyRecyclerview.setAdapter(commonAdapter);
            }
        });
        //setIption();
    }

    @Event(R.id.home)
    private  void back(View v)
    {
        finish();
    }

}
