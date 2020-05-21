package kg.onlinestore.unas.services.implementation;

import kg.onlinestore.unas.entities.*;
import kg.onlinestore.unas.enums.Status;

import kg.onlinestore.unas.exceptions.WrongBalanceException;
import kg.onlinestore.unas.repositories.PaymentChequeRepo;

import kg.onlinestore.unas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    private CartItemService cartItemService;


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
    public PaymentCheque createPayment(String login) throws WrongBalanceException {
        User user = userService.findByLogin(login);
        Wallet walletFrom = walletService.findByUser(user);
        Wallet walletTo = walletService.getById(1L);
        if(walletFrom.getBalance().compareTo(BigDecimal.ZERO) <= 0) throw new WrongBalanceException();
        PaymentCheque paymentCheque = new PaymentCheque().builder()
                .amount(cartService.findByUser(user).getTotalAmount())
                .createdDate(new Date())
                .currency(walletFrom.getCurrency())
                .walletFrom(walletFrom)
                .walletTo(walletTo)
                .status(Status.SUCCESS)
                .build();

        return paymentProcess(paymentCheque);
    }

    @Override
    public List<PaymentCheque> findAllByWalletFrom(Wallet wallet) {
        return paymentChequeRepo.findAllByWalletFrom(wallet);
    }

    private PaymentCheque paymentProcess(PaymentCheque paymentCheque) {
        Wallet from = walletService.getById(paymentCheque.getWalletFrom().getId());
        from.setBalance(from.getBalance().subtract(paymentCheque.getAmount()));
        Wallet to = walletService.getById(paymentCheque.getWalletTo().getId());
        to.setBalance(to.getBalance().add(paymentCheque.getAmount()));
        walletService.save(from);
        walletService.save(to);

        Cart cart = cartService.findByUser(from.getUser());
        List<CartItem> cartItemList = cartItemService.findAllByCart_IdAndStatus(cart.getId(), Status.NOT_PURCHASED);
        for (CartItem cartItem : cartItemList) {
            cartItem.setStatus(Status.PURCHASED);
            cartItemService.save(cartItem);
        }
        return save(paymentCheque);
    }



}
