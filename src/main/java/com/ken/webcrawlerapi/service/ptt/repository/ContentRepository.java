package com.ken.webcrawlerapi.service.ptt.repository;

import com.ken.webcrawlerapi.service.ptt.pojo.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContentRepository extends JpaRepository<Content, Integer> {

}
