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
public class GenrePageController {

    private final HardwareTypeRepository genreRepository;
    private final HardwareTypeMapper genreMapper;

    @GetMapping("/genres")
    public String index() {
        return "genre/genres";
    }

    @GetMapping("/genre/add")
    public String newGenre() {
        return "genre/genre";
    }

    @GetMapping("/genre/edit")
    public String currentGenre(@RequestParam("code") String genreCode, Model model) {
        model.addAttribute("genre", genreRepository.findById(genreCode));
        return "genre/genre";
    }

    @PostMapping("/genre/save")
    public Mono<String> saveGenre(@Valid HardwareTypeDto genre) {
        return genreRepository.save(genreMapper.toEntity(genre)).map(g ->
                "redirect:/genres"
        );
    }
}
