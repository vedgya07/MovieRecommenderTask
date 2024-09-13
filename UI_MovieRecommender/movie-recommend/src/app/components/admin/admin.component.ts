import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MovieService } from '../../services/movie.service'; // Adjust the path as needed
import { GenreService } from '../../services/genre.service'; // Adjust the path as needed

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  movie = {
    title: '',
    description: '',
    imageUrl: '',
    directorName: '',
    genre: ''
  };
  genres: any[] = []; // Replace with your Genre type

  constructor(
    private movieService: MovieService,
    private genreService: GenreService
  ) {}

  ngOnInit(): void {
    this.loadGenres();
  }

  loadGenres(): void {
    this.genreService.getGenres().subscribe(genres => {
      this.genres = genres;
    });
  }

  onSubmit(): void {
    if (this.movie.title && this.movie.description && this.movie.imageUrl && this.movie.directorName && this.movie.genre) {
      this.movieService.addMovie(this.movie).subscribe(response => {
        console.log('Movie added successfully!', response);
        // Reset form or navigate to another page
      });
    }
  }
}
