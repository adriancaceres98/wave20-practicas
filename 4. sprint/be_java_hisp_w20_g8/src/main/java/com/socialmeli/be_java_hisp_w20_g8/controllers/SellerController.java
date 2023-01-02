package com.socialmeli.be_java_hisp_w20_g8.controllers;


import com.socialmeli.be_java_hisp_w20_g8.dto.SellerDTO;
import com.socialmeli.be_java_hisp_w20_g8.dto.UserCountDTO;
import com.socialmeli.be_java_hisp_w20_g8.dto.UserDTO;
import com.socialmeli.be_java_hisp_w20_g8.exceptions.OperationFailedException;
import com.socialmeli.be_java_hisp_w20_g8.services.sellers.ISellerService;
import com.socialmeli.be_java_hisp_w20_g8.services.sellers.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    ISellerService sellerService;


    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<UserCountDTO> countFollowers(@PathVariable int userId){
        return ResponseEntity.ok().body(sellerService.followersCount(userId));
    }
}

