package ru.vsu.csd.datatransferservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.csd.datatransferservice.domain.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    static List<Movie> findByEmotionalColoringJoyAndEmotionalColoringSadnessAndEmotionalColoringAngerAndEmotionalColoringDisgustAndEmotionalColoringFear(
            double emotionalColoringJoy,
            double emotionalColoringSadness,
            double emotionalColoringAnger,
            double emotionalColoringDisgust,
            double emotionalColoringFear,
            double eps) {
        return null;
    }

    List<Movie> findByEmotionalColoringJoyBetweenAndEmotionalColoringSadnessBetweenAndEmotionalColoringAngerBetweenAndEmotionalColoringDisgustBetweenAndEmotionalColoringFearBetween(
            double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7, double v8, double v9);
}
