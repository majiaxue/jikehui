package com.example.upgrade;

import com.example.mvp.IView;
import com.example.upgrade.adapter.UpgradeAdapter;

public interface UpgradeView extends IView {
    void loadUI(UpgradeAdapter adapter);
}
