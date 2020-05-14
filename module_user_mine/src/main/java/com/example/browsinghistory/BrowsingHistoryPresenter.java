package com.example.browsinghistory;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.browsinghistory.adapter.BrowsingHistoryParentAdapter;
import com.example.bean.BrowsingHistoryBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryPresenter extends BasePresenter<BrowsingHistoryView> {

    private boolean isCompile = false;
    private BrowsingHistoryParentAdapter browsingHistoryParentAdapter;
    private List<BrowsingHistoryBean.RecordsBean> parentBeanList = new ArrayList<>();
    private boolean flag = true;

    public BrowsingHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void browsingHistoryRec(final RecyclerView browsingHistoryRec) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.HISTORYALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("browsingHistoryRecResult------------->" + result);
                BrowsingHistoryBean browsingHistoryBean = JSON.parseObject(result, new TypeReference<BrowsingHistoryBean>() {
                }.getType());
                if (browsingHistoryBean != null) {
                    getView().empty(false);
                    parentBeanList.clear();
                    parentBeanList.addAll(browsingHistoryBean.getRecords());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    browsingHistoryRec.setLayoutManager(linearLayoutManager);
                    browsingHistoryParentAdapter = new BrowsingHistoryParentAdapter(mContext, parentBeanList, R.layout.item_browsing_history_parent, false);
                    browsingHistoryRec.setAdapter(browsingHistoryParentAdapter);
                    //点击选中全部子布局的check
                    browsingHistoryParentAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(final View view, final int index) {

                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    flag = true;
                                    checkAll(index);
                                }
                            });

                        }
                    });
                }else{
                    getView().empty(true);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("browsingHistoryRecErrorMsg------------->" + errorMsg);
                getView().empty(true);
            }
        }));


    }

    //判断每个点击的parentCheckbox如果全部选中就去选中全选按钮
    private void checkAll(int index) {
        if (parentBeanList.get(index).isCheck()) {
            parentBeanList.get(index).setCheck(false);
            browsingHistoryParentAdapter.checkAll(index, true);
        } else {
            parentBeanList.get(index).setCheck(true);
            browsingHistoryParentAdapter.checkAll(index, false);
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentBeanList.size(); i++) {
            if (!parentBeanList.get(i).isCheck()) {
                flag = false;
            }
        }

        if (getView() != null) {
            getView().isCheckAll(flag);
        }

    }

    //编辑状态
    public void browsingHistoryState() {
        if (parentBeanList.size() == 0) {
            if (isCompile) {
                isCompile = false;
                getView().isCompile(isCompile);
            } else {
                isCompile = true;
                getView().isCompile(isCompile);
            }
        } else {
            if (isCompile) {
                isCompile = false;
                getView().isCompile(isCompile);
            } else {
                isCompile = true;
                getView().isCompile(isCompile);
            }
            browsingHistoryParentAdapter.setCompile(isCompile);

        }

    }

    //选中parent全部的checkbox
    public void checkAllParent(boolean isCheckAllParent) {
        if (isCheckAllParent) {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(false);
                browsingHistoryParentAdapter.checkAll(i, true);
            }
        } else {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(true);
                browsingHistoryParentAdapter.checkAll(i, false);
            }
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

    }

    //删除
    public void deleteList() {

        List<String> deleteList = new ArrayList<>();
        for (int i = 0; i < parentBeanList.size(); i++) {
            for (int j = 0; j < parentBeanList.get(i).getItem().size(); j++) {
                if (parentBeanList.get(i).getItem().get(j).isCheck()) {
                    deleteList.add(parentBeanList.get(i).getItem().get(j).getHistoryId() + "");
                }
            }
        }

        Observable<ResponseBody> deleteGoodsCollection = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postDelete(CommonResource.HISTORYDELETE, deleteList, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(deleteGoodsCollection, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("deleteBrowsingHistory----->" + msg);

                for (int i = parentBeanList.size() - 1; i >= 0; i--) {
                    List<BrowsingHistoryBean.RecordsBean.ItemBean> items = parentBeanList.get(i).getItem();
                    if (parentBeanList.get(i).isCheck()) {
                        parentBeanList.remove(i);
                    }
                    for (int j = items.size() - 1; j >= 0; j--) {
                        if (items.get(j).isCheck()) {
                            items.remove(j);
                        }
                    }
                }

                browsingHistoryParentAdapter.notifyDataSetChanged();

                if (parentBeanList.size() == 0) {
                    getView().isCompile(false);
                    getView().isCheckAll(false);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("deleteBrowsingHistory----->" + errorMsg);
            }
        }));


    }
}
