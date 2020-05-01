package kg.onlinestore.unas.models;

import kg.onlinestore.unas.entities.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartModel {
    Long userId;

    List<ItemQuantityViewModel> itemQuantityViewModels;

    BigDecimal totalAmount;

}
