package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Content;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PTTCrawlerContentRepository {

    private final WebDriver chromeDriver;

    private final PostRepository postRepository;

    public Content getContentByPost(Post post) {
        Content content = new Content();
        String contentUrl = post.getUrl();
        chromeDriver.get(contentUrl);

        content.setPostId(post.getId());

        String contentPageSource = CheckUtil.checkIsOverEighteen(chromeDriver, chromeDriver.getPageSource());

        assert contentPageSource != null;
        Document document = Jsoup.parse(contentPageSource);
        Elements metalineElements = document.getElementsByClass("article-metaline");
        Elements brandElements = document.getElementsByClass("article-metaline-right");

        setContentMetaData(content, brandElements, metalineElements);

        Element mainContentElement = document.getElementById("main-content");

        assert mainContentElement != null;
        setContentText(content, mainContentElement);

        return content;
    }

    private void setContentMetaData(Content content, Elements brandElements, Elements metalineElements) {
        Element authorElement = metalineElements.first();
        Element titleElement = metalineElements.get(1);
        Element createTimeElement = metalineElements.get(2);

        assert authorElement != null;
        String author = authorElement.getElementsByClass("article-meta-value").text();
        String title = titleElement.getElementsByClass("article-meta-value").text();
        String createTimeText = createTimeElement.getElementsByClass("article-meta-value").text();
        String brandName = Objects.requireNonNull(brandElements.first()).text();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy", Locale.ENGLISH);
        LocalDateTime createTime = LocalDateTime.parse(createTimeText, inputFormatter);

        content.setArticleAuthor(author);
        content.setTitle(title);
        content.setCreateTime(createTime);
        content.setBrandName(brandName);
    }

    private void setContentText(Content content, Element mainContentElement) {
        String contentText = mainContentElement.text();
        content.setContent(contentText);
    }

}
