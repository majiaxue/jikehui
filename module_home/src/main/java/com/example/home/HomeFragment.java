package com.example.home;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.TxtUtil;
import com.example.view.CustomHeader;
import com.example.view.CustomeRecyclerView;
import com.example.view.MarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, NestedScrollView.OnScrollChangeListener {

    @BindView(R2.id.home_top_bg)
    ImageView homeTopBg;
    @BindView(R2.id.home_search)
    LinearLayout homeSearch;
    @BindView(R2.id.home_message)
    LinearLayout homeMessage;
    @BindView(R2.id.home_xbanner)
    XBanner homeXbanner;
    @BindView(R2.id.home_marquee)
    MarqueeView homeMarquee;
    @BindView(R2.id.home_see_more_top)
    TextView homeSeeMoreTop;
    @BindView(R2.id.home_top_rec)
    RecyclerView homeTopRec;
    @BindView(R2.id.home_see_more_bottom)
    TextView homeSeeMoreBottom;
    @BindView(R2.id.home_good_choice_rec)
    RecyclerView homeGoodChoiceRec;
    @BindView(R2.id.home_bottom_rec)
    RecyclerView homeBottomRec;
    @BindView(R2.id.home_smart_refresh)
    SmartRefreshLayout homeSmartRefresh;
    @BindView(R2.id.home_gotop)
    ImageView mGoTop;
    @BindView(R2.id.home_nested_scroll)
    NestedScrollView homeNestedScroll;
    @BindView(R2.id.home_slide_indicator_point)
    SeekBar homeSlideIndicatorPoint;
    @BindView(R2.id.text131_gradual_change)
    TextView text131GradualChange;
    @BindView(R2.id.text141_gradual_change)
    TextView text141GradualChange;
    @BindView(R2.id.home_zhong_xbanner)
    ViewPager homeZhongXbanner;
    @BindView(R2.id.home_hot_recommend)
    RelativeLayout homeHotRecommend;
    @BindView(R2.id.home_dou_juan_buy)
    RelativeLayout homeDouJuanBuy;
    @BindView(R2.id.home_punch_sign)
    RelativeLayout homePunchSign;
    @BindView(R2.id.home_free_of_charge)
    RelativeLayout homeFreeOfCharge;
    @BindView(R2.id.home_huo_dong)
    ImageView homeHuoDong;
    @BindView(R2.id.home_see_more)
    TextView homeSeeMore;
    @BindView(R2.id.home_time)
    TextView homeTime;
    @BindView(R2.id.home_count_down)
    TextView homeCountDown;
    @BindView(R2.id.home_flash_sale_rec)
    RecyclerView homeFlashSaleRec;
    @BindView(R2.id.home_xianshiqianggou)
    RelativeLayout homeXianShiQiangGou;

    private int nextPage = 1;
    private CountDownTimer countDownTimer;
    private String format1;
    private long currentTime = 0;//判断点击时间间隔

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
//        time();
        //跑马灯
        presenter.setViewSingleLine();
        //xBanner
        presenter.setXBanner(homeXbanner, homeTopBg);
        //中间轮播图
        presenter.setZhongXBanner(homeZhongXbanner);
        //topRec
        presenter.setRec(homeTopRec, homeSlideIndicatorPoint);
//优选recycler
        presenter.setGoodChoiceRec(homeGoodChoiceRec);
        //推荐recycler
        presenter.setBottomRec(nextPage, homeBottomRec);
        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        homeSmartRefresh.setRefreshHeader(customHeader);
        homeSmartRefresh.setRefreshFooter(new ClassicsFooter(getContext()));


        TxtUtil.txtJianbian(text131GradualChange, "#0c53e4", "#ae3fed");
        TxtUtil.txtJianbian(text141GradualChange, "#fe5d05", "#fdb902");

    }

    @Override
    public void initClick() {
        homeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        homeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtil.getToken())) {
                    PopUtils.isLogin(getContext());
                } else {
                    ARouter.getInstance().build("/mine/messagecenter").navigation();
                }
            }
        });

        homeSeeMoreTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });

        homeSeeMoreBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 5).withInt("type", 1).navigation();
            }
        });

        //爆款推荐
        homeHotRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 4).withInt("type", 2).navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }

            }
        });
        //抖劵购买
        homeDouJuanBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/ShakeStockActivity").navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }

            }
        });
        //打卡签到
        homePunchSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/PunchSignActivity").navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }
            }
        });
        //今日免单
        homeFreeOfCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!TextUtils.isEmpty(SPUtil.getToken())) {
//                    ARouter.getInstance().build("/module_home/FreeChargeActivity").navigation();
//                } else {
//                    //是否登录
//                    PopUtils.isLogin(getContext());
//                }
                ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 3).navigation();

            }
        });
        //活动
        homeHuoDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///module_user_store/UserActivity
                ARouter.getInstance().build("/module_user_store/UserActivity").navigation();
            }
        });

        //设置上拉刷新下拉加载
        homeSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                nextPage = 1;
                //xBanner
                presenter.setXBanner(homeXbanner, homeTopBg);
                //优选recycler
                presenter.setGoodChoiceRec(homeGoodChoiceRec);
                //推荐recycler
                presenter.setBottomRec(nextPage, homeBottomRec);

            }
        });
        homeSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                nextPage++;
                presenter.setBottomRec(nextPage, homeBottomRec);
            }
        });

        homeNestedScroll.setOnScrollChangeListener(this);

        mGoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeNestedScroll.fullScroll(NestedScrollView.FOCUS_UP);
            }
        });

        homeSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/FlashSaleActivity").navigation();
            }
        });

    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见
            homeMarquee.stopFlipping();
            homeXbanner.stopAutoPlay();
