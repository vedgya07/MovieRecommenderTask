package com.example.movie.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "genres")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private Long gid;

     @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
     @JsonBackReference 
    private Set<Movie> movies;
    
	public Genre() {
	}

	public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
	
	public Genre(Long id, String name, Long gid) {
        this.id = id;
        this.name = name;
        this.gid = gid;
    }
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

	    @Override
	    public String toString() {
	        return "Genre{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                '}';
	    }

    
}


