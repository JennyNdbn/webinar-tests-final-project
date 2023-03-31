package ru.nadobnaya.drivers;

import com.codeborne.selenide.WebDriverProvider;
import ru.nadobnaya.config.EmulatorConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

public class LocalMobileDriver implements WebDriverProvider {

    public static URL getAppiumServerUrl() {
        EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class);
        try {
            return new URL(emulatorConfig.appiumServer());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class);

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);


        options.setAutomationName(ANDROID_UIAUTOMATOR2)
                .setPlatformName(ANDROID)
                .setDeviceName(emulatorConfig.deviceName())
                .setPlatformVersion(emulatorConfig.platformVersion())
                .setApp(getAppPath())
                .setAppPackage(emulatorConfig.appPackage())
                .setAppActivity(emulatorConfig.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private String getAppPath() {
        EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class);
        File app = new File(emulatorConfig.appPath());
        return app.getAbsolutePath();
    }
}
