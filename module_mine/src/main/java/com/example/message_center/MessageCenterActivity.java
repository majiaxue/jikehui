package com.example.message_center;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.message_center.adapter.MessageCenterAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = "/mine/messagecenter")
public class MessageCenterActivity extends BaseActivity<MessageCenterView, MessageCenterPresenter> implements MessageCenterView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.message_center_lst)
    ListView messageCenterLst;
    @BindView(R2.id.message_center_refresh)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_center;
    }

    @Override
    public void initData() {
        includeTitle.setText("消息中心");
        presenter.loadData();

        //设置 Header 为 官方主题 样式
        refreshLayout.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messageCenterLst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.jumpToDetail(position);
            }
        });

        //********************设置上拉刷新下拉加载
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void loadFinish() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void loadRv(MessageCenterAdapter adapter) {
        messageCenterLst.setAdapter(adapter);
    }

    @Override
    public MessageCenterView createView() {
        return this;
    }

    @Override
    public MessageCenterPresenter createPresenter() {
        return new MessageCenterPresenter(this);
    }
}
