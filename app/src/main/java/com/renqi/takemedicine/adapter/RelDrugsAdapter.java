package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.activity.RelatedDrugsWebActivity;
import com.renqi.takemedicine.bean.response.RelatedDrugsWebResponseBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsj on 2017/7/25.
 */

public class RelDrugsAdapter extends BaseAdapter {
    Context mContext;
RelatedDrugsWebResponseBean relatedDrugsWebResponseBean;
    LayoutInflater layoutInflater;
    List<Bitmap> bitmapList;
    public RelDrugsAdapter( Context mContext,RelatedDrugsWebResponseBean relatedDrugsWebResponseBean){
        this.mContext = mContext;
        this.relatedDrugsWebResponseBean = relatedDrugsWebResponseBean;
        this.layoutInflater= LayoutInflater.from(mContext);
        bitmapList = new ArrayList<>();
        int size = relatedDrugsWebResponseBean.getDrug_names().size();
        for (int i = 0; i < size; i++) {
            bitmapList.add(i, null);
        }
        Log.e("position", "构造方法");
    }
    @Override
    public int getCount() {
        return relatedDrugsWebResponseBean.getDrug_names().size();
    }

    @Override
    public Object getItem(int position) {
        return relatedDrugsWebResponseBean.getDrug_names().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_related_drugs, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_related_drugs_name);
            viewHolder.spec = (TextView) convertView.findViewById(R.id.item_related_drugs_spec);
            viewHolder.code = (TextView) convertView.findViewById(R.id.item_related_drugs_code);
            viewHolder.price = (TextView) convertView.findViewById(R.id.item_related_drugs_price);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.imageView);


            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(relatedDrugsWebResponseBean.getDrug_names().get(position).getName());
        viewHolder.spec.setText("规格："+relatedDrugsWebResponseBean.getDrug_names().get(position).getSpec());
        viewHolder.code.setText("批号："+relatedDrugsWebResponseBean.getDrug_names().get(position).getCode());
        viewHolder.price.setText(relatedDrugsWebResponseBean.getDrug_names().get(position).getPrice());

        if (bitmapList.get(position) == null) {

            new MyThread(relatedDrugsWebResponseBean.getDrug_names().get(position).getImage(), position).start();

        } else {
            viewHolder.icon.setImageBitmap(bitmapList.get(position));
        }
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView spec;
        TextView code;
        TextView price;
        ImageView icon;
    }
    class MyThread extends Thread {

        private String imageUrl;
        int position;

        public MyThread(String imageUrl, int position) {
            this.imageUrl = imageUrl;
            this.position = position;
        }

        @Override
        public void run() {
            super.run();
            Bitmap mBitmap = null;


            HttpURLConnection conn = null;
            try {
                URL url = new URL(imageUrl);
                conn = (HttpURLConnection) url.openConnection();
                InputStream is = conn.getInputStream();
                mBitmap = BitmapFactory.decodeStream(is);
                bitmapList.add(position, mBitmap);
                Message message = new Message();
                message.what = 0;
                ((RelatedDrugsWebActivity)mContext).MyHendler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}

