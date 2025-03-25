package com.ken.webcrawlerapi.service.ptt;

import com.ken.webcrawlerapi.service.ptt.pojo.Content;
import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import com.ken.webcrawlerapi.service.ptt.repository.ContentRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PTTCrawlerContentRepository;
import com.ken.webcrawlerapi.service.ptt.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContentService {

    private final PostRepository postRepository;

    private final ContentRepository contentRepository;

    private final PTTCrawlerContentRepository pttCrawlerContentRepository;

    public void updateContents(){
        List<Post> contentNotUpdated = postRepository.findContentNoUpdate();

        for(Post post : contentNotUpdated){
            Content content = pttCrawlerContentRepository.getContentByPost(post);
            contentRepository.save(content);
            postRepository.updateContentIsUpdateById(post.getId(), 1);
        }
    }
}
