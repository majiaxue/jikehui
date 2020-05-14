package com.example.shippingaddress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.ShippingAddressBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.shippingaddress.adapter.ShippingAddressAdapter;
import com.example.shippingaddress.amendaddress.AmendAddressActivity;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressPresenter extends BasePresenter<ShippingAddressView> {

    private ShippingAddressAdapter shippingAddressAdapter;

    public ShippingAddressPresenter(Context context) {
        super(context);
    }

    private List<ShippingAddressBean> shippingAddressBeanList;

    @Override
    protected void onViewDestroy() {

    }

    public void setShippingAddressRec(final RecyclerView shippingAddressRec, final String from) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.ADDRESSSHOW, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("ShippingAddressResult----->" + result);
                shippingAddressBeanList = JSON.parseArray(result, ShippingAddressBean.class);

                LogUtil.e("ShippingAddressResult----->" + shippingAddressBeanList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                shippingAddressRec.setLayoutManager(linearLayoutManager);
                shippingAddressAdapter = new ShippingAddressAdapter(mContext, shippingAddressBeanList, R.layout.item_shipping_address_rec);
                shippingAddressRec.setAdapter(shippingAddressAdapter);

                if (!TextUtils.isEmpty(from)) {
                    shippingAddressAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            Intent intent = new Intent();
                            intent.putExtra("address", shippingAddressBeanList.get(position));
                            ((Activity) mContext).setResult(Activity.RESULT_OK, intent);
//                        ARouter.getInstance().build("/user/order_confirm").withSerializable("address", shippingAddressBeanList.get(position)).navigation();
                            ((Activity) mContext).finish();
                        }
                    });
                }

                shippingAddressAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                    @Override
                    public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                        //点击选中
                        view1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.ADDRESSDEFAULT + "/" + shippingAddressBeanList.get(position).getId(), SPUtil.getToken());
                                RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                    @Override
                                    public void onSuccess(String result, String msg) {
                                        LogUtil.e("shippingAddressResult默认地址------------->" + msg);
                                        for (int i = 0; i < shippingAddressBeanList.size(); i++) {
                                            if (i != position) {
                                                shippingAddressBeanList.get(i).setAddressDefault(0);
                                            }
                                            shippingAddressBeanList.get(position).setAddressDefault(1);
                                            shippingAddressAdapter.notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void onError(String errorCode, String errorMsg) {
                                        LogUtil.e("shippingAddressErrorMsg默认地址------>" + errorMsg);
                                    }
                                }));

                            }
                        });
                        //修改
                        view2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, AmendAddressActivity.class);
                                intent.putExtra("shippingAddressBeanList", (Serializable) shippingAddressBeanList);
                                intent.putExtra("position", position);
                                mContext.startActivity(intent);
                            }
                        });
                        //删除
                        view3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //删除地址
                                final SelfDialog selfDialog = new SelfDialog(mContext);
                                selfDialog.setTitle("提示");
                                selfDialog.setMessage("您确定要删除此地址吗？");
                                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                                    @Override
                                    public void onYesClick() {
                                        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).deleteDataWithout(CommonResource.DELETEADDRESS + "/" + shippingAddressBeanList.get(position).getId(), SPUtil.getToken());
                                        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                            @Override
                                            public void onSuccess(String result, String msg) {
                                                LogUtil.e("shippingAddressResult删除地址------------->" + msg);
//                                                for (int i = shippingAddressBeanList.size() - 1; i >= 0; i--) {
                                                shippingAddressBeanList.remove(position);
//                                                }

                                                shippingAddressAdapter.notifyDataSetChanged();
                                                selfDialog.dismiss();
                                                PopUtils.setTransparency(mContext, 1f);
                                            }

                                            @Override
                                            public void onError(String errorCode, String errorMsg) {
                                                LogUtil.e("shippingAddressErrorMsg删除地址------>" + errorMsg);
                                            }
                                        }));

                                    }
                                });
                                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                                    @Override
                                    public void onNoClick() {
                                        selfDialog.dismiss();
                                        PopUtils.setTransparency(mContext, 1f);
                                    }
                                });
                                PopUtils.setTransparency(mContext, 0.3f);
                                selfDialog.show();
                            }
                        });

                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("ShippingAddressMsg--------->" + errorMsg);
            }
        }));

    }
}
