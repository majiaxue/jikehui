package com.example.mineorder.stayappraise;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.bean.MineOrderBean;
import com.example.mineorder.stayappraise.adapter.StayAppraiseParentAdapter;
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
 * Describe:待评价
 */
public class StayAppraisePresenter extends BasePresenter<StayAppraiseView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();
    private StayAppraiseParentAdapter stayAppraiseParentAdapter;

    public StayAppraisePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayAppraiseRec() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        Map map = MapUtil.getInstance().addParms("status", 3).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("stayAppraiseRec" + result);
//                MineOrderBean mineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
//                LogUtil.e("MineOrderBean" + mineOrderBean.getOrderList());
                MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    if (stayAppraiseParentAdapter == null){
                        stayAppraiseParentAdapter = new StayAppraiseParentAdapter(mContext, listBeans, R.layout.item_stay_appraise_parent);

                    }else {
                        stayAppraiseParentAdapter.notifyDataSetChanged();
                    }
                    if (getView()!=null){
                        getView().load(stayAppraiseParentAdapter);
                    }

                    stayAppraiseParentAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, int index) {

                        }
                    });

                    stayAppraiseParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {

                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("stayAppraiseErrorMsg------->" + errorMsg);
            }
        }));
    }
}
