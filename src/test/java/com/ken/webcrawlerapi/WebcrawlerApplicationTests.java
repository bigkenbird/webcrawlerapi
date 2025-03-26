package com.ken.webcrawlerapi;

import com.ken.webcrawlerapi.service.ptt.BrandService;
import com.ken.webcrawlerapi.service.ptt.ContentService;
import com.ken.webcrawlerapi.service.ptt.PostService;
import com.ken.webcrawlerapi.service.ptt.pojo.Content;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.service.ptt.repository.PTTCrawlerContentRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WebcrawlerApplicationTests {

    @Autowired
    PTTCrawlerContentRepository pttCrawlerContentRepository;

    @Autowired
    PostService postService;

    @Autowired
    ContentService contentService;

    @Test
    void updateContent() throws InterruptedException {
       //postService.updatePost();


//        Post post = new Post();
//        post.setUrl("https://www.ptt.cc/bbs/Stock/M.1742867749.A.8AA.html");
//        Content content = pttCrawlerContentRepository.getContentByPost(post);
//        System.out.println(content);

        contentService.updateContents();
    }

}
