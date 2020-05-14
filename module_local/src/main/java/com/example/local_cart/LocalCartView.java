package com.example.local_cart;

import com.example.mvp.IView;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;

public interface LocalCartView extends IView {
    void loadSuccess();

    void isHide(boolean b);

    void isCheckAll(boolean isAllCheck);

    void loadCartRv(CartParentRecAdapter adapter);

    void deleteSuccess();

    void updateCount(int count);

    void totalPrice(double totalPrice);
}
