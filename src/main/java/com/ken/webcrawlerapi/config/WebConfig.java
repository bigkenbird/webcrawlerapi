package com.ken.webcrawlerapi.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class WebConfig {

    @Value("${chrome.driver.path}")
    private String chromeDriverPath;

    @Bean
    @Scope("singleton")
    @Lazy
    public WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return new ChromeDriver(options);
    }
}