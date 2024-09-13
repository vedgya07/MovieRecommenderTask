package com.example.movie.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String imageUrl;

    
    private String directorName;

 // Many movies can have one genre
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)  // Enforces foreign key
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference 
    private Genre genre;
    
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference 
    private List<Rating> ratings;

    
	public Movie() {
	}

	public Movie(Long id, String title, String description, String imageUrl, String directorName, Genre genre) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.directorName = directorName;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName  = directorName;
	}

	public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", directorName='" + directorName + '\'' +
                ", genre=" + genre +
                '}';
    }

    
}


