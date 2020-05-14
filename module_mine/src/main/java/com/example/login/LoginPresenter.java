package com.example.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.RegisterBean;
import com.example.bean.UserInfoBean;
import com.example.code_login.CodeLoginActivity;
import com.example.common.CommonResource;
import com.example.forget.ForgetActivity;
import com.example.login_wechat.LoginWeChatActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.JpushUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
    }

    public void toForget() {
        mContext.startActivity(new Intent(mContext, ForgetActivity.class));
    }

    public void toCodeLogin() {
        mContext.startActivity(new Intent(mContext, CodeLoginActivity.class));
    }

    public void WeChatLogin() {
        SPUtil.addParm("weixin", "code");
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

    public void sendCode() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        String wx_code = SPUtil.getStringValue("wx_code");
        Map map = MapUtil.getInstance().addParms("code", wx_code).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.WXLOGIN_CODE, map);//"http://192.168.1.9:4001"
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                }.getType());
                LogUtil.e("denglu:" + result);
                if (userInfoBean.getPhone() == null || "".equals(userInfoBean.getPhone())) {
                    Intent intent = new Intent(mContext, LoginWeChatActivity.class);
                    intent.putExtra("bean", userInfoBean);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                } else {
                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean.getToken());
                    SPUtil.addParm(CommonResource.USERCODE, userInfoBean.getUserCode());
                    SPUtil.addParm(CommonResource.USER_NAME, userInfoBean.getNickname());
                    SPUtil.addParm(CommonResource.USER_PIC, userInfoBean.getIcon());
                    SPUtil.addParm(CommonResource.USER_INVITE, userInfoBean.getInviteCode());
                    SPUtil.addParm(CommonResource.LEVELID, userInfoBean.getLevelId());
                    SPUtil.addParm(CommonResource.USER_PHONE, userInfoBean.getPhone());
                    SPUtil.addParm(CommonResource.INTEGRATION, userInfoBean.getIntegration());

                    JpushUtil.setAlias(userInfoBean.getUserCode());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "------------" + errorMsg);
                if (errorCode.equals("1")) {
                    Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    public void login(String phone, String password) {
        if (!PhoneNumUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if ("".equals(password) || password == null) {
            Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

            Map map = MapUtil.getInstance().addParms("phone", phone).addParms("password", password).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postData(CommonResource.LOGIN_PHONE, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
//                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("登录：" + result);
                    UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                    }.getType());

                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean.getToken());
                    SPUtil.addParm(CommonResource.USERCODE, userInfoBean.getUserCode());
                    SPUtil.addParm(CommonResource.USER_NAME, userInfoBean.getNickname());
                    SPUtil.addParm(CommonResource.USER_PIC, userInfoBean.getIcon());
                    SPUtil.addParm(CommonResource.USER_INVITE, userInfoBean.getInviteCode());
                    SPUtil.addParm(CommonResource.LEVELID, userInfoBean.getLevelId());
                    SPUtil.addParm(CommonResource.USER_PHONE, userInfoBean.getPhone());

                    JpushUtil.setAlias(userInfoBean.getUserCode());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
//                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("登录：" + errorCode + "-------" + errorMsg);
                    if (errorCode.equals("1")) {
                        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }));
        }
    }

    public void register(String phone, String password, String code, String inviteCode) {
        if (!PhoneNumUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(code)) {
            Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(mContext, "请设置密码", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6 || password.length() > 20) {
            Toast.makeText(mContext, "密码为6-12位字符", Toast.LENGTH_SHORT).show();
        } else {
            RegisterBean registerBean = new RegisterBean(phone, code, password, inviteCode);
            String jsonString = JSON.toJSONString(registerBean);
            Map map = MapUtil.getInstance().addParms("memberStr", jsonString).build();
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postData(CommonResource.PHONEREGISTER, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("注册：" + result);
                    UserInfoBean userInfoBean = new Gson().fromJson(result, new TypeToken<UserInfoBean>() {
                    }.getType());
                    SPUtil.addParm(CommonResource.TOKEN, "JWT " + userInfoBean.getToken());
                    SPUtil.addParm(CommonResource.USERCODE, userInfoBean.getUserCode());
                    SPUtil.addParm(CommonResource.USER_NAME, userInfoBean.getNickname());
                    SPUtil.addParm(CommonResource.USER_PIC, userInfoBean.getIcon());
                    SPUtil.addParm(CommonResource.USER_INVITE, userInfoBean.getInviteCode());
                    SPUtil.addParm(CommonResource.LEVELID, userInfoBean.getLevelId());

                    JpushUtil.setAlias(userInfoBean.getUserCode());
                    ARouter.getInstance().build("/home/main").withString("type", "login").navigation();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }

    public void getCodeNum(String phone) {
        if (PhoneNumUtil.isMobileNO(phone)) {
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.GETREGISTERCODE + "/" + phone);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("获取验证码：" + result);
                    getView().getCodeSuccess();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    getView().getCodeFail();
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                    LogUtil.e(errorCode + "-----------" + errorMsg);
                }
            }));
        } else {
            getView().getCodeFail();
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }
}
