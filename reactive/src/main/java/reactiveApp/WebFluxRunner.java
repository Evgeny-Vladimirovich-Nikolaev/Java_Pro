package reactiveApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactiveApp.model.HardwareType;
import reactiveApp.repostiory.HardwareTypeRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class WebFluxRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxRunner.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(HardwareTypeRepository repository) {
        return route()
                .GET("/api/genres", accept(APPLICATION_JSON),
                        request -> ok().contentType(APPLICATION_JSON).body(repository.findAll(), HardwareType.class)
                )
                .DELETE("/api/genres/{code}",
                        request -> repository.deleteById(request.pathVariable("code")).flatMap(v -> ok().build())
                ).build();
    }
}
