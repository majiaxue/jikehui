package com.example.mineorder.staysendgoods;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
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
 * Describe:待发货
 */
public class StaySendGoodsPresenter extends BasePresenter<StaySendGoodsView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();
    private MineOrderParentAdapter mineOrderParentAdapter;

    public StaySendGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void staySendGoodsRec() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        Map map = MapUtil.getInstance().addParms("status", 1).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("StaySendGoodsResult-------->" + result);
//                MineOrderBean MineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
                final MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());

                    if (mineOrderParentAdapter == null) {
                        mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);

                    } else {
                        mineOrderParentAdapter.notifyDataSetChanged();
                    }
                    if (getView() != null) {
                        getView().load(mineOrderParentAdapter);
                    }

                    mineOrderParentAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                        @Override
                        public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                            final TextView left = view2.findViewById(R.id.mine_order_parent_btn_left);

                            //去店铺
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                                }
                            });
                            //申请退款
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if ("申请退款".equals(left.getText().toString())) {
                                        ARouter.getInstance()
                                                .build("/module_user_mine/RefundActivity")
                                                .withSerializable("mineOrderBean", mineOrderBean)
                                                .withString("type", "1")
                                                .withInt("position", position)
                                                .navigation();
                                    } else if ("取消申请".equals(left.getText().toString())) {
                                        Map map = MapUtil.getInstance().addParms("orderSn", mineOrderBean.getOrderList().get(position).getOrderSn()).build();
                                        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postData(CommonResource.CANCEL_TUIKUAN, map);
                                        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                            @Override
                                            public void onSuccess(String result, String msg) {
                                                LogUtil.e("取消申请退款：" + result);
                                                staySendGoodsRec();
                                            }

                                            @Override
                                            public void onError(String errorCode, String errorMsg) {
                                                LogUtil.e(errorCode + "---------------" + errorMsg);
                                            }
                                        }));
                                    }
                                }
                            });
                            //提醒发货
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(mContext, "已提醒商家发货!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_user_mine/OrderDetailsActivity")
                                    .withString("orderSn", listBeans.get(0).getOrderItems().get(position).getOrderSn())
                                    .navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("StaySendGoodsErrorMsg-------->" + errorMsg);
            }
        }));
    }
}
