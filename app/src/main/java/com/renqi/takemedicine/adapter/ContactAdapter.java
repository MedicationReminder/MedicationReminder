package com.renqi.takemedicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.renqi.takemedicine.R;

/**
 * Created by Z on 2017/7/15.
 */

public class ContactAdapter extends BaseSwipeAdapter {
    private Context mContext;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 6;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void fillValues(int position, View convertView) {
        // TODO Auto-generated method stub

        TextView message_center_item_sk_tv = (TextView)convertView.findViewById(R.id.tv1);
        TextView message_center_item_sksj_tv = (TextView)convertView.findViewById(R.id.tv2);
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_contact, null);
        //SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));


        v.findViewById(R.id.message_center_item_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        // TODO Auto-generated method stub
        return R.id.message_center_item_swipe;
    }

}