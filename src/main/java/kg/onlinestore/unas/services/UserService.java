package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.models.UserAuth;
import kg.onlinestore.unas.models.UserModel;

import java.util.Optional;

public interface UserService extends BaseService<User> {
    User createUser(UserModel userModel);

    User findByLogin (String login);

    String getToken(UserAuth userAuth);
}
