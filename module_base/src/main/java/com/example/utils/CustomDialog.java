package com.example.utils;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.KeyEvent;

import com.example.module_base.R;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(CustomDialog.this.isShowing())
                    CustomDialog.this.dismiss();
                break;
        }
        return true;
    }

    private void initView(){
        setContentView(R.layout.dialog_view);
        SimpleDraweeView dialogGif = findViewById(R.id.dialog_gif);
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(R.drawable.dialog_gif1))
                .build();
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        dialogGif.setController(draweeController);

        setCanceledOnTouchOutside(true);

        setCancelable(false);
    }
}
