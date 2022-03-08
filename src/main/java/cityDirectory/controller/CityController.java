package cityDirectory.controller;

import bookBase.BaseController;
import cityDirectory.model.City;
import cityDirectory.service.CityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.persistence.NonUniqueResultException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@ShellComponent
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private Optional<City> city;
    private String report;

    private final String APP_ID = "appId";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    @ShellMethod(value = "change language", key = {"l", "lang"})
    public void changeLocale(@ShellOption({"-l, --lang"}) String lang) {
        System.out.println("Смена языка");
        ResourceBundle resources = ResourceBundle.getBundle("messages", new Locale(lang));
    }

    @ShellMethod(value = "save/update city", key = {"sc", "savecity"})
    public void save(
            @ShellOption({"-c", "--code"}) Integer code,
            @ShellOption({"-ru", "--cityru"}) String ru,
            @ShellOption({"-en", "--cityen"}) String en,
            @ShellOption(value = {"-p", "--pop"}, defaultValue = ShellOption.NULL) Integer population) {
        final City city = new City(code, ru, en);
        city.setPopulation(population);
        cityService.save(city);
    }

    @ShellMethod(value = "update population by code", key = {"pc", "popcode"})
    public String updatePopulationByCode(
            @ShellOption({"-c", "--code"}) Integer code,
            @ShellOption(value = {"-p", "--pop"}) Integer population) {
        findByCode(code);
        city.ifPresentOrElse(
                city -> report = updatePopulation(city, population),
                () -> report = String.format("Город с кодом %s в базе не найден", code)
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
                () -> report = String.format("Город %s в базе не найден", name)
        );
        return report;
    }

    @ShellMethod(value = "delete city by code", key = {"dc", "delc"})
    public String deleteByCode(
            @ShellOption({"-nru", "--runame"}) String ruName,
            @ShellOption(value = {"-p", "--pop"}) Integer population) {
        findByName(ruName);
        city.ifPresentOrElse(
                c -> report = updatePopulation(c, population),
                () -> report = String.format("Город %s в базе не найден", ruName)
        );
        return report;
    }

    @ShellMethod(value = "delete city by name", key = {"dn", "deln"})
    public String deleteByName(
            @ShellOption({"-nru", "--name"}) String name) {
        findByName(name);
        city.ifPresentOrElse(
                c -> {
                    cityService.deleteByCode(city.get().getCode());
                    report = String.format("Город %s успешно удалён из базы", name);
                },
                () -> report = String.format("Город %s в базе не найден", name)
        );
        return report;
    }

    @ShellMethod(value = "find city by name", key = {"fn", "findn"})
    public String findByName(
            @ShellOption(value = {"-n", "--name"}, defaultValue = ShellOption.NULL) String name) {
        try {
            city = cityService.findByRuName(name);
            if (city.isEmpty()) {
                city = cityService.findByEnName(name);
            }
            if (city.isEmpty()) {
                report = String.format("Город %s в базе не найден", name);
            } else {
                report = city.toString();
            }
        } catch (NonUniqueResultException e) {
            report = "Невозможно завершить операцию, так как значение %s не является уникальным";
        }
        return report;
    }

    @ShellMethod(value = "find city by code", key = {"fc", "findc"})
    public String findByCode(@ShellOption({"-c", "--code"}) Integer code) {
        cityService.findByCode(code).ifPresentOrElse(
                city -> report = String.format("Коду %s соответствует город %s", code, city.toString()),
                () -> report = String.format("Город с кодом %s в базе не найден", code)
        );
        return report;
    }

    private String updatePopulation(City city, Integer population) {
        city.setPopulation(population);
        cityService.save(city);
        return String.format("Данные о населении города %s внесены в базу", city.getRuName());
    }

}

