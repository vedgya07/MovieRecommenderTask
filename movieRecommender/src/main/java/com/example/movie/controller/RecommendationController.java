package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.movie.entity.Movie;
import com.example.movie.service.MovieService;
import com.example.movie.service.RecommendationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Recommendations", description = "APIs for getting movie recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final MovieService movieService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService, MovieService movieService) {
        this.recommendationService = recommendationService;
        this.movieService = movieService;
    }

     
    @Operation(summary = "Get similar movies", description = "Endpoint to get similar movies.")
    @GetMapping("/similar/{movieId}")
    public ResponseEntity<List<Movie>> getSimilarMovies(@PathVariable Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        List<Movie> similarMovies = recommendationService.getSimilarMovies(movie);
        return ResponseEntity.ok(similarMovies);
    }

    
    @Operation(summary = "Get recommended movies", description = "Endpoint to get recommended movies.")
    @GetMapping("/recommended/{movieId}")
    public ResponseEntity<List<Movie>> getRecommendedMovies(@PathVariable Long movieId) {
        List<Movie> recommendedMovies = recommendationService.getRecommendedMovies(movieId);
        return ResponseEntity.ok(recommendedMovies);
    }
}