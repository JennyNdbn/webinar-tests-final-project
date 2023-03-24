package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CVPage {

    public CVPage verifyCVWindowOpened() {
        $("header").shouldHave(text("Привет! Ты в двух шагах от работы мечты. Расскажи подробнее о себе"));
        return this;
    }
}
