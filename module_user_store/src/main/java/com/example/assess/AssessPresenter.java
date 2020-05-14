package com.example.assess;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.assess.adapter.AssessAdapter;
import com.example.assess.adapter.AssessTitleAdapter;
import com.example.bean.AssessBean;
import com.example.bean.AssessTitleBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class AssessPresenter extends BasePresenter<AssessView> {
    private List<AssessBean.RecordsBean> assessList = new ArrayList<>();
    private AssessAdapter assessAdapter;

    public AssessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page, String id) {
        final List<AssessTitleBean> titleList = new ArrayList<>();
        titleList.add(new AssessTitleBean("全部", true));
        titleList.add(new AssessTitleBean("最新", false));
        titleList.add(new AssessTitleBean("有图", false));
        final AssessTitleAdapter titleAdapter = new AssessTitleAdapter(mContext, titleList, R.layout.rv_assess_title);
        if (getView() != null) {
            getView().loadTitle(titleAdapter);
        }
        titleAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < titleList.size(); i++) {
                            if (i == index) {
                                titleList.get(i).setCheck(true);
                            } else {
                                titleList.get(i).setCheck(false);
                            }
                        }
                        titleAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        Map map = MapUtil.getInstance().addParms("current", page).addParms("productId", id).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getData(CommonResource.GETUSERASSESS, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("评论列表：" + result);
                AssessBean assessBean = JSON.parseObject(result, AssessBean.class);
                if (page == 1) {
                    assessList.clear();
                }
                assessList.addAll(assessBean.getRecords());
                assessAdapter = new AssessAdapter(mContext, assessList, R.layout.rv_assess_content);

                assessAdapter.setOnViewIndexClickListener(new MyRecyclerAdapter.OnViewIndexClickListener() {
                    @Override
                    public void viewIndexClick(View view, final List<String> list, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PopUtils.popAssessBigPic(mContext, list, index);
                            }
                        });
                    }
                });
                if (getView() != null) {
                    getView().loadAssess(assessAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
