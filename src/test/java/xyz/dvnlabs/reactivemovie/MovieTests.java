package xyz.dvnlabs.reactivemovie;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import xyz.dvnlabs.reactivemovie.entity.Movie;

import java.util.Comparator;

@SpringBootTest
@AutoConfigureWebTestClient
public class MovieTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void checkMovieListMustOK() {
        webClient
                .get()
                .uri("/movie/list")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void movieShouldInserted() {

        Movie movie = new Movie();
        movie.setMovieName("Test Movie");
        movie.setPublisher("N/A");
        movie.setSynopsis("Test synopsis");

        webClient
                .post()
                .uri("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(movie))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK);

    }

    @Test
    public void getMovieById() {
        webClient
                .get()
                .uri("/movie/by-id/1")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }


    @Test
    public void deleteMovie() {

        FluxExchangeResult<Movie> result = webClient.get().uri("/movie/list")
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .returnResult(Movie.class);

        result.getResponseBody().collectList().map(
                rslt -> rslt.stream().max(Comparator.comparing(Movie::getId)).orElse(new Movie())
        ).doOnSuccess(e -> {
            webClient
                    .delete()
                    .uri("/movie/" + e.getId())
                    .exchange()
                    .expectStatus()
                    .isEqualTo(HttpStatus.OK);
        });

    }


}
