package com.example.dbflow;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ShareUtil {

    private static ShareUtil shareUtil;

    public static ShareUtil getInstance() {
        if (shareUtil == null) {
            synchronized (ShareUtil.class) {
                if (shareUtil == null) {
                    shareUtil = new ShareUtil();
                }
            }
        }
        return shareUtil;
    }

    public boolean insert(String userCode, int count, String updateTime) {
        ShareBean shareBean = new ShareBean();
        shareBean.insertData(userCode,count, updateTime);
        shareBean.save();
//        SQLite.insert(ShareBean.class)
//                .columns(ShareBean_Table.userCode,ShareBean_Table.count,ShareBean_Table.time)
//                .values(userCode,count,time)
//                .execute();
        return true;
    }

    public List<ShareBean> query() {
        List<ShareBean> shareBean = SQLite.select().from(ShareBean.class).queryList();
        return shareBean;
    }

    public ShareBean query(String userCode) {
        ShareBean shareBean = SQLite.select().from(ShareBean.class).where(ShareBean_Table.userCode.eq(userCode)).querySingle();
        return shareBean;
    }

    public void updateData(String userCode,int count,String updateTime) {
        //2.SQLite.update()
        SQLite.update(ShareBean.class)
                .set(ShareBean_Table.count.eq(count), ShareBean_Table.updateTime.eq(updateTime))
                .where(ShareBean_Table.userCode.eq(userCode))
                .async()
                .execute();
    }

}
