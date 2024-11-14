package com.ken.webcrawlerapi.service.users;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class IgLoginService {

    @Value("${ig.crawler.login}")
    private String igCrawlerLogin;

    @Value("${ig.crawler.use.account}")
    private String igCrawlerUseAccount;

    @Value("${ig.crawler.use.password}")
    private String igCrawlerUsePassword;

    private final WebDriver chromeDriver;

    public void login() {
        try {

            chromeDriver.get(igCrawlerLogin);

            // 等待帳號欄位出現並定位
            WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(300));
            WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
            WebElement passwordInput = chromeDriver.findElement(By.name("password"));

            // 輸入帳號和密碼
            usernameInput.sendKeys(igCrawlerUseAccount);
            passwordInput.sendKeys(igCrawlerUsePassword);

            // 等待登入按鈕可點擊
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='loginForm']/div/div[3]/button/div")));

            // 點擊登入按鈕
            loginButton.click();

            Thread.sleep(5000);

            // 點擊稍後再說
            WebElement noSaveLoginStatusButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='button']")));
            noSaveLoginStatusButton.click();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
