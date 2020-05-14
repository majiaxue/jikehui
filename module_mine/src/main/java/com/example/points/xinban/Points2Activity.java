package com.example.points.xinban;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.chunzhi.ChunZhiActivity;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.points.xinban.adapter.Points2Adapter;
import com.example.bean.MingXiBean;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/Points2Activity")
public class Points2Activity extends BaseActivity<Points2View, Points2Presenter> implements Points2View {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R2.id.points_total_points)
    TextView pointsTotalPoints;
    @BindView(R2.id.rec_points_mingxi)
    RecyclerView recPointsMingxi;
    private MingXiBean bean = new MingXiBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_points2;
    }

    @Override
    public void initData() {
        includeTitle.setText("购物金");
        includeRightBtn.setVisibility(View.VISIBLE);
        includeRightBtn.setText("充值");
        presenter.loadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recPointsMingxi.setLayoutManager(layoutManager);
        recPointsMingxi.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) this.getResources().getDimension(R.dimen.dp_8)));
    }

    @Override
    public void initClick() {

        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Points2Activity.this,ChunZhiActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public Points2View createView() {
        return this;
    }

    @Override
    public Points2Presenter createPresenter() {
        return new Points2Presenter(this);
    }

    @Override
    public void loadRv(Points2Adapter adapter) {
        recPointsMingxi.setAdapter(adapter);
    }

    @Override
    public void loadData(MingXiBean recordBeans) {
        this.bean = recordBeans;
        pointsTotalPoints.setText(recordBeans.getMemberIntegration() == null ? "0" : recordBeans.getMemberIntegration());
        LogUtil.e("是我的积分-----"+recordBeans.getMemberIntegration());
    }
}
