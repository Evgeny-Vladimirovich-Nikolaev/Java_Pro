package reactiveApp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactiveApp.dto.HardwareTypeDto;
import reactiveApp.mapper.HardwareTypeMapper;
import reactor.core.publisher.Mono;


import reactiveApp.model.HardwareType;
import reactiveApp.service.HardwareTypeService;


@Controller
@Validated
@RequiredArgsConstructor
public class AccessoryPageController {

    private final HardwareTypeService service;
    private final HardwareTypeMapper mapper;

    @GetMapping("/accessories")
    public String index(Model model) {
        model.addAttribute("accessories", service.findAll());
        return "accessory/accessories";
    }

    @GetMapping("/accessory/add")
    public String newAccessory(Model model) {
        return "accessory/accessory";
    }

    @GetMapping("/accessory/edit")
    public String currentAccessory(@RequestParam("code") String code, Model model) {
        Mono<HardwareType> type = service.findByCode(code);
        model.addAttribute("accessory", type);
        return "accessory/accessory";
    }


    @PostMapping("/accessory/save")
    public String saveAccessory(HardwareTypeDto dto) {
        service.save(mapper.toEntity(dto));
        return "redirect:/accessories";
    }
}
