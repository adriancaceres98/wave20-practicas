package com.bootcamp.movies.service;

import com.bootcamp.movies.dto.response.ActorFavoriteMovieResponseDto;
import com.bootcamp.movies.model.Actor;
import com.bootcamp.movies.repository.ActorMovieRepository;
import com.bootcamp.movies.repository.ActorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService implements IActorService {

    private final ActorRepository actorRepo;
    private final ActorMovieRepository actorMovieRepository;
    private final ModelMapper modelMapper;


    public ActorService(ActorRepository actorRepo, ActorMovieRepository actorMovieRepository, ModelMapper modelMapper) {
        this.actorRepo = actorRepo;
        this.actorMovieRepository = actorMovieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActorFavoriteMovieResponseDto> findAllActorsWhichFavoriteMovieIsNotNull() {
        return actorRepo.findAllByFavoriteMovieIsNotNull()
                .stream()
                .map(a -> modelMapper.map(a, ActorFavoriteMovieResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Actor> findAllActorsWhichRatingExceeds(Double lowerBound) {
        return actorRepo.findAllByRatingGreaterThan(lowerBound);
    }

    @Override
    public List<Actor> findActorsThatWorkedInMovie(Integer movieId) {
        return actorMovieRepository.findActorsThatWorkedInMovie(movieId);
    }


}
