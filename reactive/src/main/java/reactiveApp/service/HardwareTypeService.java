package reactiveApp.service;



import reactiveApp.model.HardwareType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HardwareTypeService {
    Flux<HardwareType> findAll();

    Mono<HardwareType> findByCode(String code);

    Mono<String> save(HardwareType type);

    void delete(String code);
}
