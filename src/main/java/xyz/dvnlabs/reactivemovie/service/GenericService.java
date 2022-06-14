package xyz.dvnlabs.reactivemovie.service;

import reactor.core.publisher.Mono;

public interface GenericService<E, ID> {

    Mono<E> findById(ID id);

    Mono<Void> save(E entity);

    Mono<Void> update(E entity);

    Mono<Void> deleteById(ID id);

    Mono<Boolean> existById(ID id);

}
