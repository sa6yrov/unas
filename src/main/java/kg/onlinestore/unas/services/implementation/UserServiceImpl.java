package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.UserRole;
import kg.onlinestore.unas.repositories.UserRepo;
import kg.onlinestore.unas.services.CartService;
import kg.onlinestore.unas.services.UserRoleService;
import kg.onlinestore.unas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private CartService cartService;

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public User save(User object) {
        return userRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) userRepo.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepo.save(user);

        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        userRole.setUser(user);
        userRoleService.save(userRole);

        Cart cart = new Cart();
        cart.setTotalAmount(new BigDecimal(0));
        cart.setUser(user);
        cartService.save(cart);

        return user;
    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login).orElse(null);
    }

}