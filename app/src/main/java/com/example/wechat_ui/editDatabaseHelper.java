package com.example.wechat_ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 22308 on 2020-12-12.
 */

public class editDatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_MEMORY = "create table memory_item("
            + "time text primary key,"
            + "memory text)";

    private Context mContext;

    public editDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_MEMORY);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }
}
