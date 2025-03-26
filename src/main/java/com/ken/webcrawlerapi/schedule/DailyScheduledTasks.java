package com.ken.webcrawlerapi.schedule;

import com.ken.webcrawlerapi.service.ptt.BrandService;
import com.ken.webcrawlerapi.service.ptt.ContentService;
import com.ken.webcrawlerapi.service.ptt.PostService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class DailyScheduledTasks {

    private final BrandService brandService;

    private final PostService postService;

    private final ContentService contentService;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    public void init() {
        if(brandService.brandsNotExist()){
            updateBrand();
        }

    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateBrand() {
        log.info("start updateBrand - Current Time: {}", LocalDateTime.now().format(formatter));
        brandService.updateBrand();
        log.info("end updateBrand - Current Time: {}", LocalDateTime.now().format(formatter));
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void updatePosts() throws InterruptedException {
        log.info("start updatePosts - Current Time: {}", LocalDateTime.now().format(formatter));
        postService.updatePost();
        log.info("end updatePosts - Current Time: {}", LocalDateTime.now().format(formatter));
    }

    @Scheduled(cron = "0 * * * * ?")
    public void updateContents() {
        log.info("start updateContents - Current Time: {}", LocalDateTime.now().format(formatter));
        contentService.updateContents();
        log.info("end updateContents - Current Time: {}", LocalDateTime.now().format(formatter));
    }

}
