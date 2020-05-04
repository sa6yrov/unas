package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.models.ItemQuantityViewModel;

import java.util.List;

public interface CartService {
    Cart save(Cart cart);

    Cart findByUser(User user);

    List<ItemQuantityViewModel> showItemViews(String login);

    PaymentCheque buy(String login);
}
