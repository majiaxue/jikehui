package com.example.mineorder.stayobligation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.CustomDialog;
import com.google.gson.Gson;
import com.kongzue.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:待付款
 */
public class StayObligationPresenter extends BasePresenter<StayObligationView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();
    private MineOrderParentAdapter mineOrderParentAdapter;

    public StayObligationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayObligationRec() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        Map map = MapUtil.getInstance().addParms("status", 6).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("待付款-------->" + result);
//                MineOrderBean MineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
                MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    if (mineOrderParentAdapter == null){
                        mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
                    }else{
                        mineOrderParentAdapter.notifyDataSetChanged();
                    }
                    if (getView()!=null){
                        getView().load(mineOrderParentAdapter);
                    }
                    mineOrderParentAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                        @Override
                        public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                            //去店铺
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                                }
                            });
                            //删除订单
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Map build = MapUtil.getInstance().addParms("orderId", listBeans.get(position).getOrderId()).build();
                                    Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERREMOVE, build, SPUtil.getToken());
                                    RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                                        @Override
                                        public void onSuccess(String result, String msg) {
                                            LogUtil.e("删除---------->" + result);
                                            if ("true".equals(result)) {
                                                listBeans.remove(position);
                                                mineOrderParentAdapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void onError(String errorCode, String errorMsg) {
                                            LogUtil.e("删除---------->" + errorMsg);
                                        }
                                    }));
                                }
                            });
                            //付款
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                                    submitOrderBean.setTotalAmount(listBeans.get(position).getPayAmount());
                                    submitOrderBean.setMasterNo(listBeans.get(position).getOrderItems().get(0).getOrderSn());
                                    ARouter.getInstance().build("/module_user_store/PaymentActivity")
                                            .withSerializable("submitOrderBean", submitOrderBean)
                                            .navigation();
                                }
                            });
                        }
                    });

                    mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            //待付款
                            ARouter.getInstance()
                                    .build("/module_user_mine/ObligationActivity")
                                    .withString("orderSn", listBeans.get(position).getOrderItems().get(0).getOrderSn())
                                    .navigation();
                        }
                    });

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("待付款-------->" + errorMsg);
            }
        }));

    }
}
