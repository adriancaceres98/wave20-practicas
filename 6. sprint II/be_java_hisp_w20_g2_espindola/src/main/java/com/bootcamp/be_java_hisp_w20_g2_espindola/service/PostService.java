package com.bootcamp.be_java_hisp_w20_g2_espindola.service;

import com.bootcamp.be_java_hisp_w20_g2_espindola.dto.PostDTO;
import com.bootcamp.be_java_hisp_w20_g2_espindola.dto.response.PostResponseDTO;
import com.bootcamp.be_java_hisp_w20_g2_espindola.exception.BadRequestException;
import com.bootcamp.be_java_hisp_w20_g2_espindola.model.Post;
import com.bootcamp.be_java_hisp_w20_g2_espindola.model.User;
import com.bootcamp.be_java_hisp_w20_g2_espindola.repository.interfaces.IUserRepository;
import com.bootcamp.be_java_hisp_w20_g2_espindola.service.interfaces.IPostService;
import com.bootcamp.be_java_hisp_w20_g2_espindola.utils.mapper.PostMapper;
import com.bootcamp.be_java_hisp_w20_g2_espindola.utils.sort.PostStreamSorter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class PostService implements IPostService {
    private IUserRepository userRepository;
    private PostMapper postMapper;

    public PostService(IUserRepository userRepository, PostMapper postMapper) {
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    /**
     * Creates a post and saves it in a user posts list.
     *
     * @param postRequestDTO has the data for the post to be created.
     */
    @Override
    public PostDTO createPost(PostDTO postRequestDTO) {
        User user = getUserOrThrow(postRequestDTO.getUserId());

        Post newPost = postMapper.toPost(postRequestDTO);
        user.addPost(newPost);

        userRepository.save(user);
        return postRequestDTO;
    }

    /**
     * It returns a list of all the posts made by users that a given user (userId) follows in the last two weeks.
     *
     * @param userId the id of the user to check.
     * @param order  receives a String ("date_asc" or "date_desc") to order the list of posts.
     * @return PostResponseDTO.
     */
    @Override
    public PostResponseDTO sendLastPostOfFollowed(int userId, Optional<String> order) {
        User user = getUserOrThrow(userId);

        PostResponseDTO postResponse = new PostResponseDTO(userId);

        user.getFollowing()
                .forEach(followedUser -> {
                    followedUser.getPosts().stream()
                            .sorted(PostStreamSorter.getSortCriteria(order.orElse("date_desc")))
                            .filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(2)))
                            .forEach(post -> postResponse.addPost(postMapper.toWithIdDTO(post, followedUser.getId())));
                });

        return postResponse;
    }

    private User getUserOrThrow(int userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            throw new BadRequestException("The given userId not exist.");
        }

        return user;
    }

}