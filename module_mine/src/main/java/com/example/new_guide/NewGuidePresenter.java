package com.example.new_guide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

public class NewGuidePresenter extends BasePresenter<NewGuideView> {
    public NewGuidePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
