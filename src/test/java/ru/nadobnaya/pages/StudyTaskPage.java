package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudyTaskPage {

    public StudyTaskPage verifyStudyTaskPageOpened() {
        $("body").shouldHave(text("Обучайте студентов и школьников онлайн"));
        return this;
    }

    public StudyTaskPage openPage() {
        open("/task/obuchenie_studentov/");
        return this;
    }

    public StudyTaskPage openDateAndTimeChoiceWindow() {
        $("body").$(byText("Выбрать время")).click();
        switchTo().window(1);
        return this;
    }

}
