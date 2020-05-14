package com.example.shakestock;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.VideoRecBean;
import com.example.common.CommonResource;
import com.example.dbflow.ShareOperationUtil;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.shakestock.adapter.VideoRecAdapter;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jzvd.JzvdStd;
import io.reactivex.Observable;

public class ShakeStockPresenter extends BasePresenter<ShakeStockView> {

    private LinearLayoutManager linearLayoutManager;
    private VideoRecAdapter videoRecAdapter;
    private List<VideoRecBean.DataBean> videoList = new ArrayList<>();
    private PagerSnapHelper pagerSnapHelper;

    public ShakeStockPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void recyclerVideo(final RecyclerView shakeStockRec, final int page) {
        Map map = MapUtil.getInstance().addParms("min_id", page).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETTRILLDATA, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("ShakeStockPresenterResult" + result);
                final VideoRecBean videoRecBean = JSON.parseObject(result, new TypeReference<VideoRecBean>() {
                }.getType());
                if (videoRecBean != null && videoRecBean.getData() != null) {
                    if (page == 1) {
                        videoList.clear();
                    }
                    videoList.addAll(videoRecBean.getData());

                    if (pagerSnapHelper == null) {
                        pagerSnapHelper = new PagerSnapHelper();
                    }
                    pagerSnapHelper.attachToRecyclerView(shakeStockRec);
                    if (linearLayoutManager == null) {
                        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    }
                    videoRecAdapter = new VideoRecAdapter(mContext, videoList, R.layout.item_shake_stock_rec);
                    shakeStockRec.setLayoutManager(linearLayoutManager);
                    shakeStockRec.setAdapter(videoRecAdapter);

                    videoRecAdapter.setViewFourOnClickListener(new MyRecyclerAdapter.ViewFourOnClickListener() {
                        @Override
                        public void ViewFourOnClick(View view1, View view2, final View view3, View view4, final int position) {
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    JzvdStd shakeStockVideo = v.findViewById(R.id.shake_stock_rec_video);
//                                    if (shakeStockVideo.isPlaying()) {
//                                        shakeStockVideo.pause();
//                                    } else {
//                                        shakeStockVideo.start();
//                                    }
                                }
                            });
                            //去购买
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String startTime = MyTimeUtil.date2String(videoList.get(position).getCouponstarttime() + "000");
                                    String endTime = MyTimeUtil.date2String(videoList.get(position).getCouponendtime() + "000");
                                    ARouter.getInstance()
                                            .build("/module_classify/TBCommodityDetailsActivity")
                                            .withString("para", videoList.get(position).getItemid())
                                            .withString("shoptype", videoList.get(position).getShoptype())
                                            .withDouble("youhuiquan", Double.valueOf(videoList.get(position).getCouponmoney()))
                                            .withString("coupon_start_time", startTime)
                                            .withString("coupon_end_time", endTime)
                                            .withString("commission_rate", videoList.get(position).getTkrates())
                                            .withInt("type",0)
                                            .navigation();
                                }
                            });
                            //收藏
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(final View v) {
                                    final ImageView image = v.findViewById(R.id.shake_stock_rec_collect_image);
                                    Map map = MapUtil.getInstance().addParms("productId", videoList.get(position).getItemid()).addParms("type", 4).build();
                                    Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
                                    RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                                        @Override
                                        public void onSuccess(String result, String msg) {
                                            LogUtil.e("ShakeStockPresenterResult点击收藏----->" + result);
                                            if (result.equals("true")) {
//                                                videoRecBean.getData().get(position).setShouCang(true);
                                                image.setImageResource(R.drawable.icon_shoucang_red);
                                            } else {
//                                                videoRecBean.getData().get(position).setShouCang(false);
                                                image.setImageResource(R.drawable.icon_shoucang);
                                            }
                                        }

                                        @Override
                                        public void onError(String errorCode, String errorMsg) {
                                            LogUtil.e("ShakeStockPresenterErrorMsg点击收藏----->" + errorMsg);

                                        }
                                    }));
                                }
                            });
                            //分享
                            view4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Glide.with(mContext)
                                            .asBitmap()
                                            .load(videoList.get(position).getItempic())
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                                    saveImageToPhotos(bitmap, position);
                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                                }
                                            });
                                }
                            });
                        }
                    });

                    shakeStockRec.addOnScrollListener(new RecyclerView.OnScrollListener() {

                        private JzvdStd videoView;
                        private RecyclerView.ViewHolder viewHolder;

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


                        }

                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            switch (newState) {
                                case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                                    if (pagerSnapHelper != null) {
                                        View view = pagerSnapHelper.findSnapView(linearLayoutManager);
                                        JzvdStd.resetAllVideos();
                                        viewHolder = recyclerView.getChildViewHolder(view);
                                        if (viewHolder != null && viewHolder instanceof RecyclerViewHolder) {
                                            videoView = ((RecyclerViewHolder) viewHolder).getView(R.id.shake_stock_rec_video);
                                            videoView.startVideo();
                                        }
                                    }
                                    break;
                                case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                                    break;
                                case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                                    break;
                            }

                        }
                    });
                    videoRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {

                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));


    }

    private void saveImageToPhotos(Bitmap bmp, int position) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "wwww" + ".jpg";
        File file = new File(appDir, fileName);
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
        viewToImage(position, file.getPath());
        LogUtil.e("图片路径" + file.getPath());
    }

    //加载生成图片布局
    public void viewToImage(int position, String path) {
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
        preferentialPrice.setText("￥" + videoList.get(position).getItemendprice());//优惠价
        originalPrice.setText("原价：￥" + videoList.get(position).getItemprice());//原价
        couponPrice.setText("￥" + videoList.get(position).getCouponmoney());
        LogUtil.e("url主图---------->" + videoList.get(position).getItempic());
        image.setImageURI(Uri.fromFile(new File(path)));
        name.setText(videoList.get(position).getItemtitle());
        number.setText("已售" + videoList.get(position).getItemsale() + "件");//已售
        Bitmap qr = QRCode.createQRImage(videoList.get(position).getCouponurl(), DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
        qRCode.setImageBitmap(qr);
        LogUtil.e("url2二维码---------->" + videoList.get(position).getCouponurl());

        Bitmap bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        share(bitmap);
    }

    //分享
    private void share(Bitmap bitmap) {
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
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)//SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
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
            Toast.makeText(mContext, "成功了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(mContext, "失败了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_SHORT).show();
        }
    };


}
