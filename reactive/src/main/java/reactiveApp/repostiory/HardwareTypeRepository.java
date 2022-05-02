package reactiveApp.repostiory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactiveApp.model.HardwareType;

public interface HardwareTypeRepository extends ReactiveMongoRepository<HardwareType, String> {
}
