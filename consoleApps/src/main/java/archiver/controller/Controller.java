package archiver.controller;

import archiver.service.ArchiveService;
import archiver.service.impl.ArchiveServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class Controller {

    private static final String APP_ID = "appId";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Controller.class);

    @Autowired
    private final ArchiveService archiveService;

    @ShellMethod(value = "zip", key = "file")
    public void zip(@ShellOption("-f") String file) {
        archiveService.zip(file);
    }

    @ShellMethod(value = "unzip", key = {"file", "password"})
    public void unzip(@ShellOption("-f") String file, @ShellOption("-p") String password) {
        archiveService.unzip(file, password);
    }




}

