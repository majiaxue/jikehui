package com.example.fans_order;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.bean.FansOrderCensusBean;
import com.example.common.CommonResource;
import com.example.fans_order.fragment_all.FansAllOrderFragment;
import com.example.fans_order.fragment_lose.FansLoseOrderFragment;
import com.example.fans_order.fragment_pay.FansPayOrderFragment;
import com.example.fans_order.fragment_settle.FansSettleOrderFragment;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order.adapter.OrderVPAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class FansOrderPresenter extends BasePresenter<FansOrderView> {
    private String[] titleArr = {"全部订单", "已付款", "已结算", "已失效"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private FansAllOrderFragment fansAllOrderFragment;
    private FansLoseOrderFragment fansLoseOrderFragment;
    private FansSettleOrderFragment fansSettleOrderFragment;
    private FansPayOrderFragment fansPayOrderFragment;

    public FansOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        FansOrderActivity.index = 1;
    }

    public void initTabLayout(final TabLayout orderTab) {
        fansAllOrderFragment = FansAllOrderFragment.getInstance();
        fansPayOrderFragment = FansPayOrderFragment.getInstance();
        fansSettleOrderFragment = FansSettleOrderFragment.getInstance();
        fansLoseOrderFragment = FansLoseOrderFragment.getInstance();
        fragmentList.add(fansAllOrderFragment);
        fragmentList.add(fansPayOrderFragment);
        fragmentList.add(fansSettleOrderFragment);
        fragmentList.add(fansLoseOrderFragment);

        orderTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) orderTab.getChildAt(0);

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

    public void initViewPager(FragmentManager fm) {
        OrderVPAdapter vpAdapter = new OrderVPAdapter(fm, fragmentList, titleArr);
        getView().updateVP(vpAdapter);
    }

    public void change(int i, int index) {
        getView().typeChanged(i);

        fansAllOrderFragment.setOrigin();
        fansPayOrderFragment.setOrigin();
        fansSettleOrderFragment.setOrigin();
        fansLoseOrderFragment.setOrigin();
        loadData(index);
    }

    public void loadData(int index) {
        Map map = MapUtil.getInstance().addParms("type", index).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.FANS_TOTAL_MONEY, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("title:" + result);
                FansOrderCensusBean fansOrderCensusBean = JSON.parseObject(result, FansOrderCensusBean.class);
                if (getView() != null) {
                    getView().loadCensus(fansOrderCensusBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));
    }
}
