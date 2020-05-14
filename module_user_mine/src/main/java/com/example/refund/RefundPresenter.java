package com.example.refund;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.ImageUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.TxtUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class RefundPresenter extends BasePresenter<RefundView> {

    private Uri fileUri;//相册
    private String filePath = Environment.getExternalStorageDirectory() + "/fltk/image";

    public RefundPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void popupCause(final TextView refundCauseText) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_refund_cause, null);
        TextView text = view.findViewById(R.id.popup_refund_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_refund_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_refund_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_refund_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_refund_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_refund_but3);
        final RadioButton but4 = view.findViewById(R.id.popup_refund_but4);
        final RadioButton but5 = view.findViewById(R.id.popup_refund_but5);
        String textCause = refundCauseText.getText().toString();
        if (textCause.equals(but1.getText().toString())) {
            but1.setChecked(true);
        } else if (textCause.equals(but2.getText().toString())) {
            but2.setChecked(true);
        } else if (textCause.equals(but3.getText().toString())) {
            but3.setChecked(true);
        } else if (textCause.equals(but4.getText().toString())) {
            but4.setChecked(true);
        } else if (textCause.equals(but5.getText().toString())) {
            but5.setChecked(true);
        }else{
            but1.setChecked(false);
            but2.setChecked(false);
            but3.setChecked(false);
            but4.setChecked(false);
            but5.setChecked(false);
        }
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT,DisplayUtil.dip2px(mContext, 372), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                popupRefundRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.popup_refund_but1) {
                            refundCauseText.setText(but1.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_refund_but2) {
                            refundCauseText.setText(but2.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_refund_but3) {
                            refundCauseText.setText(but3.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_refund_but4) {
                            refundCauseText.setText(but4.getText().toString());
                            pop.dismiss();
                        } else {
                            refundCauseText.setText(but5.getText().toString());
                            pop.dismiss();
                        }
                    }
                });
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);

    }

    public void popupType(final TextView refundTypeText){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_refund_type, null);
        TextView text = view.findViewById(R.id.popup_refund_type_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_refund_type_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_refund_type_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_refund_type_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_refund_type_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_refund_type_but3);
        String textCause = refundTypeText.getText().toString();
        if (textCause.equals(but1.getText().toString())) {
            but1.setChecked(true);
        } else if (textCause.equals(but2.getText().toString())) {
            but2.setChecked(true);
        } else if (textCause.equals(but3.getText().toString())) {
            but3.setChecked(true);
        } else{
            but1.setChecked(false);
            but2.setChecked(false);
            but3.setChecked(false);
        }
        PopUtils.createPop(mContext, view,LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 248), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                popupRefundRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.popup_refund_type_but1) {
                            refundTypeText.setText(but1.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_refund_type_but2) {
                            refundTypeText.setText(but2.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_refund_type_but3) {
                            refundTypeText.setText(but3.getText().toString());
                            pop.dismiss();
                        }
                    }
                });
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }

    public void popupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_bottom, null);
        final TextView popHeaderCancel = view.findViewById(R.id.pop_header_cancel);
        final TextView popHeaderCamera = view.findViewById(R.id.pop_header_camera);
        final TextView popHeaderXiangce = view.findViewById(R.id.pop_header_xiangce);
        PopUtils.setTransparency(mContext, 0.3f);
        PopUtils.createPop(mContext, view,LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 146), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                popHeaderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                popHeaderCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCamera();
                        pop.dismiss();
                    }
                });
                popHeaderXiangce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPhotoAlbum();
                        pop.dismiss();
                    }
                });
            }
        });
    }

    private void openCamera() {
        File file0 = new File(filePath);
        if (!file0.exists()) {
            file0.mkdirs();
        }
        File file = new File(filePath, System.currentTimeMillis() + ".jpg");

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(mContext.getApplicationContext(), mContext.getPackageName(), file);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            fileUri = Uri.fromFile(file);
        }
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        getView().takePhoto(captureIntent);
    }

    public void openPhotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        getView().photoAlbum(intent);
    }

    public void parseUri(Intent intent) {
        fileUri = intent.getData();
        String type = intent.getType();
        if (fileUri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = fileUri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = mContext.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID}, buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        fileUri = uri_temp;
                    }
                }
            }
        }
        getView().selectPhoto(fileUri);
    }

    public void uploadPhoto() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            getView().showHeader(base64);
        } catch (Exception e) {
        }
        getView().selectPhoto(fileUri);
    }
}
