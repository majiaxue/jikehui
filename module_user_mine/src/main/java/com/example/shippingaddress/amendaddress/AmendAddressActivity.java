package com.example.shippingaddress.amendaddress;

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
import com.example.bean.AmendAddressBean;
import com.example.bean.ShippingAddressBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R2;
import com.example.module_user_mine.R;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 修改地址
 */
@Route(path = "/module_user_mine/AmendAddressActivity")
public class AmendAddressActivity extends BaseActivity<AmendAddressView, AmendAddressPresenter> implements AmendAddressView {


    @BindView(R2.id.amend_address_image_back)
    ImageView amendAddressImageBack;
    @BindView(R2.id.amend_address_delete)
    TextView amendAddressDelete;
    @BindView(R2.id.amend_address_name)
    EditText amendAddressName;
    @BindView(R2.id.amend_address_phone)
    EditText amendAddressPhone;
    @BindView(R2.id.amend_address_detailed)
    EditText amendAddressDetailed;
    @BindView(R2.id.amend_address_home)
    RadioButton amendAddressHome;
    @BindView(R2.id.amend_address_company)
    RadioButton amendAddressCompany;
    @BindView(R2.id.amend_address_school)
    RadioButton amendAddressSchool;
    @BindView(R2.id.amend_address_radio)
    RadioGroup amendAddressRadio;
    @BindView(R2.id.amend_address_switch)
    Switch amendAddressSwitch;
    @BindView(R2.id.amend_address_save)
    TextView amendAddressSave;
    @BindView(R2.id.amend_address_province)
    TextView amendAddressProvince;
    @BindView(R2.id.amend_address_city)
    TextView amendAddressCity;
    @BindView(R2.id.amend_address_area)
    TextView amendAddressArea;
    @BindView(R2.id.amend_address_where)
    LinearLayout amendAddressWhere;

    private List<ShippingAddressBean> shippingAddressBeanList;
    private int position;

    @Override
    public int getLayoutId() {
        return R.layout.activity_amend_address;
    }

    @Override
    public void initData() {
        position = getIntent().getIntExtra("position", 0);
        shippingAddressBeanList = (List<ShippingAddressBean>) getIntent().getSerializableExtra("shippingAddressBeanList");
        amendAddressName.setText(shippingAddressBeanList.get(position).getAddressName());
        amendAddressPhone.setText(shippingAddressBeanList.get(position).getAddressPhone());
        amendAddressProvince.setText(shippingAddressBeanList.get(position).getAddressProvince());
        amendAddressCity.setText(shippingAddressBeanList.get(position).getAddressCity());
        amendAddressArea.setText(shippingAddressBeanList.get(position).getAddressArea());
        amendAddressDetailed.setText(shippingAddressBeanList.get(position).getAddressDetail());

        if (shippingAddressBeanList.get(position).getAddressTips() == 1) {
            amendAddressHome.setChecked(true);
        } else if (shippingAddressBeanList.get(position).getAddressTips() == 2) {
            amendAddressCompany.setChecked(true);
        } else {
            amendAddressSchool.setChecked(true);
        }

        if (shippingAddressBeanList.get(position).getAddressDefault() == 1) {
            amendAddressSwitch.setChecked(true);
        } else {
            amendAddressSwitch.setChecked(false);
        }


    }

    @Override
    public void initClick() {
        amendAddressImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        amendAddressWhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupAddressWhere(amendAddressProvince, amendAddressCity, amendAddressArea);
            }
        });

        //删除
        amendAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //删除地址
                final SelfDialog selfDialog = new SelfDialog(AmendAddressActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("您确定要删除此地址吗？");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).deleteDataWithout(CommonResource.DELETEADDRESS + "/" + shippingAddressBeanList.get(position).getId(), SPUtil.getToken());
                        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                            @Override
                            public void onSuccess(String result, String msg) {
                                selfDialog.dismiss();
                                PopUtils.setTransparency(AmendAddressActivity.this, 1f);
                                finish();
                            }

                            @Override
                            public void onError(String errorCode, String errorMsg) {
                                LogUtil.e("shippingAddressErrorMsg删除地址------>" + errorMsg);
                            }
                        }));

                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                        PopUtils.setTransparency(AmendAddressActivity.this, 1f);
                    }
                });
                PopUtils.setTransparency(AmendAddressActivity.this, 0.3f);
                selfDialog.show();
            }
        });

        //保存
        amendAddressSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(amendAddressName.getText().toString())) {
                    Toast.makeText(AmendAddressActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(amendAddressPhone.getText().toString())) {
                    Toast.makeText(AmendAddressActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(amendAddressPhone.getText().toString())) {
                    Toast.makeText(AmendAddressActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(amendAddressCity.getText().toString()) && TextUtils.isEmpty(amendAddressArea.getText().toString())) {
                    Toast.makeText(AmendAddressActivity.this, "请选择地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(amendAddressDetailed.getText().toString())) {
                    Toast.makeText(AmendAddressActivity.this, "请填写详细地址", Toast.LENGTH_SHORT).show();
                } else {
                    AmendAddressBean amendAddressBean = new AmendAddressBean();
                    amendAddressBean.setId(shippingAddressBeanList.get(position).getId());
                    amendAddressBean.setUserCode(shippingAddressBeanList.get(position).getUserCode());
                    LogUtil.e("userCode------>" + shippingAddressBeanList.get(position).getUserCode());
                    amendAddressBean.setAddressName(amendAddressName.getText().toString());
                    amendAddressBean.setAddressPhone(amendAddressPhone.getText().toString());
                    amendAddressBean.setAddressProvince(amendAddressProvince.getText().toString());
                    amendAddressBean.setAddressCity(amendAddressCity.getText().toString());
                    amendAddressBean.setAddressArea(amendAddressArea.getText().toString());
                    amendAddressBean.setAddressDetail(amendAddressDetailed.getText().toString());
                    if (amendAddressHome.isChecked()) {
                        amendAddressBean.setAddressTips(1);
                    } else if (amendAddressCompany.isChecked()) {
                        amendAddressBean.setAddressTips(2);
                    } else if (amendAddressSchool.isChecked()) {
                        amendAddressBean.setAddressTips(3);
                    }
                    if (amendAddressSwitch.isChecked()) {
                        amendAddressBean.setAddressDefault(1);
                    } else {
                        amendAddressBean.setAddressDefault(0);
                    }
                    String amendAddressJson = JSON.toJSONString(amendAddressBean);
                    LogUtil.e("AmendAddressActivityJson----------->" + amendAddressJson);
                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), amendAddressJson);
                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).putDataBody(CommonResource.AMENDADDRESS, body, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("AmendAddressActivityResult----------->" + result);
                            finish();
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("AmendAddressActivityErrorMsg----------->" + errorMsg);
                        }
                    }));

                }
            }
        });
    }

    @Override
    public AmendAddressView createView() {
        return this;
    }

    @Override
    public AmendAddressPresenter createPresenter() {
        return new AmendAddressPresenter(this);
    }

}
