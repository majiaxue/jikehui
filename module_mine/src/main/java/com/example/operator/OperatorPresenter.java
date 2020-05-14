package com.example.operator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OperatorBean;
import com.example.bean.UserGoodsDetail;
import com.example.buy2up.Buy2UpActivity;
import com.example.common.CommonResource;
import com.example.invite_friends.InviteFriendsActivity;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.operator.adapter.OperatorVpRvAdapter;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.operator.adapter.YysQuanyiAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OperatorPresenter extends BasePresenter<OperatorView> {

    private YysFactorAdapter factorAdapter;
    private List<OperatorBean> beanList;
    private int sort;
    private int clickPosition = 0;
    private List<UserGoodsDetail> goodsBeans;
    private List<View> viewList = new ArrayList<>();
    private OperatorVpRvAdapter rvAdapter;

    public OperatorPresenter(Context context) {
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
                getFactor(0);

                factorAdapter = new YysFactorAdapter(mContext, beanList, R.layout.rv_yys_factor);

                factorAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
                    @Override
                    public void ViewTwoOnClick(View view1, View view2, final int index) {
                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                upJustNow("0", index);
                            }
                        });

                        view2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                judge();
                                if (sort >= beanList.get(index).getSort()) {
                                    Toast.makeText(mContext, "请勿重复升级", Toast.LENGTH_SHORT).show();
                                } else {
                                    ARouter.getInstance().build("/module_mine/up_pay")
                                            .withString("money", beanList.get(index).getPrice())
                                            .withString("name", beanList.get(index).getName())
                                            .withString("type", "operator")
                                            .withString("levelId", beanList.get(index).getId())
                                            .navigation();
                                }
                            }
                        });
                    }
                });
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
            View view = inflater.inflate(R.layout.vp_operator, null);
            ImageView img = view.findViewById(R.id.vp_operator_level);
            RecyclerView rv = view.findViewById(R.id.vp_operator_rv);
            if (i == 0) {
                img.setImageResource(R.drawable.vip_chuji);
            } else if (i == 1) {
                img.setImageResource(R.drawable.vip_zhongji);
            } else if (i == 2) {
                img.setImageResource(R.drawable.vip_gaoji);
            }

            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            rv.setLayoutManager(layoutManager);
            rv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) mContext.getResources().getDimension(R.dimen.dp_11)));
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
            rvAdapter = new OperatorVpRvAdapter(mContext, dataList, R.layout.rv_vp_operator);
            rv.setAdapter(rvAdapter);

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

    public void getFactor(int position) {
        StringBuffer str = new StringBuffer();
        if (beanList.get(position).getSelfOrderNum() != null) {
            str.append("· 自购订单数量：" + beanList.get(position).getSelfOrderNum() + "单\n");
        }
        if (beanList.get(position).getDirectFansNum() != null) {
            str.append("· 邀请直属粉丝数量：" + beanList.get(position).getDirectFansNum() + "个\n");
        }
        if (beanList.get(position).getSelfCommission() != null) {
            str.append("· 累计预估佣金：" + beanList.get(position).getSelfCommission() + "元\n");
        }
        if (beanList.get(position).getIndirectFansNum() != null) {
            str.append("· 非直推有效粉丝：" + beanList.get(position).getIndirectFansNum() + "人\n");
        }
        if (beanList.get(position).getRecommendNum() != null) {
            str.append("· 推荐运营商：" + beanList.get(position).getRecommendNum() + "个\n");
        }
        if (getView() != null) {
            getView().loadFactor(str.length() == 0 ? "" : str.toString().substring(0, str.length() - 1));
        }
    }

    public void inviteFans() {
        mContext.startActivity(new Intent(mContext, InviteFriendsActivity.class));
    }

    private void judge() {
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getId().equals(SPUtil.getStringValue(CommonResource.LEVELID))) {
                sort = beanList.get(i).getSort();
                break;
            }
        }
    }

    /**
     * @param flag     点击按钮   0:立即升级   1:支付(前端用)
     * @param position
     */
    private void upJustNow(final String flag, final int position) {
        Map map = MapUtil.getInstance().addParms("levelId", beanList.get(position).getId()).addParms("payType", flag).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.UP_JUSTNOW, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("升级运营商：" + result);
                PopUtils.popUpSuccess(mContext, beanList.get(clickPosition), new OnClearCacheListener() {
                    @Override
                    public void setOnClearCache(final PopupWindow pop, View confirm) {
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pop.dismiss();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("error:" + errorCode + "------------------" + errorMsg);
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
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
                        YysQuanyiAdapter quanyiAdapter = new YysQuanyiAdapter(mContext, goodsBeans, R.layout.rv_yys_quanyi);
                        LogUtil.e("价钱------------"+goodsBeans.get(0).getPrice());
                        if (getView() != null) {
                            getView().loadQuanyi(quanyiAdapter);
                        }

                        quanyiAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                Intent intent = new Intent(mContext, Buy2UpActivity.class);
                                intent.putExtra("bean", goodsBeans.get(position));
                                mContext.startActivity(intent);
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
