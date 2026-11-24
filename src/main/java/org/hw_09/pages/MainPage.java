package org.hw_09.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public SelenideElement adminButton = $(".btn-outline");
    public SelenideElement cartButton = $("#open-cart-btn");
    public ElementsCollection productCards = $$(".product-card");
    public SelenideElement addToCartButton = $(".product-card .btn");
    public SelenideElement firstProductTitle = $(".product-card h4");

    public void clickAdminButton() { adminButton.click(); }
    public void clickCartButton() { cartButton.click(); }
    public void clickAddToCart() { addToCartButton.click(); }
    public void clickAddToCartByIndex(int index) { productCards.get(index).$(".btn").click(); }
    public void openCart() { cartButton.click(); }
}