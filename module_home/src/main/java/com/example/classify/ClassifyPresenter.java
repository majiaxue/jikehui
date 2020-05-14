package com.example.classify;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.classify.adapter.ClassifyLeftRecAdapter;
import com.example.classify.adapter.MyRightRecAdapter;
import com.example.entity.LeftGroupBean;
import com.example.entity.RightRecBean;
import com.example.entity.TopBannerBean;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    private List<LeftGroupBean> gList;
    private List<TopBannerBean> images;

    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setLeftRec(final RecyclerView classifyLeftRec, final XBanner classifyXBanner) {

        //定义第一级的数据集合
        gList = new ArrayList<>();
        gList.add(new LeftGroupBean("推荐", true));
        gList.add(new LeftGroupBean("服装", false));
        gList.add(new LeftGroupBean("数码", false));
        gList.add(new LeftGroupBean("配饰", false));
        gList.add(new LeftGroupBean("洗护", false));
        gList.add(new LeftGroupBean("美妆", false));
        gList.add(new LeftGroupBean("家电", false));
        gList.add(new LeftGroupBean("数码", false));
        gList.add(new LeftGroupBean("数码", false));
        gList.add(new LeftGroupBean("服装", false));
        gList.add(new LeftGroupBean("数码", false));
        gList.add(new LeftGroupBean("配饰", false));
        gList.add(new LeftGroupBean("洗护", false));
        gList.add(new LeftGroupBean("美妆", false));
        gList.add(new LeftGroupBean("家电", false));
        gList.add(new LeftGroupBean("数码", false));
        gList.add(new LeftGroupBean("数码", false));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        classifyLeftRec.setLayoutManager(linearLayoutManager);
        final ClassifyLeftRecAdapter classifyLeftRecAdapter = new ClassifyLeftRecAdapter(mContext, gList, R.layout.group);
        classifyLeftRec.setAdapter(classifyLeftRecAdapter);

        classifyLeftRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {

                for (int i = 0; i < gList.size(); i++) {
                    gList.get(i).setSelected(i == position ? true : false);
                    classifyLeftRecAdapter.notifyDataSetChanged();
                }

                if (gList.get(0).isSelected()){
                    classifyXBanner.setVisibility(View.VISIBLE);
                }else{
                    classifyXBanner.setVisibility(View.GONE);
                }

            }
        });

        
    }

    public void setXBanner(XBanner homeXbanner) {
        images = new ArrayList<>();
        images.add(new TopBannerBean(R.drawable.img_10));
        images.add(new TopBannerBean(R.drawable.img_10));
        images.add(new TopBannerBean(R.drawable.img_10));
        homeXbanner.setBannerData(R.layout.image_fresco, images);
        homeXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
            }
        });
        // 设置XBanner的页面切换特效
        homeXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        homeXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
//                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRightRec(RecyclerView classifyRecommendRec) {
        List<RightRecBean> list = new ArrayList<>();

        List<RightRecBean.ListBean> a_childList = new ArrayList<>();
        a_childList.add(new RightRecBean.ListBean(R.drawable.tcl, "tcl"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.sony, "索尼"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.feilipu, "飞利浦"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.xiapu, "夏普"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.haier, "海尔"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.chuangwei, "创维"));


        List<RightRecBean.ListBean> b_childList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                b_childList.add(new RightRecBean.ListBean(R.drawable.e76696ec1eea0f7e8f0208ba61583434, "电视"));
                b_childList.add(new RightRecBean.ListBean(R.drawable.bingxiang, "冰箱"));
                b_childList.add(new RightRecBean.ListBean(R.drawable.fgfd, "洗衣机"));
            }
            list.add(new RightRecBean("专场推荐", a_childList));
            list.add(new RightRecBean("热门分类", b_childList));
        }


        MyRightRecAdapter myRightRecAdapter = new MyRightRecAdapter(mContext, list, R.layout.item_rec_group);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        classifyRecommendRec.setLayoutManager(linearLayoutManager);
        classifyRecommendRec.setAdapter(myRightRecAdapter);

    }

}
