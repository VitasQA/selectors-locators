package org.hw_09.asserts;

import com.codeborne.selenide.Condition;
import org.hw_09.pages.AdminPage;

import static com.codeborne.selenide.Selenide.$;

public class AdminPageAssert {
    private final AdminPage adminPage;
    public AdminPageAssert(AdminPage adminPage) { this.adminPage = adminPage; }

    // Проверка, что товар появился в таблице (по названию)
    public void checkProductAddedToTable(String productName) {
        $("tbody#tbody input[value='" + productName + "']").shouldBe(Condition.visible);
    }

    // Опционально: проверка toast (если хотите оставить)
    public void checkToastVisible() {
        adminPage.toastContainer.shouldHave(Condition.text("Товар"));
    }
}