//            countDownTimer.cancel();
//            countDownTimer.onFinish();
        } else {
            //可见
//            time();
            homeMarquee.startFlipping();
            homeXbanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        homeMarquee.stopFlipping();
        homeXbanner.stopAutoPlay();
//        countDownTimer.cancel();
//        countDownTimer.onFinish();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeMarquee.startFlipping();
        homeXbanner.startAutoPlay();
//        time();
    }

    @Override
    public void lodeMarquee(List<View> views) {
        homeMarquee.setViews(views);
    }

    @Override
    public void refreshSuccess() {
        homeSmartRefresh.finishLoadMore();
        homeSmartRefresh.finishRefresh();
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int[] ints = new int[2];
        homeBottomRec.getLocationOnScreen(ints);
        int y = ints[1];
        if (y <= 0) {
            mGoTop.setVisibility(View.VISIBLE);
        } else {
            mGoTop.setVisibility(View.GONE);
        }
    }

    private void time() {
        //获取当前时间
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH");// yyyy年MM月dd日 HH:mm:ss
        Date date1 = new Date(System.currentTimeMillis());
        format1 = simpleDateFormat1.format(date1);
        homeTime.setText(format1 + "点场");
        //获取之后的一个小时
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");// yyyy年MM月dd日 HH:mm:ss
        Date date = new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000);
        String format = simpleDateFormat.format(date);
        long afterTime = getTimeStamp(format + ":00:00", "yyyy-MM-dd HH:mm:ss");
        LogUtil.e("时间" + format + "-----------" + afterTime + "--------------" + (afterTime - System.currentTimeMillis()));
        //第一个参数表示总时间，第二个参数表示间隔时间。
        countDownTimer = new CountDownTimer(afterTime - System.currentTimeMillis(), 1000) {//第一个参数表示总时间，第二个参数表示间隔时间。
            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
                String dateString = formatter.format(millisUntilFinished);
                homeCountDown.setText("00:" + dateString);
                if (homeCountDown.getText().toString().contains("00:00:00")) {
                    time();
//                    presenter.initGoods(format1);
                }
            }

            @Override
            public void onFinish() {
                LogUtil.e("结束");
            }
        }.start();

    }

    /**
     * 时间转换为时间戳
     *
     * @param timeStr 时间 例如: 2016-03-09
     * @param format  时间对应格式  例如: yyyy-MM-dd
     * @return
     */
    public static long getTimeStamp(String timeStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
            long timeStamp = date.getTime();
            return timeStamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
