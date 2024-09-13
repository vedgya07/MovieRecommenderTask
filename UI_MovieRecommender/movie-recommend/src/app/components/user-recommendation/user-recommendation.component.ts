import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../services/movie.service';
import { RecommendationService } from '../../services/recommendation.service';
import { Movie } from '../../models/movie.model';
@Component({
  selector: 'app-user-recommendation',
  templateUrl: './user-recommendation.component.html',
  styleUrls: ['./user-recommendation.component.css']
})
export class UserRecommendationComponent implements OnInit {

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
