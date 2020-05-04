package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart_IdAndStatus_NotPurchased(Long id);
}
