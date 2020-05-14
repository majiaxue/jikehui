package com.example.collection;

import com.example.collection.adapter.CollectionAdapter;
import com.example.mvp.IView;

public interface CollectionView extends IView {

    void toEdit();

    void toFinish();

    void allCheck();

    void notAllCheck();

    void loadUI(CollectionAdapter adapter);

    void loadFinish(int size);
}
