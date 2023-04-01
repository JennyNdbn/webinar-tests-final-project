package ru.nadobnaya.tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import ru.nadobnaya.drivers.BrowserstackMobileDriver;
import ru.nadobnaya.drivers.LocalMobileDriver;
import ru.nadobnaya.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {
    public static String deviceHost = System.getProperty("env_mobile");
    TestData testData = new TestData();

    String iHaveAccountButton = "ru.webinar.mobile:id/tvChangeAction";
    String forgotPasswordButton = "ru.webinar.mobile:id/forgot_password_btn";
    String etEmail = "ru.webinar.mobile:id/etEmail";
    String etPassword = "ru.webinar.mobile:id/etPassword";
    String logInButton = "ru.webinar.mobile:id/btnAction";
    String startNewMeetingButton = "ru.webinar.mobile:id/startFastEvent";
    String eventList = "ru.webinar.mobile:id/tvEmptyEventList";
    String signInSSO = "ru.webinar.mobile:id/tvSSOAction";
    String title = "ru.webinar.mobile:id/title";


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
        if (deviceHost.equals("mobile_bs")) {
            Attach.addVideo(sessionId().toString());
        }
        closeWebDriver();
    }

}
