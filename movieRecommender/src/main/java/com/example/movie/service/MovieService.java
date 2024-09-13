package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.repository.GenreRepository;
import com.example.movie.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private GenreRepository genreRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie createMovie(Movie movie) {
        Genre genre = genreRepository.findById(movie.getGenre().getId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        movie.setGenre(genre);
        return movieRepository.save(movie);
    }
    
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMoviesByGenre(String genreName) {
        return movieRepository.findByGenre_Name(genreName);
    }
}