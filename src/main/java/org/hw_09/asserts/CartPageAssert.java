package org.hw_09.asserts;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.hw_09.pages.CartPage;

public class CartPageAssert {
    private final CartPage cartPage;
    public CartPageAssert(CartPage cartPage) { this.cartPage = cartPage; }

    @Step("Проверить, что количество единиц в первой строке = {expectedQuantity}")
    public void checkQuantityOfFirstItem(int expectedQuantity) {
        int actualQty = cartPage.getFirstItemQuantity();
        assert actualQty == expectedQuantity : "Ожидалось " + expectedQuantity + " единиц, а в корзине: " + actualQty;
    }

    @Step("Проверить, что корзина не пуста")
    public void checkCartNotEmpty() {
        cartPage.cartItems.shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Step("Проверить, что итоговая цена больше нуля")
    public void checkTotalPriceGreaterThanZero() {
        int total = Integer.parseInt(cartPage.getTotalPrice());
        assert total > 0;
    }
}