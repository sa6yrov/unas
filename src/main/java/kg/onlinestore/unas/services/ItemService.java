package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.models.ItemModel;

import java.util.List;

public interface ItemService extends BaseService<Item> {
    Item create (ItemModel itemModel);

    List<Item> findAllByCategory_IdAndStatus(Long id, Status status);

    List<Item> findAllByStatus(Status status);

}
