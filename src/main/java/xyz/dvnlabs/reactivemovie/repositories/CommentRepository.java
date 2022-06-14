package xyz.dvnlabs.reactivemovie.repositories;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import xyz.dvnlabs.reactivemovie.entity.Comment;

@Repository
public interface CommentRepository extends GenericRepository<Comment, Long> {


    Flux<Comment> findByMovieId(Long movieId);

}
