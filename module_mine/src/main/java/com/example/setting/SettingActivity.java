package com.example.setting;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.UserInfoBean;
import com.example.bind_wechat.BindWeChatActivity;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.replace_phone.ReplacePhoneActivity;
import com.example.update_password.UpdatePasswordActivity;
import com.example.utils.CacheUtil;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 设置
 */
@Route(path = "/mine/setting")
public class SettingActivity extends BaseActivity<SettingView, SettingPresenter> implements SettingView {
    @BindView(R2.id.setting_back)
    ImageView settingBack;
    @BindView(R2.id.setting_preserve)
    TextView settingPreserve;
    @BindView(R2.id.setting_header)
    ImageView settingHeader;
    @BindView(R2.id.setting_update_header)
    TextView settingUpdateHeader;
    @BindView(R2.id.setting_nick_name)
    EditText settingNickName;
    @BindView(R2.id.setting_personality_sign)
    EditText settingPersonalitySign;
    @BindView(R2.id.setting_update_password)
    LinearLayout settingUpdatePassword;
    @BindView(R2.id.setting_bind_wechat)
    LinearLayout settingBindWechat;
    @BindView(R2.id.setting_replace_phone)
    LinearLayout settingReplacePhone;
    @BindView(R2.id.setting_clear_cache)
    LinearLayout settingClearCache;
    @BindView(R2.id.setting_about_us)
    LinearLayout settingAboutUs;
    @BindView(R2.id.setting_logout)
    TextView settingLogout;
    @BindView(R2.id.setting_cache_txt)
    TextView settingCacheTxt;
    @BindView(R2.id.setting_temp)
    TextView settingTemp;

    private String totalCache;
    private boolean isLoad = true;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        totalCache = CacheUtil.getTotalCacheSize(this);
        settingCacheTxt.setText(totalCache);

    }

    @Override
    public void initClick() {
        settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        settingUpdateHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoad = false;
                presenter.updateHeader();
            }
        });

        settingClearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearCache(totalCache);
            }
        });

        settingUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoad = false;
                presenter.jumpToRevisePassword();
            }
        });

        settingBindWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoad = true;
                startActivity(new Intent(SettingActivity.this, BindWeChatActivity.class));
            }
        });

        settingReplacePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoad = false;
                presenter.jumpToRevisePhone();
            }
        });

        settingPreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(settingNickName.getText().toString())) {
                    Toast.makeText(SettingActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.preserve(settingNickName.getText().toString(), settingPersonalitySign.getText().toString());
                }
            }
        });

        settingAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoad = false;
                presenter.aboutUs();
            }
        });

        settingLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.logout();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isLoad)
            presenter.loadData();
    }

    @Override
    public void getDataSUccess(UserInfoBean userInfoBean) {
        Glide.with(this).load(userInfoBean.getIcon()).placeholder(R.drawable.vhjfg).apply(RequestOptions.circleCropTransform()).into(settingHeader);
        settingNickName.setText(userInfoBean.getNickname());
        settingPersonalitySign.setText(userInfoBean.getPersonalizedSignature());
        if ("".equals(userInfoBean.getWeixinOpenid()) || userInfoBean.getWeixinOpenid() == null) {
            settingTemp.setText("去绑定");
        } else {
            settingTemp.setText("已绑定");
            settingBindWechat.setEnabled(false);
        }
    }

    @Override
    public void takePhoto(Intent intent) {
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void cropPhoto(Intent intent) {
        startActivityForResult(intent, CROP_CODE);
    }

    @Override
    public void showHeader(String url) {
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(settingHeader);
    }

    @Override
    public void clearSuccess() {
        totalCache = CacheUtil.getTotalCacheSize(this);
        settingCacheTxt.setText(totalCache);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.cropImage();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                presenter.uploadPhoto();
                break;
        }
    }

    @Override
    public SettingView createView() {
        return this;
    }

    @Override
    public SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }
}
