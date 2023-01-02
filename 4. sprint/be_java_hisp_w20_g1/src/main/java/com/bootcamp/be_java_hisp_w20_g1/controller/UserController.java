package com.bootcamp.be_java_hisp_w20_g1.controller;


import com.bootcamp.be_java_hisp_w20_g1.dto.response.UserFollowersCountResponseDto;
import com.bootcamp.be_java_hisp_w20_g1.dto.response.UserFollowersResponseDto;
import com.bootcamp.be_java_hisp_w20_g1.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<UserFollowersResponseDto> getSellerFollowers(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getSellerFollowers(userId));
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<UserFollowersCountResponseDto> getFollowersCount(@PathVariable int userId) {
        return ResponseEntity.ok().body(userService.getFollowersCount(userId));
    }
}
