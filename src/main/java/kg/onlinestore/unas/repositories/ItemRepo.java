package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findAllByCategory_IdAndStatus(Long id, Status status);

    List<Item> findAllByStatus(Status status);

}
