package com.moi.freetimetabletest.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloat;
import com.gc.materialdesign.widgets.SnackBar;
import com.moi.freetimetabletest.BounceListView;
import com.moi.freetimetabletest.ExitApplication;
import com.moi.freetimetabletest.R;
import com.moi.freetimetabletest.adpter.TableListAdapter;
import com.moi.freetimetabletest.db.MemberDatabaseHelper;
import com.moi.freetimetabletest.db.TableDatabaseHelper;
import com.moi.freetimetabletest.objectclass.Table;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, Toolbar.OnMenuItemClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

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

    private ButtonFloat buttonFloat;

    //数据库

    private TableDatabaseHelper dbHelper;

    private MemberDatabaseHelper memberdbHelper;

    private SQLiteDatabase db;

    private SQLiteDatabase memberdb;

    private ContentValues values;

    private List<Integer> idList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建数据库
        dbHelper = new TableDatabaseHelper(this, "TimeTableStore.db", null, 1);
        dbHelper.getWritableDatabase();
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        // 创建member数据库
        memberdbHelper = new MemberDatabaseHelper(this.getApplicationContext(), "Member.db", null, 1);
        memberdbHelper.getWritableDatabase();
        memberdb = memberdbHelper.getWritableDatabase();

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

        buttonFloat = (ButtonFloat) findViewById(R.id.buttonFloat);
        buttonFloat.setBackgroundColor(getResources().getColor(R.color.accent_color));
        buttonFloat.setOnClickListener(this);
        // 创建listview
        mListView = (BounceListView) findViewById(R.id.list_view);
        adapter = new TableListAdapter(MainActivity.this, R.layout.item_table_list, tableList);

        View titleView = LayoutInflater.from(MainActivity.this).inflate(R.layout.header_table_list, null);
        titleView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                ListView.LayoutParams.WRAP_CONTENT));
        mListView.addHeaderView(titleView, null, false);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
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
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.BURGER, false);
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
        // 把member表也全清空
        memberdb.execSQL("DELETE FROM member");
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
     * 创建
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create:

                createTable();
                buttonFloat.setVisibility(View.VISIBLE);
                //初始化
                Animation alphaAnimation2 = new AlphaAnimation(0.1f, 1.0f);
                //设置动画时间
                alphaAnimation2.setDuration(500);
                buttonFloat.startAnimation(alphaAnimation2);
                break;
            case R.id.bt_cancel:
                createTableLayout.setVisibility(View.GONE);
                showHint();
                inputMethodManager = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                buttonFloat.setVisibility(View.VISIBLE);
                //初始化
                Animation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
                //设置动画时间
                alphaAnimation1.setDuration(500);
                buttonFloat.startAnimation(alphaAnimation1);
                break;
            case R.id.buttonFloat:
                createTableLayout.setVisibility(View.VISIBLE);
                //初始化
                Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                //设置动画时间
                alphaAnimation.setDuration(500);
                relativeLayout.startAnimation(alphaAnimation);
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                Animation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                buttonFloat.startAnimation(rotateAnimation);
                buttonFloat.setVisibility(View.GONE);
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

                /**
                 * 这一段用来删除sqlite中对应的数据
                 * 先用cursor把sqlite中的id一个个给idList,这样我们就获取到了id的顺序
                 * 然后在idList根据position获取id，对应删除就可以了
                 */

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Cursor cursor = db.query("timeTable", null, null, null, null, null, null);
                        if (cursor.moveToFirst()) {
                            do {
                                int id = cursor.getInt(cursor.getColumnIndex("id"));
                                idList.add(id);
                            } while (cursor.moveToNext());
                        }
                        Log.d("---------->",idList.get(position - 1) + "");
                        Log.d("---------->","删除了！");
                        db.delete("timeTable", "id = ?", new String[] { idList.get(position - 1) + "" });

                        // 把对应的member表删除
                        memberdb.delete("member", "tableId = ?", new String[] { idList.get(position - 1) + "" });
                        // 不清空数据全乱了
                        idList.clear();

                    }
                }).start();
                tableList.remove(position - 1);// 这里-1是因为listView加了一个header，原来每个item的position都+1了，所以这里-1补平
                adapter.notifyDataSetChanged();
                showHint();

            }
        });
        snackbar.show();
        return true;
    }

    /**
     * Toolbar item的点击事件（创建item）
     */
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        return true;
    }

    /**
     * 跳转
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Table table = tableList.get(position - 1);
        Cursor cursor = db.query("timeTable", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int tableId = cursor.getInt(cursor.getColumnIndex("id"));
                idList.add(tableId);
            } while (cursor.moveToNext());
        }
        TimeTableActivity.actionStart(view.getContext(), table.getName(), idList.get(position - 1));
        // 不清空数据全乱了
        idList.clear();
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitApplication.getInstance().exit();
    }


}
