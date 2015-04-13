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
            + "tableId integer, "
            + "class_0_0 integer, "
            + "class_0_1 integer, "
            + "class_0_2 integer, "
            + "class_0_3 integer, "
            + "class_0_4 integer, "
            + "class_0_5 integer, "
            + "class_0_6 integer, "
            + "class_0_7 integer, "
            + "class_0_8 integer, "
            + "class_0_9 integer, "
            + "class_0_10 integer, "
            + "class_0_11 integer, "
            + "class_1_0 integer, "
            + "class_1_1 integer, "
            + "class_1_2 integer, "
            + "class_1_3 integer, "
            + "class_1_4 integer, "
            + "class_1_5 integer, "
            + "class_1_6 integer, "
            + "class_1_7 integer, "
            + "class_1_8 integer, "
            + "class_1_9 integer, "
            + "class_1_10 integer, "
            + "class_1_11 integer, "
            + "class_2_0 integer, "
            + "class_2_1 integer, "
            + "class_2_2 integer, "
            + "class_2_3 integer, "
            + "class_2_4 integer, "
            + "class_2_5 integer, "
            + "class_2_6 integer, "
            + "class_2_7 integer, "
            + "class_2_8 integer, "
            + "class_2_9 integer, "
            + "class_2_10 integer, "
            + "class_2_11 integer, "
            + "class_3_0 integer, "
            + "class_3_1 integer, "
            + "class_3_2 integer, "
            + "class_3_3 integer, "
            + "class_3_4 integer, "
            + "class_3_5 integer, "
            + "class_3_6 integer, "
            + "class_3_7 integer, "
            + "class_3_8 integer, "
            + "class_3_9 integer, "
            + "class_3_10 integer, "
            + "class_3_11 integer, "
            + "class_4_0 integer, "
            + "class_4_1 integer, "
            + "class_4_2 integer, "
            + "class_4_3 integer, "
            + "class_4_4 integer, "
            + "class_4_5 integer, "
            + "class_4_6 integer, "
            + "class_4_7 integer, "
            + "class_4_8 integer, "
            + "class_4_9 integer, "
            + "class_4_10 integer, "
            + "class_4_11 integer, "
            + "class_5_0 integer, "
            + "class_5_1 integer, "
            + "class_5_2 integer, "
            + "class_5_3 integer, "
            + "class_5_4 integer, "
            + "class_5_5 integer, "
            + "class_5_6 integer, "
            + "class_5_7 integer, "
            + "class_5_8 integer, "
            + "class_5_9 integer, "
            + "class_5_10 integer, "
            + "class_5_11 integer, "
            + "class_6_0 integer, "
            + "class_6_1 integer, "
            + "class_6_2 integer, "
            + "class_6_3 integer, "
            + "class_6_4 integer, "
            + "class_6_5 integer, "
            + "class_6_6 integer, "
            + "class_6_7 integer, "
            + "class_6_8 integer, "
            + "class_6_9 integer, "
            + "class_6_10 integer, "
            + "class_6_11 integer, "
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
