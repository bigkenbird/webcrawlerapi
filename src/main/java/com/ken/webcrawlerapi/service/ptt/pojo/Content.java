package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "content")
@Data
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "POST_ID")
    private Integer postId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ARTICLE_AUTHOR")
    private String articleAuthor;

}
