package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.exception.pojo.WebCrawlerException;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ken.chen
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class PTTCrawlerPostsRepository {

    private final WebDriver chromeDriver;

    private final BrandRepository brandRepository;

    public List<Post> getPosts(String brandUrl, String brandName) throws InterruptedException {
        chromeDriver.get(brandUrl);
        String brandPageSource = checkIsOverEighteen(chromeDriver.getPageSource());

        if(!StringUtils.hasText(brandPageSource)){
            throw new WebCrawlerException(String.format("crawl url:%s, but no html can find",brandUrl));
        }
        Document document = Jsoup.parse(brandPageSource);
        Elements elements = document.select("div.r-ent");
        return elements.stream().map(element -> mapToPost(element,brandName)).toList();
    }

    public String checkIsOverEighteen(String html) throws InterruptedException {
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

    private Post mapToPost(Element element, String brandName){

        Element commentCountElement = Objects.requireNonNull(element.getElementsByClass("nrec").getFirst()).tagName("span");
        Integer commentCount =  StringUtils.hasText(commentCountElement.text()) ? Integer.parseInt(commentCountElement.text()) : 0;

        Element titleElement = Objects.requireNonNull(element.getElementsByClass("title").getFirst()).tagName("a");

        String title = titleElement.text();
        String uri = titleElement.attr("href");
        String url = mapToPostUrl(uri);

        Element articleAuthorElement = Objects.requireNonNull(element.getElementsByClass("meta").getFirst()).getElementsByClass("div.author").getFirst();
        String author = articleAuthorElement.text();

        Post post = new Post();
        post.setUrl(url);
        post.setCommentCount(commentCount);
        post.setArticleAuthor(author);
        post.setTitle(title);
        post.setBrand(brandName);
        return post;
    }

    private String mapToPostUrl(String uri){
        return String.format("https://www.ptt.cc/%s",uri);
    }

}
