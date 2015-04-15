package com.moi.freetimetabletest.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.activity.FreeMembersActivity;
import com.moi.freetimetabletest.db.MemberDatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class TimetableFragment extends Fragment implements View.OnClickListener{

    private int tableId;

    private String week;

    private Calendar calendar;

    private ImageButton class_0_0;
    private ImageButton class_0_1;
    private ImageButton class_0_2;
    private ImageButton class_0_3;
    private ImageButton class_0_4;
    private ImageButton class_0_5;
    private ImageButton class_0_6;
    private ImageButton class_0_7;
    private ImageButton class_0_8;
    private ImageButton class_0_9;
    private ImageButton class_0_10;
    private ImageButton class_0_11;

    private ImageButton class_1_0;
    private ImageButton class_1_1;
    private ImageButton class_1_2;
    private ImageButton class_1_3;
    private ImageButton class_1_4;
    private ImageButton class_1_5;
    private ImageButton class_1_6;
    private ImageButton class_1_7;
    private ImageButton class_1_8;
    private ImageButton class_1_9;
    private ImageButton class_1_10;
    private ImageButton class_1_11;

    private ImageButton class_2_0;
    private ImageButton class_2_1;
    private ImageButton class_2_2;
    private ImageButton class_2_3;
    private ImageButton class_2_4;
    private ImageButton class_2_5;
    private ImageButton class_2_6;
    private ImageButton class_2_7;
    private ImageButton class_2_8;
    private ImageButton class_2_9;
    private ImageButton class_2_10;
    private ImageButton class_2_11;

    private ImageButton class_3_0;
    private ImageButton class_3_1;
    private ImageButton class_3_2;
    private ImageButton class_3_3;
    private ImageButton class_3_4;
    private ImageButton class_3_5;
    private ImageButton class_3_6;
    private ImageButton class_3_7;
    private ImageButton class_3_8;
    private ImageButton class_3_9;
    private ImageButton class_3_10;
    private ImageButton class_3_11;

    private ImageButton class_4_0;
    private ImageButton class_4_1;
    private ImageButton class_4_2;
    private ImageButton class_4_3;
    private ImageButton class_4_4;
    private ImageButton class_4_5;
    private ImageButton class_4_6;
    private ImageButton class_4_7;
    private ImageButton class_4_8;
    private ImageButton class_4_9;
    private ImageButton class_4_10;
    private ImageButton class_4_11;

    private ImageButton class_5_0;
    private ImageButton class_5_1;
    private ImageButton class_5_2;
    private ImageButton class_5_3;
    private ImageButton class_5_4;
    private ImageButton class_5_5;
    private ImageButton class_5_6;
    private ImageButton class_5_7;
    private ImageButton class_5_8;
    private ImageButton class_5_9;
    private ImageButton class_5_10;
    private ImageButton class_5_11;

    private ImageButton class_6_0;
    private ImageButton class_6_1;
    private ImageButton class_6_2;
    private ImageButton class_6_3;
    private ImageButton class_6_4;
    private ImageButton class_6_5;
    private ImageButton class_6_6;
    private ImageButton class_6_7;
    private ImageButton class_6_8;
    private ImageButton class_6_9;
    private ImageButton class_6_10;
    private ImageButton class_6_11;

    private List<ImageButton> imageButtonList = new ArrayList<ImageButton>();

    //数据库

    private MemberDatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private ContentValues values;


    private String[][][] isFree;

    private int[][] isFreeNumber = new int[7][12];

    private List<Integer> idList = new ArrayList<Integer>();

    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private String tableName;

    private TextView textViewHint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("en", "onCreateView");
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        final View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        tableId = getActivity().getIntent().getIntExtra("table_id", 0);
        tableName = getActivity().getIntent().getStringExtra("table_name");

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                isFreeNumber[i][j] = 0;
            }
         }

        // 创建数据库
        dbHelper = new MemberDatabaseHelper(getActivity().getApplicationContext(), "Member.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

                // 绑定list
                Cursor cursor = db.rawQuery("select * from member where tableId=?",
                        new String[]{tableId + ""});
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        idList.add(id);
                    } while (cursor.moveToNext());
                }
                cursor.close();

                isFree = new String[7][12][idList.size()];

                cursor = db.rawQuery("select * from member where tableId=?",
                        new String[]{tableId + ""});
                if (cursor.moveToFirst()) {
                    do {
                        for (int i = 0; i < 7; i++) {
                            for (int j = 0; j < 12; j++) {
                                if (cursor.getInt(cursor.getColumnIndex("class_" + i + "_" + j)) == 1) {
                                    isFree[i][j][isFreeNumber[i][j]] = cursor.getString(cursor.getColumnIndex("member_name"));
                                    isFreeNumber[i][j]++;
                                }
                            }
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();

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


        class_0_0 = (ImageButton) view.findViewById(R.id.bt_class_0_0);
        class_0_0.setOnClickListener(this);
        class_0_1 = (ImageButton) view.findViewById(R.id.bt_class_0_1);
        class_0_1.setOnClickListener(this);
        class_0_2 = (ImageButton) view.findViewById(R.id.bt_class_0_2);
        class_0_2.setOnClickListener(this);
        class_0_3 = (ImageButton) view.findViewById(R.id.bt_class_0_3);
        class_0_3.setOnClickListener(this);
        class_0_4 = (ImageButton) view.findViewById(R.id.bt_class_0_4);
        class_0_4.setOnClickListener(this);
        class_0_5 = (ImageButton) view.findViewById(R.id.bt_class_0_5);
        class_0_5.setOnClickListener(this);
        class_0_6 = (ImageButton) view.findViewById(R.id.bt_class_0_6);
        class_0_6.setOnClickListener(this);
        class_0_7 = (ImageButton) view.findViewById(R.id.bt_class_0_7);
        class_0_7.setOnClickListener(this);
        class_0_8 = (ImageButton) view.findViewById(R.id.bt_class_0_8);
        class_0_8.setOnClickListener(this);
        class_0_9 = (ImageButton) view.findViewById(R.id.bt_class_0_9);
        class_0_9.setOnClickListener(this);
        class_0_10 = (ImageButton) view.findViewById(R.id.bt_class_0_10);
        class_0_10.setOnClickListener(this);
        class_0_11 = (ImageButton) view.findViewById(R.id.bt_class_0_11);
        class_0_11.setOnClickListener(this);

        class_1_0 = (ImageButton) view.findViewById(R.id.bt_class_1_0);
        class_1_0.setOnClickListener(this);
        class_1_1 = (ImageButton) view.findViewById(R.id.bt_class_1_1);
        class_1_1.setOnClickListener(this);
        class_1_2 = (ImageButton) view.findViewById(R.id.bt_class_1_2);
        class_1_2.setOnClickListener(this);
        class_1_3 = (ImageButton) view.findViewById(R.id.bt_class_1_3);
        class_1_3.setOnClickListener(this);
        class_1_4 = (ImageButton) view.findViewById(R.id.bt_class_1_4);
        class_1_4.setOnClickListener(this);
        class_1_5 = (ImageButton) view.findViewById(R.id.bt_class_1_5);
        class_1_5.setOnClickListener(this);
        class_1_6 = (ImageButton) view.findViewById(R.id.bt_class_1_6);
        class_1_6.setOnClickListener(this);
        class_1_7 = (ImageButton) view.findViewById(R.id.bt_class_1_7);
        class_1_7.setOnClickListener(this);
        class_1_8 = (ImageButton) view.findViewById(R.id.bt_class_1_8);
        class_1_8.setOnClickListener(this);
        class_1_9 = (ImageButton) view.findViewById(R.id.bt_class_1_9);
        class_1_9.setOnClickListener(this);
        class_1_10 = (ImageButton) view.findViewById(R.id.bt_class_1_10);
        class_1_10.setOnClickListener(this);
        class_1_11 = (ImageButton) view.findViewById(R.id.bt_class_1_11);
        class_1_11.setOnClickListener(this);

        class_2_0 = (ImageButton) view.findViewById(R.id.bt_class_2_0);
        class_2_0.setOnClickListener(this);
        class_2_1 = (ImageButton) view.findViewById(R.id.bt_class_2_1);
        class_2_1.setOnClickListener(this);
        class_2_2 = (ImageButton) view.findViewById(R.id.bt_class_2_2);
        class_2_2.setOnClickListener(this);
        class_2_3 = (ImageButton) view.findViewById(R.id.bt_class_2_3);
        class_2_3.setOnClickListener(this);
        class_2_4 = (ImageButton) view.findViewById(R.id.bt_class_2_4);
        class_2_4.setOnClickListener(this);
        class_2_5 = (ImageButton) view.findViewById(R.id.bt_class_2_5);
        class_2_5.setOnClickListener(this);
        class_2_6 = (ImageButton) view.findViewById(R.id.bt_class_2_6);
        class_2_6.setOnClickListener(this);
        class_2_7 = (ImageButton) view.findViewById(R.id.bt_class_2_7);
        class_2_7.setOnClickListener(this);
        class_2_8 = (ImageButton) view.findViewById(R.id.bt_class_2_8);
        class_2_8.setOnClickListener(this);
        class_2_9 = (ImageButton) view.findViewById(R.id.bt_class_2_9);
        class_2_9.setOnClickListener(this);
        class_2_10 = (ImageButton) view.findViewById(R.id.bt_class_2_10);
        class_2_10.setOnClickListener(this);
        class_2_11 = (ImageButton) view.findViewById(R.id.bt_class_2_11);
        class_2_11.setOnClickListener(this);

        class_3_0 = (ImageButton) view.findViewById(R.id.bt_class_3_0);
        class_3_0.setOnClickListener(this);
        class_3_1 = (ImageButton) view.findViewById(R.id.bt_class_3_1);
        class_3_1.setOnClickListener(this);
        class_3_2 = (ImageButton) view.findViewById(R.id.bt_class_3_2);
        class_3_2.setOnClickListener(this);
        class_3_3 = (ImageButton) view.findViewById(R.id.bt_class_3_3);
        class_3_3.setOnClickListener(this);
        class_3_4 = (ImageButton) view.findViewById(R.id.bt_class_3_4);
        class_3_4.setOnClickListener(this);
        class_3_5 = (ImageButton) view.findViewById(R.id.bt_class_3_5);
        class_3_5.setOnClickListener(this);
        class_3_6 = (ImageButton) view.findViewById(R.id.bt_class_3_6);
        class_3_6.setOnClickListener(this);
        class_3_7 = (ImageButton) view.findViewById(R.id.bt_class_3_7);
        class_3_7.setOnClickListener(this);
        class_3_8 = (ImageButton) view.findViewById(R.id.bt_class_3_8);
        class_3_8.setOnClickListener(this);
        class_3_9 = (ImageButton) view.findViewById(R.id.bt_class_3_9);
        class_3_9.setOnClickListener(this);
        class_3_10 = (ImageButton) view.findViewById(R.id.bt_class_3_10);
        class_3_10.setOnClickListener(this);
        class_3_11 = (ImageButton) view.findViewById(R.id.bt_class_3_11);
        class_3_11.setOnClickListener(this);

        class_4_0 = (ImageButton) view.findViewById(R.id.bt_class_4_0);
        class_4_0.setOnClickListener(this);
        class_4_1 = (ImageButton) view.findViewById(R.id.bt_class_4_1);
        class_4_1.setOnClickListener(this);
        class_4_2 = (ImageButton) view.findViewById(R.id.bt_class_4_2);
        class_4_2.setOnClickListener(this);
        class_4_3 = (ImageButton) view.findViewById(R.id.bt_class_4_3);
        class_4_3.setOnClickListener(this);
        class_4_4 = (ImageButton) view.findViewById(R.id.bt_class_4_4);
        class_4_4.setOnClickListener(this);
        class_4_5 = (ImageButton) view.findViewById(R.id.bt_class_4_5);
        class_4_5.setOnClickListener(this);
        class_4_6 = (ImageButton) view.findViewById(R.id.bt_class_4_6);
        class_4_6.setOnClickListener(this);
        class_4_7 = (ImageButton) view.findViewById(R.id.bt_class_4_7);
        class_4_7.setOnClickListener(this);
        class_4_8 = (ImageButton) view.findViewById(R.id.bt_class_4_8);
        class_4_8.setOnClickListener(this);
        class_4_9 = (ImageButton) view.findViewById(R.id.bt_class_4_9);
        class_4_9.setOnClickListener(this);
        class_4_10 = (ImageButton) view.findViewById(R.id.bt_class_4_10);
        class_4_10.setOnClickListener(this);
        class_4_11 = (ImageButton) view.findViewById(R.id.bt_class_4_11);
        class_4_11.setOnClickListener(this);

        class_5_0 = (ImageButton) view.findViewById(R.id.bt_class_5_0);
        class_5_0.setOnClickListener(this);
        class_5_1 = (ImageButton) view.findViewById(R.id.bt_class_5_1);
        class_5_1.setOnClickListener(this);
        class_5_2 = (ImageButton) view.findViewById(R.id.bt_class_5_2);
        class_5_2.setOnClickListener(this);
        class_5_3 = (ImageButton) view.findViewById(R.id.bt_class_5_3);
        class_5_3.setOnClickListener(this);
        class_5_4 = (ImageButton) view.findViewById(R.id.bt_class_5_4);
        class_5_4.setOnClickListener(this);
        class_5_5 = (ImageButton) view.findViewById(R.id.bt_class_5_5);
        class_5_5.setOnClickListener(this);
        class_5_6 = (ImageButton) view.findViewById(R.id.bt_class_5_6);
        class_5_6.setOnClickListener(this);
        class_5_7 = (ImageButton) view.findViewById(R.id.bt_class_5_7);
        class_5_7.setOnClickListener(this);
        class_5_8 = (ImageButton) view.findViewById(R.id.bt_class_5_8);
        class_5_8.setOnClickListener(this);
        class_5_9 = (ImageButton) view.findViewById(R.id.bt_class_5_9);
        class_5_9.setOnClickListener(this);
        class_5_10 = (ImageButton) view.findViewById(R.id.bt_class_5_10);
        class_5_10.setOnClickListener(this);
        class_5_11 = (ImageButton) view.findViewById(R.id.bt_class_5_11);
        class_5_11.setOnClickListener(this);

        class_6_0 = (ImageButton) view.findViewById(R.id.bt_class_6_0);
        class_6_0.setOnClickListener(this);
        class_6_1 = (ImageButton) view.findViewById(R.id.bt_class_6_1);
        class_6_1.setOnClickListener(this);
        class_6_2 = (ImageButton) view.findViewById(R.id.bt_class_6_2);
        class_6_2.setOnClickListener(this);
        class_6_3 = (ImageButton) view.findViewById(R.id.bt_class_6_3);
        class_6_3.setOnClickListener(this);
        class_6_4 = (ImageButton) view.findViewById(R.id.bt_class_6_4);
        class_6_4.setOnClickListener(this);
        class_6_5 = (ImageButton) view.findViewById(R.id.bt_class_6_5);
        class_6_5.setOnClickListener(this);
        class_6_6 = (ImageButton) view.findViewById(R.id.bt_class_6_6);
        class_6_6.setOnClickListener(this);
        class_6_7 = (ImageButton) view.findViewById(R.id.bt_class_6_7);
        class_6_7.setOnClickListener(this);
        class_6_8 = (ImageButton) view.findViewById(R.id.bt_class_6_8);
        class_6_8.setOnClickListener(this);
        class_6_9 = (ImageButton) view.findViewById(R.id.bt_class_6_9);
        class_6_9.setOnClickListener(this);
        class_6_10 = (ImageButton) view.findViewById(R.id.bt_class_6_10);
        class_6_10.setOnClickListener(this);
        class_6_11 = (ImageButton) view.findViewById(R.id.bt_class_6_11);
        class_6_11.setOnClickListener(this);

        textViewHint = (TextView) view.findViewById(R.id.tv_hint_add_member);
        if (idList.size() == 0) {
            textViewHint.setVisibility(View.VISIBLE);
        } else {
            textViewHint.setVisibility(View.GONE);
        }

        initImageButtonList();

        return view;
    }

    private void initImageButtonList() {
        imageButtonList.add(class_0_0);
        imageButtonList.add(class_0_1);
        imageButtonList.add(class_0_2);
        imageButtonList.add(class_0_3);
        imageButtonList.add(class_0_4);
        imageButtonList.add(class_0_5);
        imageButtonList.add(class_0_6);
        imageButtonList.add(class_0_7);
        imageButtonList.add(class_0_8);
        imageButtonList.add(class_0_9);
        imageButtonList.add(class_0_10);
        imageButtonList.add(class_0_11);

        imageButtonList.add(class_1_0);
        imageButtonList.add(class_1_1);
        imageButtonList.add(class_1_2);
        imageButtonList.add(class_1_3);
        imageButtonList.add(class_1_4);
        imageButtonList.add(class_1_5);
        imageButtonList.add(class_1_6);
        imageButtonList.add(class_1_7);
        imageButtonList.add(class_1_8);
        imageButtonList.add(class_1_9);
        imageButtonList.add(class_1_10);
        imageButtonList.add(class_1_11);

        imageButtonList.add(class_2_0);
        imageButtonList.add(class_2_1);
        imageButtonList.add(class_2_2);
        imageButtonList.add(class_2_3);
        imageButtonList.add(class_2_4);
        imageButtonList.add(class_2_5);
        imageButtonList.add(class_2_6);
        imageButtonList.add(class_2_7);
        imageButtonList.add(class_2_8);
        imageButtonList.add(class_2_9);
        imageButtonList.add(class_2_10);
        imageButtonList.add(class_2_11);

        imageButtonList.add(class_3_0);
        imageButtonList.add(class_3_1);
        imageButtonList.add(class_3_2);
        imageButtonList.add(class_3_3);
        imageButtonList.add(class_3_4);
        imageButtonList.add(class_3_5);
        imageButtonList.add(class_3_6);
        imageButtonList.add(class_3_7);
        imageButtonList.add(class_3_8);
        imageButtonList.add(class_3_9);
        imageButtonList.add(class_3_10);
        imageButtonList.add(class_3_11);

        imageButtonList.add(class_4_0);
        imageButtonList.add(class_4_1);
        imageButtonList.add(class_4_2);
        imageButtonList.add(class_4_3);
        imageButtonList.add(class_4_4);
        imageButtonList.add(class_4_5);
        imageButtonList.add(class_4_6);
        imageButtonList.add(class_4_7);
        imageButtonList.add(class_4_8);
        imageButtonList.add(class_4_9);
        imageButtonList.add(class_4_10);
        imageButtonList.add(class_4_11);

        imageButtonList.add(class_5_0);
        imageButtonList.add(class_5_1);
        imageButtonList.add(class_5_2);
        imageButtonList.add(class_5_3);
        imageButtonList.add(class_5_4);
        imageButtonList.add(class_5_5);
        imageButtonList.add(class_5_6);
        imageButtonList.add(class_5_7);
        imageButtonList.add(class_5_8);
        imageButtonList.add(class_5_9);
        imageButtonList.add(class_5_10);
        imageButtonList.add(class_5_11);

        imageButtonList.add(class_6_0);
        imageButtonList.add(class_6_1);
        imageButtonList.add(class_6_2);
        imageButtonList.add(class_6_3);
        imageButtonList.add(class_6_4);
        imageButtonList.add(class_6_5);
        imageButtonList.add(class_6_6);
        imageButtonList.add(class_6_7);
        imageButtonList.add(class_6_8);
        imageButtonList.add(class_6_9);
        imageButtonList.add(class_6_10);
        imageButtonList.add(class_6_11);


        showFreeTable();

    }

    private void showFreeTable() {
        Log.d("en", "showFreeTable");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                Log.d("aaaaaaa", isFreeNumber[i][j] + "____" + idList.size());
                if (isFreeNumber[i][j] == idList.size()) {
                    if (j == 0) {
                        if (isFreeNumber[i][j + 1] == idList.size()) {
                            imageButtonList.get(i * 12 + j).setBackgroundColor(getResources().getColor(R.color.green));
                        } else {
                            imageButtonList.get(i * 12 + j).setImageDrawable(getResources().getDrawable(R.mipmap.class_down));
                        }
                    } else if (j == 11) {
                        if (isFreeNumber[i][j - 1] == idList.size()) {
                            imageButtonList.get(i * 12 + j).setBackgroundColor(getResources().getColor(R.color.green));
                        } else {
                            imageButtonList.get(i * 12 + j).setImageDrawable(getResources().getDrawable(R.mipmap.class_up));
                        }
                    } else {
                        if (isFreeNumber[i][j + 1] == idList.size()) {
                            if (isFreeNumber[i][j - 1] == idList.size()) {
                                imageButtonList.get(i * 12 + j).setBackgroundColor(getResources().getColor(R.color.green));
                            } else {
                                imageButtonList.get(i * 12 + j).setImageDrawable(getResources().getDrawable(R.mipmap.class_up));
                            }
                        } else if (isFreeNumber[i][j - 1] == idList.size()) {
                            imageButtonList.get(i * 12 + j).setImageDrawable(getResources().getDrawable(R.mipmap.class_down));
                        } else {
                            imageButtonList.get(i * 12 + j).setImageDrawable(getResources().getDrawable(R.mipmap.class_all));
                        }
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_class_0_0:
                classClick(0, 0);
                break;
            case R.id.bt_class_0_1:
                classClick(0, 1);
                break;
            case R.id.bt_class_0_2:
                classClick(0, 2);
                break;
            case R.id.bt_class_0_3:
                classClick(0, 3);
                break;
            case R.id.bt_class_0_4:
                classClick(0, 4);
                break;
            case R.id.bt_class_0_5:
                classClick(0, 5);
                break;
            case R.id.bt_class_0_6:
                classClick(0, 6);
                break;
            case R.id.bt_class_0_7:
                classClick(0, 7);
                break;
            case R.id.bt_class_0_8:
                classClick(0, 8);
                break;
            case R.id.bt_class_0_9:
                classClick(0, 9);
                break;
            case R.id.bt_class_0_10:
                classClick(0, 10);
                break;
            case R.id.bt_class_0_11:
                classClick(0, 11);
                break;

            case R.id.bt_class_1_0:
                classClick(1, 0);
                break;
            case R.id.bt_class_1_1:
                classClick(1, 1);
                break;
            case R.id.bt_class_1_2:
                classClick(1, 2);
                break;
            case R.id.bt_class_1_3:
                classClick(1, 3);
                break;
            case R.id.bt_class_1_4:
                classClick(1, 4);
                break;
            case R.id.bt_class_1_5:
                classClick(1, 5);
                break;
            case R.id.bt_class_1_6:
                classClick(1, 6);
                break;
            case R.id.bt_class_1_7:
                classClick(1, 7);
                break;
            case R.id.bt_class_1_8:
                classClick(1, 8);
                break;
            case R.id.bt_class_1_9:
                classClick(1, 9);
                break;
            case R.id.bt_class_1_10:
                classClick(1, 10);
                break;
            case R.id.bt_class_1_11:
                classClick(1, 11);
                break;

            case R.id.bt_class_2_0:
                classClick(2, 0);
                break;
            case R.id.bt_class_2_1:
                classClick(2, 1);
                break;
            case R.id.bt_class_2_2:
                classClick(2, 2);
                break;
            case R.id.bt_class_2_3:
                classClick(2, 3);
                break;
            case R.id.bt_class_2_4:
                classClick(2, 4);
                break;
            case R.id.bt_class_2_5:
                classClick(2, 5);
                break;
            case R.id.bt_class_2_6:
                classClick(2, 6);
                break;
            case R.id.bt_class_2_7:
                classClick(2, 7);
                break;
            case R.id.bt_class_2_8:
                classClick(2, 8);
                break;
            case R.id.bt_class_2_9:
                classClick(2, 9);
                break;
            case R.id.bt_class_2_10:
                classClick(2, 10);
                break;
            case R.id.bt_class_2_11:
                classClick(2, 11);
                break;

            case R.id.bt_class_3_0:
                classClick(3, 0);
                break;
            case R.id.bt_class_3_1:
                classClick(3, 1);
                break;
            case R.id.bt_class_3_2:
                classClick(3, 2);
                break;
            case R.id.bt_class_3_3:
                classClick(3, 3);
                break;
            case R.id.bt_class_3_4:
                classClick(3, 4);
                break;
            case R.id.bt_class_3_5:
                classClick(3, 5);
                break;
            case R.id.bt_class_3_6:
                classClick(3, 6);
                break;
            case R.id.bt_class_3_7:
                classClick(3, 7);
                break;
            case R.id.bt_class_3_8:
                classClick(3, 8);
                break;
            case R.id.bt_class_3_9:
                classClick(3, 9);
                break;
            case R.id.bt_class_3_10:
                classClick(3, 10);
                break;
            case R.id.bt_class_3_11:
                classClick(3, 11);
                break;

            case R.id.bt_class_4_0:
                classClick(4, 0);
                break;
            case R.id.bt_class_4_1:
                classClick(4, 1);
                break;
            case R.id.bt_class_4_2:
                classClick(4, 2);
                break;
            case R.id.bt_class_4_3:
                classClick(4, 3);
                break;
            case R.id.bt_class_4_4:
                classClick(4, 4);
                break;
            case R.id.bt_class_4_5:
                classClick(4, 5);
                break;
            case R.id.bt_class_4_6:
                classClick(4, 6);
                break;
            case R.id.bt_class_4_7:
                classClick(4, 7);
                break;
            case R.id.bt_class_4_8:
                classClick(4, 8);
                break;
            case R.id.bt_class_4_9:
                classClick(4, 9);
                break;
            case R.id.bt_class_4_10:
                classClick(4, 10);
                break;
            case R.id.bt_class_4_11:
                classClick(4, 11);
                break;

            case R.id.bt_class_5_0:
                classClick(5, 0);
                break;
            case R.id.bt_class_5_1:
                classClick(5, 1);
                break;
            case R.id.bt_class_5_2:
                classClick(5, 2);
                break;
            case R.id.bt_class_5_3:
                classClick(5, 3);
                break;
            case R.id.bt_class_5_4:
                classClick(5, 4);
                break;
            case R.id.bt_class_5_5:
                classClick(5, 5);
                break;
            case R.id.bt_class_5_6:
                classClick(5, 6);
                break;
            case R.id.bt_class_5_7:
                classClick(5, 7);
                break;
            case R.id.bt_class_5_8:
                classClick(5, 8);
                break;
            case R.id.bt_class_5_9:
                classClick(5, 9);
                break;
            case R.id.bt_class_5_10:
                classClick(5, 10);
                break;
            case R.id.bt_class_5_11:
                classClick(5, 11);
                break;

            case R.id.bt_class_6_0:
                classClick(6, 0);
                break;
            case R.id.bt_class_6_1:
                classClick(6, 1);
                break;
            case R.id.bt_class_6_2:
                classClick(6, 2);
                break;
            case R.id.bt_class_6_3:
                classClick(6, 3);
                break;
            case R.id.bt_class_6_4:
                classClick(6, 4);
                break;
            case R.id.bt_class_6_5:
                classClick(6, 5);
                break;
            case R.id.bt_class_6_6:
                classClick(6, 6);
                break;
            case R.id.bt_class_6_7:
                classClick(6, 7);
                break;
            case R.id.bt_class_6_8:
                classClick(6, 8);
                break;
            case R.id.bt_class_6_9:
                classClick(6, 9);
                break;
            case R.id.bt_class_6_10:
                classClick(6, 10);
                break;
            case R.id.bt_class_6_11:
                classClick(6, 11);
                break;
        }
    }

    private void classClick(int line, int low) {
        String message = "";
        for (int i = 0; i < isFreeNumber[line][low]; i++) {
            message += isFree[line][low][i];
            message += "\n";
        }
        FreeMembersActivity.actionStart(getActivity().getApplicationContext(), message, tableId, tableName, line, low);
    }


}
