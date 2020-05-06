package kg.onlinestore.unas.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "item_name")
    String itemName;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "discount_percentages")
    Integer discountPercentages;

    @ManyToMany(mappedBy = "items")
    List<Set> sets;

}
