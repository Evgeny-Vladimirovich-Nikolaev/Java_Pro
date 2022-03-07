package cityDirectory.controller;

import bookBase.BaseController;
import org.slf4j.Logger;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;
import java.util.ResourceBundle;

@ShellComponent
public class CityController {

    private ResourceBundle resources = ResourceBundle.getBundle("messages");
    private final String APP_ID = "appId";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    @ShellMethod(value = "change language", key = {"lang", "l"})
    public void changeLocale(@ShellOption({"-l, --lang"}) String lang) {
        resources = ResourceBundle.getBundle("messages", new Locale(lang));
    }

}

