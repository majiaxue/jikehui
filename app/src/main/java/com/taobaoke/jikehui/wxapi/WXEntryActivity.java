package com.taobaoke.jikehui.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, CommonResource.WXAPPID);
        boolean handleIntent = api.handleIntent(getIntent(), this);
        //下面代码是判断微信分享后返回WXEnteryActivity的，如果handleIntent==false,说明没有调用IWXAPIEventHandler，则需要在这里销毁这个透明的Activity;
        if (!handleIntent) {
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        LogUtil.e("onReq...");
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {

        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:

                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:

                    finish();
                    break;
                default:
                    finish();
                    break;
            }
        } else {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    SPUtil.addParm("wx_code", ((SendAuth.Resp) baseResp).code);
                    String weixin = SPUtil.getStringValue("weixin");
                    EventBus.getDefault().post(new EventBusBean("bind".equals(weixin) ? "WXBIND" : "WXCODE"));
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    LogUtil.e("onResp: 用户取消");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    LogUtil.e("onResp: 发送请求被拒绝");
                    finish();
                    break;
            }
        }
    }

}
