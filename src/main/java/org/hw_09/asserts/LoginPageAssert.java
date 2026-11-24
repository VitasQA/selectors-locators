package org.hw_09.asserts;

import com.codeborne.selenide.Condition;
import org.hw_09.pages.LoginPage;

public class LoginPageAssert {
    private final LoginPage loginPage;
    public LoginPageAssert(LoginPage loginPage) { this.loginPage = loginPage; }

    public void checkUsernameVisible() { loginPage.usernameInput.shouldBe(Condition.visible); }
    public void checkPasswordVisible() { loginPage.passwordInput.shouldBe(Condition.visible); }
    public void checkSubmitButtonVisible() { loginPage.submitButton.shouldBe(Condition.visible); }
}