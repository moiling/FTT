package com.moi.freetimetabletest.fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;

import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.widgets.SnackBar;
import com.moi.freetimetabletest.BounceListView;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.adpter.MemberListAdapter;
import com.moi.freetimetabletest.db.MemberDatabaseHelper;
import com.moi.freetimetabletest.objectclass.Member;

import java.util.ArrayList;
import java.util.List;


public class MemberFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemLongClickListener{

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
        Cursor cursor = db.rawQuery("select * from member where tableId=?",
                new String[] { tableId + "" });
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("member_name"));
                Member member = new Member(name);
                memberList.add(member);
            } while (cursor.moveToNext());
        }
        cursor.close();

        mListView = (BounceListView) view.findViewById(R.id.list_view);
        adapter = new MemberListAdapter(getActivity().getBaseContext(), R.layout.item_table_list, memberList);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(this);

        menuButton = (ButtonFloat) view.findViewById(R.id.menu_button);
        menuButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        menuButton.setOnClickListener(this);
        addMemberButton = (ButtonFloat) view.findViewById(R.id.btn_add_member);
        addMemberButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        addMemberButton.setOnClickListener(this);
        addMemberButton.setVisibility(View.GONE);
        clearMembersButton = (ButtonFloat) view.findViewById(R.id.btn_clear_members);
        clearMembersButton.setBackgroundColor(getResources().getColor(R.color.accent_color));
        clearMembersButton.setOnClickListener(this);
        clearMembersButton.setVisibility(View.GONE);

        return view;
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
                Animation rotateAnimation = new RotateAnimation(360f, 0f, 0f, 0f);
                rotateAnimation.setDuration(500);
                addMemberButton.startAnimation(rotateAnimation);
                createMember();
                Animation rotateAnimation3 = new RotateAnimation(0f, 360f, 0f, 0f);
                rotateAnimation3.setDuration(500);
                addMemberButton.startAnimation(rotateAnimation3);
                clearMembersButton.startAnimation(rotateAnimation3);
                addMemberButton.setVisibility(View.GONE);
                clearMembersButton.setVisibility(View.GONE);
                break;
            case R.id.btn_clear_members:
                Animation rotateAnimation2 = new RotateAnimation(360f, 0f, 0f, 0f);
                rotateAnimation2.setDuration(500);
                clearMembersButton.startAnimation(rotateAnimation2);
                clearMembers();
                Animation rotateAnimation4 = new RotateAnimation(0f, 360f, 0f, 0f);
                rotateAnimation4.setDuration(500);
                addMemberButton.startAnimation(rotateAnimation4);
                clearMembersButton.startAnimation(rotateAnimation4);
                addMemberButton.setVisibility(View.GONE);
                clearMembersButton.setVisibility(View.GONE);
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

    /**
     * 清空所有table
     */
    private void clearMembers() {
        memberList.clear();
        // 清空数据库
        db.delete("member", "tableId = ?", new String[] { tableId + "" });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Animation shake = AnimationUtils.loadAnimation(view.getContext(), R.anim.shake);
        view.startAnimation(shake);
        SnackBar snackbar = new SnackBar((Activity) view.getContext(), "Delete this member?", "Yes", new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // 让这个tableId中的member们记录id，否则删到别人的了
                        Cursor cursor = db.rawQuery("select * from member where tableId=?",
                                new String[] { tableId + "" });
                        if (cursor.moveToFirst()) {
                            do {
                                int id = cursor.getInt(cursor.getColumnIndex("id"));
                                idList.add(id);
                            } while (cursor.moveToNext());
                        }
                        Log.d("MemberFragment---->", idList.get(position) + "");
                        Log.d("MemberFragment---->","删除了！");

                        db.delete("member", "id = ?", new String[] { idList.get(position) + "" });
                        // 不清空数据全乱了
                        idList.clear();

                    }
                }).start();
                memberList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        snackbar.show();
        return true;
    }
}
