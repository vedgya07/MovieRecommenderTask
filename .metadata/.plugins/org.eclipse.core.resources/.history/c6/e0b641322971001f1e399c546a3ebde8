package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.movie.entity.Rating;
import com.example.movie.exception.InvalidCredentialsException;
import com.example.movie.exception.InvalidInputException;
import com.example.movie.exception.InvalidRatingException;
import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Rating", description = "APIs for managing Ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Operation(summary = "Get rating with id", description = "Get rating of a given movie.")
    @GetMapping("/movie/{movieId}")
    public List<Rating> getRatingsByMovieId(@PathVariable Long movieId) throws InvalidInputException, InvalidCredentialsException, ResourceNotFoundException {
       try {
    	   return ratingService.getRatingsByMovieId(movieId);
       }catch (Exception e) {
    	   throw e;
       }
    }

    @Operation(summary = "Insert a rating", description = "Insert a rating for movie.")
    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating)throws InvalidInputException, InvalidCredentialsException, ResourceNotFoundException, InvalidRatingException {
        try { 
        	return ResponseEntity.ok(ratingService.addRating(rating));
        }catch (Exception e) {
        	throw e;
        }
    }
}
