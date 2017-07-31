package com.renqi.takemedicine.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renqi.takemedicine.R;

/**
 * Created by Xu Wei on 2017/7/30.
 */

public class FragmentTest3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment_online_conuslting, container, false);
        return view;
    }


}


