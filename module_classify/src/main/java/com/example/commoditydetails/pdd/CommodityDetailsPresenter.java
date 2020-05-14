package com.example.commoditydetails.pdd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CommodityDetailsBean;
import com.example.bean.CommodityDetailsPddRecBean;
import com.example.bean.LedSecuritiesBean;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsPddRecAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.webview.WebViewActivity;
import com.example.common.CommonResource;
import com.example.dbflow.ShareBean;
import com.example.dbflow.ShareOperationUtil;
import com.example.dbflow.ShareUtil;
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
import com.example.utils.ProcessDialogUtil;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityDetailsPresenter extends BasePresenter<CommodityDetailsView> {

    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList = new ArrayList<>();
    private List<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> topGoodsList = new ArrayList<>();
    private LedSecuritiesBean ledSecuritiesBean;
    private Bitmap bitmap;

    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(String goods_id) {
        Map map = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.PDDGOODSDETAIL + "/" + goods_id, map);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult详情------------>" + result);
                CommodityDetailsBean commodityDetailsBean = JSON.parseObject(result, new TypeReference<CommodityDetailsBean>() {
                }.getType());
                if (commodityDetailsBean != null && commodityDetailsBean.getGoods_detail_response() != null && commodityDetailsBean.getGoods_detail_response().getGoods_details().size() != 0) {
                    beanList.clear();
                    beanList.addAll(commodityDetailsBean.getGoods_detail_response().getGoods_details());
                    if (getView() != null) {
                        getView().CommodityDetailsList(beanList);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg详情------------>" + errorMsg);
            }
        }));
    }

    public void historySave(String goodsId) {
        Map map = MapUtil.getInstance().addParms("productId", goodsId).addParms("userCode", SPUtil.getUserCode()).addParms("type", 1).build();
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
    public void setXBanner(XBanner commodityXbanner, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        final List<String> images = beanList.get(0).getGoods_gallery_urls();

        commodityXbanner.setData(images, null);
//        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
                Glide.with(mContext).load(images.get(position)).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
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
    public void setShopParticulars(RecyclerView shopParticulars, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        final List<String> images = beanList.get(0).getGoods_gallery_urls();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setNestedScrollingEnabled(false);
        shopParticulars.setHasFixedSize(true);
        shopParticulars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Fresco.getImagePipeline().resume();
                } else {
                    Fresco.getImagePipeline().pause();
                }
//                // 查看源码可知State有三种状态：SCROLL_STATE_IDLE（静止）、SCROLL_STATE_DRAGGING（上升）、SCROLL_STATE_SETTLING（下落）
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源，极大提升流畅度
//                    commodityDetailsRecAdapter.setScrolling(false);
//                    commodityDetailsRecAdapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
//                } else
//                    mRecyclerViewAdapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }

    public void goodsCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Map map = MapUtil.getInstance().addParms("productId", beanList.get(0).getGoods_id()).addParms("type", 2).build();
            Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("CommodityDetailsResult点击收藏----->" + result);
                    if (result.equals("true")) {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                    } else {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("CommodityDetailsErrorMsg点击收藏----->" + errorMsg);

                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(long id) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCOUPON + "/" + SPUtil.getUserCode() + "/" + id);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult领劵------------>" + result);
                ledSecuritiesBean = JSON.parseObject(result, new TypeReference<LedSecuritiesBean>() {
                }.getType());

                if (ledSecuritiesBean != null && ledSecuritiesBean.getGoods_promotion_url_generate_response() != null && ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().size() != 0) {
                    if (getView() != null) {
                        getView().imageUri(ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().get(0).getWe_app_web_view_url());
                    }
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg领劵------------>" + errorMsg);
            }
        }));
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
    //web
    public void jumpToWeb(String imageUrl) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("url", imageUrl);
            mContext.startActivity(intent);
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        LogUtil.e("id------------->" + beanList.get(0).getGoods_id());
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + beanList.get(0).getGoods_id(), SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsIsCollectResult是否收藏------->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsIsCollectErrorMsg收藏------------>" + errorMsg);
            }
        }));
    }

    //推荐
    public void setRecommendRec(final RecyclerView shopRecommendRec) {

        Map map = MapUtil.getInstance().addParms("limit", 20).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TOPGOODS, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult推荐----->" + result);
//                CommodityDetailsPddRecBean commodityDetailsPddRecBean = JSON.parseObject(result, new TypeReference<CommodityDetailsPddRecBean>() {
//                }.getType());
                CommodityDetailsPddRecBean commodityDetailsPddRecBean = new Gson().fromJson(result, CommodityDetailsPddRecBean.class);
                LogUtil.e("CommodityDetailsResult推荐Bean" + commodityDetailsPddRecBean);
                if (commodityDetailsPddRecBean != null && commodityDetailsPddRecBean.getTop_goods_list_get_response() != null) {
                    topGoodsList.clear();
                    topGoodsList.addAll(commodityDetailsPddRecBean.getTop_goods_list_get_response().getList());
                    for (int i = topGoodsList.size() - 1; i >= 0; i--) {
                        if (topGoodsList.get(i).getCoupon_discount() == 0) {
                            topGoodsList.remove(i);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    shopRecommendRec.setLayoutManager(linearLayoutManager);
                    CommodityDetailsPddRecAdapter pddRecAdapter = new CommodityDetailsPddRecAdapter(mContext, topGoodsList, R.layout.item_base_rec);
                    shopRecommendRec.setAdapter(pddRecAdapter);

                    pddRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/CommodityDetailsActivity")
                                    .withString("goods_id", topGoodsList.get(position).getGoods_id() + "")
                                    .navigation();
                        }
                    });

                    pddRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance()
                                            .build("/module_classify/CommodityDetailsActivity")
                                            .withString("goods_id", topGoodsList.get(index).getGoods_id() + "")
                                            .navigation();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg推荐----->" + errorMsg);
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
        double div = ArithUtil.div(beanList.get(0).getMin_group_price() - beanList.get(0).getCoupon_discount(), 100, 1);//到手价
        LogUtil.e("url主图---------->" + beanList.get(0).getGoods_gallery_urls().get(0));
        String s = beanList.get(0).getGoods_gallery_urls().get(0);
        image.setImageURI(Uri.fromFile(new File(path)));
//        Glide.with(mContext)
//                .load(s)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .placeholder(R.drawable.icon_logo)
//                .error(R.drawable.icon_chahao)
//                .into(image);

        LogUtil.e("url1轮播图---------->" + s);

        name.setText(beanList.get(0).getGoods_name());
        preferentialPrice.setText("￥" + div);
        originalPrice.setText("￥" + ArithUtil.div(beanList.get(0).getMin_group_price(), 100, 1));
        couponPrice.setText("￥" + ArithUtil.sub(ArithUtil.div(beanList.get(0).getMin_group_price(), 100, 1), div) + "元");
        number.setText("已售" + beanList.get(0).getSold_quantity() + "件");//已售
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
        LogUtil.e("bitmap" + this.bitmap);

        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, this.bitmap))
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)// SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
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

}
