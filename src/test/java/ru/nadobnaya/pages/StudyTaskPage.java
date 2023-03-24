package ru.nadobnaya.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudyTaskPage {

    public StudyTaskPage openDateAndTimeChoiceWindow() {
        $("body").$(byText("Выбрать время")).click();
        switchTo().window(1);
        return this;
    }

}
