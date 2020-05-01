package kg.onlinestore.unas.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "set")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "set_name")
    String setName;

    @Column(name = "price")
    BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "set_item",
            joinColumns = {@JoinColumn(name = "set_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    List<Item> items;
}
