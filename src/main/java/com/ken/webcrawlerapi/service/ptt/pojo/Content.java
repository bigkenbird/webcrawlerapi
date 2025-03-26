package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "content")
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "POST_ID", columnDefinition = "INT NOT NULL")
    private Integer postId;

    @Column(name = "TITLE", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String title;

    @Lob
    @Column(name = "CONTENT", columnDefinition = "TEXT DEFAULT NULL")
    private String content;

    @Column(name = "ARTICLE_AUTHOR", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String articleAuthor;

    @Column(name = "BRAND_NAME", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String brandName;

    @Column(name = "CREATE_TIME",columnDefinition = "DATETIME NULL")
    private LocalDateTime createTime;



}
