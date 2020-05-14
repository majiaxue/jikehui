package com.example.commoditydetails.webview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends Activity {

    @BindView(R2.id.web_view)
    WebView webView;
    @BindView(R2.id.web_image_back)
    ImageView webImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        LogUtil.e("========>1" + url);
//        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

//        //设置不用系统浏览器打开,直接显示在当前Webview
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                url.replace("");
//                LogUtil.e("========>2"+url);
//                view.loadUrl(url);
//
//                return true;
//            }
//        });

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                if (url == null) return false;

                try {
                    if (url.startsWith("pinduoduo://") || url.startsWith("tbopen://") || url.startsWith("openapp.jdmobile://")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }

                //处理http和https开头的url
                wv.loadUrl(url);
                return true;
            }
        };

        webView.setWebViewClient(webViewClient);

//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();    //表示等待证书响应
//                // handler.cancel();      //表示挂起连接，为默认方式
//                // handler.handleMessage(null);    //可做其他处理
//            }
//        });

        webImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

//    //点击返回上一页面而不是退出浏览器
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
//            webView.goBack();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();

            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
