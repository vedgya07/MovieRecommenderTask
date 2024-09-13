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

  
    public List<Movie> getSimilarMovies(Movie movie) {
        return movieRepository.findByGenre(movie.getGenre())
                .stream()
                .filter(m -> !m.equals(movie)) // Excluding the original movie
                .collect(Collectors.toList());
    }

    
    
 // Get recommended movies based on other users' ratings
    public List<Movie> getRecommendedMovies(Long movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);

        // Get users who rated the movie
        List<Long> userIds = ratings.stream()
                .map(Rating -> Rating.getUser().getId())
                .distinct()
                .collect(Collectors.toList());

        // Find movies rated by these users
        return ratingRepository.findByUserIdIn(userIds)
                .stream()
                .map(Rating::getMovie)
                .distinct()
                .filter(movie -> !movie.getId().equals(movieId)) // Exclude the original movie
                .collect(Collectors.toList());
    }
}
