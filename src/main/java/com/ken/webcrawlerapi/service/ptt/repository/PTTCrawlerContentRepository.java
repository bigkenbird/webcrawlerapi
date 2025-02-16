package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Content;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.util.CheckUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PTTCrawlerContentRepository {

    private final WebDriver chromeDriver;

    private final PostRepository postRepository;

    public Content getContentByUrl(Post post) {
        Content content = new Content();
        String contentUrl = post.getUrl();
        chromeDriver.get(contentUrl);

        content.setPostId(post.getId());

        String contentPageSource = CheckUtil.checkIsOverEighteen(chromeDriver,chromeDriver.getPageSource());


        return null;
    }

}
