package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @PostMapping
    public Cart save(@RequestBody Cart cart){
        return cartService.save(cart);
    }

    @GetMapping("/{id}")
    public Cart getById (@PathVariable("id") Long id){
        return cartService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        cartService.deleteById(id);
    }
}
