package main.java.cityDirectory.repository;

import cityDirectory.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByRuName(String ruName) throws NonUniqueResultException;
    Optional<City> findByEnName(String enName) throws NonUniqueResultException;
}
