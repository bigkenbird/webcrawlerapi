package com.ken.webcrawlerapi.service.users.pojo;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class PostDto {
    private UUID id = UUID.randomUUID();
    private String userName;
    private String userImage;
    private String content;
    private Set<String> imageUrls;
    private List<LinkDto> link;
}
