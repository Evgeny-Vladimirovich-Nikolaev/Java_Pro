package computerHardware;

import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.HardwareTypeDto;
import computerHardware.service.ComputerHardwareService;
import computerHardware.service.HardwareTypeService;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

@EnableMongock
@SpringBootApplication
public class MongoRunner {

    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext =
                SpringApplication.run(MongoRunner.class, args);
        final HardwareTypeService hardwareTypeService = applicationContext.getBean(HardwareTypeService.class);
        hardwareTypeService.save(new HardwareTypeDto("KB", "Keyboard"));
        hardwareTypeService.save(new HardwareTypeDto("Ms", "Mouse"));
        final HardwareTypeDto monitor = new HardwareTypeDto("Mon", "Monitor");
        hardwareTypeService.save(monitor);
       final ComputerHardwareService computerHardwareService = applicationContext.getBean(ComputerHardwareService.class);
        computerHardwareService.save(new ComputerHardwareDto(UUID.randomUUID().toString(), "Philips", "Philips 279P1", monitor.getCode()));
    }
}
