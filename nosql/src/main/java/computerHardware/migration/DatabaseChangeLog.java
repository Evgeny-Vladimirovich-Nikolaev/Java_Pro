package computerHardware.migration;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import computerHardware.model.HardwareType;
import computerHardware.repository.HardwareTypeRepository;
import org.bson.Document;

@ChangeLog
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dropDb", author = "Evgeny Nikolaev", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertCPU", author = "Evgeny Nikolaev")
    public void insertCPU(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("hardwareType");
        var doc = new Document()
                .append("_id", "CPU")
                .append("description", "Central processing unit");
        collection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertDDR", author = "Evgeny Nikolaev")
    public void insertDDR(HardwareTypeRepository repository) {
        repository.save(new HardwareType("DDR", "Double Data Rate Synchronous Dynamic Random-Access Memory"));
    }

    @ChangeSet(order = "004", id = "insertHDD", author = "Evgeny Nikolaev")
    public void insertHDD(HardwareTypeRepository repository) {
        repository.save(new HardwareType("HDD", "Hard disk drive"));
    }

    @ChangeSet(order = "005", id = "insertSSD", author = "Evgeny Nikolaev")
    public void insertSSD(HardwareTypeRepository repository) {
        repository.save(new HardwareType("SSD", "Solid-State Drive"));
    }

    @ChangeSet(order = "006", id = "insertCD", author = "Evgeny Nikolaev")
    public void insertCD(HardwareTypeRepository repository) {
        repository.save(new HardwareType("CD-ROM", "Compact Disc Read-Only Memory"));
    }

    @ChangeSet(order = "007", id = "insertDVD", author = "Evgeny Nikolaev")
    public void insertDVD(HardwareTypeRepository repository) {
        repository.save(new HardwareType("DVD-ROM", "Digital Versatile Disc Read-Only Memory"));
    }

}
