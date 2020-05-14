package com.example.local_order;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalOrderBean;
import com.example.bean.LocalTuiKuanBean;
import com.example.bean.TxtAndChooseBean;
import com.example.common.CommonResource;
import com.example.local_order.adapter.LocalOrderAdapter;
import com.example.local_order.adapter.LocalOrderNavbarAdapter;
import com.example.local_order.adapter.LocalTuiKuanAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.TxtUtil;
import com.example.view.SelfDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LocalOrderPresenter extends BasePresenter<LocalOrderView> {

    private List<TxtAndChooseBean> navbarList;
    private LocalOrderNavbarAdapter navbarAdapter;
    private LocalOrderAdapter orderAdapter;
    private List<LocalOrderBean> localOrderBeans = new ArrayList<>();

    private String status;
    private boolean isTui = false;
    private LocalTuiKuanAdapter tuiKuanAdapter;
    private List<LocalTuiKuanBean.RecordsBean> tuiKuanBeanRecords = new ArrayList<>();

    public LocalOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initNavbar() {
        navbarList = new ArrayList<>();
        navbarList.add(new TxtAndChooseBean("全部订单", true));
        navbarList.add(new TxtAndChooseBean("待付款", false));
        navbarList.add(new TxtAndChooseBean("待取货", false));
        navbarList.add(new TxtAndChooseBean("配送中", false));
        navbarList.add(new TxtAndChooseBean("已完成", false));
        navbarList.add(new TxtAndChooseBean("已关闭", false));
        navbarList.add(new TxtAndChooseBean("退款中", false));
        navbarAdapter = new LocalOrderNavbarAdapter(mContext, navbarList, R.layout.rv_local_order_navbar);
        if (getView() != null) {
            getView().loadNavbar(navbarAdapter);
        }

        navbarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < navbarList.size(); i++) {
                    if (i == position) {
                        navbarList.get(i).setChoose(true);
                    } else {
                        navbarList.get(i).setChoose(false);
                    }
                }

                navbarAdapter.notifyDataSetChanged();
                if (getView() != null) {
                    getView().changeType(position);
                }
            }
        });
    }

    public void loadData(final String status, final int page) {
        this.status = status;
        ProcessDialogUtil.showProcessDialog(mContext);
        Map map = MapUtil.getInstance().addParms("status", status).addParms("page", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getHead(CommonResource.LOCAL_GET_ORDER, map,SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("附近小店订单：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                if (page == 1) {
                    localOrderBeans.clear();
                }
                localOrderBeans.addAll(JSON.parseArray(result, LocalOrderBean.class));
                if (orderAdapter == null) {
                    orderAdapter = new LocalOrderAdapter(mContext, localOrderBeans, R.layout.rv_local_order_list);
                    if (getView() != null) {
                        getView().loadRv(orderAdapter);
                    }
                } else {
                    if (isTui) {
                        getView().loadRv(orderAdapter);
                    } else {
                        orderAdapter.notifyDataSetChanged();
                    }
                }

                isTui = false;

                orderAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_local/OrderInfoActivity").withSerializable("bean", localOrderBeans.get(position)).navigation();
                    }
                });

                orderAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                    @Override
                    public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });

                        view2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (localOrderBeans.get(position).getStatus()) {
                                    case "0":
                                        cancelOrderTip(localOrderBeans.get(position));
                                        break;

                                    case "1":
                                        refundPop(localOrderBeans.get(position));
                                        break;

                                    case "2":
                                        refundPop(localOrderBeans.get(position));
                                        break;

                                    case "3":
                                        refundPop(localOrderBeans.get(position));
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });

                        view3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (localOrderBeans.get(position).getStatus()) {
                                    case "0":
                                        ARouter.getInstance().build("/module_local/LocalPaymentActivity").withSerializable("bean", localOrderBeans.get(position)).navigation();
                                        break;

                                    case "1":
                                        ARouter.getInstance().build("/module_local/OrderInfoActivity").withSerializable("bean", localOrderBeans.get(position)).navigation();
                                        break;

                                    case "2":
                                        confrimOrderTip(localOrderBeans.get(position));
                                        break;

                                    case "3":
                                        confrimOrderTip(localOrderBeans.get(position));
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----------" + errorMsg);
                if (getView() != null) {
                    getView().loadFinish();
                }
                if (page == 1) {
                    localOrderBeans.clear();
                }
                if (orderAdapter == null) {
                    orderAdapter = new LocalOrderAdapter(mContext, localOrderBeans, R.layout.rv_local_order_list);
                    if (getView() != null) {
                        getView().loadRv(orderAdapter);
                    }
                } else {
                    orderAdapter.notifyDataSetChanged();
                }
            }
        }));
    }

    public void tuihuo(final int page) {

        Map map = MapUtil.getInstance().addParms("page", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getData(CommonResource.LOCAL_TUIKUAN + "/" + SPUtil.getUserCode(), map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("退款中----------->" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                if (page == 1) {
                    tuiKuanBeanRecords.clear();
                }

                LocalTuiKuanBean localTuiKuanBean = JSON.parseObject(result, LocalTuiKuanBean.class);
                tuiKuanBeanRecords.addAll(localTuiKuanBean.getRecords());
                if (tuiKuanAdapter == null || !isTui) {
                    tuiKuanAdapter = new LocalTuiKuanAdapter(mContext, tuiKuanBeanRecords, R.layout.rv_local_order_list);
                    if (getView() != null) {
                        getView().loadTuiKuanRv(tuiKuanAdapter);
                    }
                } else {
                    tuiKuanAdapter.notifyDataSetChanged();
                }

                isTui = true;

                tuiKuanAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_local/OrderInfoActivity").withSerializable("tuikuan", tuiKuanBeanRecords.get(position)).navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (getView() != null) {
                    getView().loadFinish();
                }
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void cancelOrderTip(final LocalOrderBean localOrderBean) {
        final SelfDialog selfDialog = new SelfDialog(mContext);
        selfDialog.setTitle("提示");
        selfDialog.setMessage("确定要取消订单吗？");
        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                cancelOrder(localOrderBean, selfDialog);
            }
        });

        selfDialog.setNoOnclickListener("暂不取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtil.setTransparency(mContext, 1.0f);
            }
        });
        PopUtil.setTransparency(mContext, 0.3f);
        selfDialog.show();
    }

    /**
     * 取消订单
     *
     * @param localOrderBean
     * @param selfDialog
     */
    private void cancelOrder(LocalOrderBean localOrderBean, final SelfDialog selfDialog) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_CANCEL_ORDER + "/" + localOrderBean.getOrderSn() + "/" + localOrderBean.getId());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("取消订单：" + result);
                selfDialog.dismiss();
                if (isTui) {
                    tuihuo(1);
                } else {
                    loadData(status, 1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void confrimOrderTip(final LocalOrderBean localOrderBean) {
        final SelfDialog selfDialog = new SelfDialog(mContext);
        selfDialog.setTitle("提示");
        selfDialog.setMessage("要确认收货吗？");
        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                confirmOrder(localOrderBean, selfDialog);
            }
        });

        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtil.setTransparency(mContext, 1.0f);
            }
        });
        PopUtil.setTransparency(mContext, 0.3f);
        selfDialog.show();
    }

    /**
     * 确认收货
     *
     * @param localOrderBean
     * @param selfDialog
     */
    private void confirmOrder(LocalOrderBean localOrderBean, final SelfDialog selfDialog) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_CONFIRM_ORDER + localOrderBean.getOrderSn() + "/" + localOrderBean.getId());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("确认收货：" + result);
                selfDialog.dismiss();
                if (isTui) {
                    tuihuo(1);
                } else {
                    loadData(status, 1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void refundPop(final LocalOrderBean localOrderBean) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_local_tuikuan, null);
        TextView text = view.findViewById(R.id.popup_local_tuikuan_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView cancel = view.findViewById(R.id.popup_local_tuikuan_close);
        final RadioGroup radioGroup = view.findViewById(R.id.popup_local_tuikuan_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_local_tuikuan_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_local_tuikuan_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_local_tuikuan_but3);
        final RadioButton but4 = view.findViewById(R.id.popup_local_tuikuan_but4);
        final RadioButton but5 = view.findViewById(R.id.popup_local_tuikuan_but5);
        final TextView btn = view.findViewById(R.id.popup_local_tuikuan_btn);

        final String[] reason = {""};
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.popup_local_tuikuan_but1) {
                            reason[0] = but1.getText().toString();
                        } else if (checkedId == R.id.popup_local_tuikuan_but2) {
                            reason[0] = but2.getText().toString();
                        } else if (checkedId == R.id.popup_local_tuikuan_but3) {
                            reason[0] = but3.getText().toString();
                        } else if (checkedId == R.id.popup_local_tuikuan_but4) {
                            reason[0] = but4.getText().toString();
                        } else if (checkedId == R.id.popup_local_tuikuan_but5) {
                            reason[0] = but5.getText().toString();
                        }
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(reason[0])) {
                            Toast.makeText(mContext, "请选择退款原因", Toast.LENGTH_SHORT).show();
                        } else {
                            refund(localOrderBean, reason[0], pop);
                        }
                    }
                });
            }
        });
    }

    /**
     * 退款
     */
    private void refund(final LocalOrderBean localOrderBean, String reason, final PopupWindow pop) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderSn", localOrderBean.getOrderSn());
        jsonObject.put("reason", reason);
        String jsonString = JSON.toJSONString(jsonObject);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);

        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postHeadWithBody(CommonResource.LOCAL_TUIKUAN, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("退款：" + result);
                Toast.makeText(mContext, "申请成功,等待商家处理", Toast.LENGTH_SHORT).show();
                pop.dismiss();
                if (isTui) {
                    tuihuo(1);
                } else {
                    loadData(status, 1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }
}
