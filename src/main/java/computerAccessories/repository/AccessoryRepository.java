package computerAccessories.repository;

import computerAccessories.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryRepository extends JpaRepository<Accessory, String> {
}

