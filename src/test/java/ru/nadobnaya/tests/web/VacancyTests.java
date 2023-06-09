package ru.nadobnaya.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of vacancy window content")
@Tags({@Tag("ui"), @Tag("vacancy")})
public class VacancyTests extends TestBase {

    @Test
    @DisplayName("Testing of opening vacancy window")
    @Story("Testing of opening vacancy window from side menu")
    @Severity(SeverityLevel.BLOCKER)
    void openVacancyWindowTest() {
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
    }

    @Test
    @DisplayName("Testing of opening CV window")
    @Story("Testing of opening CV window from vacancy page")
    @Severity(SeverityLevel.CRITICAL)
    void vacancyTest() {
        step("Open vacancy page", () -> {
            vacancyPage.openPage();
        });
        step("Open CV window", () -> {
            vacancyPage.openCVWindow();
        });
        step("Verify that CV window has opened", () -> {
            cvPage.verifyCVWindowOpened();
        });
    }

}
