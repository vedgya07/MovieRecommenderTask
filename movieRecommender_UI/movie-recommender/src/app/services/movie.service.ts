import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Movie } from '../models/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private apiUrl = `${environment.apiUrl}/movies`;

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.apiUrl);
  }

  getMovieById(id: number): Observable<Movie> {
    return this.http.get<Movie>(`${this.apiUrl}/${id}`);
  }
}
