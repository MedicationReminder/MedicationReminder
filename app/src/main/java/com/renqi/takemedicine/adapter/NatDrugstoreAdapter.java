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
import com.renqi.takemedicine.activity.NationalDrugstoreWebActivity;
import com.renqi.takemedicine.bean.response.NationalDrugstoreResponseBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsj on 2017/7/25.
 */

public class NatDrugstoreAdapter extends BaseAdapter {
    Context mContext;
    NationalDrugstoreResponseBean nationalDrugstoreResponseBean;
    LayoutInflater layoutInflater;
    List<Bitmap> bitmapList;

    public NatDrugstoreAdapter(Context mContext, NationalDrugstoreResponseBean nationalDrugstoreResponseBean) {
        this.mContext = mContext;
        this.nationalDrugstoreResponseBean = nationalDrugstoreResponseBean;
        this.layoutInflater = LayoutInflater.from(mContext);
        bitmapList = new ArrayList<>();
        int size = nationalDrugstoreResponseBean.getHosptials().size();
        for (int i = 0; i < size; i++) {
            bitmapList.add(i, null);
        }
        Log.e("position", "构造方法");
    }

    @Override
    public int getCount() {
        return nationalDrugstoreResponseBean.getHosptials().size();
    }

    @Override
    public Object getItem(int position) {
        return nationalDrugstoreResponseBean.getHosptials().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("position", position + "");
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.item_national_drugstore, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_national_drugstore_name);
            viewHolder.star = (TextView) convertView.findViewById(R.id.item_national_drugstore_star);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.item_national_drugstore_tel);

            viewHolder.icon = (ImageView) convertView.findViewById(R.id.imageView);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(nationalDrugstoreResponseBean.getHosptials().get(position).getName());
        viewHolder.star.setText("星级：" + nationalDrugstoreResponseBean.getHosptials().get(position).getStar());
        viewHolder.tel.setText("电话：" + nationalDrugstoreResponseBean.getHosptials().get(position).getTelephone());

        if (bitmapList.get(position) == null) {

            new MyThread(nationalDrugstoreResponseBean.getHosptials().get(position).getImage(), position).start();

        } else {
            viewHolder.icon.setImageBitmap(bitmapList.get(position));
        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView star;
        TextView tel;
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
                ((NationalDrugstoreWebActivity)mContext).MyHendler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}


