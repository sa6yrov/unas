package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.models.ItemModel;

public interface ItemService extends BaseService<Item> {
    Item create (ItemModel itemModel);
}
