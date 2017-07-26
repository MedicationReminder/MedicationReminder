package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.bean.response.ContactResponseBean;

/**
 * Created by Z on 2017/7/15.
 */

public class ContactAdapter extends BaseAdapter {
    Context mContext;
    ContactResponseBean contactResponseBean;
    LayoutInflater layoutInflater;
    public ContactAdapter( Context mContext,ContactResponseBean contactResponseBean){
        this.mContext = mContext;
        this.contactResponseBean = contactResponseBean;
        this.layoutInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return contactResponseBean.getApp_contact().size();
    }

    @Override
    public Object getItem(int position) {
        return contactResponseBean.getApp_contact().get(position);
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
            convertView=layoutInflater.inflate(R.layout.item_contact, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.item_contact_name);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.item_contact_phone);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(contactResponseBean.getApp_contact().get(position).getName());
        viewHolder.phone.setText(contactResponseBean.getApp_contact().get(position).getPhone());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView phone;
    }
}
