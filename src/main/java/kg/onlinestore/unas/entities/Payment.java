package kg.onlinestore.unas.entities;

import kg.onlinestore.unas.enums.Currency;
import kg.onlinestore.unas.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreatedDate
    @Column(name = "created_date")
    Date createdDate = new Date();

    @Column(name = "amount")
    BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "wallet_from_id", referencedColumnName = "id")
    Wallet walletFrom;

    @ManyToOne
    @JoinColumn(name = "wallet_to_id", referencedColumnName = "id")
    Wallet walletTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    Currency currency;

    @Column(name = "confirmation_code")
    String confirmationCode;
}
