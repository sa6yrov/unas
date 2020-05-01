package kg.onlinestore.unas.services;

import kg.onlinestore.unas.entities.PaymentProcessLog;
import kg.onlinestore.unas.enums.Status;

public interface PaymentProcessLogService extends BaseService<PaymentProcessLog> {
    Long count (Long id, Status status);

    PaymentProcessLog findPaymentProcessLogByPayment_Id (Long id);
}
