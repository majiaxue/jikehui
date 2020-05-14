package com.example.shakestock;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.StatusBarUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JzvdStd;

/**
 * 抖劵购买
 */
@Route(path = "/module_home/ShakeStockActivity")
public class ShakeStockActivity extends BaseActivity<ShakeStockView, ShakeStockPresenter> implements ShakeStockView {


    @BindView(R2.id.shake_stock_image_back)
    ImageView shakeStockImageBack;
    @BindView(R2.id.shake_stock_rec)
    RecyclerView shakeStockRec;
    @BindView(R2.id.shake_stock_smart_refresh)
    SmartRefreshLayout shakeStockSmartRefresh;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shake_stock;
    }

    @Override
    public void initData() {
        StatusBarUtils.setStatusBar(this, getResources().getColor(R.color.black));

        presenter.recyclerVideo(shakeStockRec, page);

//        String url = "http://video.haodanku.com/ff02cd42e310c04251892b53d13e8d63?attname=1562752503.mp4";
//        shakeStockVideo.setVideoPath(url);
//        shakeStockVideo.seekTo(0);
//        shakeStockVideo.requestFocus();
//        shakeStockVideo.start();
//        shakeStockVideo.stopPlayback();

        shakeStockSmartRefresh.setRefreshHeader(new MaterialHeader(this));
//        shakeStockSmartRefresh.setRefreshFooter(new ClassicsFooter(this));

        shakeStockSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.recyclerVideo(shakeStockRec, page);
                refreshLayout.finishRefresh();
            }
        });

        shakeStockSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.recyclerVideo(shakeStockRec, page);
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void initClick() {
        shakeStockImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        shakeStockVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shakeStockVideo.isPlaying()){
//                    shakeStockVideo.pause();
//                    shakeStockVideoImage.setVisibility(View.VISIBLE);
//                }else{
//                    shakeStockVideo.start();
//                    shakeStockVideoImage.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        shakeStockVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//                mp.setLooping(true);
//            }
//        });


    }

    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.resetAllVideos();

    }

    @Override
    public ShakeStockView createView() {
        return this;
    }

    @Override
    public ShakeStockPresenter createPresenter() {
        return new ShakeStockPresenter(this);
    }

}
