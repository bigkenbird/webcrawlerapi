package com.ken.webcrawlerapi.service.users;

import com.ken.webcrawlerapi.controller.output.UsersPostsOutputDto;
import com.ken.webcrawlerapi.service.users.pojo.PostDto;
import com.ken.webcrawlerapi.service.users.pojo.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetIgPostsByUserNameService {

    private final IgLoginService igLoginService;

    private final IgProfileService igProfileService;

    private final IgPostsService igPostsService;

    private static Boolean isNotLogin = true;


    public UsersPostsOutputDto getPostsByUserName(String userName, Integer postsLimitNumber) {

        if (isNotLogin) {
            igLoginService.login();
            isNotLogin = false;
        }


        UsersPostsOutputDto usersPostsOutputDto = new UsersPostsOutputDto();

        ProfileDto profileDto = igProfileService.findByUserName(userName);
        usersPostsOutputDto.setProfileDto(profileDto);

        List<PostDto> postDtos = igPostsService.findPosts(postsLimitNumber);
        usersPostsOutputDto.setPostsDtos(postDtos);

        return usersPostsOutputDto;
    }


}
