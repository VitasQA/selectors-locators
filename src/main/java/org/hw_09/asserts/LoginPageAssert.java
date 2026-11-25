package org.hw_09.asserts;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.hw_09.pages.LoginPage;

public class LoginPageAssert {
    private final LoginPage loginPage;
    public LoginPageAssert(LoginPage loginPage) { this.loginPage = loginPage; }

    @Step("Проверить, что поле логина видимо")
    public void checkUsernameVisible() { loginPage.usernameInput.shouldBe(Condition.visible); }

    @Step("Проверить, что поле пароля видимо")
    public void checkPasswordVisible() { loginPage.passwordInput.shouldBe(Condition.visible); }

    @Step("Проверить, что кнопка 'Войти' видима")
    public void checkSubmitButtonVisible() { loginPage.submitButton.shouldBe(Condition.visible); }
}