package xyz.dvnlabs.reactivemovie.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import xyz.dvnlabs.reactivemovie.entity.Movie;
import xyz.dvnlabs.reactivemovie.exception.ResourceNotFoundException;
import xyz.dvnlabs.reactivemovie.repositories.CommentRepository;
import xyz.dvnlabs.reactivemovie.repositories.MovieRepository;
import xyz.dvnlabs.reactivemovie.service.MovieService;

import java.util.List;
import java.util.Locale;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CommentRepository commentRepository) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Mono<Movie> findById(Long aLong) {
        return movieRepository.findById(aLong)
                .flatMap(e ->
                        commentRepository
                                .findByMovieId(e.getId()).collectList().map(comments -> {
                                    e.setComments(comments);
                                    return e;
                                })
                )
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Movie not found with id: " + aLong)
                ));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Mono<Void> save(Movie entity) {
        entity.setId(null);
        return movieRepository
                .save(entity).then();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Mono<Void> update(Movie entity) {
        return movieRepository.findById(entity.getId())
                .doOnSuccess(movieRepository::save)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Movie not found with id: " + entity.getId())
                ))
                .then();
    }

    @Override
    public Mono<Void> deleteById(Long aLong) {
        return movieRepository.existsById(aLong)
                .doOnSuccess(
                        e -> {
                            if (!e) {
                                throw new ResourceNotFoundException("Movie not found with id: " + aLong);
                            }
                        }
                )
                .then(movieRepository.deleteById(aLong));
    }

    @Override
    public Mono<Boolean> existById(Long aLong) {
        return movieRepository.existsById(aLong);
    }

    @Override
    public Mono<List<Movie>> findAllWithFilter(
            String movieName,
            String publisher
    ) {
        return movieRepository
                .findAll(movieName.toLowerCase(Locale.ROOT), publisher.toLowerCase(Locale.ROOT))
                .flatMap(e ->
                        commentRepository
                                .findByMovieId(e.getId()).collectList().map(comments -> {
                                    e.setComments(comments);
                                    return e;
                                })
                )
                .collectList();
    }
}
