package com.ken.webcrawlerapi.service.users;

import com.ken.webcrawlerapi.service.users.pojo.ProfileDto;
import com.ken.webcrawlerapi.util.UrlUtil;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class IgProfileService {

    private final WebDriver chromeDriver;

    public ProfileDto findByUserName(String userName) {
        String profileUrl = UrlUtil.getProfileUrlByUserName(userName);
        chromeDriver.get(profileUrl);
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("footer")));

        ProfileDto profileDto = new ProfileDto();
        WebElement profileTitleElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//meta[@property='og:title']"))).getFirst();
        profileDto.setName(profileTitleElement.getAttribute("content"));

        WebElement profileDescriptionElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//meta[@property='og:description']"))).getFirst();
        profileDto.setDescription(profileDescriptionElement.getAttribute("content"));

        WebElement profileImageElement = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//meta[@property='og:image']"))).getFirst();
        profileDto.setImage(profileImageElement.getAttribute("content"));

        return profileDto;
    }
}
