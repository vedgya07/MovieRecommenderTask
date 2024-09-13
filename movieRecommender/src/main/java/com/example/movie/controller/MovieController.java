package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;
import com.example.movie.exception.InvalidCredentialsException;
import com.example.movie.exception.InvalidInputException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.repository.GenreRepository;
import com.example.movie.repository.MovieRepository;
import com.example.movie.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movie", description = "APIs for managing movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    @Operation(summary = "Get all movies", description = "Retrieve a list of all movies.")
    @GetMapping
    public List<Movie> getAllMovies() throws InvalidCredentialsException {
        try {
        	return movieService.getAllMovies();
        }catch (Exception e) {
        	throw e;
        }
    }

    @Operation(summary = "Get movie by ID", description = "Retrieve a movie by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id)throws InvalidCredentialsException, ResourceNotFoundException {
       try {
    	   return ResponseEntity.ok(movieService.getMovieById(id));
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Create a new movie", description = "Add a new movie to the catalog.")
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie)throws InvalidCredentialsException, InvalidInputException {
        try {
        	//return ResponseEntity.ok(movieService.saveMovie(movie)); 
        	 Movie savedMovie = movieService.saveMovie(movie);
        	    return ResponseEntity.ok(savedMovie);
        	       // return movieService.createMovie(movie);
        	    
        }catch (Exception e) {
        	throw e;
        }
    }

    @Operation(summary = "Delete a movie", description = "Delete a movie by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id)throws InvalidCredentialsException, ResourceNotFoundException {
        try{
        	movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
        }catch (Exception e) {
        	throw e;
        }
    }
}