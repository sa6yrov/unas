package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.models.CartItemHistoryModel;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.CartItemResponseModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;

import java.security.Principal;
import java.util.List;

public interface CartItemService extends BaseService<CartItem> {
    CartItemResponseModel create(CartItemModel cartItemModel, String login);

    List<CartItem> findAllByCart_IdAndStatus(Long id, Status status);

    List<ItemQuantityViewModel> getItemViews(String login);

    List<CartItemHistoryModel> getAllPurchasedCartItems(String login);
}
