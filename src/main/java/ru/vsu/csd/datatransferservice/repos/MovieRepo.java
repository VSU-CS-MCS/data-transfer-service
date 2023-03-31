package ru.vsu.csd.datatransferservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.csd.datatransferservice.domain.Movie;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

    List<Movie> fideByYearRelease(String yearRelease);
}
