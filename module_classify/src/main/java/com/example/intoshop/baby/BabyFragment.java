package com.example.intoshop.baby;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public class BabyFragment extends BaseFragment<BabyView, BabyPresenter> implements BabyView, View.OnClickListener {

    @BindView(R2.id.into_shop_text1)
    TextView intoShopText1;
    @BindView(R2.id.into_shop_synthesize_bottom)
    ImageView intoShopSynthesizeBottom;
    @BindView(R2.id.into_shop_synthesize)
    RelativeLayout intoShopSynthesize;
    @BindView(R2.id.into_shop_text2)
    TextView intoShopText2;
    @BindView(R2.id.into_shop_sales_volume_top)
    ImageView intoShopSalesVolumeTop;
    @BindView(R2.id.into_shop_sales_volume_bottom)
    ImageView intoShopSalesvolumeBottom;
    @BindView(R2.id.into_shop_sales_volume)
    RelativeLayout intoShopSalesvolume;
    @BindView(R2.id.into_shop_text3)
    TextView intoShopText3;
    @BindView(R2.id.into_shop_price_top)
    ImageView intoShopPriceTop;
    @BindView(R2.id.into_shop_price_bottom)
    ImageView intoShopPriceBottom;
    @BindView(R2.id.into_shop_price)
    RelativeLayout intoShopPrice;
    @BindView(R2.id.into_shop_text4)
    TextView intoShopText4;
    @BindView(R2.id.into_shop_credit_top)
    ImageView intoShopCreditTop;
    @BindView(R2.id.into_shop_credit_bottom)
    ImageView intoShopCreditBottom;
    @BindView(R2.id.into_shop_credit)
    RelativeLayout intoShopCredit;
    @BindView(R2.id.into_shop_switchover)
    ImageView intoShopSwitchover;
    @BindView(R2.id.into_shop_rec)
    RecyclerView intoShopRec;

    private boolean shop_image_state = true;
    private boolean shop_salesvolume = true;
    private boolean shop_price = true;
    private boolean shop_credit = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_into_shop_baby;
    }

    @Override
    public void initData() {
        presenter.setBabyRec(intoShopRec);
    }

    @Override
    public void initClick() {
        intoShopSwitchover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shop_image_state) {
                    //瀑布流布局
                    presenter.setBabyRecStaggeredGrid(intoShopRec);
                    intoShopSwitchover.setImageResource(R.drawable.fghfghfg);
                    shop_image_state = false;
                } else {
                    //切换布局条形
                    presenter.setBabyRec(intoShopRec);
                    intoShopSwitchover.setImageResource(R.drawable.xfxfgvx);
                    shop_image_state = true;
                }
            }
        });

        intoShopSalesvolume.setOnClickListener(this);
        intoShopPrice.setOnClickListener(this);
        intoShopCredit.setOnClickListener(this);
        intoShopSynthesize.setOnClickListener(this);
    }

    @Override
    public BabyView createView() {
        return this;
    }

    @Override
    public BabyPresenter createPresenter() {
        return new BabyPresenter(getContext());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.into_shop_sales_volume) {
            //销量
            if (shop_salesvolume) {
                intoShopText2.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText3.setTextColor(Color.parseColor("#333333"));
                intoShopText4.setTextColor(Color.parseColor("#333333"));
                intoShopSalesVolumeTop.setImageResource(R.drawable.gvhgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopPriceTop.setImageResource(R.drawable.ghfgh);
                intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopCreditTop.setImageResource(R.drawable.ghfgh);
                intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_salesvolume = false;
            } else {
                intoShopText2.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText3.setTextColor(Color.parseColor("#333333"));
                intoShopText4.setTextColor(Color.parseColor("#333333"));
                intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.cgbhdfg);
                intoShopPriceTop.setImageResource(R.drawable.ghfgh);
                intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopCreditTop.setImageResource(R.drawable.ghfgh);
                intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_salesvolume = true;
            }
        } else if (v.getId() == R.id.into_shop_price) {
            //价格
            if (shop_price) {
                intoShopText3.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText2.setTextColor(Color.parseColor("#333333"));
                intoShopText4.setTextColor(Color.parseColor("#333333"));
                intoShopPriceTop.setImageResource(R.drawable.gvhgh);
                intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopCreditTop.setImageResource(R.drawable.ghfgh);
                intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_price = false;
            } else {
                intoShopText3.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText2.setTextColor(Color.parseColor("#333333"));
                intoShopText4.setTextColor(Color.parseColor("#333333"));
                intoShopPriceTop.setImageResource(R.drawable.ghfgh);
                intoShopPriceBottom.setImageResource(R.drawable.cgbhdfg);
                intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopCreditTop.setImageResource(R.drawable.ghfgh);
                intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_price = true;
            }

        } else if (v.getId() == R.id.into_shop_credit) {
            //信用
            if (shop_credit) {
                intoShopText4.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText2.setTextColor(Color.parseColor("#333333"));
                intoShopText3.setTextColor(Color.parseColor("#333333"));
                intoShopCreditTop.setImageResource(R.drawable.gvhgh);
                intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopPriceTop.setImageResource(R.drawable.ghfgh);
                intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_credit = false;
            } else {
                intoShopText4.setTextColor(Color.parseColor("#FFFFFF"));
                intoShopText1.setTextColor(Color.parseColor("#333333"));
                intoShopText2.setTextColor(Color.parseColor("#333333"));
                intoShopText3.setTextColor(Color.parseColor("#333333"));
                intoShopCreditTop.setImageResource(R.drawable.ghfgh);
                intoShopCreditBottom.setImageResource(R.drawable.cgbhdfg);
                intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
                intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                intoShopPriceTop.setImageResource(R.drawable.ghfgh);
                intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
                shop_credit = true;
            }
        } else {
            intoShopText1.setTextColor(Color.parseColor("#FFFFFF"));
            intoShopText2.setTextColor(Color.parseColor("#333333"));
            intoShopText3.setTextColor(Color.parseColor("#333333"));
            intoShopText4.setTextColor(Color.parseColor("#333333"));
            intoShopCreditTop.setImageResource(R.drawable.ghfgh);
            intoShopCreditBottom.setImageResource(R.drawable.khjkjhgjk);
            intoShopSalesVolumeTop.setImageResource(R.drawable.ghfgh);
            intoShopSalesvolumeBottom.setImageResource(R.drawable.khjkjhgjk);
            intoShopPriceTop.setImageResource(R.drawable.ghfgh);
            intoShopPriceBottom.setImageResource(R.drawable.khjkjhgjk);
        }
    }
}
