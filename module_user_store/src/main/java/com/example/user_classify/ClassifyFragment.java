package com.example.user_classify;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.mvp.BaseFragment;
import com.example.search.UserSearchActivity;
import com.example.user_classify.adapter.UserLeftRvAdapter;
import com.example.user_classify.adapter.UserRightRecAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {
    @BindView(R2.id.user_classify_search)
    TextView userClassifySearch;
    @BindView(R2.id.user_classify_msg_img)
    ImageView userClassifyMsgImg;
    @BindView(R2.id.user_classify_message)
    LinearLayout userClassifyMessage;
    @BindView(R2.id.user_classify_rv_left)
    RecyclerView leftRv;
    @BindView(R2.id.user_classify_x_banner)
    XBanner userClassifyXBanner;
    @BindView(R2.id.user_classify_rec)
    RecyclerView userClassifyRec;

    public static int position = -1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_classify;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftRv.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userClassifyRec.setLayoutManager(layoutManager1);

        presenter.loadData();
        //xBanner
        presenter.setXBanner();
    }

    @Override
    public void initClick() {
        userClassifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                startActivity(new Intent(getContext(), UserSearchActivity.class));
            }
        });

        userClassifyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && position != -1) {
            presenter.formHomeNavbar(position);
            position = -1;
        }
    }

    @Override
    public void loadRv(UserLeftRvAdapter leftAdapter, UserRightRecAdapter rightAdapter) {
        leftRv.setAdapter(leftAdapter);
        userClassifyRec.setAdapter(rightAdapter);
        presenter.setLeftRvCLick();
    }

    @Override
    public void loadBanner(final List<BannerBean.RecordsBean> list) {
        userClassifyXBanner.setBannerData(list);
        userClassifyXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(R.dimen.dp_10))).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        userClassifyXBanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        userClassifyXBanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        userClassifyXBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(getContext(), "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public ClassifyView createView() {
        return this;
    }

    @Override
    public ClassifyPresenter createPresenter() {
        return new ClassifyPresenter(getContext());
    }
}
