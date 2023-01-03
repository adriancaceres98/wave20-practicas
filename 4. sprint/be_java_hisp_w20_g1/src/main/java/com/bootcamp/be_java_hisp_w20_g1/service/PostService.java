package com.bootcamp.be_java_hisp_w20_g1.service;

import com.bootcamp.be_java_hisp_w20_g1.dto.response.PostListResponseDto;
import com.bootcamp.be_java_hisp_w20_g1.dto.response.PostResponseDto;
import com.bootcamp.be_java_hisp_w20_g1.dto.request.PostRequestDto;
import com.bootcamp.be_java_hisp_w20_g1.dto.request.ProductRequestDto;
import com.bootcamp.be_java_hisp_w20_g1.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w20_g1.model.Post;
import com.bootcamp.be_java_hisp_w20_g1.repository.interfaces.IPostRepository;
import com.bootcamp.be_java_hisp_w20_g1.service.interfaces.IPostService;
import com.bootcamp.be_java_hisp_w20_g1.service.interfaces.IProductService;
import com.bootcamp.be_java_hisp_w20_g1.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    private final int initialId = 1;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper mapper;

    public List<PostResponseDto> sortPostByDate(List<PostResponseDto> posts, String order) {
        if (order.equalsIgnoreCase("date_asc")) {
            posts.sort(Comparator.comparing(PostResponseDto::getDate));
        } else {
            posts.sort(Comparator.comparing(PostResponseDto::getDate).reversed());
        }
        return posts;
    }

    public PostListResponseDto lastTwoWeeksPostsFromFollowers(int id, String order) {
        userService.validateUserExistById(id);
        Set<Integer> followedByuserId = userService.getUserFollowed(id);
        List<PostResponseDto> posts = new ArrayList<>();

        for (Integer followed : followedByuserId) {
            posts.addAll(lastTwoWeeksPostsFromUserId(followed));
        }

        return new PostListResponseDto(id, sortPostByDate(posts, order));
    }

    @Override
    public List<PostResponseDto> lastTwoWeeksPostsFromUserId(int id) {
        return postRepository.getPostsByUserId(id).stream().filter(post -> LocalDate.now().minusDays(14).isBefore(post.getDate())).
                map(post -> PostResponseDto.builder().userId(id)
                        .postId(post.getId())
                        .date(post.getDate())
                        .product(productService.getProductById(post.getProductId()))
                        .category(post.getCategory())
                        .price(post.getPrice())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public boolean add(PostRequestDto postDto) {
        if (postDto == null || postDto.getUserId() == 0) {
            throw new BadRequestException("Request body inválido");
        } else {
            if (productService.alreadyExist(postDto.getProduct().getProductId())) {
                throw new BadRequestException("El producto ya existe");
            }
            if (!userService.alreadyExists(postDto.getUserId())) {

                throw new BadRequestException("Usuario invalido.");
            }
            ProductRequestDto productDto = postDto.getProduct();
            productService.add(productDto);
            postRepository.add(buildPost(postDto, productDto.getProductId()));
            //Se actualiza el usuario indicando que es seller en caso de que se trate de su primer posteo.
            userService.updateUser(postDto.getUserId());

            return true;
        }
    }

    public Post buildPost(PostRequestDto postDto, int productId) {
        Post post = mapper.map(postDto, Post.class);
        post.setProductId(productId);
        //Si se trata del primer posteo que realiza el usuario se le setea id 1, de lo contrario, se coloca como id el número
        //inmediatamente posterior al del último posteo realizado.
        if (postRepository.getPosts().isEmpty()) {
            post.setId(initialId);
        } else {
            Optional<Integer> lastId = postRepository.getPosts().stream().map(p -> p.getId()).max(Comparator.comparing(Integer::valueOf));
            if (lastId.isPresent()) {
                Integer aux = lastId.get();
                post.setId(++aux);
            }
        }
        return post;
    }
}
