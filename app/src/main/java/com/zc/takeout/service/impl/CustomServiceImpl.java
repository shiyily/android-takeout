package com.zc.takeout.service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zc.takeout.bean.Custom;
import com.zc.takeout.dao.CustomDao;
import com.zc.takeout.db.DataBaseHelper;
import com.zc.takeout.service.CustomService;

public class CustomServiceImpl implements CustomService {


    private CustomDao dao ;

    public CustomServiceImpl(Context context) {
        dao = new CustomDao(context);
    }

    @Override
    public boolean login(String username, String password) {
        if (dao.selectCustomByAccountAndPassword(username, password) || dao.selectCustomByTelAndPassword(username, password)){
            return true;
        }

        return false;
    }

    @Override
    public void register(Custom custom) {
        dao.insertCustom(custom);
    }
}
