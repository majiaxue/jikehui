package com.example.new_guide;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewGuideActivity extends BaseActivity<NewGuideView, NewGuidePresenter> implements NewGuideView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.newguide_img1)
    ImageView newguideImg1;
    @BindView(R2.id.newguide_img2)
    ImageView newguideImg2;
    @BindView(R2.id.newguide_img3)
    ImageView newguideImg3;
    @BindView(R2.id.newguide_img4)
    ImageView newguideImg4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_guide;
    }

    @Override
    public void initData() {
        includeTitle.setText("新人指导");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newguideImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.seeBigImg(NewGuideActivity.this,R.drawable.newguide1);
            }
        });

        newguideImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.seeBigImg(NewGuideActivity.this,R.drawable.newguide2);
            }
        });

        newguideImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.seeBigImg(NewGuideActivity.this,R.drawable.newguide3);
            }
        });

        newguideImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.seeBigImg(NewGuideActivity.this,R.drawable.newguide4);
            }
        });
    }

    @Override
    public NewGuideView createView() {
        return this;
    }

    @Override
    public NewGuidePresenter createPresenter() {
        return new NewGuidePresenter(this);
    }
}
