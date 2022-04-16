package bankAccountService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank-pages")
public class SiteNavigator {
    @GetMapping("/create")
    public String create() {
        return "/bank/create.html";
    }

    @GetMapping("/enter")
    public String enter() {
        return "/bank/enter";
    }

    @GetMapping("/start")
    public String start() {
        return "/bank/start";
    }

//    @GetMapping("exit")
//    public String exit() {
//        return "/";
//    }
}
