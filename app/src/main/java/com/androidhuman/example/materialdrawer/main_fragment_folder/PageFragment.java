package com.androidhuman.example.materialdrawer.main_fragment_folder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidhuman.example.materialdrawer.R;

/**
 * Created by youmyeongsic on 15. 4. 5..
 */
public class PageFragment extends Fragment {

    private String Current_Text;

    public void setCurrent_Text(String Current_Text) {

        this.Current_Text = Current_Text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_pagefragment, container, false);
        TextView textView = (TextView) view.findViewById(R.id.main_viewpager_text);
        textView.setText(Current_Text);
        return view;
    }
}
