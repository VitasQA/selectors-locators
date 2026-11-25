package org.hw_09.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public SelenideElement adminButton = $(".btn-outline");
    public SelenideElement cartButton = $("#open-cart-btn");
    public ElementsCollection productCards = $$(".product-card");
    public SelenideElement addToCartButton = $(".product-card .btn");
    public SelenideElement firstProductTitle = $(".product-card h4");

    @Step("Нажать кнопку 'Администрирование'")
    public void clickAdminButton() { adminButton.click(); }

    @Step("Нажать кнопку 'Корзина'")
    public void clickCartButton() { cartButton.click(); }

    @Step("Добавить первый товар в корзину")
    public void clickAddToCart() { addToCartButton.click(); }

    @Step("Добавить товар с индексом {index} в корзину")
    public void clickAddToCartByIndex(int index) { productCards.get(index).$(".btn").click(); }

    @Step("Открыть корзину")
    public void openCart() { cartButton.click(); }
}