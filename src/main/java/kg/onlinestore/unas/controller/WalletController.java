package kg.onlinestore.unas.controller;

import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.models.ReplenishModel;
import kg.onlinestore.unas.models.WalletModel;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
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
    public ResponseEntity<Wallet> save (@RequestBody WalletModel walletModel, Principal principal){
        return new ResponseEntity<Wallet>( walletService.create(walletModel, principal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteById(id);
    }

    @PostMapping("/replenish")
    public String replenish(@RequestBody ReplenishModel replenishModel, Principal principal){
        return walletService.replenish(replenishModel, principal.getName());
    }

}
