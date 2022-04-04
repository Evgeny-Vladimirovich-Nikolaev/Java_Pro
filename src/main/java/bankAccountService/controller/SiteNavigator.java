package bankAccountService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class SiteNavigator {
    @GetMapping
    public String create() {
        return null;
    }

    public String enter(String owner, Long id) {
        return null;
    }

    public String deposit(String owner, Long id) {
        return null;
    }

    public String withdraw(String owner, Long id) {
        return null;
    }

    public String withdrawAndClose(String owner, Long id) {
        return null;
    }

    public String Exit() {
        return null;
    }
}
