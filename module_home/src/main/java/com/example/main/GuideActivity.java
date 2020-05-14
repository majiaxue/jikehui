package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.entity.TopBannerBean;
import com.example.module_home.R;
import com.example.utils.StatusBarUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private XBanner guideBanner;
    private TextView guideSkip;
    private List<TopBannerBean> images = new ArrayList<>();
//    private CountDownTimer start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        changeStatus();
        initView();

        initData();
    }

    private void changeStatus() {
        // 设置状态栏
        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusTheme(this, true, true);
    }

    private void initView() {
        guideBanner = findViewById(R.id.guide_banner);
        guideSkip = findViewById(R.id.guide_skip);
    }


    private void initData() {

//        start = new CountDownTimer(5000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                guideSkip.setText("跳转(" + Math.round((double) millisUntilFinished / 1000) + "s)");
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        }.start();

        images.add(new TopBannerBean(R.drawable.frist111));
        images.add(new TopBannerBean(R.drawable.jgfhfhdfhdf));
        images.add(new TopBannerBean(R.drawable.hfcgxhdhd));

//        guideBanner.setData(images,null);
        guideBanner.setBannerData(images);
        guideBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                Glide.with(GuideActivity.this).load(images.get(position).getXBannerUrl()).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        guideBanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        guideBanner.setPageChangeDuration(1000);

        guideBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if (position == 2) {
                    guideBanner.stopAutoPlay();
                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    finish();
                }
            }

        });

        guideSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideBanner.stopAutoPlay();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    /**
     * 为了更好的体验效果建议在下面两个生命周期中调用下面的方法
     **/
    @Override
    protected void onResume() {
        super.onResume();
        guideBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        guideBanner.stopAutoPlay();
    }

}
