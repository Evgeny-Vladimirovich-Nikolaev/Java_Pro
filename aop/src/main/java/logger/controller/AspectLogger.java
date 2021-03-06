package logger.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Slf4j
@Validated
@RestController
public class AspectLogger {

    @GetMapping("/logger")
    public void aspect(@RequestParam @NotEmpty String target,
                       @RequestParam @NotEmpty String method,
                       @RequestParam String params) {
        log.info("Был вызван метод {} объекта {} с параметрами {}", method, target, params);
    }
}
