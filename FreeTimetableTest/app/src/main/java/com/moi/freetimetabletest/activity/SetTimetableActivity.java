package com.moi.freetimetabletest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.moi.freetimetabletest.R;

import java.util.ArrayList;
import java.util.List;

public class SetTimetableActivity extends ActionBarActivity implements View.OnClickListener {

    public static void actionStart(Context context, String memberName, int memberId) {
        Intent intent = new Intent(context, SetTimetableActivity.class);
        intent.putExtra("member_name", memberName);
        intent.putExtra("member_id", memberId);
        context.startActivity(intent);
    }

    private int memberId;

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

    private List<ImageButton> imageButtonList = new ArrayList<ImageButton>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timetable);

        memberName = getIntent().getStringExtra("member_name");
        memberId = getIntent().getIntExtra("member_id", 0);

        // 初始化前五天全为零，后两天为1……
        ischeck = new int[7][12];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 12; j++) {
                ischeck[i][j] = 0;
            }
        }
        for (int i = 5; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                ischeck[i][j] = 1;
            }
        }

        initClass();

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

        setToolbar();

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
                if (ischeck[0][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_0.startAnimation(alphaAnimation);
                    class_0_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][0] = 1;
                } else {
                    class_0_0.setImageDrawable(null);
                    ischeck[0][0] = 0;
                }
                break;
            case R.id.bt_class_0_1:
                if (ischeck[0][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_1.startAnimation(alphaAnimation);
                    class_0_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][1] = 1;
                } else {
                    class_0_1.setImageDrawable(null);
                    ischeck[0][1] = 0;
                }
                break;
            case R.id.bt_class_0_2:
                if (ischeck[0][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_2.startAnimation(alphaAnimation);
                    class_0_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][2] = 1;
                } else {
                    class_0_2.setImageDrawable(null);
                    ischeck[0][2] = 0;
                }
                break;
            case R.id.bt_class_0_3:
                if (ischeck[0][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_3.startAnimation(alphaAnimation);
                    class_0_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][3] = 1;
                } else {
                    class_0_3.setImageDrawable(null);
                    ischeck[0][3] = 0;
                }
                break;
            case R.id.bt_class_0_4:
                if (ischeck[0][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_4.startAnimation(alphaAnimation);
                    class_0_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][4] = 1;
                } else {
                    class_0_4.setImageDrawable(null);
                    ischeck[0][4] = 0;
                }
                break;
            case R.id.bt_class_0_5:
                if (ischeck[0][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_5.startAnimation(alphaAnimation);
                    class_0_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][5] = 1;
                } else {
                    class_0_5.setImageDrawable(null);
                    ischeck[0][5] = 0;
                }
                break;
            case R.id.bt_class_0_6:
                if (ischeck[0][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_6.startAnimation(alphaAnimation);
                    class_0_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][6] = 1;
                } else {
                    class_0_6.setImageDrawable(null);
                    ischeck[0][6] = 0;
                }
                break;
            case R.id.bt_class_0_7:
                if (ischeck[0][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_7.startAnimation(alphaAnimation);
                    class_0_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][7] = 1;
                } else {
                    class_0_7.setImageDrawable(null);
                    ischeck[0][7] = 0;
                }
                break;
            case R.id.bt_class_0_8:
                if (ischeck[0][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_8.startAnimation(alphaAnimation);
                    class_0_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][8] = 1;
                } else {
                    class_0_8.setImageDrawable(null);
                    ischeck[0][8] = 0;
                }
                break;
            case R.id.bt_class_0_9:
                if (ischeck[0][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_9.startAnimation(alphaAnimation);
                    class_0_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][9] = 1;
                } else {
                    class_0_9.setImageDrawable(null);
                    ischeck[0][9] = 0;
                }
                break;
            case R.id.bt_class_0_10:
                if (ischeck[0][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_10.startAnimation(alphaAnimation);
                    class_0_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][10] = 1;
                } else {
                    class_0_10.setImageDrawable(null);
                    ischeck[0][10] = 0;
                }
                break;
            case R.id.bt_class_0_11:
                if (ischeck[0][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_0_11.startAnimation(alphaAnimation);
                    class_0_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[0][11] = 1;
                } else {
                    class_0_11.setImageDrawable(null);
                    ischeck[0][11] = 0;
                }
                break;

            case R.id.bt_class_1_0:
                if (ischeck[1][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_0.startAnimation(alphaAnimation);
                    class_1_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][0] = 1;
                } else {
                    class_1_0.setImageDrawable(null);
                    ischeck[1][0] = 0;
                }
                break;
            case R.id.bt_class_1_1:
                if (ischeck[1][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_1.startAnimation(alphaAnimation);
                    class_1_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][1] = 1;
                } else {
                    class_1_1.setImageDrawable(null);
                    ischeck[1][1] = 0;
                }
                break;
            case R.id.bt_class_1_2:
                if (ischeck[1][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_2.startAnimation(alphaAnimation);
                    class_1_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][2] = 1;
                } else {
                    class_1_2.setImageDrawable(null);
                    ischeck[1][2] = 0;
                }
                break;
            case R.id.bt_class_1_3:
                if (ischeck[1][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_3.startAnimation(alphaAnimation);
                    class_1_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][3] = 1;
                } else {
                    class_1_3.setImageDrawable(null);
                    ischeck[1][3] = 0;
                }
                break;
            case R.id.bt_class_1_4:
                if (ischeck[1][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_4.startAnimation(alphaAnimation);
                    class_1_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][4] = 1;
                } else {
                    class_1_4.setImageDrawable(null);
                    ischeck[1][4] = 0;
                }
                break;
            case R.id.bt_class_1_5:
                if (ischeck[1][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_5.startAnimation(alphaAnimation);
                    class_1_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][5] = 1;
                } else {
                    class_1_5.setImageDrawable(null);
                    ischeck[1][5] = 0;
                }
                break;
            case R.id.bt_class_1_6:
                if (ischeck[1][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_6.startAnimation(alphaAnimation);
                    class_1_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][6] = 1;
                } else {
                    class_1_6.setImageDrawable(null);
                    ischeck[1][6] = 0;
                }
                break;
            case R.id.bt_class_1_7:
                if (ischeck[1][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_7.startAnimation(alphaAnimation);
                    class_1_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][7] = 1;
                } else {
                    class_1_7.setImageDrawable(null);
                    ischeck[1][7] = 0;
                }
                break;
            case R.id.bt_class_1_8:
                if (ischeck[1][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_8.startAnimation(alphaAnimation);
                    class_1_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][8] = 1;
                } else {
                    class_1_8.setImageDrawable(null);
                    ischeck[1][8] = 0;
                }
                break;
            case R.id.bt_class_1_9:
                if (ischeck[1][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_9.startAnimation(alphaAnimation);
                    class_1_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][9] = 1;
                } else {
                    class_1_9.setImageDrawable(null);
                    ischeck[1][9] = 0;
                }
                break;
            case R.id.bt_class_1_10:
                if (ischeck[1][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_10.startAnimation(alphaAnimation);
                    class_1_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][10] = 1;
                } else {
                    class_1_10.setImageDrawable(null);
                    ischeck[1][10] = 0;
                }
                break;
            case R.id.bt_class_1_11:
                if (ischeck[1][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_1_11.startAnimation(alphaAnimation);
                    class_1_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[1][11] = 1;
                } else {
                    class_1_11.setImageDrawable(null);
                    ischeck[1][11] = 0;
                }
                break;

            case R.id.bt_class_2_0:
                if (ischeck[2][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_0.startAnimation(alphaAnimation);
                    class_2_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][0] = 1;
                } else {
                    class_2_0.setImageDrawable(null);
                    ischeck[2][0] = 0;
                }
                break;
            case R.id.bt_class_2_1:
                if (ischeck[2][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_1.startAnimation(alphaAnimation);
                    class_2_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][1] = 1;
                } else {
                    class_2_1.setImageDrawable(null);
                    ischeck[2][1] = 0;
                }
                break;
            case R.id.bt_class_2_2:
                if (ischeck[2][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_2.startAnimation(alphaAnimation);
                    class_2_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][2] = 1;
                } else {
                    class_2_2.setImageDrawable(null);
                    ischeck[2][2] = 0;
                }
                break;
            case R.id.bt_class_2_3:
                if (ischeck[2][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_3.startAnimation(alphaAnimation);
                    class_2_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][3] = 1;
                } else {
                    class_2_3.setImageDrawable(null);
                    ischeck[2][3] = 0;
                }
                break;
            case R.id.bt_class_2_4:
                if (ischeck[2][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_4.startAnimation(alphaAnimation);
                    class_2_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][4] = 1;
                } else {
                    class_2_4.setImageDrawable(null);
                    ischeck[2][4] = 0;
                }
                break;
            case R.id.bt_class_2_5:
                if (ischeck[2][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_5.startAnimation(alphaAnimation);
                    class_2_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][5] = 1;
                } else {
                    class_2_5.setImageDrawable(null);
                    ischeck[2][5] = 0;
                }
                break;
            case R.id.bt_class_2_6:
                if (ischeck[2][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_6.startAnimation(alphaAnimation);
                    class_2_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][6] = 1;
                } else {
                    class_2_6.setImageDrawable(null);
                    ischeck[2][6] = 0;
                }
                break;
            case R.id.bt_class_2_7:
                if (ischeck[2][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_7.startAnimation(alphaAnimation);
                    class_2_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][7] = 1;
                } else {
                    class_2_7.setImageDrawable(null);
                    ischeck[2][7] = 0;
                }
                break;
            case R.id.bt_class_2_8:
                if (ischeck[2][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_8.startAnimation(alphaAnimation);
                    class_2_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][8] = 1;
                } else {
                    class_2_8.setImageDrawable(null);
                    ischeck[2][8] = 0;
                }
                break;
            case R.id.bt_class_2_9:
                if (ischeck[2][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_9.startAnimation(alphaAnimation);
                    class_2_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][9] = 1;
                } else {
                    class_2_9.setImageDrawable(null);
                    ischeck[2][9] = 0;
                }
                break;
            case R.id.bt_class_2_10:
                if (ischeck[2][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_10.startAnimation(alphaAnimation);
                    class_2_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][10] = 1;
                } else {
                    class_2_10.setImageDrawable(null);
                    ischeck[2][10] = 0;
                }
                break;
            case R.id.bt_class_2_11:
                if (ischeck[2][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_2_11.startAnimation(alphaAnimation);
                    class_2_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[2][11] = 1;
                } else {
                    class_2_11.setImageDrawable(null);
                    ischeck[2][11] = 0;
                }
                break;

            case R.id.bt_class_3_0:
                if (ischeck[3][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_0.startAnimation(alphaAnimation);
                    class_3_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][0] = 1;
                } else {
                    class_3_0.setImageDrawable(null);
                    ischeck[3][0] = 0;
                }
                break;
            case R.id.bt_class_3_1:
                if (ischeck[3][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_1.startAnimation(alphaAnimation);
                    class_3_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][1] = 1;
                } else {
                    class_3_1.setImageDrawable(null);
                    ischeck[3][1] = 0;
                }
                break;
            case R.id.bt_class_3_2:
                if (ischeck[3][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_2.startAnimation(alphaAnimation);
                    class_3_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][2] = 1;
                } else {
                    class_3_2.setImageDrawable(null);
                    ischeck[3][2] = 0;
                }
                break;
            case R.id.bt_class_3_3:
                if (ischeck[3][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_3.startAnimation(alphaAnimation);
                    class_3_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][3] = 1;
                } else {
                    class_3_3.setImageDrawable(null);
                    ischeck[3][3] = 0;
                }
                break;
            case R.id.bt_class_3_4:
                if (ischeck[3][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_4.startAnimation(alphaAnimation);
                    class_3_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][4] = 1;
                } else {
                    class_3_4.setImageDrawable(null);
                    ischeck[3][4] = 0;
                }
                break;
            case R.id.bt_class_3_5:
                if (ischeck[3][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_5.startAnimation(alphaAnimation);
                    class_3_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][5] = 1;
                } else {
                    class_3_5.setImageDrawable(null);
                    ischeck[3][5] = 0;
                }
                break;
            case R.id.bt_class_3_6:
                if (ischeck[3][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_6.startAnimation(alphaAnimation);
                    class_3_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][6] = 1;
                } else {
                    class_3_6.setImageDrawable(null);
                    ischeck[3][6] = 0;
                }
                break;
            case R.id.bt_class_3_7:
                if (ischeck[3][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_7.startAnimation(alphaAnimation);
                    class_3_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][7] = 1;
                } else {
                    class_3_7.setImageDrawable(null);
                    ischeck[3][7] = 0;
                }
                break;
            case R.id.bt_class_3_8:
                if (ischeck[3][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_8.startAnimation(alphaAnimation);
                    class_3_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][8] = 1;
                } else {
                    class_3_8.setImageDrawable(null);
                    ischeck[3][8] = 0;
                }
                break;
            case R.id.bt_class_3_9:
                if (ischeck[3][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_9.startAnimation(alphaAnimation);
                    class_3_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][9] = 1;
                } else {
                    class_3_9.setImageDrawable(null);
                    ischeck[3][9] = 0;
                }
                break;
            case R.id.bt_class_3_10:
                if (ischeck[3][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_10.startAnimation(alphaAnimation);
                    class_3_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][10] = 1;
                } else {
                    class_3_10.setImageDrawable(null);
                    ischeck[3][10] = 0;
                }
                break;
            case R.id.bt_class_3_11:
                if (ischeck[3][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_3_11.startAnimation(alphaAnimation);
                    class_3_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[3][11] = 1;
                } else {
                    class_3_11.setImageDrawable(null);
                    ischeck[3][11] = 0;
                }
                break;

            case R.id.bt_class_4_0:
                if (ischeck[4][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_0.startAnimation(alphaAnimation);
                    class_4_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][0] = 1;
                } else {
                    class_4_0.setImageDrawable(null);
                    ischeck[4][0] = 0;
                }
                break;
            case R.id.bt_class_4_1:
                if (ischeck[4][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_1.startAnimation(alphaAnimation);
                    class_4_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][1] = 1;
                } else {
                    class_4_1.setImageDrawable(null);
                    ischeck[4][1] = 0;
                }
                break;
            case R.id.bt_class_4_2:
                if (ischeck[4][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_2.startAnimation(alphaAnimation);
                    class_4_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][2] = 1;
                } else {
                    class_4_2.setImageDrawable(null);
                    ischeck[4][2] = 0;
                }
                break;
            case R.id.bt_class_4_3:
                if (ischeck[4][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_3.startAnimation(alphaAnimation);
                    class_4_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][3] = 1;
                } else {
                    class_4_3.setImageDrawable(null);
                    ischeck[4][3] = 0;
                }
                break;
            case R.id.bt_class_4_4:
                if (ischeck[4][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_4.startAnimation(alphaAnimation);
                    class_4_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][4] = 1;
                } else {
                    class_4_4.setImageDrawable(null);
                    ischeck[4][4] = 0;
                }
                break;
            case R.id.bt_class_4_5:
                if (ischeck[4][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_5.startAnimation(alphaAnimation);
                    class_4_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][5] = 1;
                } else {
                    class_4_5.setImageDrawable(null);
                    ischeck[4][5] = 0;
                }
                break;
            case R.id.bt_class_4_6:
                if (ischeck[4][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_6.startAnimation(alphaAnimation);
                    class_4_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][6] = 1;
                } else {
                    class_4_6.setImageDrawable(null);
                    ischeck[4][6] = 0;
                }
                break;
            case R.id.bt_class_4_7:
                if (ischeck[4][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_7.startAnimation(alphaAnimation);
                    class_4_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][7] = 1;
                } else {
                    class_4_7.setImageDrawable(null);
                    ischeck[4][7] = 0;
                }
                break;
            case R.id.bt_class_4_8:
                if (ischeck[4][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_8.startAnimation(alphaAnimation);
                    class_4_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][8] = 1;
                } else {
                    class_4_8.setImageDrawable(null);
                    ischeck[4][8] = 0;
                }
                break;
            case R.id.bt_class_4_9:
                if (ischeck[4][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_9.startAnimation(alphaAnimation);
                    class_4_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][9] = 1;
                } else {
                    class_4_9.setImageDrawable(null);
                    ischeck[4][9] = 0;
                }
                break;
            case R.id.bt_class_4_10:
                if (ischeck[4][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_10.startAnimation(alphaAnimation);
                    class_4_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][10] = 1;
                } else {
                    class_4_10.setImageDrawable(null);
                    ischeck[4][10] = 0;
                }
                break;
            case R.id.bt_class_4_11:
                if (ischeck[4][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_4_11.startAnimation(alphaAnimation);
                    class_4_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[4][11] = 1;
                } else {
                    class_4_11.setImageDrawable(null);
                    ischeck[4][11] = 0;
                }
                break;

            case R.id.bt_class_5_0:
                if (ischeck[5][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_0.startAnimation(alphaAnimation);
                    class_5_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][0] = 1;
                } else {
                    class_5_0.setImageDrawable(null);
                    ischeck[5][0] = 0;
                }
                break;
            case R.id.bt_class_5_1:
                if (ischeck[5][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_1.startAnimation(alphaAnimation);
                    class_5_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][1] = 1;
                } else {
                    class_5_1.setImageDrawable(null);
                    ischeck[5][1] = 0;
                }
                break;
            case R.id.bt_class_5_2:
                if (ischeck[5][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_2.startAnimation(alphaAnimation);
                    class_5_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][2] = 1;
                } else {
                    class_5_2.setImageDrawable(null);
                    ischeck[5][2] = 0;
                }
                break;
            case R.id.bt_class_5_3:
                if (ischeck[5][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_3.startAnimation(alphaAnimation);
                    class_5_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][3] = 1;
                } else {
                    class_5_3.setImageDrawable(null);
                    ischeck[5][3] = 0;
                }
                break;
            case R.id.bt_class_5_4:
                if (ischeck[5][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_4.startAnimation(alphaAnimation);
                    class_5_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][4] = 1;
                } else {
                    class_5_4.setImageDrawable(null);
                    ischeck[5][4] = 0;
                }
                break;
            case R.id.bt_class_5_5:
                if (ischeck[5][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_5.startAnimation(alphaAnimation);
                    class_5_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][5] = 1;
                } else {
                    class_5_5.setImageDrawable(null);
                    ischeck[5][5] = 0;
                }
                break;
            case R.id.bt_class_5_6:
                if (ischeck[5][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_6.startAnimation(alphaAnimation);
                    class_5_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][6] = 1;
                } else {
                    class_5_6.setImageDrawable(null);
                    ischeck[5][6] = 0;
                }
                break;
            case R.id.bt_class_5_7:
                if (ischeck[5][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_7.startAnimation(alphaAnimation);
                    class_5_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][7] = 1;
                } else {
                    class_5_7.setImageDrawable(null);
                    ischeck[5][7] = 0;
                }
                break;
            case R.id.bt_class_5_8:
                if (ischeck[5][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_8.startAnimation(alphaAnimation);
                    class_5_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][8] = 1;
                } else {
                    class_5_8.setImageDrawable(null);
                    ischeck[5][8] = 0;
                }
                break;
            case R.id.bt_class_5_9:
                if (ischeck[5][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_9.startAnimation(alphaAnimation);
                    class_5_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][9] = 1;
                } else {
                    class_5_9.setImageDrawable(null);
                    ischeck[5][9] = 0;
                }
                break;
            case R.id.bt_class_5_10:
                if (ischeck[5][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_10.startAnimation(alphaAnimation);
                    class_5_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][10] = 1;
                } else {
                    class_5_10.setImageDrawable(null);
                    ischeck[5][10] = 0;
                }
                break;
            case R.id.bt_class_5_11:
                if (ischeck[5][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_5_11.startAnimation(alphaAnimation);
                    class_5_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[5][11] = 1;
                } else {
                    class_5_11.setImageDrawable(null);
                    ischeck[5][11] = 0;
                }
                break;

            case R.id.bt_class_6_0:
                if (ischeck[6][0] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_0.startAnimation(alphaAnimation);
                    class_6_0.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][0] = 1;
                } else {
                    class_6_0.setImageDrawable(null);
                    ischeck[6][0] = 0;
                }
                break;
            case R.id.bt_class_6_1:
                if (ischeck[6][1] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_1.startAnimation(alphaAnimation);
                    class_6_1.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][1] = 1;
                } else {
                    class_6_1.setImageDrawable(null);
                    ischeck[6][1] = 0;
                }
                break;
            case R.id.bt_class_6_2:
                if (ischeck[6][2] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_2.startAnimation(alphaAnimation);
                    class_6_2.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][2] = 1;
                } else {
                    class_6_2.setImageDrawable(null);
                    ischeck[6][2] = 0;
                }
                break;
            case R.id.bt_class_6_3:
                if (ischeck[6][3] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_3.startAnimation(alphaAnimation);
                    class_6_3.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][3] = 1;
                } else {
                    class_6_3.setImageDrawable(null);
                    ischeck[6][3] = 0;
                }
                break;
            case R.id.bt_class_6_4:
                if (ischeck[6][4] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_4.startAnimation(alphaAnimation);
                    class_6_4.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][4] = 1;
                } else {
                    class_6_4.setImageDrawable(null);
                    ischeck[6][4] = 0;
                }
                break;
            case R.id.bt_class_6_5:
                if (ischeck[6][5] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_5.startAnimation(alphaAnimation);
                    class_6_5.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][5] = 1;
                } else {
                    class_6_5.setImageDrawable(null);
                    ischeck[6][5] = 0;
                }
                break;
            case R.id.bt_class_6_6:
                if (ischeck[6][6] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_6.startAnimation(alphaAnimation);
                    class_6_6.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][6] = 1;
                } else {
                    class_6_6.setImageDrawable(null);
                    ischeck[6][6] = 0;
                }
                break;
            case R.id.bt_class_6_7:
                if (ischeck[6][7] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_7.startAnimation(alphaAnimation);
                    class_6_7.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][7] = 1;
                } else {
                    class_6_7.setImageDrawable(null);
                    ischeck[6][7] = 0;
                }
                break;
            case R.id.bt_class_6_8:
                if (ischeck[6][8] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_8.startAnimation(alphaAnimation);
                    class_6_8.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][8] = 1;
                } else {
                    class_6_8.setImageDrawable(null);
                    ischeck[6][8] = 0;
                }
                break;
            case R.id.bt_class_6_9:
                if (ischeck[6][9] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_9.startAnimation(alphaAnimation);
                    class_6_9.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][9] = 1;
                } else {
                    class_6_9.setImageDrawable(null);
                    ischeck[6][9] = 0;
                }
                break;
            case R.id.bt_class_6_10:
                if (ischeck[6][10] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_10.startAnimation(alphaAnimation);
                    class_6_10.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][10] = 1;
                } else {
                    class_6_10.setImageDrawable(null);
                    ischeck[6][10] = 0;
                }
                break;
            case R.id.bt_class_6_11:
                if (ischeck[6][11] == 0) {
                    Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    //设置动画时间
                    alphaAnimation.setDuration(500);
                    class_6_11.startAnimation(alphaAnimation);
                    class_6_11.setImageDrawable(getResources().getDrawable(R.mipmap.check));
                    ischeck[6][11] = 1;
                } else {
                    class_6_11.setImageDrawable(null);
                    ischeck[6][11] = 0;
                }
                break;
        }
    }
}
