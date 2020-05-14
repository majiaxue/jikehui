package com.example.local_mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.RedPackageBean;
import com.example.common.CommonResource;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SPUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

public class LocalMineFragment extends BaseFragment<LocalMineView, LocalMinePresenter> implements LocalMineView {
    @BindView(R2.id.local_mine_setting)
    ImageView localMineSetting;
    @BindView(R2.id.local_mine_msg)
    ImageView localMineMsg;
    @BindView(R2.id.local_mine_name)
    TextView localMineName;
    @BindView(R2.id.local_mine_uid)
    TextView localMineUid;
    @BindView(R2.id.local_mine_header)
    ImageView localMineHeader;
    @BindView(R2.id.local_mine_xbanner)
    XBanner localMineXbanner;
    @BindView(R2.id.local_mine_shangjiaruzhu)
    LinearLayout localMineShangjiaruzhu;
    @BindView(R2.id.local_mine_shouhuodizhi)
    LinearLayout localMineShouhuodizhi;
    @BindView(R2.id.local_mine_youhuiquan)
    LinearLayout localMineYouhuiquan;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_mine;
    }

    @Override
    public void initData() {
        Glide.with(getContext()).load(SPUtil.getStringValue(CommonResource.USER_PIC)).placeholder(R.drawable.vhjfg).apply(RequestOptions.circleCropTransform()).into(localMineHeader);
        localMineName.setText(SPUtil.getStringValue(CommonResource.USER_NAME));
        localMineUid.setText(SPUtil.getStringValue(CommonResource.USER_INVITE));
        presenter.getRedPackage();
    }

    @Override
    public void initClick() {
        localMineXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                presenter.jumpToWallet();
            }
        });

        localMineShangjiaruzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localMineShangjiaruzhu.setEnabled(false);
                presenter.businessApplication();
            }
        });

        localMineShouhuodizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation();
            }
        });

        localMineYouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/CouponActivity").withString("from", CommonResource.HISTORY_LOCAL).navigation();
            }
        });
    }

    @Override
    public void loadBanner(List<RedPackageBean> redPackageBeans) {
        localMineXbanner.setBannerData(R.layout.xbanner_local_mine, redPackageBeans);

        localMineXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                TextView money = view.findViewById(R.id.xbanner_local_mine_money);
                TextView count = view.findViewById(R.id.xbanner_local_mine_count);
                LinearLayout linear = view.findViewById(R.id.xbanner_local_mine_parent);

                money.setText("￥" + ((RedPackageBean) model).getMoney());
                count.setText("(X" + ((RedPackageBean) model).getCount() + "张)");
                linear.setBackgroundResource(((RedPackageBean) model).getBackground());
            }
        });
    }

    @Override
    public void callBack() {
        localMineShangjiaruzhu.setEnabled(true);
    }

    @Override
    public LocalMineView createView() {
        return this;
    }

    @Override
    public LocalMinePresenter createPresenter() {
        return new LocalMinePresenter(getContext());
    }
}
