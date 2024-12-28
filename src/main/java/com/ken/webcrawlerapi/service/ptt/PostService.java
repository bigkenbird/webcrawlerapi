package com.ken.webcrawlerapi.service.ptt;

import com.ken.webcrawlerapi.service.ptt.pojo.Brand;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.service.ptt.repository.BrandRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PTTCrawlerPostsRepository;
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

    private final PTTCrawlerPostsRepository pttCrawlerPostsRepository;

    public void updatePost(){
        List<Brand> brands = brandRepository.findAll().subList(0,2);
        for(Brand brand:brands){
            String brandName = brand.getName();
            String brandUrl = brand.getUrl();

            List<Post> posts = pttCrawlerPostsRepository.getPosts(brandUrl,brandName);

            posts.forEach(System.out::println);

        }
    }



}
