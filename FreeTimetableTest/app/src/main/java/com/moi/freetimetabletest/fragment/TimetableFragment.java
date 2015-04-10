package com.moi.freetimetabletest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moi.freetimetabletest.R;


public class TimetableFragment extends Fragment {

    private int tableId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        tableId = getActivity().getIntent().getIntExtra("table_id", 0);

        return view;
    }

}
