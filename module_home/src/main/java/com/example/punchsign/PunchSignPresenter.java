package com.example.punchsign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.PunchSignBean;
import com.example.common.CommonResource;
import com.example.dbflow.ShareBean;
import com.example.dbflow.ShareUtil;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.lang.annotation.Retention;
import java.text.SimpleDateFormat;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class PunchSignPresenter extends BasePresenter<PunchSignView> {

    private PunchSignBean punchSignBean;

    public PunchSignPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void popGuiZe(TextView punchSignGuiZe) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_guize, null);
        PopUtils.viewPopBottom(punchSignGuiZe, mContext, inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(PopupWindow pop) {

            }
        });
    }

    public void signQuery() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNQUERY, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("PunchSignPresenterResult" + result);
                punchSignBean = JSON.parseObject(result, new TypeReference<PunchSignBean>() {
                }.getType());
//                LogUtil.e("punchSignBean"+punchSignBean);
                if (punchSignBean != null) {
                    if (getView() != null) {
                        getView().punchSign(punchSignBean);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
            }
        }));
    }

    public void qiandao() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNTODAY, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("签到Result" + result + msg);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().qianDao();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("签到ErrorMsg" + errorMsg);
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void meiRiQianDao(final int type) {
        Map map = MapUtil.getInstance().addParms("type", type).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.SIGNGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("浏览商品签到Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().meiRiQianDao(type);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void shareCount() {
        int count = 0;
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = formatter.format(timeMillis);
        ShareBean shareBean = ShareUtil.getInstance().query(SPUtil.getUserCode());
        if (shareBean != null) {
            if (dateTime.equals(shareBean.getUpdateTime())) {
                if (shareBean.getCount() > punchSignBean.getSignSetting().getShareNum()) {
                    count = punchSignBean.getSignSetting().getShareNum();
                } else {
                    count = shareBean.getCount();
                }
            }
        }
        Map map = MapUtil.getInstance().addParms("count", count).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.SIGNSHARE, map, SPUtil.getToken());
        final int finalCount = count;
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("每日分享Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().shareCount(finalCount);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void yaoQingHaoYou() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNINVITE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("邀请好友Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().yaoQingHaoYou();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void firstOrder() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNFIRSTORDER, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("首次下单Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().firstOrder();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void order() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNORDER, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("有效订单Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().order();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void fans() {
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SIGNFANS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝数量Result" + result);
                if (result.contains("true")) {
                    if (getView() != null) {
                        getView().fans();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
