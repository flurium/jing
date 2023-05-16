package flurium.jing.db.repositories;

import flurium.jing.db.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
