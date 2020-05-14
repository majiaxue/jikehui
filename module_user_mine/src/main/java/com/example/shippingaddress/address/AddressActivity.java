package com.example.shippingaddress.address;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.fastjson.JSON;
import com.example.bean.AddressInfo;
import com.example.common.CommonResource;
import com.example.module_user_mine.R2;
import com.example.module_user_mine.R;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.SPUtil;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 新建收货地址
 */
@Route(path = "/module_user_mine/AddressActivity")
public class AddressActivity extends BaseActivity<AddressView, AddressPresenter> implements AddressView {

    @BindView(R2.id.address_name)
    EditText addressName;
    @BindView(R2.id.address_phone)
    EditText addressPhone;
    @BindView(R2.id.address_where)
    LinearLayout addressWhere;
    @BindView(R2.id.address_detailed)
    EditText addressDetailed;
    @BindView(R2.id.address_home)
    RadioButton addressHome;
    @BindView(R2.id.address_company)
    RadioButton addressCompany;
    @BindView(R2.id.address_school)
    RadioButton addressSchool;
    @BindView(R2.id.address_radio)
    RadioGroup addressRadio;
    @BindView(R2.id.address_switch)
    Switch addressSwitch;
    @BindView(R2.id.address_save)
    TextView addressSave;
    @BindView(R2.id.address_province)
    TextView addressProvince;
    @BindView(R2.id.address_city)
    TextView addressCity;
    @BindView(R2.id.address_area)
    TextView addressArea;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initData() {
        includeTitle.setText("新建收货地址");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //选择地址
        addressWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupAddressWhere(addressProvince, addressCity, addressArea);
            }
        });
        //保存
        addressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(addressName.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addressPhone.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(addressPhone.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addressCity.getText().toString()) && TextUtils.isEmpty(addressArea.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "请选择地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addressDetailed.getText().toString())) {
                    Toast.makeText(AddressActivity.this, "请填写详细地址", Toast.LENGTH_SHORT).show();
                } else {
                    AddressInfo addressInfo = new AddressInfo();
                    addressInfo.setAddressName(addressName.getText().toString());
                    addressInfo.setAddressPhone(addressPhone.getText().toString());
                    addressInfo.setAddressProvince(addressProvince.getText().toString());
                    addressInfo.setAddressCity(addressCity.getText().toString());
                    addressInfo.setAddressArea(addressArea.getText().toString());
                    addressInfo.setAddressDetail(addressDetailed.getText().toString());
                    if (addressHome.isChecked()) {
                        addressInfo.setAddressTips("1");
                    } else if (addressCompany.isChecked()) {
                        addressInfo.setAddressTips("2");
                    } else if (addressSchool.isChecked()) {
                        addressInfo.setAddressTips("3");
                    } else {
                        addressInfo.setAddressTips("0");
                    }
                    if (addressSwitch.isChecked()) {
                        addressInfo.setAddressDefault("1");
                    } else {
                        addressInfo.setAddressDefault("0");
                    }
                    String jsonString = JSON.toJSONString(addressInfo);
                    LogUtil.e("SecondaryDetailsJson----------->" + jsonString);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
                    final Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postHeadWithBody(CommonResource.ADDRESSADD, body, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("AddressResult---------------->" + result);
                            if (result.equals("true")) {
                                finish();
                            }
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("AddressErrorMsg---------------->" + errorMsg);
                        }
                    }));
                }
            }
        });

    }

    @Override
    public AddressView createView() {
        return this;
    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(this);
    }

}
