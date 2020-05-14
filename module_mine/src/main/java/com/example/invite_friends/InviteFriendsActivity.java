package com.example.invite_friends;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.bean.InviteBean;
import com.example.invite_friends.adapter.InviteVpAdapter;
import com.example.module_base.ModuleBaseApplication;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.UIHelper;
import com.stx.xhb.xbanner.transformers.BasePageTransformer;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;

import butterknife.BindView;

@Route(path = "/mine/invite_friends")
public class InviteFriendsActivity extends BaseActivity<InviteFriendsView, InviteFriendsPresenter> implements InviteFriendsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.invite_friends_link)
    TextView inviteFriendsLink;
    @BindView(R2.id.invite_friends_bill)
    TextView inviteFriendsBill;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.invite_friends_vp)
    ViewPager mVP;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_friends;
    }

    @Override
    public void initData() {
        includeTitle.setText("邀请好友");
        includeRight.setImageResource(R.drawable.icon_guize);
        includeRight.setVisibility(View.VISIBLE);

        ModuleBaseApplication.initShare();
        mVP.setPageTransformer(false, BasePageTransformer.getPageTransformer(Transformer.Scale));
        mVP.setPageMargin((int) getResources().getDimension(R.dimen.dp_20));
        presenter.loadData();

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inviteFriendsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareLink();
            }
        });

        inviteFriendsBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.share(mVP.getCurrentItem());
            }
        });

//        inviteFriendsBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(XBanner banner, Object model, View view, int position) {
//                UIHelper.seeBigBitmap(InviteFriendsActivity.this, ((InviteBean) model).getXBannerUrl());
//            }
//        });

        includeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showRules(InviteFriendsActivity.this);
            }
        });
    }
    @Override
    public void loadVP(InviteVpAdapter adapter, int size) {
        mVP.setAdapter(adapter);
        mVP.setCurrentItem(Integer.MAX_VALUE / 2, false);
    }

    @Override
    public InviteFriendsView createView() {
        return this;
    }

    @Override
    public InviteFriendsPresenter createPresenter() {
        return new InviteFriendsPresenter(this);
    }
}
