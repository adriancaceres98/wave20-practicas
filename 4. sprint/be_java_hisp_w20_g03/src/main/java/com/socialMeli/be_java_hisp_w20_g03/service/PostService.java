package com.socialMeli.be_java_hisp_w20_g03.service;

import com.socialMeli.be_java_hisp_w20_g03.dto.PostDTO;
import com.socialMeli.be_java_hisp_w20_g03.dto.PromoPostDTO;
import com.socialMeli.be_java_hisp_w20_g03.dto.UserPromoPostCountDTO;
import com.socialMeli.be_java_hisp_w20_g03.exception.NotFoundException;
import com.socialMeli.be_java_hisp_w20_g03.model.Post;
import com.socialMeli.be_java_hisp_w20_g03.model.Product;
import com.socialMeli.be_java_hisp_w20_g03.repository.IPostRepository;
import com.socialMeli.be_java_hisp_w20_g03.repository.IUserRepository;
import com.socialMeli.be_java_hisp_w20_g03.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PostService implements IPostService {

    @Autowired
    IPostRepository postRepository;
    @Autowired
    IUserRepository userRepository;
    ModelMapper mapper = new ModelMapper();

    @Override
    public String addPost(PostDTO postDTO) {

        if (userRepository.getUserById(postDTO.getUserId()) == null) {
            throw new NotFoundException("El usuario ingresado no existe");
        }
        Product product = Product.builder()
                .productId(postDTO.getProduct().getProductId())
                .productName(postDTO.getProduct().getProductName())
                .type(postDTO.getProduct().getType())
                .brand(postDTO.getProduct().getBrand())
                .color(postDTO.getProduct().getColor())
                .notes(postDTO.getProduct().getNotes())
                .build();
        Post post = Post.builder()
                .postId(postRepository.getPosts().size() + 1)
                .userId(postDTO.getUserId())
                .category(postDTO.getCategory())
                .price(postDTO.getPrice())
                .product(product)
                .date(postDTO.getDate())
                .hasPromo(false)
                .discount(0)
                .build();
        postRepository.addPost(post);
        return "Publicacion agregada";
    }

    @Override
    public List<PostDTO> getPost(int userId, String order) {
        LocalDate dateNow = LocalDate.now();
        List<PostDTO> postList = new ArrayList<>();
        User userEx = userRepository.getUserById(userId);

        if (userEx == null) {
            throw new NotFoundException("El usuario ingresado no existe.");
        }
        for (User user : userRepository.getUserById(userId).getFollowed()) {
            postList.addAll(postRepository.getPosts().stream().filter(post -> post.getUserId() == user.getUserId())
                    .filter(post -> DAYS.between(post.getDate(), dateNow) <= 15)
                    .map(u -> mapper.map(u, PostDTO.class)).collect(Collectors.toList()));
        }
        if (order != null && order.equals("date_desc")) {
            postList = postList.stream().sorted(Comparator.comparing(x -> x.getDate(), Comparator.reverseOrder())).collect(Collectors.toList());
        } else {
            postList = postList.stream().sorted(Comparator.comparing(x -> x.getDate())).collect(Collectors.toList());
        }
        return postList;
    }

    @Override
    public String addPromoPost(PromoPostDTO promoPostDTO) {
        if (userRepository.getUserById(promoPostDTO.getUserId()) == null) {
            throw new NotFoundException("El usuario ingresado no existe");
        }

        Product product = Product.builder()
                .productId(promoPostDTO.getProduct().getProductId())
                .productName(promoPostDTO.getProduct().getProductName())
                .type(promoPostDTO.getProduct().getType())
                .brand(promoPostDTO.getProduct().getBrand())
                .color(promoPostDTO.getProduct().getColor())
                .notes(promoPostDTO.getProduct().getNotes())
                .build();
        Post post = Post.builder()
                .postId(postRepository.getPosts().size() + 1)
                .userId(promoPostDTO.getUserId())
                .category(promoPostDTO.getCategory())
                .price(promoPostDTO.getPrice())
                .product(product)
                .date(promoPostDTO.getDate())
                .hasPromo(promoPostDTO.isHasPromo())
                .discount(promoPostDTO.getDiscount())
                .build();
        postRepository.addPost(post);


        return "Publicacion agregada";
    }

    @Override
    public UserPromoPostCountDTO getUserPromoPostCount(int userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new NotFoundException("El usuario ingresado no existe");
        }

        List<Post> userPromoPosts = postRepository.getPosts().stream()
                .filter(p -> p.getUserId() == userId && p.isHasPromo())
                .collect(Collectors.toList());

        return new UserPromoPostCountDTO(userId, user.getUserName(),userPromoPosts.size());
    }

/*    //DEV
    public List<PromoPostDTO> getAllPosts() {
        return postRepository.getPosts().stream().map(u -> mapper.map(u, PromoPostDTO.class)).collect(Collectors.toList());
    }

    //FIN*/
}
