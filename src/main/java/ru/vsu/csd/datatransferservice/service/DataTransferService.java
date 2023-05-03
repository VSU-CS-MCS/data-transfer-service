package ru.vsu.csd.datatransferservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.csd.datatransferservice.domain.Movie;
import ru.vsu.csd.datatransferservice.repos.MovieRepository;

import java.util.List;
import java.util.Map;

@Service
public class DataTransferService {
    private MovieRepository movieRepository;

    @Autowired
    public DataTransferService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public DataTransferService() {
    }

    public Movie createMovie(Movie movie) {
        try {
            if (movie.getEmotionalColoringJoy() == 0 &&
                    movie.getEmotionalColoringSadness() == 0 &&
                    movie.getEmotionalColoringAnger() == 0 &&
                    movie.getEmotionalColoringDisgust() == 0 &&
                    movie.getEmotionalColoringFear() == 0) {
                movie.setEmotionalColoring(movie.analysisEmotionalColoring(movie.getReview()));
            }

            return movie;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> getMovieForEmotions(Map<String, Object> request) {
        List<Double> emotionsVector = (List<Double>) request.get("emotionsVector");
        Double eps = (Double) request.get("epsilon");

        List<Movie> movies = movieRepository.findByEmotionalColoringJoyBetweenAndEmotionalColoringSadnessBetweenAndEmotionalColoringAngerBetweenAndEmotionalColoringDisgustBetweenAndEmotionalColoringFearBetween(
                emotionsVector.get(0) - eps, emotionsVector.get(0) + eps,
                emotionsVector.get(1) - eps, emotionsVector.get(1) + eps,
                emotionsVector.get(2) - eps, emotionsVector.get(2) + eps,
                emotionsVector.get(3) - eps, emotionsVector.get(3) + eps,
                emotionsVector.get(4) - eps, emotionsVector.get(4) + eps
        );

        return movies;
    }
}
