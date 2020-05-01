package kg.onlinestore.unas.entities;

import kg.onlinestore.unas.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payment_process_log")
public class PaymentProcessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    Payment payment;

    @CreatedDate
    @Column(name = "payment_confirmation_date")
    Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;
}
