package org.hw_09.asserts;

import com.codeborne.selenide.CollectionCondition;
import org.hw_09.pages.CartPage;

public class CartPageAssert {
    private final CartPage cartPage;
    public CartPageAssert(CartPage cartPage) { this.cartPage = cartPage; }

    public void checkQuantityOfFirstItem(int expectedQuantity) {
        int actualQty = cartPage.getFirstItemQuantity();
        assert actualQty == expectedQuantity :
                "Ожидалось " + expectedQuantity + " единиц, а в корзине: " + actualQty;
    }

    public void checkCartNotEmpty() {
        cartPage.cartItems.shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void checkTotalPriceGreaterThanZero() {
        int total = Integer.parseInt(cartPage.getTotalPrice());
        assert total > 0;
    }
}