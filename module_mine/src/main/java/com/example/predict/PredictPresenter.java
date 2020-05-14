package com.example.predict;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.mvp.BasePresenter;
import com.example.predict.jd.JDFragment;
import com.example.predict.pdd.PddFragment;
import com.example.predict.tb.TBFragment;

public class PredictPresenter extends BasePresenter<PredictView> {
    private JDFragment jdFragment;
    private PddFragment pddFragment;
    private TBFragment tbFragment;
    private FragmentManager manager;

    public PredictPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void change(int position) {

        FragmentTransaction transaction = manager.beginTransaction();
        if (position == 0) {

            transaction.show(tbFragment)
                    .hide(jdFragment)
                    .hide(pddFragment)
                    .commit();

        } else if (position == 2) {

            transaction.show(jdFragment)
                    .hide(pddFragment)
                    .hide(tbFragment).commit();

        } else if (position == 1) {

            transaction.show(pddFragment)
                    .hide(jdFragment)
                    .hide(tbFragment).commit();

        }
        getView().changeType(position);
    }

    public void loadData(FragmentManager manager, int resId) {
        this.manager = manager;
        jdFragment = new JDFragment();
        pddFragment = new PddFragment();
        tbFragment = new TBFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(resId, jdFragment)
                .add(resId, pddFragment)
                .add(resId, tbFragment)
                .show(tbFragment)
                .hide(pddFragment)
                .hide(jdFragment)
                .commit();
    }
}
