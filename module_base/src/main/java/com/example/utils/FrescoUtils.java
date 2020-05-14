package com.example.utils;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoUtils {

    private static int height;
    private static int width;

    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                height = imageInfo.getHeight();
                width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imagePath))
//                .setResizeOptions(new ResizeOptions( imageWidth,(int) ((float) (imageWidth * height) / (float) width)))
//                .setProgressiveRenderingEnabled(true)
//                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .setControllerListener(controllerListener)
                .setUri(Uri.parse(imagePath))
                .build();
        simpleDraweeView.setController(controller);
    }


}