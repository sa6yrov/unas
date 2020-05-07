package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.exceptions.WrongBalanceException;

public interface PaymentChequeService extends BaseService<PaymentCheque> {
    PaymentCheque createPayment(String login) throws WrongBalanceException;



}
