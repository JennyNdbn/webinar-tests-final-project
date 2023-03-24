package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class RatesPage {

    public RatesPage verifyRatesOpen() {
        $(".block-title.tariffs__title").shouldHave(text("Тарифы"));
        return this;
    }

    public RatesPage openRatesForEducation() {
        sleep(5000);
        $(".tab-common__list").$(byText("Для обучения")).click();
        $("#tab-webinar").shouldHave(text("Pro 150"));
        return this;
    }

    public RatesPage openRatesTooltip() {
        $("#tab-webinar.tab-common.active").$(".title__info-payment", 10).$(withText("месяц")).click();
        return this;
    }

    public RatesPage selectPeriod(int value) {
        $(".tooltip-body__row-month", value).click();
        return this;
    }

    public RatesPage verifyRateChange(String value) {
        $(".title__info-price-number", 10).shouldHave(text(value));
        return this;
    }

















}
