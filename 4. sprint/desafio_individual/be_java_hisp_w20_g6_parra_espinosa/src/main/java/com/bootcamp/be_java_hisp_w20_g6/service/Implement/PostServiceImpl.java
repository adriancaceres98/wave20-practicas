package com.bootcamp.be_java_hisp_w20_g6.service.Implement;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import com.bootcamp.be_java_hisp_w20_g6.dto.response.PostListResponseDTO;
import com.bootcamp.be_java_hisp_w20_g6.dto.response.PostResponseDTO;
import com.bootcamp.be_java_hisp_w20_g6.dto.response.PromoCountResponseDTO;
import com.bootcamp.be_java_hisp_w20_g6.dto.response.PromoListResponseDTO;
import com.bootcamp.be_java_hisp_w20_g6.exception.InvalidParamException;

import com.bootcamp.be_java_hisp_w20_g6.exception.UserNotFoundException;
import com.bootcamp.be_java_hisp_w20_g6.model.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.be_java_hisp_w20_g6.dto.request.PostRequestDto;

import com.bootcamp.be_java_hisp_w20_g6.model.PostModel;
import com.bootcamp.be_java_hisp_w20_g6.repository.PostRepository;
import com.bootcamp.be_java_hisp_w20_g6.service.Interface.IPostService;
import com.bootcamp.be_java_hisp_w20_g6.service.Interface.IUserService;

import org.modelmapper.config.Configuration;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IUserService userService;

    private ModelMapper mapper;

    public PostServiceImpl() {
        super();
        mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

    }

    @Override
    public boolean save(PostRequestDto postRequestDto) {
        userService.getUserById(postRequestDto.getUser_id());
        PostModel postModel = mapper.map(postRequestDto, PostModel.class);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        postModel.setDate(LocalDate.parse(postRequestDto.getDate(),formatter));
        postModel.setId(postRepository.idGenerator());
        postRepository.save(postModel);
        return true;
    }

    @Override
    public PostListResponseDTO postFollowedLastWeeks(int user_id, String orderBy) {
        LocalDate dateNow=LocalDate.now();
        List<PostResponseDTO> followedPost=new ArrayList<>();
        for(int id : userService.getUserById(user_id).getFollowed()){
            postRepository.getPostList().stream().filter(p->p.getUser_id()==id)
                    .filter(p-> DAYS.between(p.getDate(),dateNow)<=15)
                    .forEach(p->followedPost.add(
                            new PostResponseDTO(p.getUser_id(),p.getId(),p.getDate()
                                    ,p.getProduct(),p.getCategory(),p.getPrice())
                    ));
        }

        if(orderBy != null && orderBy.equals("date_asc")) {
            followedPost.sort(Comparator.comparing(PostResponseDTO::getDate));
        }else if(orderBy == null || orderBy.equals("date_desc")){
            followedPost.sort(Comparator.comparing(PostResponseDTO::getDate).reversed());
        }else{
            throw new InvalidParamException("Argumento invalido");
        }

        return new PostListResponseDTO(user_id,followedPost );
    }
    @Override
    public PromoCountResponseDTO promoCount(int userId){
        try {
            UserModel user = userService.getUserById(userId);
            String userName = user.getUser_name();
            List<PostModel> itemPromo = this.getPromoListByUser(userId);
            int totalPromo = itemPromo.size();
            return new PromoCountResponseDTO(userId, userName, totalPromo);
        } catch (UserNotFoundException e){
            throw new UserNotFoundException("Usuario no existe");
        }
    }
    private List<PostModel> getPromoListByUser(int userId){
        List<PostModel> listPromo = (List<PostModel>) postRepository.getPostList()
                .stream()
                .filter(post -> post.isHas_promo() && post.getUser_id() == userId)
                .collect(Collectors.toList());
        return listPromo;
    }
    @Override
    public PromoListResponseDTO promoListDiscount(int userId){
        try {
            UserModel user = userService.getUserById(userId);
            String userName = user.getUser_name();
            List<PostModel> itemPromo = this.getPromoListByUser(userId);
            return new PromoListResponseDTO(userId, userName, itemPromo);
        } catch (UserNotFoundException e){
            throw new UserNotFoundException("Usuario no existe");
        }
    }
}
