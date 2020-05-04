package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);

}
