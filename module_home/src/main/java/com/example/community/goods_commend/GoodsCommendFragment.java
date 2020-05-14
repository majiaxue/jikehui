package com.example.community.goods_commend;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.CommunityLocalBean;
import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class GoodsCommendFragment extends BaseFragment<GoodsCommendView, GoodsCommendPresenter> implements GoodsCommendView {
    @BindView(R2.id.goods_commend_title)
    RecyclerView goodsCommendTitle;
    @BindView(R2.id.goods_commend_rv)
    RecyclerView goodsCommendRv;
    @BindView(R2.id.goods_commend_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.goods_commend_image)
    ImageView goodsCommendImage;
    @BindView(R2.id.goods_commend_name)
    TextView goodsCommendName;
    @BindView(R2.id.goods_commend_preferential_price)
    TextView goodsCommendPreferentialPrice;
    @BindView(R2.id.goods_commend_original_price)
    TextView goodsCommendOriginalPrice;
    @BindView(R2.id.goods_commend_coupon_price)
    TextView goodsCommendCouponPrice;
    @BindView(R2.id.goods_commend_number)
    TextView goodsCommendNumber;
    @BindView(R2.id.goods_commend_qr_code)
    ImageView goodsCommendQrCode;
    @BindView(R2.id.goods_commend_linear)
    LinearLayout mLinear;

    private LinearLayoutManager verManager;
    private GridLayoutManager gridLayoutManager;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_commend;
    }

    @Override
    public void initData() {
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        goodsCommendTitle.setLayoutManager(gridLayoutManager);
        goodsCommendTitle.addItemDecoration(new SpaceItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_11), (int) getContext().getResources().getDimension(R.dimen.dp_11), 0, 0));

        verManager = new LinearLayoutManager(getContext());
        verManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsCommendRv.setLayoutManager(verManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        presenter.initTitle();
        presenter.initData(page);
    }

    @Override
    public void initClick() {
        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.initData(page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.initData(page);
            }
        });
    }

    @Override
    public void changeType() {
        page = 1;
        presenter.initData(page);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public void loadShareInfo(CommunityLocalBean bean) {
        Glide.with(getContext()).load(bean.getItempic()).into(goodsCommendImage);
        goodsCommendName.setText(bean.getItemtitle());
        goodsCommendOriginalPrice.setText("￥" + bean.getItemprice());
        goodsCommendPreferentialPrice.setText("￥" + (Double.valueOf(bean.getItemprice()) - Double.valueOf(bean.getCouponmoney())));
        goodsCommendCouponPrice.setText("￥" + bean.getCouponmoney());
        goodsCommendOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
    }

    @Override
    public void loadQR(Bitmap bitmap) {
        goodsCommendQrCode.setImageBitmap(bitmap);
        presenter.share(mLinear);
    }

    @Override
    public void loadTitle(CommendTitleAdapter adapter) {
        goodsCommendTitle.setAdapter(adapter);
    }

    @Override
    public void loadContent(GoodsCommendAdapter adapter) {
        goodsCommendRv.setAdapter(adapter);
    }

    @Override
    public GoodsCommendView createView() {
        return this;
    }

    @Override
    public GoodsCommendPresenter createPresenter() {
        return new GoodsCommendPresenter(getContext());
    }
}
