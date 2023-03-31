package ru.vsu.csd.datatransferservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.csd.datatransferservice.domain.Movie;

public interface MovieRepo extends CrudRepository <Movie, Long> {
}
