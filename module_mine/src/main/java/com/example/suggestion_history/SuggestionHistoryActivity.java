package com.example.suggestion_history;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.suggestion_history.adapter.SuggestionHistoryAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class SuggestionHistoryActivity extends BaseActivity<SuggestionHistoryView, SuggestionHistoryPresenter> implements SuggestionHistoryView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.suggestion_history_rv)
    RecyclerView suggestionHistoryRv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_suggestion_history;
    }

    @Override
    public void initData() {
        includeTitle.setText("反馈历史");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        suggestionHistoryRv.setLayoutManager(layoutManager);
        suggestionHistoryRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_20)));
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
    }

    @Override
    public void loadRv(SuggestionHistoryAdapter adapter) {
        suggestionHistoryRv.setAdapter(adapter);
    }

    @Override
    public SuggestionHistoryView createView() {
        return this;
    }

    @Override
    public SuggestionHistoryPresenter createPresenter() {
        return new SuggestionHistoryPresenter(this);
    }
}
