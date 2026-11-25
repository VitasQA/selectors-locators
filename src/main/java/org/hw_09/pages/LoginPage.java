package org.hw_09.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement usernameInput = $("#username");
    public SelenideElement passwordInput = $("#password");
    public SelenideElement submitButton = $("button[type='submit']");

    @Step("Ввести имя пользователя: {username}")
    public void enterUsername(String username) { usernameInput.setValue(username); }

    @Step("Ввести пароль")
    public void enterPassword(String password) { passwordInput.setValue(password); }

    @Step("Нажать кнопку 'Войти'")
    public void clickSubmit() { submitButton.click(); }

    @Step("Авторизоваться в системе")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }
}