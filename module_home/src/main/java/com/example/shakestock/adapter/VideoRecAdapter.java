package com.example.shakestock.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.VideoRecBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.jzvd.JzvdStd;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class VideoRecAdapter extends MyRecyclerAdapter<VideoRecBean.DataBean> {

    public VideoRecAdapter(Context context, List<VideoRecBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, VideoRecBean.DataBean data, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + data.getItemid(), SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult收藏------->" + result);
                if (result.equals("true")) {
//                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                    holder.setImageResource(R.id.shake_stock_rec_collect_image, R.drawable.icon_shoucang_red);
                } else {
//                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                    holder.setImageResource(R.id.shake_stock_rec_collect_image, R.drawable.icon_shoucang);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg收藏------------>" + errorMsg);
            }
        }));

        Map map = MapUtil.getInstance().addParms("productId", data.getItemid()).addParms("userCode", SPUtil.getUserCode()).addParms("type", 3).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.HISTORYSAVE, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("添加浏览记录" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("添加浏览记录errorMsg" + errorMsg);
            }
        }));

//        Glide.with(context)
//                .asBitmap()
//                .load(data.getItempic()+"_310x310.jpg")
//                .into(new CustomTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                        saveImageToPhotos(bitmap);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                    }
//                });

        JzvdStd jzvdStd = holder.getView(R.id.shake_stock_rec_video);
//        shakeStockRecVideo.setVideoPath(data);
//        jzvdStd.setVideoURI(Uri.parse(data));
        jzvdStd.setUp(data.getDy_video_url(), "");
        if (position == 0) {
            jzvdStd.startVideo();
        }

        Glide.with(context).load(data.getFirst_frame()).into(jzvdStd.thumbImageView);
        holder.setText(R.id.shake_stock_rec_nick, data.getShopname());//商家名
        holder.setImageFresco(R.id.shake_stock_rec_goods_image, data.getItempic() + "_310x310.jpg");
        holder.setText(R.id.shake_stock_rec_number, data.getItemsale() + "人已购");//销量
        holder.setText(R.id.shake_stock_rec_title, data.getItemshorttitle());//短标题
        holder.setText(R.id.shake_stock_rec_message, data.getItemdesc());//详细信息
        holder.setText(R.id.shake_stock_rec_juan_hou, "券后￥" + data.getItemendprice());//卷后价
        holder.setText(R.id.shake_stock_rec_ling_juan, data.getCouponmoney() + "元劵");//优惠券金额
        holder.setText(R.id.shake_stock_rec_gain_money, "" + ArithUtil.mul(Double.valueOf(data.getTkmoney()), SPUtil.getFloatValue(CommonResource.BACKBL)));//赚了多少钱
        holder.setText(R.id.shake_stock_rec_province_money, data.getCouponmoney());//省了
        if (viewFourOnClickListener != null) {
            viewFourOnClickListener.ViewFourOnClick(holder.getView(R.id.shake_stock_rec_video), holder.getView(R.id.shake_stock_rec_go_to_buy), holder.getView(R.id.shake_stock_rec_collect), holder.getView(R.id.shake_stock_rec_share), position);
        }
    }


//    /**
//     * 保存二维码到本地相册
//     */
//    private void saveImageToPhotos(Bitmap bmp) {
//        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = "wwww" + ".jpg";
//        File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.JPEG, 30, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        viewToImage(imageUrl, file.getPath());
//        LogUtil.e("图片路径" + file.getPath());
//    }

//    //加载生成图片布局
//    public void viewToImage(String qRImage, String path) {
//        final View view = LayoutInflater.from(mContext).inflate(R.layout.sharebg, null, false);
//        ImageView image = view.findViewById(R.id.share_image);
//        TextView name = view.findViewById(R.id.share_name);
//        TextView preferentialPrice = view.findViewById(R.id.share_preferential_price);
//        TextView originalPrice = view.findViewById(R.id.share_original_price);
//        TextView couponPrice = view.findViewById(R.id.share_coupon_price);
//        TextView number = view.findViewById(R.id.share_number);
//        ImageView qRCode = view.findViewById(R.id.share_qr_code);
//        //字体加中划线
//        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
//        String zkFinalPrice = tbGoodsDetailsBean.getN_tbk_item().getZk_final_price();
//        if (!TextUtils.isEmpty(zkFinalPrice)) {
//            if (zkFinalPrice.contains("-")) {
//                String[] split = zkFinalPrice.split("-");
//                String couponInfo = tbLedSecuritiesBean.getCoupon_info();
//                if (!TextUtils.isEmpty(couponInfo)) {
//                    String substring = couponInfo.substring(couponInfo.indexOf("减"));
//                    String price = substring.substring(1, substring.indexOf("元"));
//                    double sub = ArithUtil.sub(Double.valueOf(split[0]), Double.valueOf(price));
//                    preferentialPrice.setText("￥" + sub);//优惠价
//                    originalPrice.setText("原价：￥" + split[0]);//原价
//                    couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(split[0]), sub));
//
//                }
//
//            } else {
//                String couponInfo = tbLedSecuritiesBean.getCoupon_info();
//                if (!TextUtils.isEmpty(couponInfo)) {
//                    String substring = couponInfo.substring(couponInfo.indexOf("减"));
//                    String price = substring.substring(1, substring.indexOf("元"));
//                    double sub = ArithUtil.sub(Double.valueOf(zkFinalPrice), Double.valueOf(price));
//                    preferentialPrice.setText("￥" + sub);//优惠价
//                    originalPrice.setText("原价：￥" + zkFinalPrice);//原价
//                    couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(zkFinalPrice), sub));
//
//                }
//
//
//            }
//            LogUtil.e("url主图---------->" + tbGoodsDetailsBean.getN_tbk_item().getPict_url());
//            image.setImageURI(Uri.fromFile(new File(path)));
//            name.setText(tbGoodsDetailsBean.getN_tbk_item().getTitle());
//            number.setText("已售" + tbGoodsDetailsBean.getN_tbk_item().getVolume() + "件");//已售
//            Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 300), DisplayUtil.dip2px(mContext, 300));
//            qRCode.setImageBitmap(qr);
//            LogUtil.e("url2二维码---------->" + qRImage);
//
//            this.bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
//        }
//    }
}
