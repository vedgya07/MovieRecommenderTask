import { Component, OnInit } from '@angular/core';
//import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MovieService } from '../../services/movie.service';
import { RecommendationService } from '../../services/recommendation.service';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  movie: Movie | undefined;
  recommendations: Movie[] = [];

  constructor(
    private movieService: MovieService,
    private recommendationService: RecommendationService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.movieService.getMovieById(id).subscribe(data => {
      this.movie = data;
    });
  }

  fetchRecommendations(): void {
    if (this.movie) {
      this.recommendationService.getRecommendedMovies(this.movie.id).subscribe(data => {
        this.recommendations = data;
      });
    }
  }
}
