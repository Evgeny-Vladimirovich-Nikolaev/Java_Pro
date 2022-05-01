package computerHardware.service;

import computerHardware.dto.ComputerHardwareDto;
import computerHardware.dto.ComputerHardwarePageDto;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface ComputerHardwareService {
    List<ComputerHardwareDto> findAll();
    ComputerHardwarePageDto getPage(Pageable pageable);
    Optional<ComputerHardwareDto> getById(String id);
    ComputerHardwareDto save(@Valid ComputerHardwareDto dto);
    void partialSave(@Valid ComputerHardwareDto dto);
    void deleteById(String id);
    void delete(@Valid ComputerHardwareDto dto);
}
