package com.zc.takeout.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zc.takeout.bean.Dish;
import com.zc.takeout.db.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DishDao {

    private final DataBaseHelper helper;

    public DishDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    public List<Dish> getDishesByCategory(String _category) {
        List<Dish> dishes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "select * from dish where category = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[] {_category});
        int _id = cursor.getColumnIndex("_id");
        int name = cursor.getColumnIndex("name");
        int price = cursor.getColumnIndex("price");
        int image = cursor.getColumnIndex("image");
        int category = cursor.getColumnIndex("category");
        int description = cursor.getColumnIndex("description");

        while (cursor.moveToNext()) {
            Dish dish = new Dish();
            dish.set_id(cursor.getInt(_id));
            dish.setName(cursor.getString(name));
            dish.setPrice(cursor.getDouble(price));
            dish.setImage(cursor.getString(image));
            dish.setCategory(cursor.getString(category));
            dish.setDescription(cursor.getString(description));
            dishes.add(dish);
        }
        cursor.close();
        sqLiteDatabase.close();
        return dishes;
    }

    public List<String> getAllCategory() {
        List<String> categories = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "select distinct category from dish";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int category = cursor.getColumnIndex("category");
        while (cursor.moveToNext()) {
            categories.add(cursor.getString(category));
        }
        return categories;
    }


    public List<Dish> getAllDish() {
        List<Dish> dishes = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String sql = "select * from dish";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int _id = cursor.getColumnIndex("_id");
        int name = cursor.getColumnIndex("name");
        int price = cursor.getColumnIndex("price");
        int image = cursor.getColumnIndex("image");
        int category = cursor.getColumnIndex("category");
        int description = cursor.getColumnIndex("description");

        while (cursor.moveToNext()) {
            Dish dish = new Dish();
            dish.set_id(cursor.getInt(_id));
            dish.setName(cursor.getString(name));
            dish.setPrice(cursor.getDouble(price));
            dish.setImage(cursor.getString(image));
            dish.setCategory(cursor.getString(category));
            dish.setDescription(cursor.getString(description));
            dishes.add(dish);
        }
        cursor.close();
        sqLiteDatabase.close();
        return dishes;
    }
}
