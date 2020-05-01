package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.User;

import java.util.Optional;

public interface UserService extends BaseService<User> {
    User createUser(User user);
    User findByLogin (String login);
}
