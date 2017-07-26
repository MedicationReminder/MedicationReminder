package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.renqi.takemedicine.R;

/**
 * Created by zsj on 2017/7/26.
 */

public class SpinnerAdapter  extends BaseAdapter {
    Context mContext;
    String[] mCountries;
    LayoutInflater layoutInflater;
    public SpinnerAdapter( Context mContext,String[] mCountries){
        this.mContext = mContext;
        this.mCountries = mCountries;
        this.layoutInflater= LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mCountries.length;
    }

    @Override
    public Object getItem(int position) {
        return mCountries[position];
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
            convertView=layoutInflater.inflate(R.layout.item_sprinner, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(mCountries[position]);

        return convertView;
    }
    class ViewHolder{
        TextView name;
    }
}
