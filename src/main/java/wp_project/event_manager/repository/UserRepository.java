package wp_project.event_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wp_project.event_manager.model.User;

public interface UserRepository extends JpaRepository<User, String > {
}
