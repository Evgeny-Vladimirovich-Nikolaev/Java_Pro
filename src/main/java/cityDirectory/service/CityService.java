package cityDirectory.service;

import cityDirectory.model.City;

import java.util.Optional;

public interface CityService {

    Optional<City> findByCode(Integer code);
    City save(City city);
    City update(City city);
    void deleteByCode(Integer code);

}
