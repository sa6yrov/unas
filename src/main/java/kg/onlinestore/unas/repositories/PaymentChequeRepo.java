package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.PaymentCheque;
import kg.onlinestore.unas.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentChequeRepo extends JpaRepository<PaymentCheque, Long> {
    List<PaymentCheque> findAllByWalletFrom(Wallet wallet);
}
