package archiver;

import archiver.service.ArchiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class ZipRunner {

    public static void main(String[] args) {
        SpringApplication.run(ZipRunner.class, args);
    }
}
