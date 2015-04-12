package com.moi.freetimetabletest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moi.freetimetabletest.R;

import java.util.Calendar;
import java.util.TimeZone;


public class TimetableFragment extends Fragment {

    private int tableId;

    private String week;

    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        tableId = getActivity().getIntent().getIntExtra("table_id", 0);

        // 获取星期
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));

        switch (week) {
            case "1":
                TextView textView = (TextView) view.findViewById(R.id.tv_1);
                textView.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "2":
                TextView textView1 = (TextView) view.findViewById(R.id.tv_2);
                textView1.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "3":
                TextView textView2 = (TextView) view.findViewById(R.id.tv_3);
                textView2.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "4":
                TextView textView3 = (TextView) view.findViewById(R.id.tv_4);
                textView3.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "5":
                TextView textView4 = (TextView) view.findViewById(R.id.tv_5);
                textView4.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "6":
                TextView textView5 = (TextView) view.findViewById(R.id.tv_6);
                textView5.setTextColor(getResources().getColor(R.color.accent_color));
                break;
            case "7":
                TextView textView6 = (TextView) view.findViewById(R.id.tv_7);
                textView6.setTextColor(getResources().getColor(R.color.accent_color));
                break;
        }
        return view;
    }

}
