package com.zc.takeout.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zc.takeout.R;
import com.zc.takeout.adapter.CarAdapter;
import com.zc.takeout.adapter.DishAdapter;
import com.zc.takeout.bean.Dish;
import com.zc.takeout.dao.DishDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DishDao dao = new DishDao(this);
    private MHandler mHandler;
    private TextView tvTotalMoney, tvCount, tvClear;
    private Button btnGo;
    ImageView ivShopCar;
    private ListView lvCar, lvDish;
    public static final int MSG_COUNT_OK = 1;
    private int totalCount = 0;                 // 获取购物车中商品的数量
    private double totalMoney = 0.0;            //购物车中菜品的总价格
    private List<Dish> carDish;                 //购物车中的菜品数据
    private RelativeLayout rlCarList;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new MHandler();
        carDish = new ArrayList<>();
        initView();
        initAdapter();
    }

    private void initView() {
        lvDish = findViewById(R.id.lv_dishes);
        lvCar = findViewById(R.id.lv_car);
        rlCarList = findViewById(R.id.rl_car_list);
        tvClear = (TextView) findViewById(R.id.tv_clear);
        tvTotalMoney = findViewById(R.id.tv_total_money);
        tvCount = (TextView) findViewById(R.id.tv_count);
        ivShopCar = (ImageView) findViewById(R.id.iv_shop_car);
        btnGo = findViewById(R.id.btn_go);

        rlCarList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (rlCarList.getVisibility() == View.VISIBLE) {
                    rlCarList.setVisibility(View.GONE);
                }
                return false;
            }
        });

        //设置返回键、去结算按钮、购物车图片、清空购物车按钮的点击监听事件
        btnGo.setOnClickListener(this);
        ivShopCar.setOnClickListener(this);
        tvClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go: //去结算按钮的点击事件
                //跳转到订单界面
                if (totalCount > 0) {
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("carDish", (Serializable) carDish);
                    intent.putExtra("totalMoney", totalMoney + "");
                    intent.putExtra("totalCount", totalCount);
                    //intent.putExtra("distributionCost", bean.getDistributionCost() + "");
                    startActivity(intent);
                }
                break;
            case R.id.iv_shop_car:          //购物车的点击事件
                if (totalCount <= 0) return;
                if (rlCarList != null) {
                    if (rlCarList.getVisibility() == View.VISIBLE) {
                        rlCarList.setVisibility(View.GONE);
                    } else {
                        rlCarList.setVisibility(View.VISIBLE);
                        //创建一个从底部滑出的动画
                        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_bottom_to_top);
                        rlCarList.startAnimation(animation);//将动画加载到购物车列表界面
                    }
                }
                carAdapter.setData(carDish);
                lvCar.setAdapter(carAdapter);
                break;
            case R.id.tv_clear://清空按钮的点击事件
                if (carDish == null) return;
                for (Dish bean : carDish) {
                    bean.setCount(0);//设置购物车中所有菜品的数量为0
                }
                carDish.clear();//清空购物车中的数据
                carAdapter.notifyDataSetChanged();    //更新界面
                totalCount = 0;      //购物车中菜品的数量设置为0
                totalMoney = 0;       //总价格设置为0
                carDataMsg();        //通过Handler更新购物车中菜品的数量和总价格
                break;
        }
    }

    private void initAdapter() {
        List<Dish> allDish = dao.getAllDish();
        DishAdapter adapter = new DishAdapter(this, allDish, new DishAdapter.OnSelectListener() {
            @Override
            public void onSelectAddCar(int position) {
                //点击加入购物车按钮将菜添加到购物车中
                Dish dish = allDish.get(position);
                dish.setCount(dish.getCount() + 1);
                Iterator<Dish> iterator = carDish.iterator();
                while (iterator.hasNext()) {     //遍历购物车中的菜
                    Dish food = iterator.next();
                    if (food.get_id() == dish.get_id()) {//找到购物车中当前菜的Id
                        iterator.remove();         //删除存放的菜
                    }
                }
                carDish.add(dish);
                totalCount += 1;
                totalMoney += dish.getPrice();
                carDataMsg();
            }
        });
        lvDish.setAdapter(adapter);


        carAdapter = new CarAdapter(this, new CarAdapter.OnSelectListener() {
            @Override
            public void onSelectAdd(int position, TextView tv_food_count, TextView tv_food_price) {
                //添加菜品到购物车中
                Dish dish = carDish.get(position);

                dish.setCount(dish.getCount() + 1);//将当前菜品在购物车中的数量设置给菜品对象//获取当前菜品对象
                tv_food_count.setText(dish.getCount() + ""); //设置该菜品在购物车中的数量
                tv_food_price.setText("￥" + (dish.getPrice() * dish.getCount()));//菜品总价格
                totalCount += 1;      //购物车中菜品的总数量+1
                //购物车中菜品的总价格+当前菜品价格
                totalMoney += dish.getPrice();
                carDataMsg();           //将购物车中菜品的总数量和总价格通过Handler传递更新数据
            }
            @Override
            public void onSelectMis(int position, TextView tv_food_count, TextView tv_food_price) {
                Dish dish = carDish.get(position);       //获取当前菜品对象
                if (dish.getCount() == 1) {
                    carDish.remove(dish);
                }else if (dish.getCount() == 0){
                    return;
                }
                dish.setCount(dish.getCount() - 1);//将当前菜品在购物车中的数量设置给菜品对象//获取当前菜品对象
                tv_food_count.setText(dish.getCount() + ""); //设置该菜品在购物车中的数量
                tv_food_price.setText("￥" + (dish.getPrice() * dish.getCount()));//菜品总价格
                totalCount -= 1;      //购物车中菜品的总数量+1
                //购物车中菜品的总价格+当前菜品价格
                totalMoney -= dish.getPrice();
                carDataMsg();
            }
        });
    }

    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_COUNT_OK:
                    Bundle bundle = msg.getData();
                    String count = bundle.getString("totalCount", "");
                    String money = bundle.getString("totalMoney", "");
                    if (bundle != null) {
                        if (Integer.parseInt(count) > 0) {//如果购物车中有菜品
                            ivShopCar.setImageResource(R.drawable.cart_active);
                            tvCount.setVisibility(View.VISIBLE);
                            btnGo.setTextColor(Color.parseColor("#333333"));
                            btnGo.setBackgroundResource(R.drawable.btn_go_bg_yellow);
                            btnGo.getPaint().setFakeBoldText(true);//加粗字体
                            tvTotalMoney.setText("￥" + money);//设置购物车中菜品总价格
                            tvCount.setText(count);        //设置购物车中菜品总数量

                        } else { //如果购物车中没有菜品
                            ivShopCar.setImageResource(R.drawable.cart);
                            tvCount.setVisibility(View.GONE);
                            btnGo.setTextColor(Color.parseColor("#fdfdfd"));
                            btnGo.setBackgroundResource(R.drawable.btn_go_bg);
                            btnGo.getPaint().setFakeBoldText(true);//加粗字体
                            tvTotalMoney.setText("￥" + money);//设置购物车中菜品总价格
                        }
                    }
                    break;
            }
        }
    }
    /**
     * 将购物车中菜品的总数量和总价格通过Handler传递到主线程中
     */
    private void carDataMsg() {
        Message msg = Message.obtain();
        msg.what = MSG_COUNT_OK;
        Bundle bundle = new Bundle();//创建一个Bundler对象
        //将购物车中的菜品数量和价格放入Bundler对象中
        bundle.putString("totalCount", totalCount + "");
        bundle.putString("totalMoney", totalMoney + "");
        msg.setData(bundle);        //将Bundler对象放入Message对象
        mHandler.sendMessage(msg); //将Message对象传递到MHandler类
    }
}