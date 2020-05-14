package com.example.local_store.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.KeyValueBean;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalStoreBean;
import com.example.bean.PopGuiGeBean;
import com.example.bean.TxtAndChooseBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_store.ShoppingRight.RvAdapter;
import com.example.local_store.ShoppingRight.RvHolder;
import com.example.local_store.ShoppingRight.RvListener;
import com.example.local_store.ShoppingRight.ShopOnClickListtener;
import com.example.module_local.R;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.OnChooseSpecsListener;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by yadianna02 on 2018/7/31.
 */

public class ShoppingRightAdapter extends RvAdapter<LocalStoreBean.ListBean> {

    public static List<LocalCartBean.InsideCart> cartBeanList = new ArrayList<>();

    public ShoppingRightAdapter(Context context, List<LocalStoreBean.ListBean> list, RvListener listener, ShopOnClickListtener shopOnClickListtener) {
        super(context, list, listener, shopOnClickListtener);
    }

    public ShoppingRightAdapter() {
    }

    public static void setCartBeanList(List<LocalCartBean.InsideCart> data) {
        cartBeanList = data;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.shopping_right_title : R.layout.rv_shop_right;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isTitle() ? 0 : 1;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ShoppingRightHolder(view, viewType, listener);
    }

    class ShoppingRightHolder extends RvHolder<LocalStoreBean.ListBean> {
        private TextView title;
        private ImageView pic;
        private TextView name;
        private TextView newPrice;
        private TextView oldPrice;
        private ImageView add;
        private ImageView minus;
        private TextView count;

