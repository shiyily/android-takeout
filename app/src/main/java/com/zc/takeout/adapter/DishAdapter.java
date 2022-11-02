package com.zc.takeout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zc.takeout.R;
import com.zc.takeout.bean.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private List<Dish> data = new ArrayList<>();
    private OnSelectListener onSelectListener;

    public DishAdapter(Context context) {
        this.context = context;
    }

    public DishAdapter(Context context, List<Dish> data, OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
        this.context = context;
        this.data = data;
    }

    public void setData(List<Dish> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_dish, null);
            viewHolder = new ViewHolder();
            viewHolder.ivDishPic = view.findViewById(R.id.iv_dish_pic);
            viewHolder.tvDishName = view.findViewById(R.id.tv_dish_name);
            viewHolder.tvDishPrice = view.findViewById(R.id.tv_dish_price);
            viewHolder.tvDishDes = view.findViewById(R.id.tv_dish_des);
            viewHolder.btnAddCar = view.findViewById(R.id.btn_add_car);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Dish dish = data.get(i);
        Glide.with(context).load("https://s1.imagehub.cc/images/2022/10/31/" + dish.getImage()).into(viewHolder.ivDishPic);
        viewHolder.tvDishName.setText(dish.getName());
        viewHolder.tvDishPrice.setText("￥" + dish.getPrice());
        viewHolder.tvDishDes.setText(dish.getDescription());
        viewHolder.btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //加入购物车按钮的点击事件
                onSelectListener.onSelectAddCar(i);
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tvDishName, tvDishPrice, tvDishDes;
        ImageView ivDishPic;
        Button btnAddCar;
    }
    public interface OnSelectListener {
        void onSelectAddCar (int position); //处理加入购物车按钮的方法
    }
}
