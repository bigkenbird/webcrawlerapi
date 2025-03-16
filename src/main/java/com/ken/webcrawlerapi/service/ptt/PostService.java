package com.ken.webcrawlerapi.service.ptt;

import com.ken.webcrawlerapi.service.ptt.pojo.Brand;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.service.ptt.repository.BrandRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PTTCrawlerPostsRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ken.chen
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final BrandRepository brandRepository;

    private final PostRepository postRepository;

    private final PTTCrawlerPostsRepository pttCrawlerPostsRepository;

    public void updatePost() throws InterruptedException {
        log.info("Updating post start");
        List<Brand> brands = brandRepository.findAll();
        for (Brand brand : brands) {
            String brandName = brand.getName();
            String brandUrl = brand.getUrl();
            List<Post> posts = pttCrawlerPostsRepository.getPosts(brandUrl, brandName);
            postRepository.saveAll(posts);
        }
        log.info("Updating post end, update count:{}", postRepository.count());
    }


}
