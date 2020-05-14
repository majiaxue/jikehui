package com.example.local_detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.BaseVPAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalShopBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.local_coupon.LocalCouponActivity;
import com.example.local_detail.local_goods.LocalGoodsFragment;
import com.example.local_detail.local_seller.LocalSellerFragment;
import com.example.local_shop.adapter.ManJianAdapter;
import com.example.map_detail.MapDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnAdapterListener;
import com.example.utils.PopUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalDetailPresenter extends BasePresenter<LocalDetailView> {
    private String[] titleArr = {"商品", "商家"};
    private LocalGoodsFragment goodsFragment;
    private LocalSellerFragment sellerFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private ManJianAdapter manJianAdapter;

    public LocalDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTab(final TabLayout tab) {
        tab.addTab(tab.newTab().setText(titleArr[0]));
        tab.addTab(tab.newTab().setText(titleArr[1]));

        tab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) tab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    public void initVp(FragmentManager fm, LocalShopBean bean) {
        goodsFragment = LocalGoodsFragment.getInstance(bean);
        sellerFragment = LocalSellerFragment.getInstance(bean);
        fragmentList.add(goodsFragment);
        fragmentList.add(sellerFragment);

        BaseVPAdapter vpAdapter = new BaseVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }

    public void loadData(List<UserCouponBean> couponList) {
        manJianAdapter = new ManJianAdapter(mContext, couponList, R.layout.rv_local_seller_inside);
        if (getView() != null) {
            getView().loadManJian(manJianAdapter);
        }
    }

    public void lingquan(final List<UserCouponBean> list) {
        if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
            PopUtil.lingquanPop(mContext, list, new OnAdapterListener() {
                @Override
                public void setOnAdapterListener(final PopupWindow popupWindow, final PopLingQuanAdapter adapter) {
                    adapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Map map = MapUtil.getInstance().addParms("couponID", list.get(index).getId()).addParms("userID", SPUtil.getUserCode()).addParms("userNickName", SPUtil.getStringValue(CommonResource.USER_NAME)).build();
                                    Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.LINGCOUPON, map);
                                    RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                        @Override
                                        public void onSuccess(String result, String msg) {
                                            LogUtil.e("领取：" + result);
                                            list.get(index).setHas(true);
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onError(String errorCode, String errorMsg) {
                                            LogUtil.e(errorCode + "------------" + errorMsg);
                                        }
                                    }));
                                }
                            });
                        }
                    });
                }
            });
        } else {
            PopUtils.isLogin(mContext);
        }
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + CommonResource.SERVICE_PHONE);
        intent.setData(data);
        mContext.startActivity(intent);
    }

    public void jumpToCoupon() {
        if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
            Intent intent = new Intent(mContext, LocalCouponActivity.class);
            mContext.startActivity(intent);
        } else {
            PopUtils.isLogin(mContext);
        }
    }

    public void jumpToMap(LocalShopBean bean) {
        if (bean.getSeller_lat() != null && bean.getSeller_lon() != null) {
            Intent intent = new Intent(mContext, MapDetailActivity.class);
            intent.putExtra("bean", bean);
            mContext.startActivity(intent);
        } else {
            Toast.makeText(mContext, "商家未设置地图信息", Toast.LENGTH_SHORT).show();
        }
    }
}
