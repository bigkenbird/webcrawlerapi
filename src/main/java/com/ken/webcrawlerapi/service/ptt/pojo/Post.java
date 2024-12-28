package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author ken.chen
 */
@Entity
@Table(name="post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="BRAND")
    private String brand;

    @Column(name="TITLE")
    private String title;

    @Column(name="COMMENT_COUNT")
    private Integer commentCount;

    @Column(name="ARTICLE_AUTHOR")
    private String articleAuthor;

    @Column(name="URL")
    private String url;

}