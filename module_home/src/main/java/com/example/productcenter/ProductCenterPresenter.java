package com.example.productcenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.ProductCenterBean;
import com.example.bean.ProductCenterClassBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.productcenter.adapter.ProductCenterAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ProductCenterPresenter extends BasePresenter<ProductCenterView> {

    //    private String[] attr = {"淘客", "刷脸支付", "信用卡产品"};
    private List<ProductCenterClassBean> classBeanList;
    private List<ProductCenterBean.RecordsBean> recordsBeanList = new ArrayList<>();
    private ProductCenterAdapter productCenterAdapter;

    private int nextPage = 1;

    public ProductCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTab(final TabLayout productCenterTab, SmartRefreshLayout productCenterSmart) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.PRODUCTCENTERCATEGORY);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("分类" + result);
                if (result != null) {
                    classBeanList = JSON.parseArray(result, ProductCenterClassBean.class);
                    for (int i = 0; i < classBeanList.size(); i++) {
                        productCenterTab.addTab(productCenterTab.newTab().setText(classBeanList.get(i).getName()));
                    }

                    productCenterGoods(classBeanList.get(productCenterTab.getSelectedTabPosition()).getId(), 1);

                    productCenterTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            productCenterGoods(classBeanList.get(tab.getPosition()).getId(), 1);
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("分类失败" + errorMsg);
            }
        }));

        productCenterSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                nextPage = 1;
                productCenterGoods(classBeanList.get(productCenterTab.getSelectedTabPosition()).getId(), nextPage);
            }
        });
        productCenterSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                nextPage++;
                productCenterGoods(classBeanList.get(productCenterTab.getSelectedTabPosition()).getId(), nextPage);
            }
        });
    }

    public void productCenterGoods(int categoryId, final int nextPage) {
        Map map = MapUtil.getInstance().addParms("current", nextPage).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.PRODUCTCENTER + categoryId, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("产品中心商品" + result);
                getView().loadRefresh();
                if (result != null) {
                    final ProductCenterBean productCenterBean = JSON.parseObject(result, ProductCenterBean.class);
                    if (1 == nextPage) {
                        recordsBeanList.clear();
                    }
                    recordsBeanList.addAll(productCenterBean.getRecords());
                    if (productCenterAdapter == null) {
                        productCenterAdapter = new ProductCenterAdapter(mContext, recordsBeanList, R.layout.item_product_center_rec);
                        if (getView() != null) {
                            getView().loadAdapter(productCenterAdapter);
                        }
                    } else {
                        productCenterAdapter.notifyDataSetChanged();
                    }

                    productCenterAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/module_home/ProductDetailActivity")
                                    .withSerializable("bean", productCenterBean.getRecords().get(position))
                                    .navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("产品中心商品失败" + errorMsg);
                getView().loadRefresh();
            }
        }));

    }

}
