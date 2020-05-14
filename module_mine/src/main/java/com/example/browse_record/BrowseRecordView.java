package com.example.browse_record;

import com.example.browse_record.adapter.BrowseRecordAdapter;
import com.example.mvp.IView;

public interface BrowseRecordView extends IView {
    void loadUI(BrowseRecordAdapter adapter);

    void loadFinish();
}
