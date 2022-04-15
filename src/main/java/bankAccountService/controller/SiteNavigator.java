package bankAccountService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class SiteNavigator {
    @GetMapping("/bank/create")
    public String create() {
        return "/bank/create";
    }

    @GetMapping("/bank/enter")
    public String enter() {
        return "/bank/enter";
    }

//    @GetMapping("exit")
//    public String exit() {
//        return "/";
//    }
}
