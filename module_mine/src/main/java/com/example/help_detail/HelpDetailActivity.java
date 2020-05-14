package com.example.help_detail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class HelpDetailActivity extends BaseActivity<HelpDetailView, HelpDetailPresenter> implements HelpDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.help_detail_question)
    TextView helpDetailQuestion;
    @BindView(R2.id.help_detail_answer)
    TextView helpDetailAnswer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("详情页");
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
    public HelpDetailView createView() {
        return this;
    }

    @Override
    public HelpDetailPresenter createPresenter() {
        return new HelpDetailPresenter(this);
    }
}
