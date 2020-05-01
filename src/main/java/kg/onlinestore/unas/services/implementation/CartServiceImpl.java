package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Cart;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.repositories.CartRepo;
import kg.onlinestore.unas.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Override
    public List<Cart> getAll() {
        return cartRepo.findAll();
    }

    @Override
    public Cart getById(Long id) {
        Optional<Cart> optionalOrder = cartRepo.findById(id);
        return optionalOrder.orElse(null);
    }

    @Override
    public Cart save(Cart object) {
        return cartRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) cartRepo.deleteById(id);
    }

    @Override
    public Cart findByUser(User user) {
        return cartRepo.findByUser(user);
    }
}
