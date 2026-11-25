package org.hw_09.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CartPage {
    public ElementsCollection cartItems = $$(".cart-item");
    public SelenideElement makeOrderButton = $("#makeOrder");
    public SelenideElement totalPrice = $("#total-price");

    @Step("Нажать кнопку 'Оформить заказ'")
    public void clickMakeOrder() { makeOrderButton.click(); }

    @Step("Получить итоговую цену")
    public String getTotalPrice() { return totalPrice.getText(); }

    @Step("Получить количество единиц товара в первой строке")
    public int getFirstItemQuantity() {
        String itemText = cartItems.first().getText();
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(itemText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        throw new RuntimeException("Не удалось найти количество в тексте: " + itemText);
    }
}