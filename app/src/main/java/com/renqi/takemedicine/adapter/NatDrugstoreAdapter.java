package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.bean.response.NationalDrugstoreResponseBean;

/**
 * Created by zsj on 2017/7/25.
 */

public class NatDrugstoreAdapter extends BaseAdapter {
    Context mContext;
    NationalDrugstoreResponseBean nationalDrugstoreResponseBean;
    LayoutInflater layoutInflater;
    public NatDrugstoreAdapter( Context mContext,NationalDrugstoreResponseBean nationalDrugstoreResponseBean){
        this.mContext = mContext;
        this.nationalDrugstoreResponseBean = nationalDrugstoreResponseBean;
        this.layoutInflater= LayoutInflater.from(mContext);
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
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_national_drugstore, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_national_drugstore_name);
            viewHolder.star = (TextView) convertView.findViewById(R.id.item_national_drugstore_star);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.item_national_drugstore_tel);

            viewHolder.icon = (ImageView) convertView.findViewById(R.id.imageView);


            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(nationalDrugstoreResponseBean.getHosptials().get(position).getName());
        viewHolder.star.setText("星级："+nationalDrugstoreResponseBean.getHosptials().get(position).getStar());
        viewHolder.tel.setText("电话："+nationalDrugstoreResponseBean.getHosptials().get(position).getTelephone());

        viewHolder.icon.setImageResource(R.mipmap.example);
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView star;
        TextView tel;
        ImageView icon;
    }
}


