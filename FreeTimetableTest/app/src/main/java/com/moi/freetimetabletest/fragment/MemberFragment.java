package com.moi.freetimetabletest.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.gc.materialdesign.views.ButtonFloat;
import com.moi.freetimetabletest.BounceListView;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.adpter.MemberListAdapter;
import com.moi.freetimetabletest.db.MemberDatabaseHelper;
import com.moi.freetimetabletest.objectclass.Member;

import java.util.ArrayList;
import java.util.List;


public class MemberFragment extends Fragment implements View.OnClickListener{

    private BounceListView mListView;

    private MemberListAdapter adapter;

    private List<Member> memberList = new ArrayList<Member>();

    private ButtonFloat menuButton;

    private ButtonFloat addMemberButton;

    private ButtonFloat clearMembersButton;

    private int tableId;


    //数据库

    private MemberDatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private ContentValues values;

    private List<Integer> idList = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_member, container, false);

        tableId = getActivity().getIntent().getIntExtra("table_id", 0);

        // 创建数据库
        dbHelper = new MemberDatabaseHelper(getActivity().getApplicationContext(), "Member.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        // 绑定list
        Cursor cursor =db.rawQuery("select * from member where tableId=?",
                new String[]{tableId + ""});
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("member_name"));
                Member member = new Member(name);
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();

        //initMember();
        mListView = (BounceListView) view.findViewById(R.id.list_view);
        adapter = new MemberListAdapter(getActivity().getBaseContext(), R.layout.item_table_list, memberList);
        mListView.setAdapter(adapter);

        menuButton = (ButtonFloat) view.findViewById(R.id.menu_button);
        menuButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        menuButton.setOnClickListener(this);
        addMemberButton = (ButtonFloat) view.findViewById(R.id.btn_add_member);
        addMemberButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        addMemberButton.setOnClickListener(this);
        addMemberButton.setVisibility(View.GONE);
        clearMembersButton = (ButtonFloat) view.findViewById(R.id.btn_clear_members);
        clearMembersButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        clearMembersButton.setVisibility(View.GONE);

        return view;
    }

    private void initMember() {
        for (int i = 0; i < 20; i++) {
            Member a = new Member("test_" + i + " " + tableId);
            memberList.add(a);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_button:

                if (addMemberButton.getVisibility() == View.GONE) {
                    Animation rotateAnimation = new RotateAnimation(360f, 0f, 0f, 0f);
                    rotateAnimation.setDuration(500);
                    addMemberButton.startAnimation(rotateAnimation);
                    clearMembersButton.startAnimation(rotateAnimation);
                    addMemberButton.setVisibility(View.VISIBLE);
                    clearMembersButton.setVisibility(View.VISIBLE);
                } else {
                    Animation rotateAnimation = new RotateAnimation(0f, 360f, 0f, 0f);
                    rotateAnimation.setDuration(500);
                    addMemberButton.startAnimation(rotateAnimation);
                    clearMembersButton.startAnimation(rotateAnimation);
                    addMemberButton.setVisibility(View.GONE);
                    clearMembersButton.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_add_member:
                createMember();
                break;
        }
    }

    /**
     * 新建一个table
     */
    private void createMember() {

        //数据库
        values.clear();
        values.put("member_name", "test");
        values.put("tableId", tableId);
        db.insert("member", null, values);

        Member member = new Member("test");
        memberList.add(member);
        adapter.notifyDataSetChanged();
    }
}
