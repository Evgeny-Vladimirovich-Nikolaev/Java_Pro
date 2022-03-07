package cityDirectory.controller;

import bookBase.BaseController;
import cityDirectory.model.City;
import cityDirectory.service.CityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@ShellComponent
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    private final String APP_ID = "appId";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    @ShellMethod(value = "change language", key = {"lang", "l"})
    public void changeLocale(@ShellOption({"-l, --lang"}) String lang) {
        System.out.println("Смена языка");
        ResourceBundle resources = ResourceBundle.getBundle("messages", new Locale(lang));
        System.out.println(resources.getString("no.results"));
    }

    @ShellMethod(value = "save city", key = {"sc", "city"})
    public void save(
            @ShellOption({"-c", "--code"}) Integer code,
            @ShellOption({"-ru", "--cityru"}) String ru,
            @ShellOption({"-en", "--cityen"}) String en,
            @ShellOption(value = {"-p", "--pop"}, defaultValue = ShellOption.NULL) Integer population) {
        final City city = new City(code, ru, en);
        city.setPopulation(population);
        cityService.save(city);
    }

    @ShellMethod(value = "find by code", key = {"fc", "findc"})
    public void findByCode(@ShellOption({"-c", "--code"}) Integer code) {
        cityService.findByCode(code).ifPresentOrElse(
            city -> log.info("Коду {} соответсвует город {}", code, city.toString()),
            () -> log.warn("Город с кодом {} не найден", code)
        );
    }



}

