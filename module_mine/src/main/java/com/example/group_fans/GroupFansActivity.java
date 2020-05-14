package com.example.group_fans;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.GroupFansPeopleBean;
import com.example.common.CommonResource;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
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

@Route(path = "/mine/groupfans")
public class GroupFansActivity extends BaseActivity<GroupFansView, GroupFansPresenter> implements GroupFansView {
    @BindView(R2.id.group_fans_back)
    ImageView includeBack;
    @BindView(R2.id.group_fans_total)
    TextView groupFansTotal;
    @BindView(R2.id.group_fans_edit)
    EditText groupFansEdit;
    @BindView(R2.id.group_fans_search)
    TextView groupFansSearch;
    @BindView(R2.id.group_fans_zhitui)
    TextView groupFansZhitui;
    @BindView(R2.id.group_fans_xinzeng)
    TextView groupFansXinzeng;
    @BindView(R2.id.group_fans_tuijianren)
    TextView groupFansTuijianren;
    @BindView(R2.id.group_fans_rv)
    RecyclerView groupFansRv;
    @BindView(R2.id.group_fans_refresh)
    SmartRefreshLayout groupFansRefresh;
    @BindView(R2.id.group_fans_ztfs)
    TextView mZtfs;
    @BindView(R2.id.group_fans_jtfs)
    TextView mJtfs;
    @BindView(R2.id.group_fans_header)
    ImageView mHeader;
    @BindView(R2.id.group_fans_name)
    TextView mName;

    private int totalPage = 1;
    private int page = 1;
    private boolean isSearch = false;
    private boolean isZhitui = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_group_fans;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        groupFansRv.setLayoutManager(layoutManager);
        groupFansRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_7)));

        mName.setText(SPUtil.getStringValue(CommonResource.USER_NAME));
        Glide.with(this).load(SPUtil.getStringValue(CommonResource.USER_PIC)).placeholder(R.drawable.vhjfg).apply(RequestOptions.circleCropTransform()).into(mHeader);
        presenter.loadData(page, "", "1");
        presenter.loadCount();

        //设置 Header 为 官方主题 样式
        groupFansRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        groupFansRefresh.setRefreshFooter(new ClassicsFooter(this));


    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //********************设置上拉刷新下拉加载
        groupFansRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (isSearch) {
                    presenter.loadData(page, groupFansEdit.getText().toString(), isZhitui ? "1" : "0");
                } else {
                    presenter.loadData(page, "", isZhitui ? "1" : "0");
                }
            }
        });
        groupFansRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (page <= totalPage) {
                    if (isSearch) {
                        presenter.loadData(page, groupFansEdit.getText().toString(), isZhitui ? "1" : "0");
                    } else {
                        presenter.loadData(page, "", isZhitui ? "1" : "0");
                    }
                } else {
                    groupFansRefresh.finishLoadMore();
                }
            }
        });

        groupFansEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    groupFansSearch.setVisibility(View.VISIBLE);
                } else {
                    groupFansSearch.setVisibility(View.INVISIBLE);
                }
            }
        });

        groupFansSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupFansEdit.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                isSearch = true;
                page = 1;
                presenter.loadData(page, groupFansEdit.getText().toString(), isZhitui ? "1" : "0");
            }
        });

        mZtfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mZtfs.setTextColor(Color.parseColor("#ffffff"));
                mJtfs.setTextColor(Color.parseColor("#ff6c6b"));
                mZtfs.setBackground(getResources().getDrawable(R.drawable.bg_2_eb665d_left));
                mJtfs.setBackgroundColor(Color.parseColor("#ffffff"));
                if (!isZhitui) {
                    isZhitui = true;
                    page = 1;
                    if (isSearch) {
                        presenter.loadData(page, groupFansEdit.getText().toString(), "1");
                    } else {
                        presenter.loadData(page, "", "1");
                    }
                }
            }
        });

        mJtfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJtfs.setTextColor(Color.parseColor("#ffffff"));
                mZtfs.setTextColor(Color.parseColor("#ff6c6b"));
                mJtfs.setBackground(getResources().getDrawable(R.drawable.bg_2_eb665d_right));
                mZtfs.setBackgroundColor(Color.parseColor("#ffffff"));
                if (isZhitui) {
                    isZhitui = false;
                    page = 1;
                    if (isSearch) {
                        presenter.loadData(page, groupFansEdit.getText().toString(), "0");
                    } else {
                        presenter.loadData(page, "", "0");
                    }
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (presenter.isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);

    }

    @Override
    public void loadFinish() {
        groupFansRefresh.finishRefresh();
        groupFansRefresh.finishLoadMore();
    }

    @Override
    public void loadUI(int totalPage, int totalFans) {
        this.totalPage = totalPage;
        groupFansTotal.setText(totalFans + "");
    }

    @Override
    public void loadRv(GroupFansRvAdapter adapter) {
        groupFansRv.setAdapter(adapter);

    }

    @Override
    public void loadCount(GroupFansPeopleBean peopleBean) {
        groupFansZhitui.setText(peopleBean.getFirstFans() + "");
        groupFansXinzeng.setText(peopleBean.getTodayFans() + "");
        if (peopleBean.getParent() == null || "".equals(peopleBean.getParent())) {
            groupFansTuijianren.setText("无");
        } else {
            groupFansTuijianren.setText(peopleBean.getParent());
        }
    }

    @Override
    public void noFans() {
        this.totalPage = 0;
        groupFansTotal.setText("0");
    }

    @Override
    public GroupFansView createView() {
        return this;
    }

    @Override
    public GroupFansPresenter createPresenter() {
        return new GroupFansPresenter(this);
    }
}
