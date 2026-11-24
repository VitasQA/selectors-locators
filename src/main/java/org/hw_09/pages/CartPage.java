package org.hw_09.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CartPage {
    public ElementsCollection cartItems = $$(".cart-item");
    public SelenideElement makeOrderButton = $("#makeOrder");
    public SelenideElement totalPrice = $("#total-price");

    public void clickMakeOrder() { makeOrderButton.click(); }
    public String getTotalPrice() { return totalPrice.getText(); }

    public int getFirstItemQuantity() {
        String itemText = cartItems.first().getText();
        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d+").matcher(itemText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        throw new RuntimeException("Не удалось найти количество в тексте: " + itemText);
    }
}