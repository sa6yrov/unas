package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.exceptions.WrongBalanceException;
import kg.onlinestore.unas.models.ItemQuantityViewModel;
import kg.onlinestore.unas.services.CartItemService;
import kg.onlinestore.unas.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;
//
//    @GetMapping
//    public List<Cart> getAll(){
//        return cartService.getAll();
//    }
//
//    @PostMapping
//    public Cart save(@RequestBody Cart cart){
//        return cartService.save(cart);
//    }
//
//    @GetMapping("/{id}")
//    public Cart getById (@PathVariable("id") Long id){
//        return cartService.getById(id);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteOrder(@PathVariable("id") Long id) {
//        cartService.deleteById(id);
//    }

    @GetMapping("/my")
    public List<ItemQuantityViewModel> getAll(Principal principal){

        return cartItemService.getItemViews(principal.getName());
    }

    @GetMapping("/my/purchase")
    public ResponseEntity<PaymentCheque> buyItems(Principal principal) throws WrongBalanceException {
        return new ResponseEntity<>(cartService.buy(principal.getName()), HttpStatus.OK);
    }
}
