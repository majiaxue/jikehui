package com.example.businessapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.bean.BusinessApplicationBean;
import com.example.bean.SellerVo;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 商家申请
 */
@Route(path = "/module_user_mine/BusinessApplicationActivity")
public class BusinessApplicationActivity extends BaseActivity<BusinessApplicationView, BusinessApplicationPresenter> implements BusinessApplicationView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.business_application_icon)
    SimpleDraweeView businessApplicationIcon;
    @BindView(R2.id.business_application_shop_name)
    EditText businessApplicationShopName;
    @BindView(R2.id.business_application_shop_classify_text)
    TextView businessApplicationShopClassifyText;
    @BindView(R2.id.business_application_shop_classify)
    LinearLayout businessApplicationShopClassify;
    @BindView(R2.id.business_application_name)
    EditText businessApplicationName;
    @BindView(R2.id.business_application_phone)
    EditText businessApplicationPhone;
    @BindView(R2.id.business_application_shop_address)
    LinearLayout businessApplicationShopAddress;
    @BindView(R2.id.business_application_detail_address)
    EditText businessApplicationDetailAddress;
    @BindView(R2.id.business_application_front_photo)
    SimpleDraweeView businessApplicationFrontPhoto;
    @BindView(R2.id.business_application_verso_photo)
    SimpleDraweeView businessApplicationVersoPhoto;
    @BindView(R2.id.business_application_business_license)
    SimpleDraweeView businessApplicationBusinessLicense;
    @BindView(R2.id.business_application_food_safety_permit)
    SimpleDraweeView businessApplicationFoodSafetyPermit;
    @BindView(R2.id.business_application_submit)
    TextView businessApplicationSubmit;
    @BindView(R2.id.business_application_agreement)
    TextView businessApplicationAgreement;
    @BindView(R2.id.business_application_address_province)
    TextView businessApplicationAddressProvince;
    @BindView(R2.id.business_application_address_city)
    TextView businessApplicationAddressCity;
    @BindView(R2.id.business_application_address_area)
    TextView businessApplicationAddressArea;
    @BindView(R2.id.business_application_shop_type_text)
    TextView businessApplicationShopTypeText;
    @BindView(R2.id.business_application_shop_type)
    LinearLayout businessApplicationShopType;

    @Autowired(name = "from")
    String from;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;


    private int type;
    private Map<String, String> map = new HashMap<>();
    private int categoryId;
    private GeoCoder geoCoder;
    private double lat = 0;
    private double lon = 0;
    private boolean editFocus = false;//详细地址是否有焦点 每当失去焦点获取详细地址对应的经纬度

    @Override
    public int getLayoutId() {
        return R.layout.activity_business_application;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("商家申请");
        geoCoder = GeoCoder.newInstance();
        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            presenter.goodsClass(from);
        } else {
            presenter.goodsClass(from);
        }
    }

    @Override
    public void initClick() {
        //返回
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击提交
        businessApplicationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(businessApplicationShopName.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入店铺名!", Toast.LENGTH_SHORT).show();
                } else if ("点击选择".equals(businessApplicationShopClassifyText.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择商品分类!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(businessApplicationName.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入姓名!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(businessApplicationPhone.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(businessApplicationPhone.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入正确的手机号!", Toast.LENGTH_SHORT).show();
                } else if ("点击选择".equals(businessApplicationAddressProvince.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择地址!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(map.get("0"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择头像", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(map.get("1"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传身份证正面", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(map.get("2"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传身份证背面", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(map.get("3"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传营业执照", Toast.LENGTH_SHORT).show();

                } else {
                    ProcessDialogUtil.showProcessDialog(BusinessApplicationActivity.this);
                    SellerVo sellerVo = new SellerVo();
                    sellerVo.setUserCode(SPUtil.getUserCode());//SPUtil.getUserCode()"297881222686703616"
                    sellerVo.setSellerLogo(map.get("0"));
                    sellerVo.setSellerIdPositiveCardUrl(map.get("1"));
                    sellerVo.setSellerIdBackCardUrl(map.get("2"));
                    sellerVo.setSellerBusinessLicenseUrl(map.get("3"));
                    sellerVo.setSellerFoodSafetyPermitUrl(map.get("4"));

                    if (CommonResource.HISTORY_LOCAL.equals(from)) {
                        sellerVo.setSellerType("1");
                    } else {
                        sellerVo.setSellerType("0");
                    }
                    sellerVo.setSellerShopName(businessApplicationShopName.getText().toString());
                    sellerVo.setSellerCategory(categoryId + "");
                    sellerVo.setSellerName(businessApplicationName.getText().toString());
                    sellerVo.setSellerPhone(businessApplicationPhone.getText().toString());
                    sellerVo.setSellerLon(lon + "");
                    sellerVo.setSellerLat(lat + "");
                    sellerVo.setSellerAddredd(businessApplicationAddressProvince.getText().toString() + " " + businessApplicationAddressCity.getText().toString() + " " + businessApplicationAddressArea.getText().toString() + " " + businessApplicationDetailAddress.getText().toString());
                    String sellerVoJson = JSON.toJSONString(sellerVo);

                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), sellerVoJson);

                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).postHeadWithBody(CommonResource.SELLERINFO, body, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("BusinessApplicationResult----------->" + result);
                            BusinessApplicationBean businessApplicationBean = JSON.parseObject(result, new TypeReference<BusinessApplicationBean>() {
                            }.getType());

                            String msg1 = businessApplicationBean.getMsg();
                            if (msg1.equals("success")) {
                                Toast.makeText(BusinessApplicationActivity.this, "商家申请成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(BusinessApplicationActivity.this, msg1, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
//                            ProcessDialogUtil.dismissDialog();
                            LogUtil.e("BusinessApplicationErrorMsg----------->" + errorMsg);
                        }
                    }));
                }
            }
        });
        //商家logo
        businessApplicationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPhotoAlbum();
                type = 0;
            }
        });
        //身份证正面
        businessApplicationFrontPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 1;
            }
        });
        //身份证反面
        businessApplicationVersoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 2;
            }
        });
        //营业执照
        businessApplicationBusinessLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 3;
            }
        });
        //食品安全许可证
        businessApplicationFoodSafetyPermit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 4;
            }
        });
        //选择分类
        businessApplicationShopClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupGoodsClassify(from);
            }
        });
        //选择地址
        businessApplicationShopAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_store/MapDetailActivity").navigation(BusinessApplicationActivity.this, 112);
