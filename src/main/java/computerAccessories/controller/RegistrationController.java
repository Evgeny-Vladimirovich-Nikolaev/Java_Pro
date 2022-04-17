package computerAccessories.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import computerAccessories.dto.UserDto;
import computerAccessories.service.UserServic;

import javax.validation.Valid;

@Controller
@Validated
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServic userServic;

    @GetMapping("/regPage")
    public String getRegistrationPage() {
        return "registration/registrationPage";
    }

    @PostMapping("/registration")
    public String createUser(@Valid UserDto userDto) {
        userServic.save(userDto);
        return "redirect:/login";
    }

}
