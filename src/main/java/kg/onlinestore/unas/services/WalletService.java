package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.models.ReplenishModel;
import kg.onlinestore.unas.models.WalletModel;

public interface WalletService extends BaseService<Wallet> {
    Wallet create (WalletModel walletModel, String login);

    Wallet findByUser(User user);

    String replenish(ReplenishModel replenishModel, String login);

    String generateRequisite();
}
