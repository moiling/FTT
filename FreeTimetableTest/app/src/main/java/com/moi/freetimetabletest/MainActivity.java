package com.moi.freetimetabletest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.widgets.SnackBar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener, AdapterView.OnItemLongClickListener {

    private MaterialMenuDrawable materialMenu;

    private BounceListView mListView;

    private List<Table> tableList = new ArrayList<Table>();

    private TableListAdapter adapter;

    private TextView textViewHint;

    private SnackBar snackbar;

    private String tableName;

    private LinearLayout createTableLayout;

    private EditText editText;

    private ButtonFlat createButton;

    private ButtonFlat cancelButton;

    private InputMethodManager inputMethodManager;

    private Animation animation;

    private LayoutAnimationController controller;

    private RelativeLayout relativeLayout;

    //数据库

    private TableDatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建数据库
        dbHelper = new TableDatabaseHelper(this, "TimeTableStore.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        // 绑定list
        Cursor cursor = db.query("timeTable", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("table_name"));
                Table table = new Table(name);

                tableList.add(table);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // 设置Toolbar
        setToolbar();
        // 为了一口气关掉活动
        ExitApplication.getInstance().addActivity(this);
        // 初始化控件
        init();

    }

    /**
     * 初始化控件
     */
    private void init() {
        // 当listview中没有item的时候显示的背景提示
        textViewHint = (TextView) findViewById(R.id.tv_hint_add_table);
        // 判断提示是否出现（当list为空时）
        showHint();

        // 创建listview
        mListView = (BounceListView) findViewById(R.id.list_view);
        adapter = new TableListAdapter(MainActivity.this, R.layout.item_table_list, tableList);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(this);

        createTableLayout = (LinearLayout) findViewById(R.id.ll_create);
        createTableLayout.setVisibility(View.GONE);
        editText = (EditText) findViewById(R.id.ed_table);
        createButton = (ButtonFlat) findViewById(R.id.bt_create);
        createButton.setBackgroundColor(getResources().getColor(R.color.white));
        cancelButton = (ButtonFlat) findViewById(R.id.bt_cancel);
        cancelButton.setBackgroundColor(getResources().getColor(R.color.white));
        createButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);

        relativeLayout = (RelativeLayout) findViewById(R.id.rl_create);


        View titleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.header_table_list, null);
        titleView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                ListView.LayoutParams.WRAP_CONTENT));
        mListView.addHeaderView(titleView);

    }

    /**
     * 判断是否显示提示……
     */
    private void showHint() {
        if (tableList.isEmpty()) {
            textViewHint.setVisibility(View.VISIBLE);
        } else {
            textViewHint.setVisibility(View.GONE);
        }
    }

    /**
     * 设置Toolbar
     * 内有点击清空table
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // App Logo
        //toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("  FTT");
        // Sub Title
        //toolbar.setSubtitle("  Choose Timetable");
        setSupportActionBar(toolbar);
        // Toolbar上最左边按钮的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                // Handle your drawable state here
                /**
                 * 这一大串switch：
                 * 默认初始式样为≡：1、当list中没有item时：≡→√→≡（循环没有作用）
                 *                                         ┌→当snackBar消失后点击────────────────────┐
                 *                                         │                                             ↓
                 *                2、当list中有item时：≡→×→√（所有item抖动）→当snackBar存在时点击┐        ≡（循环）
                 *                                      │                                      ↓         ↑
                 *                                      └→弹出snackBar→Yes───────────→清空items→判断显示背景hint
                 */
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
                                // 清空table
                                clearTables();
                                materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER, false);
                            }
                        });
                        snackbar.show();
                        materialMenu.animateIconState(MaterialMenuDrawable.IconState.CHECK, false);
                        break;
                    case CHECK:
                        if (!tableList.isEmpty()) {
                            if (snackbar.isShowing()) {
                                // 清空table
                                clearTables();
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
        toolbar.setOnMenuItemClickListener(this);
    }

    /**
     * 清空所有table
     */
    private void clearTables() {
        tableList.clear();
        // 清空数据库
        db.execSQL("DELETE FROM timeTable");
        showHint();
        adapter.notifyDataSetChanged();
    }

    /**
     * 新建一个table
     */
    private void createTable() {
        tableName = editText.getText().toString();
        //数据库
        values.clear();
        values.put("table_name", tableName);
        db.insert("timeTable", null, values);

        Table table = new Table(tableName);
        tableList.add(table);
        adapter.notifyDataSetChanged();
        createTableLayout.setVisibility(View.GONE);
        editText.setText("");
        inputMethodManager = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        showHint();
    }

    /**
     * 创建table
     * 取消创建
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create:

                createTable();

                break;
            case R.id.bt_cancel:
                createTableLayout.setVisibility(View.GONE);
                showHint();
                inputMethodManager = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;

        }

    }

    /**
     *  item的长按事件（删除item）
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Animation shake = AnimationUtils.loadAnimation(view.getContext(),R.anim.shake);
        view.startAnimation(shake);
        SnackBar snackbar = new SnackBar((Activity) view.getContext(), "Delete this item?", "Yes", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableList.remove(position - 1);// 这里-1是因为listView加了一个header，原来每个item的position都+1了，所以这里-1补平

                showHint();
                adapter.notifyDataSetChanged();
            }
        });
        snackbar.show();
        return true;
    }

    /**
     * Toolbar item的点击事件（创建table）
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //创建一个新的list item（这里还没做好，不能和保存到数据库）
            case R.id.action_edit:
                if (createTableLayout.getVisibility() == View.GONE) {
                    createTableLayout.setVisibility(View.VISIBLE);
                    animation = new AlphaAnimation(0,1);
                    animation.setDuration(500);
                    //animationSet.addAnimation(animation);
                    controller = new LayoutAnimationController(animation,1);
                    relativeLayout.setLayoutAnimation(controller);
                    //textViewHint.setVisibility(View.GONE);
                } else {
                    createTable();
                }
                break;
        }
        return true;
    }



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
