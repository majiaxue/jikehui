package com.example.commoditydetails.taobao;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerImageBean;
import com.example.bean.MXBean;
import com.example.bean.NewTBGoodsDetailsBean;
import com.example.bean.TBGoodChoiceBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.taobao.adapter.TBRecommendAdapter;
import com.example.common.CommonResource;
import com.example.dbflow.ShareOperationUtil;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import mtopsdk.common.util.StringUtils;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBCommodityDetailsPresenter extends BasePresenter<TBCommodityDetailsView> {

    private List<TBGoodChoiceBean.DataBean> tbRecommendList = new ArrayList<>();
    private TBLedSecuritiesBean tbLedSecuritiesBean;
    private String num_iid;
    private Bitmap bitmap;
    private NewTBGoodsDetailsBean tbGoodsDetailsBean;
    private int temp = 0;
    private MXBean recordBeans;

    public TBCommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void login(final String para, final String type) {
        final AlibcLogin alibcLogin = AlibcLogin.getInstance();

        if (!alibcLogin.isLogin()) {
            alibcLogin.showLogin(new AlibcLoginCallback() {

                @Override
                public void onSuccess(int loginResult, String openId, String userNick) {
                    LogUtil.e("获取淘宝用户信息: " + AlibcLogin.getInstance().getSession());
                    if ("share".equals(type)) {
                        ShareledSecurities(para);
                    } else if ("ling".equals(type)) {
                        ledSecurities(para);
                    }
                }



                @Override
                public void onFailure(int code, String msg) {
                    Toast.makeText(mContext, "登录失败 ", Toast.LENGTH_LONG).show();
                    ProcessDialogUtil.dismissDialog();
//                    if (getView() != null) {
//                        getView().finishLoad();
//                    }
//                    final SelfDialog selfDialog = new SelfDialog(mContext);
//                    selfDialog.setTitle("提示");
//                    selfDialog.setMessage("淘宝授权失败，请重试");
//                    selfDialog.setYesOnclickListener("取消", new SelfDialog.onYesOnclickListener() {
//                        @Override
//                        public void onYesClick() {
//                            selfDialog.dismiss();
//                            ((Activity) mContext).finish();
//                        }
//                    });
//
//                    selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
//                        @Override
//                        public void onNoClick() {
//                            temp = 1;
//                            login(para, type);
//                            selfDialog.dismiss();
//                        }
//                    });
//                    selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                        @Override
//                        public void onDismiss(DialogInterface dialog) {
//                            PopUtils.setTransparency(mContext, 1f);
//                            if (temp == 0) {
//                                ((Activity) mContext).finish();
//                            }
//                        }
//                    });
//
//                    PopUtils.setTransparency(mContext, 0.3f);
//                    selfDialog.show();
//                    temp = 0;
                }
            });
        } else {
            if ("share".equals(type)) {
                ShareledSecurities(para);
            } else if ("ling".equals(type)) {
                ledSecurities(para);
            }
        }
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

    private void shouQuan() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.SHOUQUAN, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("授权：" + result);
                SPUtil.addParm("link", result);
                ARouter.getInstance().build("/module_classify/shouquan").withString("url", result.replace("web", "wap")).navigation();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("授权：" + errorMsg);
            }
        }));
    }

    //初始化视图
    public void initView(final String goodsId) {
        Map num_iid = MapUtil.getInstance().addParms("goodsId", goodsId).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.GOODSDETAILS, num_iid);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult---------------->" + result);

                if (result.contains("\"code\":0")) {
                    tbGoodsDetailsBean = JSON.parseObject(result, new TypeReference<NewTBGoodsDetailsBean>() {
                    }.getType());
                    if (getView() != null) {
                        getView().tbBeanList(tbGoodsDetailsBean);
                        getView().tBDetails();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg---------------->" + errorMsg);
            }
        }));
    }

    public void historySave(String goodsId) {
        Map map = MapUtil.getInstance().addParms("productId", goodsId).addParms("userCode", SPUtil.getUserCode()).addParms("type", 3).build();
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
    public void setXBanner(XBanner commodityXbanner, final List<BannerImageBean> images) {

//        commodityXbanner.setData(images, null);
        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position));
                bannerImage.setImageURI(Uri.parse(images.get(position).getXBannerUrl()));
//                Glide.with(mContext).load(images.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        commodityXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        commodityXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
//        commodityXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(XBanner banner, Object model, View view, int position) {
//                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    //商品详情图
    public void setShopParticulars(RecyclerView shopParticulars, List<String> itemDetail) {

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        final CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, itemDetail, R.layout.itme_commodity_details_rec);//
//        linearLayoutManager.setAutoMeasureEnabled(true);
//        linearLayoutManager.setSmoothScrollbarEnabled(true);
        shopParticulars.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
