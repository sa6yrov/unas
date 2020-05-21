package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.exceptions.WrongBalanceException;

import java.util.List;

public interface PaymentChequeService extends BaseService<PaymentCheque> {
    PaymentCheque createPayment(String login) throws WrongBalanceException;

    List<PaymentCheque> findAllByWalletFrom(Wallet wallet);


}
