package com.example.suggestion_history;

import com.example.mvp.IView;
import com.example.suggestion_history.adapter.SuggestionHistoryAdapter;

public interface SuggestionHistoryView extends IView {
    void loadRv(SuggestionHistoryAdapter adapter);
}
