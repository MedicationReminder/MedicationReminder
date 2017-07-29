package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.bean.MedIntroductionAdapterBean;

import java.util.List;

/**
 * Created by zsj on 2017/7/25.
 */

public class MedIntroductionAdapter extends BaseAdapter {
    Context mContext;
   List<MedIntroductionAdapterBean> stringList;
    LayoutInflater layoutInflater;
    public MedIntroductionAdapter( Context mContext,List<MedIntroductionAdapterBean> stringList){
        this.mContext = mContext;
        this.stringList = stringList;
        this.layoutInflater= LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
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
            convertView=layoutInflater.inflate(R.layout.item_medication_introduction, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.title);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(stringList.get(position).getName());
        viewHolder.icon.setImageResource(stringList.get(position).getId());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        ImageView icon;
    }
}
