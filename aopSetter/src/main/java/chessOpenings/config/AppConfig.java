package chessOpenings.config;

import chessOpenings.service.ChessOpeningsClassifier;
import chessOpenings.service.impl.ChessOpeningsClassifierImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public ChessOpeningsClassifier chessOpeningsClassifier() {
//        return new ChessOpeningsClassifierImpl();
//    }

}
