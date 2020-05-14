package com.example.community;

import com.example.adapter.BaseVPAdapter;
import com.example.mvp.IView;

public interface CommunityView extends IView {
    void updateVP(BaseVPAdapter adapter);
}
