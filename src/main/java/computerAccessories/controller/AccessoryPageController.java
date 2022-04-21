package computerAccessories.controller;

import computerAccessories.dto.AccessoryDto;
import computerAccessories.dto.AccessoryPageDto;
import computerAccessories.service.AccessoryService;
import computerAccessories.validator.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Positive;

@Controller
@Validated
@RequiredArgsConstructor
public class AccessoryPageController {

    private final AccessoryService accessoryService;

    @GetMapping("/accessories")
    public String index(Model model,
                        @PositiveOrZero(originValue = 15) @RequestParam(required = false, defaultValue = "0") Integer page,
                        @Positive @RequestParam(required = false, defaultValue = "5") Integer size) {
        AccessoryPageDto allAccessories = accessoryService.getPage(PageRequest.of(page, size));
        model.addAttribute("accessories", allAccessories);
        return "accessory/accessories";
    }

    @GetMapping("/accessory/add")
    public String newAccessory(Model model) {
        return "accessory/accessory";
    }

    @GetMapping("/accessory/edit")
    public String currentAccessory(@RequestParam("code") String accessoryCode, Model model) {
        AccessoryDto currentAccessory = accessoryService.getByCode(accessoryCode)
                .orElseThrow(() -> new IllegalArgumentException("Non existed accessory"));
        model.addAttribute("accessory", currentAccessory);
        return "accessory/accessory";
    }


    @PostMapping("/accessory/save")
    public String saveAccessory(AccessoryDto accessory) {
        accessoryService.save(accessory);
        return "redirect:/accessories";
    }
}
