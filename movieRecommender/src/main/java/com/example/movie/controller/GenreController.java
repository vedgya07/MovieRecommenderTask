package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.movie.entity.Genre;
import com.example.movie.exception.InvalidCredentialsException;
import com.example.movie.exception.InvalidInputException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.service.GenreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Genre", description = "APIs for managing Genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Operation(summary = "Get all genres", description = "Retrieve a list of all genres.")
    @GetMapping
    public List<Genre> getAllGenres()throws InvalidCredentialsException {
        try {
        	return genreService.getAllGenres();
        }catch (Exception e ) {
        	throw e;
        }
    }

    @Operation(summary = "Get a genre", description = "Retrieve a genre with given id.")
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id)throws InvalidCredentialsException, ResourceNotFoundException {
       try {
    	   return ResponseEntity.ok(genreService.getGenreById(id));
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Add a Genre", description = "Add a movie genre.")
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre)throws InvalidCredentialsException, InvalidInputException {
       try {
    	   return ResponseEntity.ok(genreService.saveGenre(genre));
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Delete a Genre", description = "Delete a Genre with Given Id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id)throws InvalidCredentialsException, ResourceNotFoundException {
        try {
        	genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
        }catch (Exception e) {
        	throw e;
        }
    }
}
