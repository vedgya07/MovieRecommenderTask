package com.example.movie.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int noveltyScore;
    private int finalScore;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

	public Rating() {
			}

	public Rating(Long id, Movie movie, User user, int noveltyScore, int finalScore, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.movie = movie;
		this.user = user;
		this.noveltyScore = noveltyScore;
		this.finalScore = finalScore;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNoveltyScore() {
		return noveltyScore;
	}

	public void setNoveltyScore(int noveltyScore) {
		this.noveltyScore = noveltyScore;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", movie=" + movie + ", user=" + user + ", noveltyScore=" + noveltyScore
				+ ", finalScore=" + finalScore + ", createdAt=" + createdAt + "]";
	}

    
}

