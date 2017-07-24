package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.renqi.takemedicine.R;
import com.renqi.takemedicine.bean.response.KitDetialsResponseBean;

/**
 * Created by zsj on 2017/7/15.
 */

public class KitDetialsAdapter extends BaseAdapter {
    Context mContext;
    KitDetialsResponseBean kitDetialsResponseBean;
    LayoutInflater layoutInflater;
    boolean flag;

    public KitDetialsAdapter(Context mContext, KitDetialsResponseBean kitDetialsResponseBean, boolean flag) {
        this.mContext = mContext;
        this.kitDetialsResponseBean = kitDetialsResponseBean;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.flag = flag;
    }

    @Override
    public int getCount() {
        if (flag) {
            return kitDetialsResponseBean.getApp_drugreminds().size();
        } else {
            return kitDetialsResponseBean.getApp_register_reminds().size();
        }
    }

    @Override
    public Object getItem(int position) {

        if (flag) {
            return kitDetialsResponseBean.getApp_drugreminds().get(position);
        } else {
            return kitDetialsResponseBean.getApp_register_reminds().get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(flag) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                //获得组件，实例化组件
                convertView = layoutInflater.inflate(R.layout.item_kit_details, null);

                viewHolder.name = (TextView) convertView.findViewById(R.id.item_drugs_name);
                viewHolder.time = (TextView) convertView.findViewById(R.id.item_drugs_time);
                viewHolder.eat_count = (TextView) convertView.findViewById(R.id.item_drugs_consumption);
                viewHolder.counts = (TextView) convertView.findViewById(R.id.item_drugs_frequency);
                viewHolder.time_for = (TextView) convertView.findViewById(R.id.item_drugs_intervaltime);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(kitDetialsResponseBean.getApp_drugreminds().get(position).getName());
            viewHolder.time.setText(kitDetialsResponseBean.getApp_drugreminds().get(position).getTime());
            viewHolder.eat_count.setText(kitDetialsResponseBean.getApp_drugreminds().get(position).getEat_count()+"粒/颗");
            viewHolder.counts.setText(kitDetialsResponseBean.getApp_drugreminds().get(position).getCounts()+"次");
            viewHolder.time_for.setText(kitDetialsResponseBean.getApp_drugreminds().get(position).getTime_for()+"小时");
            return convertView;
        }else {
            ViewHolder2 viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder2();
                //获得组件，实例化组件

                convertView = layoutInflater.inflate(R.layout.item_kit_details_hosptial, null);
                viewHolder.hosptial_name = (TextView) convertView.findViewById(R.id.item_hosptial_name);
                viewHolder.time = (TextView) convertView.findViewById(R.id.item_register_time);
                viewHolder.characteristic_name = (TextView) convertView.findViewById(R.id.item_characteristic_name);
                viewHolder.doctor_name = (TextView) convertView.findViewById(R.id.item_doctor_name);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder2) convertView.getTag();
            }
            viewHolder.hosptial_name.setText(kitDetialsResponseBean.getApp_register_reminds().get(position).getHosptial_name());
            viewHolder.time.setText(kitDetialsResponseBean.getApp_register_reminds().get(position).getTime());
            viewHolder.characteristic_name.setText(kitDetialsResponseBean.getApp_register_reminds().get(position).getCharacteristic_name());
            viewHolder.doctor_name.setText(kitDetialsResponseBean.getApp_register_reminds().get(position).getDoctor_name());

            return convertView;
        }
    }

    class ViewHolder {
        TextView name;
        TextView time;
        TextView eat_count;
        TextView counts;
        TextView time_for;
    }
    class ViewHolder2 {
        TextView hosptial_name;
        TextView time;
        TextView characteristic_name;
        TextView doctor_name;
    }

}
