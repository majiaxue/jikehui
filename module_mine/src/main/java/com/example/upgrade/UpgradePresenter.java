package com.example.upgrade;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OperatorBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.operator.OperatorActivity;
import com.example.upgrade.adapter.UpgradeAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnClearCacheListener;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class UpgradePresenter extends BasePresenter<UpgradeView> {

    private List<OperatorBean> beanList;
    private UpgradeAdapter adapter;
    private int clickPosition;

    public UpgradePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.IWANTUP, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("我要升级：" + result);
                beanList = JSON.parseArray(result, OperatorBean.class);
                if (beanList.size() > 0) {
                    beanList.remove(0);
                }
                adapter = new UpgradeAdapter(mContext, beanList, R.layout.rv_upgrade);
                if (getView() != null) {
                    getView().loadUI(adapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));


    }


    public void click() {
        adapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
            @Override
            public void ViewThreeOnClick(final View view1, final View view2, final View view3, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = position;
                        upJustNow("0", position, view1);
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = position;
                        upJustNow("1", position, view2);
                    }
                });

                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = position;
                        PopUtils.popQuanYi(mContext, beanList, position, new OnClearCacheListener() {
                            @Override
                            public void setOnClearCache(final PopupWindow pop, View confirm) {
                                confirm.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        pop.dismiss();
                                        if ("0".equals(beanList.get(position).getUpType())) {
                                            upJustNow("1", position, view3);
                                        } else {
                                            upJustNow("0", position, view3);
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    private void toPay(String money, String name, String id) {
        ARouter.getInstance().build("/module_mine/up_pay")
                .withString("money", money)
                .withString("name", name)
                .withString("levelId", id)
                .withString("type", "upgrade")
                .navigation((Activity) mContext, 100);
    }

    /**
     * @param flag     点击按钮   0:立即升级   1:支付(前端用)
     * @param position
     */
    private void upJustNow(final String flag, final int position, View view) {
        Map map = MapUtil.getInstance().addParms("levelId", beanList.get(position).getId()).addParms("payType", flag).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.UP_JUSTNOW, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("立即升级：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("error:" + errorCode + "------------------" + errorMsg);
                if ("1".equals(flag) && errorCode.equals("9")) {
                    toPay(beanList.get(position).getPrice(), beanList.get(position).getName(), beanList.get(position).getId());
                } else {
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    public void upSuccess() {
        PopUtils.popUpSuccess(mContext, beanList.get(clickPosition), new OnClearCacheListener() {
            @Override
            public void setOnClearCache(final PopupWindow pop, View confirm) {
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, OperatorActivity.class));
                        pop.dismiss();
                    }
                });
            }
        });
    }
}