//        shopParticulars.setHasFixedSize(true);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);

    }

    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, String id) {
        LogUtil.e("id------------->" + id);
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + id, SPUtil.getToken());

        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("是否收藏------->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("是否收藏失败------------>" + errorMsg);
            }
        }));
    }

    //收藏商品
    public void goodsCollect(final ImageView commodityCollectImage, String id) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Map map = MapUtil.getInstance().addParms("productId", id).addParms("type", 4).build();
            Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("收藏成功----->" + result);
                    if (result.equals("true")) {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                    } else {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("收藏失败----->" + errorMsg);

                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(String para) {
        LogUtil.e("----------------------->" + para);
        Map map = MapUtil.getInstance().addParms("para", para).addParms("flag", 0).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHead(CommonResource.TBKGOODSGETGYURLBYALL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("TBCommodityDetailsResult领劵--------->" + result);
                if (result.startsWith("{\"code\":3")) {
                    shouQuan();
                } else if (result.startsWith("{\"error\":15")) {
                    Map errorMap = new Gson().fromJson(result, Map.class);
                    LogUtil.e("errorMap---->" + errorMap.toString());
                    num_iid = (String) errorMap.get("num_iid");
                    jumpToTB("", 1);
//                    if (getView() != null) {
//                        getView().noCoupon(true);
//                    }
                } else {
                    String data1 = null;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        data1 = jsonObject.getString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    tbLedSecuritiesBean = JSON.parseObject(data1, new TypeReference<TBLedSecuritiesBean>() {
                    }.getType());
                    if (tbLedSecuritiesBean != null) {
                        if (getView() != null) {
                            LogUtil.e("成功:"+tbLedSecuritiesBean);
                            jumpToTB(tbLedSecuritiesBean.getCoupon_click_url(), 2);
                        }

                    }

                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("TBCommodityDetailsErrorMsg领劵--------->" + errorMsg);
            }
        }));
    }

    //获取分享url
    public void ShareledSecurities(String para) {
        LogUtil.e("----------------------->" + para);
        Map map = MapUtil.getInstance().addParms("para", para).addParms("flag", 1).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHead(CommonResource.TBKGOODSGETGYURLBYALL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("TBCommodityDetailsResult领劵--------->" + result);
                if (result.startsWith("{\"code\":3")) {
                    shouQuan();
                } else if (result.startsWith("{\"error\":15")) {
                    Map errorMap = new Gson().fromJson(result, Map.class);
                    LogUtil.e("errorMap---->" + errorMap.toString());
                    num_iid = (String) errorMap.get("num_iid");
//                    if (getView() != null) {
//                        getView().noCoupon(true);
//                    }
                } else {
                    String data1 = null;
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        data1 = jsonObject.getString("data");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    tbLedSecuritiesBean = JSON.parseObject(data1, new TypeReference<TBLedSecuritiesBean>() {
                    }.getType());
                    if (tbLedSecuritiesBean != null) {
                        if (getView() != null) {
                            LogUtil.e("成功");
                            if (StringUtils.isNotBlank(tbGoodsDetailsBean.getData().getMainPic())) {
                                if (!tbGoodsDetailsBean.getData().getMainPic().contains("https:")) {
                                    Glide.with(mContext)
                                            .asBitmap()
                                            .load("https:" + tbGoodsDetailsBean.getData().getMainPic())
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                                    saveImageToPhotos(bitmap);
                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                                }
                                            });
                                } else {
                                    Glide.with(mContext)
                                            .asBitmap()
                                            .load(tbGoodsDetailsBean.getData().getMainPic())
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                                    saveImageToPhotos(bitmap);
                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                                }
                                            });
                                }

                            } else {
                                if (!tbGoodsDetailsBean.getData().getMarketingMainPic().contains("https:")) {
                                    Glide.with(mContext)
                                            .asBitmap()
                                            .load("https:" + tbGoodsDetailsBean.getData().getMarketingMainPic())
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                                    saveImageToPhotos(bitmap);
                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                                }
                                            });
                                } else {
                                    Glide.with(mContext)
                                            .asBitmap()
                                            .load(tbGoodsDetailsBean.getData().getMarketingMainPic())
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                                    saveImageToPhotos(bitmap);
                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                                }
                                            });
                                }
                            }

                        }

                    }

                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("TBCommodityDetailsErrorMsg领劵--------->" + errorMsg);
            }
        }));
    }

    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "wwww" + ".jpg";
        final File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewToImage(tbLedSecuritiesBean.getCoupon_click_url(), file.getPath());

        LogUtil.e("图片路径" + file.getPath());
    }

    private void jumpToTB(String originUrl, int flag) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (flag == 2) {

                //提供给三方传递配置参数
                Map<String, String> exParams = new HashMap<>();
                exParams.put(AlibcConstants.ISV_CODE, "appisvcode");


                //设置页面打开方式
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Auto);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_112883640_11584347_72287650277");

                AlibcTrade.openByUrl((Activity) mContext, "", originUrl, null, null, null, showParams, taokeParams, exParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });

            } else if (flag == 1) {
                //提供给三方传递配置参数
                Map<String, String> exParams = new HashMap<>();
                exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

                //打开指定页面
                AlibcBasePage detailPage = new AlibcDetailPage(num_iid);

                //设置页面打开方式
                AlibcShowParams showParams = new AlibcShowParams(OpenType.Native);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_112883640_11584347_72287650277");

                AlibcTrade.openByBizCode((Activity) mContext, detailPage, null, null, null, "shop", showParams, taokeParams, exParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });

            }
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    public void setRecommendRec(final RecyclerView shopRecommendRec) {
        Map map = MapUtil.getInstance().addParms("page", 1).addParms("pagesize", 20).build();
        Observable data1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSPRODUCTS, map);
        RetrofitUtil.getInstance().toSubscribe(data1, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult推荐------------>" + result);
                TBGoodChoiceBean tbGoodChoiceBean = JSON.parseObject(result, new TypeReference<TBGoodChoiceBean>() {
                }.getType());

                if (tbGoodChoiceBean.getData() != null && tbGoodChoiceBean != null) {
                    tbRecommendList.clear();
                    tbRecommendList.addAll(tbGoodChoiceBean.getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    shopRecommendRec.setLayoutManager(linearLayoutManager);
                    TBRecommendAdapter goodsRecommendAdapter = new TBRecommendAdapter(mContext, tbRecommendList, R.layout.item_base_rec);
                    shopRecommendRec.setAdapter(goodsRecommendAdapter);

                    goodsRecommendAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            String startTime = MyTimeUtil.date2String(tbRecommendList.get(position).getCoupon_start_time() + "000");
                            String endTime = MyTimeUtil.date2String(tbRecommendList.get(position).getCoupon_end_time() + "000");
                            ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", tbRecommendList.get(position).getItem_id())
                                    .withString("shoptype", tbRecommendList.get(position).getUser_type())
                                    .withDouble("youhuiquan", Double.valueOf(tbRecommendList.get(position).getCoupon_amount()))
                                    .withString("coupon_start_time", startTime)
                                    .withString("coupon_end_time", endTime)
                                    .withString("commission_rate", tbRecommendList.get(position).getCommission_rate())
                                    .withInt("type", 1)
                                    .navigation();
                        }
                    });
                } else {
                    LogUtil.e("数据为空");
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg推荐------------>" + errorMsg);
            }
        }));
    }

    //加载生成图片布局
    public void viewToImage(String qRImage, String path) {
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
        preferentialPrice.setText("￥" + tbGoodsDetailsBean.getData().getActualPrice());//优惠价
        originalPrice.setText("原价：￥" + tbGoodsDetailsBean.getData().getOriginalPrice());//原价
        couponPrice.setText("￥" + tbGoodsDetailsBean.getData().getCouponPrice());
        LogUtil.e("url主图---------->" + tbGoodsDetailsBean.getData().getMainPic());
        image.setImageURI(Uri.fromFile(new File(path)));
        name.setText(tbGoodsDetailsBean.getData().getTitle());
        number.setText("已售" + tbGoodsDetailsBean.getData().getMonthSales() + "件");//已售
        Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
        qRCode.setImageBitmap(qr);
        LogUtil.e("url2二维码---------->" + qRImage);

        this.bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        share();
    }

    //分享
    public void share() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setTitleText("分享到")
                .setTitleTextColor(Color.parseColor("#222222"))
                .setMenuItemTextColor(Color.parseColor("#666666"))
                .setMenuItemIconPressedColor(Color.parseColor("#000000"))
//                .setMenuItemBackgroundColor(Color.parseColor("#fd3c15"),Color.parseColor("#008577"))
                .setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE, (int) mContext.getResources().getDimension(R.dimen.dp_20));
//                .setCancelButtonText("您取消了分享");


        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)// SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
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


//    private void redirectUrl(String url, final OnSuccessListener onSuccessListener) {
//        new AsyncTask<String, Integer, String>() {
//            @Override
//            protected String doInBackground(String... strings) {
//                HttpURLConnection conn = null;
//                try {
//                    conn = (HttpURLConnection) new URL(strings[0]).openConnection();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                conn.setInstanceFollowRedirects(false);
//                conn.setConnectTimeout(5000);
//                String url = conn.getHeaderField("Location");
//                conn.disconnect();
//                return url;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                onSuccessListener.doLogic(s);
//
//            }
//        }.execute(url);
//    }
//
//    private interface OnSuccessListener {
//        void doLogic(String s);
//
//    }


}