package com.example.star.viewpagerfragmentdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiongxingxing on 15/8/15.
 */
public class MyTestFragment extends Fragment {
    private static final String EXTRA_LAYOUT_ID = "fragment_layout_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resId = getArguments().getInt(EXTRA_LAYOUT_ID);
        View rootView = inflater.inflate(resId, container, false);
        return rootView;
    }

    public static MyTestFragment newInstance(@NonNull int resId) {
        MyTestFragment fragment = new MyTestFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_LAYOUT_ID, resId);
        fragment.setArguments(args);
        return fragment;
    }
}
