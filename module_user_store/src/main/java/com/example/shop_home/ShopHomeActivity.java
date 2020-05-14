package com.example.shop_home;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.ShopHomeBean;
import com.example.mvp.BaseFragmentActivity;
import com.example.shop_home.adapter.ShopHomeVPAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.view.RatingBarView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

@Route(path = "/module_user_store/ShopHomeActivity")
public class ShopHomeActivity extends BaseFragmentActivity<ShopHomeView, ShopHomePresneter> implements ShopHomeView {

    @BindView(R2.id.shop_home_bg)
    ImageView shopHomeBg;
    @BindView(R2.id.shop_home_back)
    ImageView shopHomeBack;
    @BindView(R2.id.shop_home_search)
    EditText shopHomeSearch;
    @BindView(R2.id.shop_home_more)
    ImageView shopHomeMore;
    @BindView(R2.id.shop_home_store_image)
    SimpleDraweeView shopHomeStoreImage;
    @BindView(R2.id.shop_home_youhuiquan)
    SimpleDraweeView shopHomeYouhuiquan;
    @BindView(R2.id.shop_home_store_name)
    TextView shopHomeStoreName;
    @BindView(R2.id.shop_home_star)
    RatingBarView shopHomeStar;
    @BindView(R2.id.shop_home_collect_store)
    TextView shopHomeCollectStore;
    @BindView(R2.id.shop_home_store_collect_number)
    TextView shopHomeStoreCollectNumber;
    @BindView(R2.id.shop_home_tab)
    TabLayout shopHomeTab;
    @BindView(R2.id.shop_home_vp)
    ViewPager shopHomeVp;

    @Autowired(name = "sellerId")
    String shop_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_home;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        presenter.initView(shop_id);

        //加载背景，高斯模糊
        Glide.with(this)
                .load(R.drawable.dpyhq_bj)
                .dontAnimate()
                .error(R.drawable.dpyhq_bj)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(this, 0, 3)))
                .into(shopHomeBg);

        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(com.example.module_base.R.drawable.youhuiquan))
                .build();
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        shopHomeYouhuiquan.setController(draweeController);

        presenter.isCollect(shop_id);
    }

    @Override
    public void initClick() {
        shopHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        shopHomeSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //到搜索页面
//                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
//            }
//        });

        shopHomeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showMore(shopHomeMore);
            }
        });

        shopHomeCollectStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.collectShop(shop_id);
            }
        });

        shopHomeYouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.youhuiquan(shop_id);
            }
        });
    }

    @Override
    public void isCollect(String result) {
        if ("true".equals(result)) {
            shopHomeCollectStore.setText("取消收藏");
        } else if ("false".equals(result)) {
            shopHomeCollectStore.setText("收藏");
        }
    }

    @Override
    public void initView(ShopHomeBean shopHomeBean) {
        shopHomeStar.setStar(4, false);
        shopHomeStar.setClickable(false);

        shopHomeStoreImage.setImageURI(Uri.parse(shopHomeBean.getSellerLogo() == null ? "" : shopHomeBean.getSellerLogo()));
        shopHomeStoreName.setText(shopHomeBean.getSellerShopName());
        shopHomeStoreCollectNumber.setText(0 + "收藏");
        presenter.initTabLayout(shopHomeTab, shop_id, shopHomeBean.getSellerCategory());

        shopHomeTab.setupWithViewPager(shopHomeVp);

    }

    @Override
    public void loadVP(ShopHomeVPAdapter adapter) {
        shopHomeVp.setAdapter(adapter);
    }

    @Override
    public ShopHomeView createView() {
        return this;
    }

    @Override
    public ShopHomePresneter createPresenter() {
        return new ShopHomePresneter(this);
    }

}
