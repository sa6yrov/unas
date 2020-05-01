package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.UserRole;
import kg.onlinestore.unas.models.UserRoleModel;
import kg.onlinestore.unas.repositories.UserRoleRepo;
import kg.onlinestore.unas.services.UserRoleService;
import kg.onlinestore.unas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserService userService;

    @Override
    public List<UserRole> getAll() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole getById(Long id) {
        Optional<UserRole> userRoleOptional = userRoleRepo.findById(id);
        return userRoleOptional.orElse(null);
    }

    @Override
    public UserRole save(UserRole object) {
        return userRoleRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) userRoleRepo.deleteById(id);
    }

    @Override
    public UserRole create(UserRoleModel userRoleModel) {
        User user = userService.getById(userRoleModel.getUserId());
        if(user == null) return null;

        UserRole userRole = new UserRole();
        userRole.setRoleName(userRoleModel.getRoleName());
        userRole.setUser(user);
        return userRoleRepo.save(userRole);
    }
}
