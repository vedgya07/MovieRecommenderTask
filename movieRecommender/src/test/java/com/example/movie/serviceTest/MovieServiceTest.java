package com.example.movie.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.movie.entity.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie(1L, "Movie Title", "Description", "image_url", "Director", null);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovieById(1L);
        assertNotNull(result);
        assertEquals("Movie Title", result.getTitle());
    }

    @Test
    public void testGetAllMovies() {
        Movie movie1 = new Movie(1L, "Movie Title 1", "Description 1", "image_url", "Director", null);
        Movie movie2 = new Movie(2L, "Movie Title 2", "Description 2", "image_url", "Director", null);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> result = movieService.getAllMovies();
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveMovie() {
        Movie movie = new Movie(1L, "Movie Title", "Description", "image_url", "Director", null);
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie result = movieService.saveMovie(movie);
        assertNotNull(result);
        assertEquals("Movie Title", result.getTitle());
    }
}

