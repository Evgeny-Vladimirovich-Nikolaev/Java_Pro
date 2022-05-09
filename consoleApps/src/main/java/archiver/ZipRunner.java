package archiver;

import archiver.service.ArchiveService;
import archiver.service.impl.ArchiveServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ZipRunner {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(ZipRunner.class, args);
        final ArchiveService archiveService = applicationContext.getBean(ArchiveService.class);
        final ValueReceiver valueReceiver = applicationContext.getBean(ValueReceiver.class);
        String source = valueReceiver.receiveString("Укажите путь к файлу");
        System.out.println(archiveService.zip(source));
    }
}
