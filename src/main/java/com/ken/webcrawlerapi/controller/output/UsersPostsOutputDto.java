package com.ken.webcrawlerapi.controller.output;

import com.ken.webcrawlerapi.service.users.pojo.PostDto;
import com.ken.webcrawlerapi.service.users.pojo.ProfileDto;
import lombok.Data;

import java.util.List;

@Data
public class UsersPostsOutputDto {

    private ProfileDto profileDto;
    private List<PostDto> postsDtos;
}
