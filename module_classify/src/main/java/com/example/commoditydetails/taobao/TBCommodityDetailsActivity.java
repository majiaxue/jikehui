package com.example.commoditydetails.taobao;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.BannerImageBean;
import com.example.bean.MXBean;
import com.example.bean.NewTBGoodsDetailsBean;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.module_base.ModuleBaseApplication;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.ArithUtil;
import com.example.utils.CustomDialog;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kongzue.dialog.v3.WaitDialog;
import com.stx.xhb.xbanner.XBanner;
import com.umeng.socialize.UMShareAPI;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mtopsdk.common.util.StringUtils;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:淘宝商品详情
 */
@Route(path = "/module_classify/TBCommodityDetailsActivity")
public class TBCommodityDetailsActivity extends BaseActivity<TBCommodityDetailsView, TBCommodityDetailsPresenter> implements TBCommodityDetailsView, NestedScrollView.OnScrollChangeListener {

    @BindView(R2.id.commodity_image_back)
    ImageView commodityImageBack;
    @BindView(R2.id.commodity_xbanner)
    XBanner commodityXbanner;
    @BindView(R2.id.commodity_name)
    TextView commodityName;
    @BindView(R2.id.commodity_text)
    TextView commodityText;
    @BindView(R2.id.commodity_preferential_price)
    TextView commodityPreferentialPrice;
    @BindView(R2.id.commodity_earnings)
    TextView commodityEarnings;
    @BindView(R2.id.commodity_original_price)
    TextView commodityOriginalPrice;
    @BindView(R2.id.commodity_number_sold)
    TextView commodityNumberSold;
    @BindView(R2.id.commodity_shop_image)
    SimpleDraweeView commodityShopImage;
    @BindView(R2.id.commodity_shop_name)
    TextView commodityShopName;
    @BindView(R2.id.shop_describe_score)
    TextView shopDescribeScore;
    @BindView(R2.id.shop_service_score)
    TextView shopServiceScore;
    @BindView(R2.id.shop_logistics_score)
    TextView shopLogisticsScore;
    @BindView(R2.id.shop_particulars)
    RecyclerView shopParticulars;
    @BindView(R2.id.shop_recommend_rec)
    RecyclerView shopRecommendRec;
    @BindView(R2.id.commodity_nested_scroll)
    NestedScrollView commodityNestedScroll;
    @BindView(R2.id.commodity_stick)
    ImageView commodityStick;
    @BindView(R2.id.commodity_go_home)
    LinearLayout commodityGoHome;
    @BindView(R2.id.commodity_collect_image)
    ImageView commodityCollectImage;
    @BindView(R2.id.commodity_collect)
    LinearLayout commodityCollect;
    @BindView(R2.id.commodity_share)
    LinearLayout commodityShare;
    @BindView(R2.id.commodity_led_securities)
    LinearLayout commodityLedSecurities;
    @BindView(R2.id.commodity_linear)
    LinearLayout commodityLinear;
    @BindView(R2.id.commodity_into_shop)
    TextView commodityIntoShop;
    @BindView(R2.id.commodity_coupon_price)
    TextView commodityCouponPrice;
    @BindView(R2.id.commodity_time)
    TextView commodityTime;
    @BindView(R2.id.commodity_immediately_receive)
    TextView commodityImmediatelyReceive;
    @BindView(R2.id.commodity_led_securities_text)
    TextView commodityLedSecuritiesText;
    @BindView(R2.id.commodity_details_no_coupon)
    LinearLayout commodityDetailsNoCoupon;
    @BindView(R2.id.shop_xinxi)
    LinearLayout shopXinxi;

    @Autowired(name = "para")
    String para;
    @Autowired(name = "type")
    int type;

    private int status = 0;
    private MXBean bean=new MXBean();

    private List<BannerImageBean> bannerImageBeans = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    //触碰标识
    private long exitTime = 0;
    private double mul;
    private int points;
    private TextView popDuihuan;
    private TextView tvOk;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        LogUtil.e("传数据:---------->ID:" + para);
        ModuleBaseApplication.initShare();
        shopXinxi.setVisibility(View.GONE);
        commodityIntoShop.setVisibility(View.GONE);

