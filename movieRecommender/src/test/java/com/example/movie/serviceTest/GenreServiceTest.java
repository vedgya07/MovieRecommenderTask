package com.example.movie.serviceTest;

import com.example.movie.entity.Genre;
import com.example.movie.repository.GenreRepository;
import com.example.movie.service.GenreService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllGenres() {
        Genre genre1 = new Genre(1L, "Action");
        Genre genre2 = new Genre(2L, "Comedy");

        when(genreRepository.findAll()).thenReturn(List.of(genre1, genre2));

        List<Genre> genres = genreService.getAllGenres();
        assertNotNull(genres);
        assertEquals(2, genres.size());
        assertTrue(genres.contains(genre1));
        assertTrue(genres.contains(genre2));
    }

    @Test
    public void testGetGenreById() {
        Long genreId = 1L;
        Genre genre = new Genre(genreId, "Action");

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));

        Genre result = genreService.getGenreById(genreId);
        assertNotNull(result);
        assertEquals(genreId, result.getId());
        assertEquals("Action", result.getName());
    }

    @Test
    public void testGetGenreById_NotFound() {
        Long genreId = 99L;

        when(genreRepository.findById(genreId)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            genreService.getGenreById(genreId);
        });
        assertEquals("Genre not found", thrown.getMessage());
    }

    @Test
    public void testSaveGenre() {
        Genre genre = new Genre(1L, "Action");

        when(genreRepository.save(genre)).thenReturn(genre);

        Genre result = genreService.saveGenre(genre);
        assertNotNull(result);
        assertEquals(genre.getId(), result.getId());
        assertEquals(genre.getName(), result.getName());
    }

    @Test
    public void testDeleteGenre(){
        Long genreId = 1L;

        doNothing().when(genreRepository).deleteById(genreId);

        assertDoesNotThrow(() -> genreService.deleteGenre(genreId));
        verify(genreRepository, times(1)).deleteById(genreId);
    }

    @Test
    public void testDeleteGenre_NotFound() {
        Long genreId = 99L;

        doThrow(new RuntimeException("Genre not found")).when(genreRepository).deleteById(genreId);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            genreService.deleteGenre(genreId);
        });
        assertEquals("Genre not found", thrown.getMessage());
    }
}

