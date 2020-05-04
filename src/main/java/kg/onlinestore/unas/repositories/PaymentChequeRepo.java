package kg.onlinestore.unas.repositories;

import kg.onlinestore.unas.entities.PaymentCheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentChequeRepo extends JpaRepository<PaymentCheque, Long> {
}
