package com.zc.takeout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.zc.takeout.R;
import com.zc.takeout.bean.Dish;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<Dish> dishes;

    public OrderAdapter(Context context, List<Dish> dishes) {
        this.dishes = dishes;
        this.mContext = context;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return dishes == null ? 0 : dishes.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public Dish getItem(int position) {
        return dishes == null ? null : dishes.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到相应position对应的Item视图，position是当前Item的位置，
     * convertView参数是滚出屏幕的Item的View
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order, null);
            vh.tvDishName = (TextView) convertView.findViewById(R.id.tv_dish_name_order);
            vh.tvDishCount = (TextView) convertView.findViewById(R.id.tv_dish_count_order);
            vh.tvDishTotalMoney = (TextView) convertView.findViewById(R.id.tv_total_money_order);
            vh.ivDishPic = (ImageView) convertView.findViewById(R.id.iv_dish_pic_order);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        Dish dish = dishes.get(position);
        Glide.with(mContext).load("https://s1.imagehub.cc/images/2022/10/31/" + dish.getImage()).into(vh.ivDishPic);
        vh.tvDishName.setText(dish.getName());
        vh.tvDishCount.setText("X" + dish.getCount());
        vh.tvDishTotalMoney.setText("￥" + (dish.getCount() * dish.getPrice()));
        return convertView;
    }
    class ViewHolder {
        TextView tvDishName, tvDishTotalMoney, tvDishCount;
        ImageView ivDishPic;
    }
}
