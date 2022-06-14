package xyz.dvnlabs.reactivemovie.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import xyz.dvnlabs.reactivemovie.entity.Movie;

@Repository
public interface MovieRepository extends GenericRepository<Movie, Long> {

    @Query(value = "SELECT a.* FROM movie a " +
            "WHERE (lower(a.movie_name) LIKE concat('%',:movieName,'%') " +
            "OR lower(a.publisher) = concat('%',:publisher,'%')) ")
    Flux<Movie> findAll(
            @Param("movieName") String movieName,
            @Param("publisher") String publisher
    );

}
