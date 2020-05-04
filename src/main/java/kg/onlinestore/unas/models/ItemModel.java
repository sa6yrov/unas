package kg.onlinestore.unas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemModel {

    String itemName;
    Long categoryId;
    BigDecimal price;
    Integer discountPercentages;
    
    
}
