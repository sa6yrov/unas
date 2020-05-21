package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.*;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.models.CartItemHistoryModel;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;
import kg.onlinestore.unas.repositories.CartItemRepo;
import kg.onlinestore.unas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private WalletService walletService;

    @Autowired
    private PaymentChequeService paymentChequeService;


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

        cart.setTotalAmount(cart.getTotalAmount().add(cartItem.getUnitItemPrice().multiply(new BigDecimal(cartItem.getItemsQuantity()))));
        cartService.save(cart);

        return save(cartItem);
    }

    @Override
    public List<CartItem> findAllByCart_IdAndStatus(Long id, Status status) {
        return cartItemRepo.findAllByCart_IdAndStatus(id, status);
    }

    @Override
    public List<ItemQuantityViewModel> getItemViews(String  login) {
        User user = userService.findByLogin(login);
        Cart cart = cartService.findByUser(user);
        List<ItemQuantityViewModel> itemQuantityViewModelList = new ArrayList<>();
        List<CartItem> cartItemList = findAllByCart_IdAndStatus(cart.getId(), Status.NOT_PURCHASED);
        for (CartItem cartItem : cartItemList) {
            Item item = cartItem.getItem();

            ItemQuantityViewModel itemQuantityViewModel = new ItemQuantityViewModel();
            itemQuantityViewModel.setItem(item);
            itemQuantityViewModel.setQuantity(cartItem.getItemsQuantity());
            itemQuantityViewModelList.add(itemQuantityViewModel);
        }
        return itemQuantityViewModelList;
    }

    @Override
    public List<CartItemHistoryModel> getAllPurchasedCartItems(String login) {
        User user = userService.findByLogin(login);
        Cart cart = cartService.findByUser(user);
        Wallet wallet = walletService.findByUser(user);
        List<CartItemHistoryModel> cartItemHistoryModels = new ArrayList<>();

        List<CartItem> cartItems = findAllByCart_IdAndStatus(cart.getId(), Status.PURCHASED);
        List<PaymentCheque> paymentCheques = paymentChequeService.findAllByWalletFrom(wallet);

        for (CartItem c : cartItems) {
            CartItemHistoryModel cartItemHistoryModel = new CartItemHistoryModel();
            cartItemHistoryModel.setItemName(c.getItem().getItemName());
            cartItemHistoryModel.setItemQuantity(c.getItemsQuantity());
            cartItemHistoryModel.setStatus(c.getStatus());
            cartItemHistoryModel.setAmount(c.getUnitItemPrice().multiply(new BigDecimal(c.getItemsQuantity())));
            for (PaymentCheque p : paymentCheques) {
                cartItemHistoryModel.setPurchasedDate(p.getCreatedDate().toString());
            }
            cartItemHistoryModels.add(cartItemHistoryModel);
        }
        return cartItemHistoryModels;
    }


}
