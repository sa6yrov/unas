package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.models.ReplenishModel;
import kg.onlinestore.unas.models.WalletModel;
import kg.onlinestore.unas.repositories.WalletRepo;
import kg.onlinestore.unas.services.UserService;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private UserService userService;

    @Override
    public List<Wallet> getAll() {
        return walletRepo.findAll();
    }

    @Override
    public Wallet getById(Long id) {
        Optional<Wallet> optionalWallet = walletRepo.findById(id);
        return optionalWallet.orElse(null);
    }

    @Override
    public Wallet save(Wallet object) {
        return walletRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        walletRepo.deleteById(id);
    }

    @Override
    public Wallet create(WalletModel walletModel, String login) {
        Wallet wallet = new Wallet();
        User user = userService.findByLogin(login);
        if(user == null) return null;
        wallet.setUser(user);
        wallet.setRequisite(walletModel.getRequisite());
        wallet.setBalance(new BigDecimal(0));
        wallet.setCurrency(walletModel.getCurrency());
        wallet.setBankCard(walletModel.getBankCard());

        return save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepo.findByUser(user);
    }

    @Override
    public String replenish(ReplenishModel replenishModel, String login) {
        User user = userService.findByLogin(login);
        Wallet wallet = findByUser(user);
        if(replenishModel.getBankCard().equals(wallet.getBankCard())){
            wallet.setBalance(wallet.getBalance().add(replenishModel.getAmount()));
            save(wallet);
            return "You have successfully topped up your balance";
        }
        return "Sorry, the balance is not topped up";
    }

    @Override
    public String generateRequisite() {
        return "" + System.currentTimeMillis();
    }
}
