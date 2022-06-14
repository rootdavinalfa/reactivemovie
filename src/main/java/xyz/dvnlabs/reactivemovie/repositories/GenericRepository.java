package xyz.dvnlabs.reactivemovie.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@NoRepositoryBean
public interface GenericRepository<E, ID> extends ReactiveCrudRepository<E, ID> {
}
