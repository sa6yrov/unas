package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.*;
import kg.onlinestore.unas.models.ItemQuantityViewModel;
import kg.onlinestore.unas.repositories.CartRepo;
import kg.onlinestore.unas.services.CartItemService;
import kg.onlinestore.unas.services.CartService;
import kg.onlinestore.unas.services.PaymentChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private PaymentChequeService paymentChequeService;

    @Override
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepo.findByUser(user);
    }

    @Override
    public List<ItemQuantityViewModel> showItemViews(String login) {
        return cartItemService.getItemViews(login);
    }

    @Override
    public PaymentCheque buy(String login) {
        return paymentChequeService.createPayment(login);

    }


}
