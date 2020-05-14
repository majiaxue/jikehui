package com.example.message_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.message_list.adapter.MessageListAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class MessageListActivity extends BaseActivity<MessageListView, MessageListPresenter> implements MessageListView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.message_list_rv)
    RecyclerView messageListRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    public void initData() {
        includeTitle.setText("消息列表");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageListRv.setLayoutManager(layoutManager);
        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadUI(MessageListAdapter adapter) {
        messageListRv.setAdapter(adapter);
    }

    @Override
    public MessageListView createView() {
        return this;
    }

    @Override
    public MessageListPresenter createPresenter() {
        return new MessageListPresenter(this);
    }
}
