package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.entities.Item;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;
import kg.onlinestore.unas.repositories.CartItemRepo;
import kg.onlinestore.unas.services.CartItemService;
import kg.onlinestore.unas.services.CartService;
import kg.onlinestore.unas.services.ItemService;
import kg.onlinestore.unas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Override
    public List<CartItem> getAll() {
        return cartItemRepo.findAll();
    }

    @Override
    public CartItem getById(Long id) {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(id);
        return cartItemOptional.orElse(null);
    }

    @Override
    public CartItem save(CartItem object) {
        return cartItemRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) cartItemRepo.deleteById(id);
    }

    @Override
    public CartItem create(CartItemModel cartItemModel, String login) {
        User user = userService.findByLogin(login);
        Cart cart = cartService.findByUser(user);
        Item item = itemService.getById(cartItemModel.getItemId());
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setUnitItemPrice(item.getPrice().subtract
                (item.getPrice().divide(new BigDecimal(100)).multiply(new BigDecimal(item.getDiscountPercentages()))));
        cartItem.setItemsQuantity(cartItemModel.getItemsQuantity());
        cartItem.setStatus(Status.NOT_PURCHASED);

        return save(cartItem);
    }

    @Override
    public List<CartItem> findAllByCart_Id(Long id) {
        return cartItemRepo.findAllByCart_Id(id);
    }

    @Override
    public List<ItemQuantityViewModel> getItemViews(String  login) {
        User user = userService.findByLogin(login);
        Cart cart = cartService.findByUser(user);
        List<ItemQuantityViewModel> itemQuantityViewModelList = new ArrayList<>();
        List<CartItem> cartItemList = findAllByCart_Id(cart.getId());
        for (CartItem cartItem : cartItemList) {
            Item item = cartItem.getItem();

            ItemQuantityViewModel itemQuantityViewModel = new ItemQuantityViewModel();
            itemQuantityViewModel.setItem(item);
            itemQuantityViewModel.setQuantity(cartItem.getItemsQuantity());
            itemQuantityViewModelList.add(itemQuantityViewModel);
        }
        return itemQuantityViewModelList;
    }
}
