import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {

  private apiUrl = `${environment.apiUrl}/recommendations`;

  constructor(private http: HttpClient) { }

  getRecommendedMovies(movieId: number): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.apiUrl}/${movieId}`);
  }
}
