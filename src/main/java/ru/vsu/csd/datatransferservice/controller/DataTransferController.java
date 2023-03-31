package ru.vsu.csd.datatransferservice.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.csd.datatransferservice.domain.Movie;
import ru.vsu.csd.datatransferservice.repos.MovieRepo;

import java.util.List;

@RestController
@RequestMapping("data_transfer")
public class DataTransferController {
    private final MovieRepo movieRepo;

    @Autowired
    public DataTransferController(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public List<Movie> list() {
        return movieRepo.findAll();
    }

    @GetMapping("{id}")
    public Movie getMuvie(@PathVariable("id") Movie movie) {
        return movie;
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie) {
        return movieRepo.save(movie);
    }

    @PutMapping("{id}")
    public Movie update(@PathVariable("id") Movie movieFromDb, @RequestBody Movie movie) {
        BeanUtils.copyProperties(movie, movieFromDb, "id");

        return movieRepo.save(movie);
    }

    @DeleteMapping("id")
    public void delete(@PathVariable("id") Movie movie) {
        movieRepo.delete(movie);
    }

    @PostMapping("filter_by_title")
    public List<Movie> filterByTitle(@RequestParam String title) {
        if (title != null && !title.isEmpty()) {
            return movieRepo.findByTitle(title);
        }
        else {
            return movieRepo.findAll();
        }
    }

    @PostMapping("filter_by_year_release")
    public List<Movie> filterByYearRelease(@RequestParam String yearRelease) {
        if (yearRelease != null && !yearRelease.isEmpty()) {
            return movieRepo.findByTitle(yearRelease);
        }
        else {
            return movieRepo.findAll();
        }
    }
}
