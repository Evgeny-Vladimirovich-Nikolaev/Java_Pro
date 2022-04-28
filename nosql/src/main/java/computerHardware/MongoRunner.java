package computerHardware;

import com.github.cloudyrock.spring.v5.EnableMongock;
import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.HardwareTypeDto;
import computerHardware.service.BookService;
import computerHardware.service.GenreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

@EnableMongock
@SpringBootApplication
public class MongoRunner {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(MongoRunner.class, args);
        final GenreService genreService = applicationContext.getBean(GenreService.class);
        genreService.save(new HardwareTypeDto("det", "Детективы"));
        genreService.save(new HardwareTypeDto("tri", "Триллеры"));
        final HardwareTypeDto roman = new HardwareTypeDto("rom", "Романы");
        genreService.save(roman);
        final BookService bookService = applicationContext.getBean(BookService.class);
        bookService.save(new ComputerHardwareDto(UUID.randomUUID().toString(), "978-5-17-087888-8", "Анна Каренина", roman.getCode()));
    }
}
