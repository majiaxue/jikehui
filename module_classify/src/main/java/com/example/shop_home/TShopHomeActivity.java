package com.example.shop_home;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

@Route(path = "/module_classify/tshop_home")
public class TShopHomeActivity extends BaseActivity<TShopHomeView, TShopHomePresenter> implements TShopHomeView {
    @BindView(R2.id.tshop_home_webview)
    WebView webView;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @Autowired(name = "shopId")
    String shopId;
    @Autowired(name = "url")
    String url;

    private String goodsId = "";
    private boolean isJump = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tshop_home;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        LogUtil.e("url----->" + url);
        includeTitle.setText("店铺详情");

        LogUtil.e("url111------>" + url);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //        webView.loadUrl(url);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.e("==========>" + url);
                String id = "";
                if (url.contains("?id=")) {
                    String[] split = url.split("&");
                    String[] split1 = split[0].split("=");
                    id = split1[1];
                    if (goodsId.equals(id)) {
                        return false;
                    }
                    goodsId = id;
                    LogUtil.e("goodsID----------->" + id);
                    ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                            .withString("shoptype", "0")
                            .withString("para", id)
                            .navigation();
                } else if (url.contains("&id=")) {
                    String[] split = url.split("&id=");
                    if (split[1].contains("&")) {
                        String[] split1 = split[1].split("&");
                        id = split1[0];
                    } else {
                        id = split[1];
                    }
                    if (goodsId.equals(id)) {
                        return false;
                    }
                    goodsId = id;
                    LogUtil.e("goodsID----------->" + id);
                    ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                            .withString("shoptype", "0")
                            .withString("para", id)
                            .navigation();
                }

                view.loadUrl(url);
                return false;
            }
        };

        WebChromeClient webChromeClient = new WebChromeClient();

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("//")) {
                url = "https:" + url;

            }
//        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams("57328044", "mm_26632322_6858406_23810104", "mm_26632322_6858406_23810104");

            //提供给三方传递配置参数
            Map<String, String> exParams = new HashMap<>();
            exParams.put(AlibcConstants.ISV_CODE, "appisvcode");


            //设置页面打开方式
            AlibcShowParams showParams = new AlibcShowParams();
            showParams.setOpenType(OpenType.Auto);

            AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
            taokeParams.setPid("mm_112883640_11584347_72287650277");


            AlibcTrade.openByUrl(this, "", url, webView, webViewClient, webChromeClient, showParams, taokeParams, exParams, new AlibcTradeCallback() {
                @Override
                public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

                }

                @Override
                public void onFailure(int i, String s) {

                }
            });

        } else {

            //提供给三方传递配置参数
            Map<String, String> exParams = new HashMap<>();
            exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

            //打开指定页面
            AlibcBasePage detailPage = new AlibcShopPage(shopId);

            //设置页面打开方式
            AlibcShowParams showParams = new AlibcShowParams();
            showParams.setOpenType(OpenType.Auto);

            AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
            taokeParams.setPid("mm_112883640_11584347_72287650277");

            AlibcTrade.openByBizCode(this, detailPage, webView, webViewClient, webChromeClient, "shop", showParams, taokeParams, exParams, new AlibcTradeCallback() {
                @Override
                public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

                }

                @Override
                public void onFailure(int i, String s) {

                }
            });

        }
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
    public TShopHomeView createView() {
        return this;
    }

    @Override
    public TShopHomePresenter createPresenter() {
        return new TShopHomePresenter(this);
    }
}
