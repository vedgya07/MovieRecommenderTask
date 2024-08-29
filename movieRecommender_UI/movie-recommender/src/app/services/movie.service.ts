import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface Movie {
  id: number;
  title: string;
  description: string;
  director: string;
  imageUrl: string;
  genres: string[];
}

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private baseUrl = 'http://localhost:8080/api/movies';

  constructor(private http: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}`);
  }

  getMovieById(id: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.baseUrl}/${id}`);
  }

  getRecommendations(movieId: number): Observable<Movie[]> {
    return this.http.get<Movie[]>(`${this.baseUrl}/${movieId}/recommendations`);
  }
}
