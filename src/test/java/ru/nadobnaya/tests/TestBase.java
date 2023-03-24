package ru.nadobnaya.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.nadobnaya.helpers.Attach;
import ru.nadobnaya.pages.*;

import java.util.Map;

public class TestBase {
    TestData testData = new TestData();
    MainPage mainPage = new MainPage();
    VacancyPage vacancyPage = new VacancyPage();
    CVPage cvPage = new CVPage();
    BlogPage blogPage = new BlogPage();
    ProductComdiPage productComdiPage = new ProductComdiPage();
    ComdiPage comdiPage = new ComdiPage();
    StudyTaskPage studyTaskPage = new StudyTaskPage();
    StudentEnrollPage studentEnrollPage = new StudentEnrollPage();
    RatesPage ratesPage = new RatesPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 100000;
        Configuration.timeout = 15000;

//            String remote = System.getProperty("remote", "");
//            Configuration.baseUrl = System.getProperty("baseUrl", "https://webinar.ru/");
//            Configuration.browser = System.getProperty("browser", "chrome");
//            Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
//            Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
//
//            Configuration.remote = System.getProperty("remote", "");
        WebDriverProvider provider = new WebDriverProvider() {
        };

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();

    }
}
