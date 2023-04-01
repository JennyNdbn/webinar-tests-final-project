package ru.nadobnaya.config;

import org.aeonbits.owner.Config;

@EmulatorConfig.LoadPolicy(EmulatorConfig.LoadType.MERGE)
@Config.Sources({
        "classpath:${env_mobile}.properties"
})

public interface EmulatorConfig extends Config {

    @Key("appiumServer")
    String appiumServer();

    @Key("deviceName")
    String deviceName();

    @Key("platformVersion")
    String platformVersion();

    @Key("appPath")
    String appPath();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();
}
