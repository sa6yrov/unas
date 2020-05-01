package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.models.WalletModel;

public interface WalletService extends BaseService<Wallet> {
    Wallet create (WalletModel walletModel);
}
