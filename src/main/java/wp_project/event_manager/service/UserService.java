package wp_project.event_manager.service;

import wp_project.event_manager.model.Role;
import wp_project.event_manager.model.User;

public interface UserService {

    User findByUsername(String username);

    User create(String username, String password, Role role);

    User update(String username, String password, Role role);

    User delete(String username);
}
