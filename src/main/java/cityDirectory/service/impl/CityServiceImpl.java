package cityDirectory.service.impl;

import cityDirectory.model.City;
import cityDirectory.repository.CityRepository;
import cityDirectory.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<City> findByCode(Integer code) {
        return repository.findById(code);
    }

    @Override
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    public Optional<City> findByRuName(String ruName) throws NonUniqueResultException{
        return repository.findByRuName(ruName);
    }

    @Override
    public Optional<City> findByEnName(String enName) throws NonUniqueResultException{
        return repository.findByEnName(enName);
    }

    @Override
    public void deleteByCode(Integer code) {
        repository.deleteById(code);
    }

}
