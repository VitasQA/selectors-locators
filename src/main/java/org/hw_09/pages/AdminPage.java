package org.hw_09.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class AdminPage {
    public SelenideElement nameInput = $("#n-name");
    public SelenideElement priceInput = $("#n-price");
    public SelenideElement addButton = $("#add-btn");
    public SelenideElement toastContainer = $("#toast-container");
    public SelenideElement firstRowPriceInput = $("tbody#tbody tr:first-child td:nth-child(3) input");
    public SelenideElement firstRowSaveButton = $("tbody#tbody tr:first-child .btn-upd");

    @Step("Ввести название товара: {name}")
    public void enterProductName(String name) { nameInput.setValue(name); }

    @Step("Ввести цену товара: {price}")
    public void enterProductPrice(String price) { priceInput.setValue(price); }

    @Step("Нажать кнопку 'Добавить товар'")
    public void clickAddButton() { addButton.click(); }

    @Step("Отредактировать цену первого товара на {price}")
    public void editFirstProductPrice(String price) {
        firstRowPriceInput.setValue(price);
        firstRowSaveButton.click();
    }
}