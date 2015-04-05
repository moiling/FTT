package com.moi.freetimetabletest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.gc.materialdesign.widgets.SnackBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity{

    private MaterialMenuDrawable materialMenu;

    private ListView mListView;

    private List<Table> tableList = new ArrayList<Table>();

    private TableListAdapter adapter;

    private TextView textViewHint;

    private SnackBar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置Toolbar
        setToolbar();
        // 为了一口气关掉活动
        ExitApplication.getInstance().addActivity(this);
        // 初始化控件
        init();

    }

    private void init() {
        textViewHint = (TextView) findViewById(R.id.tv_hint_add_table);
        if (tableList.isEmpty()) {
            textViewHint.setVisibility(View.VISIBLE);
        } else {
            textViewHint.setVisibility(View.GONE);
        }
        mListView = (ListView) findViewById(R.id.list_view);
        adapter = new TableListAdapter(MainActivity.this, R.layout.item_table_list, tableList);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                Animation shake = AnimationUtils.loadAnimation(view.getContext(),R.anim.shake);
                view.startAnimation(shake);
                SnackBar snackbar = new SnackBar((Activity) view.getContext(), "Delete this item?", "Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tableList.remove(position);
                        if (tableList.isEmpty()) {
                            textViewHint.setVisibility(View.VISIBLE);
                        } else {
                            textViewHint.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                snackbar.show();
                return true;
            }
        });

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // App Logo
        //toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("  FTT");
        // Sub Title
        //toolbar.setSubtitle("  Choose Timetable");
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // Handle your drawable state here
                switch (materialMenu.getIconState()) {
                    case BURGER:
                        if (!tableList.isEmpty()) {
                            materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);
                        } else {
                            materialMenu.animateIconState(MaterialMenuDrawable.IconState.CHECK, false);
                        }
                        break;
                    case ARROW:
                        break;
                    case X:
                        Animation shake = AnimationUtils.loadAnimation(v.getContext(),R.anim.shake);
                        mListView.startAnimation(shake);
                        snackbar = new SnackBar((Activity) v.getContext(), "Clear all items?", "Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tableList.clear();
                                if (tableList.isEmpty()) {
                                    textViewHint.setVisibility(View.VISIBLE);
                                } else {
                                    textViewHint.setVisibility(View.GONE);
                                }
                                adapter.notifyDataSetChanged();
                                materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER, false);
                            }
                        });
                        snackbar.show();
                        materialMenu.animateIconState(MaterialMenuDrawable.IconState.CHECK, false);
                        break;
                    case CHECK:
                        if (!tableList.isEmpty()) {
                            if (snackbar.isShowing()) {
                                tableList.clear();
                                if (tableList.isEmpty()) {
                                    textViewHint.setVisibility(View.VISIBLE);
                                } else {
                                    textViewHint.setVisibility(View.GONE);
                                }
                                adapter.notifyDataSetChanged();
                                snackbar.dismiss();
                            }
                        }
                        materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER, false);
                        break;
                }

            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.setNeverDrawTouch(true);

        // Nacigation Icon 要设定在 setSupportActionBar 之后才有作用
        // 否则会出现 back button
 /*       toolbar.setNavigationIcon(R.mipmap.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click menu", Toast.LENGTH_SHORT).show();
            }
        });*/
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_edit:
                    Table table = new Table("NEW TABLE");
                    tableList.add(table);
                    if (tableList.isEmpty()) {
                        textViewHint.setVisibility(View.VISIBLE);
                    } else {
                        textViewHint.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                    break;
                //case R.id.action_settings:
                    //msg += "Click setting";
                    //break;
            }
            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    protected void onDestroy() {
        super.onDestroy();
        ExitApplication.getInstance().exit();
    }
}
