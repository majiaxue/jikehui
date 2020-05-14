package com.example.intoshop.baby;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.intoshop.baby.adapter.BabyRecAdapter;
import com.example.intoshop.baby.adapter.BabyRecStaggeredAdapter;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.entity.BabyRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public class BabyPresenter extends BasePresenter<BabyView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;

    public BabyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setBabyRec(RecyclerView intoShopRec) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        //添加间距
        intoShopRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        intoShopRec.setLayoutManager(linearLayoutManager);

        List<BabyRecBean> babyRecBeanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_54, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_55, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_56, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        }
        BabyRecAdapter babyRecAdapter = new BabyRecAdapter(mContext, babyRecBeanList, R.layout.item_baby_rec);
        intoShopRec.setAdapter(babyRecAdapter);

        babyRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
            }
        });
    }

    public void setBabyRecStaggeredGrid(RecyclerView intoShopRec) {

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //解决item跳动
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (intoShopRec.getItemDecorationCount() == 0) {
            intoShopRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        intoShopRec.setLayoutManager(staggeredGridLayoutManager);

        List<BabyRecBean> babyRecBeanList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_54, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "", "班迪卡旗舰店"));
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_55, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "", "班迪卡旗舰店"));
            babyRecBeanList.add(new BabyRecBean(R.drawable.img_56, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "", "班迪卡旗舰店"));
        }

        BabyRecStaggeredAdapter babyRecStaggeredAdapter = new BabyRecStaggeredAdapter(mContext, babyRecBeanList, R.layout.item_baby_rec_staggered_grid);
        intoShopRec.setAdapter(babyRecStaggeredAdapter);

        babyRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
            }
        });

    }
}
