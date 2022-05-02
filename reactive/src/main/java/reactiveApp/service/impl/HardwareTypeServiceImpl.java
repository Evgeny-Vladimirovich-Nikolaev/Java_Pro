package reactiveApp.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactiveApp.model.HardwareType;
import reactiveApp.repostiory.HardwareTypeRepository;
import reactiveApp.service.HardwareTypeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class HardwareTypeServiceImpl implements HardwareTypeService {

        private final HardwareTypeRepository repository;

        @Override
        @Transactional(readOnly = true)
        public Flux<HardwareType> findAll() {
            return repository.findAll();
        }

        @Override
        @Transactional(readOnly = true)
        public Mono<HardwareType> findByCode(String code) {
            return repository.findById(code);
        }

        @Override
        @Transactional
        public Mono<String> save(HardwareType type) {
            return repository.save(type).map(t -> "redirect:/directory");
        }

        @Override
        @Transactional
        public void delete(String code) {
            repository.deleteById(code);
        }
}
