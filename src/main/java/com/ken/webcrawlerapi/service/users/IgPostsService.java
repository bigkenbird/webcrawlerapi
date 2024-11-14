package com.ken.webcrawlerapi.service.users;

import com.ken.webcrawlerapi.service.users.pojo.PostDto;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IgPostsService {

    private final WebDriver chromeDriver;

    public List<PostDto> findPosts(Integer postsLimitNumber) {
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        PostDto latestPost = latestPost(new WebDriverWait(chromeDriver, Duration.ofSeconds(10)));

        if (latestPost == null) {
            return null;
        }

        List<PostDto> postDtos = new ArrayList<>();
        postDtos.add(latestPost);

        WebElement nextPostsButton;
        JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;

        try {
            nextPostsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div._aaqg")))
                    .findElement(By.cssSelector("button._abl-"));

        } catch (Exception e) {
            e.printStackTrace();
            nextPostsButton = null;
        }

        executor.executeScript("arguments[0].click();", nextPostsButton);


        while (postDtos.size() < postsLimitNumber) {
            PostDto postDto = getPost(wait);
            if (postDto == null) {
                break;
            }
            postDtos.add(postDto);
            executor.executeScript("arguments[0].click();", nextPostsButton);
            try {
                nextPostsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div._aaqg")))
                        .findElement(By.cssSelector("button._abl-"));

            } catch (Exception e) {
                e.printStackTrace();
                nextPostsButton = null;
            }

            if (nextPostsButton == null) {
                break;
            }
        }
        return postDtos;
    }


    private PostDto latestPost(WebDriverWait wait) {
        WebElement firstPostElement, articleElement;

        try {
            firstPostElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("_aagw")));
            firstPostElement.click();

            articleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("article")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        PostDto postDto = new PostDto();
        String userName = getUserName(articleElement);

        postDto.setUserName(userName);
        postDto.setContent(getContent(articleElement));
        postDto.setUserImage(getUserImageUrl(articleElement, userName));
        postDto.setImageUrls(getImageUrls(articleElement));
        return postDto;
    }

    private PostDto getPost(WebDriverWait wait) {
        WebElement articleElement;

        try {
            articleElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("article")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        PostDto postDto = new PostDto();
        String userName = getUserName(articleElement);

        postDto.setUserName(userName);
        postDto.setContent(getContent(articleElement));
        postDto.setUserImage(getUserImageUrl(articleElement, userName));
        postDto.setImageUrls(getImageUrls(articleElement));
        return postDto;
    }

    private String getUserName(WebElement articleElement) {
        WebElement userName = articleElement.findElements(By.cssSelector("span.xt0psk2")).getFirst();
        return userName.getText();
    }

    private String getUserImageUrl(WebElement articleElement, String userName) {
        String cssSelector = String.format("img[alt=\"%s的大頭貼照\"]", userName);
        WebElement userImage = articleElement.findElement(By.cssSelector(cssSelector));
        return userImage.getAttribute("src");
    }

    private String getContent(WebElement articleElement) {
        List<WebElement> contents = articleElement.findElements(By.tagName("h1"));
        return contents.stream()
                .map(WebElement::getText)
                .collect(Collectors.joining(""));
    }

    private Set<String> getImageUrls(WebElement articleElement) {
        WebElement nextImagesButton;
        Set<String> imagesUrls = new LinkedHashSet<>();

        JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;


        try {
            nextImagesButton = articleElement.findElement(By.xpath("//button[@aria-label='下一步']"));
        } catch (Exception e) {
            nextImagesButton = null;
        }

        while (true) {
            List<WebElement> imagesUrlsElements = articleElement.findElements(By.cssSelector("div._aagv"));
            if (imagesUrlsElements.isEmpty()) {
                break;
            }

            for (WebElement imagesUrlsElement : imagesUrlsElements) {
                String imagesUrl = imagesUrlsElement.findElement(By.tagName("img")).getAttribute("src");
                imagesUrls.add(imagesUrl);
            }
            if (nextImagesButton == null) {
                break;
            }
            executor.executeScript("arguments[0].click();", nextImagesButton);
            try {
                nextImagesButton = articleElement.findElement(By.xpath("//button[@aria-label='下一步']"));
            } catch (Exception e) {
                nextImagesButton = null;
            }

            if (nextImagesButton == null) {
                break;
            }

        }
        return imagesUrls;
    }
}
