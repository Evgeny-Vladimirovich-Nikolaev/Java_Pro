package lesson40.repository;

import computerHardware.model.HardwareType;
import computerHardware.repository.HardwareTypeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с жанрами книг на основе Mongo JPA должен ")
@DataMongoTest
public class HardwareTypeRepositoryTest {

    @Autowired
    private HardwareTypeRepository repository;

    @Test
    @DisplayName("уметь сохранять жанр и получать информацию по коду")
    public void shouldSaveAndFindByCode() {
        HardwareType hardwareType = repository.save(new HardwareType("Art", "Искусство"));
        assertThat(hardwareType.getCode()).isNotEmpty();
        assertThat(repository.findById("Art")).isNotNull();
    }
}
