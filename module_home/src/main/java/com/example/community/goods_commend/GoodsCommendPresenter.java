package com.example.community.goods_commend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CommunityLocalBean;
import com.example.bean.GoodsCommendBean;
import com.example.bean.LedSecuritiesBean;
import com.example.bean.TitleBean;
import com.example.common.CommonResource;
import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnSuccessListener;
import com.example.utils.PopUtils;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.TBUtil;
import com.example.utils.ViewToBitmap;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class GoodsCommendPresenter extends BasePresenter<GoodsCommendView> {

    private List<CommunityLocalBean> dataList = new ArrayList<>();
    private GoodsCommendAdapter commendAdapter;
    //    public static int type = 0;   //淘宝：0  京东：1  拼多多：2
    private GoodsCommendBean commendBean;
    private TBUtil tbUtil = new TBUtil();

    private int number = 0;
    private View btn;


    public GoodsCommendPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTitle() {
        final List<TitleBean> titleList = new ArrayList<>();
        titleList.add(new TitleBean("淘宝", true));
        titleList.add(new TitleBean("京东", false));
        titleList.add(new TitleBean("拼多多", false));

        final CommendTitleAdapter titleAdapter = new CommendTitleAdapter(mContext, titleList, R.layout.rv_commend_title);

        titleAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
//                type = position;
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.get(i).setCheck(i == position);
                }
                titleAdapter.notifyDataSetChanged();
                getView().changeType();
            }
        });

        if (getView() != null) {
            getView().loadTitle(titleAdapter);
        }
    }

    /**
     * community_type
     *
     * @param page
     */
    public void initData(final int page) {
        Map map = MapUtil.getInstance().addParms("community_type", "0").addParms("mall_type", 0).addParms("page", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.COMMUNITY, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("商品推荐：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    commendBean = JSON.parseObject(result, GoodsCommendBean.class);
                    if (page == 1) {
                        dataList.clear();
                    }
                    List<CommunityLocalBean> local = commendBean.getLocal();
                    List<GoodsCommendBean.NetBean> net = commendBean.getNet();
                    if (local.size() > 0) {
                        for (int i = 0; i < local.size(); i++) {
                            if (local.get(i).getItempic() != null) {
                                String itempic = local.get(i).getItempic();
                                String[] split = itempic.split(",");
                                List<String> list = new ArrayList<>();
                                for (int j = 0; j < split.length; j++) {
                                    list.add(split[j]);
                                }
                                local.get(i).setPics(list);
                            }
                        }
                        dataList.addAll(local);
                    }
                    if (net.size() > 0) {
                        for (int i = 0; i < net.size(); i++) {
                            CommunityLocalBean bean = new CommunityLocalBean();
                            bean.setId(net.get(i).getItemid());
                            bean.setItemprice(net.get(i).getItemprice());
                            bean.setItempic(net.get(i).getSola_image());
                            bean.setItemtitle(net.get(i).getItemtitle());
                            bean.setCouponurl(net.get(i).getCouponurl());
                            bean.setContent(net.get(i).getContent());
                            bean.setCopyContent(net.get(i).getCopy_content());
                            bean.setTkrates(net.get(i).getTkrates());
                            bean.setCouponmoney(net.get(i).getCouponmoney());
                            bean.setDummyClickStatistics(net.get(i).getDummy_click_statistics());
                            bean.setSellerIcon(net.get(i).getSola_image());
                            bean.setTime(net.get(i).getShow_time());
                            bean.setPics(net.get(i).getItempic());
                            dataList.add(bean);
                        }
                    }

                    if (commendAdapter == null) {
                        commendAdapter = new GoodsCommendAdapter(mContext, dataList, R.layout.rv_goods_commend);
                        if (getView() != null) {
                            getView().loadContent(commendAdapter);
                        }
                    } else {
                        commendAdapter.notifyDataSetChanged();
                    }

                    commendAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                        btn = v;
                                        v.setEnabled(false);
//                                        if (type == 0) {
                                        tbZL(dataList.get(index).getId());
//                                        } else if (type == 1) {
//
//                                        } else if (type == 2) {
//                                            ledPdd(dataList.get(index).getId());
//                                        }

                                        getView().loadShareInfo(dataList.get(index));
                                    } else {
                                        PopUtils.isLogin(mContext);
                                    }
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----------------" + errorMsg);
            }
        }));
    }

    private void tbZL(String id) {
        ledTb(id);
        tbUtil.login(mContext, new OnSuccessListener() {
            @Override
            public void onSuccess() {
                number++;
                if (number == 2) {
                    btn.setEnabled(true);
                }
            }
        });
    }

    public void ledTb(String para) {
        Map map = MapUtil.getInstance().addParms("para", para).addParms("flag", "1").build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHead(CommonResource.TBKGOODSGETGYURLBYALL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("淘宝转链--------->" + result);
                number++;
                if (result.startsWith("{\"code\":3")) {
                    if (number == 2) {
                        tbUtil.shouQuan();
                        btn.setEnabled(true);
                    }
                } else {
                    Map parseObject = JSON.parseObject(result, Map.class);
                    String couponUrl = (String) parseObject.get("coupon_click_url");
                    Bitmap bitmap = QRCode.createQRImage(couponUrl, DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
                    getView().loadQR(bitmap);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                btn.setEnabled(true);
                LogUtil.e(errorCode + "---------------" + errorMsg);
            }
        }));
    }


    //领劵
    public void ledPdd(String id) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCOUPON + "/" + SPUtil.getUserCode() + "/" + id);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("拼多多转链------------>" + result);
                LedSecuritiesBean ledSecuritiesBean = JSON.parseObject(result, new TypeReference<LedSecuritiesBean>() {
                }.getType());

                if (ledSecuritiesBean != null && ledSecuritiesBean.getGoods_promotion_url_generate_response() != null && ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().size() != 0) {
                    if (getView() != null) {
                        String url = ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().get(0).getWe_app_web_view_url();
                        Bitmap bitmap = QRCode.createQRImage(url, DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
                        getView().loadQR(bitmap);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg领劵------------>" + errorMsg);
            }
        }));
    }

    public void share(LinearLayout mLinear) {
        btn.setEnabled(true);
        Bitmap bitmap = ViewToBitmap.createBitmap3(mLinear, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).open();
    }
}
