package main.java.cityDirectory.service;

import cityDirectory.model.City;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

public interface CityService {

    Optional<City> findByCode(Integer code);
    Optional<City> findByRuName(String ruName) throws NonUniqueResultException;
    Optional<City> findByEnName(String enName) throws NonUniqueResultException;
    City save(City city);
    void deleteByCode(Integer code);

}
