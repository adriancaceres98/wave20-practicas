package com.bootcamp.be_java_hisp_w20_g7.service;

import com.bootcamp.be_java_hisp_w20_g7.dto.PostDto;
import com.bootcamp.be_java_hisp_w20_g7.dto.response.UserPostFollowedDto;
import com.bootcamp.be_java_hisp_w20_g7.exception.DataIsnotCorrectException;
import com.bootcamp.be_java_hisp_w20_g7.repository.FollowRepository;
import com.bootcamp.be_java_hisp_w20_g7.repository.PostRepository;
import com.bootcamp.be_java_hisp_w20_g7.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;
import java.time.LocalDate;

import static com.bootcamp.be_java_hisp_w20_g7.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test_T005_T006_OrderedByDate {

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FollowRepository followRepository;
    @InjectMocks
    private PostService postService;
    static Clock clock;
    public static Stream<Arguments> postWithAscAndDescOrder() {
        return Stream.of(
                arguments(postDtoAscOrder(), "date_asc"), arguments(postDtoDescOrder(), "date_desc"));
    }
    // Configurar el localDate en una fecha exacta
    @BeforeAll
    static void setupClock() {
        clock = Clock.fixed(
                Instant.parse("2023-01-02T10:05:23.653Z"),
                ZoneId.of("UTC"));

        Mockito.mockStatic(Clock.class)
                .when(Clock::systemUTC).thenReturn(clock);
    }

    //Se testea que al ingresar los pathVarible "date_asc" y "date_desc" sea exitosa y continué el flujo normal del código
    @ParameterizedTest
    @ValueSource(strings = {"date_desc", "date_asc"})
    void T0005AscDescTestPathVariable(String order) {

        when(userRepository.findById(3)).thenReturn(users().get(1));
        when(postRepository.findAll()).thenReturn(anyList());
        UserPostFollowedDto userPostFollowedDto = postService.postUsersFollowed(3, order);
        verify(userRepository, times(1)).findById(3);
        verify(postRepository, times(1)).findAll();
    }

    //Cuando el valor del pathVariable es diferente a cualquiera de los dos casos correctos,arroja un error
    @ParameterizedTest
    @ValueSource(strings = {"date", "Date_asc", "", " "})
    void T0005AscDescTestPathVariableException(String order) {
        when(userRepository.findById(3)).thenReturn(users().get(1));
        assertThrows(DataIsnotCorrectException.class, () -> {
            postService.postUsersFollowed(3, order);
        });
    }

    //Se testea el correcto orden ascendente y descendente
    @ParameterizedTest
    @MethodSource("postWithAscAndDescOrder")
    void T0006AscTestOrder(List<PostDto> postDtoList, String order) {

        when(userRepository.findById(1)).thenReturn(users().get(0));
        when(followRepository.findAll()).thenReturn(followListToTest());
        when(postRepository.findAll()).thenReturn(postListTestToTest());
        UserPostFollowedDto userPostFollowedDto = postService.postUsersFollowed(1, order);
        assertEquals(postDtoList, userPostFollowedDto.getPosts());
    }
}