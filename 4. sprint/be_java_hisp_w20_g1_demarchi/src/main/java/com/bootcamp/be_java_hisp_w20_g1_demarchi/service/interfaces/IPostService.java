package com.bootcamp.be_java_hisp_w20_g1_demarchi.service.interfaces;

import com.bootcamp.be_java_hisp_w20_g1_demarchi.dto.request.PostPromoRequestDto;
import com.bootcamp.be_java_hisp_w20_g1_demarchi.dto.request.PostRequestDto;
import com.bootcamp.be_java_hisp_w20_g1_demarchi.dto.response.PostListResponseDto;

public interface IPostService {
    PostListResponseDto lastTwoWeeksPostsFromFollowers(int id, String order);
    boolean add(PostRequestDto postDto);
    boolean add(PostPromoRequestDto postDto);
}
