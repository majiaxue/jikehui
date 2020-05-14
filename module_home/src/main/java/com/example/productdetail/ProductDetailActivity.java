package com.example.productdetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.bean.BannerImageBean;
import com.example.bean.ProductCenterBean;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.productdetail.adapter.ProductAccountAdapter;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

@Route(path = "/module_home/ProductDetailActivity")
public class ProductDetailActivity extends BaseActivity<ProductDetailView, ProductDetailPresenter> implements ProductDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.product_detail_xbanner)
    XBanner productDetailXbanner;
    @BindView(R2.id.product_detail_goods_type)
    TextView productDetailGoodsType;
    @BindView(R2.id.product_detail_goods_name)
    TextView productDetailGoodsName;
    @BindView(R2.id.product_detail_goods_price)
    TextView productDetailGoodsPrice;
    @BindView(R2.id.product_detail_goods_introduce)
    TextView productDetailGoodsIntroduce;
    @BindView(R2.id.product_detail_rv)
    RecyclerView productDetailRv;
    @BindView(R2.id.product_detail_webview)
    WebView productDetailWebview;
    @BindView(R2.id.product_detail_liuyan)
    LinearLayout productDetailLiuyan;
    @BindView(R2.id.product_detail_zixun)
    LinearLayout productDetailZixun;

    @Autowired(name = "bean")
    ProductCenterBean.RecordsBean bean;

    private String phoneNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("产品详情");
        productDetailGoodsName.setText(bean.getTitle());
        productDetailGoodsPrice.setText("￥" + bean.getPrice());
        productDetailGoodsIntroduce.setText(bean.getMessage());

        String detailHtml = bean.getInfo();
        String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
        //替换img属性
        productDetailWebview.loadData(varjs + detailHtml, "text/html", "UTF-8");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productDetailRv.setLayoutManager(linearLayoutManager);

        presenter.loadData(bean);
        presenter.loadPhone();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        productDetailLiuyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.liuYanPop();
            }
        });

        productDetailZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callPhone(phoneNum);
            }
        });
    }

    @Override
    public void updatePhone(int type, String phone) {
        if (type == 0) {
            phoneNum = phone;
        } else {
            phoneNum = bean.getPhone();
        }
    }

    @Override
    public void loadRv(ProductAccountAdapter adapter) {
        productDetailRv.setAdapter(adapter);
    }

    @Override
    public void loadBanner(List<BannerImageBean> imgList) {
        productDetailXbanner.setBannerData(imgList);
        productDetailXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(ProductDetailActivity.this).load(((BannerImageBean) model).getXBannerUrl()).into((ImageView) view);
            }
        });
    }

    @Override
    public ProductDetailView createView() {
        return this;
    }

    @Override
    public ProductDetailPresenter createPresenter() {
        return new ProductDetailPresenter(this);
    }
}
