package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.Payment;
import kg.onlinestore.unas.entities.PaymentProcessLog;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.enums.Status;
import kg.onlinestore.unas.exceptions.WrongPaymentProcessException;
import kg.onlinestore.unas.models.ConfirmationCodeModel;
import kg.onlinestore.unas.repositories.PaymentRepo;
import kg.onlinestore.unas.services.PaymentProcessLogService;
import kg.onlinestore.unas.services.PaymentService;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private WalletService walletService;

    @Autowired
    private PaymentProcessLogService paymentProcessLogService;

    @Override
    public List<Payment> getAll() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment getById(Long id) {
        Optional<Payment> optionalPayment = paymentRepo.findById(id);
        return optionalPayment.get();
    }

    @Override
    public Payment save (Payment object) {
        return paymentRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if(getById(id) != null) paymentRepo.deleteById(id);

    }

    @Override
    public Payment createPayment(Payment payment) {
        payment.setStatus(Status.PROCESSING);
        Random rnd = new Random();
        String code = "";
        for (int i = 0; i < 2; i++) {
            code += (char) (97 + rnd.nextInt(122 - 97 + 1)) + "" + rnd.nextInt(100);
        }
        payment.setConfirmationCode(code);
        return save(payment);
    }

    @Override
    public Payment confirmPayment(ConfirmationCodeModel confirmationCodeModel) throws Exception {
        Payment payment = getById(confirmationCodeModel.getPaymentId());

        if(payment.getStatus().equals(Status.SUCCESS)) throw new WrongPaymentProcessException("This payment already completed");

        else if(payment.getStatus().equals(Status.BLOCKED)) throw new WrongPaymentProcessException("This payment was blocked");

        Status status;
        if(!payment.getConfirmationCode().equals(confirmationCodeModel.getConfirmationCode())){
            status = Status.FAILED;
            if(paymentProcessLogService.count(payment.getId(), Status.FAILED) >= 3L) status = Status.BLOCKED;
        }else status = Status.SUCCESS;

        payment.setStatus(status);

        paymentProcessLogService.save(PaymentProcessLog.builder()
                .payment(payment)
                .date(new Date())
                .status(status)
                .build());

        paymentProcess(payment);
        return payment;
    }

    private void paymentProcess(Payment payment){
        if (payment.getStatus().equals(Status.SUCCESS)) {
            Wallet from = walletService.getById(payment.getWalletFrom().getId());
            from.setBalance(from.getBalance().subtract(payment.getAmount()));
            Wallet to = walletService.getById(payment.getWalletTo().getId());
            to.setBalance(to.getBalance().add(payment.getAmount()));
            walletService.save(from);
            walletService.save(to);
        }
        save(payment);
    }


}
