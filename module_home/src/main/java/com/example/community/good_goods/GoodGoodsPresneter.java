package com.example.community.good_goods;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.GoodGoodsBean;
import com.example.common.CommonResource;
import com.example.community.adapter.GoodGoodsAdapter;
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

public class GoodGoodsPresneter extends BasePresenter<GoodGoodsView> {

    private List<GoodGoodsBean.NetBean> dataList = new ArrayList<>();
    private GoodGoodsAdapter goodsAdapter;
    private GoodGoodsBean goodsBean;

    private TBUtil tbUtil = new TBUtil();

    private int number = 0;

    public GoodGoodsPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("community_type", "1").addParms("mall_type", "0").addParms("page", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.COMMUNITY, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("好货专场：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    goodsBean = JSON.parseObject(result, GoodGoodsBean.class);
                    if (page == 1) {
                        dataList.clear();
                    }
                    dataList.addAll(goodsBean.getNet());

                    if (goodsAdapter == null) {
                        goodsAdapter = new GoodGoodsAdapter(mContext, dataList, R.layout.rv_good_goods);
                        if (getView() != null) {
                            getView().loadContent(goodsAdapter);
                        }
                    } else {
                        goodsAdapter.notifyDataSetChanged();
                    }


                    goodsAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tbZL(dataList.get(index).getItem_data().get(0).getItemid());
                                    getView().loadShareInfo(dataList.get(index).getItem_data().get(0));
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

    private void copy(int position) {
        //获取剪贴板管理器
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "￥jfddfddghdhduuhgj￥jfcsdcjffdjfstfhgdfdfjgdggdgu");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
        Toast.makeText(mContext, "复制成功", Toast.LENGTH_SHORT).show();
    }

    private void tbZL(String id) {
        ledSecurities(id);
        tbUtil.login(mContext, new OnSuccessListener() {
            @Override
            public void onSuccess() {
                number++;
                if (number == 2) {
                    tbUtil.shouQuan();
                }
            }
        });
    }

    public void ledSecurities(String para) {
        Map map = MapUtil.getInstance().addParms("para", para).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHead(CommonResource.TBKGOODSGETGYURLBYALL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult领劵--------->" + result);

                if (result.startsWith("{\"code\":3")) {
                    number++;
                    if (number == 2) {
                        tbUtil.shouQuan();
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
                LogUtil.e(errorCode + "---------------" + errorMsg);
            }
        }));
    }

    public void share(LinearLayout linear) {
        Bitmap bitmap = ViewToBitmap.createBitmap3(linear, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
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
