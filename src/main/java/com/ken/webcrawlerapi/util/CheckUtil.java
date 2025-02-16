package com.ken.webcrawlerapi.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.CollectionUtils;

@Slf4j
public class CheckUtil {

    public static String checkIsOverEighteen(WebDriver chromeDriver, String html) {
        try{
            Document document = Jsoup.parse(html);
            Elements overEighteenNotice = document.select("div.over18-notice");
            if(CollectionUtils.isEmpty(overEighteenNotice)){
                return html;
            }
            WebElement button = chromeDriver.findElement(By.name("yes"));
            button.click();
            Thread.sleep(3000);
            return chromeDriver.getPageSource();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }

    }
}
