package ru.vsu.csd.datatransferservice.service;

import org.springframework.stereotype.Service;
import ru.vsu.csd.datatransferservice.domain.Movie;

@Service
public class DataTransferService {
    public DataTransferService() {
    }


    public static Movie createMovie(Movie movie) {
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
}
