package kg.onlinestore.unas.models;

import kg.onlinestore.unas.entities.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemQuantityViewModel {
    Item item;
    Integer quantity;
}
