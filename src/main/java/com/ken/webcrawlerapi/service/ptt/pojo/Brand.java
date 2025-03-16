package com.ken.webcrawlerapi.service.ptt.pojo;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author ken.chen
 */
@Entity
@Table(name = "brand")
@Data
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "URL")
    private String url;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "USER_COUNT")
    private Integer userCount;

    @Column(name = "CATEGORY")
    private String category;

}
