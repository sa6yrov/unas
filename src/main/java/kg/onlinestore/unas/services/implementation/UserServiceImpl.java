package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.UserRole;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.enums.Currency;
import kg.onlinestore.unas.exceptions.WrongUserException;
import kg.onlinestore.unas.models.UserAuth;
import kg.onlinestore.unas.models.UserModel;
import kg.onlinestore.unas.repositories.UserRepo;
import kg.onlinestore.unas.services.CartService;
import kg.onlinestore.unas.services.UserRoleService;
import kg.onlinestore.unas.services.UserService;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;
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

    @Autowired
    private WalletService walletService;

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
    public UserModel createUser(UserModel userModel) throws WrongUserException {


        if(findByLogin(userModel.getLogin()) != null) throw new WrongUserException("User with this username already exists");
        User user = getUserFromUserModel(userModel);
        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_USER");
        userRole.setUser(user);
        userRoleService.save(userRole);

        Wallet wallet = new Wallet();
        wallet.setRequisite(walletService.generateRequisite());
        wallet.setBalance(new BigDecimal(BigInteger.ZERO));
        wallet.setCurrency(Currency.KGZ);
        wallet.setBankCard("VISA");
        wallet.setUser(user);
        walletService.save(wallet);

        Cart cart = new Cart();
        cart.setTotalAmount(new BigDecimal(0));
        cart.setUser(user);
        cartService.save(cart);

        return getUserModel(user);
    }
    private UserModel getUserModel(User user){
        return UserModel.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }
    private User getUserFromUserModel(UserModel userModel){
        User user = new User().builder()
                .login(userModel.getLogin())
                .password(userModel.getPassword())
                .email(userModel.getEmail())
                .isActive(true)
                .build();

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepo.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepo.findByLogin(login).orElse(null);
    }

    @Override
    public String getToken(UserAuth userAuth) {
        User user = findByLogin(userAuth.getLogin());
        if(user == null) return "User with " + userAuth.getLogin() + "not found";
        String rawPassword = userAuth.getPassword();
        String encodedPassword = user.getPassword();
        if(passwordEncoder.matches(rawPassword, encodedPassword)) {
            String loginPasswordPair = userAuth.getLogin() + ":" + userAuth.getPassword();//admin:1234
            String token = Base64.getEncoder().encodeToString(loginPasswordPair.getBytes());
            return "Basic " + token;
        }
        return "Error";
    }

}
