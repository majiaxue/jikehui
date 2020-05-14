package com.example.local_store;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_store.adapter.LocalStoreCommendAdapter;
import com.example.local_store.adapter.PopLocalCartAdapter;
import com.example.local_store.adapter.ShoppingRightAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LocalStorePresenter extends BasePresenter<LocalStoreView> {
    private int flag = 0;
    private List<LocalCartBean.InsideCart> localCartBeans;
    private List<LocalStoreBean> localStoreBeans;
    private List<LocalStoreBean.ListBean> recommendList = new ArrayList<>();

    private int temp1 = 0;
    private int temp2 = 0;
    private LocalStoreCommendAdapter commendAdapter;
    private PopLocalCartAdapter popLocalCartAdapter;


    public LocalStorePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
        SPUtil.addParm(CommonResource.SELLERID, "");
        SPUtil.addParm(CommonResource.SELLERNAME, "");
    }

    public void loadData(final String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_SHOP + id);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("店铺：" + result);
                flag++;
                localStoreBeans = JSON.parseArray(result, LocalStoreBean.class);

                if (flag == 2) {
                    ShoppingRightAdapter.setCartBeanList(localCartBeans);
                    relevance();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (temp1 < 3) {
                    loadData(id);
                    temp1++;
                }
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }

    public void loadCart(final String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_CART + id + "/" + SPUtil.getUserCode());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("购物车：" + result);
                flag++;
                LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                EventBus.getDefault().post(new EventBusBean(CommonResource.UPCART, JSON.toJSONString(localCartBean.getLocalShopcarList())));
                LocalCartBean cartBean = JSON.parseObject(result, LocalCartBean.class);
                localCartBeans = cartBean.getLocalShopcarList();
                if (flag == 2) {
                    ShoppingRightAdapter.setCartBeanList(localCartBeans);
                    relevance();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (temp2 < 3) {
                    loadCart(id);
                    temp2++;
                }
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void relevance() {
        for (int i = 0; i < localStoreBeans.size(); i++) {
            for (int j = 0; j < localStoreBeans.get(i).getList().size(); j++) {
                int temp = 0;
                for (int k = 0; k < localCartBeans.size(); k++) {
                    if (localStoreBeans.get(i).getList().get(j).getId().equals(localCartBeans.get(k).getLocalGoodsId())) {
                        temp += Integer.valueOf(localCartBeans.get(k).getNum());
                    }
                }
                localStoreBeans.get(i).getList().get(j).setCount(temp);
            }
        }

        if (getView() != null) {
            getView().loadData(localStoreBeans);
        }
    }

    public void upCart(String msg) {

        localCartBeans = JSON.parseArray(msg, LocalCartBean.InsideCart.class);
        double money = 0.0;
        for (int i = 0; i < localCartBeans.size(); i++) {
            money += (localCartBeans.get(i).getPrice() * localCartBeans.get(i).getNum());
        }
        getView().upMoney(ArithUtil.exact(money, 2), localCartBeans.size());
    }

    public void upCart2() {
        double money = 0.0;
        for (int i = 0; i < localCartBeans.size(); i++) {
            money += (localCartBeans.get(i).getPrice() * localCartBeans.get(i).getNum());
        }
        getView().upMoney(ArithUtil.exact(money, 2), localCartBeans.size());
    }

    public void cartPop(LinearLayout linear) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_local_cart, null);
        RecyclerView rv = view.findViewById(R.id.pop_local_cart_rv);
        RelativeLayout parent = view.findViewById(R.id.pop_local_cart_parent);

        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(com.example.user_store.R.style.pop_bottom_anim);

        popupWindow.showAtLocation(linear, Gravity.TOP, 0, 0);

        PopUtils.setTransparency(mContext, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(mContext, 1f);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        popLocalCartAdapter = new PopLocalCartAdapter(mContext, localCartBeans, R.layout.rv_pop_shopcart);
        rv.setAdapter(popLocalCartAdapter);

        popLocalCartAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                //减少
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        minusGoods(position, popupWindow);
                    }
                });
                //增加
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addGoods(position, popupWindow);
                    }
                });
            }
        });
    }

    private void addGoods(int position, final PopupWindow popupWindow) {
        ProcessDialogUtil.showProcessDialog(mContext);
        String jsonString = JSON.toJSONString(localCartBeans.get(position));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_ADD, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("添加商品：" + result);
                LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                SPUtil.addParm(CommonResource.LOCAL_SELLER_MANJIAN, localCartBean.getAmount());
                localCartBeans.clear();
                localCartBeans.addAll(localCartBean.getLocalShopcarList());
                popLocalCartAdapter.notifyDataSetChanged();
                relevance();
                upCart2();
                if (localCartBeans.size() == 0) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "操作失败", Toast.LENGTH_SHORT).show();
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void minusGoods(int position, final PopupWindow popupWindow) {
        ProcessDialogUtil.showProcessDialog(mContext);
        String jsonString = JSON.toJSONString(localCartBeans.get(position));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_MINUS, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("去掉商品：" + result);
                LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                SPUtil.addParm(CommonResource.LOCAL_SELLER_MANJIAN, localCartBean.getAmount());
                localCartBeans.clear();
                localCartBeans.addAll(localCartBean.getLocalShopcarList());
                popLocalCartAdapter.notifyDataSetChanged();
                relevance();
                upCart2();
                if (localCartBeans.size() == 0) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
                Toast.makeText(mContext, "操作失败", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
