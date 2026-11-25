package org.hw_09.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.hw_09.utils.ConfigLogger;
import org.hw_09.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class SelenideBaseTest {
    @BeforeEach
    public void setUp() {
        ConfigLogger.printConfig();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = ConfigReader.getStandUrl();
        Configuration.timeout = ConfigReader.getTimeout();
        open("/");
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}