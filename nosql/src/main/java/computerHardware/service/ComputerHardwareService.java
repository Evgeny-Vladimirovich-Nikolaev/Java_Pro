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
    Optional<ComputerHardwareDto> getById(String bookId);
    ComputerHardwareDto save(@Valid ComputerHardwareDto book);
    void partialSave(@Valid ComputerHardwareDto book);
    void deleteById(String bookId);
    void delete(@Valid ComputerHardwareDto book);
}
