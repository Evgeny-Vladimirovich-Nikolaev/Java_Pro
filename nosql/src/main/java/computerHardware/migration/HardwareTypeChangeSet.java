package computerHardware.migration;

import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import computerHardware.model.HardwareType;
import computerHardware.repository.HardwareTypeRepository;
import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@ChangeLog
@ChangeUnit(id="HardwareTypeChangeSet", order = "1", author = "Evgeny Nikolaev", systemVersion = "1")
@RequiredArgsConstructor
public class HardwareTypeChangeSet {

    private final MongoTemplate template;
    private Set<HardwareType> dataSet = new HashSet<>();

    @BeforeExecution
    public void before() {
        template.createCollection("hardwareType");
        fillSet();
    }

    private void fillSet() {
        dataSet.addAll(
                List.of(new HardwareType[]{
                        new HardwareType("CPU", "Central processing unit"),
                        new HardwareType("DDR", "Double Data Rate Synchronous Dynamic Random-Access Memory"),
                        new HardwareType("HDD", "Hard disk drive"),
                        new HardwareType("SSD", "Solid-State Drive"),
                        new HardwareType("CD-ROM", "Compact Disc Read-Only Memory"),
                        new HardwareType("DVD-ROM", "Digital Versatile Disc Read-Only Memory")
                })
        );
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("hardwareType");
    }

    @Execution
    public void migrationMethod() {
        dataSet.forEach(hardware -> template.save(hardware, "hardwareType"));
    }

    @RollbackExecution
    public void rollback() {
        //template.dropCollection("hardwareType");
    }

}
