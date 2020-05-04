package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;

import java.security.Principal;
import java.util.List;

public interface CartItemService extends BaseService<CartItem> {
    CartItem create(CartItemModel cartItemModel, String login);

    List<CartItem> findAllByCart_IdAndStatus_NotPurchased(Long id);

    List<ItemQuantityViewModel> getItemViews(String login);
}
