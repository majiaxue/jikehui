package com.example.suggestion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.suggestion_history.SuggestionHistoryActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;

public class SuggestionPresenter extends BasePresenter<SuggestionView> {
    public SuggestionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToHistory() {
        mContext.startActivity(new Intent(mContext, SuggestionHistoryActivity.class));
    }

    public void submit(String content) {
        Map map = MapUtil.getInstance().addParms("message", content).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postHead(CommonResource.SUGGESTION, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("提交意见：" + result);
                Toast.makeText(mContext, "反馈成功", Toast.LENGTH_SHORT).show();
                ((Activity) mContext).finish();
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
