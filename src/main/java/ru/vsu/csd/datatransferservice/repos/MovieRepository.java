package ru.vsu.csd.datatransferservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.csd.datatransferservice.domain.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByEmotionalColoringJoyBetweenAndEmotionalColoringSadnessBetweenAndEmotionalColoringAngerBetweenAndEmotionalColoringDisgustBetweenAndEmotionalColoringFearBetween(
            double JoyMin, double JoyMax,
            double SadnessMin, double SadnessMax,
            double AngerMin, double AngerMax,
            double DisgustMin, double DisgustMax,
            double FearMin, double FearMax);
}
