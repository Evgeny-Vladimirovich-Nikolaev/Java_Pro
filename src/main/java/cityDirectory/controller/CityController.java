package cityDirectory.controller;

import cityDirectory.model.City;
import cityDirectory.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.persistence.NonUniqueResultException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * sc 473 Воронеж Voronezh 1050602
 * sc 843 Казань Kazan 1257391
 * sc 495 Москва Moskow 12655050
 * l en
 * sc 815 Мурманск Murmansk 307664
 * sc 383 Новосибирск Novosibirsk
 * sc 381 Омск Omsk
 * sc 811 Псков Pskov
 * sc 863 Ростов-на-Дону Rostov-on-Don 1137904
 * sc 845 Саратов Saratov
 * sc 485 Ярославль Yaroslavl
 * pc 843 125341
 * pc 383 1620162
 * pn Псков 209426
 * l fr
 * pn Saratov 830155
 * fc 383
 * fn Мурманск
 * fn Rostov-on-Don
 * l de
 * fn Vladimir
 * delc 383
 * deln Мурманск
 */

@ShellComponent
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private Optional<City> city;
    private String report;

    private final String APP_ID = "appId";
    ResourceBundle resources = ResourceBundle.getBundle("messages");


    @ShellMethod(value = "change language", key = {"l", "lang"})
    public String changeLocale(@ShellOption("-l") String lang) {
        resources = ResourceBundle.getBundle("messages", new Locale(lang));
        return resources.getString("lang");
    }

    @ShellMethod(value = "save/update city", key = {"sc", "savecity"})
    public String save(
            @ShellOption({"-c", "--code"}) Integer code,
            @ShellOption({"-ru", "--cityru"}) String ru,
            @ShellOption({"-en", "--cityen"}) String en,
            @ShellOption(value = {"-p", "--pop"}, defaultValue = ShellOption.NULL) Integer population) {
        final City city = new City(code, ru, en);
        city.setPopulation(population);
        cityService.save(city);
        return String.format(resources.getString("city.saved"), ru, en, code);
    }

    @ShellMethod(value = "update population by code", key = {"pc", "popcode"})
    public String updatePopulationByCode(
            @ShellOption({"-c", "--code"}) Integer code,
            @ShellOption(value = {"-p", "--pop"}) Integer population) {
        findByCode(code);
        city.ifPresentOrElse(
                city -> report = updatePopulation(city, population),
                () -> report = String.format(resources.getString("city.no.code.results"), code)
        );
        return report;
    }

    @ShellMethod(value = "update population by name", key = {"pn", "popname"})
    public String updatePopulationByName(
            @ShellOption({"-n", "--name"}) String name,
            @ShellOption(value = {"-p", "--pop"}) Integer population) {
        findByName(name);
        city.ifPresentOrElse(
                c -> report = updatePopulation(c, population),
                () -> report = String.format(resources.getString("city.no.name.results"), name)
        );
        return report;
    }

    @ShellMethod(value = "delete city by code", key = {"dc", "delc"})
    public String deleteByCode(
            @ShellOption({"-c", "--code"}) Integer code) {
        findByCode(code);
        city.ifPresentOrElse(
                c -> {
                    cityService.deleteByCode(city.get().getCode());
                    report = String.format(resources.getString("city.removed"), code);
                },
                () -> report = String.format(resources.getString("city.no.code.results"), code)
        );
        return report;
    }

    @ShellMethod(value = "delete city by name", key = {"dn", "deln"})
    public String deleteByName(
            @ShellOption({"-n", "--name"}) String name) {
        findByName(name);
        city.ifPresentOrElse(
                c -> {
                    cityService.deleteByCode(city.get().getCode());
                    report = String.format(resources.getString("city.removed"), name);
                },
                () -> report = String.format(resources.getString("city.no.name.results"), name)
        );
        return report;
    }

    @ShellMethod(value = "find city by name", key = {"fn", "findn"})
    public String findByName(@ShellOption({"-n", "--name"}) String name) {
        try {
            city = cityService.findByRuName(name);
            if (city.isEmpty()) {
                city = cityService.findByEnName(name);
            }
            if (city.isEmpty()) {
                report = String.format(resources.getString("city.no.name.results"), name);
            } else {
                report = city.toString();
            }
        } catch (NonUniqueResultException e) {
            report = String.format(resources.getString("city.invalid.operation"), name);
        }
        return report;
    }

    @ShellMethod(value = "find city by code", key = {"fc", "findc"})
    public String findByCode(@ShellOption({"-c", "--code"}) Integer code) {
        city = cityService.findByCode(code);
        city.ifPresentOrElse(
                city -> report = String.format(resources.getString("city.code.results"), code, city.toString()),
                () -> report = String.format(resources.getString("city.no.code.results"), code)
        );
        return report;
    }

    private String updatePopulation(City city, Integer population) {
        city.setPopulation(population);
        cityService.save(city);
        return String.format(resources.getString("city.population.update"), city.getRuName());
    }

}