//                presenter.popupAddressWhere(businessApplicationAddressProvince, businessApplicationAddressCity, businessApplicationAddressArea);
            }
        });
        //商家类型
        businessApplicationShopType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupGoodsType(businessApplicationShopTypeText, from);
            }
        });

        businessApplicationDetailAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editFocus = hasFocus;
                if (!hasFocus && !"点击选择".equals(businessApplicationAddressProvince.getText().toString())) {
                    geoCoder.geocode(new GeoCodeOption().city(businessApplicationAddressProvince.getText().toString()).address(businessApplicationDetailAddress.getText().toString()));
                }
            }
        });

        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                lat = geoCodeResult.getLocation().latitude;
                lon = geoCodeResult.getLocation().longitude;
                String address = geoCodeResult.getAddress();
                LogUtil.e("-------->纬度：" + lat + "--------->经度：" + lon + "--------地址：" + address);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                    LogUtil.e("没检索到结果");
                    return;
                } else {
                    String address = reverseGeoCodeResult.getAddress();
                    String sematicDescription = reverseGeoCodeResult.getSematicDescription();
                    ReverseGeoCodeResult.AddressComponent addressDetail = reverseGeoCodeResult.getAddressDetail();
                    businessApplicationAddressProvince.setText(addressDetail.city);
                    businessApplicationDetailAddress.setText(address + sematicDescription);
                    LogUtil.e("地址：" + address + "-----------" + sematicDescription);
                    LogUtil.e("详细：" + addressDetail.toString());
                }
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }

        if (editFocus) {
            businessApplicationDetailAddress.clearFocus();
        }
    }

    @Override
    public BusinessApplicationView createView() {
        return this;
    }

    @Override
    public BusinessApplicationPresenter createPresenter() {
        return new BusinessApplicationPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        geoCoder.destroy();
        presenter.onViewDestroy();
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
    public void kedian() {
        businessApplicationShopClassify.setEnabled(true);
    }

    @Override
    public void selectPhoto(int flag, File file, Uri uri) {

        if (1 == flag) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(file + "", options);
            Uri uriImage = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bm, null, null));
            if (type == 1) {
                businessApplicationFrontPhoto.setImageURI(uriImage);
            } else if (type == 2) {
                businessApplicationVersoPhoto.setImageURI(uriImage);
            } else if (type == 3) {
                businessApplicationBusinessLicense.setImageURI(uriImage);
            } else if (type == 4) {
                businessApplicationFoodSafetyPermit.setImageURI(uriImage);
            } else {
                businessApplicationIcon.setImageURI(uriImage);
            }
        } else {
            String realFilePath = getRealFilePath(this, uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap bm = BitmapFactory.decodeFile(realFilePath, options);
            Uri uriImage = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bm, null, null));
            if (type == 1) {
                businessApplicationFrontPhoto.setImageURI(uriImage);
            } else if (type == 2) {
                businessApplicationVersoPhoto.setImageURI(uriImage);
            } else if (type == 3) {
                businessApplicationBusinessLicense.setImageURI(uriImage);
            } else if (type == 4) {
                businessApplicationFoodSafetyPermit.setImageURI(uriImage);
            } else {
                businessApplicationIcon.setImageURI(uriImage);
            }
        }

    }

    @Override
    public void showHeader(String bitmap) {
        if (type == 1) {
            map.put("1", bitmap.replace("\n", ""));
        } else if (type == 2) {
            map.put("2", bitmap.replace("\n", ""));
        } else if (type == 3) {
            map.put("3", bitmap.replace("\n", ""));
        } else if (type == 4) {
            map.put("4", bitmap.replace("\n", ""));
        } else {
            map.put("0", bitmap.replace("\n", ""));
        }
    }

    @Override
    public void categoryId(String name, int categoryId) {
        this.categoryId = categoryId;
        businessApplicationShopClassifyText.setText(name);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.takePhoto();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case 112:
                lat = data.getDoubleExtra("lat", 0);
                lon = data.getDoubleExtra("lon", 0);
                LogUtil.e("lat:" + lat + "----------lon:" + lon);

                geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(new LatLng(lat, lon)));
                break;
        }
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
