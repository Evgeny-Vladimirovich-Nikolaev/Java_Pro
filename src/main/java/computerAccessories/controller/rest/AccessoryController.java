package computerAccessories.controller.rest;

import computerAccessories.dto.ResultDto;
import computerAccessories.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @DeleteMapping("/accessories/delete/{code}")
    public ResponseEntity<?> delete(@PathVariable("code") String accessoryCode) {
        accessoryService.deleteByCode(accessoryCode);
        return ResponseEntity.ok(new ResultDto());
    }
}

