package com.example.invite_friends.adapter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

public class InviteAdapter extends MyRecyclerAdapter<String> {
    private List<WebView> webList = new ArrayList<>();

    public InviteAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        WebView webView = holder.getView(R.id.share_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(CommonResource.BASEURL_4001 + "/rest/share/invite?id=1&inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));
        webList.add(webView);

    }

    public List<WebView> getWebList() {
        return webList;
    }
}
