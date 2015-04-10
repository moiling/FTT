package com.moi.freetimetabletest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.moi.freetimetabletest.fragment.MemberFragment;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.fragment.TimetableFragment;

import java.util.ArrayList;
import java.util.List;


public class TimeTableActivity extends ActionBarActivity implements View.OnClickListener {



    public static void actionStart(Context context, String tableName, int tableId) {
        Intent intent = new Intent(context, TimeTableActivity.class);
        intent.putExtra("table_name", tableName);
        intent.putExtra("table_id", tableId);
        context.startActivity(intent);
    }

    private MaterialMenuDrawable materialMenu;

    private String tableName;

    private TextView tab1;

    private TextView tab2;

    private View color;

    private ViewPager mViewPager;

    private LinearLayout tabLayout;

    protected static View mView1;

    protected static View mView2;

    protected List<View> viewList;

    private List<Fragment> fragList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        tableName = getIntent().getStringExtra("table_name");
        setToolbar();

        init();
    }

    private void init() {

        tab1 = (TextView) findViewById(R.id.tv_1);
        tab1.setOnClickListener(this);
        tab2 = (TextView) findViewById(R.id.tv_2);
        tab2.setOnClickListener(this);

        tab1.setTextColor(getResources().getColor(R.color.white));
        tab2.setTextColor(getResources().getColor(R.color.light_primary_color));

        color = findViewById(R.id.color);

        int width = getScreenWidth(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 2, 10);
        color.setLayoutParams(params);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        tabLayout = (LinearLayout) findViewById(R.id.tabLayout);

        LayoutInflater lf = getLayoutInflater().from(this);
        mView1 = lf.inflate(R.layout.fragment_timetable, null);
        mView2 = lf.inflate(R.layout.fragment_member, null);

        viewList = new ArrayList<View>();
        viewList.add(mView1);
        viewList.add(mView2);

        fragList = new ArrayList<Fragment>();
        fragList.add(new TimetableFragment());
        fragList.add(new MemberFragment());

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragList);
        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(new InternalViewPagerListener());
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        //toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("  FTT    " + tableName);
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
        // Toolbar上最左边按钮的点击事件
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);*/
    }


    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            color.setX((position + positionOffset) *getScreenWidth(getApplicationContext()) / 2);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    tab1.setTextColor(getResources().getColor(R.color.white));
                    tab2.setTextColor(getResources().getColor(R.color.light_primary_color));

                    break;
                case 1:
                    tab1.setTextColor(getResources().getColor(R.color.light_primary_color));
                    tab2.setTextColor(getResources().getColor(R.color.white));

                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        private List<Fragment> fragList;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragList) {
            super(fm);
            this.fragList = fragList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragList.get(position);
        }

        @Override
        public int getCount() {
            return fragList.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_table, menu);
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
            case R.id.tv_1:
                mViewPager.setCurrentItem(0);

                break;
            case R.id.tv_2:
                mViewPager.setCurrentItem(1);

                break;
        }
    }
}
