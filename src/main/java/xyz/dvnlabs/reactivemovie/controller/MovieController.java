package xyz.dvnlabs.reactivemovie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import xyz.dvnlabs.reactivemovie.entity.Movie;
import xyz.dvnlabs.reactivemovie.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movie")
@Tag(name = "Movie Controller")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/by-id/{id}")
    @Operation(summary = "Find by id")
    Mono<Movie> findById(
            @PathVariable Long id
    ) {
        return movieService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Save movie")
    Mono<Void> save(
            @RequestBody Movie movie
    ) {
        return movieService.save(movie);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    Mono<Void> deleteById(
            @PathVariable Long id
    ) {
        return movieService.deleteById(id);
    }

    @GetMapping("/list")
    @Operation(summary = "List with filter")
    Mono<List<Movie>> findAll(
            @RequestParam(defaultValue = "") String movieName,
            @RequestParam(defaultValue = "") String publisher
    ) {
        return movieService.findAllWithFilter(movieName, publisher);
    }


}
