package wp_project.event_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wp_project.event_manager.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
