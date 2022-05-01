package computerHardware;

import com.github.cloudyrock.spring.v5.EnableMongock;
import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.HardwareTypeDto;
import computerHardware.service.ComputerHardwareService;
import computerHardware.service.HardwareTypeService;
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
//        hardwareTypeService.save(new HardwareTypeDto("det", "Детективы"));
//        hardwareTypeService.save(new HardwareTypeDto("tri", "Триллеры"));
        final HardwareTypeDto roman = new HardwareTypeDto("rom", "Романы");
//        hardwareTypeService.save(roman);
        final ComputerHardwareService computerHardwareService = applicationContext.getBean(ComputerHardwareService.class);
//        computerHardwareService.save(new ComputerHardwareDto(UUID.randomUUID().toString(), "978-5-17-087888-8", "Анна Каренина", roman.getCode()));
    }
}
