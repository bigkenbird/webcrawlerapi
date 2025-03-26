package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author ken.chen
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM post WHERE CONTENT_IS_UPDATE = 0 LIMIT :limit", nativeQuery = true)
    List<Post> findContentNoUpdate(@Param("limit") Integer limit);

    @Transactional
    @Modifying
    @Query(value = "UPDATE post " +
            "SET CONTENT_IS_UPDATE = :content_is_update " +
            "WHERE id = :id", nativeQuery = true)
    void updateContentIsUpdateById(@Param("id") Integer id,
                @Param("content_is_update") Integer content_is_update);

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
