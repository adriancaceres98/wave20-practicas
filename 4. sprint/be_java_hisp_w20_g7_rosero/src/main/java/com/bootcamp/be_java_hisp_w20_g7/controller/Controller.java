package com.bootcamp.be_java_hisp_w20_g7.controller;

import com.bootcamp.be_java_hisp_w20_g7.dto.request.PostCreateDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.request.ProductPromoDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.response.*;
import com.bootcamp.be_java_hisp_w20_g7.service.IFollowService;
import com.bootcamp.be_java_hisp_w20_g7.service.IPostService;
import com.bootcamp.be_java_hisp_w20_g7.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    IUserService iUserService;

    @Autowired
    IPostService postService;

    @Autowired
    IFollowService followService;

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<UserFollowersDto> findAllFollowers(
            @PathVariable int userId, @RequestParam(required = false) String order
    ) {
        if (order == null) {
            UserFollowersDto userFollowersDto = iUserService.userFollowers(userId, "");
            return new ResponseEntity<>(userFollowersDto, HttpStatus.OK);
        } else {
            UserFollowersDto userFollowersDto = iUserService.userFollowers(userId, order);
            return new ResponseEntity<>(userFollowersDto, HttpStatus.OK);
        }
    }

    @PostMapping("/products/post")
    ResponseEntity<String> postAdd(@RequestBody PostCreateDto post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
    }

    @PostMapping("users/{userId}/follow/{userIdToFollow}")
    ResponseEntity<String> follow(@PathVariable int userId, @PathVariable int userIdToFollow) {
        return ResponseEntity.ok(followService.follow(userId, userIdToFollow));
    }


    @PostMapping("users/{userId}/unfollow/{userIdToUnfollow}")
    ResponseEntity<String> unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        return ResponseEntity.ok(followService.unfollow(userId, userIdToUnfollow));
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<UserFollowedDto> findUserFollowedList(@PathVariable int userId, @RequestParam(required = false) String order) {
        UserFollowedDto userFollowedDto;
        if (order == null) {
            userFollowedDto = iUserService.userFollowed(userId, "");
            return new ResponseEntity<>(userFollowedDto, HttpStatus.OK);
        } else {
            userFollowedDto = iUserService.userFollowed(userId, order);
            return new ResponseEntity<>(userFollowedDto, HttpStatus.OK);
        }
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<UserFollowersCountDto> countFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(iUserService.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<UserPostFollowedDto> postUsersFollowed(
            @PathVariable int userId,
            @RequestParam(required = false) String order) {

        return new ResponseEntity<>(postService.postUsersFollowed(userId, order), HttpStatus.OK);

    }

    @GetMapping("/products/promo-post/count")
    public ResponseEntity<UserProductsPromoCountDto> countProductsPromo(@RequestParam int userId) {
        return new ResponseEntity<>(postService.countProductsPromo(userId), HttpStatus.OK);
    }


    @PostMapping("/products/promo-post")
    ResponseEntity<String> createProductPromo(@RequestBody ProductPromoDto productPromoDto) {
        return new ResponseEntity<>(postService.createPruductPromo(productPromoDto), HttpStatus.OK);
    }

    //@GetMapping("/products/followed/{userId}/list")

    //ser menos desscriptivos en los errores
    //un solo idioma pls

}
