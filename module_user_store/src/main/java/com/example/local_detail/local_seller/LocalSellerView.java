package com.example.local_detail.local_seller;

import com.example.local_detail.adapter.SellerImaAdapter;
import com.example.mvp.IView;

public interface LocalSellerView extends IView {
    void loadImg(SellerImaAdapter adapter);
}
