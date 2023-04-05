package ru.vsu.csd.datatransferservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.csd.datatransferservice.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
