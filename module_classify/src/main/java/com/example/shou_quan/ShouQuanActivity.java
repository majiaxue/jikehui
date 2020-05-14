package com.example.shou_quan;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

@Route(path = "/module_classify/shouquan")
public class ShouQuanActivity extends BaseActivity<ShouQuanView, ShouQuanPresenter> implements ShouQuanView {
    @BindView(R2.id.shouquan_webview)
    WebView webView;

    @Autowired(name = "url")
    String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shouquan;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                LogUtil.e("===============>" + url);

                if (url.indexOf("goback") != -1) {
                    if (url.indexOf("goback?code=0") != -1) {
                        ShouQuanActivity.this.finish();
                        return false;
                    } else {
                        url = SPUtil.getStringValue("link");
                    }
                }
                view.loadUrl(url);
                return false;
            }
        };

        WebChromeClient webChromeClient = new WebChromeClient();

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);

//        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams("57328044", "mm_26632322_6858406_23810104", "mm_26632322_6858406_23810104");

        //提供给三方传递配置参数
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

        AlibcShowParams showParams = new AlibcShowParams();
        showParams.setOpenType(OpenType.Auto);

        AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
        taokeParams.setPid("mm_112883640_11584347_72287650277");


        AlibcTrade.openByUrl(this, url, url, webView, webViewClient, webChromeClient, showParams, taokeParams, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

            }

            @Override
            public void onFailure(int i, String s) {

            }
        });
    }

    @Override
    public void initClick() {

    }

    @Override
    public ShouQuanView createView() {
        return this;
    }

    @Override
    public ShouQuanPresenter createPresenter() {
        return new ShouQuanPresenter(this);
    }
}
