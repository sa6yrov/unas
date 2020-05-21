package kg.onlinestore.unas.models;

import kg.onlinestore.unas.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemHistoryModel {
    String itemName;
    Integer itemQuantity;
    String purchasedDate;
    BigDecimal amount;
    Status status;
}
