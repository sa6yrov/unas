package kg.onlinestore.unas.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    Item item;

    @Column(name = "unit_item_price")
    BigDecimal unitItemPrice;

    @Column(name = "items_quantity")
    Integer itemsQuantity;
}
