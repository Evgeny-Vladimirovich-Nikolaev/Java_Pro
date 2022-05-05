package computerAccessories.repository;

import computerAccessories.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "computer_accessories")
public interface AccessoryRepository extends JpaRepository<Accessory, String> {
}
