package ru.nadobnaya.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.nadobnaya.drivers.BrowserstackMobileDriver;
import ru.nadobnaya.drivers.LocalMobileDriver;
import ru.nadobnaya.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class TestBase {
    public static String deviceHost = System.getProperty("envMobile");
    TestData testData = new TestData();

    final SelenideElement
            iHaveAccountButton = $(id("ru.webinar.mobile:id/tvChangeAction")),
            forgotPasswordButton = $(id("ru.webinar.mobile:id/forgot_password_btn")),
            etEmail = $(id("ru.webinar.mobile:id/etEmail")),
            etPassword = $(id("ru.webinar.mobile:id/etPassword")),
            logInButton = $(id("ru.webinar.mobile:id/btnAction")),
            startNewMeetingButton = $(id("ru.webinar.mobile:id/startFastEvent")),
            eventList = $(id("ru.webinar.mobile:id/tvEmptyEventList")),
            signInSSO = $(id("ru.webinar.mobile:id/tvSSOAction")),
            title = $(id("ru.webinar.mobile:id/title"));


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = null;

        if (deviceHost.equals("mobile_bs")) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        } else {
            Configuration.browser = LocalMobileDriver.class.getName();
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void afterEach() {
        Attach.pageSource();
        if ("mobile_bs".equals(deviceHost)) {
            Attach.addVideo(sessionId().toString());
        }
        closeWebDriver();
    }

}
