package reactiveApp.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactiveApp.dto.HardwareTypeDto;
import reactiveApp.service.HardwareTypeService;

@RestController
@RequiredArgsConstructor
public class AccessoryController {

    private final HardwareTypeService service;

    @DeleteMapping("/accessories/{code}")
    public ResponseEntity<?> delete(@PathVariable("code") String code) {
        service.delete(code);
        return ResponseEntity.ok(new HardwareTypeDto());
    }
}

