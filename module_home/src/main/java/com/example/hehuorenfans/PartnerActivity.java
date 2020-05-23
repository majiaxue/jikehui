package com.example.hehuorenfans;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.bean.PartnerBean;
import com.example.common.CommonResource;
import com.example.hehuorenfans.adapter.PartnerAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/model_home/PartnerActivity")
public class PartnerActivity extends BaseActivity<PartnerView, PartnerPresenter> implements PartnerView {
    @BindView(R2.id.group_fans_back)
    ImageView groupFansBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.group_fans_edit)
    EditText groupFansEdit;
    @BindView(R2.id.group_fans_search)
    TextView groupFansSearch;
    @BindView(R2.id.group_fans_header)
    ImageView groupFansHeader;
    @BindView(R2.id.group_fans_name)
    TextView groupFansName;
    @BindView(R2.id.group_fans_tuijianren)
    TextView groupFansTuijianren;
    @BindView(R2.id.group_fans_total)
    TextView groupFansTotal;
    @BindView(R2.id.group_fans_zhitui)
    TextView groupFansZhitui;
    @BindView(R2.id.group_fans_xinzeng)
    TextView groupFansXinzeng;
    @BindView(R2.id.hehuoren)
    TextView hehuoren;
    @BindView(R2.id.mendian)
    TextView mendian;
    @BindView(R2.id.group_fans_rv)
    RecyclerView groupFansRv;
    @BindView(R2.id.group_fans_refresh)
    SmartRefreshLayout groupFansRefresh;
    private int totalPage = 1;
    private int page = 1;
    private boolean isSearch = false;
    private boolean isZhitui = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_partner;
    }

    @Override
    public void initData() {
        presenter.getData("0");
        presenter.getDtaList(page,"0");
        //groupFansHeader
        String stringValue = SPUtil.getStringValue(CommonResource.USER_PIC);
        Glide.with(this).load(stringValue).into(groupFansHeader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        groupFansRv.setLayoutManager(layoutManager);
        groupFansRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_7)));
        //设置 Header 为 官方主题 样式
        groupFansRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        groupFansRefresh.setRefreshFooter(new ClassicsFooter(this));
    }

    @Override
    public void initClick() {

        groupFansBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //********************设置上拉刷新下拉加载
//        groupFansRefresh.setOnRefreshListener(new OnRefreshListener() {
////            @Override
////            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
////                page = 1;
////                if (isSearch) {
////                    presenter.loadData(page, groupFansEdit.getText().toString(), isZhitui ? "1" : "0");
////                } else {
////                    presenter.loadData(page, "", isZhitui ? "1" : "0");
////                }
////            }
////        });
////        groupFansRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
////            @Override
////            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
////                page++;
////                if (page <= totalPage) {
////                    if (isSearch) {
////                        presenter.loadData(page, groupFansEdit.getText().toString(), isZhitui ? "1" : "0");
////                    } else {
////                        presenter.loadData(page, "", isZhitui ? "1" : "0");
////                    }
////                } else {
////                    groupFansRefresh.finishLoadMore();
////                }
////            }
////        });

        hehuoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getData("0");
                presenter.getDtaList(page, "0");
                hehuoren.setTextColor(Color.parseColor("#ffffff"));
                mendian.setTextColor(Color.parseColor("#ff6c6b"));
                hehuoren.setBackground(getResources().getDrawable(R.drawable.bg_2_eb665d_left));
                mendian.setBackgroundColor(Color.parseColor("#ffffff"));
//                if (!isZhitui) {
//                    isZhitui = true;
//                    page = 1;
//                    if (isSearch) {
//                        presenter.getDtaList(page, "0");
//                    } else {
//                        presenter.getDtaList(page, "1");
//                    }
//                }
            }
        });
        mendian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getData("1");
                presenter.getDtaList(page, "1");
                mendian.setTextColor(Color.parseColor("#ffffff"));
                hehuoren.setTextColor(Color.parseColor("#ff6c6b"));
                mendian.setBackground(getResources().getDrawable(R.drawable.bg_2_eb665d_left));
                hehuoren.setBackgroundColor(Color.parseColor("#ffffff"));
//                if (isZhitui) {
//                    isZhitui = false;
//                    page = 1;
//                    if (isSearch) {
//                        presenter.getDtaList(page, "0");
//                    } else {
//                        presenter.getDtaList(page, "0");
//                    }
//                }
            }
        });


    }

    @Override
    public PartnerView createView() {
        return this;
    }

    @Override
    public PartnerPresenter createPresenter() {
        return new PartnerPresenter(this);
    }

    @Override
    public void loadPartner(PartnerBean partnerBean) {
        groupFansZhitui.setText(partnerBean.getPartnerFirstNum());
        groupFansXinzeng.setText(partnerBean.getPartnerSecondNum());
        groupFansTotal.setText(partnerBean.getPartnerAllNum());
        groupFansTuijianren.setText(partnerBean.getRecommend());
        groupFansName.setText(partnerBean.getUsername());
    }

    @Override
    public void loadFinish() {

    }

    @Override
    public void loadAdapter(PartnerAdapter adapter) {
        groupFansRv.setAdapter(adapter);
    }
}
