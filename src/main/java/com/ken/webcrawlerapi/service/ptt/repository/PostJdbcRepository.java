package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void batchUpsertPosts(List<Post> posts) {
        String sql = "INSERT INTO post (TITLE, COMMENT_COUNT, ARTICLE_AUTHOR, URL, BRAND, CONTENT_IS_UPDATE) " +
                "VALUES (?,?,?,?,?,?) " +
                "ON DUPLICATE KEY UPDATE COMMENT_COUNT = VALUES(COMMENT_COUNT)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Post post = posts.get(i);
                ps.setString(1, post.getTitle());
                ps.setInt(2, post.getCommentCount());
                ps.setString(3, post.getArticleAuthor());
                ps.setString(4, post.getUrl());
                ps.setString(5, post.getBrand());
                ps.setInt(6, post.getContentIsUpdate());
            }

            @Override
            public int getBatchSize() {
                return posts.size();
            }
        });
    }
}
