package com.example.buy2up;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.bean.City2Bean;
import com.example.bean.UserGoodsDetail;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/module_mine/buy2up")
public class Buy2UpActivity extends BaseActivity<Buy2UpView, Buy2UpPresenter> implements Buy2UpView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.buy2up_banner)
    XBanner buy2upBanner;
    @BindView(R2.id.buy2up_quanyi)
    TextView buy2upQuanyi;
    @BindView(R2.id.buy2up_webview)
    WebView buy2upWebview;
    @BindView(R2.id.buy2up_btn)
    TextView buy2upBtn;
    @BindView(R2.id.buy2up_name)
    TextView mName;
    @Autowired(name = "bean")
    UserGoodsDetail bean;
    JDCityPicker cityPicker;
    public JDCityConfig.ShowType mWheelType = JDCityConfig.ShowType.PRO_CITY;
    private JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
    private String citys;
    private String qu;
    private String pri;

    @Override
    public int getLayoutId() {
        return R.layout.activity_buy2up;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
//        Intent intent = getIntent();
//        bean = (UserGoodsDetail) intent.getSerializableExtra("bean");
        String albumPics = bean.getAlbumPics();
        String[] split = albumPics.split(",");
        List<BannerBean.RecordsBean> banner = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            banner.add(new BannerBean.RecordsBean(split[i]));
        }
        //门店商户不用选择城市   城市合伙人需要选择城市
        if (Integer.parseInt(bean.getLevelId())==3){
            buy2upQuanyi.setVisibility(View.VISIBLE);
        }else {
            buy2upQuanyi.setVisibility(View.GONE);
        }
        includeTitle.setText("商品详情");

        buy2upBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(Buy2UpActivity.this).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
                LogUtil.e("这是banner-----------------------" + ((BannerBean.RecordsBean) model).getXBannerUrl());
            }
        });

        buy2upWebview.getSettings().setJavaScriptEnabled(true);
        presenter.loadData(bean.getId(), bean.getLevelId());

        presenter.loadQuanyi(bean.getLevelId());

        mWheelType = JDCityConfig.ShowType.PRO_CITY_DIS;

        jdCityConfig.setShowType(mWheelType);

        cityPicker = new JDCityPicker();
        //初始化数据
        cityPicker.init(this);
        //设置JD选择器样式位只显示省份和城市两级
        cityPicker.setConfig(jdCityConfig);
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                LogUtil.e("省"+province.getName()+"---------城市：" + city.getName() + "------区：" + district.getName());
                pri= province.getName();
                citys=city.getName();
                qu=district.getName();
                buy2upQuanyi.setText(province.getName()+" "+citys+" "+qu);
            }
        });
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        buy2upQuanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.popQuanYi();
                showJD();
            }
        });

        buy2upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(bean.getLevelId())==3&&citys==null){
                    Toast.makeText(Buy2UpActivity.this, "请先选择城市", Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(bean.getLevelId())==2&&citys==null){
                    presenter.isCanUp(bean.getLevelId(), String.valueOf(bean.getId()),"");
                }else {
                    if (citys.contains("市")){
                        citys.substring(0,citys.indexOf("市"));
                        LogUtil.e("这是截取的城市"+citys.substring(0,citys.indexOf("市")));
                        presenter.isCanUp(bean.getLevelId(), String.valueOf(bean.getId()),pri+citys.substring(0,citys.indexOf("市"))+qu);
                    }else {
                        presenter.isCanUp(bean.getLevelId(), String.valueOf(bean.getId()),pri+citys+qu);
                    }
                    LogUtil.e("这是地址"+citys+qu);
                }
            }
        });
    }

    private void showJD() {
        cityPicker.showCityPicker();
    }

    @Override
    public void loadUI(UserGoodsDetail bean) {
        mName.setText(bean.getName());
        String detailHtml = bean.getDetailHtml();
        String varjs = "<script type='text/javascript'> \nwindow.onload = function()\n{var $img = document.getElementsByTagName('img');for(var p in  $img){$img[p].style.width = '100%'; $img[p].style.height ='auto'}}</script>";
        //替换img属性
        buy2upWebview.loadDataWithBaseURL(null,varjs + detailHtml, "text/html", "UTF-8",null);
    }

    @Override
    public void loadBanner(List<BannerBean.RecordsBean> bannerList) {
        buy2upBanner.setBannerData(bannerList);
    }

    @Override
    public Buy2UpView createView() {
        return this;
    }

    @Override
    public Buy2UpPresenter createPresenter() {
        return new Buy2UpPresenter(this);
    }
}
