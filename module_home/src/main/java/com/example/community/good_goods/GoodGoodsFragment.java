package com.example.community.good_goods;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.GoodGoodsBean;
import com.example.community.adapter.GoodGoodsAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class GoodGoodsFragment extends BaseFragment<GoodGoodsView, GoodGoodsPresneter> implements GoodGoodsView {
    @BindView(R2.id.good_goods_rv)
    RecyclerView goodsCommendRv;
    @BindView(R2.id.good_goods_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R2.id.good_goods_image)
    ImageView goodGoodsImage;
    @BindView(R2.id.good_goods_name)
    TextView goodGoodsName;
    @BindView(R2.id.good_goods_preferential_price)
    TextView goodGoodsPreferentialPrice;
    @BindView(R2.id.good_goods_original_price)
    TextView goodGoodsOriginalPrice;
    @BindView(R2.id.good_goods_coupon_price)
    TextView goodGoodsCouponPrice;
    @BindView(R2.id.good_goods_number)
    TextView goodGoodsNumber;
    @BindView(R2.id.good_goods_qr_code)
    ImageView goodGoodsQrCode;
    @BindView(R2.id.good_goods_linear)
    LinearLayout goodGoodsLinear;

    private LinearLayoutManager verManager;

    private int page = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_good_goods;
    }

    @Override
    public void initData() {
        verManager = new LinearLayoutManager(getContext());
        verManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsCommendRv.setLayoutManager(verManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        presenter.loadData(page);
    }

    @Override
    public void initClick() {
        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void loadQR(Bitmap bitmap) {
        goodGoodsQrCode.setImageBitmap(bitmap);
        presenter.share(goodGoodsLinear);
    }

    @Override
    public void loadShareInfo(GoodGoodsBean.NetBean.ItemDataBean dataBean) {
        Glide.with(getContext()).load(dataBean.getItempic()).into(goodGoodsImage);
        goodGoodsName.setText(dataBean.getItemtitle());
        goodGoodsPreferentialPrice.setText("￥" + dataBean.getItemendprice());
        goodGoodsOriginalPrice.setText("￥"+dataBean.getItemprice());
        goodGoodsCouponPrice.setText("￥"+dataBean.getCouponmoney());

    }

    @Override
    public void loadContent(GoodGoodsAdapter adapter) {
        goodsCommendRv.setAdapter(adapter);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public GoodGoodsView createView() {
        return this;
    }

    @Override
    public GoodGoodsPresneter createPresenter() {
        return new GoodGoodsPresneter(getContext());
    }
}
