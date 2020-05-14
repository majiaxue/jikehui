package com.example.browsinghistory;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 浏览历史
 */
@Route(path = "/module_user_mine/BrowsingHistoryActivity")
public class BrowsingHistoryActivity extends BaseActivity<BrowsingHistoryView, BrowsingHistoryPresenter> implements BrowsingHistoryView {

    @BindView(R2.id.browsing_history_rec)
    RecyclerView browsingHistoryRec;
    @BindView(R2.id.browsing_history_check_all)
    ImageView browsingHistoryCheckAll;
    @BindView(R2.id.browsing_history_delete)
    TextView browsingHistoryDelete;
    @BindView(R2.id.browsing_history_bottom)
    LinearLayout browsingHistoryBottom;
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    //全选初始状态
    private boolean isCheckAllParent = false;
//    //编辑初始状态
//    private boolean isCompile = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_browsing_history;
    }

    @Override
    public void initData() {
        includeRightBtn.setText("编辑");
        includeTitle.setText("浏览记录");
//        includeRightBtn.setVisibility(View.VISIBLE);
        presenter.browsingHistoryRec(browsingHistoryRec);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //编辑
        includeRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isCompile) {
//                    browsingHistoryState.setText("完成");
//                    browsingHistoryBottom.setVisibility(View.VISIBLE);
//                    isCompile = false;
//                } else {
//                    browsingHistoryState.setText("编辑");
//                    browsingHistoryBottom.setVisibility(View.GONE);
//                    isCompile = true;
//                }
                presenter.browsingHistoryState();
            }
        });
        //全选
        browsingHistoryCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheckAllParent) {
                    browsingHistoryCheckAll.setImageResource(R.drawable.icon_xuanzhong);
                    isCheckAllParent = false;
                } else {
                    browsingHistoryCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
                    isCheckAllParent = true;
                }
                presenter.checkAllParent(isCheckAllParent);
            }
        });
        //删除
        browsingHistoryDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteList();
            }
        });
    }

    @Override
    public BrowsingHistoryView createView() {
        return this;
    }

    @Override
    public BrowsingHistoryPresenter createPresenter() {
        return new BrowsingHistoryPresenter(this);
    }

    @Override
    public void isCompile(boolean isCompile) {
        if (isCompile) {
            includeRightBtn.setText("完成");
            browsingHistoryBottom.setVisibility(View.VISIBLE);
        } else {
            includeRightBtn.setText("编辑");
            browsingHistoryBottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void isCheckAll(boolean isCheckAll) {
        if (isCheckAll) {
            browsingHistoryCheckAll.setImageResource(R.drawable.icon_xuanzhong);
            isCheckAllParent = false;
        } else {
            browsingHistoryCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
            isCheckAllParent = true;
        }
    }

    @Override
    public void empty(boolean isEmpty) {
        if (isEmpty) {
            browsingHistoryRec.setVisibility(View.GONE);
            includeRightBtn.setVisibility(View.GONE);

        } else {
            browsingHistoryRec.setVisibility(View.VISIBLE);
            includeRightBtn.setVisibility(View.VISIBLE);
        }
    }
}
