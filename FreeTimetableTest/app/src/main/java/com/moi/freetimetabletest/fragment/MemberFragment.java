package com.moi.freetimetabletest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moi.freetimetabletest.BounceListView;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.adpter.MemberListAdapter;
import com.moi.freetimetabletest.objectclass.Member;

import java.util.ArrayList;
import java.util.List;


public class MemberFragment extends Fragment {

    private BounceListView mListView;

    private MemberListAdapter adapter;

    private List<Member> memberList = new ArrayList<Member>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_member, container, false);

        initMember();
        mListView = (BounceListView) view.findViewById(R.id.list_view);
        adapter = new MemberListAdapter(getActivity().getBaseContext(), R.layout.item_table_list, memberList);
        mListView.setAdapter(adapter);

        return view;
    }

    private void initMember() {
        for (int i = 0; i < 20; i++) {
            Member a = new Member("test_" + i);
            memberList.add(a);
        }
    }

}
