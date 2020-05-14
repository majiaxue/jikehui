package com.example.bind_wechat;

import android.content.Context;
import android.widget.Toast;

import com.example.common.CommonResource;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;

public class BindWeChatPresenter extends BasePresenter<BindWeChatView> {
    public BindWeChatPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
    }

    public void bind() {
        SPUtil.addParm("weixin", "bind");
        PlatformConfig.setWeixin("", "");

        IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);

        if (!api.isWXAppInstalled()) {
            Toast.makeText(mContext, "请先安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
    }

    public void bindWX() {
        String wx_code = SPUtil.getStringValue("wx_code");
        Map map = MapUtil.getInstance().addParms("code", wx_code).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.WXBIND_CODE, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                getView().bindSuccess();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
