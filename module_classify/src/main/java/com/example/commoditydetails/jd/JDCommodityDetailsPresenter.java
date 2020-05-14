package com.example.commoditydetails.jd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.JDLedSecuritiesBean;
import com.example.bean.JDListBean;
import com.example.commoditydetails.jd.adapter.JDRecAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.webview.WebViewActivity;
import com.example.common.CommonResource;
import com.example.dbflow.ShareOperationUtil;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kepler.jd.Listener.OpenAppAction;
import com.kepler.jd.login.KeplerApiManager;
import com.kepler.jd.sdk.bean.KelperTask;
import com.kepler.jd.sdk.bean.KeplerAttachParameter;
import com.kepler.jd.sdk.exception.KeplerBufferOverflowException;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDCommodityDetailsPresenter extends BasePresenter<JDCommodityDetailsView> {

    private List<String> images = new ArrayList<>();
    private List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList = new ArrayList<>();
    private Bitmap bitmap;
    private JDListBean jDGoodsRecBean;

    /**
     * 超时时间设定
     */
    public static final int timeOut = 15;

    /**
     * 这个是即时性参数  可以设置
     */
    KeplerAttachParameter mKeplerAttachParameter = new KeplerAttachParameter();

    Handler mHandler = new Handler();
    /**
     * 网络请求对象
     */
    KelperTask mKelperTask;


    OpenAppAction mOpenAppAction = new OpenAppAction() {
        @Override
        public void onStatus(final int status) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (status == OpenAppAction.OpenAppAction_start) {//开始状态未必一定执行，
//                        dialogShow();
                    } else {
                        mKelperTask = null;
//                        dialogDiss();
                    }
                }
            });
        }
    };

    public JDCommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
    public void duiPoints(double integral) {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);
        Map build = MapUtil.getInstance().addParms("integral", integral).build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postHead(CommonResource.DUIHUANYOUHUiQUAN, build, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("积分兑换券：" + result);
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------------" + errorMsg);
            }
        }));
    }
    public void historySave(String goodsId) {
        Map map = MapUtil.getInstance().addParms("productId", goodsId).addParms("userCode", SPUtil.getUserCode()).addParms("type", 2).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.HISTORYSAVE, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("添加浏览记录" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("添加浏览记录errorMsg" + errorMsg);
            }
        }));

    }

    //商品轮播图
    public void setXBanner(XBanner commodityXbanner) {
        commodityXbanner.setData(images, null);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mContext).load(images.get(position)).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        commodityXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        commodityXbanner.setPageChangeDuration(1000);

    }

    //商品详情图
    public void setShopParticulars(RecyclerView shopParticulars, final JDGoodsRecBean.DataBean.ListsBean listsBeanList) {
        List<JDGoodsRecBean.DataBean.ListsBean.ImageInfoBean.ImageListBean> imageList = listsBeanList.getImageInfo().getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            images.add(imageList.get(i).getUrl());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        shopParticulars.setHasFixedSize(true);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }


    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, String skuid) {
        LogUtil.e("id------------->" + skuid);
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + skuid, SPUtil.getToken());

        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult收藏------->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg收藏------------>" + errorMsg);
            }
        }));
    }

    //收藏商品
    public void goodsCollect(final ImageView commodityCollectImage, String skuid) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Map map = MapUtil.getInstance().addParms("productId", skuid).addParms("type", 3).build();
            Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("TBCommodityDetailsResult点击收藏----->" + result);
                    if (result.equals("true")) {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                    } else {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("TBCommodityDetailsErrorMsg点击收藏----->" + errorMsg);

                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(String url, String couponUrl) {
        Map map = MapUtil.getInstance().addParms("materialId", url).addParms("userCode", SPUtil.getUserCode()).addParms("couponUrl", couponUrl).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postData(CommonResource.JDGETGOODSMARKETLINK, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("京东转链------》" + result);
                JDLedSecuritiesBean jdLedSecuritiesBean = JSON.parseObject(result, new TypeReference<JDLedSecuritiesBean>() {
                }.getType());
                if (jdLedSecuritiesBean != null && jdLedSecuritiesBean.getData() != null) {
                    String clickURL = jdLedSecuritiesBean.getData().getClickURL();
                    LogUtil.e("url---------->" + clickURL);
                    if (getView() != null) {
                        getView().qrImage(clickURL);
                    }

                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    //点击领劵
    public void clickLedSecurities(String clickURL) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            try {
                mKelperTask = KeplerApiManager.getWebViewService().openJDUrlPage(clickURL, mKeplerAttachParameter, mContext, mOpenAppAction, timeOut);
            } catch (KeplerBufferOverflowException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }

//        if (!TextUtils.isEmpty(SPUtil.getToken())) {
//
//            Intent intent = new Intent(mContext, WebViewActivity.class);
//            intent.putExtra("url", clickURL);
//            mContext.startActivity(intent);
//        } else {
//            ARouter.getInstance().build("/mine/login").navigation();
//        }
    }


    public void setRecommendRec(final RecyclerView shopRecommendRec, String name) {
        Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", 1).addParms("pageSize", 20).addParms("isHot", 1).addParms("keyword", name).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult京东推荐商品--------------->" + result);
                final JDListBean jDGoodsRecBean = JSON.parseObject(result, new TypeReference<JDListBean>() {
                }.getType());
                if (jDGoodsRecBean != null) {
                    if (jDGoodsRecBean.getData() != null && jDGoodsRecBean.getData() != null && jDGoodsRecBean.getData().size() != 0) {
                        if (getView() != null) {
                            getView().isNoGoods(false);
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                        JDRecAdapter jdRecAdapter = new JDRecAdapter(mContext, jDGoodsRecBean.getData(), R.layout.item_base_rec);
                        shopRecommendRec.setLayoutManager(linearLayoutManager);
                        shopRecommendRec.setAdapter(jdRecAdapter);

                        jdRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", listsBeanList.get(position).getSkuId())
                                        .withSerializable("jDGoodsRecBean", jDGoodsRecBean)
                                        .withInt("position", position)
                                        .navigation();
                            }
                        });
                    } else {
                        if (getView() != null) {
                            getView().isNoGoods(true);
                        }
                    }

                } else {
                    LogUtil.e("空");
                }
            }


            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg京东推荐商品--------------->" + errorMsg);
            }
        }));
    }

    //加载生成图片布局
    public void viewToImage(JDListBean.DataBean listsBean, String qRImage, String path) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.sharebg, null, false);
        ImageView image = view.findViewById(R.id.share_image);
        TextView name = view.findViewById(R.id.share_name);
        TextView preferentialPrice = view.findViewById(R.id.share_preferential_price);
        TextView originalPrice = view.findViewById(R.id.share_original_price);
        TextView couponPrice = view.findViewById(R.id.share_coupon_price);
        TextView number = view.findViewById(R.id.share_number);
        ImageView qRCode = view.findViewById(R.id.share_qr_code);
        //字体加中划线
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        double div;
        if (listsBean.getCouponInfo().getCouponList().size() > 0) {
            div = ArithUtil.sub(Double.valueOf(listsBean.getPriceInfo().getPrice()), Double.valueOf(listsBean.getCouponInfo().getCouponList().get(0).getDiscount()));//到手价
        } else {
            div = listsBean.getPriceInfo().getPrice();
        }
        LogUtil.e("url主图---------->" + listsBean.getImageInfo().getImageList().get(0).getUrl());
        image.setImageURI(Uri.fromFile(new File(path)));
        name.setText(listsBean.getSkuName());
        preferentialPrice.setText("￥" + div);
        originalPrice.setText("￥" + Double.valueOf(listsBean.getPriceInfo().getPrice()));
        couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(listsBean.getPriceInfo().getPrice()), div) + "元");
        number.setText("已售" + listsBean.getInOrderCount30Days() + "件");//已售
        Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
        qRCode.setImageBitmap(qr);
        LogUtil.e("url2二维码---------->" + qRImage);

        this.bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
    }

    //分享
    public void share() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setTitleText("分享到")
                .setTitleTextColor(Color.parseColor("#222222"))
                .setMenuItemTextColor(Color.parseColor("#666666"))
                .setMenuItemIconPressedColor(Color.parseColor("#000000"))
                .setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE, (int) mContext.getResources().getDimension(R.dimen.dp_20));
        LogUtil.e("bitmap" + bitmap);

        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)// SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                .setCallback(shareListener).open(config);

    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LogUtil.e("start:" + share_media.toString());
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtil.e("result:" + share_media.toString());
            ShareOperationUtil.getShareOperationUtil().createOrUpdate();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
        }
    };

    public void loadData(String skuid) {
        Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("skuIds", skuid).addParms("keyword", "").build();

        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("SecondaryDetailsResult京东商品--------------->" + result);
                ProcessDialogUtil.dismissDialog();
                try {
                    org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                    String code = jsonObject.optString("code");
                    if ("-1".equals(code)) {
                        Toast.makeText(mContext, "获取数据失败", Toast.LENGTH_SHORT).show();
                    } else if ("200".equals(code)) {
                        jDGoodsRecBean = JSON.parseObject(result, JDListBean.class);


                        List<JDListBean.DataBean.ImageInfoBean.ImageListBean> imageList = jDGoodsRecBean.getData().get(0).getImageInfo().getImageList();
                        for (int i = 0; i < imageList.size(); i++) {
                            images.add(imageList.get(i).getUrl());
                        }

                        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
                        if (getView() != null) {
                            getView().loadUI(jDGoodsRecBean, commodityDetailsRecAdapter);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("SecondaryDetailsErrorMsg京东商品--------------->" + errorMsg);
                ProcessDialogUtil.dismissDialog();
            }
        }));
    }

}
