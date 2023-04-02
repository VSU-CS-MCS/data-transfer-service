package ru.vsu.csd.datatransferservice.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csd.datatransferservice.domain.Movie;
import ru.vsu.csd.datatransferservice.repos.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("data_transfer")
public class DataTransferController {
    private final MovieRepository movieRepo;

    @Autowired
    public DataTransferController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieRepo.findAll();
    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable("id") Movie movie) {
        return movie;
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        System.out.println(movie); //todo create logs
        return movieRepo.save(movie);
    }

    @PutMapping("{id}")
    public Movie update(@PathVariable("id") Movie movieFromDb, @RequestBody Movie movie) {
        BeanUtils.copyProperties(movie, movieFromDb, "id");

        return movieRepo.save(movieFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        if (id.equals("all")) {
            movieRepo.deleteAll();
        } else {
            movieRepo.deleteById(Long.parseLong(id));
        }
    }
}
