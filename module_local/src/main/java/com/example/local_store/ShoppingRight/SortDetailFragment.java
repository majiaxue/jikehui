package com.example.local_store.ShoppingRight;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_store.adapter.ShoppingRightAdapter;
import com.example.module_local.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by yadianna02 on 2018/7/31.
 */

public class SortDetailFragment extends BaseFragment<SortDetailPresenter, String> implements CheckListener {
    private RecyclerView mRv;
    private ShoppingRightAdapter mAdapter;
    private GridLayoutManager mManager;
    private List<LocalStoreBean.ListBean> mDatas;
    private boolean move = false;
    private int mIndex = 0;
    private CheckListener checkListener;
    private ItemHeaderDecoration mDecoration;
    private ShopOnClickListtener listtener;

    public SortDetailFragment() {
    }

    @SuppressLint("ValidFragment")
    public SortDetailFragment(List<LocalStoreBean.ListBean> mDatas, ShopOnClickListtener listtener) {
        this.mDatas = mDatas;
        this.listtener = listtener;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void msgEventBus(EventBusBean eventBusBean) {
        if (CommonResource.SUBMIT_ORDER.equals(eventBusBean.getMsg())) {
            presenter.loadCart();
        } else if (CommonResource.UPCART.equals(eventBusBean.getCode())) {
            String result = eventBusBean.getMsg();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sort_detail;
    }

    @Override
    protected void initCustomView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
    }

    @Override
    protected void initListener() {
        mRv.addOnScrollListener(new RecyclerViewListener());
    }

    @Override
    protected SortDetailPresenter initPresenter() {
        showRightPage(1);
        mManager = new GridLayoutManager(mContext, 1);
        //通过isTitle的标志来判断是否是title
//        mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//            }
//        });
        mRv.setLayoutManager(mManager);
        mAdapter = new ShoppingRightAdapter(getContext(), mDatas, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {

            }
        }, listtener);
        mRv.setAdapter(mAdapter);
        mDecoration = new ItemHeaderDecoration(mContext, mDatas);
        mRv.addItemDecoration(mDecoration);
        mRv.addItemDecoration(new ShopRightSpaceItemDecoration(0, 15));
        mDecoration.setCheckListener(checkListener);

        mAdapter.notifyDataSetChanged();
        mDecoration.setData(mDatas);
        return new SortDetailPresenter();
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void check(int position, boolean isScroll) {

    }

    @Override
    public void refreshView(int code, String data) {

    }

    public void setData(int n) {
        mIndex = n;
        mRv.stopScroll();
        smoothMoveToPosition(n);
    }

    public void setListener(CheckListener listener) {
        this.checkListener = listener;
    }

    private void smoothMoveToPosition(int n) {
        int firstItem = mManager.findFirstVisibleItemPosition();
        int lastItem = mManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRv.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRv.getChildAt(n - firstItem).getTop();
            mRv.scrollBy(0, top);
        } else {
            mRv.scrollToPosition(n);
            move = true;
        }
    }

    private class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                Log.e("tag", String.valueOf(n));
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    Log.e("tag", String.valueOf(top));
                    mRv.smoothScrollBy(0, top);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - mManager.findFirstVisibleItemPosition();
                if (0 <= n && n < mRv.getChildCount()) {
                    int top = mRv.getChildAt(n).getTop();
                    mRv.scrollBy(0, top);
                }
            }
        }
    }
}
