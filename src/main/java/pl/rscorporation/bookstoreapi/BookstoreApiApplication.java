package pl.rscorporation.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class BookstoreApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
    }

    @Bean
    public Docket get() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .build()
                .apiInfo(new ApiInfo(
                        "BookstoreApi",
                        "REST API project for II",
                        "1.00",
                        "https://github.com/enzoLorenzo/bookstore-api",
                        new Contact("Szymon and Radek", "https://github.com/enzoLorenzo/bookstore-api", "email@email.com"),
                        "MIT license XD ;)",
                        "",
                        Collections.emptyList()
                ));
    }
}