        public ShoppingRightHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    title = itemView.findViewById(R.id.shopping_right_title_txt);
                    break;
                case 1:
                    pic = itemView.findViewById(R.id.rv_shop_right_img);
                    name = itemView.findViewById(R.id.rv_shop_right_name);
                    newPrice = itemView.findViewById(R.id.rv_shop_right_new_price);
                    oldPrice = itemView.findViewById(R.id.rv_shop_right_old_price);
                    add = itemView.findViewById(R.id.rv_shop_add);
                    minus = itemView.findViewById(R.id.rv_shop_right_minus);
                    count = itemView.findViewById(R.id.rv_shop_right_count);
                    break;
            }
        }

        @Override
        public void bindHolder(final LocalStoreBean.ListBean commodity, final int position) {
            int itemViewType = ShoppingRightAdapter.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    title.setText(commodity.getName());
                    break;
                case 1:
                    Glide.with(mContext).load(commodity.getPics()).into(pic);
                    name.setText(commodity.getName());
                    if (TextUtils.isEmpty(commodity.getDiscountPrice()) || "0".equals(commodity.getDiscountPrice())) {
                        newPrice.setText("￥" + commodity.getPrice());
                        oldPrice.setVisibility(View.GONE);
                    } else {
                        oldPrice.setVisibility(View.VISIBLE);
                        newPrice.setText("￥" + commodity.getDiscountPrice());
                        oldPrice.setText("￥" + commodity.getPrice());
                        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                        oldPrice.getPaint().setAntiAlias(true);// 抗锯齿
                    }
                    if (commodity.getCount() > 0) {
                        count.setText(commodity.getCount() + "");
                    }

                    isShow(commodity);

                    pic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View inflate = LayoutInflater.from(mContext).inflate(com.example.module_base.R.layout.pop_choose_specs, null);
                            ImageView img = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_img);
                            final ImageView cancel = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_cancel);
                            TextView name = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_name);
                            final TextView price = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_price);
                            final TextView btn = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_btn);
                            RecyclerView rv = inflate.findViewById(com.example.module_base.R.id.pop_choose_specs_rv);
                            //选中商品的价格
                            final String[] currentPrice = {"0"};

                            Glide.with(mContext).load(commodity.getPics()).into(img);
                            name.setText(commodity.getName());
                            String parameter = commodity.getParameter();
                            final String specification = commodity.getSpecification();
                            if (TextUtils.isEmpty(parameter) && TextUtils.isEmpty(specification)) {
                                LocalCartBean.InsideCart goodsToCartBean = new LocalCartBean.InsideCart(SPUtil.getStringValue(CommonResource.SELLERID), commodity.getId(), SPUtil.getUserCode(), commodity.getCount());
                                goodsToCartBean.setLocalGoodsPic(commodity.getPics());
                                goodsToCartBean.setLocalGoodsName(commodity.getName());
                                if (TextUtils.isEmpty(commodity.getDiscountPrice()) || "0".equals(commodity.getDiscountPrice())) {
                                    goodsToCartBean.setPrice(Double.valueOf(commodity.getPrice()));
                                } else {
                                    goodsToCartBean.setPrice(Double.valueOf(commodity.getDiscountPrice()));
                                }
                                goodsToCartBean.setLocalGoodsSpecification(commodity.getSelectSpec());
                                addGoods(goodsToCartBean, commodity);
                            } else {
                                final List<PopGuiGeBean> dataList = new ArrayList<>();

                                List<KeyValueBean> keyValueBeans = new ArrayList<>();
                                List<KeyValueBean> valueBeans = new ArrayList<>();

                                final List<String> chooseList = new ArrayList<>();
                                if (!TextUtils.isEmpty(parameter)) {
                                    keyValueBeans.addAll(JSON.parseArray(parameter, KeyValueBean.class));
                                }

                                if (!TextUtils.isEmpty(specification)) {
                                    valueBeans.addAll(JSON.parseArray(specification, KeyValueBean.class));
                                }

                                if (valueBeans.size() > 0) {
                                    List<TxtAndChooseBean> contentList = new ArrayList<>();
                                    for (int i = 0; i < valueBeans.size(); i++) {
                                        contentList.add(new TxtAndChooseBean(valueBeans.get(i).getKey(), valueBeans.get(i).getValue(), false));
                                    }
                                    dataList.add(new PopGuiGeBean("规格", contentList));
                                }

                                for (int i = 0; i < keyValueBeans.size(); i++) {
                                    List<TxtAndChooseBean> contentList = new ArrayList<>();
                                    String value = keyValueBeans.get(i).getValue();
                                    contentList.clear();
                                    String[] split = value.split(",");
                                    for (int j = 0; j < split.length; j++) {
                                        contentList.add(new TxtAndChooseBean(split[j], false));
                                    }
                                    dataList.add(new PopGuiGeBean(keyValueBeans.get(i).getKey(), contentList));
                                }

                                for (int i = 0; i < dataList.size(); i++) {
                                    chooseList.add("");
                                }

                                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                                rv.setLayoutManager(layoutManager);
                                LocalChooseSpecsAdapter chooseSpecsAdapter = new LocalChooseSpecsAdapter(mContext, dataList, R.layout.rv_pop_choose_specs, new OnChooseSpecsListener() {
                                    @Override
                                    public void setOnChooseAdapter(final LocalChooseSpecsInsideAdapter adapter, final int index) {
                                        adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(RecyclerView parent, View view, int position2) {
                                                for (int i = 0; i < dataList.size(); i++) {
                                                    if (i == index) {
                                                        for (int j = 0; j < dataList.get(i).getContent().size(); j++) {
                                                            if (j == position2) {
                                                                dataList.get(i).getContent().get(j).setChoose(true);
                                                                chooseList.set(i, dataList.get(i).getContent().get(j).getTitle());
                                                            } else {
                                                                dataList.get(i).getContent().get(j).setChoose(false);
                                                            }
                                                        }
                                                    }
                                                }
                                                adapter.notifyDataSetChanged();

                                                if (index == 0) {
                                                    if (!TextUtils.isEmpty(specification)) {
                                                        price.setText("￥" + dataList.get(index).getContent().get(position2).getPrice());
                                                        currentPrice[0] = dataList.get(index).getContent().get(position2).getPrice();
                                                    } else {
                                                        if (TextUtils.isEmpty(commodity.getDiscountPrice()) || "0".equals(commodity.getDiscountPrice())) {
                                                            price.setText("￥" + commodity.getPrice());
                                                            currentPrice[0] = commodity.getPrice();
                                                        } else {
                                                            price.setText("￥" + commodity.getDiscountPrice());
                                                            currentPrice[0] = commodity.getDiscountPrice();
                                                        }
                                                    }
                                                }

                                            }
                                        });
                                    }
                                });
                                rv.setAdapter(chooseSpecsAdapter);

                                PopUtils.createPop(mContext, inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
                                    @Override
                                    public void setOnPop(final PopupWindow pop) {
                                        cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                pop.dismiss();
                                            }
                                        });

                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                StringBuffer sb = new StringBuffer();
                                                for (int i = 0; i < chooseList.size(); i++) {

                                                    if (i == chooseList.size() - 1) {
                                                        sb.append(chooseList.get(i));
                                                    } else {
                                                        if (i == 0 && !TextUtils.isEmpty(specification)) {
                                                            commodity.setSelectSpec(chooseList.get(i));
                                                        } else {
                                                            sb.append(chooseList.get(i) + "-");
                                                        }
                                                    }
                                                    if (TextUtils.isEmpty(chooseList.get(i))) {
                                                        btn.setEnabled(true);
                                                        Toast.makeText(mContext, "请选择规格", Toast.LENGTH_SHORT).show();
                                                        return;
                                                    }
                                                }
                                                commodity.setSelectName(commodity.getName() + "-" + sb.toString());


                                                boolean hasSame = false;
                                                int tempPosition = 0;
                                                for (int i = 0; i < cartBeanList.size(); i++) {
                                                    if ((commodity.getSelectName() != null && commodity.getSelectName().equals(cartBeanList.get(i).getLocalGoodsName()))
                                                            && (commodity.getSelectSpec() != null && commodity.getSelectSpec().equals(cartBeanList.get(i).getLocalGoodsSpecification()))) {
                                                        hasSame = true;
                                                        tempPosition = i;
                                                        break;
                                                    }
                                                }
                                                if (!hasSame) {
                                                    LocalCartBean.InsideCart goodsToCartBean = new LocalCartBean.InsideCart(SPUtil.getStringValue(CommonResource.SELLERID), commodity.getId(), SPUtil.getUserCode(), commodity.getCount());
                                                    goodsToCartBean.setLocalGoodsPic(commodity.getPics());
                                                    goodsToCartBean.setLocalGoodsName(commodity.getSelectName());
                                                    goodsToCartBean.setPrice(Double.valueOf(currentPrice[0]));

                                                    goodsToCartBean.setLocalGoodsSpecification(commodity.getSelectSpec());
                                                    addGoods(goodsToCartBean, commodity);
                                                } else {
                                                    addGoods(cartBeanList.get(tempPosition), commodity);
                                                }


                                                pop.dismiss();
                                            }
                                        });
                                    }
                                });
                            }


                        }
                    });

                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int temp = 0;
                            int tempPosition = 0;
                            for (int i = 0; i < cartBeanList.size(); i++) {
                                if (commodity.getId().equals(cartBeanList.get(i).getLocalGoodsId())) {
                                    temp++;
                                    tempPosition = i;
                                }
                            }

                            if (temp > 1) {
                                EventBus.getDefault().post(new EventBusBean(CommonResource.MINUS_GOODS, ""));
                            } else if (temp == 1) {
                                minusGoods(cartBeanList.get(tempPosition), commodity);
                                add.setEnabled(false);
                                minus.setEnabled(false);
                            }
                        }
                    });
            }
        }

        private void isShow(LocalStoreBean.ListBean commodity) {
            if (commodity.getCount() > 0) {
                minus.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
            } else {
                minus.setVisibility(View.GONE);
                count.setVisibility(View.GONE);
            }
        }

        private void addGoods(LocalCartBean.InsideCart goodsToCartBean, final LocalStoreBean.ListBean data) {
            String jsonString = JSON.toJSONString(goodsToCartBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_ADD, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("添加商品：" + result);
                    LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                    SPUtil.addParm(CommonResource.LOCAL_SELLER_MANJIAN, localCartBean.getAmount());
                    cartBeanList = localCartBean.getLocalShopcarList();
                    add.setEnabled(true);
                    minus.setEnabled(true);
                    int currentCount = data.getCount();
                    if (currentCount == 0) {
                        minus.setAnimation(getShowAnimation());
                        count.setAnimation(getShowAnimation());
                    }
                    currentCount++;

                    count.setText(currentCount + "");
                    data.setCount(currentCount);
                    isShow(data);
                    notifyDataSetChanged();
                    EventBus.getDefault().post(new EventBusBean(CommonResource.UPCART, JSON.toJSONString(localCartBean.getLocalShopcarList())));
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    add.setEnabled(true);
                    minus.setEnabled(true);
                    Toast.makeText(mContext, "添加失败", Toast.LENGTH_SHORT).show();
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                }
            }));
        }

        private void minusGoods(LocalCartBean.InsideCart goodsToCartBean, final LocalStoreBean.ListBean data) {

            String jsonString = JSON.toJSONString(goodsToCartBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_MINUS, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("去掉商品：" + result);
                    LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                    SPUtil.addParm(CommonResource.LOCAL_SELLER_MANJIAN, localCartBean.getAmount());
                    add.setEnabled(true);
                    minus.setEnabled(true);
                    Integer currentCount = data.getCount();
                    currentCount--;
                    if (currentCount <= 0) {
                        currentCount = 0;
                        minus.setEnabled(false);
                        minus.setAnimation(getHiddenAnimation());
                        count.setAnimation(getHiddenAnimation());
                    }
                    count.setText(currentCount + "");
                    data.setCount(currentCount);
                    isShow(data);
                    notifyDataSetChanged();
                    EventBus.getDefault().post(new EventBusBean(CommonResource.UPCART, JSON.toJSONString(localCartBean.getLocalShopcarList())));
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                    Toast.makeText(mContext, "操作失败", Toast.LENGTH_SHORT).show();
                    add.setEnabled(true);
                    minus.setEnabled(true);
                }
            }));
        }
    }

    //显示减号的动画
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    //隐藏减号的动画
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

}
