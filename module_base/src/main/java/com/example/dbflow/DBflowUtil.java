package com.example.dbflow;

import com.example.utils.LogUtil;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class DBflowUtil {
    private static DBflowUtil dBflowUtil;

    public static DBflowUtil getInstance() {
        if (dBflowUtil == null) {
            synchronized (DBflowUtil.class) {
                if (dBflowUtil == null) {
                    dBflowUtil = new DBflowUtil();
                }
            }
        }
        return dBflowUtil;
    }

    public boolean insert(String content, String type) {
        SQLite.delete().from(SearchHistoryBean.class).where(SearchHistoryBean_Table.type.eq(type),SearchHistoryBean_Table.content.eq(content)).query();
        SearchHistoryBean searchHistory = new SearchHistoryBean();
        searchHistory.insertData(content, type);
        searchHistory.save();
        return true;
    }

    public void deleteAll(String type) {
        SQLite.delete().from(SearchHistoryBean.class).where(SearchHistoryBean_Table.type.eq(type)).query();
    }

    public List<SearchHistoryBean> query(String type) {
        List<SearchHistoryBean> searchHistory = SQLite.select().from(SearchHistoryBean.class).where(SearchHistoryBean_Table.type.eq(type)).queryList();
        return searchHistory;
    }

}
