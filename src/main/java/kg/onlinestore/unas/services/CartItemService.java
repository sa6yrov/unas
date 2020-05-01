package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;

import java.util.List;

public interface CartItemService extends BaseService<CartItem> {
    CartItem create(CartItemModel cartItemModel);

    List<CartItem> findAllByCart_Id(Long id);

    List<ItemQuantityViewModel> getItemViews(Long cartId);
}
