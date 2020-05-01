package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.User;

public interface CartService extends BaseService<Cart> {
    Cart findByUser(User user);
}
