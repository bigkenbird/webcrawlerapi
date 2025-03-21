package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author ken.chen
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE post " +
            "SET title = :title, " +
            "comment_count = :comment_count, " +
            "article_author = :article_author, " +
            "url = :url " +
            "WHERE id = :id", nativeQuery = true)
    void update(@Param("title") String title,
                @Param("comment_count") Integer commentCount,
                @Param("article_author") String articleAuthor,
                @Param("url") String url,
                @Param("id") Integer id);
}
