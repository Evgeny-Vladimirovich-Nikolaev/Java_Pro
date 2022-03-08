package cityDirectory.service.impl;

import cityDirectory.model.City;
import cityDirectory.repository.CityRepository;
import cityDirectory.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Override
    public Optional<City> findByCode(Integer code) {
        return repository.findById(code);
    }

    @Override
    @Transactional(readOnly = false)
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    @Transactional(readOnly = false)
    public City update(City city) {
        return save(city);
    }

    @Override
    public void deleteByCode(Integer code) {
        repository.deleteById(code);
    }
}
