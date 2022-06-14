package xyz.dvnlabs.reactivemovie.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI nostraApi() {
        return new OpenAPI()
                .info(
                        new Info().title("Nostra Movie API")
                                .version("v.1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Davin Alfarizky Putra Basudewa")
                                                .email("dbasudewa@gmail.com")
                                )
                );
    }

}
