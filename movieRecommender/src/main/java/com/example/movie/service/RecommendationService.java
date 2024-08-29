package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Rating;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.RatingRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<Movie> getSimilarMovies(Long movieId) {
        // Basic similarity based on genre
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));
        Set<Genre> genres = movie.getGenres();
        return movieRepository.findByGenresIn(movie.getGenres());
    }

    public List<Movie> getMoviesLikedBySimilarUsers(Long movieId) {
        // Basic recommendation based on users who liked the same movie
        List<Long> userIds = ratingRepository.findByMovieId(movieId)
                .stream()
                .map(Rating -> Rating.getUser().getId())
                .collect(Collectors.toList());
        
        return movieRepository.findDistinctByRatings_UserIdIn(userIds);
    }
}
