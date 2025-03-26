package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ken.chen
 */
@Entity
@Table(name = "post",uniqueConstraints = @UniqueConstraint( name = "PostUniqueBrandAndTitle", columnNames = {"BRAND","TITLE"}))
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BRAND", columnDefinition = "varchar(200) DEFAULT NULL")
    @EqualsAndHashCode.Include
    private String brand;

    @Column(name = "TITLE", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    @EqualsAndHashCode.Include
    private String title;

    @Column(name = "COMMENT_COUNT", columnDefinition = "INT DEFAULT 0")
    private Integer commentCount;

    @Column(name = "ARTICLE_AUTHOR", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String articleAuthor;

    @Column(name = "URL", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String url;

    @Column(name = "CONTENT_IS_UPDATE", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer contentIsUpdate;

}
