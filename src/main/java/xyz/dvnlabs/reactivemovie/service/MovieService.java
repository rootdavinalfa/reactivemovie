package xyz.dvnlabs.reactivemovie.service;

import reactor.core.publisher.Mono;
import xyz.dvnlabs.reactivemovie.entity.Movie;

import java.util.List;

public interface MovieService extends GenericService<Movie, Long> {

    Mono<List<Movie>> findAllWithFilter(
            String movieName,
            String publisher
    );
}
