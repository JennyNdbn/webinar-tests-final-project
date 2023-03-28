package ru.nadobnaya.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
public class ComdiTests extends TestBase {

    @Test
    @DisplayName("Testing of opening COMDI product page")
    @Feature("Testing of webinar.ru")
    @Story("Testing of opening COMDI page from header by using hover action")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("remote")
    void openProductComdiPageTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open COMDI page from products popup", () -> {
            mainPage.headerHover(testData.products)
                    .openCOMDIProduct();
        });
        step("Verify that COMDI page has opened", () -> {
            productComdiPage.verifyProductComdiPageOpen();
        });

    }

    @Test
    @DisplayName("Testing request popup on COMDI page")
    @Story("Testing of request popup on COMDI product page")
    @Feature("Testing of webinar.ru")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void popupComdiTest() {
        step("Open COMDI product page", () -> {
            productComdiPage.openPage();
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
        step("Verify that request popup has closed", () -> {
            productComdiPage.verifyProductComdiPageOpen();
        });
    }

    @Test
    @DisplayName("Testing details button on COMDI page")
    @Feature("Testing of webinar.ru")
    @Story("Testing of details button on COMDI product page")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("remote")
    void detailsButtonComdiTest() {
        step("Open COMDI product page", () -> {
            productComdiPage.openPage();
        });
        step("Click details button on COMDI page", () -> {
            productComdiPage.openDetailsWindow();
        });
        step("Verify that COMDI site has opened", () -> {
            comdiPage.verifyDetailsWindowOpen();
        });
    }


}
