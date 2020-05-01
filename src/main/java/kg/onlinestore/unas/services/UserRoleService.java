package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.UserRole;
import kg.onlinestore.unas.models.UserRoleModel;

public interface UserRoleService extends BaseService<UserRole> {
    UserRole create (UserRoleModel userRoleModel);

}
