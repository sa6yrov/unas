package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.CartItem;
import kg.onlinestore.unas.models.CartItemModel;
import kg.onlinestore.unas.models.CartItemResponseModel;
import kg.onlinestore.unas.models.ItemQuantityViewModel;
import kg.onlinestore.unas.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<?> putIntoCart(@RequestBody CartItemModel cartItemModel, Principal principal){
        try {
            return new ResponseEntity<>(cartItemService.create(cartItemModel, principal.getName()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/my")
//    public List<ItemQuantityViewModel> getAll(Principal principal){
//
//        return cartItemService.getItemViews(principal.getName());
//    }

//    @PostMapping("/buy")
//    public ResponseEntity buyItems(@PathVariable Long cartId){
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
