package ru.vsu.csd.datatransferservice.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csd.datatransferservice.domain.Movie;
import ru.vsu.csd.datatransferservice.repos.MovieRepository;
import ru.vsu.csd.datatransferservice.service.DataTransferService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("data-transfer")
public class DataTransferController {
    private MovieRepository movieRepository;
    private DataTransferService dataTransferService;

    @Autowired
    public DataTransferController(MovieRepository movieRepository, DataTransferService dataTransferService) {
        this.movieRepository = movieRepository;
        this.dataTransferService = dataTransferService;
    }

    @GetMapping("movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("movies/{id}")
    public Movie getMovie(@PathVariable("id") Movie movie) {
        return movie;
    }

    @GetMapping("moviesForEmotions")
    public List<Movie> getMovieForEmotions(@RequestBody Map<String, Object> request) {
        return dataTransferService.getMovieForEmotions(request);
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(dataTransferService.createMovie(movie));
    }

    @PutMapping("movies/{id}")
    public Movie updateMovie(@PathVariable("id") Movie movieFromDb, @RequestBody Movie movie) {
        BeanUtils.copyProperties(movie, movieFromDb, "id");

        return movieRepository.save(movieFromDb);
    }

    @DeleteMapping("movies/{id}")
    public void deleteMovie(@PathVariable("id") Long id) {
        movieRepository.deleteById(id);
    }

    @DeleteMapping("movies")
    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }
}
