package com.example.wechat_ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.*;
import androidx.annotation.Nullable;

public class Student extends SQLiteOpenHelper {

    public Student(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=sa;user=sa;password=1234";
        try {
            Connection con = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String sql = "CREATE TABLE stu(s_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),age INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}