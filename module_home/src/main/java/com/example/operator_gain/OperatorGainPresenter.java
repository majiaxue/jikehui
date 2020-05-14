package com.example.operator_gain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OperatorBean;
import com.example.bean.UserGoodsDetail;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.operator_gain.adapter.OperatorGainBottomAdapter;
import com.example.operator_gain.adapter.OperatorGainFactorAdapter;
import com.example.operator_gain.adapter.OperatorGainQuanyiAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OperatorGainPresenter extends BasePresenter<OperatorGainView> {
    private List<OperatorBean> beanList;
    private List<UserGoodsDetail> goodsBeans;
    private List<View> viewList = new ArrayList<>();

    public OperatorGainPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.GETOPER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运营商：" + result);
                beanList = JSON.parseArray(result, OperatorBean.class);
                initVp();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void initVp() {
        for (int i = 0; i < beanList.size(); i++) {
            List<String> dataList = new ArrayList<>();
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.vp_operator_gain, null);
            ImageView img = view.findViewById(R.id.vp_operator_gain_level);
            RecyclerView factor = view.findViewById(R.id.vp_operator_gain_factor);
            RecyclerView quanyi = view.findViewById(R.id.vp_operator_gain_quanyi);
            if (i == 0) {
                img.setImageResource(R.drawable.text_chuji);
            } else if (i == 1) {
                img.setImageResource(R.drawable.text_zhongji);
            } else if (i == 2) {
                img.setImageResource(R.drawable.text_gaoji);
            }

            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            quanyi.setLayoutManager(layoutManager);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            factor.setLayoutManager(gridLayoutManager);

            quanyi.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) mContext.getResources().getDimension(R.dimen.dp_11)));
            if (i == 0) {
                dataList.add("金牌");
            } else {
                dataList.add(beanList.get(i - 1).getName());
            }
            dataList.add(beanList.get(i).getSharePercent());
            dataList.add(beanList.get(i).getSharePercent());
            dataList.add(beanList.get(i).getPerCashs());
            dataList.add(beanList.get(i).getPerCashs());
            dataList.add(beanList.get(i).getSharePercent());
            OperatorGainQuanyiAdapter rvAdapter = new OperatorGainQuanyiAdapter(mContext, dataList, R.layout.rv_vp_operator_gain);
            quanyi.setAdapter(rvAdapter);

            List<String> factorList = new ArrayList<>();
            if (!TextUtils.isEmpty(beanList.get(i).getSelfOrderNum())) {
                factorList.add("自购订单数量：" + "<font color='#f84f4f'>" + beanList.get(i).getSelfOrderNum() + "</font>单");
            }

            if (!TextUtils.isEmpty(beanList.get(i).getSelfCommission())) {
                factorList.add("累计预估佣金：" + "<font color='#f84f4f'>" + beanList.get(i).getSelfCommission() + "</font>元");
            }

            if (!TextUtils.isEmpty(beanList.get(i).getDirectFansNum())) {
                factorList.add("直属粉丝数量：" + "<font color='#f84f4f'>" + beanList.get(i).getDirectFansNum() + "</font>个");
            }

            if (!TextUtils.isEmpty(beanList.get(i).getIndirectFansNum())) {
                factorList.add("非直推有效粉丝：" + "<font color='#f84f4f'>" + beanList.get(i).getIndirectFansNum() + "</font>人");
            }
            OperatorGainFactorAdapter factorAdapter = new OperatorGainFactorAdapter(mContext, factorList, R.layout.rv_vp_operator_factor);
            factor.setAdapter(factorAdapter);

            viewList.add(view);
        }

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        };

        if (getView() != null) {
            getView().loadVp(pagerAdapter);
        }
    }

    public void loadQuanyi() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.OPERATOR_GOODS);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("商品：" + result);
                try {
                    if (result != null) {
                        goodsBeans = JSON.parseArray(result, UserGoodsDetail.class);
                        OperatorGainBottomAdapter quanyiAdapter = new OperatorGainBottomAdapter(mContext, goodsBeans, R.layout.rv_operator_gain_quanyi);
                        if (getView() != null) {
                            getView().loadQuanyi(quanyiAdapter);
                        }

                        quanyiAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance().build("/module_mine/buy2up").withSerializable("bean", goodsBeans.get(position)).navigation();
//                                Intent intent = new Intent(mContext, Buy2UpActivity.class);
//                                intent.putExtra("bean", goodsBeans.get(position));
//                                mContext.startActivity(intent);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----------" + errorMsg);
            }
        }));
    }
}