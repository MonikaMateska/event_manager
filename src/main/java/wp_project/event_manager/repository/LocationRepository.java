package wp_project.event_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wp_project.event_manager.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
