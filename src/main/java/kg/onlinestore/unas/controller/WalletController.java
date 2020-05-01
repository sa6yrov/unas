package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.models.WalletModel;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getAll(){
        return walletService.getAll();
    }

    @GetMapping("/{id}")
    public Wallet getById(@PathVariable("id") Long id){
        return walletService.getById(id);
    }

    @PostMapping
    public Wallet save (@RequestBody WalletModel walletModel){
        return walletService.create(walletModel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteById(id);
    }

}
