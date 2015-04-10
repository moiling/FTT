package com.moi.freetimetabletest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by moi on 2015/4/9.
 *
 */
public class MemberDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_MEMBER = "create table member ("
            + "id integer primary key autoincrement, "
            + "tableId interger, "
            + "member_name text)";

    private Context mContext;

    public MemberDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MEMBER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
