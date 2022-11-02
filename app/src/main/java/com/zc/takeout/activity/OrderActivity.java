package com.zc.takeout.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zc.takeout.R;
import com.zc.takeout.adapter.OrderAdapter;
import com.zc.takeout.bean.Dish;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private String totalMoney, totalCount;;
    private List<Dish> dishes;
    private ListView lvOrder;
    private TextView tvTotalMoney, tvTotalCount;
    private Button btnGo;
    private ImageView ivCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        //获取MainActivity传过来的数据
        dishes = (List<Dish>) intent.getSerializableExtra("carDish");
        totalCount = intent.getStringExtra("totalCount");
        totalMoney = intent.getStringExtra("totalMoney");
        initView();
        initData();
    }

    //初始化控件
    private void initView() {
        lvOrder = findViewById(R.id.lv_order);
        tvTotalMoney = findViewById(R.id.tv_total_money);
        tvTotalCount = findViewById(R.id.tv_count);
        btnGo = findViewById(R.id.btn_go);
        ivCar = findViewById(R.id.iv_shop_car);
    }

    //初始化空间中的数据
    private void initData() {
        tvTotalMoney.setText("￥" + totalMoney);
        tvTotalCount.setVisibility(View.VISIBLE);
        tvTotalCount.setText(totalCount);
        btnGo.setText("去支付");
        btnGo.setBackgroundResource(R.drawable.btn_go_bg_yellow);
        OrderAdapter adapter = new OrderAdapter(this, dishes);
        lvOrder.setAdapter(adapter);
        ivCar.setImageResource(R.drawable.cart_active);
    }
}