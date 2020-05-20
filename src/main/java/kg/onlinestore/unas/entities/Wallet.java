package kg.onlinestore.unas.entities;

import kg.onlinestore.unas.enums.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(name = "requisite", nullable = false, unique = true)
    String requisite;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    Currency currency;

    @Column(name = "balance")
    BigDecimal balance;

    @Column(name = "bank_card")
    String bankCard;
}
