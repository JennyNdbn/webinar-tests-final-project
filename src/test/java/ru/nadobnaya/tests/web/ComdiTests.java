package ru.nadobnaya.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of COMDI page content")
public class ComdiTests extends TestBase {

    @Test
    @DisplayName("Testing of opening COMDI product page")
    @Story("Testing of opening COMDI page from header by using hover action")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("UI"), @Tag("COMDI")})
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
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("UI"), @Tag("COMDI")})
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
    @Story("Testing of details button on COMDI product page")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("UI"), @Tag("COMDI")})
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
