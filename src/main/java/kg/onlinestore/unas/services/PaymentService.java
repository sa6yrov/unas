package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.Payment;
import kg.onlinestore.unas.models.ConfirmationCodeModel;

public interface PaymentService extends BaseService<Payment> {
    Payment createPayment(Payment payment);

    Payment confirmPayment(ConfirmationCodeModel confirmationCodeModel) throws Exception;

}
