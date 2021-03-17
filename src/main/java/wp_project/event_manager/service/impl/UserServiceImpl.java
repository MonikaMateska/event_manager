package wp_project.event_manager.service.impl;

import org.springframework.stereotype.Service;
import wp_project.event_manager.model.Role;
import wp_project.event_manager.model.User;
import wp_project.event_manager.model.exceptions.InvalidUserIdException;
import wp_project.event_manager.repository.UserRepository;
import wp_project.event_manager.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findById(username).orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public User create(String username, String password, Role role) {
       User user = new User(username, password, role);

       return this.userRepository.save(user);
    }

    @Override
    public User update(String username, String password, Role role) {
        User user = this.findByUsername(username);

        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        return this.userRepository.save(user);
    }

    @Override
    public User delete(String username) {
        User user = this.findByUsername(username);

        this.userRepository.delete(user);

        return user;
    }
}
