package com.example.businessapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CityBean;
import com.example.bean.PopupGoodsClassBean;
import com.example.bean.PopupXSGoodsClassBean;
import com.example.businessapplication.adapter.PopupGoodsClassifyAdapter;
import com.example.businessapplication.adapter.PopupXSGoodsClassifyAdapter;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.ImageUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.TxtUtil;
import com.example.view.addressselect.AddressSelector;
import com.example.view.addressselect.CityInterface;
import com.example.view.addressselect.OnItemClickListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class BusinessApplicationPresenter extends BasePresenter<BusinessApplicationView> {


    private Uri fileUri;//相册
    private String filePath = Environment.getExternalStorageDirectory() + "/fltk/image";
    private List<CityBean> cities1 = new ArrayList<>();
    private List<CityBean> cities2 = new ArrayList<>();
    private List<CityBean> cities3 = new ArrayList<>();
    private String cityName1 = "";
    private String cityName2 = "";
    private String cityName3 = "";
    private PopupGoodsClassifyAdapter popupGoodsClassifyAdapter;
    private PopupXSGoodsClassifyAdapter popupXSGoodsClassifyAdapter;
    private List<PopupGoodsClassBean> popupGoodsClassBeans;
    private List<PopupXSGoodsClassBean> popupXSGoodsClassBeans;
    private File file;

    public BusinessApplicationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void goodsClass(String from) {
        ProcessDialogUtil.showProcessDialog(mContext);
        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            Map map = MapUtil.getInstance().addParms("type", 1).build();
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.SELLERCATEGORY, map);
            RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("本地商家商品分类" + result);
                    popupGoodsClassBeans = JSON.parseArray(result, PopupGoodsClassBean.class);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("本地商家商品分类" + errorMsg);
                }
            }));
        } else {
            Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithout(CommonResource.SELLERNETCATEGORY);
            RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("popupXSGoodsClassBeans" + result);
                    popupXSGoodsClassBeans = JSON.parseArray(result, PopupXSGoodsClassBean.class);
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("本地商家商品分类" + errorMsg);
                }
            }));
        }
    }

    public void popupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_bottom, null);
        final TextView popHeaderCancel = view.findViewById(R.id.pop_header_cancel);
        final TextView popHeaderCamera = view.findViewById(R.id.pop_header_camera);
        final TextView popHeaderXiangce = view.findViewById(R.id.pop_header_xiangce);
        PopUtils.setTransparency(mContext, 0.3f);
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 146), new OnPopListener() {
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

    public void popupAddressWhere(final TextView addressProvince, final TextView addressCity, final TextView addressArea) {

        addressProvince.setText("点击选择");
        addressCity.setText("");
        addressArea.setText("");

        final View view = LayoutInflater.from(mContext).inflate(R.layout.popup_address_select, null, false);
        final ImageView close = view.findViewById(R.id.address_select_close);
        final AddressSelector addressSelector = view.findViewById(R.id.address_selector);


        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {

                addressSelector.setTabAmount(3);
                city1(addressSelector, 1);

                addressSelector.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                        switch (tabPosition) {
                            case 0:
//                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                city2(addressSelector, city.getCityId());
                                cityName1 = city.getCityName();
                                addressProvince.setText(cityName1);
//                                addressSelector.setCities(cities2);
                                break;
                            case 1:
//                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                city3(addressSelector, city.getCityId());
                                cityName2 = city.getCityName();
                                addressCity.setText(cityName2);
//                                addressSelector.setCities(cities3);
                                break;
                            case 2:
//                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                cityName3 = city.getCityName();
                                //关闭赋值
                                addressArea.setText(cityName3);
                                pop.dismiss();
                                break;
                        }
                    }
                });
                addressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                        switch (tab.getIndex()) {
                            case 0:
                                addressSelector.setCities(cities1);
                                break;
                            case 1:
                                addressSelector.setCities(cities2);
                                break;
                            case 2:
                                addressSelector.setCities(cities3);
                                break;
                        }
                    }

                    @Override
                    public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopUtils.setTransparency(mContext, 1f);
                        pop.dismiss();
                    }
                });

            }
        });

        PopUtils.setTransparency(mContext, 0.3f);


    }

    private void city1(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("addressResult-------------->" + result);
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities1.addAll(cityBeans);
                addressSelector.setCities(cities1);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("addressErrorMsg-------------->" + errorMsg);
            }
        }));

    }

    private void city2(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities2.clear();
                cities2.addAll(cityBeans);
                addressSelector.setCities(cities2);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void city3(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities3.clear();
                cities3.addAll(cityBeans);
                addressSelector.setCities(cities3);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void popupGoodsClassify(String form) {
        if (CommonResource.HISTORY_LOCAL.equals(form)) {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.popup_select_goods_classify, null);
            TextView text = view.findViewById(R.id.popup_select_goods_classify_text);
            TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
            final ImageView imageClose = view.findViewById(R.id.popup_select_goods_classify_close);
            final RecyclerView classifyRec = view.findViewById(R.id.popup_select_goods_classify_rec);
            final TextView textView = view.findViewById(R.id.popup_select_goods_classify_affirm);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            classifyRec.setLayoutManager(linearLayoutManager);
            popupGoodsClassifyAdapter = new PopupGoodsClassifyAdapter(mContext, popupGoodsClassBeans, R.layout.popup_item_goods_classify);
            classifyRec.setAdapter(popupGoodsClassifyAdapter);

            final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setAnimationStyle(com.example.module_base.R.style.pop_bottom_anim);
            popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);
            PopUtils.setTransparency(mContext, 0.3f);

            imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupGoodsClassifyAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    for (int i = 0; i < popupGoodsClassBeans.size(); i++) {
                        if (position == i) {
                            popupGoodsClassBeans.get(i).setCheck(true);
                        } else {
                            popupGoodsClassBeans.get(i).setCheck(false);
                        }
                    }
                    popupGoodsClassifyAdapter.notifyDataSetChanged();
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < popupGoodsClassBeans.size(); i++) {
                        if (popupGoodsClassBeans.get(i).isCheck()) {
                            getView().categoryId(popupGoodsClassBeans.get(i).getSellerCategoryName(), popupGoodsClassBeans.get(i).getId());
                        }
                    }
                    popupWindow.dismiss();
                }
            });

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    getView().kedian();
                    PopUtils.setTransparency(mContext, 1.0f);
                }
            });

        } else {
            final View view = LayoutInflater.from(mContext).inflate(R.layout.popup_select_goods_classify, null);
            TextView text = view.findViewById(R.id.popup_select_goods_classify_text);
            TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
            final ImageView imageClose = view.findViewById(R.id.popup_select_goods_classify_close);
            final RecyclerView classifyRec = view.findViewById(R.id.popup_select_goods_classify_rec);
            final TextView textView = view.findViewById(R.id.popup_select_goods_classify_affirm);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            classifyRec.setLayoutManager(linearLayoutManager);
            popupXSGoodsClassifyAdapter = new PopupXSGoodsClassifyAdapter(mContext, popupXSGoodsClassBeans, R.layout.popup_item_goods_classify);
            classifyRec.setAdapter(popupXSGoodsClassifyAdapter);

            final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setAnimationStyle(com.example.module_base.R.style.pop_bottom_anim);
            popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);
            PopUtils.setTransparency(mContext, 0.3f);

            imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            popupXSGoodsClassifyAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    for (int i = 0; i < popupXSGoodsClassBeans.size(); i++) {
                        if (position == i) {
                            popupXSGoodsClassBeans.get(i).setCheck(true);
                        } else {
                            popupXSGoodsClassBeans.get(i).setCheck(false);
                        }
                    }
                    popupXSGoodsClassifyAdapter.notifyDataSetChanged();
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < popupXSGoodsClassBeans.size(); i++) {
                        if (popupXSGoodsClassBeans.get(i).isCheck()) {
                            getView().categoryId(popupXSGoodsClassBeans.get(i).getName(), popupXSGoodsClassBeans.get(i).getId());
                        }
                    }
                    popupWindow.dismiss();
                }
            });

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    getView().kedian();
                    PopUtils.setTransparency(mContext, 1.0f);
                }
            });

        }

    }

    public void popupGoodsType(final TextView businessApplicationShopTypeText, String from) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_select_goods_type, null);
        TextView text = view.findViewById(R.id.popup_select_goods_type_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_select_goods_type_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_select_goods_type_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_select_goods_type_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_select_goods_type_but2);
        String textCause = businessApplicationShopTypeText.getText().toString();

        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            but1.setVisibility(View.GONE);
        } else if ("shop".equals(from)) {
            but2.setVisibility(View.GONE);
        }
        if (textCause.equals(but1.getText().toString())) {
            but1.setChecked(true);
        } else if (textCause.equals(but2.getText().toString())) {
            but2.setChecked(true);
        } else {
            but1.setChecked(false);
            but2.setChecked(false);
        }
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
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
                        if (checkedId == R.id.popup_select_goods_type_but1) {
                            businessApplicationShopTypeText.setText(but1.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_select_goods_type_but2) {
                            businessApplicationShopTypeText.setText(but2.getText().toString());
                            pop.dismiss();
                        }
                    }
                });
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }

    private void openCamera() {

        File file0 = new File(filePath);
        if (!file0.exists()) {
            file0.mkdirs();
        }
        file = new File(filePath, System.currentTimeMillis() + ".jpg");

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
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            getView().showHeader(base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getView().selectPhoto(2,file,fileUri);
    }

    public void takePhoto() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            getView().showHeader(base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getView().selectPhoto(1,file,fileUri);
    }

}
