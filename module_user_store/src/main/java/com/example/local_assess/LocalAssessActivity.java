package com.example.local_assess;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.LocalOrderBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.view.RatingBarView;

import butterknife.BindView;

/**
 * 本地商城评价
 */
@Route(path = "/module_user_store/LocalAssessActivity")
public class LocalAssessActivity extends BaseActivity<LocalAssessView, LocalAssessPresenter> implements LocalAssessView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_assess_total)
    RatingBarView localAssessTotal;
    @BindView(R2.id.local_assess_serve)
    RatingBarView localAssessServe;
    @BindView(R2.id.local_assess_description)
    RatingBarView localAssessDescription;
    @BindView(R2.id.local_assess_edit)
    EditText localAssessEdit;
    @BindView(R2.id.local_assess_btn)
    TextView localAssessBtn;

    @Autowired(name = "bean")
    LocalOrderBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_assess;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("评价");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        localAssessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.commit(localAssessTotal.getStarCount(), localAssessServe.getStarCount(), localAssessDescription.getStarCount(), localAssessEdit.getText().toString(), bean);
            }
        });
    }

    @Override
    public LocalAssessView createView() {
        return this;
    }

    @Override
    public LocalAssessPresenter createPresenter() {
        return new LocalAssessPresenter(this);
    }
}
