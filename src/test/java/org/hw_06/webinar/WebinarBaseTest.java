package org.hw_06.webinar;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import org.hw_06.utils.ConfigLogger;
import org.hw_06.utils.ConfigReader;
import org.hw_06.webinar.api.ProductApiClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class WebinarBaseTest {
    protected String createdProductId;

    @BeforeEach
    public void setUp() {

        ConfigLogger.printConfig();

        RestAssured.baseURI = ConfigReader.getApiUrl();
        Configuration.browser = "chrome";
        Configuration.baseUrl = ConfigReader.getStandUrl();
        Configuration.timeout = ConfigReader.getTimeout();
        open("/");
    }

    @AfterEach
    public void tearDown() {
        if (createdProductId != null && !createdProductId.isEmpty()) {
            try {
                String sessionId = ProductApiClient.getSessionId();
                given()
                        .cookie("JSESSIONID", sessionId)
                        .header("Cookie", "JSESSIONID=" + sessionId)
                        .when()
                        .delete("/goods/" + createdProductId)
                        .then()
                        .statusCode(200);
            } catch (Exception e) {
                System.out.println("Не удалось удалить товар " + createdProductId + ": " + e.getMessage());
            }
        }
        Selenide.closeWebDriver();
    }
}