package com.ken.webcrawlerapi;

import com.ken.webcrawlerapi.service.ptt.BrandService;
import com.ken.webcrawlerapi.service.ptt.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WebcrawlerApplicationTests {

    @Autowired
    private BrandService brandService;

    @Autowired
    private PostService postService;

    @Test
    void contextLoads() {
        brandService.updateBrand();
    }

}
