package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.movie.entity.Genre;
import com.example.movie.entity.Movie;

import java.util.List;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	
	List<Movie> findByGenre_Name(String genreName);
    
	List<Movie> findByGenre(Genre genre);
	
    List<Movie> findByGenreIn(List<Genre> genre);

    // Find distinct movies liked by users with specific user IDs
    List<Movie> findDistinctByRatings_UserIdIn(List<Long> userIds);
}
