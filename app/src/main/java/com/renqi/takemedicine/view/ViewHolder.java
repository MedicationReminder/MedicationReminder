package com.renqi.takemedicine.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Xu Wei on 2017/5/26.
 */


public class ViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public ViewHolder(Context context, View itemView, ViewGroup parent)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static ViewHolder get(Context context, ViewGroup parent, int layoutId)
    {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewHolder holder = new ViewHolder(context, itemView, parent);
        return holder;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    public ViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public ViewHolder setTextColor(int viewId, String rgb)
    {
        TextView tv = getView(viewId);
        tv.setTextColor(Color.parseColor(rgb));
        return this;
    }
    public ViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    public ViewHolder setTextViewBottomImage(int viewId, Drawable resId)
    {
        TextView view = getView(viewId);
        resId.setBounds(0, 0, resId.getMinimumWidth(), resId.getMinimumHeight());

         view.setCompoundDrawables(null,null,null,resId);
        return this;
    }
    public ViewHolder setTextViewRightImage(int viewId, Drawable resId)
    {
        TextView view = getView(viewId);
        resId.setBounds(0, 0, resId.getMinimumWidth(), resId.getMinimumHeight());

        view.setCompoundDrawables(null,null,resId,null);
        return this;
    }
    public ViewHolder setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}