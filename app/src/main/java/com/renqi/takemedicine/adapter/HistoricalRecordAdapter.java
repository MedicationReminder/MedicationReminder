package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.renqi.takemedicine.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zsj on 2017/7/26.
 */

public class HistoricalRecordAdapter extends BaseAdapter {
    Context mContext;
    List<Map<String, String>> getInfo;
    LayoutInflater layoutInflater;
    public HistoricalRecordAdapter( Context mContext, List<Map<String, String>> getInfo){
        this.mContext = mContext;
        this.getInfo = getInfo;
        this.layoutInflater= LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return getInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return getInfo.get(position).get(position);
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
            convertView=layoutInflater.inflate(R.layout.item_historical_record, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);

            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        if(getInfo.size()==1&&getInfo.get(0).get("search")!=null){
            viewHolder.name.setText("当前历史记录！");
        }else {
            viewHolder.name.setText(getInfo.get(position).get("search_name"));

        }
        return convertView;
    }
    class ViewHolder{
        TextView name;
    }
}
