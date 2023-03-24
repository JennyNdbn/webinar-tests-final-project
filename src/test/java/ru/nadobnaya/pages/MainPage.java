package ru.nadobnaya.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            headerBottom = $(".header-bottom"),
            sidebar = $(".sidebar__inner"),
            headerTopLinkBlog = $(".header-top__links-link", 0),
            headerPopupProducts = $(".header-popup.header-popup--products"),
            headerPopupTasks = $(".header-popup.header-popup--tasks"),
            menuList = $(".menu__list");


    public MainPage openPage() {
        open(baseUrl);
        return this;
    }

    public MainPage openSideMenu() {
        headerBottom.$(".menu__dropdown").click();
        return this;
    }

    public MainPage openWindowFromSidebar(String value) {
        sidebar.$(byText(value)).click();
        switchTo().window(1);
        return this;
    }

    public MainPage openBlog() {
        headerTopLinkBlog.click();
        switchTo().window(1);
        return this;
    }

    public MainPage headerHover(String value) {
        headerBottom.$(byText(value)).hover();
        return this;
    }

    public MainPage openCOMDIProduct() {
        headerPopupProducts.$(byText("COMDI")).click();
        return this;
    }

    public MainPage openStudentStudyTask() {
        headerPopupTasks.$(byText("Обучение студентов вузов")).click();
        return this;
    }

    public MainPage openRates() {
        menuList.$(byText("Тарифы")).click();
        return this;
    }

}
