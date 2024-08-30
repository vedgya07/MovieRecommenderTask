import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { RecommendationService } from '../../services/recommendation.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {

  movies: Movie[] = [];
  recommendations: Movie[] = [];
  selectedMovieId: number | undefined;

  constructor(
    private movieService: MovieService,
    private recommendationService: RecommendationService
  ) { }

  ngOnInit(): void {
    this.movieService.getAllMovies().subscribe(data => {
      this.movies = data;
    });
  }

  fetchRecommendations(): void {
    if (this.selectedMovieId) {
      this.recommendationService.getRecommendedMovies(this.selectedMovieId).subscribe(data => {
        this.recommendations = data;
      });
    }
  }
}
