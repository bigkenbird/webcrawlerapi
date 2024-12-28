package com.ken.webcrawlerapi.service.ptt.repository;


import com.ken.webcrawlerapi.service.ptt.pojo.Brand;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author ken.chen
 */
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Optional<Brand> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE brand " +
            "SET name = :name, " +
            "title = :title, " +
            "url = :url, " +
            "user_count = :userCount, " +
            "category = :category " +
            "WHERE id = :id", nativeQuery = true)
    void update(@Param("name") String name,
                @Param("title") String title,
                @Param("url") String url,
                @Param("userCount") int userCount,
                @Param("category") String category,
                @Param("id") Integer id);

}
