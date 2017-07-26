package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.bean.response.RelatedDrugsWebResponseBean;

/**
 * Created by zsj on 2017/7/25.
 */

public class RelDrugsAdapter extends BaseAdapter {
    Context mContext;
RelatedDrugsWebResponseBean relatedDrugsWebResponseBean;
    LayoutInflater layoutInflater;
    public RelDrugsAdapter( Context mContext,RelatedDrugsWebResponseBean relatedDrugsWebResponseBean){
        this.mContext = mContext;
        this.relatedDrugsWebResponseBean = relatedDrugsWebResponseBean;
        this.layoutInflater= LayoutInflater.from(mContext);
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

        viewHolder.icon.setImageResource(R.mipmap.example);
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView spec;
        TextView code;
        TextView price;
        ImageView icon;
    }
}

