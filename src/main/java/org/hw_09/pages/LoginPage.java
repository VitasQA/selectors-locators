package org.hw_09.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameInput = $("#username");
    public SelenideElement passwordInput = $("#password");
    public SelenideElement submitButton = $("button[type='submit']");

    public void enterUsername(String username) { usernameInput.setValue(username); }
    public void enterPassword(String password) { passwordInput.setValue(password); }
    public void clickSubmit() { submitButton.click(); }
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }
}