package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.PaymentCheque;

public interface PaymentChequeService extends BaseService<PaymentCheque> {
    PaymentCheque createPayment(String login);



}
