package com.example.group_fans;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.example.bean.GroupFansBean;
import com.example.bean.GroupFansPeopleBean;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class GroupFansPresenter extends BasePresenter<GroupFansView> {
    private List<UserInfoBean> dataList = new ArrayList<>();
    private GroupFansRvAdapter adapter;
    private int pages;


    public GroupFansPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page, String content, String level) {
        Map map = MapUtil.getInstance().addParms("current", page).addParms("search", content).addParms("level", level).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.GROUP_FANS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                if (getView() != null) {
                    getView().loadFinish();
                }
                LogUtil.e("团队粉丝：" + result);
                GroupFansBean groupFansBean = JSON.parseObject(result, GroupFansBean.class);
                pages = groupFansBean.getPages();

                if (page == 1) {
                    dataList.clear();
                }
                dataList.addAll(groupFansBean.getRecords());
                if (adapter == null) {
                    adapter = new GroupFansRvAdapter(mContext, dataList, R.layout.rv_group_fans);
                    if (getView() != null) {
                        getView().loadRv(adapter);
                    }
                } else {
                    adapter.notifyDataSetChanged();
                }

                if (getView() != null) {
                    getView().loadUI(groupFansBean.getPages(), groupFansBean.getTotal());
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if ("1".equals(errorCode)) {
                    dataList.clear();
                    if (adapter == null) {
                        adapter = new GroupFansRvAdapter(mContext, dataList, R.layout.rv_group_fans);
                        if (getView() != null) {
                            getView().loadRv(adapter);
                        }
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                    if (getView() != null) {
                        getView().noFans();
                        getView().loadFinish();
                    }
                }
                LogUtil.e(errorCode + "-------" + errorMsg);
            }
        }));

    }

    public void loadCount() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GROUP_FANS_POPPLE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝：" + result);
                GroupFansPeopleBean groupFansPeopleBean = JSON.parseObject(result, GroupFansPeopleBean.class);
                if (getView() != null) {
                    getView().loadCount(groupFansPeopleBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public boolean isShouldHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right
                    && ev.getY() > top && ev.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
