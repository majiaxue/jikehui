package com.example.mine;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.HomePredictBean;
import com.example.bean.UserInfoBean;
import com.example.entity.EventBusBean;
import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;
import com.example.utils.StatusBarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 个人中心
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView, NestedScrollView.OnScrollChangeListener {

    @BindView(R2.id.mine_setting)
    ImageView mineSetting;
    @BindView(R2.id.mine_msg)
    ImageView mineMsg;
    @BindView(R2.id.mine_header)
    ImageView mineHeader;
    @BindView(R2.id.mine_lv)
    TextView mineLv;
    @BindView(R2.id.mine_name)
    TextView mineName;
    @BindView(R2.id.mine_temp)
    LinearLayout mineTemp;
    @BindView(R2.id.mine_code)
    TextView mineCode;
    @BindView(R2.id.mine_copy)
    TextView mineCopy;
    @BindView(R2.id.mine_all_order)
    LinearLayout mineAllOrder;
    @BindView(R2.id.mine_yifukuan)
    LinearLayout mineYifukuan;
    @BindView(R2.id.mine_yijiesuan)
    LinearLayout mineYijiesuan;
    @BindView(R2.id.mine_yishixiao)
    LinearLayout mineYishixiao;
    @BindView(R2.id.mine_iwantup)
    ImageView mIWantUp;
    @BindView(R2.id.mine_income_form)
    LinearLayout mineIncomeForm;
    @BindView(R2.id.mine_fans_order)
    LinearLayout mineFansOrder;
    @BindView(R2.id.mine_group_fans)
    LinearLayout mineGroupFans;
    @BindView(R2.id.mine_up_yys)
    ImageView mineUpYys;
    @BindView(R2.id.mine_rec)
    RecyclerView mineRec;
    @BindView(R2.id.mine_parent)
    NestedScrollView mineParent;
    @BindView(R2.id.mine_rela)
    RelativeLayout mineRela;
    @BindView(R2.id.mine_benri)
    TextView mBenri;
    @BindView(R2.id.mine_benyue)
    TextView mBenyue;
    @BindView(R2.id.mine_shangyue)
    TextView mShangyue;
    @BindView(R2.id.mine_balance)
    TextView mBalance;
    @BindView(R2.id.mine_my_balance)
    TextView mBalanceTxt;
    @BindView(R2.id.mine_invite_friend)
    TextView mInviteFriend;
    @BindView(R2.id.mine_shangyueyugu)
    TextView mSyyg;

    private UserInfoBean userInfo;
    private boolean flag = false;
    private long currentTime = 0;//判断点击时间间隔

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
       // Glide.with(getContext()).load(R.drawable.iwantup).into(mIWantUp);
        presenter.loadRec();
        mineParent.setOnScrollChangeListener(this);
    }

    @Override
    public void initClick() {
        mineHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToLogin();
                }
            }
        });

        mineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToLogin();
                }
            }
        });

        mineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToSetting();
                }
            }
        });

//        mIWantUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
//                    currentTime = System.currentTimeMillis();
//                    presenter.jumpToUpgrade();
//                }
//            }
//        });

        mineAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToOrder(0);
                }
            }
        });

        mineYifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToOrder(1);
                }
            }
        });

        mineYijiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToOrder(2);
                }
            }
        });

        mineYishixiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToOrder(3);
                }
            }
        });

        mineFansOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToFansOrder();
                }
            }
        });

        mineGroupFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToGroupFans();
                }
            }
        });

        mineUpYys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///module_user_store/UserActivity
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToupYYS();
                }
            }
        });

        mineIncomeForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToPredict();
                }
            }
        });

        mineCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setClipboard(userInfo.getInviteCode());
            }
        });

        mBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToBalance();
                }
            }
        });

        mInviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                    currentTime = System.currentTimeMillis();
                    presenter.jumpToInviteFriend();
                }
            }
        });

        mineMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToMsgCenter();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if ("login".equals(eventBusBean.getMsg())) {
            presenter.loadData();
        }
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int[] loction = new int[2];
        mineRela.getLocationOnScreen(loction);
        int y = loction[1];
        if (y <= getContext().getResources().getDimension(R.dimen.dp_35)) {
//            StatusBarUtils.setStatusTheme(getActivity(), false, true);
            flag = true;
            StatusBarUtils.setStatusBarColor(getActivity(), R.color.statusBg);
        } else {
            StatusBarUtils.setStatusTheme(getActivity(), true, true);
            StatusBarUtils.setAndroidNativeLightStatusBar(getActivity(), false);
            flag = false;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && TextUtils.isEmpty(SPUtil.getToken())) {
            //如果该页面可见但是未登录
            mBenri.setText("0元");
            mBenyue.setText("0元");
            mShangyue.setText("0元");
            mSyyg.setText("0元");
        } else if (!hidden && !TextUtils.isEmpty(SPUtil.getToken())) {
            //如果该页面可见并且已登录
        }

    }

    @Override
    public void loadPredict(HomePredictBean homePredictBean) {
        mBenri.setText(homePredictBean.getWaitCurrentMonth() + "元");
        mBenyue.setText(homePredictBean.getSettleCurrentMonth() + "元");
        mShangyue.setText(homePredictBean.getSettleLastMonth() + "元");
        mSyyg.setText(homePredictBean.getWaitLastMonth() + "元");
    }

    @Override
    public void loginSuccess(UserInfoBean userInfo) {
        this.userInfo = userInfo;
        mineName.setText(userInfo.getNickname());
        Glide.with(getContext()).load(userInfo.getIcon()).placeholder(R.drawable.vhjfg).apply(RequestOptions.circleCropTransform()).into(mineHeader);
        mineCode.setText("邀请码：" + userInfo.getInviteCode());
        mineTemp.setVisibility(View.VISIBLE);
        mIWantUp.setVisibility(View.VISIBLE);
        mBalanceTxt.setText(userInfo.getBlance() == null ? "0" : userInfo.getBlance());

        if (userInfo.getLevel() != null && !"".equals(userInfo.getLevel().trim())) {
            mineLv.setVisibility(View.VISIBLE);
            mineLv.setText(userInfo.getLevel());
        } else {
            mineLv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError() {
        mineName.setText("请注册/登录");
        mineHeader.setImageResource(R.drawable.vhjfg);
        mineTemp.setVisibility(View.GONE);
        mIWantUp.setVisibility(View.GONE);
        mineLv.setVisibility(View.GONE);
    }

    @Override
    public void loadMyTool(MyToolAdapter adapter) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mineRec.setLayoutManager(layoutManager);
        mineRec.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 10));
        mineRec.setAdapter(adapter);
    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }
}
