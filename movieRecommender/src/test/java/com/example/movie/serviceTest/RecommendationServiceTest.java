package com.example.movie.serviceTest;

import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.entity.Rating;
import com.example.movie.entity.User;
import com.example.movie.repository.MovieRepository;
import com.example.movie.repository.RatingRepository;
import com.example.movie.service.RecommendationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecommendationServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSimilarMovies() {
        Genre actionGenre = new Genre(1L, "Action");
        Movie movie = new Movie(1L, "Movie A", "Description A", "image_url", "Director A", Set.of(actionGenre));
        Movie similarMovie = new Movie(2L, "Movie B", "Description B", "image_url", "Director B", Set.of(actionGenre));

        when(movieRepository.findByGenresIn(Set.of(actionGenre))).thenReturn(Arrays.asList(movie, similarMovie));

        List<Movie> result = recommendationService.getSimilarMovies(movie);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(similarMovie.getId(), result.get(0).getId());
    }

    @Test
    public void testGetSimilarMovies_NoSimilarMovies() {
        Genre actionGenre = new Genre(1L, "Action");
        Movie movie = new Movie(1L, "Movie A", "Description A", "image_url", "Director A", Set.of(actionGenre));

        when(movieRepository.findByGenresIn(Set.of(actionGenre))).thenReturn(List.of(movie));

        List<Movie> result = recommendationService.getSimilarMovies(movie);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRecommendedMovies() {
        Movie movie = new Movie(1L, "Movie A", "Description A", "image_url", "Director A", new HashSet<>());
        Movie recommendedMovie = new Movie(2L, "Movie B", "Description B", "image_url", "Director B", new HashSet<>());
        Rating rating1 = new Rating(1L, movie, new User(1L, "user1", "password", "ROLE_USER"), 5, 4);
        Rating rating2 = new Rating(2L, recommendedMovie, new User(1L, "user1", "password", "ROLE_USER"), 4, 3);

        when(ratingRepository.findByMovieId(1L)).thenReturn(Arrays.asList(rating1));
        when(ratingRepository.findByUserIdIn(Arrays.asList(1L))).thenReturn(Arrays.asList(rating2));

        List<Movie> result = recommendationService.getRecommendedMovies(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(recommendedMovie.getId(), result.get(0).getId());
    }

    @Test
    public void testGetRecommendedMovies_NoRatings() {
        when(ratingRepository.findByMovieId(1L)).thenReturn(List.of());
        when(ratingRepository.findByUserIdIn(List.of())).thenReturn(List.of());

        List<Movie> result = recommendationService.getRecommendedMovies(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRecommendedMovies_NoMoviesForUsers() {
        Movie movie = new Movie(1L, "Movie A", "Description A", "image_url", "Director A", new HashSet<>());
        Rating rating1 = new Rating(1L, movie, new User(1L, "user1", "password", "ROLE_USER"), 5, 4);

        when(ratingRepository.findByMovieId(1L)).thenReturn(Arrays.asList(rating1));
        when(ratingRepository.findByUserIdIn(Arrays.asList(1L))).thenReturn(List.of());

        List<Movie> result = recommendationService.getRecommendedMovies(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRecommendedMovies_EmptyResultSet() {
        when(ratingRepository.findByMovieId(1L)).thenReturn(Arrays.asList());
        when(ratingRepository.findByUserIdIn(anyList())).thenReturn(Arrays.asList());

        List<Movie> result = recommendationService.getRecommendedMovies(1L);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
