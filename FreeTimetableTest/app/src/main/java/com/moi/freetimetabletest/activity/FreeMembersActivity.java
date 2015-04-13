package com.moi.freetimetabletest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.moi.freetimetabletest.R;

public class FreeMembersActivity extends ActionBarActivity {

    private MaterialMenuDrawable materialMenu;

    private TextView textView;

    private String message;

    private String tableName;

    private TextView time;

    private int tableId;

    private int line;

    private int row;

    private String week = null;
    public static void actionStart(Context context, String message,  int tableId, String tableName, int line, int row) {
        Intent intent = new Intent(context, FreeMembersActivity.class);
        intent.putExtra("message", message);
        intent.putExtra("table_id", tableId);
        intent.putExtra("table_name", tableName);
        intent.putExtra("line", line);
        intent.putExtra("row", row);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_members);
        setToolbar();

        message = getIntent().getStringExtra("message");
        tableId = getIntent().getIntExtra("table_id", 0);
        tableName = getIntent().getStringExtra("table_name");
        line = getIntent().getIntExtra("line", 0);
        row = getIntent().getIntExtra("row", 0);

        textView = (TextView) findViewById(R.id.tv);
        textView.setText(message);

        time = (TextView) findViewById(R.id.tv_time);

        switch (line) {
            case 0:
                week = "Mon";
                break;
            case 1:
                week = "Tue";
                break;
            case 2:
                week = "Wed";
                break;
            case 3:
                week = "Thu";
                break;
            case 4:
                week = "Fri";
                break;
            case 5:
                week = "Sta";
                break;
            case 6:
                week = "Sun";
                break;
        }

        time.setText(week + " class " + (row + 1));

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        //toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("  Free members");
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
                        TimeTableActivity.actionStart(getApplicationContext(), tableName, tableId);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_free_members, menu);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            TimeTableActivity.actionStart(getApplicationContext(), tableName, tableId);
            finish();
        }
        return true;
    }
}
