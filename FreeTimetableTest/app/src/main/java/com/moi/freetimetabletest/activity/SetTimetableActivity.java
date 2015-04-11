package com.moi.freetimetabletest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.moi.freetimetabletest.R;

public class SetTimetableActivity extends ActionBarActivity {

    public static void actionStart(Context context, String memberName, int memberId) {
        Intent intent = new Intent(context, SetTimetableActivity.class);
        intent.putExtra("member_name", memberName);
        intent.putExtra("member_id", memberId);
        context.startActivity(intent);
    }

    private int memberId;

    private String memberName;

    private MaterialMenuDrawable materialMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timetable);

        memberName = getIntent().getStringExtra("member_name");
        memberId = getIntent().getIntExtra("member_id", 0);

        setToolbar();

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
}
