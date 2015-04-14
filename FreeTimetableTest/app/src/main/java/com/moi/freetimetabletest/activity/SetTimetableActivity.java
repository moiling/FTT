package com.moi.freetimetabletest.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.db.MemberDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class SetTimetableActivity extends ActionBarActivity implements View.OnClickListener {

    public static void actionStart(Context context, String memberName, int memberId, int tableId, String tableName) {
        Intent intent = new Intent(context, SetTimetableActivity.class);
        intent.putExtra("member_name", memberName);
        intent.putExtra("member_id", memberId);
        intent.putExtra("table_id", tableId);
        intent.putExtra("table_name", tableName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }

    private int tableId;

    private int memberId;

    private String tableName;

    private String memberName;

    private MaterialMenuDrawable materialMenu;

    private int[][] ischeck;

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

    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView thu;
    private TextView fri;
    private TextView sta;
    private TextView sun;

    private List<ImageButton> imageButtonList = new ArrayList<ImageButton>();

    private TextView save;

    //数据库

    private MemberDatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private ContentValues values;

    private ImageButton hideHint;

    private LinearLayout hint;

    private TextView alwaysHideHint;


    private SharedPreferences pref;

    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timetable);

        memberName = getIntent().getStringExtra("member_name");
        tableName = getIntent().getStringExtra("table_name");
        memberId = getIntent().getIntExtra("member_id", 0);
        tableId = getIntent().getIntExtra("table_id", 0);

        ischeck = new int[7][12];

        // 创建数据库
        dbHelper = new MemberDatabaseHelper(this, "Member.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        // 绑定list
        Cursor cursor = db.rawQuery("select * from member where id=?",
                new String[] { memberId + "" });
        if (cursor.moveToFirst()) {
            do {
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 12; j++) {
                        int number = cursor.getInt(cursor.getColumnIndex("class_" + i + "_" + j));
                        Log.d("number", number + "");
                        ischeck[i][j] = number;
                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        initClass();
        init();

        // 初始化imageButtonList
        initImageButtonList();
        int k = 0;
        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 12; j++) {
                Log.d("kkkkk",k+"");
                if (ischeck[i][j] == 1) {
                    imageButtonList.get(k).setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    Log.d("--------->",k+"");
                }
                k++;
            }
        }


        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isShow = pref.getBoolean("is_show", true);

        if (isShow) {
            hint.setVisibility(View.VISIBLE);
        } else {
            hint.setVisibility(View.GONE);
        }

        setToolbar();

    }

    private void init() {
        save = (TextView) findViewById(R.id.tv_save);
        save.setOnClickListener(this);
        hideHint = (ImageButton) findViewById(R.id.btn_hide_hint);
        hideHint.setOnClickListener(this);
        hint = (LinearLayout) findViewById(R.id.ll_hint);
        alwaysHideHint = (TextView) findViewById(R.id.tv_hide_hint);
        alwaysHideHint.setOnClickListener(this);

        mon = (TextView) findViewById(R.id.tv_0);
        tue = (TextView) findViewById(R.id.tv_1);
        wed = (TextView) findViewById(R.id.tv_2);
        thu = (TextView) findViewById(R.id.tv_3);
        fri = (TextView) findViewById(R.id.tv_4);
        sta = (TextView) findViewById(R.id.tv_5);
        sun = (TextView) findViewById(R.id.tv_6);
        mon.setOnClickListener(this);
        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thu.setOnClickListener(this);
        fri.setOnClickListener(this);
        sta.setOnClickListener(this);
        sun.setOnClickListener(this);

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
    }

    private void initClass() {
        class_0_0 = (ImageButton) findViewById(R.id.bt_class_0_0);
        class_0_0.setOnClickListener(this);
        class_0_1 = (ImageButton) findViewById(R.id.bt_class_0_1);
        class_0_1.setOnClickListener(this);
        class_0_2 = (ImageButton) findViewById(R.id.bt_class_0_2);
        class_0_2.setOnClickListener(this);
        class_0_3 = (ImageButton) findViewById(R.id.bt_class_0_3);
        class_0_3.setOnClickListener(this);
        class_0_4 = (ImageButton) findViewById(R.id.bt_class_0_4);
        class_0_4.setOnClickListener(this);
        class_0_5 = (ImageButton) findViewById(R.id.bt_class_0_5);
        class_0_5.setOnClickListener(this);
        class_0_6 = (ImageButton) findViewById(R.id.bt_class_0_6);
        class_0_6.setOnClickListener(this);
        class_0_7 = (ImageButton) findViewById(R.id.bt_class_0_7);
        class_0_7.setOnClickListener(this);
        class_0_8 = (ImageButton) findViewById(R.id.bt_class_0_8);
        class_0_8.setOnClickListener(this);
        class_0_9 = (ImageButton) findViewById(R.id.bt_class_0_9);
        class_0_9.setOnClickListener(this);
        class_0_10 = (ImageButton) findViewById(R.id.bt_class_0_10);
        class_0_10.setOnClickListener(this);
        class_0_11 = (ImageButton) findViewById(R.id.bt_class_0_11);
        class_0_11.setOnClickListener(this);

        class_1_0 = (ImageButton) findViewById(R.id.bt_class_1_0);
        class_1_0.setOnClickListener(this);
        class_1_1 = (ImageButton) findViewById(R.id.bt_class_1_1);
        class_1_1.setOnClickListener(this);
        class_1_2 = (ImageButton) findViewById(R.id.bt_class_1_2);
        class_1_2.setOnClickListener(this);
        class_1_3 = (ImageButton) findViewById(R.id.bt_class_1_3);
        class_1_3.setOnClickListener(this);
        class_1_4 = (ImageButton) findViewById(R.id.bt_class_1_4);
        class_1_4.setOnClickListener(this);
        class_1_5 = (ImageButton) findViewById(R.id.bt_class_1_5);
        class_1_5.setOnClickListener(this);
        class_1_6 = (ImageButton) findViewById(R.id.bt_class_1_6);
        class_1_6.setOnClickListener(this);
        class_1_7 = (ImageButton) findViewById(R.id.bt_class_1_7);
        class_1_7.setOnClickListener(this);
        class_1_8 = (ImageButton) findViewById(R.id.bt_class_1_8);
        class_1_8.setOnClickListener(this);
        class_1_9 = (ImageButton) findViewById(R.id.bt_class_1_9);
        class_1_9.setOnClickListener(this);
        class_1_10 = (ImageButton) findViewById(R.id.bt_class_1_10);
        class_1_10.setOnClickListener(this);
        class_1_11 = (ImageButton) findViewById(R.id.bt_class_1_11);
        class_1_11.setOnClickListener(this);

        class_2_0 = (ImageButton) findViewById(R.id.bt_class_2_0);
        class_2_0.setOnClickListener(this);
        class_2_1 = (ImageButton) findViewById(R.id.bt_class_2_1);
        class_2_1.setOnClickListener(this);
        class_2_2 = (ImageButton) findViewById(R.id.bt_class_2_2);
        class_2_2.setOnClickListener(this);
        class_2_3 = (ImageButton) findViewById(R.id.bt_class_2_3);
        class_2_3.setOnClickListener(this);
        class_2_4 = (ImageButton) findViewById(R.id.bt_class_2_4);
        class_2_4.setOnClickListener(this);
        class_2_5 = (ImageButton) findViewById(R.id.bt_class_2_5);
        class_2_5.setOnClickListener(this);
        class_2_6 = (ImageButton) findViewById(R.id.bt_class_2_6);
        class_2_6.setOnClickListener(this);
        class_2_7 = (ImageButton) findViewById(R.id.bt_class_2_7);
        class_2_7.setOnClickListener(this);
        class_2_8 = (ImageButton) findViewById(R.id.bt_class_2_8);
        class_2_8.setOnClickListener(this);
        class_2_9 = (ImageButton) findViewById(R.id.bt_class_2_9);
        class_2_9.setOnClickListener(this);
        class_2_10 = (ImageButton) findViewById(R.id.bt_class_2_10);
        class_2_10.setOnClickListener(this);
        class_2_11 = (ImageButton) findViewById(R.id.bt_class_2_11);
        class_2_11.setOnClickListener(this);

        class_3_0 = (ImageButton) findViewById(R.id.bt_class_3_0);
        class_3_0.setOnClickListener(this);
        class_3_1 = (ImageButton) findViewById(R.id.bt_class_3_1);
        class_3_1.setOnClickListener(this);
        class_3_2 = (ImageButton) findViewById(R.id.bt_class_3_2);
        class_3_2.setOnClickListener(this);
        class_3_3 = (ImageButton) findViewById(R.id.bt_class_3_3);
        class_3_3.setOnClickListener(this);
        class_3_4 = (ImageButton) findViewById(R.id.bt_class_3_4);
        class_3_4.setOnClickListener(this);
        class_3_5 = (ImageButton) findViewById(R.id.bt_class_3_5);
        class_3_5.setOnClickListener(this);
        class_3_6 = (ImageButton) findViewById(R.id.bt_class_3_6);
        class_3_6.setOnClickListener(this);
        class_3_7 = (ImageButton) findViewById(R.id.bt_class_3_7);
        class_3_7.setOnClickListener(this);
        class_3_8 = (ImageButton) findViewById(R.id.bt_class_3_8);
        class_3_8.setOnClickListener(this);
        class_3_9 = (ImageButton) findViewById(R.id.bt_class_3_9);
        class_3_9.setOnClickListener(this);
        class_3_10 = (ImageButton) findViewById(R.id.bt_class_3_10);
        class_3_10.setOnClickListener(this);
        class_3_11 = (ImageButton) findViewById(R.id.bt_class_3_11);
        class_3_11.setOnClickListener(this);

        class_4_0 = (ImageButton) findViewById(R.id.bt_class_4_0);
        class_4_0.setOnClickListener(this);
        class_4_1 = (ImageButton) findViewById(R.id.bt_class_4_1);
        class_4_1.setOnClickListener(this);
        class_4_2 = (ImageButton) findViewById(R.id.bt_class_4_2);
        class_4_2.setOnClickListener(this);
        class_4_3 = (ImageButton) findViewById(R.id.bt_class_4_3);
        class_4_3.setOnClickListener(this);
        class_4_4 = (ImageButton) findViewById(R.id.bt_class_4_4);
        class_4_4.setOnClickListener(this);
        class_4_5 = (ImageButton) findViewById(R.id.bt_class_4_5);
        class_4_5.setOnClickListener(this);
        class_4_6 = (ImageButton) findViewById(R.id.bt_class_4_6);
        class_4_6.setOnClickListener(this);
        class_4_7 = (ImageButton) findViewById(R.id.bt_class_4_7);
        class_4_7.setOnClickListener(this);
        class_4_8 = (ImageButton) findViewById(R.id.bt_class_4_8);
        class_4_8.setOnClickListener(this);
        class_4_9 = (ImageButton) findViewById(R.id.bt_class_4_9);
        class_4_9.setOnClickListener(this);
        class_4_10 = (ImageButton) findViewById(R.id.bt_class_4_10);
        class_4_10.setOnClickListener(this);
        class_4_11 = (ImageButton) findViewById(R.id.bt_class_4_11);
        class_4_11.setOnClickListener(this);

        class_5_0 = (ImageButton) findViewById(R.id.bt_class_5_0);
        class_5_0.setOnClickListener(this);
        class_5_1 = (ImageButton) findViewById(R.id.bt_class_5_1);
        class_5_1.setOnClickListener(this);
        class_5_2 = (ImageButton) findViewById(R.id.bt_class_5_2);
        class_5_2.setOnClickListener(this);
        class_5_3 = (ImageButton) findViewById(R.id.bt_class_5_3);
        class_5_3.setOnClickListener(this);
        class_5_4 = (ImageButton) findViewById(R.id.bt_class_5_4);
        class_5_4.setOnClickListener(this);
        class_5_5 = (ImageButton) findViewById(R.id.bt_class_5_5);
        class_5_5.setOnClickListener(this);
        class_5_6 = (ImageButton) findViewById(R.id.bt_class_5_6);
        class_5_6.setOnClickListener(this);
        class_5_7 = (ImageButton) findViewById(R.id.bt_class_5_7);
        class_5_7.setOnClickListener(this);
        class_5_8 = (ImageButton) findViewById(R.id.bt_class_5_8);
        class_5_8.setOnClickListener(this);
        class_5_9 = (ImageButton) findViewById(R.id.bt_class_5_9);
        class_5_9.setOnClickListener(this);
        class_5_10 = (ImageButton) findViewById(R.id.bt_class_5_10);
        class_5_10.setOnClickListener(this);
        class_5_11 = (ImageButton) findViewById(R.id.bt_class_5_11);
        class_5_11.setOnClickListener(this);

        class_6_0 = (ImageButton) findViewById(R.id.bt_class_6_0);
        class_6_0.setOnClickListener(this);
        class_6_1 = (ImageButton) findViewById(R.id.bt_class_6_1);
        class_6_1.setOnClickListener(this);
        class_6_2 = (ImageButton) findViewById(R.id.bt_class_6_2);
        class_6_2.setOnClickListener(this);
        class_6_3 = (ImageButton) findViewById(R.id.bt_class_6_3);
        class_6_3.setOnClickListener(this);
        class_6_4 = (ImageButton) findViewById(R.id.bt_class_6_4);
        class_6_4.setOnClickListener(this);
        class_6_5 = (ImageButton) findViewById(R.id.bt_class_6_5);
        class_6_5.setOnClickListener(this);
        class_6_6 = (ImageButton) findViewById(R.id.bt_class_6_6);
        class_6_6.setOnClickListener(this);
        class_6_7 = (ImageButton) findViewById(R.id.bt_class_6_7);
        class_6_7.setOnClickListener(this);
        class_6_8 = (ImageButton) findViewById(R.id.bt_class_6_8);
        class_6_8.setOnClickListener(this);
        class_6_9 = (ImageButton) findViewById(R.id.bt_class_6_9);
        class_6_9.setOnClickListener(this);
        class_6_10 = (ImageButton) findViewById(R.id.bt_class_6_10);
        class_6_10.setOnClickListener(this);
        class_6_11 = (ImageButton) findViewById(R.id.bt_class_6_11);
        class_6_11.setOnClickListener(this);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        //toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("  FTT    " + memberName);
        // Sub Title
        //toolbar.setSubtitle("  " + tableName);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // Handle your drawable state here
                switch (materialMenu.getIconState()) {
                    case BURGER:
                        break;
                    case ARROW:
                        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                TimeTableActivity.actionStart(getApplicationContext(), tableName, tableId, 1);

                                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                            }
                        }, 0);
                        finish();
                        break;
                    case X:
                        break;
                    case CHECK:
                        break;
                }

            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, false);
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.setNeverDrawTouch(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_timetable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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



            case R.id.tv_save:
                //数据库
                values.clear();
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 12; j++) {
                        values.put("class_" + i + "_" + j, ischeck[i][j]);
                    }
                }
                db.update("member", values, "id = ?", new String[] { memberId + ""});
                Animation alphaAnimation = new AlphaAnimation(1.0f, 0.1f);
                //设置动画时间
                alphaAnimation.setDuration(250);
                save.startAnimation(alphaAnimation);
                save.setVisibility(View.GONE);
                TimeTableActivity.actionStart(getApplicationContext(), tableName, tableId, 1);
                finish();
                break;

            case R.id.btn_hide_hint:
                hint.setVisibility(View.GONE);
                break;

            case R.id.tv_hide_hint:
                editor = pref.edit();
                editor.putBoolean("is_show", false);
                editor.commit();
                hint.setVisibility(View.GONE);
                break;

            case R.id.tv_0:
                weekClick(0);
                //for (int i = 0; i < 12; i ++) {
                    //classClick(0, i);

                //}
                break;
            case R.id.tv_1:
                //for (int i = 0; i < 12; i ++) {
                    //classClick(1, i);
                    weekClick(1);
                //}
                break;
            case R.id.tv_2:
                //for (int i = 0; i < 12; i ++) {
                    //classClick(2, i);
                    weekClick(2);
                //}
                break;
            case R.id.tv_3:
                //for (int i = 0; i < 12; i ++) {
                    //classClick(3, i);
                    weekClick(3);
                //}
                break;
            case R.id.tv_4:
                //for (int i = 0; i < 12; i ++) {
                    //classClick(4, i);
                    weekClick(4);
                //}
                break;
            case R.id.tv_5:
                //for (int i = 0; i < 12; i ++) {
                    //classClick(5, i);
                    weekClick(5);
               // }
                break;
            case R.id.tv_6:
               // for (int i = 0; i < 12; i ++) {
                    //classClick(6, i);
                    weekClick(6);
               // }
                break;
        }
    }

    private void weekClick(int x) {
        int isAllUnClick = 0;
        for (int i = 0; i < 12; i++) {
            if (ischeck[x][i] == 1) {
                isAllUnClick++;
            }
        }
        Log.d("isAllUnClick", isAllUnClick + "");
        if (isAllUnClick == 0) {
            Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            //设置动画时间
            alphaAnimation.setDuration(250);
            for (int i = 0; i < 12; i++) {
                imageButtonList.get(x * 12 + i).startAnimation(alphaAnimation);
                imageButtonList.get(x * 12 + i).setImageDrawable(getResources().getDrawable(R.mipmap.check));
                ischeck[x][i] = 1;
            }
        } else {
            for (int i = 0; i < 12; i++) {
                imageButtonList.get(x * 12 + i).setImageDrawable(null);
                ischeck[x][i] = 0;
            }
        }
        if (save.getVisibility() == View.GONE) {
            Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            //设置动画时间
            alphaAnimation.setDuration(250);
            save.startAnimation(alphaAnimation);
            save.setVisibility(View.VISIBLE);
        }
    }

    private void classClick(int line, int row) {
        if (ischeck[line][row] == 0) {
            Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            //设置动画时间
            alphaAnimation.setDuration(250);
            imageButtonList.get(line * 12 + row).startAnimation(alphaAnimation);
            imageButtonList.get(line * 12 + row).setImageDrawable(getResources().getDrawable(R.mipmap.check));
            ischeck[line][row] = 1;
        } else {
            imageButtonList.get(line * 12 + row).setImageDrawable(null);
            ischeck[line][row] = 0;
        }
        if (save.getVisibility() == View.GONE) {
            Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
            //设置动画时间
            alphaAnimation.setDuration(250);
            save.startAnimation(alphaAnimation);
            save.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            TimeTableActivity.actionStart(getApplicationContext(), tableName, tableId, 1);
            finish();
        }
        return true;
    }
}
