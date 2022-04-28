package computerHardware.repository;

import computerHardware.model.ComputerHardware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.repository.MongoRepository;

@ConditionalOnProperty(value = "application.nosql.type", havingValue = "mongo", matchIfMissing = true)
public interface BookRepository extends MongoRepository<ComputerHardware, String> {
}
