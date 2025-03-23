package com.ken.webcrawlerapi.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author ken.chen
 */
@Configuration
public class WebConfig {

    @Value("${webdriver.path}")
    public String webDriverPath;

    @Bean
    public WebDriver chromeDriver() throws URISyntaxException, MalformedURLException {
        URI uri = new URI(webDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage","--disable-gpu");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return new RemoteWebDriver(uri.toURL(),options);
    }
}
