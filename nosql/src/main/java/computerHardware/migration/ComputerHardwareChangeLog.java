package computerHardware.migration;

import computerHardware.model.ComputerHardware;
import computerHardware.model.HardwareType;
import computerHardware.repository.HardwareTypeRepository;
import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@ChangeUnit(id="ComputerHardwareChangeLog", order = "2", author = "Evgeny Nikolaev", systemVersion = "1")
@RequiredArgsConstructor
public class ComputerHardwareChangeLog {

    private final MongoTemplate template;
    private final HardwareTypeRepository repository;
    private Set<ComputerHardware> dataSet = new HashSet<>();

    @BeforeExecution
    public void before() {
        template.createCollection("computerHardware");
        fillSet();
    }

    private void fillSet() {
        dataSet.addAll(
                List.of(new ComputerHardware[]{
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "Intel",
                                "Intel Core i9-10900KF",
                                repository.findById("CPU").orElse(new HardwareType("CPU", "no data"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "AMD",
                                "AMD Ryzen 9 3900X",
                                repository.findById("CPU").orElse(new HardwareType("CPU", "no data"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "Samsung",
                                "M393A2K40DB2-CVF",
                                repository.findById("DDR").orElse(new HardwareType("DDR", "no data"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "WD",
                                "WD2005FBYZ",
                                repository.findById("HDD").orElse(new HardwareType("HDD", "no data"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "Samsung",
                                "Samsung 870 EVO",
                                repository.findById("SSD").orElse(new HardwareType("SSD", "no data"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "GIGABYTE",
                                "GIGABYTE GeForce GTX 1650 Low Profile OC",
                                repository.findById("GDDR").orElse(new HardwareType("GDDR", "Graphics Double Data Rate"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "GIGABYTE",
                                "GIGABYTE B560 HD3",
                                repository.findById("MB").orElse(new HardwareType("MB", "MotherBoard"))),
                        new ComputerHardware(UUID.randomUUID().toString(),
                                "Creative Technology",
                                "Creative Sound BlasterX AE-5 Plus",
                                repository.findById("SC").orElse(new HardwareType("SC", "Sound Card")))
                })
        );
    }

    //When ChangeUnit provides BeforeExecution method, it must provide one(and only one) RollbackBeforeExecution method
    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("computerHardware");
    }

    @Execution
    public void migrationMethod() {
        dataSet.forEach(hardware -> template.save(hardware, "computerHardware"));
    }

    //ChangeUnit must have only one RollbackExecution method
    @RollbackExecution
    public void rollback() {
        //TODO
    }

}
