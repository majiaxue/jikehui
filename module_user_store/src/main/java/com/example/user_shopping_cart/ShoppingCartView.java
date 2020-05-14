package com.example.user_shopping_cart;

import com.example.mvp.IView;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface ShoppingCartView extends IView {
    void isHide(boolean isHide);

    void isCheckAll(boolean isCheckAll);

    void totalPrice(double price);

    void loadCartRv(CartParentRecAdapter adapter);

    void updateCount(int count);

    void deleteSuccess();

    void loadSuccess();

}
