package computerHardware.service;

import computerHardware.dto.HardwareTypeDto;
import computerHardware.dto.HardwareTypePageDto;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

public interface HardwareTypeService {
    List<HardwareTypeDto> findAll();
    HardwareTypePageDto getPage(Pageable pageable);
    Optional<HardwareTypeDto> getByCode(@NotEmpty String genreCode);
    HardwareTypeDto save(@Valid HardwareTypeDto genre);
    void deleteByCode(@NotEmpty String genreCode);
    void delete(@Valid HardwareTypeDto genre);
}
