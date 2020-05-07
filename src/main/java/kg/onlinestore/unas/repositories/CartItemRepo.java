package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart_IdAndStatus(Long id, Status status);
}
