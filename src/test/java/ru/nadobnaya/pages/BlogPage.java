package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BlogPage {

    public BlogPage verifyBlogHeader() {
        $(".header-inner").shouldHave(text("Про онлайн-технологии для бизнеса, работы и образования от компании Webinar Group"));
        return this;
    }

    public BlogPage verifyBlogArticle() {
        $("#article").shouldHave(text("Популярное"));
        return this;
    }

    public BlogPage verifyBlogFooter() {
        $(".footer").shouldHave(text("pr@webinar.ru"));
        return this;
    }




}
