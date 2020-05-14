package com.example.local_mine;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.ApplicationBean;
import com.example.bean.RedPackageBean;
import com.example.common.CommonResource;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalMinePresenter extends BasePresenter<LocalMineView> {

    private List<RedPackageBean> redPackageBeans;

    public LocalMinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getRedPackage() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_HONGBAO);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("红包：" + result);
                try {
                    redPackageBeans = JSON.parseArray(result, RedPackageBean.class);
                    if (redPackageBeans != null && redPackageBeans.size() > 0) {
                        for (int i = 0; i < redPackageBeans.size(); i++) {
                            if (i % 2 == 1) {
                                redPackageBeans.get(i).setBackground(R.drawable.redpackage_bg_lan);
                            } else {
                                redPackageBeans.get(i).setBackground(R.drawable.redpackage_bg_zi);
                            }
                        }

                        if (getView() != null) {
                            getView().loadBanner(redPackageBeans);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    //查询商家申请
    public void businessApplication() {
        Map build = MapUtil.getInstance().addParms("userCode", SPUtil.getUserCode()).build();
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.SELLERSTATE, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("查询商家申请" + result);
                getView().callBack();
                ApplicationBean applicationBean = JSON.parseObject(result, new TypeReference<ApplicationBean>() {
                }.getType());
                if (applicationBean != null) {
                    String data1 = applicationBean.getData();
                    LogUtil.e("mineFragment" + data1);
                    if (data1.equals("2") || data1.equals("3")) {
                        ARouter.getInstance().build("/module_user_mine/BusinessApplicationActivity").withString("from", CommonResource.HISTORY_LOCAL).navigation();
                    } else {
                        Toast.makeText(mContext, "您已经是商家了无需申请!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                getView().callBack();
                LogUtil.e(errorCode + "--------------------->" + errorMsg);
            }
        }));
    }

    public void jumpToWallet() {
        ARouter.getInstance().build("/module_local/CouponWalletActivity").navigation();
    }
}
