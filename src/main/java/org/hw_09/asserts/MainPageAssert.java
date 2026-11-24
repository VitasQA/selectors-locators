package org.hw_09.asserts;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.hw_09.pages.MainPage;

public class MainPageAssert {
    private final MainPage mainPage;
    public MainPageAssert(MainPage mainPage) { this.mainPage = mainPage; }

    public void checkAdminButtonVisible() { mainPage.adminButton.shouldBe(Condition.visible); }
    public void checkCartButtonVisible() { mainPage.cartButton.shouldBe(Condition.visible); }

    public void checkProductCardsNotEmpty() {
        mainPage.productCards.shouldBe(CollectionCondition.sizeGreaterThan(0));
    }


    public void checkFirstProductTitleContains(String text) {
        mainPage.productCards.findBy(Condition.text(text)).shouldBe(Condition.visible);
    }
}