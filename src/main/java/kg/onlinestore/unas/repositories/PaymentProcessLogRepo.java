package kg.onlinestore.unas.repositories;


import kg.onlinestore.unas.entities.PaymentProcessLog;
import kg.onlinestore.unas.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentProcessLogRepo extends JpaRepository<PaymentProcessLog, Long> {
    PaymentProcessLog findPaymentProcessLogByPayment_Id (Long id);

    @Query("select count(p.id)  from PaymentProcessLog p join p.payment where p.payment.id = ?1 and p.status = ?2")
    Long count(Long id, Status status);
}
