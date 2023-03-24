package ru.nadobnaya.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WebinarTests extends TestBase {



    @Test
    @DisplayName("Testing of google search")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of showing webinar.ru site in query of Google")
    @Severity(SeverityLevel.NORMAL)
    @Tag("remote")
    @Disabled("Google is protected by Capcha")
    void googleSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("вебинар").pressEnter();
        $("[#search]").shouldHave(text("Webinar Group — российская экосистема сервисов для встреч, " +
                "онлайн‑мероприятий, обучения и вебинаров."));
    }

    @Test
    @DisplayName("Testing of opening vacancy and CV windows")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of opening vacancy window from side menu and CV window from vacancy page")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void vacancyTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open side menu", () -> {
            mainPage.openSideMenu();
        });
        step("Open vacancy window", () -> {
            mainPage.openWindowFromSidebar(testData.vanancy);
        });
        step("Verify that vacancy window has opened", () -> {
            vacancyPage.verifyVacancyWindowOpened();
        });
        step("Open CV window", () -> {
            vacancyPage.openCVWindow();
        });
        step("Verify that CV window has opened", () -> {
            cvPage.verifyCVWindowOpened();
        });
    }

    @Test
    @DisplayName("Testing of blog opening")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of opening blog window from header and verifying blog header, article and footer")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void blogTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open blog page from header", () -> {
            mainPage.openBlog();
        });
        step("Verify blog header", () -> {
            blogPage.verifyBlogHeader();
        });
        step("Verify blog article", () -> {
            blogPage.verifyBlogArticle();
        });
        step("Verify blog footer", () -> {
            blogPage.verifyBlogFooter();
        });
    }

    @Test
    @DisplayName("Testing of COMDI product page")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of opening COMDI page from header by using hover action. Testing request popup and details button on COMDI page")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void productComdiTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open COMDI page from products popup", () -> {
            mainPage.headerHover(testData.products)
                .openCOMDIProduct();
        });
        step("Open request popup on COMDI page", () -> {
            productComdiPage.openRequestPopup();
        });
        step("Verify that request popup has opened on COMDI page", () -> {
            productComdiPage.verifyRequestPopupOpen();
        });
        step("Close request popup on COMDI page", () -> {
            productComdiPage.closeRequestPopup();
        });
        step("Click details button on COMDI page", () -> {
            productComdiPage.openDetailsWindow();
        });
        step("Verify that COMDI site has opened", () -> {
            comdiPage.verifyDetailsWindowOpen();
        });
    }

    @Test
    @DisplayName("Testing of education of university students tasks page")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of opening education of university students page from header by using hover action. Testing of opening enroll page from education page")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void tasksEducationTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open education of university students page from tasks popup", () -> {
            mainPage.headerHover(testData.tasks)
                    .openStudentStudyTask();
        });
        step("Open enroll page", () -> {
            studyTaskPage.openDateAndTimeChoiceWindow();
        });
        step("Verify that enroll page has opened", () -> {
            studentEnrollPage.verifyDateAndTimeChoiceWindowOpen();
        });
    }

    @Test
    @DisplayName("Testing of rates page")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of rates changing for period of 1, 6, 12 months of educational subscription on rates page")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("remote")
    void priceChangingTest(){
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open rates page", () -> {
            mainPage.openRates();
            ratesPage.verifyRatesOpen();
        });
        step("Select rates for education tab", () -> {
            ratesPage.openRatesForEducation();
        });
        step("Select 1 month period of subscription", () -> {
            ratesPage.openRatesTooltip()
                    .selectPeriod(testData.oneMonthPeriod);
        });
        step("Verify the rate of 1 month period of subscription", () -> {
            ratesPage.verifyRateChange(testData.oneMonthRate);
        });
        step("Select 6 month period of subscription", () -> {
            ratesPage.openRatesTooltip()
                    .selectPeriod(testData.sixMonthsPeriod);
        });
        step("Verify the rate of 6 month period of subscription", () -> {
            ratesPage.verifyRateChange(testData.sixMonthRate);
        });
        step("Select 12 month period of subscription", () -> {
            ratesPage.openRatesTooltip()
                    .selectPeriod(testData.twelveMonthsPeriod);
        });
        step("Verify the rate of 12 month period of subscription", () -> {
            ratesPage.verifyRateChange(testData.twelveMonthRate);
        });
    }

}
