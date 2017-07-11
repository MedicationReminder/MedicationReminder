package com.renqi.takemedicine.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.renqi.takemedicine.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Xu Wei on 2017/7/7.
 */

public class TipDialog extends Dialog {

    Context context;

    public TipDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           this.setContentView(R.layout.dialog_takemedication);
        ImageView imgClose= (ImageView) findViewById(R.id.imgClose);
         imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    @Event(R.id.imgClose)
    private void imgClose(View view)
    {
        TipDialog.this.dismiss();
    }

    @Override
    public void dismiss() {


        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        dismiss();

    }
}
