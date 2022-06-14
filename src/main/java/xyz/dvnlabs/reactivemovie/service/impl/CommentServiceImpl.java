package xyz.dvnlabs.reactivemovie.service.impl;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import xyz.dvnlabs.reactivemovie.entity.Comment;
import xyz.dvnlabs.reactivemovie.exception.ResourceNotFoundException;
import xyz.dvnlabs.reactivemovie.repositories.CommentRepository;
import xyz.dvnlabs.reactivemovie.service.CommentService;

@Repository
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Mono<Comment> findById(Long aLong) {
        return commentRepository.findById(aLong)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Comment not found with id: " + aLong)
                ));
    }

    @Override
    public Mono<Void> save(Comment entity) {
        return commentRepository
                .save(entity).then();    }

    @Override
    public Mono<Void> update(Comment entity) {
        return commentRepository.findById(entity.getId())
                .doOnSuccess(commentRepository::save)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Comment not found with id: " + entity.getId())
                ))
                .then();
    }

    @Override
    public Mono<Void> deleteById(Long aLong) {
        return commentRepository.existsById(aLong)
                .doOnSuccess(
                        e -> {
                            if (!e) {
                                throw new ResourceNotFoundException("Comment not found with id: " + aLong);
                            }
                        }
                )
                .then(commentRepository.deleteById(aLong));
    }

    @Override
    public Mono<Boolean> existById(Long aLong) {
        return commentRepository.existsById(aLong);
    }
}
