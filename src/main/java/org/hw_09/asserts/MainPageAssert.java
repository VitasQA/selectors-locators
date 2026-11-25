package org.hw_09.asserts;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.hw_09.pages.MainPage;

public class MainPageAssert {
    private final MainPage mainPage;
    public MainPageAssert(MainPage mainPage) { this.mainPage = mainPage; }

    @Step("Проверить, что кнопка 'Администрирование' видима")
    public void checkAdminButtonVisible() { mainPage.adminButton.shouldBe(Condition.visible); }

    @Step("Проверить, что кнопка 'Корзина' видима")
    public void checkCartButtonVisible() { mainPage.cartButton.shouldBe(Condition.visible); }

    @Step("Проверить, что карточки товаров отображаются")
    public void checkProductCardsNotEmpty() { mainPage.productCards.shouldBe(CollectionCondition.sizeGreaterThan(0)); }

    @Step("Проверить, что первая карточка содержит текст: {text}")
    public void checkFirstProductTitleContains(String text) { mainPage.firstProductTitle.shouldHave(Condition.text(text)); }
}