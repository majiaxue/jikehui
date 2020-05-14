package com.example.message_detail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.MessageCenterBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import java.io.Serializable;

import butterknife.BindView;

public class MessageDetailActivity extends BaseActivity<MessageDetailView, MessageDetailPresenter> implements MessageDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.message_detail_title)
    TextView messageDetailTitle;
    @BindView(R2.id.message_detail_time)
    TextView messageDetailTime;
    @BindView(R2.id.message_detail_img)
    ImageView messageDetailImg;
    @BindView(R2.id.message_detail_content)
    TextView messageDetailContent;

    private MessageCenterBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("消息详情");
        Intent intent = getIntent();
        bean = (MessageCenterBean) intent.getSerializableExtra("bean");
        messageDetailTitle.setText(bean.getTitle());
        messageDetailContent.setText(bean.getMessage());
        messageDetailTime.setText(bean.getCreateTime());

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
    public MessageDetailView createView() {
        return this;
    }

    @Override
    public MessageDetailPresenter createPresenter() {
        return new MessageDetailPresenter(this);
    }
}
