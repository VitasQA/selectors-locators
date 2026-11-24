package org.hw_09.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class AdminPage {
    public SelenideElement nameInput = $("#n-name");
    public SelenideElement priceInput = $("#n-price");
    public SelenideElement addButton = $("#add-btn");
    public SelenideElement toastContainer = $("#toast-container");
    public SelenideElement firstRowPriceInput = $("tbody#tbody tr:first-child td:nth-child(3) input");
    public SelenideElement firstRowSaveButton = $("tbody#tbody tr:first-child .btn-upd");

    public void enterProductName(String name) { nameInput.setValue(name); }
    public void enterProductPrice(String price) { priceInput.setValue(price); }
    public void clickAddButton() { addButton.click(); }
    public void editFirstProductPrice(String price) {
        firstRowPriceInput.setValue(price);
        firstRowSaveButton.click();
    }
}