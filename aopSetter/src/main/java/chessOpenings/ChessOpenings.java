package chessOpenings;

import chessOpenings.dto.ChessOpeningDto;
import chessOpenings.service.ChessOpeningsClassifier;
import chessOpenings.service.impl.ChessOpeningsClassifierImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Set;

@Slf4j
@SpringBootApplication
public class ChessOpenings {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(ChessOpenings.class, args);
        final ChessOpeningsClassifier chessOpeningsClassifier = applicationContext.getBean(ChessOpeningsClassifierImpl.class);
        final Set<ChessOpeningDto> openings = chessOpeningsClassifier.getOpenings();

        log.info("Получен список дебютов {}", openings.toString());
        final ChessOpeningDto a60 = chessOpeningsClassifier.getOpeningByEco("A60").orElse(null);
        log.info("ИНформация по дебюту с кодом {}: {}", "A60", a60);
        final ChessOpeningDto с60 = chessOpeningsClassifier.getOpeningByEco("C60").orElse(null);
        log.info("ИНформация по дебюту с кодом {}: {}", "C60", с60);
        final ChessOpeningDto e60 = chessOpeningsClassifier.getOpeningByEco("E60").orElse(null);
        log.info("ИНформация по дебюту с кодом {}: {}", "E60", e60);
        final ChessOpeningDto d44 = chessOpeningsClassifier.getOpeningByEco("D44").orElse(null);
        log.info("ИНформация по дебюту с кодом {}: {}", "D44", d44);
    }

}
