package com.example.goodscollection;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.goodscollection.adapter.GoodsCollectionRecAdapter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品收藏
 */
@Route(path = "/module_user_mine/GoodsCollectionActivity")
public class GoodsCollectionActivity extends BaseActivity<GoodsCollectionView, GoodsCollectionPresenter> implements GoodsCollectionView {

    @BindView(R2.id.goods_collection_go)
    TextView goodsCollectionGo;
    @BindView(R2.id.goods_collection_empty)
    LinearLayout goodsCollectionEmpty;
    @BindView(R2.id.goods_collection_rec)
    RecyclerView goodsCollectionRec;
    @BindView(R2.id.goods_collection_bottom_rec)
    RecyclerView goodsCollectionBottomRec;
    @BindView(R2.id.goods_collection_empty_hide)
    LinearLayout goodsCollectionEmptyHide;
    @BindView(R2.id.goods_collection_check_all)
    ImageView goodsCollectionCheckAll;
    @BindView(R2.id.goods_collection_delete)
    TextView goodsCollectionDelete;
    @BindView(R2.id.goods_collection_bottom)
    LinearLayout goodsCollectionBottom;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    //全选初始状态
    private boolean checkAll = false;
    //编辑初始状态
    private boolean isCompile = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_collection;
    }

    @Override
    public void initData() {
        includeTitle.setText("商品收藏");
        includeRightBtn.setText("编辑");
        includeRightBtn.setVisibility(View.VISIBLE);

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goodsCollectionGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去商品页
//                Toast.makeText(GoodsCollectionActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_user_store/typeDetail").withBoolean("hotSale", true).navigation();

            }
        });
        //编辑
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.compile();
            }
        });
        //全选
        goodsCollectionCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAll) {
                    goodsCollectionCheckAll.setImageResource(R.drawable.icon_xuanzhong);
                    checkAll = false;
                } else {
                    goodsCollectionCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
                    checkAll = true;
                }
                presenter.checkAll(checkAll);
            }
        });
        //删除
        goodsCollectionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteList();
            }
        });

    }

    @Override
    public GoodsCollectionView createView() {
        return this;
    }

    @Override
    public GoodsCollectionPresenter createPresenter() {
        return new GoodsCollectionPresenter(this);
    }

    @Override
    public void isCompile(boolean isCompile) {
        goodsCollectionRec.setFocusable(true);
        goodsCollectionRec.setFocusableInTouchMode(true);
        if (isCompile) {
            includeRightBtn.setText("完成");
            goodsCollectionBottom.setVisibility(View.VISIBLE);
        } else {
            includeRightBtn.setText("编辑");
            goodsCollectionBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void isCheckAll(boolean isCheckAll) {
        if (isCheckAll) {
            goodsCollectionCheckAll.setImageResource(R.drawable.icon_xuanzhong);
            checkAll = false;
        } else {
            goodsCollectionCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
            checkAll = true;
        }
    }

    @Override
    public void loadUI(GoodsCollectionRecAdapter adapter) {
        goodsCollectionRec.setAdapter(adapter);
    }

    @Override
    public void empty(boolean isEmpty) {
        if (isEmpty) {
            goodsCollectionEmpty.setVisibility(View.VISIBLE);
            includeRightBtn.setVisibility(View.GONE);

        } else {
            goodsCollectionEmpty.setVisibility(View.GONE);
            includeRightBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadCommend(BaseRecStaggeredAdapter adapter) {
        goodsCollectionBottomRec.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setGoodsCollectionRec(goodsCollectionRec);

        //为您推荐
        presenter.setGoodsCollectionBottomRec(goodsCollectionBottomRec);
    }
}
