package com.example.chunzhi;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

//充值界面
public class ChunZhiPresenter extends BasePresenter<ChunZhiView> {
    public ChunZhiPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getData(String activation,String password){
        //activation卡号     password密码
        Map map = MapUtil.getInstance().addParms("activation", activation).addParms("password",password).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postHead(CommonResource.CHUNZHI, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("充值接口------：" + result);
                if ("激活码使用成功".equals(result)){
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build("/home/main").navigation();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------" + errorMsg);
                Toast.makeText(mContext,errorMsg,Toast.LENGTH_SHORT).show();
            }
        }));

    }
}
