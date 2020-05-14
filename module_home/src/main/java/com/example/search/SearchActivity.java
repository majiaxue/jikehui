package com.example.search;

import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.dbflow.DBflowUtil;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.example.view.FlowLayout;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * 搜索页面
 */
@Route(path = "/module_home/SearchActivity")
public class SearchActivity extends BaseActivity<SearchView, SearchPresenter> implements SearchView {

    @BindView(R2.id.search_back)
    ImageView searchBack;
    @BindView(R2.id.search_edit)
    EditText searchEdit;
    @BindView(R2.id.search_text)
    TextView searchText;
    @BindView(R2.id.search_delete)
    ImageView searchDelete;
    @BindView(R2.id.search_flow_layout)
    FlowLayout searchFlowLayout;
    @BindView(R2.id.search_tab)
    TabLayout mTabLayout;

    private int position;
    private String[] titleArr = {"淘宝", "拼多多", "京东"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        mTabLayout.addTab(mTabLayout.newTab().setText(titleArr[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titleArr[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(titleArr[2]));

        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) mTabLayout.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    public void initClick() {
        searchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchEdit(searchEdit.getText().toString());

            }
        });

        searchDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBflowUtil.getInstance().deleteAll(CommonResource.HISTORY_TBK);
                searchFlowLayout.removeAllViews();
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    presenter.position = 0;
                } else if (tab.getPosition() == 1) {
                    presenter.position = 1;
                } else if (tab.getPosition() == 2) {
                    presenter.position = 2;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        searchEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == event.KEYCODE_ENTER) {
                    presenter.searchEdit(searchEdit.getText().toString());
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchFlowLayout.removeAllViews();
        presenter.getHistory(searchFlowLayout);
    }

    @Override
    public SearchView createView() {
        return this;
    }

    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

}
