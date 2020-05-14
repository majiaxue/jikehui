package com.example.dbflow;

import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class ShareOperationUtil {

    private static ShareOperationUtil shareOperationUtil;

    public static ShareOperationUtil getShareOperationUtil() {
        if (shareOperationUtil == null) {
            synchronized (ShareOperationUtil.class) {
                if (shareOperationUtil == null) {
                    shareOperationUtil = new ShareOperationUtil();
                }
            }
        }
        return shareOperationUtil;
    }

    public void createOrUpdate(){
        long timeMillis = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = formatter.format(timeMillis);
        List<ShareBean> query = ShareUtil.getInstance().query();
        LogUtil.e("query"+query);
        ShareBean shareBean = ShareUtil.getInstance().query(SPUtil.getUserCode());
        LogUtil.e("shareBean:" + shareBean);
        if (shareBean != null) {
            if (dateTime.equals(shareBean.getUpdateTime())) {
                ShareUtil.getInstance().updateData(SPUtil.getUserCode(), shareBean.getCount() + 1, dateTime);
            } else {
                ShareUtil.getInstance().updateData(SPUtil.getUserCode(), 1, dateTime);
            }
        } else {
            boolean insert = ShareUtil.getInstance().insert(SPUtil.getUserCode(), 1, dateTime);
            LogUtil.e("insert"+insert);
        }
    }

}
