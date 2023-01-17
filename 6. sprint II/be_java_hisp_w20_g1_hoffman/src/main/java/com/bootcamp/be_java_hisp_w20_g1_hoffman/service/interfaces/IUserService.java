package com.bootcamp.be_java_hisp_w20_g1_hoffman.service.interfaces;

import com.bootcamp.be_java_hisp_w20_g1_hoffman.dto.response.UserFollowedResponseDto;
import com.bootcamp.be_java_hisp_w20_g1_hoffman.dto.response.UserFollowersCountResponseDto;
import com.bootcamp.be_java_hisp_w20_g1_hoffman.dto.response.UserFollowersResponseDto;
import com.bootcamp.be_java_hisp_w20_g1_hoffman.model.User;

import java.util.Set;

public interface IUserService {

    UserFollowersResponseDto getSellerFollowersDto(int id, String order);

    UserFollowersCountResponseDto getFollowersCountDto(int id);

    UserFollowedResponseDto getFollowedDto(int id, String order);

    UserFollowedResponseDto followUser(int userId, int userIdToFollow);

    UserFollowedResponseDto unfollowUser(int userId, int userIdToUnfollow);

    boolean validateUserExistById(int id);

    Set<Integer> getUserFollowed(int id);

    boolean alreadyExists(int userId);

    User updateUser(int userId, int postId);
}
