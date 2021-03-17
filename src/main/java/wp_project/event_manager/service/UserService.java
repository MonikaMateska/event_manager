package wp_project.event_manager.service;

import wp_project.event_manager.model.Role;
import wp_project.event_manager.model.User;

import java.util.List;

public interface UserService {

    List<User> listAll();

    User findByUsername(String username);

    User create(String username, String password, Role role);

    User update(String username, String password, Role role);

    User delete(String username);
}
