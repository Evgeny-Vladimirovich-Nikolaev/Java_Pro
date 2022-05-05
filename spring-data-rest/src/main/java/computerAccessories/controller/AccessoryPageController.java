package computerAccessories.controller;

import computerAccessories.dto.AccessoryDto;
import computerAccessories.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AccessoryPageController {

    private final AccessoryService accessoryService;

    @GetMapping("/accessories")
    public String allAccessories() {
        return "accessory/accessories";
    }

    @GetMapping("/accessory/add")
    public String newAccessory() {
        return "accessory/accessory";
    }

    @GetMapping("/accessory/edit")
    public String currentAccessory(@RequestParam("code") String code, Model model) {
        AccessoryDto dto = accessoryService.getByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Non existed accessory"));
        model.addAttribute("computer_accessories", dto);
        return "accessory/accessory";
    }

    @PostMapping("/accessory/save")
    public String saveAccessory(AccessoryDto dto) {
        accessoryService.save(dto);
        return "redirect:/accessories";
    }
}
