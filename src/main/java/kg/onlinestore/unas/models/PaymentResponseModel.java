package kg.onlinestore.unas.models;

import kg.onlinestore.unas.enums.Currency;
import kg.onlinestore.unas.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponseModel{
    String consumer;
    String requisiteOfConsumer;
    String requisiteOfSeller;
    String purchasedDate;
    BigDecimal amount;
    Currency currency;
    Status status;
}
