package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author ken.chen
 */
@Entity
@Table(name = "brand_log")
@Data
public class BrandLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "URL" ,columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String url;

    @Column(name = "TITLE", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String title;

    @Column(name = "NAME", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String name;

    @Column(name = "USER_COUNT", columnDefinition = "INT DEFAULT 0")
    private Integer userCount;

    @Column(name = "CATEGORY", columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL")
    private String category;

}
