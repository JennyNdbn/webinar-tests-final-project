package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class StudentEnrollPage {

    public StudentEnrollPage verifyDateAndTimeChoiceWindowOpen() {
        $(".c-event-info-aside__title").shouldHave(text("Индивидуальное демо по Webinar Group"));
        return this;
    }

}
