package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.module_base.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import q.rorbin.badgeview.DisplayUtil;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private Context context;
    private Bitmap bitmap;

    private RecyclerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        mViews = new SparseArray<>();
    }

    public static RecyclerViewHolder getInstance(Context context, View itemView) {
        return new RecyclerViewHolder(itemView, context);
    }

    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public RecyclerViewHolder setText(int resId, String text) {
        TextView txt = getView(resId);
        txt.setText(text);
        return this;
    }

    public RecyclerViewHolder setTextFormHtml(int resId, String html) {
        TextView txt = getView(resId);
        txt.setText(Html.fromHtml(html));
        return this;
    }

    public RecyclerViewHolder setButtonText(int resId, String text) {
        Button button = getView(resId);
        button.setText(text);
        return this;
    }

    public RecyclerViewHolder setTextColor(int resId, int colorId) {
        TextView txt = getView(resId);
        txt.setTextColor(colorId);
        return this;
    }

    public RecyclerViewHolder setButtonTextColor(int resId, int colorId) {
        Button button = getView(resId);
        button.setTextColor(colorId);
        return this;
    }

    public RecyclerViewHolder setImageResource(int resId, int drawableId) {
        ImageView imageView = getView(resId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public RecyclerViewHolder setImageBitmap(int resId, Bitmap bitmap) {
        ImageView imageView = getView(resId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerViewHolder setImageUrl(int resId, String url) {
        ImageView imageView = getView(resId);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

    public RecyclerViewHolder setImageUrlCircular(int resId, String url) {
        ImageView imageView = getView(resId);
        Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).placeholder(R.drawable.icon_touxiang1).into(imageView);
        return this;
    }

    public RecyclerViewHolder setBackgroundResource(int resId, int colorId) {
        View view = getView(resId);
        view.setBackgroundResource(colorId);
        return this;
    }

    public RecyclerViewHolder setBackgroundColor(int resId, int color) {
        View view = getView(resId);
        view.setBackgroundColor(color);
        return this;
    }

    public RecyclerViewHolder setProgressBar(int resId, int num) {
        ProgressBar progressBar = getView(resId);
        progressBar.setProgress(num);
        return this;
    }

    public RecyclerViewHolder setImageFresco(int resId, String url) {

        SimpleDraweeView simpleDraweeView = getView(resId);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url == null ? "" : url))
                .setResizeOptions(new ResizeOptions(DisplayUtil.dp2px(context, 100), DisplayUtil.dp2px(context, 100)))
                .setProgressiveRenderingEnabled(true)
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setLowResImageRequest(ImageRequest.fromUri(url))
                .setOldController(simpleDraweeView.getController())
                .setAutoPlayAnimations(true)
                .build();
        simpleDraweeView.setController(controller);
//        simpleDraweeView.setImageURI(Uri.parse(url));
        return this;
    }

    public RecyclerViewHolder setTextLine(int resId) {
        TextView textView = getView(resId);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        textView.getPaint().setAntiAlias(true);// 抗锯齿
        return this;
    }
}
