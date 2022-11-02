package com.zc.takeout.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zc.takeout.bean.Custom;
import com.zc.takeout.bean.Dish;
import com.zc.takeout.db.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomDao {
    private final DataBaseHelper helper;

    public CustomDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void insertCustom(Custom custom) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "insert into custom values(?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{custom.getAccount(), custom.getTel(), custom.getPassword()});
        helper.close();
    }

    public boolean selectCustomByAccountAndPassword(String account, String password) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "select * from custom where account = ? and password = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] {account, password});
           if (cursor.moveToNext()) {
               return true;
           }
           return false;
    }

    public boolean selectCustomByTelAndPassword(String tel, String password) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "select * from custom where tel = ? and password = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] {tel, password});
        if (cursor.moveToNext()) {
            return true;
        }
        return false;
    }
}
