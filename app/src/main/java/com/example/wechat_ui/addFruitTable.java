package com.example.wechat_ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class addFruitTable extends SQLiteOpenHelper {

    public static final String CREATE_MEMORY = "create table fruit_item("
            + "time text primary key,"
            + "memory text)";

    private Context mContext;


    public addFruitTable(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_MEMORY);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }
}