        ProcessDialogUtil.showProcessDialog(this);

//        presenter.login(para);

        //加载视图
        presenter.initView(para);

        //字体加中划线
        commodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        //字体加粗
        commodityCouponPrice.getPaint().setFakeBoldText(true);
        //店铺头像
        commodityShopImage.setImageResource(R.drawable.img_taobao);
        //推荐商品
        presenter.setRecommendRec(shopRecommendRec);

    }


    @Override
    public void initClick() {
        //返回上个页面
        commodityImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //监听    NestedScrollView
        commodityNestedScroll.setOnScrollChangeListener(this);
        //点击回到顶部
        commodityStick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commodityNestedScroll.fullScroll(NestedScrollView.FOCUS_UP);
            }
        });
        //立即领取
        commodityImmediatelyReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(SPUtil.getToken())) {
                    PopUtils.isLogin(TBCommodityDetailsActivity.this);
                }else {
                    popupwindow();
                }
            }
        });
        //回到首页
        commodityGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/main").navigation();
            }
        });


        //分享
        commodityShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == status) {
                    if ((System.currentTimeMillis() - exitTime) > 3000) {
                        ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                        presenter.login(para, "share");
                        exitTime = System.currentTimeMillis();
                    } else {
                        Toast.makeText(TBCommodityDetailsActivity.this, "图片生成中!请勿重复点击", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                if (TextUtils.isEmpty(SPUtil.getToken())) {
                    PopUtils.isLogin(TBCommodityDetailsActivity.this);
                }else {
                    popupwindow();
                }



            }
        });
        //收藏
        commodityCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goodsCollect(commodityCollectImage, para);
            }
        });
        shopParticulars.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Fresco.getImagePipeline().pause();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Fresco.getImagePipeline().pause();
                        break;
                    case MotionEvent.ACTION_UP:
                        Fresco.getImagePipeline().resume();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    //点击领券弹出popupwindow
    private void popupwindow() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_dui_jifen, null);
        ImageView duiHuanVipClose = inflate.findViewById(R.id.dui_huan_vip_close);//取消按钮
        TextView tvQuXiao = inflate.findViewById(R.id.tv_cancel);   //取消按钮
        tvOk = inflate.findViewById(R.id.tv_ok);        //popupwindow的确认按钮
        popDuihuan = inflate.findViewById(R.id.duihuanjifenba);

        //设置popupwindow中textview的字体颜色
        SpannableString spannableString = new SpannableString(String.valueOf(points));
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#fa6a13"));
        spannableString.setSpan(colorSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //不能用 popDuihuan.setText(spannableString+"空间的咖啡店的");这种方式进行拼接，否则字体颜色设置失效
        popDuihuan.append("您将消耗");
        popDuihuan.append(points+"");
        popDuihuan.append("元购物金兑换");
        popDuihuan.append(spannableString);
        popDuihuan.append("元优惠券");
        LogUtil.e("优惠券的兑换----------"+points);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(this), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(this, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(TBCommodityDetailsActivity.this, 1f);
            }
        });
        tvQuXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        duiHuanVipClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断用户积分是否大于等于优惠券的价格
                if (Double.parseDouble(SPUtil.getStringValue(CommonResource.INTEGRATION))<points){
                    Toast.makeText(TBCommodityDetailsActivity.this, "购物金余额不足", Toast.LENGTH_SHORT).show();
                }else {
                    ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                 String points2= String.valueOf(points);
                 Double.parseDouble(points2);
                    //兑换积分的接口
                    presenter.duiPoints( Double.parseDouble(points2));
                    //淘宝授权转链的接口
                    presenter.login(para, "ling");
                }
            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
        int[] ints = new int[2];
        commodityLinear.getLocationOnScreen(ints);
        int y = ints[1];
        if (y <= commodityImageBack.getHeight()) {
            //显示
            commodityStick.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            commodityStick.setVisibility(View.GONE);
        }

        if (y <= commodityName.getHeight()) {
            commodityXbanner.stopAutoPlay();
        } else {
            commodityXbanner.startAutoPlay();
        }
    }

    @Override
    public TBCommodityDetailsView createView() {
        return this;
    }

    @Override
    public TBCommodityDetailsPresenter createPresenter() {
        return new TBCommodityDetailsPresenter(this);
    }

    //详情回调
    @Override
    public void tbBeanList(NewTBGoodsDetailsBean tbGoodsDetailsBean) {
//        customDialog.dismiss();
        try {
//            this.tbGoodsDetailsBean = tbGoodsDetailsBean;
            //轮播图
            if (StringUtils.isNotBlank(tbGoodsDetailsBean.getData().getImgs())) {
                String[] imgs = tbGoodsDetailsBean.getData().getImgs().split(",");
                for (int i = 0; i < imgs.length; i++) {
                    bannerImageBeans.add(new BannerImageBean(imgs[i]));
                }
            }
            presenter.setXBanner(commodityXbanner, bannerImageBeans);

            commodityName.setText(tbGoodsDetailsBean.getData().getTitle());//名字
            commodityNumberSold.setText("已售" + tbGoodsDetailsBean.getData().getMonthSales() + "件");//已售
            commodityShopName.setText(tbGoodsDetailsBean.getData().getShopName() + "");//商家名
            commodityCouponPrice.setText("可用购物金"+tbGoodsDetailsBean.getData().getCouponPrice()+"元");
            points=tbGoodsDetailsBean.getData().getCouponPrice();
            commodityTime.setText("使用期限：" + tbGoodsDetailsBean.getData().getCouponStartTime().split(" ")[0] + "~" + tbGoodsDetailsBean.getData().getCouponEndTime().split(" ")[0]);
            if (tbGoodsDetailsBean.getData().getCommissionRate() < 0) {
                if (type == 0) {
                    mul = tbGoodsDetailsBean.getData().getActualPrice() * (tbGoodsDetailsBean.getData().getCommissionRate() / 100) * 0.9;
                } else {
                    mul = tbGoodsDetailsBean.getData().getActualPrice() * (tbGoodsDetailsBean.getData().getCommissionRate() / 10000) * 0.9;
                }
            } else {
                mul = tbGoodsDetailsBean.getData().getActualPrice() * (tbGoodsDetailsBean.getData().getCommissionRate() <= 0 ? 0 : tbGoodsDetailsBean.getData().getCommissionRate() / 100) * 0.9;
            }
            commodityPreferentialPrice.setText("￥" + tbGoodsDetailsBean.getData().getActualPrice());//优惠价
            commodityOriginalPrice.setText("原价：￥" + tbGoodsDetailsBean.getData().getOriginalPrice());//原价
            commodityEarnings.setText("预估收益：￥" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));//收益
            LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + tbGoodsDetailsBean.getData().getCommissionRate() + "商品优惠后" + tbGoodsDetailsBean.getData().getActualPrice() + "最终收益" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));

            //商品详情图片
            if (StringUtils.isNotBlank(tbGoodsDetailsBean.getData().getDetailPics())) {
                String[] detailPics = tbGoodsDetailsBean.getData().getDetailPics().split(",");
                for (int i = 0; i < detailPics.length; i++) {
                    if (!detailPics[0].contains("https:")) {
                        images.add("https:" + detailPics[i]);
                    } else {
                        images.add(detailPics[i]);
                    }
                }
            } else {
                String[] imgs = tbGoodsDetailsBean.getData().getImgs().split(",");
                for (int i = 0; i < imgs.length; i++) {
                    images.add(imgs[i]);
                }
            }
            presenter.setShopParticulars(shopParticulars, images);

            //浏览历史
            presenter.historySave(para);
            //收藏状态
            presenter.isCollect(commodityCollectImage, para);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("异常了");
        }
    }

    @Override
    public void tBDetails() {
        status++;
    }

    @Override
    public void noCoupon(boolean noCoupon) {
        if (noCoupon) {
//            customDialog.dismiss();
            commodityDetailsNoCoupon.setVisibility(View.GONE);
            commodityEarnings.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadData(MXBean recordBeans) {
        this.bean=recordBeans;
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.e("HomeFragment" + "不可见");
        commodityXbanner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("HomeFragment" + "可见");
        commodityXbanner.startAutoPlay();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
