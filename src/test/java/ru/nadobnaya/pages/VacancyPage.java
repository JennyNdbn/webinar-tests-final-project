package ru.nadobnaya.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VacancyPage {

    private final SelenideElement
            coverContent =  $("[data-hook-content=covercontent]");

    public VacancyPage verifyVacancyWindowOpened() {
        coverContent.shouldHave(text("Webinar Group - не просто успешная IT-компания."));
        return this;
    }

    public VacancyPage openCVWindow() {
        coverContent.$(byText("Отправить резюме")).click();
        switchTo().window(2);
        return this;
    }



}
