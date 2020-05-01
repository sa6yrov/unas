package kg.onlinestore.unas.services.implementation;


import kg.onlinestore.unas.entities.PaymentProcessLog;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.repositories.PaymentProcessLogRepo;
import kg.onlinestore.unas.services.PaymentProcessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentProcessLogServiceImpl implements PaymentProcessLogService {
    @Autowired
    PaymentProcessLogRepo paymentProcessLogRepo;


    @Override
    public PaymentProcessLog getById(Long id) {
        Optional<PaymentProcessLog> optionalPaymentProcessLog =  paymentProcessLogRepo.findById(id);
        return optionalPaymentProcessLog.orElse(null);
    }

    @Override

    public PaymentProcessLog save(PaymentProcessLog object) {
        return paymentProcessLogRepo.save(object);

    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) paymentProcessLogRepo.deleteById(id);
    }

    @Override
    public List<PaymentProcessLog> getAll() {
        return paymentProcessLogRepo.findAll();
    }




    @Override
    public Long count(Long id, Status status) {
        return paymentProcessLogRepo.count(id, status);
    }

    @Override
    public PaymentProcessLog findPaymentProcessLogByPayment_Id(Long id) {
        return paymentProcessLogRepo.findPaymentProcessLogByPayment_Id(id);
    }


}
