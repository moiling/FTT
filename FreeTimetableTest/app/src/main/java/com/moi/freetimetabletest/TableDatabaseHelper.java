package com.moi.freetimetabletest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moi on 2015/4/7.
 *
 */
public class TableDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TIME_TABLE = "create table timeTable ("
            + "id integer primary key autoincrement, "
            + "table_name text)";

    private Context mContext;

    public TableDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
