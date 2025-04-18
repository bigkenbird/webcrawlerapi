package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.exception.pojo.WebCrawlerException;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ken.chen
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class PTTCrawlerPostsRepository {

    private final WebDriver chromeDriver;

    public List<Post> getPosts(String brandUrl, String brandName) throws InterruptedException {
        chromeDriver.get(brandUrl);
        String brandPageSource = checkIsOverEighteen(chromeDriver.getPageSource());

        if (!StringUtils.hasText(brandPageSource)) {
            throw new WebCrawlerException(String.format("crawl url:%s, but no html can find", brandUrl));
        }
        Document document = parse(brandPageSource);
        Elements elements = document.select("div.r-ent");
        return elements.stream().map(element -> mapToPost(element, brandName))
                .filter(post-> StringUtils.hasText(post.getUrl()))
                .toList();
    }

    public String checkIsOverEighteen(String html) throws InterruptedException {
        Document document = parse(html);
        Elements overEighteenNotice = document.select("div.over18-notice");
        if (CollectionUtils.isEmpty(overEighteenNotice)) {
            return html;
        }
        WebElement button = chromeDriver.findElement(By.name("yes"));
        button.click();
        Thread.sleep(3000);
        return chromeDriver.getPageSource();

    }

    private Post mapToPost(Element element, String brandName) {

        Element commentCountElement = Objects.requireNonNull(element.getElementsByClass("nrec").getFirst()).tagName("span");
        String commentCountText = commentCountElement.text();
        Map<String,Integer> countMap = buildCountMapping();
        Integer commentCount = StringUtils.hasText(commentCountText) ?
                "爆".equals(commentCountText) ?
                        Integer.MAX_VALUE :
                        countMap.containsKey(commentCountText) ? countMap.get(commentCountText) :
                                Integer.parseInt(commentCountElement.text())
                : 0;

        Element titleElement = Objects.requireNonNull(element.getElementsByClass("title").getFirst()).tagName("a");

        String title = titleElement.text();
        String uri = titleElement.select("a").attr("href");
        String url = mapToPostUrl(uri);
        String author = "";
        Elements metaElements = element.getElementsByClass("meta");
        if (!CollectionUtils.isEmpty(metaElements)) {
            author = Objects.requireNonNull(metaElements.getFirst().selectFirst(".author")).text();
        }
        Post post = new Post();
        post.setUrl(url);
        post.setCommentCount(commentCount);
        post.setArticleAuthor(author);
        post.setTitle(title);
        post.setBrand(brandName);
        post.setContentIsUpdate(0);
        return post;
    }

    private String mapToPostUrl(String uri) {
        return StringUtils.hasText(uri) ? String.format("https://www.ptt.cc%s", uri) : Strings.EMPTY;
    }

    private Document parse(String html) {
        return Jsoup.parse(html);
    }

    private Map<String,Integer> buildCountMapping(){
        Map<String,Integer> map = new HashMap<>();
        map.put("X1",-10);
        map.put("X2",-20);
        map.put("X3",-30);
        map.put("X4",-40);
        map.put("X5",-50);
        map.put("X6",-60);
        map.put("X7",-70);
        map.put("X8",-80);
        map.put("X9",-90);
        map.put("XX",-100);
        return map;
    }

}
