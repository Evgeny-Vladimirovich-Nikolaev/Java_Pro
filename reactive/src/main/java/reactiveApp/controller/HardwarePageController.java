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
import reactiveApp.repostiory.HardwareTypeRepository;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@Controller
@RequiredArgsConstructor
public class HardwarePageController {

    private final HardwareTypeRepository typeRepository;
    private final HardwareTypeMapper typeMapper;

    @GetMapping("/types")
    public String index(Model model) {
        model.addAttribute("type", typeRepository.findAll());
        return "type/types";
    }

    @GetMapping("/type/add")
    public String newHardwareType() {
        return "type/type";
    }

    @GetMapping("/type/edit")
    public String currentHardwareType(@RequestParam("code") String code, Model model) {
        model.addAttribute("type", typeRepository.findById(code));
        return "type/type";
    }

    @PostMapping("/type/save")
    public Mono<String> saveHardwareType(@Valid HardwareTypeDto typeDto) {
        return typeRepository.save(typeMapper.toEntity(typeDto)).map(g ->
                "redirect:/types"
        );
    }
}
