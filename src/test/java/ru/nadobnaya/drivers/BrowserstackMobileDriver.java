package ru.nadobnaya.drivers;

import com.codeborne.selenide.WebDriverProvider;
import ru.nadobnaya.config.MobileDriverConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static MobileDriverConfig config = ConfigFactory.create(MobileDriverConfig.class);

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.login());
        mutableCapabilities.setCapability("browserstack.key", config.password());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", config.appUrl());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", config.device());
        mutableCapabilities.setCapability("os_version", config.osVersion());

        // Set other BrowserStack mutableCapabilities
        mutableCapabilities.setCapability("project", config.projectName());
        mutableCapabilities.setCapability("build", config.buildName());
        mutableCapabilities.setCapability("name", config.testName());


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired mutableCapabilities defined above
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.baseUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
