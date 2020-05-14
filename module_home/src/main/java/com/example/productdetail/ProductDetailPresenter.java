package com.example.productdetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.BannerImageBean;
import com.example.bean.ProductCenterBean;
import com.example.bean.ProductLiuYanBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.productdetail.adapter.ProductAccountAdapter;
import com.example.utils.LogUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PhoneNumUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ProductDetailPresenter extends BasePresenter<ProductDetailView> {
    public ProductDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(ProductCenterBean.RecordsBean data) {
        String testName = data.getTestName();
        String testAddress = data.getTestAddress();
        String testAccount = data.getTestAccount();
        String testPassword = data.getTestPassword();

        List<ProductCenterBean.RecordsBean> list = new ArrayList<>();
        for (int i = 0; i < testName.split(",").length; i++) {
            ProductCenterBean.RecordsBean productCenterBean = new ProductCenterBean.RecordsBean();
            productCenterBean.setTestName(testName.split(",")[i]);
            productCenterBean.setTestAddress(testAddress.split(",")[i]);
            productCenterBean.setTestAccount(testAccount.split(",")[i]);
            productCenterBean.setTestPassword(testPassword.split(",")[i]);
            list.add(productCenterBean);
        }
        ProductAccountAdapter productAccountAdapter = new ProductAccountAdapter(mContext, list, R.layout.rv_product_detail);
        if (getView() != null) {
            getView().loadRv(productAccountAdapter);
        }

        String[] pics = data.getPic().split(",");
        List<BannerImageBean> imgList = new ArrayList<>();
        for (int i = 0; i < pics.length; i++) {
            imgList.add(new BannerImageBean(pics[i]));
        }
        if (getView() != null) {
            getView().loadBanner(imgList);
        }
    }

    public void loadPhone() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.PRODUCT_GETPHONE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("咨询电话：" + result);
                Map map = JSON.parseObject(result, Map.class);
                String phone = (String) map.get("phone");
                String name = (String) map.get("name");
                if (getView() != null) {
                    getView().updatePhone(0, phone);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "---------------" + errorMsg);
                if (getView() != null) {
                    getView().updatePhone(1, "");
                }
            }
        }));
    }

    public void liuyan(ProductLiuYanBean liuYanBean, final PopupWindow pop) {
        ProcessDialogUtil.showProcessDialog(mContext);
        String jsonString = JSON.toJSONString(liuYanBean);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.PRODUCT_LIUYAN, body);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("留言：" + result);
                pop.dismiss();
                Toast.makeText(mContext, "留言成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                pop.dismiss();
                LogUtil.e(errorCode + "----------" + errorMsg);
            }
        }));
    }

    public void liuYanPop() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_product_liuyan, null);
        final EditText name = view.findViewById(R.id.pop_product_liuyan_name);
        final EditText phone = view.findViewById(R.id.pop_product_liuyan_phone);
        final EditText content = view.findViewById(R.id.pop_product_liuyan_content);
        final TextView btn = view.findViewById(R.id.pop_product_liuyan_btn);
        PopUtils.createPopCenter(mContext, view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(name.getText().toString())) {
                            Toast.makeText(mContext, "请输入姓名", Toast.LENGTH_SHORT).show();
                        } else if (!PhoneNumUtil.isMobileNO(phone.getText().toString())) {
                            Toast.makeText(mContext, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(content.getText().toString())) {
                            Toast.makeText(mContext, "请输入留言内容", Toast.LENGTH_SHORT).show();
                        } else {
                            ProductLiuYanBean liuYanBean = new ProductLiuYanBean(name.getText().toString(), phone.getText().toString(), content.getText().toString());
                            liuyan(liuYanBean, pop);
                        }
                    }
                });
            }
        });
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        mContext.startActivity(intent);
    }
}
