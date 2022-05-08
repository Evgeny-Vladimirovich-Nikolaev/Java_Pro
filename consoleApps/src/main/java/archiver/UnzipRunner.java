package archiver;

import archiver.service.ArchiveService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class UnzipRunner {


    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(ZipRunner.class, args);
        final ArchiveService archiveService = applicationContext.getBean(ArchiveService.class);
        String file = ValueReceiver.receiveString("Введите путь к файлу");
        System.out.println(archiveService.unzip(file));

    }
}
