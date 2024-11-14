package com.ken.webcrawlerapi.controller;

import com.ken.webcrawlerapi.controller.output.UsersPostsOutputDto;
import com.ken.webcrawlerapi.service.users.GetIgPostsByUserNameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final GetIgPostsByUserNameService getIgPostsByUserNameService;

    @GetMapping("{username}/posts")
    public ResponseEntity<UsersPostsOutputDto> posts(
            @PathVariable("username") String userName,
            @RequestParam(value = "postsLimitNumber", defaultValue = "10") Integer postsLimitNumber) {
        return ResponseEntity.ok(getIgPostsByUserNameService.getPostsByUserName(userName, postsLimitNumber));
    }
}
