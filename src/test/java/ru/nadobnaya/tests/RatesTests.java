package ru.nadobnaya.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RatesTests extends TestBase {

    @Test
    @DisplayName("Testing of rates page")
    @Owner("Evgeniia Nadobnaia")
    @Feature("Testing of webinar.ru")
    @Story("Testing of rates changing for period of 1, 6, 12 months of educational subscription on rates page")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("remote")
    void priceChangingTest() {
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
