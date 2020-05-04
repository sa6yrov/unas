package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.entities.User;
import kg.onlinestore.unas.entities.Wallet;
import kg.onlinestore.unas.enums.Status;

import kg.onlinestore.unas.repositories.PaymentChequeRepo;

import kg.onlinestore.unas.services.CartService;
import kg.onlinestore.unas.services.PaymentChequeService;
import kg.onlinestore.unas.services.UserService;
import kg.onlinestore.unas.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PaymentChequeServiceImpl implements PaymentChequeService {
    @Autowired
    private PaymentChequeRepo paymentChequeRepo;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;


    @Override
    public List<PaymentCheque> getAll() {
        return paymentChequeRepo.findAll();
    }

    @Override
    public PaymentCheque getById(Long id) {
        Optional<PaymentCheque> optionalPayment = paymentChequeRepo.findById(id);
        return optionalPayment.get();
    }

    @Override
    public PaymentCheque save(PaymentCheque object) {
        return paymentChequeRepo.save(object);
    }

    @Override
    public void deleteById(Long id) {
        if (getById(id) != null) paymentChequeRepo.deleteById(id);
    }


    @Override
    public PaymentCheque createPayment(String login) {
        User user = userService.findByLogin(login);
        Wallet walletFrom = walletService.findByUser(user);
        Wallet walletTo = walletService.getById(1L);

        PaymentCheque paymentCheque = new PaymentCheque();
        paymentCheque.builder()
                .amount(cartService.findByUser(user).getTotalAmount())
                .currency(walletFrom.getCurrency())
                .walletFrom(walletFrom)
                .walletTo(walletTo)
                .status(Status.SUCCESS)
                .build();

        return paymentProcess(paymentCheque);
    }

    private PaymentCheque paymentProcess(PaymentCheque paymentCheque) {
        Wallet from = walletService.getById(paymentCheque.getWalletFrom().getId());
        from.setBalance(from.getBalance().subtract(paymentCheque.getAmount()));
        Wallet to = walletService.getById(paymentCheque.getWalletTo().getId());
        to.setBalance(to.getBalance().add(paymentCheque.getAmount()));
        walletService.save(from);
        walletService.save(to);

        return save(paymentCheque);
    }

}
