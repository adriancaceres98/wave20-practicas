package com.socialMeli.be_java_hisp_w20_g03.service;

import com.socialMeli.be_java_hisp_w20_g03.dto.request.PostDTO;
import com.socialMeli.be_java_hisp_w20_g03.exception.BadRequestException;
import com.socialMeli.be_java_hisp_w20_g03.exception.NotFoundException;
import com.socialMeli.be_java_hisp_w20_g03.model.Post;
import com.socialMeli.be_java_hisp_w20_g03.model.User;
import com.socialMeli.be_java_hisp_w20_g03.repository.IPostRepository;
import com.socialMeli.be_java_hisp_w20_g03.repository.IUserRepository;
import com.socialMeli.be_java_hisp_w20_g03.utils.PostUtils;
import com.socialMeli.be_java_hisp_w20_g03.utils.UserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    IPostRepository postRepository;

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    PostService postService;


    @Test
    @DisplayName("T-0005: Parametro de ordenamiento existe")
    void testDateOrderOk() {
        //Arrange
        int userId1 = 1;
        int userId2 = 234;
        User user1 = UserUtils.buildUserWithOneFollower(userId1, userId2);
        User user2 = user1.getFollowers().get(0);
        when(userRepository.getUserById(userId1)).thenReturn(user2);

        List<Post> posts = PostUtils.getLatestPosts();
        when(postRepository.getPostsByUserId(userId1)).thenReturn(posts);

        //Act
        List<PostDTO> ascPost = postService.getPost(userId1, "date_asc");
        List<PostDTO> descPost = postService.getPost(userId1, "date_desc");
        //Assert
        Assertions.assertFalse(ascPost.isEmpty());
        Assertions.assertFalse(descPost.isEmpty());
        verify(userRepository, atLeast(1)).getUserById(userId1);
        verify(postRepository, atLeast(1)).getPostsByUserId(userId1);
    }

    @Test
    @DisplayName("T-0005: Parametro de ordenamiento no existe")
    void testDateOrderBad() {
        //Arrange
        int userId1 = 1;
        int userId2 = 234;
        User user1 = UserUtils.buildUserWithOneFollower(userId1, userId2);
        User user2 = user1.getFollowers().get(0);
        when(userRepository.getUserById(userId1)).thenReturn(user2);

        List<Post> posts = PostUtils.getLatestPosts();
        when(postRepository.getPostsByUserId(userId1)).thenReturn(posts);

        //Act & assert
        Assertions.assertThrows(BadRequestException.class, ()->postService.getPost(userId1, "date_bsc"));
        verify(userRepository, atLeast(1)).getUserById(userId1);
        verify(postRepository, atLeast(1)).getPostsByUserId(userId1);
    }

    @Test
    @DisplayName("T-0006: Ordenamiento descendente correcto de publicaciones")
    void getPostDescendingOk() {
        //Arrange
        int userId1 = 6631;
        int userId2 = 234;
        List<Post> expectedPosts = PostUtils.getLatestPosts();
        List<PostDTO> expectedPostsDTO = PostUtils.postDTOConverter(expectedPosts);
        User user1 = UserUtils.buildUserWithOneFollower(userId1, userId2);
        User user2 = user1.getFollowers().get(0);
        when(userRepository.getUserById(userId1)).thenReturn(user2);
        when(postRepository.getPostsByUserId(userId1)).thenReturn(expectedPosts);

        //Act
        List<PostDTO> actualPosts = postService.getPost(userId1, "date_desc");

        //Assert
        verify(postRepository, atLeast(1)).getPostsByUserId(userId1);
        verify(userRepository, atLeast(1)).getUserById(userId1);
        Assertions.assertEquals(expectedPostsDTO, actualPosts);
    }

    @Test
    @DisplayName("T-0006: Ordenamiento ascendente correcto de publicaciones")
    void getPostAscendingOk() {
        //Arrange
        int userId1 = 6631;
        int userId2 = 234;
        List<Post> expectedPosts = PostUtils.getAscendingDatePosts();
        List<PostDTO> expectedPostsDTO = PostUtils.postDTOConverter(expectedPosts);
        User user1 = UserUtils.buildUserWithOneFollower(userId1, userId2);
        User user2 = user1.getFollowers().get(0);
        when(userRepository.getUserById(userId1)).thenReturn(user2);
        when(postRepository.getPostsByUserId(userId1)).thenReturn(expectedPosts);

        //Act
        List<PostDTO> actualPosts = postService.getPost(userId1, "date_asc");

        //Assert
        verify(postRepository, atLeast(1)).getPostsByUserId(userId1);
        verify(userRepository, atLeast(1)).getUserById(userId1);
        Assertions.assertEquals(expectedPostsDTO, actualPosts);
    }
    @Test
    @DisplayName("T-0008: Camino Feliz")
    void getPostOk() {
        //Arrange
        int userId1 = 6631;
        int userId2 = 234;
        List<Post> expectedPosts = PostUtils.getLatestPosts();
        List<PostDTO> expectedPostsDTO = PostUtils.postDTOConverter(expectedPosts);
        User user1 = UserUtils.buildUserWithOneFollower(userId1, userId2);
        User user2 = user1.getFollowers().get(0);
        when(userRepository.getUserById(userId1)).thenReturn(user2);
        when(postRepository.getPostsByUserId(userId1)).thenReturn(expectedPosts);

        //Act
        List<PostDTO> actualPosts = postService.getPost(userId1, null);

        //Assert
        verify(postRepository, atLeast(1)).getPostsByUserId(userId1);
        verify(userRepository, atLeast(1)).getUserById(userId1);
        Assertions.assertEquals(expectedPostsDTO, actualPosts);
    }

    @Test
    @DisplayName("T-0008: Verificando la excepcion cuando el usuario no existe.")
    void getPostThrowsNotFoundException() {
        //Arrange
        int userId1 = 1558;

        //Act y Assert
        Assertions.assertThrows(NotFoundException.class, () ->postService.getPost(userId1, null));
    }

    @Test
    @DisplayName("T-0009: Agregando una nueva publicación.")
    void addPostOk() {
        //Arrange
        int userId1 = 234;
        int userId2 = 6631;
        User user = UserUtils.buildUserWithOneFollower(userId1, userId2);
        Post post = PostUtils.buildPost(userId1);
        PostDTO postDTO = PostUtils.postDTOConverter(List.of(post)).get(0);
        String expected = "Publicación agregada";
        when(userRepository.getUserById(userId1)).thenReturn(user);

        //Act
        String actual = postService.addPost(postDTO);

        //Assert
        verify(postRepository, atLeastOnce()).addPost(post);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("T-0009: Agregando una nueva publicación con un usuario no existente.")
    void addPostNotFoundException() {
        //Arrange
        int userId1 = 234;
        Post post = PostUtils.buildPost(userId1);
        PostDTO postDTO = PostUtils.postDTOConverter(List.of(post)).get(0);
        when(userRepository.getUserById(userId1)).thenReturn(null);

        //Act y Assert
        Assertions.assertThrows(NotFoundException.class, () -> postService.addPost(postDTO));
    }


}